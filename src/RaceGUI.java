//Dorin, Dov did some edits/debugging related to restart logic


import javax.swing.*;
import java.awt.*;
import java.util.Random;


/*
 * RaceGUI handles all GUI related things as well as running the race itself through the methods in RacingVenue.
 * It provides the logic for car customization, starting the race, and restarting the game. It takes input from the user through
 * JButtons and JComboBoxes.
 */

public class RaceGUI {

    // main method to launch the game
    //Dov
    public static void main(String[] args) {
        new RaceGUI();
    }

    private JFrame frame;
    private RacingVenue venue;
    private int numCars;
    private  Car[] carConfigurations;

    // constructor to start the game
    //Dov
    public RaceGUI() {
        startGame();
    }

    // creates a RacingVenue object with the selected car configurations, initializes the GUI, and starts the race
    //Dorin with minor Dov edits
    public void getRacingVenue(Car[] carConfigurations) {
        // if the frame is already visible, dispose of it to create a new one
        if (this.frame != null) {
            this.frame.setVisible(false);
            this.frame.dispose();
            venue = null;
        }

        // create a new RacingVenue object
        venue = new RacingVenue(carConfigurations);

        // initialize the frame and add the RacingVenue to it
        frame = new JFrame("Car Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(venue);

        // center the frame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;
        frame.setLocation(x - 350, y - 250);

        // create a JButton to start the race and add it to the frame
        JButton startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(e -> {
            // disable the button once the race has started
            startRaceButton.setEnabled(false);

            // get the start time of the race
            long startTime = System.currentTimeMillis();

            // create a Timer to move the cars and check if they have finished the race
            Timer[] timerHolder = new Timer[1];
            timerHolder[0] = new Timer(10, i -> {
                venue.moveRace(startTime);

                // if all the cars have finished, stop the Timer and display the results
                if (venue.allCarsFinished()) {
                    timerHolder[0].stop();
                    displayRaceResults();
                }
            });
            timerHolder[0].start();
        });
        frame.add(startRaceButton, BorderLayout.SOUTH);

        // pack the frame and make it visible
        frame.pack();
        frame.setVisible(true);
    }

    // displays the race results in a new frame and gives the option to play again or quit the game
    //Dorin
    public void displayRaceResults() {
        // create a new frame for the race results
        JFrame resultFrame = new JFrame("Race Results");
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // center the frame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;
        resultFrame.setLocation(x+150, y+150);

        // create a JLabel for the race results and add it to the panel
        JLabel finishLabel = new JLabel("Race results!", SwingConstants.CENTER);
        finishLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultPanel.add(finishLabel, BorderLayout.NORTH);

        // initialize a StringBuilder to store the race results
        StringBuilder results = new StringBuilder();
        int winningIndex = 0;
        long bestTime = venue.getCars()[0].getFinishTime();

        // loop through each car to get their finish time and add it to the results StringBuilder
        for (int i = 0; i < venue.getCars().length; i++) {
            long timeTaken = venue.getCars()[i].getFinishTime();
            double timeTakenSeconds = timeTaken / 1000.0;
            results.append("Car [").append(winningIndex + 1).append("] : ").append(timeTakenSeconds).append(" s\n");
            if (timeTaken < bestTime) {
                winningIndex = i;
                bestTime = timeTaken;
            }
        }

        // append the winner and their car information to the results StringBuilder
        results.append("The winner is Car [").append(winningIndex + 1).append("] !\n");
        results.append(venue.getCars()[winningIndex].toString());

        // create a JTextArea to display the results and add it to the panel
        JTextArea resultsTextArea = new JTextArea(results.toString());
        resultsTextArea.setEditable(false);
        resultPanel.add(resultsTextArea, BorderLayout.CENTER);

        // create a panel to ask the user if they want to play again or quit the game
        JPanel playAgainPanel = new JPanel(new BorderLayout());
        JLabel playAgainLabel = new JLabel("Do you want to play again?", SwingConstants.CENTER);

        playAgainPanel.add(playAgainLabel, BorderLayout.CENTER);

        JButton playAgainButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        // create a button panel and add the play again and quit buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);
        buttonPanel.add(quitButton);

        // add the button panel to the play again panel and add the play again panel to the result panel
        playAgainPanel.add(buttonPanel, BorderLayout.SOUTH);
        resultPanel.add(playAgainPanel, BorderLayout.SOUTH);

        // add the result panel to the frame and make it visible
        resultFrame.add(resultPanel);
        resultFrame.pack();
        resultFrame.setVisible(true);

        // add action listeners to the play again and quit buttons
        playAgainButton.addActionListener(e -> {
            resultFrame.dispose();
            frame.dispose();
            startGame();
        });

        quitButton.addActionListener(e -> System.exit(0));
    }

    // displays the initial input frame to choose the number of cars and creates the car configuration panel
    //Dov
    private void startGame() {
        JFrame inputFrame = new JFrame("Car Race");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Car Race!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        // create an image icon and add it to the welcome panel
        ImageIcon imageIcon = new ImageIcon("resources/logo1.gif");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        welcomePanel.add(imageLabel, BorderLayout.CENTER);

        panel.add(welcomePanel, BorderLayout.NORTH);

        // create a panel to choose the number of cars and add it to the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        inputPanel.add(new JLabel("Choose the number of cars to race:"));
        JComboBox<Integer> numberBox = new JComboBox<>(new Integer[]{1, 2, 3, 4});
        inputPanel.add(numberBox);

        // add the input panel to the main panel
        panel.add(inputPanel, BorderLayout.CENTER);

        // create buttons to create the cars and quit the game
        JButton createButton = new JButton("Create");
        JButton quitButton = new JButton("Quit");

        // create a panel to hold the buttons and add them to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonPanel.add(createButton);
        buttonPanel.add(quitButton);

        // add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // add the main panel to the input frame and make it visible
        inputFrame.add(panel);
        inputFrame.pack();
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setVisible(true);

        // add action listeners to the create and quit buttons
        createButton.addActionListener(e -> {

            // get the number of cars selected and close the input frame
            numCars = (int) numberBox.getSelectedItem();;
            inputFrame.dispose();

            // create an array of cars based on the user's configuration
            carConfigurations = getCarConfigPanel();

        });

        quitButton.addActionListener(e -> System.exit(0));
    }

    // displays the car configuration panel and allows the user to customize the car colors, engines, tires, and wheel sizes
    //Dov + Dorin
    private Car[] getCarConfigPanel() {
        int carInd = numCars ;
        Car[] cars = new Car[carInd];

        // create the configuration frame
        JFrame configFrame = new JFrame("Configure Cars");
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel to hold the car configuration options
        JPanel panel = new JPanel(new GridLayout(carInd + 1, 1));

        // create arrays to hold the options for each configuration parameter
        String[] colors = {"blue", "green", "yellow", "pink"};
        String[] tireTypes = {"summer", "winter", "sport"};
        String[] engines = {"4 cyl", "V6", "V8"};
        Integer[] wheelSizes = {15, 17, 20};

        // create a 2D array to hold the JComboBoxes for each car's configuration options
        JComboBox[][] configs = new JComboBox[carInd][4];

        // create a panel for each car and add it to the main panel
        for (int i = 0; i < carInd; i++) {
            JPanel carPanel = new JPanel();
            carPanel.add(new JLabel("Car " + (i + 1) + ": "));

            // add a color selector to the car panel
            JComboBox<String> colorBox = new JComboBox<>(colors);
            carPanel.add(new JLabel("Color: "));
            carPanel.add(colorBox);

            // add an engine selector to the car panel
            JComboBox<String> engineBox = new JComboBox<>(engines);
            carPanel.add(new JLabel("Engine: "));
            carPanel.add(engineBox);

            // add a tire type selector to the car panel
            JComboBox<String> tireTypeBox = new JComboBox<>(tireTypes);
            carPanel.add(new JLabel("Tire Type: "));
            carPanel.add(tireTypeBox);

            // add a wheel size selector to the car panel
            JComboBox<Integer> wheelSizeBox = new JComboBox<>(wheelSizes);
            carPanel.add(new JLabel("Wheel Size: "));
            carPanel.add(wheelSizeBox);

            // add a randomize button to the car panel
            JButton randomButton = new JButton("Randomize");
            randomButton.addActionListener(e -> {
                colorBox.setSelectedIndex(new Random().nextInt(colors.length));
                tireTypeBox.setSelectedIndex(new Random().nextInt(tireTypes.length));
                wheelSizeBox.setSelectedIndex(new Random().nextInt(wheelSizes.length));
                engineBox.setSelectedIndex(new Random().nextInt(engines.length));
            });
            carPanel.add(randomButton);

            // add the configuration JComboBoxes to the configs array
            configs[i][0] = colorBox;
            configs[i][1] = engineBox;
            configs[i][2] = tireTypeBox;
            configs[i][3] = wheelSizeBox;

            // add the car panel to the main panel
            panel.add(carPanel);
        }

        // create a button to confirm the car configurations and add an action listener to it
        JButton confirmChoice = new JButton("Confirm Choice");
        confirmChoice.addActionListener(e -> {
            // close the configuration frame
            configFrame.dispose();

            // create a car object for each configuration and add it to the cars array
            for (int i = 0; i < carInd; i++) {
                String color = (String) configs[i][0].getSelectedItem();
                String engine = (String) configs[i][1].getSelectedItem();
                String tireType = (String) configs[i][2].getSelectedItem();
                int wheelSize = (int) configs[i][3].getSelectedItem();
                cars[i] = new Car(color, engine, tireType, wheelSize, 0, 50 + i * 150);
            }

            // start the race with the chosen car configurations
            getRacingVenue(carConfigurations);
        });

        // add the confirm button to the main panel
        panel.add(confirmChoice);

        // add the main panel to the configuration frame and make it visible
        configFrame.add(panel);
        configFrame.pack();
        configFrame.setLocationRelativeTo(null);
        configFrame.setVisible(true);

        // return the array of configured cars
        return cars;
    }
}


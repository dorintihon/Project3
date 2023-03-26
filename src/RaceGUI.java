import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class RaceGUI {

    public static void main(String[] args) {
        new RaceGUI();
    }

    private JFrame frame;
    private RacingVenue venue;
    private int numCars;
    private  Car[] carConfigurations;

    public RaceGUI() {
        startGame();
    }

    public void getRacingVenue(Car[] carConfigurations) {
        if (this.frame != null) {
            this.frame.setVisible(false);
            this.frame.dispose();
            venue = null;
        }
        venue = new RacingVenue(carConfigurations, this);

        frame = new JFrame("Car Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(venue);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;
        frame.setLocation(x - 350, y - 250);

        JButton startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(e -> {
            startRaceButton.setEnabled(false);

            long startTime = System.currentTimeMillis();
            Timer[] timerHolder = new Timer[1];
            timerHolder[0] = new Timer(10, i -> {

                venue.moveRace(startTime);

                if (venue.allCarsFinished()) {
                    timerHolder[0].stop();
                    displayRaceResults();
                }
            });
            timerHolder[0].start();
        });

        frame.add(startRaceButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }


    public void displayRaceResults() {
        JFrame resultFrame = new JFrame("Race Results");
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - frame.getSize().width) / 2;
        int y = (dim.height - frame.getSize().height) / 2;
        resultFrame.setLocation(x+150, y+150); // set the location of the frame to the center of the screen


        JLabel finishLabel = new JLabel("Race results!", SwingConstants.CENTER);
        finishLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultPanel.add(finishLabel, BorderLayout.NORTH);

        StringBuilder results = new StringBuilder();
        int winningIndex = 0;
        long bestTime = venue.getCars()[0].getFinishTime();

        for (int i = 0; i < venue.getCars().length; i++) {
            long timeTaken = venue.getCars()[i].getFinishTime();
            double timeTakenSeconds = timeTaken / 1000.0;
            results.append("Car [").append(winningIndex + 1).append("] : ").append(timeTakenSeconds).append(" s\n");
            if (timeTaken < bestTime) {
                winningIndex = i;
                bestTime = timeTaken;
            }
        }

        results.append("The winner is Car [").append(winningIndex + 1).append("] !\n");
        results.append(venue.getCars()[winningIndex].toString());


        JTextArea resultsTextArea = new JTextArea(results.toString());
        resultsTextArea.setEditable(false);
        resultPanel.add(resultsTextArea, BorderLayout.CENTER);

        JPanel playAgainPanel = new JPanel(new BorderLayout());
        JLabel playAgainLabel = new JLabel("Do you want to play again?", SwingConstants.CENTER);

        playAgainPanel.add(playAgainLabel, BorderLayout.CENTER);

        JButton playAgainButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);
        buttonPanel.add(quitButton);

        playAgainPanel.add(buttonPanel, BorderLayout.SOUTH);
        resultPanel.add(playAgainPanel, BorderLayout.SOUTH);
        resultFrame.add(resultPanel);
        resultFrame.pack();
        resultFrame.setVisible(true);

        playAgainButton.addActionListener(e -> {
            resultFrame.dispose();
            frame.dispose();
            startGame();
        });

        quitButton.addActionListener(e -> System.exit(0));
    }



    private void startGame() {
        JFrame inputFrame = new JFrame("Car Race");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Car Race!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        ImageIcon imageIcon = new ImageIcon("resources/logo1.gif");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        welcomePanel.add(imageLabel, BorderLayout.CENTER);

        panel.add(welcomePanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        inputPanel.add(new JLabel("Choose the number of cars to race:"));
        JComboBox<Integer> numberBox = new JComboBox<>(new Integer[]{1, 2, 3, 4});
        inputPanel.add(numberBox);

        panel.add(inputPanel, BorderLayout.CENTER);

        JButton createButton = new JButton("Create");
        JButton quitButton = new JButton("Quit");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        buttonPanel.add(createButton);
        buttonPanel.add(quitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        inputFrame.add(panel);
        inputFrame.pack();
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setVisible(true);

        createButton.addActionListener(e -> {

                numCars = (int) numberBox.getSelectedItem();;
                inputFrame.dispose();
                carConfigurations = getCarConfigPanel();

        });

        quitButton.addActionListener(e -> System.exit(0));
    }



    private Car[] getCarConfigPanel() {
        int carInd = numCars ;
        Car[] cars = new Car[carInd];

        JFrame configFrame = new JFrame("Configure Cars");
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(carInd + 1, 1));

        String[] colors = {"blue", "green", "yellow", "pink"};
        String[] tireTypes = {"summer", "winter", "sport"};
        String[] engines = {"4 cyl", "V6", "V8"};
        Integer[] wheelSizes = {15, 17, 20};

        JComboBox[][] configs = new JComboBox[carInd][4];

        for (int i = 0; i < carInd; i++) {
            JPanel carPanel = new JPanel();
            carPanel.add(new JLabel("Car " + (i + 1) + ": "));

            JComboBox<String> colorBox = new JComboBox<>(colors);
            carPanel.add(new JLabel("Color: "));
            carPanel.add(colorBox);

            JComboBox<String> engineBox = new JComboBox<>(engines);
            carPanel.add(new JLabel("Engine: "));
            carPanel.add(engineBox);

            JComboBox<String> tireTypeBox = new JComboBox<>(tireTypes);
            carPanel.add(new JLabel("Tire Type: "));
            carPanel.add(tireTypeBox);

            JComboBox<Integer> wheelSizeBox = new JComboBox<>(wheelSizes);
            carPanel.add(new JLabel("Wheel Size: "));
            carPanel.add(wheelSizeBox);

            JButton randomButton = new JButton("Randomize");
            randomButton.addActionListener(e -> {
                colorBox.setSelectedIndex(new Random().nextInt(colors.length));
                tireTypeBox.setSelectedIndex(new Random().nextInt(tireTypes.length));
                wheelSizeBox.setSelectedIndex(new Random().nextInt(wheelSizes.length));
                engineBox.setSelectedIndex(new Random().nextInt(engines.length));
            });
            carPanel.add(randomButton);

            configs[i][0] = colorBox;
            configs[i][1] = engineBox;
            configs[i][2] = tireTypeBox;
            configs[i][3] = wheelSizeBox;

            panel.add(carPanel);
        }

        JButton confirmChoice = new JButton("Confirm Choice");
        confirmChoice.addActionListener(e -> {
            configFrame.dispose();
            for (int i = 0; i < carInd; i++) {
                String color = (String) configs[i][0].getSelectedItem();
                String engine = (String) configs[i][1].getSelectedItem();
                String tireType = (String) configs[i][2].getSelectedItem();
                int wheelSize = (int) configs[i][3].getSelectedItem();
                cars[i] = new Car(color, engine, tireType, wheelSize, 0, 50 + i * 150);
            }
            getRacingVenue(carConfigurations);
        });
        panel.add(confirmChoice);

        configFrame.add(panel);
        configFrame.pack();
        configFrame.setLocationRelativeTo(null);
        configFrame.setVisible(true);

        return cars;
    }
}


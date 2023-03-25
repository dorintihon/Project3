import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class RaceGUI {

    public static void main(String[] args) {
       new RaceGUI();
    }
    
    private JFrame frame;
    private RacingVenue venue;
    private long startTime;

    private int numCars;

    public RaceGUI() {
        
      frame = frameBuilder();

    }
    
    public void raceStart() {
    	startTime = System.currentTimeMillis();
    	Timer[] timerHolder = new Timer[1];
		timerHolder[0] = new Timer(10, e -> {
			
			venue.moveRace(startTime);

			if (venue.allCarsFinished()) {
				timerHolder[0].stop();
                displayRaceResults();
			}
		});
		timerHolder[0].start();
    }

    public JFrame frameBuilder() {

    	if(this.frame != null) {
    		this.frame.setVisible(false);
    		this.frame.dispose();
    		venue = null;
    	}
         getNumberOfCars();
    	 Car[] carConfigurations = getCarConfigurations();
         venue = new RacingVenue(carConfigurations, this);

    	 frame = new JFrame("Car Race");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         frame.add(venue);

        // Create the StartRace button
        JButton startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(e -> {
            startRaceButton.setEnabled(false); // Disable the button to avoid clicking it multiple times
            raceStart();
        });

        frame.add(startRaceButton, BorderLayout.SOUTH);
         frame.pack();
         frame.setVisible(true);


         return frame;
    }

    public void displayRaceResults() {
        JFrame resultFrame = new JFrame("Race Results");
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        StringBuilder results = new StringBuilder("Race results!\n");
        int winningIndex = 0;
        long bestTime = venue.getCars()[0].getFinishTime();

        for (int i = 0; i < venue.getCars().length; i++) {
            long timeTaken = venue.getCars()[i].getFinishTime();
            double timeTakenSeconds = timeTaken / 1000.0;
            results.append("Car ").append(i + 1).append(": ").append(timeTakenSeconds).append(" s\n");
            if (timeTaken < bestTime) {
                winningIndex = i;
                bestTime = timeTaken;
            }
        }

        results.append("The winner is Car ").append(winningIndex + 1).append("!\n");
        results.append(venue.getCars()[winningIndex].toString());
        results.append("Do you want to play again?");

        JTextArea resultsTextArea = new JTextArea(results.toString());
        resultsTextArea.setEditable(false);
        resultPanel.add(resultsTextArea);

        JButton playAgainButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playAgainButton);
        buttonPanel.add(quitButton);

        resultPanel.add(buttonPanel);
        resultFrame.add(resultPanel);
        resultFrame.pack();
        resultFrame.setVisible(true);

        playAgainButton.addActionListener(e -> {
            resultFrame.dispose();
            frameBuilder();
        });

        quitButton.addActionListener(e -> System.exit(0));
    }



    private void getNumberOfCars() {
        do {
            try {
                JPanel panel = new JPanel(new BorderLayout());
                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

                JTextField textField = new JTextField(5);
                inputPanel.add(new JLabel("Enter the number of cars to race (2 or 3):"));
                inputPanel.add(textField);

                panel.add(inputPanel, BorderLayout.CENTER);

                JButton quitButton = new JButton("Quit");
                panel.add(quitButton, BorderLayout.SOUTH);

                int result = JOptionPane.showOptionDialog(
                        null,
                        panel,
                        "Number of Cars",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        new Object[]{"Create"},
                        null
                );

                if (result == JOptionPane.CLOSED_OPTION || result == JOptionPane.CANCEL_OPTION) {
                    System.exit(0);
                }

                numCars = Integer.parseInt(textField.getText());

            } catch (NumberFormatException ex) {
                System.out.println("For some reason, what was entered could not be parsed");
            }
        } while (numCars < 2 || numCars > 3);

    }


    private Car[] getCarConfigurations() {
        int carInd = numCars ;
        Car[] cars = new Car[carInd];

        JPanel panel = new JPanel(new GridLayout(carInd + 1, 1));

        String[] colors = {"blue", "green", "yellow"};
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
        panel.add(confirmChoice);

        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog("Configure Cars");
        confirmChoice.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);

        for (int i = 0; i < carInd; i++) {
            String color = (String) configs[i][0].getSelectedItem();
            String engine = (String) configs[i][1].getSelectedItem();
            String tireType = (String) configs[i][2].getSelectedItem();
            int wheelSize = (int) configs[i][3].getSelectedItem();
            cars[i] = new Car(color, engine, tireType, wheelSize, 0, 50 + i * 150);
        }

        return cars;
    }
}



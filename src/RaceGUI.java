import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class RaceGUI {

    public static void main(String[] args) {
       new RaceGUI();
    }
    
    private JFrame frame;
    private RacingVenue venue;
    private long startTime;

    public RaceGUI() {
        
      frame = frameBuilder();
      raceStart();

    }
    
    public void raceStart() {
    	startTime = System.currentTimeMillis();
    	Timer[] timerHolder = new Timer[1];
		timerHolder[0] = new Timer(10, e -> {
			
			venue.moveRace(startTime);

			if (venue.allCarsFinished()) {
				timerHolder[0].stop();
				if(displayRaceResults() == JOptionPane.YES_OPTION) {
					this.frameBuilder();
				}
				
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
    	 Car[] carConfigurations = getCarConfigurations();
         venue = new RacingVenue(carConfigurations, this);
         
    	 frame = new JFrame("Car Race");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         frame.add(venue);
         frame.pack();
         frame.setVisible(true);
         
         raceStart();
         
         return frame;
    }
   
    public int displayRaceResults() {
		StringBuilder results = new StringBuilder("Race results! Click Yes to play again\n");
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
		results.append("The winner is Car ").append(winningIndex + 1).append("!");

		int choice = JOptionPane.showConfirmDialog(frame, results.toString(), "Race Results", JOptionPane.YES_OPTION);
		
		
		return choice;
	}

    private Car[] getCarConfigurations() {
        int numCars = 0;
        do {
          
        	try {
        		String input = JOptionPane.showInputDialog(null, "Enter the number of cars to race (2 or 3):");
        		numCars = Integer.parseInt(input);
        	}
          
        	catch (NumberFormatException ex){
        		System.out.println("For some reason, what was entered could not be parsed");
        	}
           
        } while (numCars < 2 || numCars > 3);

        Car[] cars = new Car[numCars];

        JPanel panel = new JPanel(new GridLayout(numCars + 1, 1));

        String[] colors = {"blue", "green", "yellow"};
        String[] tireTypes = {"summer", "winter", "sport"};
        String[] engines = {"4 cyl", "V6", "V8"};
        Integer[] wheelSizes = {15, 17, 20};

        JComboBox[][] configs = new JComboBox[numCars][4];

        for (int i = 0; i < numCars; i++) {
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

        JButton startButton = new JButton("Start Race");
        panel.add(startButton);

        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = optionPane.createDialog("Configure Cars");
        startButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);

        for (int i = 0; i < numCars; i++) {
            String color = (String) configs[i][0].getSelectedItem();
            String engine = (String) configs[i][1].getSelectedItem();
            String tireType = (String) configs[i][2].getSelectedItem();
            int wheelSize = (int) configs[i][3].getSelectedItem();
            cars[i] = new Car(color, engine, tireType, wheelSize, 0, 50 + i * 150);
        }

        return cars;
    }



	
}



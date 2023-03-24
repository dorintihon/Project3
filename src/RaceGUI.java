import javax.swing.*;


public class RaceGUI {

    public static void main(String[] args) {
       new RaceGUI();
    }

    public RaceGUI() {
        JFrame frame = new JFrame("Car Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RacingVenue movingRectanglesPanel = new RacingVenue(getNumberOfCars());

        frame.add(movingRectanglesPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private int getNumberOfCars() {
        int numCars;
        do {
            String input = JOptionPane.showInputDialog(null, "Enter the number of cars to race (2 or 3):");
            numCars = Integer.parseInt(input);
        } while (numCars < 2 || numCars > 3);
        return numCars;
    }

}



import javax.swing.*;
import java.util.Random;


public class RaceGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Race");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        RacingVenue movingRectanglesPanel = new RacingVenue(createCars(getNumberOfCars()));

        frame.add(movingRectanglesPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static int getNumberOfCars() {
        int numCars;
        do {
            String input = JOptionPane.showInputDialog(null, "Enter the number of cars to race (2 or 3):");
            numCars = Integer.parseInt(input);
        } while (numCars < 2 || numCars > 3);
        return numCars;
    }

    private static Car[] createCars(int numCars) {
        Car[] cars = new Car[numCars];
        Random random = new Random();
        String[] colors = {"blue", "green", "yellow"};
        String[] tireTypes = {"summer", "winter"};
        Integer[] wheelSizes = {15, 17};

        for (int i = 0; i < numCars; i++) {
            String color = colors[random.nextInt(colors.length)];
            String tireType = tireTypes[random.nextInt(tireTypes.length)];
            int wheelSize = wheelSizes[random.nextInt(wheelSizes.length)];
            cars[i] = new Car(color, tireType, wheelSize, 0, 50 + i * 150);
        }
        return cars;
    }
}



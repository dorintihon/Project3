//Dorin and Dov
import javax.swing.*;
import java.awt.*;

/*
 * RacingVenue handles Cars as a group as well as attaching each car to a checkpoint so that it knows when to stop.
 * This class provides methods that RaceGUI uses to run the race and display the results, as well as being responsible
 * for drawing the cars and checkpoints.
 */

class RacingVenue extends JPanel {


	private Car[] cars;
	private Checkpoint[] checkpoints;

	// Constructor for RacingVenue class
	//Dorin, edited by Dov
	public RacingVenue(Car[] carConfigurations) {

		// Takes an array of Car objects as input
		this.cars = carConfigurations;

		// Sets the checkpoints array based on the number of cars
		this.checkpoints = new Checkpoint[cars.length];
		for (int i = 0; i < cars.length; i++) {
			checkpoints[i] = new Checkpoint(0, cars[i].getY(), 600, cars[i].getY());
		}

		// Calculates the height of the JPanel based on the number of cars and sets it as the preferred size
		int height = cars.length * 150; // calculate the required height based on the number of cars
		setPreferredSize(new Dimension(700, height));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}

	// Method to move the cars based on the current time
	//Dov, Dorin
	public void moveRace(long startTime) {
		for (int i = 0; i < cars.length; i++) {
			cars[i].move(checkpoints[i], startTime);
		}
		repaint();
	}

	// Getter method for cars array
	//Dov
	public Car[] getCars() {
		return cars;
	}

	// Getter method for checkpoints array
	//Dov
	public Checkpoint[] getCheckpoints() {
		return checkpoints;
	}

	// Method to check if all cars have finished the race
	//Dov
	public boolean allCarsFinished() {
		for (Car car : cars) {
			if (!car.isFinished()) {
				return false;
			}
		}
		return true;
	}

	// Method to draw the cars and checkpoints
	//Dorin, Dov
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Car car : cars) {
			car.draw(g);
		}
		for (Checkpoint checkpoint : checkpoints) {
			checkpoint.draw(g);
		}
	}

	// Method to return a string representation of the RacingVenue object
	//Dov
	@Override
	public String toString() {
		String info = "";
		info = info + "This track has " + cars.length + " cars";

		return info;
	}

	// Method to check if two RacingVenue objects are equal
	//Dov
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;

		if (obj instanceof RacingVenue) {
			RacingVenue ven = (RacingVenue) obj;

			return ven.getCars() == this.getCars() && ven.getCheckpoints() == this.getCheckpoints();
		}
		return false;
	}
}
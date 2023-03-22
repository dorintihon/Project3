import java.awt.*;

import javax.swing.JComponent;

public class RacingVenue extends JComponent{

	private int x;
	private int y;
	private int radius;

	private Car[] cars;
	private Checkpoint[] checkpoints;

	public RacingVenue(int centerx, int centery, int radius) {

		this.x = centerx;
		this.y = centery;
		this.radius = radius;

		this.makeTrack();

	}

	private void makeTrack() {
		cars = generateCars(1);
		checkpoints = generateCheckpoints(4);
		this.repaint();
	}
	
	public void moveRace() {
		for(int i = 0; i < cars.length; i++) {
			cars[i].move(0.05);
			//System.out.println(cars[i].getPositionX() + " " + cars[i].getPositionY());
			for(int j = 0; j < checkpoints.length; j++) {
				checkpoints[j].hasCar();
			}
		}
	}


	public Car[] generateCars(int numCars) {
		//placeholder, will build an array and return it, then the cars attribute will be set equal to the returned array
		//for(int i = 0; i < numCars; i++) {	}
		cars = new Car[1];
		cars[0] = new Car(this, Color.RED, 0.01);
		return cars;
	}

	public Checkpoint[] generateCheckpoints(int numPoints) {

		int checkpointDistance = (int) (2 * Math.PI * radius / numPoints);

		checkpoints = new Checkpoint[numPoints];
		for (int i = 0; i < numPoints; i++) {
			int checkpointX = x + (int) (radius * Math.cos(i * checkpointDistance / radius));
			int checkpointY = y + (int) (radius * Math.sin(i * checkpointDistance / radius));
			checkpoints[i] = new Checkpoint(checkpointX, checkpointY, 15, 15, cars[0]);
			System.out.println("Checkpoint " + i + " coords: " + checkpointX + " " + checkpointY);
		}
		return checkpoints;
	}

	public boolean isGameOver() {
		for(int i = 0; i < checkpoints.length; i++) {
			if(!checkpoints[i].hasCar()) {
				return false;
			}
		}
		//if got through loop without triggering a return, all cars have arrived
		return true;
	}

	public int getXCoord() {
		return x;
	}

	public int getYCoord() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
		for (Checkpoint checkpoint : checkpoints) {
			checkpoint.draw(g);
			cars[0].draw(g);
		}
	}
}

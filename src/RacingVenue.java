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

		generateCheckpoints(3);
	}


	public Car[] generateCars(int numCars) {
		//placeholder, will build an array and return it, then the cars attribute will be set equal to the returned array
		return cars;
	}

	public Checkpoint[] generateCheckpoints(int numPoints) {

		int checkpointDistance = (int) (2 * Math.PI * radius / numPoints);

		checkpoints = new Checkpoint[numPoints];
		for (int i = 0; i < numPoints; i++) {
			int checkpointX = x + (int) (radius * Math.cos(i * checkpointDistance / radius));
			int checkpointY = y + (int) (radius * Math.sin(i * checkpointDistance / radius));
			checkpoints[i] = new Checkpoint(checkpointX, checkpointY, 15, 15);
		}
		return checkpoints;
	}

	public boolean isGameOver() {
		for(int i = 0; i < checkpoints.length; i++) {
			//if NOT checkpoints[i].hasCar() return false
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
		}
	}
}

import java.awt.Graphics;

import javax.swing.JComponent;

public class RacingVenue extends JComponent{
	
	private Circle track;
	private Car[] cars;
	private Checkpoint[] checkpoints;
	
	private int x;
	private int y;
    private int radius;
	
	public RacingVenue(int centerx, int centery, int radius) {
		
		this.x = centerx;
        this.y = centery;
        this.radius = radius;
        
        this.makeTrack();
		
	}
	
	//will take number of cars/checkpoints as parameter and add those to arrays
	public Circle makeTrack() {
		track = new Circle(x, y, radius);
		//cars = generateCars(numCars);
		//checkpoints = generateCheckpoints(numCars * 2, raceDistance);
		return track;
	}
	
	public Car[] generateCars(int numCars) {
		//placeholder, will build an array and return it, then the cars attribute will be set equal to the returned array
		return cars;
	}
	
	public Checkpoint[] generateCheckpoints(int numPoints, int raceLength) {
		//generates and places checkpoints on track at equally distant points
		//the distance between all checkpoints is defined by raceLength
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
    }
}

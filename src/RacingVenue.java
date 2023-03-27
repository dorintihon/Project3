import javax.swing.*;
import java.awt.*;

class RacingVenue extends JPanel {


	private Car[] cars;
	private Checkpoint[] checkpoints;


	public RacingVenue(Car[] carConfigurations, RaceGUI gui) {
		this.cars = carConfigurations;
		
		this.checkpoints = new Checkpoint[cars.length];
		
		for (int i = 0; i < cars.length; i++) {
			checkpoints[i] = new Checkpoint(0, cars[i].getY(), 600, cars[i].getY());
		}

		int height = cars.length * 150; // calculate the required height based on the number of cars
		setPreferredSize(new Dimension(700, height));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}
	
	public void moveRace(long startTime) {
		
		for (int i = 0; i < cars.length; i++) {
			
			cars[i].move(checkpoints[i], startTime);
		}
	    
		repaint();
	}
	
	public Car[] getCars() {
		return cars;
	}
	
	public Checkpoint[] getCheckpoints() {
		return checkpoints;
	}

	public boolean allCarsFinished() {
		for (Car car : cars) {
			if (!car.isFinished()) {
				return false;
			}
		}
		return true;
	}
	


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
	
	@Override
	public String toString() {
		String info = "";
		info = info + "This track has " + cars.length + " cars";
		
		return info;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		
		if(obj instanceof RacingVenue) {
			RacingVenue ven = (RacingVenue) obj;
			
			return ven.getCars() == this.getCars() && ven.getCheckpoints() == this.getCheckpoints();
		}
		return false;
	}
}
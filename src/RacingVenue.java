import javax.swing.*;
import java.awt.*;

class RacingVenue extends JPanel {


	private Car[] cars;
	private Checkpoint[] checkpoints;

	public RacingVenue(Car[] cars) {
		this.cars = cars;
		this.checkpoints = new Checkpoint[cars.length];
		for (int i = 0; i < cars.length; i++) {
			checkpoints[i] = new Checkpoint(0, cars[i].getY(), 600, cars[i].getY());
		}

		Timer timer = new Timer(10, e -> {
			for (int i = 0; i < cars.length; i++) {
				cars[i].move(checkpoints[i]);
			}
			repaint();
		});
		timer.start();

		setPreferredSize(new Dimension(700, 400));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
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
}
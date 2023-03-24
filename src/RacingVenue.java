import javax.swing.*;
import java.awt.*;
import java.util.Random;

class RacingVenue extends JPanel {


	private Car[] cars;
	private Checkpoint[] checkpoints;

	public RacingVenue(int numCars) {
		this.cars = createCars(numCars);
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

	private Car[] createCars(int numCars) {
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
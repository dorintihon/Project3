import javax.swing.*;
import java.awt.*;

class RacingVenue extends JPanel {


	private Car[] cars;
	private Checkpoint[] checkpoints;

	long startTime = System.currentTimeMillis();


	public RacingVenue(Car[] carConfigurations, RaceGUI gui) {
		this.cars = carConfigurations;
		this.checkpoints = new Checkpoint[cars.length];
		for (int i = 0; i < cars.length; i++) {
			checkpoints[i] = new Checkpoint(0, cars[i].getY(), 600, cars[i].getY());
		}


		Timer[] timerHolder = new Timer[1];
		timerHolder[0] = new Timer(10, e -> {
			for (int i = 0; i < cars.length; i++) {
				cars[i].move(checkpoints[i], startTime);
			}
			repaint();

			if (allCarsFinished()) {
				timerHolder[0].stop();
				if(displayRaceResults() == JOptionPane.YES_OPTION) {
					gui.frameBuilder();
				}
			}
		});
		timerHolder[0].start();


		setPreferredSize(new Dimension(700, 400));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	}


	private boolean allCarsFinished() {
		for (Car car : cars) {
			if (!car.isFinished()) {
				return false;
			}
		}
		return true;
	}

	private int displayRaceResults() {
		StringBuilder results = new StringBuilder("Race results! Click Yes to play again\n");
		int winningIndex = 0;
		long bestTime = cars[0].getFinishTime();

		for (int i = 0; i < cars.length; i++) {
			Car car = cars[i];
			long timeTaken = car.getFinishTime();
			double timeTakenSeconds = timeTaken / 1000.0;
			results.append("Car ").append(i + 1).append(": ").append(timeTakenSeconds).append(" s\n");
			if (timeTaken < bestTime) {
				winningIndex = i;
				bestTime = timeTaken;
			}
		}
		results.append("The winner is Car ").append(winningIndex + 1).append("!");

		return JOptionPane.showConfirmDialog(null, results.toString(), "Race Results", JOptionPane.YES_OPTION);
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
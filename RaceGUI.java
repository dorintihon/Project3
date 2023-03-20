import javax.swing.*;
import java.awt.*;

public class RaceGUI extends JPanel {

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;

    private int radius = 150;
    private int centerX = WINDOW_WIDTH/2;
    private int centerY = WINDOW_HEIGHT/2;

    private RacingVenue venue;
    private Car car;

    public RaceGUI() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        venue = new RacingVenue(centerX, centerY, radius);
        car = new Car(venue, Color.RED);

        Timer timer = new Timer(50, e -> {
            car.move(0.05);
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        venue.paintComponent(g);
        car.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new RaceGUI());
        frame.pack();
        frame.setVisible(true);
    }
}
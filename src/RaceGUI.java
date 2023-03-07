import javax.swing.*;
import java.awt.*;

public class RaceGUI extends JPanel {

    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;

    private int radius = 150;
    private int centerX = WINDOW_WIDTH/2;
    private int centerY = WINDOW_HEIGHT/2;

    private Circle circle;
    private Car car;

    public RaceGUI() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        circle = new Circle(centerX, centerY, radius);
        car = new Car(circle, Color.RED);

        Timer timer = new Timer(50, e -> {
            car.move(0.05);
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        circle.draw(g);
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
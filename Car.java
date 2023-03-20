import javax.swing.*;
import java.awt.*;

class Car {

    private RacingVenue circle;
    private Color color;
    private double speed = 0.0;
    private final int WIDTH = 40;
    private final int HEIGHT = 20;

    Image carImg = new ImageIcon("car1.png").getImage();

    public Car(RacingVenue circle, Color color) {
        this.circle = circle;
        this.color = color;
    }

    public void move(double speed) {
        this.speed += speed;
    }

    public void draw(Graphics g) {
        int x = (int) (circle.getXCoord() + circle.getRadius() * Math.cos(speed)) - WIDTH/2;
        int y = (int) (circle.getYCoord() + circle.getRadius() * Math.sin(speed)) - HEIGHT/2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(speed, x + WIDTH/2, y + HEIGHT/2);
        g2d.drawImage(carImg, x-15, y, WIDTH+25, HEIGHT+50, null);
        g2d.rotate(-speed, x + WIDTH/2, y + HEIGHT/2);
    }
}
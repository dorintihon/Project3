import javax.swing.*;
import java.awt.*;

class Checkpoint {
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Checkpoint(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public int getEndX() {
        return endX;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(new ImageIcon("resources/start_flag.png").getImage(), startX, startY - 15, 70, 60, null);
        g2d.drawImage(new ImageIcon("resources/finish_flag.png").getImage(), endX, endY - 15, 20, 60, null);
    }
}
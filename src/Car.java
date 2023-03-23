import javax.swing.*;
import java.awt.*;

class Car {
    private int x;
    private int y;
    private String color;
    private String tireType;
    private int wheelSize;
    private int speed = 35;

    public Car(String color, String tireType, int wheelSize, int x, int y) {
        this.color = color;
        this.tireType = tireType;
        this.wheelSize = wheelSize;
        this.x = x;
        this.y = y;

        int speedReductionByWheel = 0;
        if (wheelSize == 17) {
            speedReductionByWheel = 15;
        } else if (wheelSize == 15) {
            speedReductionByWheel = 5;
        }

        int speedReductionByTire = 0;

        if (tireType.equals("winter")) {
            speedReductionByTire = 10;
        } else if (tireType.equals("summer")) {
            speedReductionByTire = 5;
        }


        this.speed -= speedReductionByWheel + speedReductionByTire;
    }

    public void move(Checkpoint checkpoint) {
        if (x < checkpoint.getEndX() + 10) {
            x += speed;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        switch (color) {
            case "blue" -> g2d.drawImage(new ImageIcon("resources/blue_car.png").getImage(), x, y, 50, 50, null);
            case "green" -> g2d.drawImage(new ImageIcon("resources/green_car.png").getImage(), x, y, 50, 50, null);
            case "yellow" -> g2d.drawImage(new ImageIcon("resources/yellow_car.png").getImage(), x, y, 50, 50, null);
            default -> g.setColor(Color.YELLOW);
        }

    }
}
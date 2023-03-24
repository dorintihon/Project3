import javax.swing.*;
import java.awt.*;

class Car {
    private int x;
    private int y;
    private String color;
    private String tireType;

    private String engine;
    private int wheelSize;
    private int speed = 15;

    private boolean finished;
    private long finishTime;

    public Car(String color, String engine, String tireType, int wheelSize, int x, int y) {
        this.color = color;
        this.engine = engine;
        this.tireType = tireType;
        this.wheelSize = wheelSize;
        this.x = x;
        this.y = y;

        this.finished = false;
        this.finishTime = 0;

        this.speed -= speedReductionByEngine() + speedReductionByTyre() + speedReductionByWheel();
    }


    public int speedReductionByEngine(){
        	switch (engine) {
            case "4 cyl": return 4;
            case "V6": return 2;
            case "V8": return 1;
            default: return 0;
        }
        
       
    }

    public int speedReductionByWheel(){
        switch (wheelSize) {
            case 20: return 4;
            case 17: return 2;
            case 15: return 1;
            default: return 0;
        }
    }

    public int speedReductionByTyre(){
         switch (tireType) {
            case "winter": return 4;
            case "summer": return 2;
            case "sport": return 1;
            default: return 0;
        }
    }

    public void move(Checkpoint checkpoint, long startTime) {
        if (!finished) {
            if (x < checkpoint.getEndX() + 10) {
                x += speed;
            } else {
                finish(startTime);
            }
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public void finish(long startTime) {
        this.finished = true;
        this.finishTime = System.currentTimeMillis() - startTime;
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
            case "blue": g2d.drawImage(new ImageIcon("resources/blue_car.png").getImage(), x, y, 50, 50, null);
            case "green": g2d.drawImage(new ImageIcon("resources/green_car.png").getImage(), x, y, 50, 50, null);
            case "yellow": g2d.drawImage(new ImageIcon("resources/yellow_car.png").getImage(), x, y, 50, 50, null);
            default: g.setColor(Color.YELLOW);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car car = (Car) obj;
        return wheelSize == car.wheelSize &&
                engine.equals(car.engine) &&
                color.equals(car.color) &&
                tireType.equals(car.tireType);
    }
}
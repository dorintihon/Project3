// AJ, Dorin, Dov

// 3/9 - updated class with proper attributes, accessors, and mutators [AJ]

import javax.swing.*;
import java.awt.*;
/*
 * The Car class handles all information related to displaying Cars and their speed in the race. It provides
 * methods for calculating a Car's speed based on its engine and tires, as well as providing a draw method for
 * RacingVenue to call to display the Cars. It also keeps track of the Car's x/y coordinates so that RacingVenue knows
 * when the race has ended and RaceGUI knows when to display the race results.
 */
class Car {
    private int x;
    private int y;

    private int WIDTH = 125;
    private int HEIGHT = 125;
    private String color;
    private String tireType;

    private String engine;
    private int wheelSize;
    private int speed = 10;

    private boolean finished;
    private long finishTime;

    // Constructor for Car class
    //AJ
    public Car(String color, String engine, String tireType, int wheelSize, int x, int y) {
        // Assigning values to variables using the constructor parameters
        this.color = color;
        this.engine = engine;
        this.tireType = tireType;
        this.wheelSize = wheelSize;
        this.x = x;
        this.y = y;

        // Initializing the finished and finishTime variables
        this.finished = false;
        this.finishTime = 0;

        // Calculating speed reduction due to engine, tire type, and wheel size
        this.speed -= speedReductionByEngine() + speedReductionByTyre() + speedReductionByWheel();
    }

    // Method to calculate speed reduction due to engine type
    //AJ + Dorin
    public int speedReductionByEngine(){
        switch (engine) {
            case "4 cyl": return 3;
            case "V6": return 2;
            case "V8": return 1;
            default: return 0;
        }
    }

    // Method to calculate speed reduction due to wheel size
    //AJ + Dorin
    public int speedReductionByWheel(){
        switch (wheelSize) {
            case 20: return 3;
            case 17: return 2;
            case 15: return 1;
            default: return 0;
        }
    }

    // Method to calculate speed reduction due to tire type
    //AJ + Dorin
    public int speedReductionByTyre(){
        switch (tireType) {
            case "winter": return 3;
            case "summer": return 2;
            case "sport": return 1;
            default: return 0;
        }
    }

    // Method to move the car to the next checkpoint
    //Dorin
    public void move(Checkpoint checkpoint, long startTime) {
        if (!finished) {
            if (x < checkpoint.getEndX() + 10) {
                x += speed;
            } else {
                finish(startTime);
            }
        }
    }

    // Method to check if the car has finished the race
    //AJ
    public boolean isFinished() {
        return finished;
    }

    // Method to set the finished status of the car
    //AJ
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    // Method to get the finish time of the car
    //AJ
    public long getFinishTime() {
        return finishTime;
    }

    // Method to set the finish time of the car
    //AJ
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    // Method to set the finished status of the car and calculate the finish time
    //Dorin
    public void finish(long startTime) {
        this.finished = true;
        this.finishTime = System.currentTimeMillis() - startTime;
    }

    // Getter method for the x-coordinate of the car
    //AJ
    public int getX() {
        return x;
    }

    // Setter method for the x-coordinate of the car
    //AJ
    public void setX(int x) {
        this.x = x;
    }

    // Getter method for the y-coordinate of the car
    //AJ
    public int getY() {
        return y;
    }

    // Setter method for the y-coordinate of the car
    //AJ
    public void setY(int y) {
        this.y = y;
    }

    // Getter method for the engine of the car
    //AJ
    public String getEngine() {
        return engine;
    }

    // Setter method for the engine of the car
    //AJ
    public void setEngine(String engine) {
        this.engine = engine;
    }

    // Getter method for the color of the car
    //AJ
    public String getColor() {
        return color;
    }

    // Setter method for the color of the car
    //AJ
    public void setColor(String color) {
        this.color = color;
    }

    // Getter method for the tire type of the car
    //AJ
    public String getTireType() {
        return tireType;
    }

    // Setter method for the tire type of the car
    //AJ
    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    // Getter method for the wheel size of the car
    //AJ
    public int getWheelSize() {
        return wheelSize;
    }

    // Setter method for the wheel size of the car
    //AJ
    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    // Getter method for the speed of the car
    //AJ
    public int getSpeed() {
        return speed;
    }

    // Setter method for the speed of the car
    //AJ
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Method to draw the car on the graphics context
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        switch (getColor()) {
            case "blue":
                g2d.drawImage(new ImageIcon("resources/blue_car.gif").getImage(), getX(), y-50, WIDTH, HEIGHT, null);
                break;
            case "green":
                g2d.drawImage(new ImageIcon("resources/green_car.gif").getImage(), getX(), y-50, WIDTH, HEIGHT, null);
                break;
            case "yellow":
                g2d.drawImage(new ImageIcon("resources/yellow_car.gif").getImage(), getX(), y-50, WIDTH, HEIGHT, null);
                break;
            case "pink":
                g2d.drawImage(new ImageIcon("resources/pink_car.gif").getImage(), getX(), y-50, WIDTH, HEIGHT, null);
                break;
            default:
                g2d.drawImage(new ImageIcon("resources/gray_car.gif").getImage(), getX(), y-50, WIDTH, HEIGHT, null);
        }
    }

    // Overriding the toString() method to return information about the car
    @Override
    public String toString() {
        String info = "";
        info = info + "This car is " + getColor() + " and has a " + getEngine() + " engine with " + getTireType()+ " tires and " + getWheelSize() + " wheel size\n";
        return info;
    }

    // Overriding the equals() method to check if two cars are equal based on their color, engine, tire type, and wheel size
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
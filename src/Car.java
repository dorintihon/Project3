
// AJ, Dorin, Dov (just a little)

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
    
    //AJ
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

    
    //AJ + Dorin
    public int speedReductionByEngine(){
        	switch (engine) {
            case "4 cyl": return 3;
            case "V6": return 2;
            case "V8": return 1;
            default: return 0;
        }

    }
    
    //AJ + Dorin
    public int speedReductionByWheel(){
        switch (wheelSize) {
            case 20: return 3;
            case 17: return 2;
            case 15: return 1;
            default: return 0;
        }
    }
    
    //AJ + Dorin
    public int speedReductionByTyre(){
         switch (tireType) {
            case "winter": return 3;
            case "summer": return 2;
            case "sport": return 1;
            default: return 0;
        }
    }
    
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
    
    //AJ
    public boolean isFinished() {
        return finished;
    }
    
  //AJ
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
  //AJ
    public long getFinishTime() {
        return finishTime;
    }
  //AJ
    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
      
    // constructor with no args
    public Car(){
        this.startPoint = null;
        this.endPoint = null;
        this.engine = 0;
        this.tire = 0;
        this.wheel = 0;
        this.color = null;
        this.speed = 0;
        this.power = 0;
        this.positionX = 0;
        this.positionY = 0;
    }

    // constructor with args
    public Car(Checkpoint sta, Checkpoint end, int e, int t, int w, Color color, double sp, int p, int x, int y) {
        this.startPoint = sta;
        this.endPoint = end;
        this.engine = e;
        this.tire = t;
        this.wheel = w;
        this.color = color;
        this.speed = sp;
        this.power = p;
        this.positionX = x;
        this.positionY = y;
    }

    //  Accessors
    public Checkpoint getStartPoint(){
        return this.startPoint;
    }
    public Checkpoint getEndPoint(){
        return this.endPoint;
    }
    public int getEngine(){
        return this.engine;
    }
    public int getTire(){
        return this.tire;
    }
    public int getWheel(){
        return this.wheel;
    }
    public Color getColor(){
        return this.color;
    }
    public double getSpeed(){
        return this.speed;
    }
    public int getPower(){
        return this.power;
    }
    public int getPositionX(){
        return this.positionX;
    }
    public int getPositionY(){
        return this.positionY;
    }



    public RacingVenue getCircle() {
        return circle;

    }
  //Dorin
    public void finish(long startTime) {
        this.finished = true;
        this.finishTime = System.currentTimeMillis() - startTime;
    }
  //AJ from here to comment below
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
//end AJ, draw() made by Dorin
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        switch (color) {
            case "blue":
                g2d.drawImage(new ImageIcon("resources/blue_car.gif").getImage(), x, y-50, WIDTH, HEIGHT, null);
                break;
            case "green":
                g2d.drawImage(new ImageIcon("resources/green_car.gif").getImage(), x, y-50, WIDTH, HEIGHT, null);
                break;
            case "yellow":
                g2d.drawImage(new ImageIcon("resources/yellow_car.gif").getImage(), x, y-50, WIDTH, HEIGHT, null);
                break;
            case "pink":
                g2d.drawImage(new ImageIcon("resources/pink_car.gif").getImage(), x, y-50, WIDTH, HEIGHT, null);
                break;
            default:
                g2d.drawImage(new ImageIcon("resources/gray_car.gif").getImage(), x, y-50, WIDTH, HEIGHT, null);
        }
    }


    @Override
    public String toString() {
    	String info = "";
    	info = info + "This car is " + color + " and has a " + engine + " engine with " + tireType + " tires and " + wheelSize + " wheel size\n";
    	return info;
    }
//Dov
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
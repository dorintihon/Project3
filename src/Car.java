// 3/9 - updated class with proper attributes, accessors, and mutators [AJ]
import javax.swing.*;
import java.awt.*;


/* TODO:         Adjust car class so when a car is created that:
   TODO:            -It will determine the speed of the car based on the engine/tire/wheel type (adjust power)
   TODO:            -Add enumeration for car colors to choose the png in dependence of the color
   TODO:
 */
class Car {


    private final int WIDTH = 40;
    private final int HEIGHT = 20;
    private Checkpoint startPoint;
    private Checkpoint endPoint;
    private int engine;
    private int tire;
    private int wheel;
    private Color color;
    private double speed = 0.0;
    private int power;
    private int positionX;
    private int positionY;

    Image carImg = new ImageIcon("resources/car1.png").getImage();

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
    public Car(Checkpoint sta, Checkpoint end, int e, int t, int w, Color color, int x, int y) {

        //  setting start/end points
        this.startPoint = sta;
        this.endPoint = end;

        //  car attributes that affect power/speed
        this.engine = e;
        this.tire = t;
        this.wheel = w;

        //  setting speed and power
        //  NOTE: Engine/Tire/Wheel calculation


        int eSpeed = 0;
        int tSpeed = 0;
        int wSpeed = 0;


        //  NOTE: Engines can have an index up to 6. For every 2 index levels, a different speed value is set in eSpeed.

        if (e == 1 || e == 2){
            eSpeed = 5;
        }
        if (e == 3 || e == 4){
            eSpeed = 7;
        }
        if (e == 5 || e == 6){
            eSpeed = 10;
        }

        //  NOTE: Tires can have an index up to 6. For every 2 index levels, a different speed value is set in tSpeed.

        if (t == 1 || t == 2){
            tSpeed = 5;
        }
        if (t == 3 || t == 4){
            tSpeed = 7;
        }
        if (t == 5 || t == 6){
            tSpeed = 10;
        }

        //  NOTE: Wheels can have an index up to 6. For every 2 index levels, a different speed value is set in wSpeed.

        if (w == 1 || w == 2){
            wSpeed = 5;
        }
        if (w == 3 || w == 4){
            wSpeed = 7;
        }
        if (w == 5 || w == 6){
            wSpeed = 10;
        }


        //  Speed calculation
        //  NOTE: Speed is evaluated by considering the Engine/Tires/Wheels
        if(eSpeed == 5 && tSpeed == 5 && wSpeed == 5){
            this.speed = 5;
        }
        if((eSpeed == 5 || eSpeed == 7) && (tSpeed == 5 || tSpeed == 7) && (wSpeed == 5 || wSpeed == 7)){
            this.speed = 10;
        }
        if((eSpeed == 7 || eSpeed == 10) && (tSpeed == 5 || tSpeed == 7) && (wSpeed == 5 || wSpeed == 7)){
            this.speed = 10;
        }


        this.color = color;
        //this.power
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

    public void setCircle(RacingVenue circle) {
        this.circle = circle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
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
// 3/9 - updated class with proper attributes, accessors, and mutators [AJ]
import javax.swing.*;
import java.awt.*;

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



    public void move(double speed) {
        this.speed += speed;
    }


    public void draw(Graphics g) {
        int x = (int) (circle.getX() + circle.getRadius() * Math.cos(speed)) - WIDTH/2;
        int y = (int) (circle.getY() + circle.getRadius() * Math.sin(speed)) - HEIGHT/2;

        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(speed, x + WIDTH/2, y + HEIGHT/2);
        g2d.drawImage(carImg, x-15, y, WIDTH+25, HEIGHT+50, null);
        g2d.rotate(-speed, x + WIDTH/2, y + HEIGHT/2);
    }

}
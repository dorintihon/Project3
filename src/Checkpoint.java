
import java.awt.Color;
import java.awt.Graphics;

public class Checkpoint {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean hasCar;
    private Car myCar;

    public Checkpoint(int x, int y, int width, int height, Car car) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hasCar = false;
        myCar = car;
    }

    public void draw(Graphics g) {
       g.setColor(Color.RED);
        g.fillRect(x-5, y, width, height);
    }

    public boolean hasCar() {
    	if(myCar.getPositionX() == x && myCar.getPositionY() == y) {
    		hasCar = true;
    		System.out.println("CAR!");
    	}
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }
}


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;

public class Checkpoint extends Rectangle{

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean hasCar;
    private Car myCar;

    public Checkpoint(int x, int y, int width, int height, Car car) {
        this.x = x;
        this.y = y;
        this.width = width + 10;
        this.height = height + 10;
        this.hasCar = false;
        myCar = car;
    }

    public void draw(Graphics g) {
       g.setColor(Color.RED);
        g.fillRect(x-5, y, width - 10, height - 10);
    }

    public boolean hasCar() {
    	if(SwingUtilities.computeIntersection(myCar.getPositionX(), myCar.getPositionY(), myCar.getWIDTH(), myCar.getHEIGHT(), this).getX() != 0) {
    		hasCar = true;
    		System.out.println("CAR!");
    	}
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }
   
    @Override
   public double getWidth() {
	   return width;
   }
   @Override
   public double getHeight() {
	   return height;
   }
}

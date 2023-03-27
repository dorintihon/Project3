
//Dorin with toString and equals made by Dov
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;

/*
 * The Checkpoint class tracks the location of Checkpoints in the race, and provides a draw method for RacingVenue to call.
 * It allows the Cars to know when they should stop and provides a visual finish line for the user.
 */


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
    
    @Override
    public String toString() {
    	String info = "";
    	
    	info = info + "This checkpoint's start is at " + startX + ", " + startY;
    	info = info + "\n This checkpoint's end is at " + endX + ", " + endY;
    	
    	return info;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj == this) return true;
    	if(obj == null) return false;
    	
    	if(obj instanceof Checkpoint) {
    		Checkpoint ch = (Checkpoint) obj;
    		
    		return ch.startX == this.startX && ch.endX == this.endX && ch.startY == this.startY && ch.endY == this.endY;
    	}
    	return false;
    }

}


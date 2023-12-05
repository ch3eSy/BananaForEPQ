package entities;

import java.awt.Rectangle;


public class hitbox {
	private int x,y,w,h;

	public hitbox(int x,int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public boolean intersects(hitbox other) {
		Rectangle thisRect = new Rectangle(x, y, w, h);
	    Rectangle otherRect = new Rectangle(other.x, other.y, other.w, other.h);
	    return thisRect.intersects(otherRect);
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

    public int getWidth() {
        return w;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public int getHeight() {
        return h;
    }

    public void setHeight(int h) {
        this.h = h;
    }
		
}

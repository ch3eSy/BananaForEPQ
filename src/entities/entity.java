package entities;

import java.awt.Rectangle;

public abstract class entity {
	
	protected int width, height;
	protected double posx,posy;
	protected Rectangle hitBox;
	public entity(int posx, int posy, int width, int height) {
		this.posx = posx;
		this.posy = posy; 
		this.width = width;
		this.height = height;
	}

}

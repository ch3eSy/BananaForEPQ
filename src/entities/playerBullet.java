package entities;

import java.awt.Color;
import java.awt.Graphics;

public class playerBullet extends entity{
	private int x;
	private int y;
	private boolean moving= false;
	private int direction;
	public playerBullet(int x,int y,int w,int h){
		super(x,y,w,h);
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)posx, (int)posy, 20, 5);
	}
	public void move(int x,int y,int dir) {
		direction = dir;
		if(direction==0) {
			posx=x;
			posy=y+15;
		}else if(direction==1) {
			posx=x+64;
			posy=y+15;
		}
		moving = true;
		
	}
	public void update() {
		if(moving) {
			if(direction == 1) {
				posx+=3;
			}else if(direction == 0) {
				posx-=3;
			}
		}
		
	}
}
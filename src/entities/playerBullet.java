package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Game;

public class playerBullet extends entity{
	private int x;
	private int y;
	private boolean moving= false;
	private int direction;
	private Game game;
	public playerBullet(int x,int y,int w,int h, Game game){

		super(x,y,w,h);
		this.game=game;
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)posx, (int)posy, 20, 5);
	}
	public void move(int dir) {
		direction = dir;

		moving = true;
		
	}
	public boolean update() {
		if(moving) {
			if(direction == 1) {
				posx+=3;
			}else if(direction == 0) {
				posx-=3;
			}
		}
		if((posx>=1920)||posx<=0) {
			return true;
		}else {
			return false;
		}
	}
	public Rectangle getHitbox() {
		return new Rectangle((int)posx,(int)posy,20,5);
	}
}
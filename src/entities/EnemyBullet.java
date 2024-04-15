package entities;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;

public class EnemyBullet extends entity{
	private int x;
	private int y;
	private Game game;
	private int direction;
	private boolean moving;
	public EnemyBullet(int x,int y,int w,int h, Game game){
		super(x,y,w,h);
		this.game = game;
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
			if(direction == 0) {
				posx+=1;
			}else if(direction == 1) {
				posx-=1;
			}
		}
		if((posx>=1920)||posx<=0) {
			return true;
		}else {
			return false;
		}
	}
}

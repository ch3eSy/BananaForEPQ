package entities;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyBullet extends entity{
	private int x;
	private int y;
	public EnemyBullet(int x,int y,int w,int h){
		super(x,y,w,h);
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)posx, (int)posy, 20, 5);
	}
	public void move(int x,int y) {
		posx=x;
		posy=y+15;
		
	}
	public void update() {
		posx-=1;
	}
}

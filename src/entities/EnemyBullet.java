package entities;

import java.awt.Color;
import java.awt.Graphics;

public class EnemyBullet extends entity{
	public EnemyBullet(int x,int y,int w,int h){
		super(x,y,w,h);
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)posx, (int)posy, 20, 5);
	}
	public void move() {
		
	}
}

package entities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Spikes extends entity{
	private Image anispike;
	private double OriginX, OriginY;
	public Spikes(int x,int y,int w,int h) {
		super(x,y,w,h);
		OriginX = x;
		OriginY=y;
		spikesloadAnimations();
	}
	public void render(Graphics g) {
		g.drawImage(anispike,(int)posx,(int)posy,(int)width,(int)height,null);
	}
	
	private void spikesloadAnimations() {
		InputStream is = getClass().getResourceAsStream("/spike.png");
		try {
			Image img = ImageIO.read(is);
			anispike = img;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void update() {
		posx=0;
		posy=0;

	}
	public Rectangle getHitbox() {
		return new Rectangle((int)posx+3,(int)posy-10,32,20);
	}
	public void resetScroll() {
		posx = OriginX;
	}


    public double getX() {
        return posx;
    }
	public double getY() {
		return posy;
	}
	public void scroll(float speed) {
		if((posx==OriginX)&&speed<0) {	
			posx=posx;
		}else {
			posx-=speed;
		}
	}
}







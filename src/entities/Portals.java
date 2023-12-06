package entities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Portals extends entity{
	private Image aniport;
	public Portals(int x,int y,int w,int h) {
		super(x,y,w,h);
		portalsloadAnimations();
	}
	public void render(Graphics g) {
		g.drawImage(aniport,(int)posx,(int)posy,(int)width,(int)height,null);
	}
	
	private void portalsloadAnimations() {
		InputStream is = getClass().getResourceAsStream("/portal.png");
		try {
			Image img = ImageIO.read(is);
			aniport = img;
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
		return new Rectangle((int)posx-5,(int)posy-5,128,128);
	}

    public double getX() {
        return posx;
    }
	public double getY() {
		return posy;
	}
}

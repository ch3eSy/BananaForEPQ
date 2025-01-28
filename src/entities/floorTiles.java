package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class floorTiles extends entity{
	private Image anifloor;
	private int scrolled=0;
	private double OriginX, OriginY;
	
	public floorTiles(int x,int y,int w,int h) {
		super(x,y,w,h);
		OriginX = x;
		OriginY=y;
		tileloadAnimations();
	}
	public void render(Graphics g) {
		if(posx<=2000&&posx>=-100) {
			g.drawImage(anifloor,(int)posx,(int)posy,(int)width,(int)height,null);
		}
	}
	
	private void tileloadAnimations() {
		InputStream is = getClass().getResourceAsStream("/box.png");
		try {
			Image img = ImageIO.read(is);
			anifloor = img;
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
	public Rectangle getHitbox() {
		return new Rectangle((int)posx+2,(int)posy,30,30);
	}
	public void scroll(float speed) {
		if((posx==OriginX)&&speed<0) {	
			posx=posx;
		}else {
			posx-=speed;
		}
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
	public double getWidth() {

		return width;
	}
	public double getHeight() {

		return height;
	}
}
	


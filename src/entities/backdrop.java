package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class backdrop extends entity{
	private Image animations;
	
	public backdrop(int x,int y,int w,int h) {
		super(x,y,w,h);
		loadAnimations();
	}
	
	public void render(Graphics g) {
		g.drawImage(animations,(int)posx,(int)posy,(int)width,(int)height,null);
	}
	
	private void loadAnimations() {
		InputStream is = getClass().getResourceAsStream("/background.png");
		
		try {
			Image img = ImageIO.read(is);
			animations = img;
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
		posx = 0;
		posy = 0;
	}
	
}

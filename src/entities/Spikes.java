package entities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Spikes extends entity{
	private Image anispike;
	private double OriginX, OriginY;
	private int rotation;
	private BufferedImage[][] animations;
	private int aniTick, aniSpd = 25, aniIndex;
	public Spikes(int x,int y,int w,int h, int r) {
		super(x,y,w,h);
		OriginX = x;
		OriginY=y;
		rotation = r;
		spikesloadAnimations();
	}
	public void render(Graphics g) {
		g.drawImage(animations[rotation][aniIndex],(int)posx,(int)posy,(int)width,(int)height,null);
	}
	
	private void spikesloadAnimations() {
        InputStream is = getClass().getResourceAsStream("/spike.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[4][5];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 64, j * 64, 64, 64);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpd) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 5) {
                aniIndex = 0;
            }
        }
    }
	public void update() {
		updateAnimationTick();
		
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







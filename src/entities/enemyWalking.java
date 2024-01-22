package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class enemyWalking extends entity{
	private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpd = 25;
	private double OriginX, OriginY;
    public enemyWalking(int x,int y,int w,int h) {
		super(x,y,w,h);
		OriginX = x;
		OriginY=y;
		portalsloadAnimations();
	}
	public void render(Graphics g) {
		g.drawImage(animations[0][aniIndex],(int)posx,(int)posy,(int)width,(int)height,null);
	}
    private void portalsloadAnimations() {
        InputStream is = getClass().getResourceAsStream("/snailsprites.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[3][6];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 64, j * 64, 64, 64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void update() {
        updateAnimationTick();
	}
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpd) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 6) {
                aniIndex = 0;
            }
        }
    }
	public Rectangle getHitbox() {
		return new Rectangle((int)posx-5,(int)posy-5,96,128);
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
}

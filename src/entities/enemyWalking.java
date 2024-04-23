package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class enemyWalking extends entity{
	private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpd = 25;
	private double OriginX, OriginY;
	private boolean killed;
	private boolean trueorno;
	private Rectangle bullhitbox;
	private Rectangle enthitbox;
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
	public boolean update(List<playerBullet> playerBullets) {
		killed = checkCollisions(playerBullets);
		move();
        updateAnimationTick();
        return killed;
	}
	public boolean intersects1(Rectangle rectangle) {
        Rectangle thisRect = new Rectangle((int)posx,(int)posy,width,height);
        Rectangle otherRect = new Rectangle(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
        boolean result = thisRect.intersects(otherRect);
        return (result);
    }
    private boolean checkCollisions(List<playerBullet> playerBullets) {
    	for(playerBullet bullet : playerBullets) {
    		enthitbox = new Rectangle((int)posx,(int)posy,width,height);
    		bullhitbox = bullet.getHitbox();
    		if(enthitbox.intersects(bullhitbox)) {
    			System.out.println("Killed");
    			trueorno = true;
    		}else {
    			trueorno =  false;
    		}
    	}
    	return trueorno;
	}
    private void move() {
    	posx-=0.05;
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
	public void scroll(float speed,boolean scrollsnail) {
		if(!scrollsnail) {
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

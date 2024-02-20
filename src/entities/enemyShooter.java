package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static utils.Constants.enemyConstants.GetSpriteLength;
import static utils.Constants.enemyConstants.idle;
import static utils.Constants.enemyConstants.runningleft;
import static utils.Constants.enemyConstants.attack;
import javax.imageio.ImageIO;

import main.Game;

public class enemyShooter extends entity{
	private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpd = 25;
	private double OriginX, OriginY;
	private int enemyaction = runningleft;
	public Timer timer;
//	public Graphics g;
	private EnemyBullet bullet;
	private Game game;
	private int shootdelay= 0;
	private int shots=0;
	private Rectangle bullhitbox;
	private Rectangle enthitbox;
    public enemyShooter(int x,int y,int w,int h, Game game) {
		super(x,y,w,h);
		this.game = game;
		OriginX = x;
		OriginY=y;
		loadAnimations();
	}
	public void render(Graphics g) {
		g.drawImage(animations[enemyaction][aniIndex],(int)posx,(int)posy,(int)width,(int)height,null);
	}
    private void loadAnimations() {
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
	public void update(List<playerBullet> playerBullets) {
		checkCollisions(playerBullets);
		move();
        updateAnimationTick();
	}
    public boolean intersects1(Rectangle rectangle) {
        Rectangle thisRect = new Rectangle((int)posx,(int)posy,width,height);
        Rectangle otherRect = new Rectangle(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
        boolean result = thisRect.intersects(otherRect);
        return (result);
    }
    private void checkCollisions(List<playerBullet> playerBullets) {
    	for(playerBullet bullet : playerBullets) {
    		enthitbox = new Rectangle((int)posx,(int)posy,width,height);
    		bullhitbox = bullet.getHitbox();
    		if(enthitbox.intersects(bullhitbox)) {
    			System.out.println("Killed");
    		}
    	}
	}
	private void move() {
    	enemyaction = runningleft;
    	posx-=0.05;
    	shootdelay +=1;
    	if(shootdelay==1000) {
    		shootdelay=0;
    		shots+=1;
    		shoot();
    	}
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
	
	public void shoot() {
		game.enemyshoot(posx,posy,shots);
		if(shots==5) {
			shots = 0;
		}
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

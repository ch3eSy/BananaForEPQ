package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
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
    private int aniTick, aniIndex, aniSpd = 22;
	private double OriginX, OriginY;
	private int enemyaction = attack;
	public Timer timer;
//	public Graphics g;
	private EnemyBullet bullet;
	private Game game;
	private int shootdelay= 0;
	private int shots=0;
	private Rectangle bullhitbox;
	private Rectangle enthitbox;
	private boolean timetoShoot;
	private int count;
	private boolean delay;
	private boolean trueorno;
	private boolean killed;
	private int randomnum = (int)(Math.random() * 3001);
	private boolean onscreen;
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
        InputStream is = getClass().getResourceAsStream("/Monkey.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[3][7];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 128, j * 128, 128, 128);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public boolean update(List<playerBullet> playerBullets) {
		killed = checkCollisions(playerBullets);
		move();
        updateAnimationTick();
        delaytime();
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
    			bullet.toRemove();
    			System.out.println("Killed");
    			return true;
    		}
    			

    	}
    	return false;
	}
	private void move() {
//    	enemyaction = attack;
		if(posx>=1920||posx<=0) {
			onscreen=false;
		}


    	if(timetoShoot) {
    		timetoShoot = false;
    		shots +=1;
    		shoot();
    	}
	}

	private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpd) {
            aniTick = 0;
            aniIndex++;
//            if(aniIndex == )
            if(aniIndex==5&&enemyaction==attack) {
            	timetoShoot = true;
            }
            if (aniIndex >= 7) {
                aniIndex = 0;
            }
        }
    }
	public Rectangle getHitbox() {
		return new Rectangle((int)posx-5,(int)posy-5,96,128);
	}
	
	public void shoot() {
		game.enemyshoot(posx,posy,shots,1);

		if(shots==5) {
			shots = 0;
			enemyaction = idle;
			startdelay();
		}
	}
	
    private void delaytime() {
		if(delay) {
			if((count==randomnum)&&onscreen==true) {
				enemyaction = attack;
			}else if((count==randomnum)&&onscreen==false) {
				count=count;
			}else {
				count+=1;
			}
		}
		
	}
	private void startdelay() {
		randomnum = (int)(Math.random() * 3001);
		delay = true;
		count = 0;

	}
	public void scroll(float speed) {

			posx-=speed;

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

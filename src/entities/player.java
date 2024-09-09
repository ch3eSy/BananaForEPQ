package entities;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.GetSpriteAmount;
import static utils.Constants.PlayerConstants.idle;
import static utils.Constants.PlayerConstants.runningleft;
import static utils.Constants.PlayerConstants.runningright;
import static utils.Constants.PlayerConstants.jumpingleft;
import static utils.Constants.PlayerConstants.jumpingright;
import static utils.Constants.PlayerConstants.attack;
import static utils.Constants.PlayerConstants.attackright;
import static entities.backdrop.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;
import entities.floorTiles.*;
import main.GamePanel;
import main.Game;
import main.Game.*;
import java.util.List;
import java.awt.Graphics;
import java.awt.Rectangle;


public class player extends entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpd = 15;
    private int playeraction = idle;
    private boolean movingleft = false, movingright = false, movingupleft = false, movingupright = false, movingup = false,attacking = false, movingdownright = false, movingdownleft = false, movingdown = false;
    private boolean left, up, right, down;
    private float playerSpeed = 0.01f;
    private float gravity = 0.005f;
    private double boundaryx = 1450, boundaryy = 1080;
    private Timer timer;
    public boolean isJumping = true;
    private float jumpspeed = 2.25f;
    private int vsp = 0;
    private float hsp = 0f;
    private int hittingtile = 0;
    private boolean isHittingSide;
    private boolean isOntile;
    private int jumpTimer = 0;
    private static final int JUMP_TIMER_MAX = 5;
    private floorTiles currentFloorTile;
    private Spikes currentSpike;
    long lastUpdateTime;
    private float acceleration = 1.0f;
    private Game game;
    private int scrolled = 0;
	private int shots= 0;
	private int dir = 0;
	private int playerMaxR = -5,playerMaxL= 5;
	private Thread playerThread;	
	private playerProcess process;
	private List<floorTiles> tiles;
	private int count;
	private List<Spikes> spikes;
	private List<Portals> portals;
	private List<enemyWalking> snail;
	private List<EnemyBullet> EnemBulls;
	private int lives = 3;
	private int i;
	
	
	private class playerProcess implements Runnable{
		private int counter;
		private final int fps_max = 140;
		private final int ups_max = 200;
		private int tutorial=1;

		public playerProcess() {
			startPlayerProcess();
			
		}
		public void startPlayerProcess() {
	        playerThread=new Thread(this);
	        playerThread.start();
		}
		public void update() {
			if(posx>1400&&tutorial==1) {
				tutorial=2;
				game.tutpart(tutorial);
				posx=100;
			}
			
			if(!tiles.isEmpty()) {
				isCollidingWith(tiles);
			}
			if(isShot(EnemBulls, tiles, spikes, portals, snail)) {
				lives--;
			}
			if(isHit(tiles, spikes, portals, snail)) {
				lives--;
			}
			if(isSpiking(tiles,spikes,portals,snail)) {
				lives--;
			}
		}
		private boolean isHit(List<floorTiles> floorTilesList, List<Spikes> spikelist, List<Portals> portallist,List<enemyWalking> snails) {
			for(enemyWalking snail : snails) {
				Rectangle playerHitbox = new Rectangle((int) posx, (int) posy, 80, 80);
				Rectangle snailHitbox = snail.getHitbox();
				if(playerHitbox.intersects(snailHitbox)) {
	            	posx = 300;
	            	posy = 900;
	        		for(floorTiles floortile : floorTilesList) {
	        			floortile.resetScroll();
	        		}
	        		for(Spikes spikeScroll : spikelist) {
	        			spikeScroll.resetScroll();
	        		}
	        		for(Portals portal : portallist) {
	        			portal.resetScroll();
	        		}
	        		for(enemyWalking snaill : snails) {
	        			snaill.resetScroll();
	        		}
	        		game.resetplayer();
	            	return true;
				}
			}
			return false;
		}
	    private boolean isShot(List<EnemyBullet> enemBulls,List<floorTiles> floorTilesList, List<Spikes> spikelist, List<Portals> portallist,List<enemyWalking> snails) {
			for(EnemyBullet bullet : enemBulls) {
				Rectangle playerHitbox = new Rectangle((int) posx, (int) posy, 80, 80);
				Rectangle bullethitbox = bullet.gethitbox();
				if(playerHitbox.intersects(bullethitbox)) {
	            	posx = 300;
	            	posy = 900;
	        		for(floorTiles floortile : floorTilesList) {
	        			floortile.resetScroll();
	        		}
	        		for(Spikes spikeScroll : spikelist) {
	        			spikeScroll.resetScroll();
	        		}
	        		for(Portals portal : portallist) {
	        			portal.resetScroll();
	        		}
	        		for(enemyWalking snail : snails) {
	        			snail.resetScroll();
	        		}
	        		game.resetplayer();
	            	return true;
	            }
	        }

	        return false;
			
		}
		public boolean isSpiking(List<floorTiles> floorTilesList, List<Spikes> spikelist, List<Portals> portallist,List<enemyWalking> snails) {
	        for (Spikes spike : spikelist) {
	            Rectangle playerHitbox = new Rectangle((int) posx, (int) posy, 80, 80);
	            Rectangle spikeHitbox = spike.getHitbox();

	            if (playerHitbox.intersects(spikeHitbox)) {
	            	posx = 300;
	            	posy = 900;
	        		for(floorTiles floortile : floorTilesList) {
	        			floortile.resetScroll();
	        		}
	        		for(Spikes spikeScroll : spikelist) {
	        			spikeScroll.resetScroll();
	        		}
	        		for(Portals portal : portallist) {
	        			portal.resetScroll();
	        		}
	        		for(enemyWalking snail : snails) {
	        			snail.resetScroll();
	        		}
	        		game.resetplayer();
	            	return true;
	            }
	        }

	        return false;
	    }
	    public boolean isCollidingWith(List<floorTiles> floorTilesList) {
	        hittingtile = 0;
	        isOntile = false;

	        for (floorTiles floortile : floorTilesList) {
	            Rectangle playerHitbox = new Rectangle((int) posx+5, (int) posy+20, 58, 59);
	            Rectangle floorHitbox = floortile.getHitbox();

	            if (playerHitbox.intersects(floorHitbox)) {
	                hittingtile = 1;

	                int side1 = ((int) floortile.getX());
	                int side2 = (((int) floortile.getX())+((int)floortile.getWidth()));

	                if (posy + height <= floortile.getY() + 5 && posy + height >= floortile.getY()) {
	                    isOntile = true;

	                    vsp = 0;
	                }else if((posy-32 <= floortile.getY()&&posy>floortile.getY())) {
	                	posy = floortile.getY()+floortile.getWidth();
	                	vsp=0;
	                }

	                // Check if player is colliding with the sides of the box
	                if (((posx + width-5 >= side1 && posx <= side1) || (posx <= side2 && posx + width >= side2))&&((posy+height>=floortile.getY()+5)&&posy<=floortile.getY()+floortile.getHeight())) {
	                    isHittingSide = true;

	                    // Stop horizontal speed when hitting the side
	                    hsp = 0;
	                } else {
	                    isHittingSide = false;
	                }

	                // Check if player is standing on the tile
	                if (posy + height <= floortile.getY() + 5) {
	                    isOntile = true;
	                    currentFloorTile = floortile;  // Set the current floor tile
	                }

	                return true;
	            }
	        }

	        return false;
	    }
		
		@Override
		public void run() {
			
			double timePerFrame = 1000000000/fps_max;
			double timePerUpdate = 1000000000/ups_max;
			long prevtime = System.nanoTime();
			
			
			int frames = 0;
			int updates = 0;
			long lastCheck = System.currentTimeMillis();
			
			double deltau = 0;
			double deltaf = 0;
			
			
			
			while(true) {
				
				long currentTime = System.nanoTime();
				
				deltau += (currentTime - prevtime) / timePerUpdate;
				deltaf +=(currentTime - prevtime) / timePerFrame;
				prevtime = currentTime;
				
				if(deltau>=1) {
					update();
					updates++;
					deltau--;
				}
				
				
	/*			if(now - lastFrame >= timePerFrame) {
					
					gamePanel.repaint();
					lastFrame = now;
					frames++;
				}
				
				
	*/
				if (System.currentTimeMillis() - lastCheck >=1000) {
					lastCheck = System.currentTimeMillis();
					frames=0;
					updates=0;
				}
			}
			
		}
	}
    
    public class OnTile {
        public OnTile(int seconds) {
            timer = new Timer();
            timer.schedule(new StandingTile(), seconds * 250);
        }

        class StandingTile extends TimerTask {
            public void run() {
                isOntile = true;
                timer.cancel();
            }
        }
    }

    public player(int x, int y, int w, int h, Game game) {
        super(x, y, w, h);
        this.game = game;
        loadAnimations();

    }

    public boolean intersects1(Rectangle rectangle) {
        Rectangle thisRect = new Rectangle((int) posx, (int) posy, 80, 80);
        Rectangle otherRect = new Rectangle(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
        boolean result = thisRect.intersects(otherRect);
        return (result);
    }

    public Rectangle getHitbox() {
        return new Rectangle((int) posx, (int) posy, width, height);
    }


    

    public boolean touchingPortal(List<Portals> portallist) {
        for (Portals portal : portallist) {
            Rectangle playerHitbox = new Rectangle((int) posx, (int) posy, 80, 80);
            Rectangle portalHitbox = portal.getHitbox();

            if (playerHitbox.intersects(portalHitbox)) {

            	
            	game.reset();

            	return true;
            }
        }

        return false;
    }
    public void update(List<floorTiles> floorTilesList,List<Spikes> spikelist, List<Portals> portallist, List<enemyWalking> snails,List<enemyShooter> monkeys,List<playerBullet> bulls,List<EnemyBullet>Ebullets) {
    	tiles = floorTilesList;
    	spikes=spikelist;
    	portals=portallist;
    	snail=snails;
    	EnemBulls = Ebullets;
        touchingPortal(portallist);
        updatePos();
        checkScroll(floorTilesList, spikelist, portallist,snails,monkeys,bulls,Ebullets);
        updateAnimationTick();
        setAnimation();
        if(process==null) {
        	process = new playerProcess();
        }
    }

    private void checkScroll(List<floorTiles> floorTilesList, List<Spikes> spikelist, List<Portals> portallist,List<enemyWalking> snails,List<enemyShooter> monkeys,List<playerBullet> bulls,List<EnemyBullet>Ebullets ) {
    	if(scrolled>=6200&&right) {
    		scrolled=6200;
    		return;
    	}
    	if(posx>=boundaryx&&hsp>0) {
    		for(floorTiles floortile : floorTilesList) {
    			floortile.scroll(hsp);
    		}
    		for(Spikes spike : spikelist) {
    			spike.scroll(hsp);
    		}
    		for(Portals portal : portallist) {
    			portal.scroll(hsp);
    		}
    		for(enemyWalking snail : snails) {
        		 
            	snail.scroll(hsp);
    		}
    		for(enemyShooter monkey : monkeys) {
        		 
            	monkey.scroll(hsp);
    		}
    		for(EnemyBullet ebull : Ebullets) {
        		 
            	ebull.scroll(hsp);
    		}
    		for(playerBullet pbull : bulls) {
        		 
            	pbull.scroll(hsp);
    		}
    			
    		
    		scrolled+=hsp;
    		posx = boundaryx;
    	}
    	if(posx<=250&&hsp<0&&scrolled>0) {
    		for(floorTiles floortile : floorTilesList) {
    			floortile.scroll(hsp);
    		}
    		for(Spikes spike : spikelist) {
    			spike.scroll(hsp);
    		}
    		for(Portals portal : portallist) {
    			portal.scroll(hsp);
    		}
    		for(enemyWalking snail : snails) {
            	snail.scroll(hsp);
    		}
    		for(enemyShooter monkey : monkeys) {
            	monkey.scroll(hsp);
    		}
    		for(EnemyBullet ebull : Ebullets) {
    			ebull.scroll(hsp);
    		}
        	posx = 250;
    			
    	}


    	
	}

	public void render(Graphics g) {
        g.drawImage(animations[playeraction][aniIndex], (int) posx, (int) posy, (int) width, (int) height, null);
        for(i=0;i<lives;i++) {
        	g.drawImage(animations[idle][aniIndex], 50+(i*32), 50, (int) width, (int) height, null);
		}
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpd) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playeraction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAni = playeraction;

        if (movingleft)
            playeraction = runningleft;
        else if (movingright)
            playeraction = runningright;
        else if (movingupleft)
            playeraction = jumpingleft;
        else if (movingupright)
            playeraction = jumpingright;
        else if (movingup)
            playeraction = jumpingleft;
        else if (movingdownleft)
            playeraction = jumpingleft;
        else if (movingdownright)
            playeraction = jumpingright;
        else if (movingdown) {
            playeraction = jumpingright;
        } else
            playeraction = idle;
        if (attacking)
            if (movingright)
                playeraction = attackright;
            else if (movingleft)
                playeraction = attack;
            else if (movingupleft)
                playeraction = attack;
            else if (movingupright)
                playeraction = attackright;
            else
                playeraction = attack;

        if (startAni != playeraction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        long currentTime = System.currentTimeMillis();
		float deltaTime = (currentTime - lastUpdateTime) / 1000.0f;
        lastUpdateTime = currentTime;
        class JumpTime {
            public JumpTime(int seconds) {
                timer = new Timer();
                timer.schedule(new Jumped(), seconds * 250);
            }

            class Jumped extends TimerTask {
                public void run() {
                    isJumping = true;
                    timer.cancel();
                }
            }
        }

        movingleft = false;
        movingright = false;
        movingupleft = false;
        movingupright = false;
        movingup = false;
        movingdownleft = false;
        movingdownright = false;
        movingdown = false;
        
        
        
        if (vsp == 0 && hittingtile != 1 && posy <= boundaryy - 73) {
            isJumping = true;
        }

        if (posy >= boundaryy-72) {
            isJumping = false;
            vsp = 0;
        }

        if ((isJumping)) {
        	
            vsp += gravity;
            
        }

        if ((posy >= boundaryy - 73) || (hittingtile == 1)) {
            isJumping = false;
        }

        if (vsp > 2) {
            vsp = 2;
        } else if (vsp < -2) {
            vsp = -2;
        }
        if (vsp >= -0.4 && vsp <= 0.4) {
            gravity = 1f;
        }

        if (hsp > 3) {
            hsp = 3;
        } else if (hsp < -3) {
            hsp = -3;
        }

        if (vsp > 0) {
            if (left && !right) {
                movingdownleft = true;
            } else if (right && !left) {
                movingdownright = true;
            } else if (!right && !left) {
                movingdown = true;
            }
        }

        if (left && !right) {

                hsp -= playerSpeed;
                if (up && posy >= -31) {
                    movingupleft = true;
                } else if (down && posy <= (boundaryy - 1)) {
                    movingdownleft = true;
                } else {
                    movingleft = true;
                }

        } else if (right && !left) {
                hsp += playerSpeed;

                if (up && posy >= -31) {
                    movingupright = true;
                } else if (down && posy <= (boundaryy - 1)) {
                    movingdownright = true;
                } else {
                    movingright = true;
                }

        }else if(!left && !right) {
        	hsp = 0;
        }

        if (up && !down && !isJumping) {
            vsp -= jumpspeed;
            if (isOntile) {
                new JumpTime(1);
                isOntile = false;
            } else {
                new JumpTime(1);
            }
            if (!left && !right) {
                movingupright = true;
            }

        } else if (down && !up) {
            if (posy >= boundaryy) {

            } else {
                posy += playerSpeed;
                movingdown = true;	
            }
        }
        if (isJumping) {
        	if(left&&!right) {
        		hsp -= playerSpeed/2;
        	} else if (right&&!left) {
        		hsp += playerSpeed/2;
        	}
        }

        if (isOntile && !isHittingSide) {
        	movingdown = false;
            float boxWalkSpeed = 0.02f;
            if (left) {
                hsp -= boxWalkSpeed;
                if(posx>=0) {
                	if(hsp>10) {
                		hsp=10;
                	}else if(hsp<-10) {
                		hsp=-10;
                	}
                	posx+=hsp;
                }else {
                	posx=0;
                }
            } else if (right) {
            	hsp += boxWalkSpeed;
            	if(posx<=boundaryx) {
                	if(hsp>10) {
                		hsp=10;
                	}else if(hsp<-10) {
                		hsp=-10;
                	}
            		posx+=hsp;
            	}else {
            		posx = boundaryx;
            	}
            }
            if(up&&left) {
            	if(posx>=0) {
            		posx-=playerSpeed;
            	}else if(posx<0) {
            		posx=0;
            	}
            }else if(up&&right){
            	if(posx<=boundaryx) {
            		posx+=playerSpeed;
            	}else if(posx>boundaryx) {
            		posx=boundaryx;
            	}
            }
            
        }

        if (!isOntile) {
            posy += vsp;


            if (isHittingSide) {
                if (left) {
                    posx += hsp; 
                } else if (right) {
                    posx += hsp; 
                }
            } else {
            	if(posx>=250&&posx<=boundaryx) {
                	if(hsp>10) {
                		hsp=10;
                	}else if(hsp<-10) {
                		hsp=-10;
                	}
            		posx += hsp;
            	}else if(posx>boundaryx) {
            		posx=boundaryx;
            	}else if(posx<250) {
            		posx=250;
            	}
            }
        }else {
        	if(posx<250&&left) {
        		posx=250;
        	}
        }

     // Check if the player is hitting the bottom of the box
        if (hittingtile == 1 && !up && !isOntile && posy < currentFloorTile.getY()) {
            isJumping = false;
            vsp = 0;
        }
    }
    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player-sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[8][5];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        if (!isJumping) {
            return up;
        }
        return up = false;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

	public void Attack() {
		if(left) {
			dir = 0;
		}else if(right) {
			dir = 1;
		}else if(!left && !right) {
			dir = 1;
		}else if(left && right) {
			dir=1;
		}
		
		shots +=1;
		game.Attack(posx, posy+40, shots, dir);

		
		if(shots==5) {
			shots = 0;
		}
	}




}

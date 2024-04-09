package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import entities.EnemyBullet;
import entities.Portals;
import entities.Spikes;
import entities.backdrop;
import entities.enemyShooter;
import entities.floorTiles;
import entities.player;
import entities.playerBullet;
import entities.enemyWalking;
import main.levels.*;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int fps_max = 140;
	private final int ups_max = 200;
	public int level = 1;
    private List<floorTiles> floorTilesList;
	private player Player;

	private enemyWalking snail1;
	private backdrop Backdrop;
	private levels Level;
	private List<Spikes> spikeList;
	private List<Portals> portalList;
	private List<enemyWalking> snails;
	private List<enemyShooter> monkey;
	private boolean started = false;
	private int i = 0;
	private int colournum;
	private int bull=0;
	private List<EnemyBullet> bullets;
	private List<playerBullet> playerBullets;
	private EnemyBullet current;
	private int s = 0;
	private playerBullet curr;
	private int listlength;
	private boolean mustRemove;
	private playerBullet removal;
	
	
	public Game() {

		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		started = true;

//		gamePanel = new GamePanel(this);

		gamePanel.requestFocus();
		
		
		startGameLoop();

	}

	public void reset() {
		gamePanel.removeAll();
		level+=1;
		initClasses();
	}

	private void initClasses() {
        floorTilesList = new ArrayList<>();
        spikeList = new ArrayList<>();
        portalList = new ArrayList<>();
        snails = new ArrayList<>();
        monkey = new ArrayList<>();
        bullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        bullets.add(new EnemyBullet(500,20000,100,20));
        bullets.add(new EnemyBullet(500,20000,100,20));
        bullets.add(new EnemyBullet(500,20000,100,20));
        bullets.add(new EnemyBullet(500,20000,100,20));
        bullets.add(new EnemyBullet(500,20000,100,20));        
        
        
        if(level==1) {
        	Player = new player(0,600,80,80,this);

        	snails.add(new enemyWalking(1000,1000,80,80));
        	floorTilesList = levels.level1(1);
        	spikeList = levels.level1(2);
        	portalList.add(new Portals(1503,128,128,128));
        	monkey.add(new enemyShooter(1500,968,80,80, this));

        }else if(level==2) {
        	floorTilesList = levels.level2(1);
        	portalList.add(new Portals(1500,800,128,128));
    		Player = new player(0,200,80,80,this);
//            spikeList.add(new Spikes(500,1048,32,32));
        }else if(level == 3) {
        	floorTilesList.add(new floorTiles(160, 1000, 32, 32));
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 4) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 5) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 6) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 7) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 8) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 9) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }else if(level == 10) {
        	portalList.add(new Portals(1500,900,128,128));
    		Player = new player(0,600,80,80,this);
        }



		Backdrop = new backdrop(0,0,1920,1080);
	}


	


	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void update() {
		removal = null;
        Player.update(floorTilesList,spikeList, portalList,snails);

	    for (Portals portal : portalList) {
	    	portal.update();
	    }
	    for(enemyWalking snail : snails) {
	    	snail.update();
	    }
	    if(!playerBullets.isEmpty()) {
	    	for(playerBullet playBull : playerBullets) {
	    		mustRemove = playBull.update();
	    		if(mustRemove) {
	    			removal = playBull;
	    		}
	    	}
	    	playerBullets.remove(removal);
	    }
	    for(enemyShooter enemShoot : monkey) {
	    	enemShoot.update(playerBullets);
	    }
	    for(EnemyBullet bullet : bullets) {
	    	bullet.update();
	    }
	}
	public void render(Graphics g) {
		Backdrop.render(g);
		Player.render(g);
		for(EnemyBullet bullet : bullets) {
			bullet.render(g);
		}
		if(!playerBullets.isEmpty()) {
			for(playerBullet playBull : playerBullets) {
				playBull.render(g);
			}
		}
		for(enemyShooter enemShoot : monkey) {
			enemShoot.render(g);
		}
		for(enemyWalking snail : snails) {
			snail.render(g);
		}
		for (floorTiles tile : floorTilesList) {
			tile.render(g);
		}
		for (Spikes spike : spikeList) {
	    	spike.render(g);
	    }
	    for (Portals portal : portalList) {
	    	if(level==1||level==6||level==11||level==16) {
	    		colournum=1;
	    	}else if(level==2||level==7||level==12||level==17) {
	    		colournum=2;
	    	}else if(level==3||level==8||level==13||level==18) {
	    		colournum=3;
	    	}else if(level==4||level==9||level==14||level==19) {
	    		colournum=4;
	    	}else if(level==5||level==10||level==15||level==20) {
	    		colournum=5;
	    	}
	    	
	    	portal.render(g, colournum);
	    	
	    }
	}
	
	
	@Override
	public void run() { //function for fps
		
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
			
			if(deltaf >= 1){
				gamePanel.repaint();
				frames++;
				deltaf--;
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
	
	public void enemyshoot(double posx, double posy, int b) {
		for(EnemyBullet bullett : bullets) {
			if(i < b) {
				current = bullets.get(i);
				current.move((int)posx,(int)posy);
//				bullett.move((int)posx,(int)posy);
				i++;

			}else {
				break;
			}

		}
		if(i==5) {
			i=0;
		}
		
	}
	
	public void windowFocusLost() {
		Player.resetDirBooleans();

	}
	
	public player getPlayer() {
		return Player;
	}

	
	public backdrop getBackdrop() {
		return Backdrop;
	}

	public void Attack(double posx, double posy, int b,int dir) {
		if(playerBullets.size()!=5) {
			if(dir == 0) {
				curr = new playerBullet((int)posx,(int)posy,60,5,this);
			}else if(dir==1) {
				curr = new playerBullet((int)posx+40,(int)posy,60,5,this);
			}
			curr.move(dir);
			
			playerBullets.add(curr);
		}
//		for(playerBullet bullet : playerBullets) {
//			if(s < b) {
//				curr = playerBullets.get(s);
//				curr.move((int)posx,(int)posy,(int)dir);
//				s++;
//			}else {
//				break;
//			}
//		}
//		if(s==5) {
//			s=0;
//		}
		
	}

	public void removeFromList(playerBullet toRemove) {
		playerBullets.remove(toRemove);
	}

	
	
	
}

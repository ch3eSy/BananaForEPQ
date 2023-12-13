package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import entities.Portals;
import entities.Spikes;
import entities.backdrop;
import entities.floorTiles;
import entities.player;
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
	private backdrop Backdrop;
	private levels Level;
	private List<Spikes> spikeList;
	private List<Portals> portalList;
	private boolean started = false;
	private int i = 0;
	private int colournum;
	
	
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
        if(level==1) {
        	Player = new player(0,600,80,80,this);
        	floorTilesList = levels.level1(1);
        	spikeList = levels.level1(2);
        	portalList.add(new Portals(1503,128,128,128));

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

	public Image Image(InputStream is) {
		// TODO Auto-generated method stub
		return null;
	}
	


	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void update() {
        Player.update(floorTilesList,spikeList, portalList);
	    for (Portals portal : portalList) {
	    	portal.update();
	    }
	}
	public void render(Graphics g) {
		Backdrop.render(g);
		Player.render(g);
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
	
	public void windowFocusLost() {
		Player.resetDirBooleans();
	}
	
	public player getPlayer() {
		return Player;
	}
	
	public backdrop getBackdrop() {
		return Backdrop;
	}
	
}

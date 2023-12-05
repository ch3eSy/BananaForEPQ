package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import entities.Spikes;
import entities.backdrop;
import entities.floorTiles;
import entities.player;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int fps_max = 140;
	private final int ups_max = 200;
	
    private List<floorTiles> floorTilesList;
	private player Player;
	private backdrop Backdrop;
	private List<Spikes> spikeList;
	
	
	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		
		startGameLoop();

	}


	private void initClasses() {
        floorTilesList = new ArrayList<>();
        spikeList = new ArrayList<>();
        floorTilesList.add(new floorTiles(0, 1048, 32, 32));
        floorTilesList.add(new floorTiles(32, 1048, 32, 32));
        floorTilesList.add(new floorTiles(64, 1048, 32, 32));
        floorTilesList.add(new floorTiles(500, 990, 32, 32));

        spikeList.add(new Spikes(500,1048,32,32));
		Player = new player(0,600,80,80);
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
        Player.update(floorTilesList,spikeList);
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
				System.out.println("FPS - " + frames + " ¦ UPS - " + updates);
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

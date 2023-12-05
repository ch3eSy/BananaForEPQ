package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

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
	private floorTiles floor;
	private floorTiles floor2;
	
	
	public Game() {
		initClasses();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		
		
		startGameLoop();

	}


	private void initClasses() {
        floorTilesList = new ArrayList<>();
        floorTilesList.add(new floorTiles(0, 690, 32, 32));
        floorTilesList.add(new floorTiles(32, 690, 32, 32));
        floorTilesList.add(new floorTiles(64, 690, 32, 32));
        floorTilesList.add(new floorTiles(96, 690, 32, 32));
        floorTilesList.add(new floorTiles(128, 690, 32, 32));
        floorTilesList.add(new floorTiles(160, 690, 32, 32));
        floorTilesList.add(new floorTiles(192, 690, 32, 32));
        floorTilesList.add(new floorTiles(224, 690, 32, 32));
        floorTilesList.add(new floorTiles(256, 690, 32, 32));
        floorTilesList.add(new floorTiles(288, 690, 32, 32));
        floorTilesList.add(new floorTiles(320, 690, 32, 32));
        floorTilesList.add(new floorTiles(352, 690, 32, 32));
        floorTilesList.add(new floorTiles(384, 690, 32, 32));
        floorTilesList.add(new floorTiles(416, 690, 32, 32));
        floorTilesList.add(new floorTiles(448, 690, 32, 32));
        floorTilesList.add(new floorTiles(480, 690, 32, 32));
        floorTilesList.add(new floorTiles(512, 690, 32, 32));
        floorTilesList.add(new floorTiles(544, 690, 32, 32));
        floorTilesList.add(new floorTiles(576, 690, 32, 32));
        floorTilesList.add(new floorTiles(608, 690, 32, 32));
        floorTilesList.add(new floorTiles(640, 690, 32, 32));
        floorTilesList.add(new floorTiles(672, 690, 32, 32));
        floorTilesList.add(new floorTiles(704, 690, 32, 32));
        floorTilesList.add(new floorTiles(736, 690, 32, 32));
        floorTilesList.add(new floorTiles(768, 690, 32, 32));
        floorTilesList.add(new floorTiles(800, 690, 32, 32));
        floorTilesList.add(new floorTiles(832, 690, 32, 32));
        floorTilesList.add(new floorTiles(864, 690, 32, 32));
        floorTilesList.add(new floorTiles(896, 690, 32, 32));
        floorTilesList.add(new floorTiles(928, 690, 32, 32));
        floorTilesList.add(new floorTiles(960, 690, 32, 32));
        floorTilesList.add(new floorTiles(992, 690, 32, 32));
        floorTilesList.add(new floorTiles(1024, 690, 32, 32));
        floorTilesList.add(new floorTiles(1056, 690, 32, 32));
        floorTilesList.add(new floorTiles(1088, 690, 32, 32));
        floorTilesList.add(new floorTiles(1120, 690, 32, 32));
        floorTilesList.add(new floorTiles(1152, 690, 32, 32));
        floorTilesList.add(new floorTiles(1184, 690, 32, 32));
        floorTilesList.add(new floorTiles(1216, 690, 32, 32));
        floorTilesList.add(new floorTiles(200, 570, 32, 32));
        floorTilesList.add(new floorTiles(232, 570, 32, 32));
		Player = new player(0,600,80,80);
		Backdrop = new backdrop(0,0,1280,800);
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
        Player.update(floorTilesList);
	}
	public void render(Graphics g) {
		Backdrop.render(g);
		Player.render(g);
	    for (floorTiles tile : floorTilesList) {
	        tile.render(g);
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

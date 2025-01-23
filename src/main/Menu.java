package main;

import java.awt.Font;
import java.awt.Graphics;
import menuEnt.menuBackdrop;
import javax.swing.*;

public class Menu implements Runnable{
	private MenuPanel menuPanel;
	private MenuWindow menuWindow;
	private Thread menuThread;
	private menuBackdrop backg;
	private final int fps_max = 240;
	private final int ups_max = 200;
	private JButton startbutton;
	private ImageIcon startIcon;
	public Menu() {

		initclasses();
		menuPanel = new MenuPanel(this);
		menuPanel.setLayout(null);
		menuWindow = new MenuWindow(menuPanel);

	    startIcon = new ImageIcon("/mainmenu.png");
		startbutton = new JButton(startIcon);
		startbutton.setBounds(885, 320, 250,100);
		menuPanel.add(startbutton);



		menuPanel.requestFocus();
		
		
		startGameLoop();

	}
	
	private void initclasses() {

		backg = new menuBackdrop(0,0,1920,1080);

	}

	public void render(Graphics g) {
		backg.render(g);
	}
	
	public void startGameLoop() {
		menuThread = new Thread(this);
		menuThread.start();
	}
	public void update() {
		backg.update();
	}
	
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
				menuPanel.repaint();
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

	}
}

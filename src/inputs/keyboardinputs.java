package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import main.GamePanel;
import main.GameWindow;

import static utils.Constants.Directions.*;

public class keyboardinputs implements KeyListener {
	private GamePanel gamePanel;
	private GameWindow window;
	private Clip clip;
	public keyboardinputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case  KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(false);
			
			break;
		case  KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setUp(false);
			
			break;
		case  KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(false);
			
			break;
		case  KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(false);
			
			break;
		case  KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(false);
			break;

		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case  KeyEvent.VK_W:
			gamePanel.getGame().getPlayer().setUp(true);
			break;
		case  KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setUp(true);
			gamePanel.getGame().loadSound("/PlayerBeep.wav");
			break;
		case  KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(true);
			break;
		case  KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(true);
			break;
		case  KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(true);
			break;
		case KeyEvent.VK_E:
			gamePanel.getGame().getPlayer().Attack();
			break;
		case KeyEvent.VK_F:
			break;

		}
		
		
	}



}

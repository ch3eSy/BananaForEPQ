package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import static utils.Constants.Directions.*;

public class keyboardinputs implements KeyListener {
	private GamePanel gamePanel;
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
			System.out.println("W");
			break;
		case  KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setUp(true);
			System.out.println("space");
			break;
		case  KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(true);
			System.out.println("A");
			break;
		case  KeyEvent.VK_S:
			gamePanel.getGame().getPlayer().setDown(true);
			System.out.println("S");
			break;
		case  KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(true);
			System.out.println("D");
			break;
		case KeyEvent.VK_E:
			gamePanel.getGame().getPlayer().setAttacking(true);
			break;
		}
		
		
	}



}

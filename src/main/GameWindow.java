package main;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class GameWindow{
	private JFrame jframe;
	private JLabel displayfield;
	private ImageIcon image;
	
	public GameWindow(GamePanel gamePanel) {
		
		
		jframe= new JFrame("Beans");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(gamePanel);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.setUndecorated(true);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
		jframe.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {

				
			}
			

			@Override
			public void windowLostFocus(WindowEvent e) {
				System.out.println("lost");
				gamePanel.getGame().windowFocusLost();
				
			}
			
			
			
		});
		
		
		

	}
}

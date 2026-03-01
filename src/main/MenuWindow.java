package main;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class MenuWindow{
	private JFrame jframe;

	public MenuWindow(MenuPanel menupanel) {
		jframe= new JFrame("Banana | Back To The Tree");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jframe.add(menupanel);
		jframe.setUndecorated(true);
		jframe.setResizable(true);
		jframe.setVisible(true);
		jframe.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {

				
			}
			

			@Override
			public void windowLostFocus(WindowEvent e) {
				menupanel.getMenu().windowFocusLost();
				
			}
			

			
			
			
		});
		jframe.pack();
		
		


	}
	public void kill() {
		jframe.dispose();
	}


}

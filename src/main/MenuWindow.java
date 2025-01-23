package main;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.*;

public class MenuWindow{
	private JFrame jframe;

	public MenuWindow(MenuPanel menupanel) {
		jframe= new JFrame("Beans");
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
				System.out.println("lost");
				menupanel.getMenu().windowFocusLost();
				
			}
			

			
			
			
		});
		jframe.pack();
		
		
		

	}


}

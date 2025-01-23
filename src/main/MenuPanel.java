package main;
import java.awt.Dimension;
import java.awt.Graphics;
import inputs.keyboardinputs;
import inputs.mouseinputs;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private mouseinputs mouseInputs;
	private Menu menu;
	
	public MenuPanel(Menu menu) {
		this.menu = menu;
		setPanelSize();
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1920,1080);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		menu.render(g);
		
	}
	public Menu getMenu() {
		return menu;
	}

}

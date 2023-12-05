package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import inputs.keyboardinputs;
import inputs.mouseinputs;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel{ //creates class for gamepanel using jpanel library (for graphics panel)
	
	
	private mouseinputs mouseInputs;
	private Game game;
	
	public GamePanel(Game game) {

		mouseInputs = new mouseinputs(this);
		this.game = game;
		setPanelSize();
		addKeyListener(new keyboardinputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	




	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}


	public void updateGame() {

	}
	
	public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		
		
		
		game.render(g);
		


		
	}
	public Game getGame() {
		return game;
	}

}

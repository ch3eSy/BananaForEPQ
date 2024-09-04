package entities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class tutorialText extends entity{

	private boolean tutorial1=true;
	public tutorialText(int posx, int posy, int width, int height) {
		super(posx, posy, width, height);

	}

	public void render(Graphics g) {
		if(tutorial1) {
		g.setColor(Color.RED);
		g.setFont(new Font("Stanberry", Font.BOLD, 50));
		g.drawString("These Are Spikes.", (int)posx, (int)posy);
		g.setFont(new Font("Stanberry", Font.BOLD, 40));
		g.drawString("You die when you hit them", (int)posx, (int)posy+40);
		g.setFont(new Font("Stanberry", Font.BOLD, 20));
		g.drawString("S", (int)posx, (int)posy+60);
		g.setColor(Color.orange);
		g.drawString("O", (int)posx+10, (int)posy+60);
		g.setColor(Color.yellow);
		g.drawString("D", (int)posx+35, (int)posy+60);
		g.setColor(Color.green);
		g.drawString("O", (int)posx+50, (int)posy+60);
		g.setColor(Color.blue);
		g.drawString("N", (int)posx+65, (int)posy+60);
		g.setColor(Color.magenta);
		g.drawString("'", (int)posx+80, (int)posy+60);
		g.setColor(Color.red);
		g.drawString("T", (int)posx+85, (int)posy+60);
		g.setColor(Color.orange);
		g.drawString("H", (int)posx+105, (int)posy+60);
		g.setColor(Color.yellow);
		g.drawString("I", (int)posx+118, (int)posy+60);
		g.setColor(Color.green);
		g.drawString("T", (int)posx+125, (int)posy+60);
		g.setColor(Color.blue);
		g.drawString("T", (int)posx+145, (int)posy+60);
		g.setColor(Color.magenta);
		g.drawString("H", (int)posx+160, (int)posy+60);
		g.setColor(Color.red);
		g.drawString("E", (int)posx+175, (int)posy+60);
		g.setColor(Color.orange);
		g.drawString("M", (int)posx+190, (int)posy+60);
		}
	
	}
	public void setFalse1() {
		tutorial1= false;
	}
}

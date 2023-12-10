package entities;
import static utils.Constants.PlayerConstants.GetSpriteAmount;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Portals extends entity{
	private BufferedImage[][] aniport;
    private int aniTick, aniIndex, aniSpd = 25;
    public Portals(int x,int y,int w,int h) {
		super(x,y,w,h);
		portalsloadAnimations();
	}
	public void render(Graphics g, int colournum) {
		g.drawImage(aniport[colournum-1][aniIndex],(int)posx,(int)posy,(int)width,(int)height,null);
	}
	
    private void portalsloadAnimations() {
        InputStream is = getClass().getResourceAsStream("/portal-sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);
            aniport = new BufferedImage[5][12];

            for (int j = 0; j < aniport.length; j++)
                for (int i = 0; i < aniport[j].length; i++)
                    aniport[j][i] = img.getSubimage(i * 128, j * 128, 128, 128);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void update() {
        updateAnimationTick();
	}
	public Rectangle getHitbox() {
		return new Rectangle((int)posx-5,(int)posy-5,96,128);
	}
	
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpd) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 10) {
                aniIndex = 0;
            }
        }
    }
    public double getX() {
        return posx;
    }
	public double getY() {
		return posy;
	}
}

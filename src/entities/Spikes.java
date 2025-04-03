package entities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import main.Game;

public class Spikes extends entity{
	private Image anispike;
	private double OriginX, OriginY;
	private int rotation;
	private BufferedImage[][] animations;
	private int aniTick, aniSpd = 35, aniIndex;
	private boolean rotated = false;
	private Game game;
	private boolean L=false ,R=false,U=false,D=false;
	private int tilex;
	private int tiley;
	public Spikes(int x,int y,int w,int h, int r, Game game) {
		super(x,y,w,h);
		this.game = game;
		OriginX = x;
		OriginY=y;
		rotation = 0;
		spikesloadAnimations();

	}
	private void Rotate() {
		for (floorTiles tile : game.floorTilesList) {
		    if (tile.getX() >= posx - 64 && tile.getX() <= posx + 64 &&
		        tile.getY() >= posy - 64 && tile.getY() <= posy + 64) {

		        // Check left, right, up, down
		        if (tile.getX() == posx - 32 && tile.getY() == posy) L = true;
		        if (tile.getX() == posx + 32 && tile.getY() == posy) R = true;
		        if (tile.getX() == posx && tile.getY() == posy - 32) U = true;
		        if (tile.getX() == posx && tile.getY() == posy + 32) D = true;
		    }
		}
		if(U&&!D&&!L&&!R) {
			rotation = 2;
		}else if(D&&!U&&!L&&!R) {
			rotation = 0;
		}else if(L&&!U&&!R&&!D) {
			rotation = 1;
		}else if(R&&!U&&!L&&!D) {
			rotation = 3;
		}else if(L&&U&&D&&!R) {
			rotation = 1;
		}else if(R&&U&&D&&!L) {
			rotation = 3;
		}else if(L&&R&&U&&!D) {
			rotation = 2;
		}else if(L&&R&&D&&!U) {
			rotation = 0;
		}else if(L&&U&&!D&&!R) {
			rotation = 2;
		}else if(R&&U&&!D&&!L) {
			rotation = 2;
		}else if(U&&D&&!R&&!L) {
			rotation = 0;
		}
		
		
		
	}
	public void render(Graphics g) {
		if(!rotated) {
			Rotate();
			rotated = true;
		}
		g.drawImage(animations[rotation][aniIndex],(int)posx,(int)posy,(int)width,(int)height,null);
	}
	
	private void spikesloadAnimations() {
        InputStream is = getClass().getResourceAsStream("/spike.png");

        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[4][5];

            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i * 256, j * 256, 256, 256);
    		Rotate();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpd) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 5) {
                aniIndex = 0;
            }
        }
    }
	public void update() {
		updateAnimationTick();
	}
	public Rectangle getHitbox() {
		return new Rectangle((int)posx+3,(int)posy-10,32,20);
	}
	public void resetScroll() {
		posx = OriginX;
	}


    public double getX() {
        return posx;
    }
	public double getY() {
		return posy;
	}
	public void scroll(float speed) {
		if((posx==OriginX)&&speed<0) {	
			posx=posx;
		}else {
			posx-=speed;
		}
	}
}







/*package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave { 

	
	public static BufferedImage GetPlayerAtlas() {
		InputStream is = LoadSave.class.getResourceAsStream("/player-sprites.png");
		
		try {
			BufferedImage img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
} */
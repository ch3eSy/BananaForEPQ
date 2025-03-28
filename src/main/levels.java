package main;

import java.util.ArrayList;
import java.util.List;
import entities.Portals;
import entities.Spikes;
import entities.floorTiles;
import static utils.Constants.spikeConstants.down;
import static utils.Constants.spikeConstants.up;
import static utils.Constants.spikeConstants.left;
import static utils.Constants.spikeConstants.right;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class levels {
	private static List<Spikes> spikeList;
	private static List<Portals> portalList;
    private static List<floorTiles> floorTilesList;
    private Game game;
    private static int i;
    
    public void TileSet(int num) {
		TileMap(num);
    }
    
	public static List level1(int type) {

        if(type==1) {
        	return floorTilesList;
        }else if(type==2){
        	return spikeList;
        }else {
        	return null;
        }
		
	}
	public static List level2(int type) {
        for(i=0;i<5;i++) {
        	floorTilesList.add(new floorTiles(448+(i*32),1016,32,32));
        }
    	for(i = 0; i<1920;i+=32) {
    		spikeList.add(new Spikes(i, 1048, 32, 32,0));
    	}

        
        if(type==1) {
        	return floorTilesList;
        }else if(type==2){
        	return spikeList;
        }else {
        	return null;
        }
		
	}
	
	
	public void TileMap(int num) {
		spikeList = new ArrayList<>();
		floorTilesList = new ArrayList<>();
		String address = "/map"+num+".png";
		System.out.println(address);
		InputStream is = getClass().getResourceAsStream(address);
        try {
            BufferedImage image = ImageIO.read(is);

            // Define color-to-tile mapping
            Map<Integer, String> tileMap = new HashMap<>();
            tileMap.put(new Color(0, 255, 0).getRGB(), "FloorTile");  // Green
            tileMap.put(new Color(255, 0, 0).getRGB(), "SpikeTile");  // Red

            // Tile size
            int tileSize = 32;
            int width = image.getWidth();
            int height = image.getHeight();

            // Loop through pixels
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    Color color = new Color(pixel, true); // Extracts colour correctly
                    
                    // Match the closest tile type
                    String tile = tileMap.getOrDefault(color.getRGB(), "Empty");

                    // Convert pixel coordinates to game world coordinates
                    int worldX = x * tileSize;
                    int worldY = y * tileSize;
                    
                    if(tile=="FloorTile") {
                    	floorTilesList.add(new floorTiles(worldX, worldY, 32, 32));
                        System.out.println("Spawning " + tile + " at (" + worldX + "," + worldY + ")");
                    }else if (tile=="SpikeTile") {
                    	spikeList.add(new Spikes(worldX,worldY,32,32,up));
                        System.out.println("Spawning " + tile + " at (" + worldX + "," + worldY + ")");
                    }
                    

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}

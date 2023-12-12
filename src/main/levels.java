package main;

import java.util.ArrayList;
import java.util.List;

import entities.Portals;
import entities.Spikes;
import entities.floorTiles;

public class levels {
	private static List<Spikes> spikeList;
	private static List<Portals> portalList;
    private static List<floorTiles> floorTilesList;
    private Game game;
    private static int i;
	public static List level1(int type) {
        floorTilesList = new ArrayList<>();
        spikeList = new ArrayList<>();
        for(i=0;i<5;i++) {
        	spikeList.add(new Spikes(448+(i*32),1016,32,32));
        }
    	for(i = 0; i<1920;i+=32) {
    		floorTilesList.add(new floorTiles(i, 1048, 32, 32));
    	}
    	for(i = 0; i<4;i++) {
		floorTilesList.add(new floorTiles(850+(32*i), 920, 32, 32));
    	}
    	for(i=0;i<3;i++) {
			floorTilesList.add(new floorTiles(1100+(32*i), 850, 32, 32));
			floorTilesList.add(new floorTiles(470+(32*i), 950, 32, 32));
			floorTilesList.add(new floorTiles(1515+(32*i), 256, 32, 32));
			floorTilesList.add(new floorTiles(590+(32*i),700,32,32));
			floorTilesList.add(new floorTiles(830+(32*i),550,32,32));
			floorTilesList.add(new floorTiles(1200+(32*i),400,32,32));
		}
        
        if(type==1) {
        	return floorTilesList;
        }else if(type==2){
        	return spikeList;
        }else {
        	return null;
        }
		
	}
	public void level2() {
		
	}
	public void level3() {
		
	}
	public void level4() {
		
	}
	public void level5() {
		
	}
	public void level6() {
		
	}
	public void level7() {
		
	}
	public void level8() {
		
	}
	public void level9() {
		
	}
	public void level10() {
		
	}
	public void level11() {
		
	}
	public void level12() {
		
	}
	public void level13() {
		
	}
	public void level14() {
		
	}
	public void level15() {
		
	}
	public void level16() {
		
	}
	public void level17() {
		
	}
	public void level18() {
		
	}
	public void level19() {
		
	}
	public void level20() {
		
	}
	
}

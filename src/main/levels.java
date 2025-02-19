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
        	spikeList.add(new Spikes(700+(i*32),1016,32,32,0));
        }
    	for(i = 0; i<7680;i+=32) {
    		floorTilesList.add(new floorTiles(i, 1048, 32, 32));
    	}

        
        if(type==1) {
        	return floorTilesList;
        }else if(type==2){
        	return spikeList;
        }else {
        	return null;
        }
		
	}
	public static List level2(int type) {
        floorTilesList = new ArrayList<>();
        spikeList = new ArrayList<>();
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
	
}

package Tile;

import java.awt.Rectangle;

import Assets.House;
import GameState.GameStateManager;
import mytool.Colors;
import mytool.Mapping;
import mytool.SpriteSheet256;

public enum Tile{

	VOID(0,0xFF000000,0,Colors.getEColorSeries(000,-1,-1,-1),Colors.getEColorSeries(-1,-1,-1,-1),true),
	GRASS(1,0xFF00FF00,2,Colors.getEColorSeries(131,-1,-1,-1),Colors.getEColorSeries(-1,-1,141,252),false),
	STONE(2,0xFF555555,1,Colors.getEColorSeries(111,-1,-1,-1),Colors.getEColorSeries(222,333,444,555),true),
	WATER(3,0xFF0000FF,new int[][]{{0,5},{1,5},{2,5},{1,5}},Colors.getEColorSeries(004, 004, 004,004),
			Colors.getEColorSeries(115,115,115,115),1000,false),
	CLIFF(4,0xFFFFFF00,3,Colors.getEColorSeries(111,-1,-1,-1),Colors.getEColorSeries(321,431,543,555),true),
	STAIRL(5,0xFF550000,4,Colors.getEColorSeries(-1,-1,333,-1),Colors.getEColorSeries(-1,555,444,-1),false),
	STAIRR(6,0xFF005500,5,Colors.getEColorSeries(-1,-1,333,-1),Colors.getEColorSeries(-1,555,444,-1),false),
	BASE(7,0xFF55FF55,2,Colors.getEColorSeries(131,-1,-1,-1),Colors.getEColorSeries(-1,-1,141,252),true),
	TUBE(8,0xFF22FF22,2,Colors.getEColorSeries(-1,-1,-1,-1),Colors.getEColorSeries(-1,-1,-1,-1),false),
	ROAD(9,0xFF222222,1+32,Colors.getEColorSeries(-1,-1,-1,-1),Colors.getEColorSeries(-1,-1,444,555),false);
	
	private SpriteSheet256 sheet=new SpriteSheet256("/sprite_sheet.png");
	private int id;
	private int colorseries;
	private int colorseries2;
	private int detectedId;
	private int tilePos;
	private boolean isSolid;
	private int[][] animateCoords;
	private int delay;
	private int index;
	private long last;
	
	private Tile(int id,int detectionId,int[][] animateCoords,int colorseries,int colorseries2,int delay,boolean isSolid) {
		this.colorseries=colorseries;
		this.colorseries2=colorseries2;
		this.id=id;
		this.detectedId=detectionId;
		this.tilePos=animateCoords[0][0]+32*animateCoords[0][1];
		this.animateCoords=animateCoords;
		this.delay=delay;
		last=System.currentTimeMillis();
		index=0;
		this.isSolid=isSolid;
	}
	
	private Tile(int id,int detectionId,int tilePos,int colorseries,int colorseries2,boolean isSolid) {
		this.id=id;
		this.detectedId=detectionId;
		this.tilePos=tilePos;
		this.colorseries=colorseries;
		this.colorseries2=colorseries2;
		this.isSolid=isSolid;
	}
	
	
	
	
	public void tick(){
		
		if(this.id==3){
		if(System.currentTimeMillis()-last>=delay){
			last=System.currentTimeMillis();
			tilePos=animateCoords[(index++)%animateCoords.length][0]+32*animateCoords[(index)%animateCoords.length][1];
		}
		}
		
	}
	
	public void render(Mapping mapp,int x,int y){
		mapp.doMapping(x, y,tilePos, colorseries,colorseries2,false,false);
	}
	
	public int getTileId(){
		return id;
	}
	public int getdetectedId(){
		return detectedId;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public Tile setSolid(boolean isSolid) {
		this.isSolid = isSolid;
		return this;
	}
	
	

}

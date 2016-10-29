/*
package Bak;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

import Assets.House;
import Assets.Proffesor;
import Assets.Tree;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.HUD;
import Main.GameFrame;
import Tool.MapLoader;
import mytool.Colors;
import mytool.Mapping;
import mytool.SpriteSheet256;
import Tile.*;
public class MazeRunner extends GameState{

	
	
	int[] pixels;
	int[] colors;
	Mapping mapp;
	public int width;
	public int height;
	SpriteSheet256 sheet;
	int xOffset,yOffset;
	public MapLoader ml;
	String test1="/face.png";
	String maze="/123.png";
	String test2="/test.png";
	HUD hud;
	private Proffesor prof;
	private House house;
	private Tree[][] trees=new Tree[10][10];
	
	public MazeRunner(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		width=this.gsm.getCanvas().getUWidth();
		height=this.gsm.getCanvas().getUHeight();
		sheet=new SpriteSheet256("/sprite_sheet.png");
		sheet.appendSheetToBottom("/sprite_house.png");
		mapp=new Mapping(width,height,sheet);
		this.pixels=this.gsm.getCanvas().getPixels();
		this.colors=this.gsm.getCanvas().getColors();
		hud=new HUD(this);
		
		ml=new MapLoader(mapp,test2);
		prof=new Proffesor(ml, GameFrame.input, mapp,0,0);
		house=new House(ml,null, mapp, 10,5,5,"");
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
		trees[i][j]=new Tree(ml, null, mapp,5+(3*i)<<3,5+(3*j)<<3,0,"");
		trees[i][j].creatToMap();
		}}
		prof.creatToMap();
		house.creatToMap();
		
	}

	@Override
	public void tick() {
		//Random Pixel:
//		for(int x=0;x<width;x++){
//			for(int y=0;y<height;y++){
//				pixels[x+y*gsm.getCanvas().getUWidth()]=colors[new Random().nextInt(216)];
//			}
//		}
		ml.tick();
		hud.tick();
		if(GameFrame.input.show_hud.isPressed){
			hud.setAppear();
			GameFrame.input.show_hud.isPressed=false;
		}
		
	}

	@Override
	public void render() {
	
		int xOffset=prof.x-mapp.width/2+8;
    	int yOffset=prof.y-mapp.height/2;
		ml.renderTile(xOffset, yOffset);
		
		ml.renderEntity();
		
		
		for(int y=0;y<mapp.height;y++){
    		for(int x=0;x<mapp.width;x++){
    			int index=mapp.pixels[x+y*mapp.width];
    			if(index<255){
        		pixels[x+y*width]=colors[index];
        		}
        		}
        }
		
		hud.render();
		
		
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public MapLoader getMl() {
		return ml;
	}

	public Proffesor getProf() {
		return prof;
	}

}
*/
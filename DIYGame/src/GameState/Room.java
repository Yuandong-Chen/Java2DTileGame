package GameState;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import Main.GameFrame;
import Tool.MapLoader;
import mytool.*;
import Assets.*;
import Inventory.Item;

public class Room {
	
	int[] pixels;
	int[] colors;
	SpriteSheet256 sheet;
	private int width,height;
	Mapping mapp;
	public MapLoader ml;
	String mapName;
	private Proffesor prof;
	private int profX,profY;
	private House house;
	private Tree[][] trees=new Tree[10][10];
	HashMap<String,Entity> entities=new HashMap<String,Entity>();
	private String Name;
	public Room(int[] pixels,int[] colors,int width,int height,String mapName,int profX,int profY) {
		this.pixels=pixels;this.colors=colors;
		this.width=width;this.height=height;
		this.mapName=mapName;
		this.profX=profX;this.profY=profY;
		init();
	}
	
	public void init() {
		sheet=new SpriteSheet256("/sprite_sheet.png");
		sheet.appendSheetToBottom("/sprite_house.png");
		mapp=new Mapping(width,height,sheet);
		
		ml=new MapLoader(mapp,mapName);
		prof=new Proffesor(ml, GameFrame.input, mapp,profX,profY);
		prof.creatToMap();
	}
	
	public void tick() {
		
		if(GameFrame.input.pressed){
			Check:for(Entity e: entities.values()){
			if(e instanceof RealItem){
				if((getProf().rc.intersects(e.rc))){
				if(((RealItem)e).rc.contains(new Point(GameFrame.input.getWorldxPos()<<3,GameFrame.input.getWorldyPos()<<3))){
				
				//((RealItem)e).setUser(getProf());
				//getProf().addItemToInven((RealItem)(e));
				if(e.isLive){
				MazeRunner.hud.getInv().addItem(Item.RED_BALL);}
				entities.get(e.getName()).isLive=false;
				break Check;
				}}}
			}
		}
		
//		if(GameFrame.input.shoot.isPressed){
//			Iterator it = getProf().getInven().entrySet().iterator();
//			   while (it.hasNext())
//			   {
//			      Entry item = (Entry) it.next();
//			      RealItem e=(RealItem) item.getValue();
//			      e.isLive=true;e.setUser(null);
//			      it.remove();
//			   }
//		}
		
		//
		ml.tick();	
	}

	public void renderG(Graphics g) {
		
	}
	
	public void render() {
		
		int xOffset=(prof.x)-mapp.width/2+8;
    	int yOffset=(prof.y)-mapp.height/2;
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
	}
	
	public Proffesor getProf() {
		return prof;
	}
	
	public void setProf(Proffesor prof) {
		this.prof = prof;
	}
	
	public void addEntity(Entity e){
		if(!entities.containsKey(e.getName())){
		entities.put(e.getName(),e);
		e.creatToMap();}
	}
	
	public MapLoader getMl() {
		return ml;
	}
	
	public Entity getEntity(String Name) {
		if(entities.containsKey(Name)){
		return entities.get(Name);
		}else{
			return null;
		}
	}

	
}

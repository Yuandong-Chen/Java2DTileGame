package Tool;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;

import Assets.Entity;
import Main.GameFrame;
import Tile.Tile;
import mytool.Mapping;
import mytool.SpriteSheet256;

public class MapLoader {

	private BufferedImage image;
	private static int width=64;
	private static int height=64;
	public static int[] tiles=new int[width*height];
	Mapping mapp;
	private String filePath;
	private CopyOnWriteArrayList<Entity> entities=new CopyOnWriteArrayList<Entity>();
	
	public MapLoader(Mapping mapp,String filePath){
		this.mapp=mapp;
		if(filePath!=null){
			this.filePath=filePath;
			this.loadLevelFromFile();
		}else{
			this.generateDefaultLevel();
		}
	}
	
	private void loadLevelFromFile() {
		try {
			this.image=ImageIO.read(MapLoader.class.getResource(filePath));
			this.width=image.getWidth();
			this.height=image.getHeight();
			tiles=new int[width*height];
			this.loadTiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadTiles() {
		int[] tileColors=image.getRGB(0, 0, width, height, null, 0,width);
		for(int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				tileCheck:for(Tile t:Tile.values()){
					if(t!=null&&t.getdetectedId()==tileColors[x+y*width]){
						this.tiles[x+y*width]=t.getTileId();
						break tileCheck;
					}
				}
			}
		}
	}
	
	public void tick(){
		this.bubbleSort(entities);
		for(Entity e:entities){
			if(e.isLive){
			e.tick();}
		}
		
		for(Tile t: Tile.values()){
			if(t==null) continue;
			t.tick();
		}
	}
	
	public void renderEntity(){
		
		for(Entity e:entities){
			if(e.isLive){
			e.render(mapp);}
		}
	}
	
	public void renderTile(int xOffset,int yOffset){
		if(xOffset<0){
			xOffset=0;
		}
		if(xOffset>((width<<3)-mapp.width)){
			xOffset=(width<<3)-mapp.width;
		}
		if(yOffset<0){
			yOffset=0;
		}
		if(yOffset>((height<<3)-mapp.height)){
			yOffset=(height<<3)-mapp.height;
		}
		mapp.setOffset(xOffset,yOffset);
		for(int y=yOffset>>3;y<(yOffset>>3)+16;y++){
			for(int x=xOffset>>3;x<(xOffset>>3)+21;x++){
				getTile(x,y).render(mapp,x<<3,y<<3);
			}
		}
		
		GameFrame.input.setxPos(xOffset>>3);
		GameFrame.input.setyPos(yOffset>>3);
	}
	
	public void generateDefaultLevel(){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(i*j%10<7){
				tiles[i+width*j]=Tile.GRASS.getTileId();}
				else if(i*j%10>=7){
					tiles[i+width*j]=Tile.WATER.getTileId();	
				}
				//mapp.doMapping(i<<3, j<<3,2,Colors.getFourColorSeries(-1, 141,131,-1),false,false);
			}
		}
	}
	

	public Tile getTile(int x,int y){
		if(x<0||x>=width||y<0||y>=height){
			return Tile.VOID;
		}else{
		return detectTile(tiles[x+y*width]);}
	}
	
	public void setTile(int x,int y,Tile tile){
		if(x<0||x>=width||y<0||y>=height){
			tiles[x+y*width]=Tile.VOID.getTileId();
		}else{
		tiles[x+y*width]=tile.getTileId();}
	}
	
	public Tile detectTile(int detectedId){
		switch(detectedId){
		case 1: return Tile.GRASS;
		case 2: return Tile.STONE;
		case 3: return Tile.WATER;
		case 4: return Tile.CLIFF;
		case 5: return Tile.STAIRL;
		case 6: return Tile.STAIRR;
		case 7: return Tile.BASE;
		case 8: return Tile.TUBE;
		case 9: return Tile.ROAD;
		default: return Tile.VOID;
		}
	}

	public void addRectangle(Entity rc) {
		this.entities.add(rc);
	}
	
	public void removeRectangle(Entity rc){
		if(entities.contains(rc)){
			this.entities.remove(rc);
		}
	}
	
	public void bubbleSort(List<Entity> data) {  
		int nuM=data.size();
        for (int i = 0; i < nuM - 1; i++) {// 控制趟数  
            for (int j = 0; j < nuM - i - 1; j++) {  
  
                if (data.get(j).getLocY() > data.get(j+1).getLocY()) { 
                    Entity tmp = (Entity)data.get(j);  
                    Entity tmp2 = (Entity)data.get(j+1);
                    if((!tmp2.isFlying)&&(!tmp.isFlying)){
                    data.set(j, tmp2);
                    data.set(j+1, tmp);
                    }else if((tmp2.isFlying)&&(tmp.isFlying)){
                    	data.set(j, tmp2);
                        data.set(j+1, tmp);
                    }else if((!tmp2.isFlying)&&(tmp.isFlying)){
                    	data.set(j, tmp2);
                        data.set(j+1, tmp);
                    }else{
                    	
                    }
                    
                }  
            }  
        } 
	}
	
	public CopyOnWriteArrayList<Entity> getEntities() {
		return entities;
	}
}

package Assets;

import java.awt.Rectangle;

import Tile.Tile;
import Tool.MapLoader;
import mytool.InputHandler;
import mytool.Mapping;

public abstract class Mob extends Entity{

	public Mob(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed, String name) {
		super(ml, input, mapp, x, y, speed, name);
		rc=new Rectangle();
	}
	
    public abstract boolean hasCollided(int xa,int ya);
	
	public boolean isSolidTile(int xa,int ya,int x,int y){
		
		if(ml==null) {return false;}
		Tile lastTile=ml.getTile(((this.x+x)>>3),((this.y+y)>>3));
		Tile newTile=ml.getTile(((this.x+x+xa)>>3),((this.y+y+ya)>>3));
		if(!lastTile.equals(newTile)&&newTile.isSolid()){
			return true;
		}
		return false;
	}
	public void creatToMap(){
		this.ml.addRectangle(this);
	}
	
	public void removeFromMap(){
		this.ml.removeRectangle(this);
	}
	
	public String getName() {
		return name;
	}
	
}

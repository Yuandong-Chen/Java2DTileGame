package Assets;

import java.awt.Rectangle;

import Tool.MapLoader;
import mytool.InputHandler;
import mytool.Mapping;

public abstract class Entity extends Rectangle{

	public boolean isLive=true;
	public int x,y;
	protected int speed;
	protected InputHandler input;
	protected Mapping mapp;
	public boolean isMove,isSwimming,isFlying;
	protected MapLoader ml;
	protected String name;
	protected int locY;
	public Rectangle rc;
	public boolean isHurting;
	public int hurtTime;
	protected int bloodbar=87;
	
	public Entity(MapLoader ml,InputHandler input,Mapping mapp,int x,int y,int speed,String name) {
		this.ml=ml;
		this.input=input;
		this.mapp=mapp;
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.name=name;
		rc=new Rectangle();
	}
	
	public abstract void render(Mapping mapp);

	public abstract void tick();
	
	public int getLocY() {
		return locY;
	}
	
	public void setLocY(int locY) {
		this.locY = locY;
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
	
	public void setHurting(boolean isHurting) {
		this.isHurting = isHurting;
	}
	
	public int getBloodbar() {
		return bloodbar;
	}
	

}

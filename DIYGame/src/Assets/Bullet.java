package Assets;

import java.awt.Point;
import java.util.Iterator;

import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class Bullet extends Mob{

	private AK ak;
	private int direction;
	private int colorseries=Colors.getEColorSeries(-1,-1,-1,-1),
			colorseries2=Colors.getEColorSeries(-1,-1,-1,555);
	
	public Bullet(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed,String name,AK ak) {
		super(ml,null, mapp, x, y,2,"Bullet");
		this.ak=ak;
		this.direction=ak.getDirection();
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin=0;
		int xMax=1;
		int yMin=0;
		int yMax=1;
		
		for(int x=xMin;x<xMax;x++){
			if(isSolidTile(xa,ya,x,yMin)){
				return true;
			}
		}
		for(int x=xMin;x<xMax;x++){
			if(isSolidTile(xa,ya,x,yMax)){
				return true;
			}
		}
		for(int y=yMin;y<yMax;y++){
			if(isSolidTile(xa,ya,xMin,y)){
				return true;
			}
		}
		for(int y=yMin;y<yMax;y++){
			if(isSolidTile(xa,ya,xMax,y)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void render(Mapping mapp) {
		mapp.doMapping(x,y,32, colorseries, colorseries2,false,false);
	}

	@Override
	public void tick(){
		if(isLive){
			switch(direction){
			case 0: walk(0,-1);break;
			case 1: walk(1,-1);break;
			case 2: walk(1,0);break;
			case 3: walk(1,1);break;
			case 4: walk(0,1);break;
			case 5: walk(-1,1);break;
			case 6: walk(-1,0);break;
			case 7: walk(-1,-1);break;
			}
		}
		
		Iterator<Entity> it=ml.getEntities().iterator();
		Check:while(it.hasNext()){
			Entity t=it.next();
			if(t.rc.contains(new Point(x, y))){
				t.setHurting(true);
				if(t.isLive){
				this.isLive=false;}
				break Check;
			}
		}
	}
	
	public void walk(int xa,int ya){
		if(xa!=0&&ya!=0){
			walk(xa,0);
			walk(0,ya);
			
			return;
		}
		
		if(!hasCollided(xa,ya)){
			x+=xa*speed;
			y+=ya*speed;
		}else{
			isLive=false;
		}
	}	
	
}




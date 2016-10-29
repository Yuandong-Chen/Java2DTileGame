package Assets;

import GameState.Room;
import Tile.Tile;
import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class House extends Entity{

	private boolean toggle=true;
	private boolean isOpen=false;
	private int colorseries=Colors.getEColorSeries(-1, 000, 100, 200);
	private int colorseries2=Colors.getEColorSeries(322,-1,-1,444);
	private int tickCount;
	private Room room;
	
	public House(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed, String name) {
		super(ml,null, mapp, x, y, 0,name);
	}

	@Override
	public void render(Mapping mapp) {
		int tickCount=(this.tickCount>>3)%3;
		if(toggle){
		
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				mapp.doMapping(x+(i<<3), y+(j<<3),(tickCount*7)+i+(j+32)*32, colorseries, colorseries2,false,false);
				
			}
		}
		
		
		}
		
	}

	public void tick() {
		// TODO Auto-generated method stub
		rc.setBounds(x+16, y+30,8,16);
		setLocY(y+48);
		if(((tickCount>>3)%3)!=2){
			tickCount++;
		}
		
		tickCount=tickCount%1000;
	}

	public void creatToMap(){
		this.ml.addRectangle(this);
		int rx=x;
		int ry=y+(2<<3);
		for(int i=1;i<7;i++){
		for(int j=2;j<4;j++){
		this.ml.setTile((rx>>3)+i, (ry>>3)+j,Tile.BASE);
		}
		}
		
		this.ml.setTile((rx>>3)+3,(ry>>3)+(3),Tile.TUBE);
	}
	
	
	public void setToggle() {
		toggle=!toggle;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	
}

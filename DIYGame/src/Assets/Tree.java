package Assets;

import Tile.Tile;
import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class Tree extends Entity{

	
	private int colorseries=Colors.getEColorSeries(-1,321, 210, 111);
	private int colorseries2=Colors.getEColorSeries(020,131,241,-1);
	
	
	public Tree(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed, String name) {
		super(ml,null, mapp, x, y, 0,name);
	}

	public void tick() {
		setLocY(y+48);
		
	}

	@Override
	public void render(Mapping mapp) {
		// TODO Auto-generated method stub
		for(int i=0;i<4;i++){
			for(int j=0;j<6;j++){
				mapp.doMapping(x+(i<<3), y+(j<<3),i+(j+32+7)*32, colorseries, colorseries2,false,false);
				
			}
		}
	}
	
	public void creatToMap(){
		this.ml.addRectangle(this);
		int rx=x;
		int ry=y+(2<<3);
		for(int i=0;i<4;i++){
		for(int j=3;j<4;j++){
		
		this.ml.setTile((rx>>3)+i, (ry>>3)+j,Tile.BASE);
		}
		}
	}
	
	
}

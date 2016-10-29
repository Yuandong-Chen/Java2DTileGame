package Assets;

import java.util.Random;

import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class Taint extends Mob{

	private int intensity1=new Random().nextInt(6);
	private int intensity2=new Random().nextInt(1);
	private int intensity3=new Random().nextInt(1);
	private int bcolor=intensity1*100+intensity2*10+intensity3;
	private int colorseries=Colors.getEColorSeries(-1,-1,-1,-1),
			colorseries2=Colors.getEColorSeries(-1,-1,-1,bcolor);
	private boolean jump,right,left;
	private float lifeTime=5;
	private float maxUp=2f;
	private float currUp;
	
	
	public Taint(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed, String name) {
		super(ml,null, mapp, x, y,0,"BloodTaint");
		// TODO Auto-generated constructor stub
		jump=true;
		int rans =new Random().nextInt(2);
		switch(rans){
		case 0:
			right=true;
			left=false;
			break;
		case 1:
			right=false;
			left=true;
			break;
		}
	}

	@Override
	public void render(Mapping mapp) {
		if(isLive){
		mapp.doMapping(x,y,32, colorseries, colorseries2,false,false);
		}
	}

	@Override
	public void tick() {
		setLocY(y);
		if(isLive){
			if(lifeTime!=0){
				lifeTime-=0.05f;
			}
			if(lifeTime<=0){
				isLive=false;
			}
			if(jump){
				if(currUp!=maxUp){
					currUp+=0.2f;
					y-=currUp;
				}
				if(currUp>=maxUp){
					jump=false;
				}
				
				if(right){
					x+=currUp*new Random().nextFloat()*maxUp;
				}
				if(left){
					x-=currUp*new Random().nextFloat()*maxUp;
				}
			}
			
			if(!jump){
				if(currUp!=0){
					currUp-=0.05;
					y+=currUp;
				}
				if(currUp<=0){
					maxUp-=0.5;
					jump=true;
				}
			}
			
		}
		
	}

	@Override
	public boolean hasCollided(int xa, int ya) {
		// TODO Auto-generated method stub
		return false;
	}
}

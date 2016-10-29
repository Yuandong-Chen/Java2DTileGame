package Assets;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Inventory.Inventory;
import Main.GameFrame;
import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class AK extends RealItem{
	protected int numSteps=0;
	private int direction=3;
	private int colorseries=Colors.getEColorSeries(-1,-1,110,222),
			colorseries2=Colors.getEColorSeries(222, 321, 310, -1);
	private boolean isJumping;
	private Proffesor user;
	private int preparedTime=3;
	private boolean picked;
	private int lastX,lastY;
	public List<Bullet> bullets=new ArrayList<Bullet>();
	public int originX=new Random().nextInt(50*8);
	public int originY=new Random().nextInt(50*8);
	
	public AK(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed, String name) {
		super(ml,null, mapp, x, y,1, name);
		
	}
	
	@Override
	public void render(Mapping mapp) {
		int xOffset=0,yOffset=0;
		if(!isJumping){
		xOffset=this.x;yOffset=this.y;
		}else{
		xOffset=this.x;yOffset=this.y-4;
		}
		
		switch(direction){
		case 0:
			mapp.doMapping(xOffset, yOffset,2+17*32, colorseries, colorseries2,false,false);
			mapp.doMapping(xOffset, yOffset+8,2+18*32, colorseries, colorseries2,false,false);
			break;
		case 1:
			mapp.doMapping(xOffset, yOffset,4+17*32, colorseries, colorseries2,false,false);
			mapp.doMapping(xOffset+8, yOffset,5+17*32, colorseries, colorseries2,false,false);
			
			mapp.doMapping(xOffset, yOffset+8,4+18*32, colorseries, colorseries2,false,false);
			mapp.doMapping(xOffset+8, yOffset+8,5+18*32, colorseries, colorseries2,false,false);
			break;
		case 2:
			mapp.doMapping(xOffset, yOffset+8,0+17*32, colorseries, colorseries2,false,false);
			mapp.doMapping(xOffset+8, yOffset+8,1+17*32, colorseries, colorseries2,false,false);
			break;
		case 3:
			mapp.doMapping(xOffset, yOffset+8,6+17*32, colorseries, colorseries2,false,false);
			mapp.doMapping(xOffset+8, yOffset+8,7+17*32, colorseries, colorseries2,false,false);
		
			mapp.doMapping(xOffset, yOffset+8+8,6+18*32, colorseries, colorseries2,false,false);
			mapp.doMapping(xOffset+8, yOffset+8+8,7+18*32, colorseries, colorseries2,false,false);
			break;
		case 4:
			
			mapp.doMapping(xOffset, yOffset+8+8,2+17*32, colorseries, colorseries2,false,true);
			mapp.doMapping(xOffset, yOffset+8,2+18*32, colorseries, colorseries2,false,true);
			break;	
		case 5:
			mapp.doMapping(xOffset+8, yOffset+8,6+17*32, colorseries, colorseries2,true,false);
			mapp.doMapping(xOffset, yOffset+8,7+17*32, colorseries, colorseries2,true,false);

			mapp.doMapping(xOffset+8, yOffset+8+8,6+18*32, colorseries, colorseries2,true,false);
			mapp.doMapping(xOffset, yOffset+8+8,7+18*32, colorseries, colorseries2,true,false);
			break;
		case 6:
			mapp.doMapping(xOffset+8, yOffset+8,0+17*32, colorseries, colorseries2,true,false);
			mapp.doMapping(xOffset, yOffset+8,1+17*32, colorseries, colorseries2,true,false);
			break;
		case 7:
			mapp.doMapping(xOffset+8, yOffset,4+17*32, colorseries, colorseries2,true,false);
			mapp.doMapping(xOffset, yOffset,5+17*32, colorseries, colorseries2,true,false);
		
			mapp.doMapping(xOffset+8, yOffset+8,4+18*32, colorseries, colorseries2,true,false);
			mapp.doMapping(xOffset, yOffset+8,5+18*32, colorseries, colorseries2,true,false);
			break;
		}

		if(bullets.size()>0){
			for(Bullet e:bullets){
				if(e.isLive)
				e.render(mapp);
			}}
		
	}

	public void tick(){
		setPara();
		rc.setBounds(x-4,y-4,16,16);
		
		if(bullets.size()>0){
			for(Bullet e:bullets){
				if(e.isLive)
				e.tick();
			}}
		
		setLocY(y+16);
//		{
//		if(input.right.isPressed){
//			if(input.up.isPressed){
//				direction=1;
//			}else if(input.down.isPressed){
//				direction=3;
//			}else{
//				direction=2;
//			}
//		}
//		
//		if(input.left.isPressed){
//			if(input.up.isPressed){
//				direction=7;
//			}else if(input.down.isPressed){
//				direction=5;
//			}else{
//				direction=6;
//			}
//		}
//		
//		if(input.up.isPressed&&!input.right.isPressed&&!input.left.isPressed){
//			direction=0;
//		}
//		
//		if(input.down.isPressed&&!GameFrame.input.right.isPressed&&!input.left.isPressed){
//			direction=4;
//		}
//		if(input.shoot.isPressed){
//			modifiedShoot();
//			}
//		}
		
		if(user!=null&&user.input!=null){
		originX=x;originY=y+4;
		int x=input.getWorldxPos()-(this.x>>3);
		int y=(this.y>>3)-input.getWorldyPos();
		if(10*y>=-24*x&&10*y>24*x){
			direction=0;
		}
		else if(10*y<=24*x&&24*y>10*x){
			direction=1;
		}
		else if(-24*y<10*x&&24*y<=10*x){
			direction=2;
		}
		else if(-24*y>=10*x&&-10*y<24*x){
			direction=3;
		}
		else if(10*y<24*x&&-10*y>=24*x){
			direction=4;
		}
		else if(10*y>=24*x&&24*y<10*x){
			direction=5;
		}
		else if(-24*y>10*x&&24*y>=10*x){
			direction=6;
		}else{
			direction=7;
		}
		
		if(input.pressed){
			modifiedShoot();
			}
		prepareShoot();
	}
	}
	
	public void modifiedShoot(){
		if(bullets.size()>100){
			bullets.removeAll(bullets);
			}
		if(preparedTime<=0){
		switch(direction){
		case 0:bullets.add(new Bullet(this.ml,null,this.mapp,this.x+3,this.y,0,"",this));break;
		case 1:bullets.add(new Bullet(this.ml,null,this.mapp,this.x+13,this.y,0,"",this));break;
		case 2:bullets.add(new Bullet(this.ml,null,this.mapp,this.x+15,this.y+11,0,"",this));break;
		case 3:bullets.add(new Bullet(this.ml,null,this.mapp,this.x+15,this.y+20,0,"",this));break;
		case 4:bullets.add(new Bullet(this.ml,null,this.mapp,this.x+3,this.y+23,0,"",this));break;
		case 5:bullets.add(new Bullet(this.ml,null,this.mapp,this.x,this.y+20,0,"",this));break;
		case 6:bullets.add(new Bullet(this.ml,null,this.mapp,this.x,this.y+11,0,"",this));break;
		case 7:bullets.add(new Bullet(this.ml,null,this.mapp,this.x+3,this.y,0,"",this));break;
		}
		preparedTime=20;
		if(user.bulletNum>0){
		user.bulletNum--;
		Inventory.used_weapons.get(1).setCurrentStack(user.bulletNum);
		}
		}
	}
	
	public void prepareShoot(){
		if(preparedTime>0){
			preparedTime--;}
	}
	
	public void setPara(){
		if(user!=null){
		setJump(user.isJumping());
		setX(user.x);setY(user.y);}else{
			setJump(false);
			
			setX(originX);setY(originY);
		}
	}
	
	public void setJump(boolean isJumping){
		this.isJumping=isJumping;
	}

	public void setX(int x){
		
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}

	public void setPicked(boolean picked) {
		this.picked = picked;
	}
	
	
	@Override
	public boolean hasCollided(int xa, int ya) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setUser(Proffesor user) {
		this.user = user;
		if(user!=null){
		this.input=user.input;}else{
			input=null;
		}
	}
	
	public Proffesor getUser() {
		return user;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
}

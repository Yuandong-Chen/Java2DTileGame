package Assets;

import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import Inventory.*;
import Main.GameFrame;
import Tool.Font;
import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class Proffesor extends Mob{

	private String source="";
	private boolean teleport;
	private int direction;
	private Mapping mapp;
	private int state;
	protected int numSteps=0;
	int colorseries=Colors.getEColorSeries(000,100,100,500);
	int colorseries2=Colors.getEColorSeries(420,431,543,-1);
	Animator animateDown;
	Animator animateDown2;
	Animator animateDown3;
	Animator animateDown4;
	Animator animateUp;
	Animator animateUp2;
	Animator animateUp3;
	Animator animateUp4;
	Animator animateRight;
	Animator animateRight2;
	Animator animateRight3;
	Animator animateRight4;
	Animator animateLeft;
	Animator animateLeft2;
	Animator animateLeft3;
	Animator animateLeft4;
	Animator animateIdile;
	Animator animateIdile2;
	Animator animateIdile3;
	Animator animateIdile4;
	Animator animateJump;
	Animator animateJump2;
	Animator animateJump3;
	Animator animateJump4;
	private boolean isJumping;
	private int tickCount;
	
	public RealItem[] inven;
	private boolean used1=false;
	private boolean used2=false;
	public int bulletNum;
	private CopyOnWriteArrayList<Taint> taints;
	private int exp=200;
	
	
	public Proffesor(MapLoader ml,InputHandler input,Mapping mapp,int x,int y) {
		super(ml, input, mapp, x, y,1,"Proffesor");
		inven=new RealItem[3];
		taints=new CopyOnWriteArrayList<Taint>();
		//ak=new AK(ml,input, mapp, x, y, 1,"AK47",this);
		init();
	}
	
	public void init(){
		this.animateDown=new Animator(new int[][]{{0,6},{2,6},{0,6},{2,6}}, colorseries, colorseries2,250, mapp);
		this.animateDown2=new Animator(new int[][]{{1,6},{3,6},{1,6},{3,6}}, colorseries, colorseries2,250, mapp);
		this.animateDown3=new Animator(new int[][]{{0,7},{2,7},{0,7},{2,7}}, colorseries, colorseries2,250, mapp);
		this.animateDown4=new Animator(new int[][]{{1,7},{3,7},{1,7},{3,7}}, colorseries, colorseries2,250, mapp);
		this.animateUp=new Animator(new int[][]{{0,8},{2,8},{0,8},{2,8}}, colorseries, colorseries2,250, mapp);
		this.animateUp2=new Animator(new int[][]{{1,8},{3,8},{1,8},{3,8}}, colorseries, colorseries2,250, mapp);
		this.animateUp3=new Animator(new int[][]{{0,9},{2,9},{0,9},{2,9}}, colorseries, colorseries2,250, mapp);
		this.animateUp4=new Animator(new int[][]{{1,9},{3,9},{1,9},{3,9}}, colorseries, colorseries2,250, mapp);
		this.animateRight=new Animator(new int[][]{{0,10},{2,10},{4,10},{2,10}}, colorseries, colorseries2,250, mapp);
		this.animateRight2=new Animator(new int[][]{{1,10},{3,10},{5,10},{3,10}}, colorseries, colorseries2,250, mapp);
		this.animateRight3=new Animator(new int[][]{{0,11},{2,11},{4,11},{2,11}}, colorseries, colorseries2,250, mapp);
		this.animateRight4=new Animator(new int[][]{{1,11},{3,11},{5,11},{3,11}}, colorseries, colorseries2,250, mapp);
		this.animateLeft=new Animator(new int[][]{{0,10},{2,10},{4,10},{2,10}}, colorseries, colorseries2,250, mapp);
		this.animateLeft2=new Animator(new int[][]{{1,10},{3,10},{5,10},{3,10}}, colorseries, colorseries2,250, mapp);
		this.animateLeft3=new Animator(new int[][]{{0,11},{2,11},{4,11},{2,11}}, colorseries, colorseries2,250, mapp);
		this.animateLeft4=new Animator(new int[][]{{1,11},{3,11},{5,11},{3,11}}, colorseries, colorseries2,250, mapp);
		this.animateIdile=new Animator(new int[][]{{0,12},{2,12},{4,12},{6,12},{8,12},{6,12},{4,12},{2,12}}, colorseries, colorseries2,150, mapp);
		this.animateIdile2=new Animator(new int[][]{{1,12},{3,12},{5,12},{7,12},{9,12},{7,12},{5,12},{3,12}}, colorseries, colorseries2,150, mapp);
		this.animateIdile3=new Animator(new int[][]{{0,13},{2,13},{4,13},{6,13},{8,13},{6,13},{4,13},{2,13}}, colorseries, colorseries2,150, mapp);
		this.animateIdile4=new Animator(new int[][]{{1,13},{3,13},{5,13},{7,13},{9,13},{7,13},{5,13},{3,13}}, colorseries, colorseries2,150, mapp);
		this.animateJump=new Animator(new int[][]{{0,14},{2,14},{4,14},{6,14},{8,14},{10,14},{8,14},{6,14},{4,14},{2,14}}, colorseries, colorseries2,50, mapp);
		this.animateJump2=new Animator(new int[][]{{1,14},{3,14},{5,14},{7,14},{9,14},{11,14},{9,14},{7,14},{5,14},{3,14}}, colorseries, colorseries2,50, mapp);
		this.animateJump3=new Animator(new int[][]{{0,15},{2,15},{4,15},{6,15},{8,15},{10,15},{8,15},{6,15},{4,15},{2,15}}, colorseries, colorseries2,50, mapp);
		this.animateJump4=new Animator(new int[][]{{1,15},{3,15},{5,15},{7,15},{9,15},{11,15},{9,15},{7,15},{5,15},{3,15}}, colorseries, colorseries2,50, mapp);
	}
	
	
	public void tick(){
		
		Iterator<Taint> it=taints.iterator();
		Check:while(it.hasNext()){
			Taint t=it.next();
			if(!t.isLive){
				taints.remove(t);break Check;
			}
			t.tick();
		}
		
		rc.setBounds(x+4,y+4,8,10);
		setLocY(y+16);
		if(input!=null){
			int xa=0,ya=0;
			
			if(this.input.up.isPressed){
				ya--;
			}
			if(this.input.down.isPressed){
				ya++;
			}
			if(this.input.left.isPressed){
				xa--;
			}
			if(this.input.right.isPressed){
				xa++;
			}
			
			if(xa!=0||ya!=0){
				walk(xa,ya);
				isMove=true;
			}else{
				isMove=false;
			}}
			if(ml.getTile((this.x+8)>>3, (this.y+10)>>3).getTileId()==3){
	    		isSwimming=true;
	    	}
	    	if(isSwimming&&ml.getTile((this.x+8)>>3, (this.y+10)>>3).getTileId()!=3){
	    		isSwimming=false;
	    	}
	    	if(ml.getTile((this.x+1)>>3, (this.y+16)>>3).getTileId()==4&&direction==2){
	    		isJumping=true;y++;
	    	}
	    	
	    	if(ml.getTile((this.x+8)>>3, (this.y+10)>>3).getTileId()==8){
	    		teleport=true;
	    		Check:for(Entity e:ml.getEntities()){
	    			if(rc.intersects(e.rc)&&(e instanceof House)){
	    				setSource(e.getName());
	    				break Check;
	    			}
	    		}
	    	}
	    	
	    	if(isMove&&this.input.down.isPressed){
	    		animateDown.tick();
	    		animateDown2.tick();
	    		animateDown3.tick();
	    		animateDown4.tick();
	    	}
	    	
	    	if(isMove&&this.input.up.isPressed){
	    		animateUp.tick();
	    		animateUp2.tick();
	    		animateUp3.tick();
	    		animateUp4.tick();
	    	}
	    	
	    	if(isMove&&this.input.right.isPressed){
	    		animateRight.tick();
	    		animateRight2.tick();
	    		animateRight3.tick();
	    		animateRight4.tick();
	    	}
	    	if(isMove&&this.input.left.isPressed){
	    		animateLeft.tick();
	    		animateLeft2.tick();
	    		animateLeft3.tick();
	    		animateLeft4.tick();
	    	}
	    	if(!isMove&&!isJumping){
	    		animateIdile.tick();
	    		animateIdile2.tick();
	    		animateIdile3.tick();
	    		animateIdile4.tick();
	    	}
	    	
	    	if(isJumping){
	    		animateJump.tick();
	    		animateJump2.tick();
	    		animateJump3.tick();
	    		animateJump4.tick();
	    	}
	    	
	    	
	    	tickCount++;
	    	tickCount=tickCount%1000;
	    	
	    	if(inven.length>0){
	    		for(int i=0;i<3;i++){
	    			if(inven[i]!=null){
	    			inven[i].tick();}
	    		}
	    	}
	    	if(!used1){
	    	if(Inventory.used_weapons.get(0).getItem()!=null){
	    	if(Inventory.used_weapons.get(0).getItem().getItemID()==1){
	    		AK ak=new AK(ml,null, mapp,0,0,0,"AK1000");
	    		ak.setUser(this);
	    		setItemToInven(ak,0);
	    		used1=true;
	    	}}
	    	}else{
	    		if(Inventory.used_weapons.get(0).getItem()==null){
	    			setItemToInven(null,0);
		    		used1=false;
	    		}
	    	}
	    	
		    	if(Inventory.used_weapons.get(1).getItem()!=null){
		    	if(Inventory.used_weapons.get(1).getItem().getItemID()==2){
		    		bulletNum=Inventory.used_weapons.get(1).getCurrentStack();
		    		
		    	}}
		    	else{
		    		bulletNum=0;
		    	}
		    	
		    	if(isHurting&&hurtTime>0){
					taints.add(new Taint(ml,null, mapp, x+7, y,0,"BloodTaint"));
					taints.add(new Taint(ml,null, mapp, x+8, y,0,"BloodTaint"));
					taints.add(new Taint(ml,null, mapp, x+9, y,0,"BloodTaint"));
					hurtTime--;
					bloodbar--;
				}
				if(hurtTime==0){
					setHurting(false);
					hurtTime=10;
				}
				
				if(bloodbar<=0){
					for(int j=0;j<16;j++){
					taints.add(new Taint(ml,null, mapp, x+7, y+j,0,"BloodTaint"));
					taints.add(new Taint(ml,null, mapp, x+8, y+j,0,"BloodTaint"));
					taints.add(new Taint(ml,null, mapp, x+9, y+j,0,"BloodTaint"));}
					exp--;
					//
				}
				if(exp<=0){
					isLive=false;
				}
	}
	
	public void render(Mapping mapp){
		
		Iterator<Taint> it=taints.iterator();
		Check:while(it.hasNext()){
			Taint t=it.next();
			t.render(mapp);
		}
		
		int xOffset=x;
		int yOffset=y;
		if(isSwimming){
			int waterColor=0;
			yOffset+=4;
			if(tickCount%60<15){
				waterColor=Colors.getEColorSeries(-1, -1, 225, -1);
			}else if(tickCount%60>=15&&tickCount%60<30){
				yOffset-=1;
				waterColor=Colors.getEColorSeries(-1, 225, 115, -1);
			}else if(tickCount%60>=30&&tickCount%60<45){
				waterColor=Colors.getEColorSeries(-1, 115, -1, 225);
			}else{
				yOffset-=1;
				waterColor=Colors.getEColorSeries(-1, 225, 115, -1);
			}
			mapp.doMapping(xOffset, yOffset+3,0+27*32,waterColor,waterColor,false, false);
			mapp.doMapping(xOffset+8, yOffset+3,0+27*32,waterColor,waterColor,true, false);
		}
		
		if(direction==2&&isMove){
			if((animateDown.getIndex()%4)==2){
				animateDown.render(mapp,xOffset+8+1,yOffset,true,false);
				animateDown2.render(mapp,xOffset+1,yOffset,true,false);
				if(!isSwimming){
				animateDown3.render(mapp,xOffset+8+1,yOffset+8,true,false);
				animateDown4.render(mapp,xOffset+1,yOffset+8,true,false);}
			}else{
				animateDown.render(mapp,xOffset,yOffset);
				animateDown2.render(mapp,xOffset+8,yOffset);
				if(!isSwimming){
				animateDown3.render(mapp,xOffset,yOffset+8);
				animateDown4.render(mapp,xOffset+8,yOffset+8);}}
			}
		
		if(direction==0&&isMove){
			if((animateUp.getIndex()%4)==2){
				animateUp.render(mapp,xOffset+8+1,yOffset,true,false);
				animateUp2.render(mapp,xOffset+1,yOffset,true,false);
				if(!isSwimming){
				animateUp3.render(mapp,xOffset+8+1,yOffset+8,true,false);
				animateUp4.render(mapp,xOffset+1,yOffset+8,true,false);}
			}else{
				animateUp.render(mapp,xOffset,yOffset);
				animateUp2.render(mapp,xOffset+8,yOffset);
				if(!isSwimming){
				animateUp3.render(mapp,xOffset,yOffset+8);
				animateUp4.render(mapp,xOffset+8,yOffset+8);}}
			}
		if(direction==1&&isMove){
			
				animateRight.render(mapp,xOffset,yOffset);
				animateRight2.render(mapp,xOffset+8,yOffset);
				if(!isSwimming){
				animateRight3.render(mapp,xOffset,yOffset+8);
				animateRight4.render(mapp,xOffset+8,yOffset+8);}}	
		if(direction==3&&isMove){
				animateLeft.render(mapp,xOffset+8+1,yOffset,true,false);
				animateLeft2.render(mapp,xOffset+1,yOffset,true,false);
				if(!isSwimming){
				animateLeft3.render(mapp,xOffset+8+1,yOffset+8,true,false);
				animateLeft4.render(mapp,xOffset+1,yOffset+8,true,false);}
			}
		if(!isMove&&!isJumping){
			
			animateIdile.render(mapp,xOffset,yOffset);
			animateIdile2.render(mapp,xOffset+8,yOffset);
			if(!isSwimming){
			animateIdile3.render(mapp,xOffset,yOffset+8);
			animateIdile4.render(mapp,xOffset+8,yOffset+8);}
			}
		
		
		if(isJumping){
			
			int shadeColor=0;
			int shadeColor2=0;
			int index=this.animateJump.getIndex()%this.animateJump.getAnimateCoords().length;
			
			if(index==0){
				

				shadeColor=Colors.getEColorSeries(-1,-1,-1,-1);
				shadeColor2=Colors.getEColorSeries(-1,-1,-1,-1);
				
			}else if(index==1){
				shadeColor=Colors.getEColorSeries(-1,-1,-1,-1);
				shadeColor2=Colors.getEColorSeries(-1,-1,-1,-1);
			}else if(index==2){
				y--;
				shadeColor=Colors.getEColorSeries(-1, -1, 000, -1);
				shadeColor2=Colors.getEColorSeries(000, 000, 000,-1);
				mapp.doMapping(x, y+12,0+27*32,shadeColor,shadeColor2,false,false);
				mapp.doMapping(x+8, y+12,0+27*32,shadeColor,shadeColor2,true, false);
			}else if(index==3){
				
				shadeColor=Colors.getEColorSeries(-1, -1, 000, 000);
				shadeColor2=Colors.getEColorSeries(000, 000, 000,-1);
				mapp.doMapping(x, y+12,0+27*32,shadeColor,shadeColor2,false,false);
				mapp.doMapping(x+8, y+12,0+27*32,shadeColor,shadeColor2,true, false);
			}else if(index==4){
				
				shadeColor=Colors.getEColorSeries(-1, 000, 000, 000);
				shadeColor2=Colors.getEColorSeries(000, 000, 000,-1);
				mapp.doMapping(x, y+13,0+27*32,shadeColor,shadeColor2,false,false);
				mapp.doMapping(x+8, y+13,0+27*32,shadeColor,shadeColor2,true, false);
			}else if(index==5){
				y++;
				shadeColor=Colors.getEColorSeries(-1, 000, 000, 000);
				shadeColor2=Colors.getEColorSeries(000, 000, 000,-1);
				mapp.doMapping(x, y+14,0+27*32,shadeColor,shadeColor2,false,false);
				mapp.doMapping(x+8, y+14,0+27*32,shadeColor,shadeColor2,true, false);
			}else if(index==6){
				y++;
				shadeColor=Colors.getEColorSeries(-1, 000, 000, 000);
				shadeColor2=Colors.getEColorSeries(000, 000, 000,-1);
				mapp.doMapping(x, y+13,0+27*32,shadeColor,shadeColor2,false,false);
				mapp.doMapping(x+8, y+13,0+27*32,shadeColor,shadeColor2,true, false);
			}else if(index==7){
				
				shadeColor=Colors.getEColorSeries(-1,-1,-1,-1);
				shadeColor2=Colors.getEColorSeries(-1,-1,-1,-1);
			}else if(index==8){
				shadeColor=Colors.getEColorSeries(-1,-1,-1,-1);
				shadeColor2=Colors.getEColorSeries(-1,-1,-1,-1);
				
			}else if(index==9){
				shadeColor=Colors.getEColorSeries(-1,-1,-1,-1);
				shadeColor2=Colors.getEColorSeries(-1,-1,-1,-1);
				this.isJumping=false;
			}
			
			animateJump.render(mapp,x,y);
			animateJump2.render(mapp,x+8,y);
			
			if(!isSwimming){
			animateJump3.render(mapp,x,y+8);
			animateJump4.render(mapp,x+8,y+8);
			}
			}
		
		if(!isJumping){
			mapp.doMapping(xOffset, yOffset,16*32, Colors.getEColorSeries(111,-1,-1,-1), 
					 Colors.getEColorSeries(222,333,555,-1), false,false);
			mapp.doMapping(xOffset+8, yOffset,1+16*32, Colors.getEColorSeries(111, -1,-1,-1), 
					 Colors.getEColorSeries(222,333,555,-1), false,false);}else{
		mapp.doMapping(xOffset, yOffset-4,16*32,Colors.getEColorSeries(111, -1,-1,-1), 
				 Colors.getEColorSeries(222,333,555,-1), false,false);
		mapp.doMapping(xOffset+8, yOffset-4,1+16*32,Colors.getEColorSeries(111, -1,-1,-1), 
				 Colors.getEColorSeries(222,333,555,-1), false,false);}
		
		if(inven.length>0){
    		for(int i=0;i<3;i++){
    			if(inven[i]!=null){
    			inven[i].render(mapp);;}
    		}
    	}
	}
		
	
	
	public void walk(int xa,int ya){
			if(xa!=0&&ya!=0){
				walk(xa,0);
				walk(0,ya);
				numSteps--;
				return;
			}
			
			numSteps++;
			numSteps=numSteps%10000;
			if(!hasCollided(xa,ya)){
			if(ya<0){
				direction=0;
			}
			if(ya>0){
				direction=2;
			}
			if(xa>0){
				direction=1;
			}
			if(xa<0){
				direction=3;
			}
			x+=xa*speed;
			y+=ya*speed;
			}	
		
	}

	@Override
	public boolean hasCollided(int xa, int ya) {
		int xMin=6;
		int xMax=10;
		int yMin=10;
		int yMax=15;
		
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
	
	public boolean isTeleport() {
		return teleport;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setItemToInven(RealItem item,int num){
		inven[num]=item;
	}
	
	public RealItem getItemFromInven(int num){
		return inven[num];
	}
	
	public void removeItemFromInven(int num){
		if(inven[num]!=null){
		inven[num].isLive=false;
		inven[num]=null;}
	}
	
	public boolean isJumping() {
		return isJumping;
	}
	
	public RealItem[] getInven() {
		return inven;
	}
}

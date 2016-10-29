package Assets;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import Inventory.Inventory;
import Main.GameFrame;
import Tool.MapLoader;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;

public class Zombie extends Proffesor{

	private String source="";
	private boolean teleport;
	private int direction=2;
	private Mapping mapp;
	private int state;
	protected int numSteps=0;
	int colorseries=Colors.getEColorSeries(000,550,005,500);
	int colorseries2=Colors.getEColorSeries(240,341,555,-1);
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
	
	private int tickCount;
	
	public RealItem[] inven;
	private boolean used1=false;
	private boolean used2=false;
	public int bulletNum;
	private CopyOnWriteArrayList<Taint> taints;
	long last;
	private int exp=200;
	private Line2D line,line2,line3;
	
	public Zombie(MapLoader ml, InputHandler input, Mapping mapp, int x, int y,String name) {
		super(ml, input, mapp, x, y);
		this.speed=1;
		this.name=name;
		inven=new RealItem[3];
		taints=new CopyOnWriteArrayList<Taint>();
		//ak=new AK(ml,input, mapp, x, y, 1,"AK47",this);
		hurtTime=10;
		init();
	}
	
	
	public void init(){
		this.animateDown=new Animator(new int[][]{{12,6},{14,6},{12,6},{14,6}}, colorseries, colorseries2,250, mapp);
		this.animateDown2=new Animator(new int[][]{{13,6},{15,6},{13,6},{15,6}}, colorseries, colorseries2,250, mapp);
		this.animateDown3=new Animator(new int[][]{{12,7},{14,7},{12,7},{14,7}}, colorseries, colorseries2,250, mapp);
		this.animateDown4=new Animator(new int[][]{{13,7},{15,7},{13,7},{15,7}}, colorseries, colorseries2,250, mapp);
		this.animateUp=new Animator(new int[][]{{12,8},{14,8},{12,8},{14,8}}, colorseries, colorseries2,250, mapp);
		this.animateUp2=new Animator(new int[][]{{13,8},{15,8},{13,8},{15,8}}, colorseries, colorseries2,250, mapp);
		this.animateUp3=new Animator(new int[][]{{12,9},{14,9},{12,9},{14,9}}, colorseries, colorseries2,250, mapp);
		this.animateUp4=new Animator(new int[][]{{13,9},{15,9},{13,9},{15,9}}, colorseries, colorseries2,250, mapp);
		this.animateRight=new Animator(new int[][]{{12,10},{14,10},{16,10},{14,10}}, colorseries, colorseries2,250, mapp);
		this.animateRight2=new Animator(new int[][]{{13,10},{15,10},{17,10},{15,10}}, colorseries, colorseries2,250, mapp);
		this.animateRight3=new Animator(new int[][]{{12,11},{14,11},{16,11},{14,11}}, colorseries, colorseries2,250, mapp);
		this.animateRight4=new Animator(new int[][]{{13,11},{15,11},{17,11},{15,11}}, colorseries, colorseries2,250, mapp);
		this.animateLeft=new Animator(new int[][]{{12,10},{14,10},{16,10},{14,10}}, colorseries, colorseries2,250, mapp);
		this.animateLeft2=new Animator(new int[][]{{13,10},{15,10},{17,10},{15,10}}, colorseries, colorseries2,250, mapp);
		this.animateLeft3=new Animator(new int[][]{{12,11},{14,11},{16,11},{14,11}}, colorseries, colorseries2,250, mapp);
		this.animateLeft4=new Animator(new int[][]{{13,11},{15,11},{17,11},{15,11}}, colorseries, colorseries2,250, mapp);
		this.animateIdile=new Animator(new int[][]{{12,12},{14,12},{16,12},{18,12},{20,12},{18,12},{16,12},{14,12}}, colorseries, colorseries2,150, mapp);
		this.animateIdile2=new Animator(new int[][]{{13,12},{15,12},{17,12},{19,12},{21,12},{19,12},{17,12},{15,12}}, colorseries, colorseries2,150, mapp);
		this.animateIdile3=new Animator(new int[][]{{12,13},{14,13},{16,13},{18,13},{20,13},{18,13},{16,13},{14,13}}, colorseries, colorseries2,150, mapp);
		this.animateIdile4=new Animator(new int[][]{{13,13},{15,13},{17,13},{19,13},{21,13},{19,13},{17,13},{15 ,13}}, colorseries, colorseries2,150, mapp);
		
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
		int xa=0,ya=0;
		if(input!=null){
			
			
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
			
		
		}
		
		if(input==null){
			switch(direction){
			case 0: ya=-1;break;
			case 1: xa=1;break;
			case 2: ya=1;break;
			case 3: xa=-1;break;
			default: xa=ya=0;break;
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
			
			pluginAI(xa, ya);
			if(isMove&&direction==2){
	    		animateDown.tick();
	    		animateDown2.tick();
	    		animateDown3.tick();
	    		animateDown4.tick();
	    	}
	    	
	    	if(isMove&&direction==0){
	    		animateUp.tick();
	    		animateUp2.tick();
	    		animateUp3.tick();
	    		animateUp4.tick();
	    	}
	    	
	    	if(isMove&&direction==1){
	    		animateRight.tick();
	    		animateRight2.tick();
	    		animateRight3.tick();
	    		animateRight4.tick();
	    	}
	    	if(isMove&&direction==3){
	    		animateLeft.tick();
	    		animateLeft2.tick();
	    		animateLeft3.tick();
	    		animateLeft4.tick();
	    	}
		}
		
		if(xa!=0||ya!=0){
			walk(xa,ya);
			isMove=true;
		}else{
			isMove=false;
		}
		
			if(ml.getTile((this.x+8)>>3, (this.y+10)>>3).getTileId()==3){
	    		isSwimming=true;
	    	}
	    	if(isSwimming&&ml.getTile((this.x+8)>>3, (this.y+10)>>3).getTileId()!=3){
	    		isSwimming=false;
	    	}
	    	
	    	if(ml.getTile((this.x+8)>>3, (this.y+10)>>3).getTileId()==8){
	    		teleport=true;
	    		Check:for(Entity e:ml.getEntities()){
	    			if((Math.abs(e.x-x)>>3)<=2&&!e.getName().equals("Zombie")
	    					&&!(e instanceof RealItem)){
	    				setSource(e.getName());break Check;
	    			}
	    		}
	    	}
	    	
	    	
	    	if(!isMove){
	    		animateIdile.tick();
	    		animateIdile2.tick();
	    		animateIdile3.tick();
	    		animateIdile4.tick();
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
	    	AK ak=new AK(ml,GameFrame.input, mapp,0,0,0,"AK900");
	    	ak.setUser(this);
	    	inven[0]=ak;
	    	used1=true;
	    	}
	    	if(inven[0]!=null){
	    		if(findTarget()&&!shootFriend()){
	    		((AK)inven[0]).prepareShoot();
	    		((AK)inven[0]).modifiedShoot();}
	    	}
	}
	
	private boolean findTarget() {
		Iterator<Entity> it=ml.getEntities().iterator();
		Check:while(it.hasNext()){
			Entity t=it.next();
			if(t.getName().equals("Proffesor")){
				line=new Line2D.Double(x+4,y+4,t.x+4,t.y+4);
				line2=new Line2D.Double(x+12,y+14,t.x+12,t.y+14);
				line3=new Line2D.Double(x+12,y,t.x+12,t.y);
				((AK)inven[0]).setDirection(findDirection(t));
				if(line.intersects(t.rc)||line2.intersects(t.rc)||line3.intersects(t.rc)){
				bulletNum=30;
				return true;}
			}
		}
		return false;
	}
	
	private boolean shootFriend(){
		Iterator<Entity> it=ml.getEntities().iterator();
		Check:while(it.hasNext()){
			Entity t=it.next();
			if(!t.isLive) continue;
			if((line.intersects(t.rc)||line2.intersects(t.rc)||line3.intersects(t.rc))&&!(t.getName().equals("Proffesor"))&&t!=this){
				return true;
			}
		}
		return false;
	}

	private int findDirection(Entity e){
		int x=(e.x>>3)-(this.x>>3);
		int y=(this.y>>3)-(e.y>>3);
		int ak_direction;
		if(10*y>=-24*x&&10*y>24*x){
			ak_direction=0;
		}
		else if(10*y<=24*x&&24*y>10*x){
			ak_direction=1;
		}
		else if(-24*y<10*x&&24*y<=10*x){
			ak_direction=2;
		}
		else if(-24*y>=10*x&&-10*y<24*x){
			ak_direction=3;
		}
		else if(10*y<24*x&&-10*y>=24*x){
			ak_direction=4;
		}
		else if(10*y>=24*x&&24*y<10*x){
			ak_direction=5;
		}
		else if(-24*y>10*x&&24*y>=10*x){
			ak_direction=6;
		}else{
			ak_direction=7;
		}
		return ak_direction;
	}

	private void pluginAI(int xa,int ya) {
		if(bloodbar>0){
		if(hasCollided(xa, ya)){
			direction=(direction+1)%4;
		}else{
			if(System.currentTimeMillis()-last>2000){
				last=System.currentTimeMillis();
				direction=new Random().nextInt(5);
			}
		}}else{
			if(System.currentTimeMillis()-last>50){
				last=System.currentTimeMillis();
				direction=new Random().nextInt(5);
			}
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
		if(!isMove){
			
			animateIdile.render(mapp,xOffset,yOffset);
			animateIdile2.render(mapp,xOffset+8,yOffset);
			if(!isSwimming){
			animateIdile3.render(mapp,xOffset,yOffset+8);
			animateIdile4.render(mapp,xOffset+8,yOffset+8);}
			}
		
		if(inven.length>0){
    		for(int i=0;i<3;i++){
    			if(inven[i]!=null){
    			inven[i].render(mapp);;}
    		}
    	}
	}
		
	
}


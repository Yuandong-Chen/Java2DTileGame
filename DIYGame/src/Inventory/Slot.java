package Inventory;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;

import Main.GameFrame;

public class Slot extends Rectangle{

	private Item item;
	private int size=30;
	private int x,y;
	private boolean isHeldOver;
	private int maxStack=30;
	private int currentStack;
	private int slotID=0;
	private Rectangle rc;
	private boolean picked;
	private boolean mousepicked;
	private boolean plugin;
	private boolean abandon;
	private java.awt.Font font=new java.awt.Font("Fipps",java.awt.Font.PLAIN,12);
	
	public Slot(int x,int y) {
		this.x=x;this.y=y;
		this.setBounds(x, y, size, size);
		rc=new Rectangle();
	}
	
	public void renderG(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		if(item!=null){	
			g.drawImage(item.getItemImage(),rc.x,rc.y,size-1,size-1,null);		
			//g.setFont(font);
			if(getSlotID()!=1){
			g.setColor(Color.YELLOW);
			g.setFont(font);
			g.drawString(""+currentStack,rc.x+size/2,rc.y+size-4);}
			g.setColor(Color.WHITE);
		}
		
		g.setColor(Color.WHITE);
		if(isHeldOver){
			g.setColor(Color.PINK);
		}
		g2.drawRect(x,y,size-1,size-1);
	}
	
	public void tick(){
		if(currentStack==0){
			if(item!=null){
				clear();
			}
		}
		rc.setBounds(x,y,size,size);
		//
		if(picked&&!Inventory.mouseSlot.mousepicked){
			
			Inventory.mouseSlot.setItemforPick(item,currentStack);
			Inventory.mouseSlot.mousepicked=true;
		}
		
		if(abandon){
			Inventory.mouseSlot.clear();
			Inventory.mouseSlot.mousepicked=false;
			abandon=!abandon;
		}
		//
		if(picked){
			clear();
		}
		
		if(plugin){
			if((Inventory.mouseSlot.hasSameID(item)||getSlotID()==0)&&!isFull()&&getSlotID()!=1){
			setItemforPick(Inventory.mouseSlot.getItem(),currentStack+Inventory.mouseSlot.getCurrentStack());
			Inventory.mouseSlot.clear();Inventory.mouseSlot.mousepicked=false;GameFrame.input.pressed=false;
			}
			plugin=false;
		}
		//
		if(this.contains(GameFrame.input.mouse)){
			isHeldOver=true;
			if(GameFrame.input.pressed){
				if(!Inventory.mouseSlot.mousepicked){
				picked=true;}else{
					plugin=true;
				}
			}
			
		}else{
			isHeldOver=false;
			picked=false;
			if(GameFrame.input.leftpressed){
				abandon=true;
			}
			
		}
		
	}
	
	public void setItemforPick(Item item,int num){
		this.item=item;
		if(item!=null){
		slotID=item.getItemID();
		currentStack=num;}else{
			slotID=0;
			currentStack=0;
		}
	}
	
	public void setItem(Item item){
		this.item=item;
		slotID=item.getItemID();
		currentStack++;
	}
	
	public Item getItem() {
		return item;
	}
	
	public boolean isAir(){
		return (item==null&&slotID==0);
	}
	
	public boolean hasSameID(Item item){
		if(item==null){
			if(this.item==null){ return true;}else{
				return false;
			}	
		}
		if(item.getItemID()==slotID){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void addItemToStack(Item item){
		currentStack++;
	}
	
	public boolean isFull(){
		return (currentStack>=maxStack);
	}
	
	private void clear() {
		slotID=0;
		item=null;
		currentStack=0;
	}

	public int getSlotID() {
		return slotID;
	}
	
	public void setCurrentStack(int currentStack) {
		this.currentStack = currentStack;
	}
	
	public int getCurrentStack() {
		return currentStack;
	}
	
	public void setMaxStack(int maxStack) {
		this.maxStack = maxStack;
	}
	
	public boolean isRightClicked(){
		if(isHeldOver){
			if(GameFrame.input.pressed){
				GameFrame.input.pressed=false;
				return true;
			}else{
				return false;
			}
			}else{
				return false;
			}
	}
	
	public boolean hasItem(){
		if(item!=null){
			if(slotID!=0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}

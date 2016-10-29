package Inventory;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.util.concurrent.CopyOnWriteArrayList;

import Main.GameFrame;


public class Inventory {

	private int x,y;
	private int dimensionX,dimensionY;
	public static Slot mouseSlot;
	private CopyOnWriteArrayList<Slot> slots;
	public static CopyOnWriteArrayList<Slot> used_weapons;

	
	public Inventory(int x,int y,int dimensionX,int dimensionY) {
		this.x=y;this.y=x;this.dimensionX=dimensionX;this.dimensionY=dimensionY;
		init();
	}
	
	public void init(){
		
		mouseSlot=new Slot(2, 2);
		slots=new CopyOnWriteArrayList<Slot>();
		used_weapons=new CopyOnWriteArrayList<Slot>();
		for(int x=0;x<dimensionX;x++){
			for(int y=0;y<dimensionY;y++){
				slots.add(new Slot(this.y+y*30,this.x+x*30));
			}
		}
		
		for(int x=0;x<3;x++){
		used_weapons.add(new Slot(20+x*30,50+30*(x%2)));	
		}
		
	}
	
	public void renderG(Graphics g){
			for(Slot slot: slots){
				slot.renderG(g);
			}
			for(Slot slot: used_weapons){
				slot.renderG(g);
			}
			mouseSlot.renderG(g);
	}
	
	public void tick(){
		mouseSlot.setX(GameFrame.input.mouseMovedX-10);
		mouseSlot.setY(GameFrame.input.mouseMovedY-10);
		mouseSlot.tick();
			for(Slot slot: slots){
				slot.tick();
				
			}
		
			for(Slot slot: used_weapons){
				slot.tick();
			}
	}
	
	public void addItem(Item item){
		for(Slot slot: slots){
			if(slot.isAir()){
				slot.setItem(item);
				
				break;
			}else{
				if(slot.hasSameID(item)){
					if(!slot.isFull()){
						slot.addItemToStack(item);break;
					}
				}
			}
		}
	}
	
	public void addItem(Item item,int slotNum){
		if(slotNum<slots.size()){
			Slot slot=slots.get(slotNum);
			if(slot.isAir()){
				slot.setItem(item);
			}else{
				if(slot.hasSameID(item)){
					if(!slot.isFull()){
						slot.addItemToStack(item);
					}
				}
			}
			
		}else{
			System.err.println("Out Of Bounds: Inventory");
		}
		
	}

}

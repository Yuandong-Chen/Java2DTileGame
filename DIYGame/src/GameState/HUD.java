package GameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.text.StyledEditorKit.FontSizeAction;

import Icon.BloodBar;
import Inventory.Inventory;
import Inventory.Item;
import Inventory.Slot;
import Main.GameFrame;
import Main.Main;
import Tool.DialogBubble;
import Tool.Font;
import mytool.Colors;
import mytool.InputHandler;
import mytool.Mapping;
import mytool.SpriteSheet256;
import mytool.load;

public class HUD {

	Mapping mapp;
	MazeRunner gamestate;
	public int width,height;
	SpriteSheet256 sheet;
	int[] pixels;
	int[] colors;
	public boolean toggle=false;
	private java.awt.Font font=new java.awt.Font("Fipps",java.awt.Font.PLAIN,15);
	private Inventory inv;
	
	
	
	public HUD(MazeRunner gamestate) {
		this.gamestate=gamestate;
		this.width=gamestate.getWidth();
		this.height=gamestate.getHeight();
		this.pixels=gamestate.getGsm().getCanvas().getPixels();
		this.colors=gamestate.getGsm().getCanvas().getColors();
		inv=new Inventory(30,150,5,5);
		init();
	}
	
	public void init(){
		sheet=new SpriteSheet256("/sprite_sheet.png");
		mapp=new Mapping(width,height,sheet);
		
		
	}
	
	public void tick(){

		if(toggle){
		inv.tick();}
		GameFrame.input.tick();
		
		if(GameFrame.input.shoot.isPressed){
			inv.addItem(Item.RED_BALL,new Random().nextInt(24));
			inv.addItem(Item.GREEN_BALL,new Random().nextInt(24));
		}	
	}
	
	public void renderG(Graphics g) {

		
		renderForeground(g);
		
	
		renderInvTime(g);
		
		GameFrame.input.renderG(g);;
	}
	
	
	public void renderForeground(Graphics g){
		g.setColor(new Color(50,50,50,50));
		g.fillRect(0, 0, width*3, height*3);
	}
	public void renderInvTime(Graphics g){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		if(toggle){
		inv.renderG(g);
		g.setFont(font);
		g.setColor(new Color(255, 255, 255,200));
		g.drawString("(X,Y,Z)=("+(this.gamestate.getProf().x>>3)+","+(this.gamestate.getProf().y>>3)+","+0+"); "+dateFormat.format(date),0,(15*3)<<3);
		}
	}
	
	public void render(){
		
	}
	
	public void setAppear() {
		if(toggle){
			toggle=false;
		}else{
			toggle=true;
		}
	}
	
	public Inventory getInv() {
		return inv;
	}

}

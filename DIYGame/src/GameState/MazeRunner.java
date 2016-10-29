package GameState;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import Assets.AK;
import Assets.Entity;
import Assets.House;
import Assets.Proffesor;
import Assets.RealItem;
import Assets.Tree;
import Assets.Zombie;
import Icon.BloodBar;
import Main.GameFrame;
import Tool.MapLoader;
import mytool.Colors;
import mytool.Mapping;
import mytool.SpriteSheet256;
import Tile.*;
public class MazeRunner extends GameState{

	int[] pixels;
	int[] colors;
	public int width;
	public int height;
	public static HUD hud;
	Room room;
	private BloodBar selected_enemy_blood;
	private BloodBar blood;
	public static Stack<Room> rooms=new Stack<Room>();
	private Entity temp;
	
	
	public MazeRunner(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		width=this.gsm.getCanvas().getUWidth();
		height=this.gsm.getCanvas().getUHeight();
		
		this.pixels=this.gsm.getCanvas().getPixels();
		this.colors=this.gsm.getCanvas().getColors();
		
		room = new Room(pixels, colors, width, height,"/test.png",44,50);
		room.addEntity(new Tree(room.ml,null,room.mapp,10<<3,20,0,"Tree1"));
		House house1=new House(room.ml,null,room.mapp,28,8,5,"House");
		//house1.setRoom(room);
		room.addEntity(house1);
		
		rooms.push(room);
		hud=new HUD(this);
		blood=new BloodBar(10,10,getProf().getName().substring(0,4));
	}

	@Override
	public void tick() {
		
		if(getProf().getSource().equals("House3")){
			
			rooms.pop();
			Room room;
			room = new Room(pixels, colors, width, height,"/test.png",2,50);
			room.addEntity(new Tree(room.ml,null,room.mapp,10<<3,20,0,"Tree1"));
			House house1=new House(room.ml,null,room.mapp,30,60,5,"House3");
			
			//house1.setRoom(room);
			room.addEntity(house1);
			
			rooms.push(room);
		}
		if(getProf().getSource().equals("House")){
			
			rooms.pop();
			Room room;
			room = new Room(pixels, colors, width, height,"/face.png",44,50);
			room.addEntity(new Tree(room.ml,null,room.mapp,10<<3,20,0,"Tree1"));
			for(int i=0;i<50;i++){
			room.addEntity(new AK(room.ml,null,room.mapp,0,0,0,"AK"+i));}
			House house1=new House(room.ml,null,room.mapp,28,8,5,"House2");
			//house1.setRoom(room);
			Zombie[] zombies=new Zombie[4];
			for(int i=0;i<4;i++){
			zombies[i]=new Zombie(room.ml,null, room.mapp,i<<3,80,i+"");
			room.addEntity(zombies[i]);
			}
			room.addEntity(house1);
			rooms.push(room);
		}
		if(getProf().getSource().equals("House2")){
			
			rooms.pop();
			Room room;
			room = new Room(pixels, colors, width, height,"/test.png",2,50);
			room.addEntity(new Tree(room.ml,null,room.mapp,10<<3,20,0,"Tree1"));
			House house1=new House(room.ml,null,room.mapp,28,8,5,"House");
			House house2=new House(room.ml,null,room.mapp,30,60,5,"House3");
			//house1.setRoom(room);
			room.addEntity(house2);
			room.addEntity(house1);
			rooms.push(room);
		}
		
		rooms.peek().tick();
		hud.tick();
		if(GameFrame.input.show_hud.isPressed){
			hud.setAppear();
			GameFrame.input.show_hud.isPressed=false;
		}
		
		Iterator<Entity> it=rooms.peek().ml.getEntities().iterator();
		Check:while(it.hasNext()){
			Entity t=it.next();
			if(!t.isLive) continue;
			if(t.rc.contains(new Point(GameFrame.input.worldxPos<<3, GameFrame.input.worldyPos<<3))&&(t instanceof Proffesor)
					){
				if(selected_enemy_blood==null){
					selected_enemy_blood=new BloodBar(320,10,t.getName());
					temp=t;
				}
				if(!selected_enemy_blood.getName().equals(t.getName())){
				selected_enemy_blood=new BloodBar(320,10,t.getName());
				temp=t;
				}
				
				break Check;
			}
		}
		
		if(selected_enemy_blood!=null){
		selected_enemy_blood.setCurrent(temp.getBloodbar());
		selected_enemy_blood.decreaseTo(temp.getBloodbar());
		selected_enemy_blood.tick();}
		blood.setCurrent(getProf().getBloodbar());
		blood.decreaseTo(getProf().getBloodbar());
		blood.tick();
		
	}
	
	public void renderG(Graphics g) {	
		rooms.peek().renderG(g);
		hud.renderG(g);
		if(selected_enemy_blood!=null){
			selected_enemy_blood.renderG(g);}
		blood.renderG(g);
		
	}

	public void render() {
	
		rooms.peek().render();
		hud.render();
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public Proffesor getProf() {
		return getRoom().getProf();
	}

	public Room getRoom() {
		return rooms.peek();
	}

	
}

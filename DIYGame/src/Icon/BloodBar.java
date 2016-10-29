package Icon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Main;
import mytool.load;

public class BloodBar {

	private BufferedImage heroIcon=load.LoadImageFrom(Main.class, "bloodbar.png");
	private int x,y;
	private int current=29*3;
	private int delay=29*3;
	private int life=3;
	private int valve=current;
	private String name;
	private java.awt.Font font=new java.awt.Font("Fipps",java.awt.Font.PLAIN,15);
	
	public BloodBar(int x,int y,String name) {
		this.x=x;this.y=y;this.name=name;
	}

	public void setValve(int valve) {
		this.valve = valve;
	}
	
	public void renderG(Graphics g){
		
		g.setColor(new Color(255,0,0,200));
		g.fillRect(11+2+x,11+2+y, delay,6*3);
		if(current>20){
		g.setColor(new Color(0,255,0,200));
		}else{
			g.setColor(new Color(255,255,0,200));
		}
		g.fillRect(11+2+x,11+2+y, current,6*3);
		g.drawImage(heroIcon,10+x,10+y,32*3,8*3, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(name,x,y+20);
	}
	
	public void tick(){
		if(current>valve){
		current--;}else if (current<valve){
			current++;
		}
		if(life>0){
			life--;
		}else{
			life=3;
			if(current<delay){
			delay--;}else{
				delay=current;
			}
		}
	}
	
	public void decrease(){
		if(valve>0){
		setValve(--valve);}
	}
	
	public void decreaseTo(int set){
		if(valve>set&&valve>0){
		
		setValve(--valve);}
	}
	
	public void increase(){
		if(valve<29*3){
		setValve(++valve);}
	}
	
	public void increaseTo(int set){
		if(valve<29*3&&valve<set){
		setValve(++valve);}
	}
	
	public int getCurrent() {
		return current;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
}

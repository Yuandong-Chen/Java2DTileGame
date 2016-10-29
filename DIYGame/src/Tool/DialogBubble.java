package Tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Assets.*;

public class DialogBubble {

	Mob e;
	String msg="";
	private int width;
	private int height;
	private boolean shrink;
	
	public DialogBubble() {
		
	}
	
	public void renderG(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(new Color(255,255,255,100));
		g2.fillRoundRect(20,250,height,width,20, 20);
		g2.setColor(Color.BLACK);
		
		g2.drawRoundRect(20,250,height,width,20, 20);
	}
	
	public void tick(){
		if(!shrink&&width<100){
			width++;
		}
		if(height<430&&!shrink){
			height+=4;
		}
		
	}
	
}

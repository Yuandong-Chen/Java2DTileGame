package DIYGameCanvasThread;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Random;

import GameState.*;
import Main.GameFrame;
import mytool.GameCanvasThread;
import mytool.Mapping;

public class DIYGameCanvasThread extends GameCanvasThread{

	private static final long serialVersionUID = 1L;
	GameStateManager gsm;
	
	public DIYGameCanvasThread(String name) {
		super(name);
		gsm=new GameStateManager(this);
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Cursor cursor=toolkit.createCustomCursor(toolkit.getImage(""),new Point(0,0),"Cursor");
		setCursor(cursor);
	}

	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		gsm.init();
	}

	


	@Override
	public void renderG(Graphics g) {
		super.renderG(g);
		gsm.renderG(g);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
		gsm.render();
		
		super.render();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	    //super.tick();

		
		gsm.tick();
		
	}
	

}

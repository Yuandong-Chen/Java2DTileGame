package mytool;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class InputHandler implements KeyListener,MouseListener, MouseMotionListener, MouseWheelListener{
	
	public static int mouseMovedX=80*3,mouseMovedY=60*3;
	public static Point mouse;
	public static boolean pressed;
	public int xPos,yPos;
	public int worldxPos,worldyPos;
	public static boolean leftpressed;
	
	public void tick(){
		setWorldxPos(xPos+(mouseMovedX/3)/8);
		setWorldyPos(yPos+(mouseMovedY/3)/8);
		mouse=new Point(mouseMovedX,mouseMovedY);
	}
	
	public void render(Mapping mapp){
		
		if(pressed){
			
			mapp.doMapping(mouseMovedX/3,mouseMovedY/3, 32,Colors.getEColorSeries(-1,-1,-1,-1)
					,Colors.getEColorSeries(-1,-1,-1,444), false,false);
		}else{
			mapp.doMapping(mouseMovedX/3,mouseMovedY/3, 32,Colors.getEColorSeries(-1,-1,-1,-1)
					,Colors.getEColorSeries(-1,-1,-1,500),false,false);
		}
	}
	
	public void renderG(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		if(this.pressed){
			g2.setStroke(new BasicStroke(3));
			g.setColor(Color.RED);
		g2.drawRect(mouseMovedX,mouseMovedY,5,5);
		}else{
			g.setColor(Color.PINK);
			g2.drawRect(mouseMovedX,mouseMovedY,5,5);
		}
	}
	
	public class Key{
		public boolean isPressed=false;
	}
	
	public Key up=new Key();
	public Key down=new Key();
	public Key left=new Key();
	public Key right=new Key();
	public Key shoot=new Key();
	public Key show_hud=new Key();
	
	public InputHandler(Canvas game){
		game.addKeyListener(this);
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
		game.addMouseWheelListener(this);
	}

	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_W){
			this.up.isPressed=true;
			//System.out.println("ok");
		}
		if(e.getKeyCode()==KeyEvent.VK_A){
			this.left.isPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			this.down.isPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			this.right.isPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_J){
			this.shoot.isPressed=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_U){
			this.show_hud.isPressed=true;
		}
	}

	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_W){
			this.up.isPressed=false;
			//System.out.println("nope");
		}
		if(e.getKeyCode()==KeyEvent.VK_A){
			this.left.isPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_S){
			this.down.isPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_D){
			this.right.isPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_J){
			this.shoot.isPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_U){
			this.show_hud.isPressed=false;
		}
		
	}

	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMovedX=e.getX();
		mouseMovedY=e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMovedX=e.getX();
		mouseMovedY=e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			pressed=true;
			
			System.out.println(getWorldxPos()+" "+getWorldyPos());
			
		}
		
		if(e.getButton()==MouseEvent.BUTTON3){
			leftpressed=true;
			
			System.out.println(getWorldxPos()+" "+getWorldyPos());
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			pressed=false;
		}
		if(e.getButton()==MouseEvent.BUTTON3){
			leftpressed=false;			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWorldxPos() {
		return worldxPos;
	}

	public void setWorldxPos(int worldxPos) {
		this.worldxPos = worldxPos;
	}

	public int getWorldyPos() {
		return worldyPos;
	}

	public void setWorldyPos(int worldyPos) {
		this.worldyPos = worldyPos;
	}
	
}

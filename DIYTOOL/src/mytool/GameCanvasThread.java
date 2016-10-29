package mytool;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;


public class GameCanvasThread extends Canvas implements Runnable{

	private Thread thread;
	protected static final int WIDTH=160;
	protected static final int HEIGHT=WIDTH*3/4;
	protected static final int SCALE=3;
	private String name;
	boolean running =false;
	private BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels=((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colors=new int[6*6*6];
	
	public GameCanvasThread(String name) {
		this.name=name;
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	}
	
	public void init(){
		
		int index=0;
		
		for(int r=0;r<6;r++){
			for(int g=0;g<6;g++){
				for(int b=0;b<6;b++){
					int rr=(r*255/5);
					int gg=(g*255/5);
					int bb=(b*255/5);
					
					colors[index++]=(rr<<16)|(gg<<8)|(bb);
				}
			}
		}
		
	}
	
	
	public void run() {
		int tickRate=50;
		long lastTime=System.nanoTime();
		double delta=0;
		double nsPerTick=(1000000000/tickRate);
		init();
		while(running){
			
			long currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/nsPerTick;
			lastTime=currentTime;
			if(delta>=1){
			tick();
			render();
			
			delta--;
			}
		}
		
	}

	public void renderG(Graphics g) {
		
	}
	
	public void render() {
		
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		g.drawImage(image, 0, 0, this.getWidth(),this.getHeight(),null);
		renderG(g);
		g.dispose();
		bs.show();
	}

	public void tick() {
		for(int x=0;x<WIDTH;x++){
			for(int y=0;y<HEIGHT;y++){
				pixels[x+y*WIDTH]=colors[new Random().nextInt(216)];
			}
		}
		
	}
	
	public synchronized void start(){
		if(running==false){
		running=true;
		new Thread(this).start();
		}
	}
	public synchronized void stop(){
		running=false;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int[] getColors() {
		return colors;
	}
	

	public int getUWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}
	
	public int getUHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}

}

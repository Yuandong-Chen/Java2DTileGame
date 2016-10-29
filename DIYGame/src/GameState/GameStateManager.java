package GameState;

import java.awt.Graphics;
import java.util.Stack;

import mytool.GameCanvasThread;

public class GameStateManager {

	public static Stack<GameState> states;
	public GameCanvasThread canvas;
	
	public GameStateManager(GameCanvasThread canvas) {
		this.canvas=canvas;
		states=new Stack<GameState>();
		states.push(new MazeRunner(this));
	}

	public void tick(){
		states.peek().tick();
		
	}
	public void render(){
		
		states.peek().render();
		
	}
	
	public void init() {
		states.peek().init();
	}

	public GameCanvasThread getCanvas() {
		return canvas;
	}

	public void renderG(Graphics g) {
		states.peek().renderG(g);
	}
	
}

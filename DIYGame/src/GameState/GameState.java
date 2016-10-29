package GameState;


import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameState {
	
	public GameStateManager gsm;

	public GameState(GameStateManager gsm) {
		this.gsm=gsm;
	}

	public abstract void init();
	public abstract void tick();
	public abstract void render();
	
	public GameStateManager getGsm() {
		return gsm;
	}

	public abstract void renderG(Graphics g);
}

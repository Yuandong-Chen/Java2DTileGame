package Assets;

import Tool.MapLoader;
import mytool.InputHandler;
import mytool.Mapping;

public abstract class RealItem extends Mob{

	public RealItem(MapLoader ml, InputHandler input, Mapping mapp, int x, int y, int speed, String name) {
		super(ml, input, mapp, x, y, speed, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void render(Mapping mapp);

	@Override
	public abstract void tick();
	
	public abstract void setUser(Proffesor pr);

}

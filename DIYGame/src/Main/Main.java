package Main;
import DIYGameCanvasThread.DIYGameCanvasThread;
import mytool.GameCanvasThread;

public class Main {
	public static void main(String[] args) {
			new GameFrame(new DIYGameCanvasThread("半成品")).getCanvas().start();
	}
}



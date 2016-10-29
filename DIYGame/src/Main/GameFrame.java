package Main;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import mytool.GameCanvasThread;
import mytool.InputHandler;
import mytool.load;

public class GameFrame extends JFrame{

	GameCanvasThread canvas;
	public static InputHandler input;
	
	public GameFrame(GameCanvasThread canvas) {
		this.canvas=canvas;
		input=new InputHandler(canvas);
		this.setLayout(new BorderLayout());
		this.add(canvas,BorderLayout.CENTER);
		//this.setUndecorated(true);
		//
		//
		this.pack();
		this.setTitle(canvas.getName());
		this.setIconImage(load.LoadImageFrom(Main.class,"head.gif"));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public GameCanvasThread getCanvas() {
		return canvas;
	}
}

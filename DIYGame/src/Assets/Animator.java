package Assets;

import mytool.Mapping;

public class Animator {

	private int colorseries;
	private int colorseries2;
	private int tilePos;
	private int[][] animateCoords;
	private int delay;
	private int index;
	private long last;
	private Mapping mapp;
	
	public Animator(int[][] animateCoords,int colorseries,int colorseries2,int delay,Mapping mapp) {
		this.mapp=mapp;
		this.colorseries=colorseries;
		this.colorseries2=colorseries2;
		this.tilePos=animateCoords[0][0]+32*animateCoords[0][1];
		this.animateCoords=animateCoords;
		this.delay=delay;
		last=System.currentTimeMillis();
		index=0;
	}
	
	public void tick(){

		if(System.currentTimeMillis()-last>=delay){
			last=System.currentTimeMillis();
			tilePos=animateCoords[(index++)%animateCoords.length][0]+32*animateCoords[(index)%animateCoords.length][1];
		}
	}
	
	public void render(Mapping mapp,int x,int y,boolean MirrorX,boolean MirrorY){
		mapp.doMapping(x, y,tilePos, colorseries,colorseries2,MirrorX,MirrorY);
	}
	
	public void render(Mapping mapp,int x,int y){
		mapp.doMapping(x, y,tilePos, colorseries,colorseries2,false,false);
	}
	
	public int getIndex() {
		return index;
	}
	
	public int[][] getAnimateCoords() {
		return animateCoords;
	}

}

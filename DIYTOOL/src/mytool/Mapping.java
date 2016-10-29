package mytool;

public class Mapping {

	public int width;
	public int height;
	private SpriteSheet256 sheet;
	public int[] pixels;
	public int xOffset=0,yOffset=0;
	public Mapping(int width, int height, SpriteSheet256 sheet){
		this.width=width;
		this.height=height;
		this.sheet=sheet;
		pixels=new int[width*height];
	}
	
	public void doMapping(int x,int y,int sheetIndex,int colorseries,int colorseries2,boolean mirrorX,boolean mirrorY){
		int xsheetIndex=sheetIndex%32;
		int ysheetIndex=(sheetIndex-sheetIndex%32);
		sheetIndex=(xsheetIndex<<3)+(ysheetIndex<<6);
		int xSheet=sheetIndex%(32*8);
		int ySheet=sheetIndex/(32*8);
		for(int i=0;i<8;i++){
			int xPos=x+i-xOffset;
			if(mirrorX==true){xPos=x+7-i-xOffset;}
			if(xPos<0||xPos>=width) continue;
			for(int j=0;j<8;j++){
				int yPos=y+j-yOffset;
				if(mirrorY==true){yPos=y+7-j-yOffset;}
				if(yPos<0||yPos>=height) continue;
				int intensity=sheet.pixelsEIntensity[xSheet+i+((ySheet+j)<<8)];
				int index;
				if(intensity<=3){
				index=(int)(colorseries>>(intensity<<3))&255;
				}else{
					intensity-=4;
					index=(int)(colorseries2>>(intensity<<3))&255;
				}
				
				if(index<255){
				pixels[xPos+yPos*width]=index;}
			}
		}
	}
	
	public void setOffset(int xOffset,int yOffset){
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}
	
}

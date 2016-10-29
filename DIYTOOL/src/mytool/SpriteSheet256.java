package mytool;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet256 {

	
	private BufferedImage image=null;
	private int[] pixels;
	public int[] pixelsEIntensity;
	public int width;
	public int height;
	
	
	public SpriteSheet256(String path){
		
		try {
			image=ImageIO.read(SpriteSheet256.class.getResourceAsStream(path));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.width=image.getWidth();
		this.height=image.getHeight();
		pixels=image.getRGB(0, 0, width, height,null,0, width);
		pixelsEIntensity=new int[pixels.length];
		for(int i=0;i<pixels.length;i++){
			pixelsEIntensity[i]=(pixels[i]&0xff)/32;
		}
	}
	
	public void appendSheetToBottom(String path){
		
		try {
			BufferedImage imagetemp=ImageIO.read(SpriteSheet256.class.getResourceAsStream(path));
			int widthtemp=imagetemp.getWidth();
			int heighttemp=imagetemp.getHeight();
			int[] pixelstemp=imagetemp.getRGB(0, 0, widthtemp, heighttemp,null,0, widthtemp);
			int[] pixelsEIntensitytemp=new int[pixelstemp.length];
			
			for(int i=0;i<pixelstemp.length;i++){
				pixelsEIntensitytemp[i]=(pixelstemp[i]&0xff)/32;
			}
			int[] lastPixelsEIntensity=pixelsEIntensity;
		
			pixelsEIntensity=new int[lastPixelsEIntensity.length+pixelsEIntensitytemp.length];
			
			for(int i=0;i<lastPixelsEIntensity.length;i++){
				pixelsEIntensity[i]=lastPixelsEIntensity[i];
			}
			
			for(int i=0;i<pixelsEIntensitytemp.length;i++){
				pixelsEIntensity[i+lastPixelsEIntensity.length]=pixelsEIntensitytemp[i];
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

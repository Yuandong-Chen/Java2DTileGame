package mytool;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class load{

	public static BufferedImage LoadImageFrom(Class<?> classfile,String path) {
		URL url=classfile.getResource(path);
		BufferedImage img=null;
		
		try{
			img=ImageIO.read(url);
		}catch(Exception e){
			e.printStackTrace();
		}

		return img;
	}

}

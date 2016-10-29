package Tool;

import mytool.Mapping;

public class Font {

	private static String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+
	"0123456789.,:;'\"!?$%()-=+/      ";
	
	public static void render(String msg,Mapping mapp,int x,int y,int color,int color2){
		msg=msg.toUpperCase();
		
		for(int i=0;i<msg.length();i++){
			int charindex=chars.indexOf(msg.charAt(i));
			if(charindex>=0){
				mapp.doMapping(x+i*8, y,charindex+30*32, color, color2,false, false);
			}
		}
	}
}

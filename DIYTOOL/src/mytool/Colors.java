package mytool;

public class Colors {

	public static int getEColorSeries(int color1,int color2,int color3,int color4){
		return (convertToColorIndex(color4)<<24)+(convertToColorIndex(color3)<<16)
				+(convertToColorIndex(color2)<<8)+(convertToColorIndex(color1));
	}
	
	public static int convertToColorIndex(int three_digit_color){
		if(three_digit_color<0){return 255;}
		int redIntensity=(three_digit_color/100)%10;
		int greenIntensity=(three_digit_color/10)%10;
		int blueIntensity=(three_digit_color)%10;
		int colorIndex=redIntensity*36+greenIntensity*6+blueIntensity;
		return colorIndex;
	}
}

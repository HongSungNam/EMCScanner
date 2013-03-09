package emcscanner.kth.se;
/**
 * 
 * @author Jonas
 *
 */
public class WaveLengthToColorConverter {
	private static int ALPHA = 100;
	
	/**
	 * Takes in a value and returns the corresponding color value.
	 * 
	 * @param wave
	 * @return
	 */
	public static int wavelengthToColorConverter(double wave){
		/* Constants */
		double waveLength = wave;
		double intensityMax = 255;	
		double gamma = 0.80;	
		/* Variables */
		double R = 0;
		double G = 0;
		double B = 0;
		double A = ALPHA;
		
		if ( 379.9 < waveLength && waveLength < 440)
		{
			R = -(waveLength - 440) / (440 - 380);
			G = 0.0;
			B = 1.0;
		}
		else if ( 440 <= waveLength && waveLength < 490)
		{
			R = 0.0;
			G = (waveLength - 440) / (490 - 440);
			B = 1.0;
		}
		else if ( 490 <= waveLength && waveLength < 510)
		{
			R = 0.0;
			G = 1.0;
			B = -(waveLength - 510) / (510 - 490);
		}
		else if ( 510 <= waveLength && waveLength < 580)
		{
			R = (waveLength - 510) / (580 - 510);
			G = 1.0;
			B = 0.0;
		}
		else if (580 <= waveLength && waveLength < 645)
		{
			R = 1.0;
			G = -(waveLength - 645) / (645 - 580);
			B = 0.0;
		}
		else if ( 645 <= waveLength && waveLength < 780.1)
		{
			R = 1.0;
			G = 0.0;
			B = 0.0;
		}
		else
		{
			R = 0.0;
	   		G = 0.0;
	    	B = 0.0;
		}
		double factor; 
		if (380 <= waveLength && waveLength < 420)
			factor = 0.3 + 0.7 * (waveLength - 380) / (420 - 380);
		else if (420 <= waveLength && waveLength < 701)
			factor = 1.0;
		else if (701 <= waveLength && waveLength <= 780)
			factor = 0.3 + 0.7 * (780 - waveLength) / (780 - 700);
		else
			factor = 0.0;
		
		R = adjust(R, factor, intensityMax, gamma);
		G = adjust(G, factor, intensityMax, gamma);
		B = adjust(B, factor, intensityMax, gamma);
		
		return new java.awt.Color((int)R, (int)G, (int)B, (int)A).getRGB();
	}

	/**
	 * Adjust the color just by a a little bit in the ends so it looks as it should
	 * @param color
	 * @param factor
	 * @param intensityMax
	 * @param gamma
	 * @return
	 */
	private static double adjust(double color, double factor, double intensityMax, double gamma){
		if (color == 0.0)
			return 0.0;
		else
			return (intensityMax * Math.pow(color * factor, gamma));
	}
}

package emcscanner.kth.se;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ColorPallet {

	/**
	 * Function that creates a color pellet for all wave lengths
	 */
	public void createColorPalet() {
		IplImage colorImage2 = IplImage.create(100, 400, SettingsPanel.getPhoto().depth(), SettingsPanel.getPhoto().nChannels());
		BufferedImage buffImage2 = colorImage2.getBufferedImage();
		
		double wave = 380.0;
		for (int xi = 0; xi < 100; xi++){
			for (int yi = 0; yi < 400; yi++) {
				buffImage2.setRGB(xi, yi, WaveLengthToColorConverter.wavelengthToColorConverter(wave));
				wave += 1;
			}
			wave = 380;
		}
		try {
			File outputfile = new File("webcam photo/ColorPalet" + SettingsPanel.getFILE_NAME() + ".png");
			ImageIO.write(buffImage2, "png", outputfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

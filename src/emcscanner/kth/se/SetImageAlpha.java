package emcscanner.kth.se;

import java.awt.image.BufferedImage;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class SetImageAlpha {
	/**
	 * Sets the scan background to invisible so that we can see the image bellow 
	 */
	public static void setBuffImageAlpha() {
		SettingsPanel.scanPanel.scan.setBuffImage(new BufferedImage[SettingsPanel.scanPanel.scan.frequency.length]);
		
		SettingsPanel.scanPanel.scan.colorImage = IplImage.create(SettingsPanel.CROPT_PHOTO_DIMENSION.width * 
																	Program.TIONDELS_MILLI_METER_PIXEL, 
																  SettingsPanel.CROPT_PHOTO_DIMENSION.height * 
																  	Program.TIONDELS_MILLI_METER_PIXEL, 
																  8/*SettingsPanel.photo.depth()*/, 
																  4/*SettingsPanel.photo.nChannels()*/);
		
		for (int i = 0; i < SettingsPanel.scanPanel.scan.frequency.length; i++) { 
			SettingsPanel.scanPanel.scan.getBuffImage()[i] = SettingsPanel.scanPanel.scan.colorImage.clone().getBufferedImage();
			SettingsPanel.scanPanel.scan.getBuffImage()[i].setRGB(0, 
																  0, 
																  SettingsPanel.scanPanel.scan.getBuffImage()[i].getWidth(), 
																  SettingsPanel.scanPanel.scan.getBuffImage()[i].getHeight(), 
																  new int[SettingsPanel.scanPanel.scan.getBuffImage()[i].getWidth() * 
																          SettingsPanel.scanPanel.scan.getBuffImage()[i].getHeight()], 
																  0, 
																  SettingsPanel.scanPanel.scan.getBuffImage()[i].getWidth());
		}
	}
}

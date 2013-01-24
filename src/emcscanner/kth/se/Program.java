package emcscanner.kth.se;

import java.awt.*;

/**
 * Main class for the program 
 * EMC-Scanner
 */
public class Program {	
	public static MainFrame frame;
	public static CameraPanel cameraPanel;
	public static SettingsPanel settingsPanel;
	public static DimentionPanel dimentionPanel;
	public static ConclutionPanel conclutionPanel;
	public static ImageScannedPanel imageScannedPanel;
	public static StartControlPanel startControlPanel;
	public static ManualPanel manualPanel;

	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args) {
		
    	cameraPanel = new CameraPanel();
    	settingsPanel = new SettingsPanel();
    	dimentionPanel = new DimentionPanel();
    	conclutionPanel = new ConclutionPanel();
    	imageScannedPanel = new ImageScannedPanel();
    	startControlPanel = new StartControlPanel();
    	manualPanel = new ManualPanel();
    	frame = new MainFrame();
    	
    	GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);

		//Show it the frame
		frame.setVisible(true);
        
	}
}
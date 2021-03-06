package emcscanner.kth.se;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Main class for the program 
 * EMC-Scanner
 */
public class Program {	
	public static CameraPanel cameraPanel;
	public static SettingsPanel settingsPanel;
	public static ConclutionPanel conclutionPanel;
	public static StartControlPanel startControlPanel;
	public static ManualPanel manualPanel;
	public static ImagePanel imagePanel;
	public static MainFrame frame;
	
	public static MainListener mainListener;
	
	public static Motor motor;
	public static WaveLengthToColorConverter waveLengthToColorConverter;
	public static SetImageAlpha setImageAlpha; 
	public static SetPanelStage setPanelStage;

	public static int TIONDELS_MILLI_METER_PIXEL = 1;
		
	/* Used Colors */
	public static Color LIGHT_BLUE_COLOR 	= new Color(100,150,255); 
	public static Color LIGHT_BLUE_COLOR2 	= new Color(240,250,255); 
	public static Color RED_COLOR 			= new Color(255,0,0); 
	public static Color LIGHT_RED_COLOR 	= new Color(255,240,240); 
	public static Color LIGHT_GREEN_COLOR 	= new Color(150,255,80); 
	public static Color DARK_GREEN_COLOR 	= new Color(120,200,40);
	public static Color LIGHT_GRAY_COLOR 	= new Color(120,120,120);
	public static Color LIGHT_GRAY_COLOR2 	= new Color(240,240,240);
	public static Color LIGHT_BLUE_ALPHA_COLOR1 = new Color(100,150,255,0); 
	public static Color LIGHT_BLUE_ALPHA_COLOR 	= new Color(100,150,255,40);
	
	public static Color LIGHT_GREEN_ALPHA_COLOR1 = new Color(150,255,80,0); 
	public static Color LIGHT_GREEN_ALPHA_COLOR = new Color(220,255,180); 
	public static Color LIGHT_RED_ALPHA_COLOR1 = new Color(255,240,240,0); 
	public static Color LIGHT_RED_ALPHA_COLOR 	= new Color(255,200,200); 

	/* Light blue border for the float input text field */
	public static Border LIGHT_BLUE_BORDER 	= BorderFactory.createLineBorder(LIGHT_BLUE_COLOR);
	public static Border LIGHT_GRAY_BORDER 	= BorderFactory.createLineBorder(LIGHT_GRAY_COLOR);
	public static Border GREEN_BORDER 		= BorderFactory.createLineBorder(LIGHT_GREEN_COLOR);
	public static Border RED_BORDER 		= BorderFactory.createLineBorder(RED_COLOR);	
	
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args) {

    	cameraPanel = new CameraPanel();
    	settingsPanel = new SettingsPanel();
    	conclutionPanel = new ConclutionPanel();
    	startControlPanel = new StartControlPanel();
    	manualPanel = new ManualPanel();
    	imagePanel = new ImagePanel();
    	frame = new MainFrame();
    	
    	mainListener = new MainListener();
    	
    	MainPanel.setStages(startControlPanel, manualPanel);
    	GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);

		//Show it the frame
		frame.setVisible(true);
        
	}
}
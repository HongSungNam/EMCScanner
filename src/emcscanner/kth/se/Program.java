package emcscanner.kth.se;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 * Main class for the program 
 * EMC-Scanner
 */
public class Program {	
	public static CameraPanel cameraPanel;
	public static SettingsPanel settingsPanel;
	public static DimentionPanel dimentionPanel;
	public static ConclutionPanel conclutionPanel;
	public static ImageScannedPanel imageScannedPanel;
	public static StartControlPanel startControlPanel;
	public static ManualPanel manualPanel;
	public static ImagePanel imagePanel;
	public static MainFrame frame;
	
	public static SetPanelStage setPanelStage;

	public static int TIONDELS_MILLI_METER_PIXEL = 1;
	
	/* Image Icon next button */
	public static ImageIcon NEXT_BUTTON_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonBlueNext.png");
	public static ImageIcon NEXT_BUTTON_DISABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonGrayNext.png");
	public static ImageIcon NEXT_BUTTON_BLUE_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueNextPrest.png");
	public static ImageIcon NEXT_BUTTON_GRAY_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonGrayNextPrest.png");
	
	/* Import the images for the BACK button */
	public static ImageIcon BACK_BUTTON_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonBlueBack.png");
	public static ImageIcon BACK_BUTTON_BLUE_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueBackPrest.png");
	
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
	
	/* Dimension */
	public static Dimension BUTTON_DIMENSION 		= new Dimension(85, 50);
	public static Dimension MEDIUM_BUTTON_DIMENSION = new Dimension(100, 50);
	public static Dimension BUTTON_SMAL_DIMENSION 	= new Dimension(20, 20);
	
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
    	imagePanel = new ImagePanel();
    	frame = new MainFrame();
    	
    	MainPanel.setStages(startControlPanel, manualPanel);
    	GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);

		//Show it the frame
		frame.setVisible(true);
        
	}
}
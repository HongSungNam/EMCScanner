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
	public static MainFrame frame;
	public static CameraPanel cameraPanel;
	public static SettingsPanel settingsPanel;
	public static DimentionPanel dimentionPanel;
	public static ConclutionPanel conclutionPanel;
	public static ImageScannedPanel imageScannedPanel;
	public static StartControlPanel startControlPanel;
	public static ManualPanel manualPanel;
	public static ImagePanel imagePanel;
	
	/* Image Icon next button */
	public static ImageIcon NEXT_BUTTON_ENABLED_IMAGE_ICON = new ImageIcon("image/ButtonBlueNext.png");
	public static ImageIcon NEXT_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonGrayNext.png");
	public static ImageIcon NEXT_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonBlueNextPrest.png");
	public static ImageIcon NEXT_BUTTON_GRAY_PREST_IMAGE_ICON = new ImageIcon("image/ButtonGrayNextPrest.png");
	public static Dimension NEXT_BUTTON_DIMENSION = new Dimension(90, 50);
	
	/* Import the images for the BACK button */
	public static ImageIcon BACK_BUTTON_ENABLED_IMAGE_ICON = new ImageIcon("image/ButtonBlueBack.png");
	public static ImageIcon BACK_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonBlueBackPrest.png");
	
	/* Used Colors */
	public static Color LIGHT_BLUE_COLOR = new Color(100,150,255); 
	public static Color RED_COLOR = new Color(255,0,0); 
	public static Color LIGHT_GREEN_COLOR = new Color(150,255,80); 
	public static Color DARK_GREEN_COLOR = new Color(120,200,40);
	public static Color LIGHT_GRAY_COLOR = new Color(120,120,120);

	/* Light blue border for the float input text field */
	public static Border LIGHT_BLUE_BORDER = BorderFactory.createLineBorder(LIGHT_BLUE_COLOR);
	public static Border GREEN_BORDER = BorderFactory.createLineBorder(LIGHT_GREEN_COLOR);
	public static Border RED_BORDER = BorderFactory.createLineBorder(RED_COLOR);

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
    	imagePanel = new ImagePanel();
    	
    	GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);

		//Show it the frame
		frame.setVisible(true);
        
	}
}
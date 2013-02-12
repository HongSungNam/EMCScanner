package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
/**
 * 
 * @author Jonas
 *
 */
public class SettingsPanel extends JPanel{
	/* Real life in table dimensions in one tenth of a millimeter that is taken in by the camera */
	static int TABLE_WIDTH = 19200; 			// Temporary
	static int TABLE_HEIGHT = 10800;			// Temporary
	public Dimension TABLE_DIMENSION = new Dimension(19200, 10800);
	
	private static int stage = 1;
	
	/* User selected Frequency values */
	public static boolean FREQUENCY_SELECTED = false;
	
	public static float FREQUENCY_START_SELECTED_VALUE;
	public static float FREQUENCY_END_SELECTED_VALUE;
	public static int FREQUENCY_DENSITY_SELECTED_VALUE;
	
	/* User selected Area Values */
	public static boolean AREA_SELECTED = false;
	public static float AREA_SELECTED_START_X;
	public static float AREA_SELECTED_START_Y;
	public static float AREA_SELECTED_END_X;
	public static float AREA_SELECTED_END_Y;
	
	public static Dimension AREA_SELECTED_CAMERA_DIMENSION;
	
	public static float AREA_SELECTED_IMAGE_DEPENDENT_START_X;
	public static float AREA_SELECTED_IMAGE_DEPENDENT_START_Y;
	public static float AREA_SELECTED_IMAGE_DEPENDENT_END_X;
	public static float AREA_SELECTED_IMAGE_DEPENDENT_END_Y;
	public static float AREA_SELECTED_IMAGE_DEPENDENT_WIDTH;
	public static float AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT;
	
	public static IplImage photo;
	
	/* User selected density values */
	public static boolean DENSITY_SELECTED = false;
	public static int DENSITY_SELECTED_WIDTH;
	public static int DENSITY_SELECTED_HEIGHT;
	
	public static int numberOfStepsWidth = 0;
	public static int numberOfStepsHeight = 0;
	public static int stepSizeHeight = 0;
	public static int stepSizeWidth = 0;
	
	/* User selected file name */
	public static boolean FILE_NAME_SELECTED
	= false;
	public static String FILE_NAME;
	
	/* Scan viable */
	public static final boolean SCAN_DONE = false;
	
	/* This class */
	/* Dimensions */
	public static Dimension PHOTO_VIEW_DIMENSION;
	public static Dimension CROPT_PHOTO_DIMENSION;
	
	/* Global values used by the program */
	public static FrequencySettingsSubPanel frequencyPanel;
	public static AreaSettingsSubPanel areaPanel;
	public static DensitySettingsSubPanel densityPanel;
	public static FileNameSettingsSubPanel fileNamePanel;
	public static ScanSettingsSubPanel scanPanel;
	
	/* Buttons */
	public JButton backButton = new JButton();
	
	/**
	 * SettingsPanel
	 * 
	 * 
	 */
	public SettingsPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		
		this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Program.LIGHT_GRAY_COLOR));
		
		/* Container made for GUI to be able to add the back button in the right corner */
		JPanel settingsContiner  = 	new JPanel();
		settingsContiner.setLayout(new BoxLayout(settingsContiner, BoxLayout.Y_AXIS));
		
		/* Creates the different sub panels for the settingsPanel */
		frequencyPanel = new FrequencySettingsSubPanel();
		areaPanel = new AreaSettingsSubPanel();
		densityPanel = new DensitySettingsSubPanel();
		fileNamePanel = new FileNameSettingsSubPanel();
		scanPanel = new ScanSettingsSubPanel();
		
		settingsContiner.add(frequencyPanel);
		settingsContiner.add(areaPanel);
		settingsContiner.add(densityPanel);
		settingsContiner.add(fileNamePanel);
		settingsContiner.add(scanPanel);
		
		/* Button made for going back to previous views */
		backButton.setEnabled(true);
		backButton.setPreferredSize(Program.BUTTON_DIMENSION);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new BackActionListener());
		
		/* Container made for setting backButton in the corner to the bottom */
		JPanel continer  = 	new JPanel();
		continer.setLayout(new BorderLayout());
		continer.add(backButton, BorderLayout.WEST);
		
		this.add(settingsContiner, BorderLayout.NORTH);
		this.add(continer, BorderLayout.SOUTH);
	}
	public static int getStage() {
		return stage;
	}
	public static void setStage(int stage) {
		SettingsPanel.stage = stage;
	}
}

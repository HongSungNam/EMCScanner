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
	private static int TABLE_WIDTH = 1920; 			// Temporary
	private static int TABLE_HEIGHT = 1080;			// Temporary
	private static int stage = 1;

	private Dimension TABLE_DIMENSION = new Dimension(1920, 1080);
	
	/* User selected Frequency values */
	private static boolean FREQUENCY_SELECTED = false;
	
	public static float frequencyStartUserSelectedFloat;
	public static float frequencyEndUserSelectedFloat;
	public static int frequencyDensityUserSelectedInt;
	
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
	public static boolean FILE_NAME_SELECTED = false;
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
	public static EndSubSettingsPanel endPanel;

	private Dimension settingsPanelDimension = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3), 0);
	
	/**
	 * SettingsPanel
	 * 
	 */
	public SettingsPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(settingsPanelDimension);
		
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
		endPanel = new EndSubSettingsPanel();
		
		settingsContiner.add(frequencyPanel);
		settingsContiner.add(areaPanel);
		settingsContiner.add(densityPanel);
		settingsContiner.add(fileNamePanel);
		settingsContiner.add(scanPanel);
		settingsContiner.add(endPanel);
		
		this.add(settingsContiner, BorderLayout.NORTH);
	}
	public static int getStage() {
		return stage;
	}
	public static void setStage(int stage) {
		SettingsPanel.stage = stage;
	}
	public static int getTABLE_WIDTH() {
		return TABLE_WIDTH;
	}
	public static void setTABLE_WIDTH(int tABLE_WIDTH) {
		TABLE_WIDTH = tABLE_WIDTH;
	}
	public static int getTABLE_HEIGHT() {
		return TABLE_HEIGHT;
	}
	public static void setTABLE_HEIGHT(int tABLE_HEIGHT) {
		TABLE_HEIGHT = tABLE_HEIGHT;
	}
	public Dimension getTABLE_DIMENSION() {
		return TABLE_DIMENSION;
	}
	public void setTABLE_DIMENSION(Dimension tABLE_DIMENSION) {
		TABLE_DIMENSION = tABLE_DIMENSION;
	}
	public static boolean isFREQUENCY_SELECTED() {
		return FREQUENCY_SELECTED;
	}
	public static void setFREQUENCY_SELECTED(boolean fREQUENCY_SELECTED) {
		FREQUENCY_SELECTED = fREQUENCY_SELECTED;
	}
}

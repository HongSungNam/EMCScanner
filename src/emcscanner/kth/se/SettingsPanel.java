package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
/**
 * 
 * @author Jonas
 *
 */
public class SettingsPanel extends JPanel{
	/**
	 * ID
	 */
	private static final long serialVersionUID = 5111348442320167815L;
	
	/* Real life in table dimensions in one tenth of a millimeter that is taken in by the camera */
	private static int TABLE_WIDTH = 1920; 			// Temporary
	private static int TABLE_HEIGHT = 1080;			// Temporary
	private static int stage = 1;

	private Dimension TABLE_DIMENSION = new Dimension(1920, 1080);
	
	/* User selected Frequency values */
	private static boolean FREQUENCY_SELECTED = false;
	
	private static float frequencyStartUserSelectedFloat;
	private static float frequencyEndUserSelectedFloat;
	private static int frequencyDensityUserSelectedInt;
	
	/* User selected Area Values */
	private static boolean AREA_SELECTED = false;
	private static float AREA_SELECTED_START_X;
	private static float AREA_SELECTED_START_Y;
	private static float AREA_SELECTED_END_X;
	private static float AREA_SELECTED_END_Y;
	
	public static Dimension AREA_SELECTED_CAMERA_DIMENSION;
	
	private static float AREA_SELECTED_IMAGE_DEPENDENT_START_X;
	private static float AREA_SELECTED_IMAGE_DEPENDENT_START_Y;
	private static float AREA_SELECTED_IMAGE_DEPENDENT_END_X;
	private static float AREA_SELECTED_IMAGE_DEPENDENT_END_Y;
	private static float AREA_SELECTED_IMAGE_DEPENDENT_WIDTH;
	private static float AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT;
	
	private static IplImage photo;
	
	/* User selected density values */
	private static boolean DENSITY_SELECTED = false;
	private static int DENSITY_SELECTED_WIDTH;
	private static int DENSITY_SELECTED_HEIGHT;
	
	private static int numberOfStepsWidth = 0;
	private static int numberOfStepsHeight = 0;
	private static int stepSizeHeight = 0;
	private static int stepSizeWidth = 0;
	
	/* User selected file name */
	private static boolean FILE_NAME_SELECTED = false;
	private static String FILE_NAME;
	
	/* Scan viable */
	private static final boolean SCAN_DONE = false;
	
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

	public static Dimension SUB_PANEL_MINIMUM_DIMENSION = new Dimension(400, 100);
	
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
	public static float getFrequencyStartUserSelectedFloat() {
		return frequencyStartUserSelectedFloat;
	}
	public static void setFrequencyStartUserSelectedFloat(
			float frequencyStartUserSelectedFloat) {
		SettingsPanel.frequencyStartUserSelectedFloat = frequencyStartUserSelectedFloat;
	}
	public static float getFrequencyEndUserSelectedFloat() {
		return frequencyEndUserSelectedFloat;
	}
	public static void setFrequencyEndUserSelectedFloat(
			float frequencyEndUserSelectedFloat) {
		SettingsPanel.frequencyEndUserSelectedFloat = frequencyEndUserSelectedFloat;
	}
	public static int getFrequencyDensityUserSelectedInt() {
		return frequencyDensityUserSelectedInt;
	}
	public static void setFrequencyDensityUserSelectedInt(
			int frequencyDensityUserSelectedInt) {
		SettingsPanel.frequencyDensityUserSelectedInt = frequencyDensityUserSelectedInt;
	}
	public static boolean isAREA_SELECTED() {
		return AREA_SELECTED;
	}
	public static void setAREA_SELECTED(boolean aREA_SELECTED) {
		AREA_SELECTED = aREA_SELECTED;
	}
	public static float getAREA_SELECTED_START_X() {
		return AREA_SELECTED_START_X;
	}
	public static void setAREA_SELECTED_START_X(float aREA_SELECTED_START_X) {
		AREA_SELECTED_START_X = aREA_SELECTED_START_X;
	}
	public static float getAREA_SELECTED_START_Y() {
		return AREA_SELECTED_START_Y;
	}
	public static void setAREA_SELECTED_START_Y(float aREA_SELECTED_START_Y) {
		AREA_SELECTED_START_Y = aREA_SELECTED_START_Y;
	}
	public static float getAREA_SELECTED_END_X() {
		return AREA_SELECTED_END_X;
	}
	public static void setAREA_SELECTED_END_X(float aREA_SELECTED_END_X) {
		AREA_SELECTED_END_X = aREA_SELECTED_END_X;
	}
	public static float getAREA_SELECTED_END_Y() {
		return AREA_SELECTED_END_Y;
	}
	public static void setAREA_SELECTED_END_Y(float aREA_SELECTED_END_Y) {
		AREA_SELECTED_END_Y = aREA_SELECTED_END_Y;
	}
	public static Dimension getAREA_SELECTED_CAMERA_DIMENSION() {
		return AREA_SELECTED_CAMERA_DIMENSION;
	}
	public static void setAREA_SELECTED_CAMERA_DIMENSION(
			Dimension aREA_SELECTED_CAMERA_DIMENSION) {
		AREA_SELECTED_CAMERA_DIMENSION = aREA_SELECTED_CAMERA_DIMENSION;
	}
	public static float getAREA_SELECTED_IMAGE_DEPENDENT_START_X() {
		return AREA_SELECTED_IMAGE_DEPENDENT_START_X;
	}
	public static void setAREA_SELECTED_IMAGE_DEPENDENT_START_X(
			float aREA_SELECTED_IMAGE_DEPENDENT_START_X) {
		AREA_SELECTED_IMAGE_DEPENDENT_START_X = aREA_SELECTED_IMAGE_DEPENDENT_START_X;
	}
	public static float getAREA_SELECTED_IMAGE_DEPENDENT_START_Y() {
		return AREA_SELECTED_IMAGE_DEPENDENT_START_Y;
	}
	public static void setAREA_SELECTED_IMAGE_DEPENDENT_START_Y(
			float aREA_SELECTED_IMAGE_DEPENDENT_START_Y) {
		AREA_SELECTED_IMAGE_DEPENDENT_START_Y = aREA_SELECTED_IMAGE_DEPENDENT_START_Y;
	}
	public static float getAREA_SELECTED_IMAGE_DEPENDENT_END_Y() {
		return AREA_SELECTED_IMAGE_DEPENDENT_END_Y;
	}
	public static void setAREA_SELECTED_IMAGE_DEPENDENT_END_Y(
			float aREA_SELECTED_IMAGE_DEPENDENT_END_Y) {
		AREA_SELECTED_IMAGE_DEPENDENT_END_Y = aREA_SELECTED_IMAGE_DEPENDENT_END_Y;
	}
	public static float getAREA_SELECTED_IMAGE_DEPENDENT_HEIGHT() {
		return AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT;
	}
	public static void setAREA_SELECTED_IMAGE_DEPENDENT_HEIGHT(
			float aREA_SELECTED_IMAGE_DEPENDENT_HEIGHT) {
		AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT = aREA_SELECTED_IMAGE_DEPENDENT_HEIGHT;
	}
	public static float getAREA_SELECTED_IMAGE_DEPENDENT_END_X() {
		return AREA_SELECTED_IMAGE_DEPENDENT_END_X;
	}
	public static void setAREA_SELECTED_IMAGE_DEPENDENT_END_X(
			float aREA_SELECTED_IMAGE_DEPENDENT_END_X) {
		AREA_SELECTED_IMAGE_DEPENDENT_END_X = aREA_SELECTED_IMAGE_DEPENDENT_END_X;
	}
	public static float getAREA_SELECTED_IMAGE_DEPENDENT_WIDTH() {
		return AREA_SELECTED_IMAGE_DEPENDENT_WIDTH;
	}
	public static void setAREA_SELECTED_IMAGE_DEPENDENT_WIDTH(
			float aREA_SELECTED_IMAGE_DEPENDENT_WIDTH) {
		AREA_SELECTED_IMAGE_DEPENDENT_WIDTH = aREA_SELECTED_IMAGE_DEPENDENT_WIDTH;
	}
	public static IplImage getPhoto() {
		return photo;
	}
	public static void setPhoto(IplImage photo) {
		SettingsPanel.photo = photo;
	}
	public static boolean isDENSITY_SELECTED() {
		return DENSITY_SELECTED;
	}
	public static void setDENSITY_SELECTED(boolean dENSITY_SELECTED) {
		DENSITY_SELECTED = dENSITY_SELECTED;
	}
	public static int getDENSITY_SELECTED_WIDTH() {
		return DENSITY_SELECTED_WIDTH;
	}
	public static void setDENSITY_SELECTED_WIDTH(int dENSITY_SELECTED_WIDTH) {
		DENSITY_SELECTED_WIDTH = dENSITY_SELECTED_WIDTH;
	}
	public static int getDENSITY_SELECTED_HEIGHT() {
		return DENSITY_SELECTED_HEIGHT;
	}
	public static void setDENSITY_SELECTED_HEIGHT(int dENSITY_SELECTED_HEIGHT) {
		DENSITY_SELECTED_HEIGHT = dENSITY_SELECTED_HEIGHT;
	}
	public static int getNumberOfStepsWidth() {
		return numberOfStepsWidth;
	}
	public static void setNumberOfStepsWidth(int numberOfStepsWidth) {
		SettingsPanel.numberOfStepsWidth = numberOfStepsWidth;
	}
	public static int getNumberOfStepsHeight() {
		return numberOfStepsHeight;
	}
	public static void setNumberOfStepsHeight(int numberOfStepsHeight) {
		SettingsPanel.numberOfStepsHeight = numberOfStepsHeight;
	}
	public static int getStepSizeHeight() {
		return stepSizeHeight;
	}
	public static void setStepSizeHeight(int stepSizeHeight) {
		SettingsPanel.stepSizeHeight = stepSizeHeight;
	}
	public static int getStepSizeWidth() {
		return stepSizeWidth;
	}
	public static void setStepSizeWidth(int stepSizeWidth) {
		SettingsPanel.stepSizeWidth = stepSizeWidth;
	}
	public static boolean isFILE_NAME_SELECTED() {
		return FILE_NAME_SELECTED;
	}
	public static void setFILE_NAME_SELECTED(boolean fILE_NAME_SELECTED) {
		FILE_NAME_SELECTED = fILE_NAME_SELECTED;
	}
	public static String getFILE_NAME() {
		return FILE_NAME;
	}
	public static void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public static boolean isScanDone() {
		return SCAN_DONE;
	}
}

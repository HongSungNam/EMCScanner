package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jonas
 *
 */
public class DensitySettingsSubPanel extends JPanel {
	/**
	 * Density Settings Sub Panel ID
	 */
	private static final long serialVersionUID = 2569252283514723108L;
	
	public static BufferedImage buffDensityImg = null;
	/* Creates a ColorPanel and adds it to this camera panel */
	public static ColorPanel colorDensityVideoPanel = new ColorPanel(buffDensityImg);
	/* Threads */
	public static Thread threadDisplayDensityVideo;
	
	/* Integers */
	public int MAX_LINES = 40;
	public int NUMBER_MAX_OF_LINES_WIDTH;
	public int NUMBER_MAX_OF_LINES_HEIGHT;
	public int NUMBER_OF_LINES_HEIGHT;
	public int NUMBER_OF_LINES_WIDTH;
	public static int STAGE = 3;
	
	/* Boolean */	
	public static boolean DISPLAY_HELP_VIDEO = false;
	public static boolean DISPLAY_VIDEO = true;
	public static boolean HEADER_BUTTON_ENABLED = false;
	public static boolean WIDTH_ENTERD_CORRECTLY = false;
	public static boolean HEIGHT_ENTERD_CORRECTLY = false;
	public static boolean VISIBLE = true;
	public static boolean NOT_VISIBLE = false;
	public static boolean inputStepBoolean = true;
	
	/* Buttons */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	public static NextButton nextButton 	= new NextButton(STAGE);
	public static BackButton backButton 	= new BackButton(STAGE);
	
	public static DensityButton densityMillimeter	= new DensityButton(1);
	public static DensityButton densityNumberOfSteps	= new DensityButton(1);
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 	= "Press to reselect the density";
	
	public String STEP_TEXT_GRAY	 			= "<html> <font color = rgb(120,120,120)>Step 3/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  		= "<html> <font color = rgb(100,150,255)>Step 3/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  		= "<html> <font color = rgb(120,200,40)>Step 3/4</font></html>";
	
	public String SCAN_DENSITY_LIGHT_BLUE_LABEL = "<html> <font color = rgb(100,150,255)>Scan density: </font></html>";
	public String SCAN_DENSITY_DARK_GREEN_LABEL	= "<html> <font color = rgb(120,200,40)>Scan density: </font></html>";
	public String SCAN_DENSITY_RED_LABEL 		= "<html> <font color = rgb(255,0,0)>Scan density: </font></html>";
	
	public String WIDTH_LIGHT_BLUE_LABEL 		= "<html> <font color = rgb(100,150,255)> Width: </font></html>";
	public String WIDTH_DARK_GREEN_LABEL 		= "<html> <font color = rgb(120,200,40)> Width: </font></html>";
	public String WIDTH_RED_LABEL 				= "<html> <font color = rgb(255,0,0)> Width: </font></html>";
	
	public String HEIGHT_LIGHT_BLUE_LABEL 		= "<html> <font color = rgb(100,150,255)> Height: </font></html>";
	public String HEIGHT_DARK_GREEN_LABEL 		= "<html> <font color = rgb(120,200,40)> Height: </font></html>";
	public String HEIGHT_RED_LABEL 				= "<html> <font color = rgb(255,0,0)> Height: </font></html>";
	
	public String NOTE_TEXT 					= "<html><p align=center><font color = rgb(255,0,0)> Note:</font> Higher density equals longer scanning process.</p></html>";
	
	/* Labels */
	public JLabel scanDensityLabel 				= new JLabel(SCAN_DENSITY_LIGHT_BLUE_LABEL);
	
	public JLabel widthLabel 			= new JLabel();
	public JLabel heightLabel 			= new JLabel();
	public JLabel widthLabel0 			= new JLabel();
	public JLabel heightLabel0 			= new JLabel();
	public JLabel widthLabelValue		= new JLabel();
	public JLabel heightLabelValue		= new JLabel();
	
	public JLabel densitySelectedLabel 	= new JLabel();
	public JLabel stepLabel 			= new JLabel(STEP_TEXT_GRAY);
	
	public JLabel noteLabel  			= new JLabel();
	
	/* Dimensions */
	public Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50, 40);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 360);
	public Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	
	public Dimension DENSITY_PANEL_DIMENSION_ACTIVE 			= new Dimension(322, 320);
	public Dimension DENSITY_PANEL_DIMENSION_DONE 				= new Dimension(322, 40);
	public Dimension HEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 360);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);

	public Dimension NOTE_LABEL_DIMENSION 						= new Dimension(200, 40);
	public Dimension INPUT_FEILDS_CONTAINER_DIMENSION 			= new Dimension(160, 85);
	public Dimension DENSITY_INPUT_TEXT_FIELD_DIMENSION 		= new Dimension(30, 20);
	
	
	public Dimension DENSITY_BUTTON_DIMENSION 					= new Dimension(40, 40);
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner 				  = new JPanel(new BorderLayout());
	public JPanel headerAndPanelContiner 	  = new JPanel(new BorderLayout());
	public JPanel densityPanel				  = new JPanel(new BorderLayout());
	public JPanel continer1 				  = new JPanel(new BorderLayout());

	private JPanel imputFeildsContainer  	  = new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer2  	  = new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer3  	  = new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer4  	  = new JPanel(new BorderLayout());
	private JPanel imputFeildsContainerValue1 = new JPanel(new BorderLayout());
	private JPanel imputFeildsContainerValue2 = new JPanel(new BorderLayout());
	private JPanel inputFeildsAButtons 		  = new JPanel(new BorderLayout());
	private JPanel densityButtonContainer 	  = new JPanel(new BorderLayout());
	
	/* JTextField */
	public DensityTextField heightDensityInputTextField = new DensityTextField();
	public DensityTextField widthDensityInputTextField  = new DensityTextField();
	
	public DensitySettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(SettingsPanel.SUB_PANEL_MINIMUM_DIMENSION);
		
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());

		/* warning note */
        noteLabel.setText(NOTE_TEXT);
        
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF);
		
		/* Panel for the frequency input */
		densityPanel.setPreferredSize(HEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF);
		
		threadDisplayDensityVideo = new FrameGrabberThread(STAGE, "densityVideo");
        threadDisplayDensityVideo.setDaemon(true);
        threadDisplayDensityVideo.start();
        
        /* Sets container backgrounds to white instead of gray for contrast */
        colorDensityVideoPanel.setBackground(Color.WHITE);
		continer1.setBackground(Color.WHITE);
		densityPanel.setBackground(Color.WHITE);
		densityButtonContainer.setBackground(Color.WHITE);
        imputFeildsContainer.setBackground(Color.WHITE);
        imputFeildsContainer2.setBackground(Color.WHITE);
        imputFeildsContainer3.setBackground(Color.WHITE);
        imputFeildsContainer4.setBackground(Color.WHITE);
        imputFeildsContainerValue1.setBackground(Color.WHITE);
        imputFeildsContainerValue2.setBackground(Color.WHITE);
		
		/* Sets proffered sizes for the right gui look*/
        noteLabel.setPreferredSize(NOTE_LABEL_DIMENSION);
        imputFeildsContainer.setPreferredSize(INPUT_FEILDS_CONTAINER_DIMENSION);
    	widthDensityInputTextField.setPreferredSize(DENSITY_INPUT_TEXT_FIELD_DIMENSION);
    	heightDensityInputTextField.setPreferredSize(DENSITY_INPUT_TEXT_FIELD_DIMENSION);
    	
    	densityButtonContainer.setPreferredSize(DENSITY_BUTTON_DIMENSION);
        
    	densityButtonContainer.add(densityMillimeter, BorderLayout.NORTH);
    	densityButtonContainer.add(densityNumberOfSteps, BorderLayout.SOUTH);
        
        imputFeildsContainer.add(noteLabel, BorderLayout.NORTH);
        
        imputFeildsContainer.add(scanDensityLabel, BorderLayout.WEST);
        imputFeildsContainer.add(densityButtonContainer, BorderLayout.CENTER);
        
        imputFeildsContainerValue1.add(widthLabel, BorderLayout.WEST);
        imputFeildsContainerValue1.add(widthLabelValue, BorderLayout.EAST);
        
        imputFeildsContainer2.add(imputFeildsContainerValue1, BorderLayout.WEST);
        imputFeildsContainer2.add(widthDensityInputTextField, BorderLayout.CENTER);
        imputFeildsContainer2.add(widthLabel0, BorderLayout.EAST);
        
        imputFeildsContainerValue2.add(heightLabel, BorderLayout.WEST);
        imputFeildsContainerValue2.add(heightLabelValue, BorderLayout.EAST);
        
        imputFeildsContainer3.add(imputFeildsContainerValue2, BorderLayout.WEST);
        imputFeildsContainer3.add(heightDensityInputTextField, BorderLayout.CENTER);
        imputFeildsContainer3.add(heightLabel0, BorderLayout.EAST);
        imputFeildsContainer4.add(imputFeildsContainer2, BorderLayout.NORTH);
        imputFeildsContainer4.add(imputFeildsContainer3, BorderLayout.SOUTH);
        imputFeildsContainer.add(imputFeildsContainer4, BorderLayout.EAST);
        
		continer1.add(nextButton, BorderLayout.EAST);
		continer1.add(backButton, BorderLayout.WEST);
		
        getInputFeildsAButtons().add(continer1, BorderLayout.SOUTH);
        getInputFeildsAButtons().add(imputFeildsContainer, BorderLayout.NORTH);

		densityPanel.add(getInputFeildsAButtons(), BorderLayout.SOUTH);
		densityPanel.add(colorDensityVideoPanel, BorderLayout.CENTER);
		
		densityPanel.add(densitySelectedLabel, BorderLayout.EAST);
		densitySelectedLabel.setVisible(NOT_VISIBLE);
		
		headerAndPanelContiner.add(densityPanel, BorderLayout.SOUTH);
		densityPanel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	public JPanel getInputFeildsAButtons() {
		return inputFeildsAButtons;
	}
	public void setInputFeildsAButtons(JPanel inputFeildsAButtons) {
		this.inputFeildsAButtons = inputFeildsAButtons;
	}
}

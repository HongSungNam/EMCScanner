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
	
	/**
	 * Density help video image.
	 */
	public static BufferedImage buffDensityImg = null;
	/**
	 * Creates a ColorPanel and the density video to it.
	 */
	public static ColorPanel colorDensityVideoPanel = new ColorPanel(buffDensityImg);
	
	/**
	 * Help density video thread.
	 */
	public static Thread threadDisplayDensityVideo;
	
	/**
	 * number of density lines horizontal.
	 */
	public int NUMBER_OF_LINES_HEIGHT;
	/**
	 * number of density lines vertical.
	 */
	public int NUMBER_OF_LINES_WIDTH;
	/**
	 * The stage of density panel is 3.
	 */
	public static int STAGE = 3;
	
	/* Boolean */
	/**
	 * True: Displaying the help video in density panel.<br>
	 * False: Do not display help video in density panel.
	 */
	public static boolean DISPLAY_HELP_VIDEO = false;
	/**
	 * True: Displaying the help video. <br>
	 * False: Not displaying the help video.
	 */
	public static boolean DISPLAY_VIDEO = true;
	/**
	 * True: Header button enabled and density panel active. <br>
	 * False: Header Button not active and enabled.
	 */
	public static boolean HEADER_BUTTON_ENABLED = false;
	/**
	 * True: Density width entered correctly and next button enabled if HEIGHT_ENTERD_CORRECTLY also true. <br>
	 * False: Density width entered incorrectly and next button disabled. 
	 */
	public static boolean WIDTH_ENTERD_CORRECTLY = false;
	/**
	 * True: Density height entered correctly and next button enabled if WIDTH_ENTERD_CORRECTLY also true. <br>
	 * False: Density height entered incorrectly and next button disabled. 
	 */
	public static boolean HEIGHT_ENTERD_CORRECTLY = false;
	/**
	 * Used for hiding and showing components when panel is active or inactive. <br>
	 * True: Visible <br>
	 * False: Not Visible
	 */
	public static boolean VISIBLE = true;
	/**
	 * Used for hiding and showing components when panel is active or inactive. <br>
	 * True: Not Visible <br> 
	 * False: Visible 
	 */
	public static boolean NOT_VISIBLE = false;
	/**
	 * True if user is using steps
	 * False if user is using millimeter.  
	 */
	public static boolean inputStepBoolean = true;
	
	/* Buttons */
	/** 
	 * Header Button is a button for the area panels header. <br>
	 * Clickable and has image icons on it.
	 */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	/** 
	 * Next Button is not be used for going to the next stage when enabled. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static NextButton nextButton = new NextButton(STAGE);
	/** 
	 * Back Button is not be used for going to the to the previous stage when enabled. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static BackButton backButton = new BackButton(STAGE);
	
	/** 
	 * Density millimeter steps activated when pressed. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static DensityButton densityMillimeter = new DensityButton(1);
	/** 
	 * Density number of steps activated when pressed. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static DensityButton densityNumberOfSteps = new DensityButton(2);
	
	/* Strings */
	/** 
	 * Used by the step label. <br>
	 * Color: Gray (Read Green Blue(120,120,120))<br>
	 * Text: Step 3/4
	 */
	public String STEP_TEXT_GRAY	 			= "<html> <font color = rgb(120,120,120)>Step 3/4</font></html>";
	/** 
	 * Used by the step label. <br>
	 * Color: Light Blue (Read Green Blue(100,150,255))<br>
	 * Text: Step 3/4
	 */
	public String STEP_TEXT_LIGHT_BLUE  		= "<html> <font color = rgb(100,150,255)>Step 3/4</font></html>";
	/** 
	 * Used by the step label. <br>
	 * Color: Dark Green (Read Green Blue(120,200,40))<br>
	 * Text: Step 3/4
	 */
	public String STEP_TEXT_DARK_GREEN  		= "<html> <font color = rgb(120,200,40)>Step 3/4</font></html>";
	
	/** 
	 * Used by the scanDensityLabel. <br>
	 * Color: Light Blue (Read Green Blue(100,150,255))<br>
	 * Scan density:
	 */
	public String SCAN_DENSITY_LIGHT_BLUE_LABEL = "<html> <font color = rgb(100,150,255)>Scan density: </font></html>";
	/** 
	 * Used by the scanDensityLabel. <br>
	 * Color: Dark Green (Read Green Blue(120,200,40))<br>
	 * Scan density:
	 */
	public String SCAN_DENSITY_DARK_GREEN_LABEL	= "<html> <font color = rgb(120,200,40)>Scan density: </font></html>";
	/** 
	 * Used by the scanDensityLabel. <br>
	 * Color: Red (Read Green Blue(255,0,0))<br>
	 * Scan density:
	 */
	public String SCAN_DENSITY_RED_LABEL 		= "<html> <font color = rgb(255,0,0)>Scan density: </font></html>";
	
	/** 
	 * Used by the width label. <br>
	 * Color: Light Blue (Read Green Blue(100,150,255))<br>
	 * Text: Width: 
	 */
	public String WIDTH_LIGHT_BLUE_LABEL 		= "<html> <font color = rgb(100,150,255)> Width: </font></html>";
	/** 
	 * Used by the width label. <br>
	 * Color: Dark Green (Read Green Blue(120,200,40))<br>
	 * Text: Width: 
	 */
	public String WIDTH_DARK_GREEN_LABEL 		= "<html> <font color = rgb(120,200,40)> Width: </font></html>";
	/** 
	 * Used by the width label. <br>
	 * Color: Red (Read Green Blue(255,0,0))<br>
	 * Text: Width: 
	 */
	public String WIDTH_RED_LABEL 				= "<html> <font color = rgb(255,0,0)> Width: </font></html>";
	
	/** 
	 * Used by the width label. <br>
	 * Color: Light Blue (Read Green Blue(100,150,255))<br>
	 * Text: Height: 
	 */
	public String HEIGHT_LIGHT_BLUE_LABEL 		= "<html> <font color = rgb(100,150,255)> Height: </font></html>";
	/** 
	 * Used by the width label. <br>
	 * Color: Dark Green (Read Green Blue(120,200,40))<br>
	 * Text: Height: 
	 */
	public String HEIGHT_DARK_GREEN_LABEL 		= "<html> <font color = rgb(120,200,40)> Height: </font></html>";
	/** 
	 * Used by the width label. <br>
	 * Color: Red (Read Green Blue(255,0,0))<br>
	 * Text: Height: 
	 */
	public String HEIGHT_RED_LABEL 				= "<html> <font color = rgb(255,0,0)> Height: </font></html>";
	
	/** 
	 * Warning for the user. <br>
	 * Text: Note:</font> Higher density equals longer scanning process.
	 */
	public String NOTE_TEXT 					= "<html><p align=center><font color = rgb(255,0,0)> Note:</font> Higher density equals longer scanning process.</p></html>";
	
	/* Labels */
	/** 
	 * Used ass a text label. <br>
	 * Text: Scan density:
	 */
	public JLabel scanDensityLabel 		= new JLabel(SCAN_DENSITY_LIGHT_BLUE_LABEL);
	/** 
	 * Used ass a text label. <br>
	 * Text: Width: 
	 */
	public JLabel widthLabel 			= new JLabel();
	/** 
	 * Used ass a text label. <br>
	 * Text: Height:
	 */
	public JLabel heightLabel 			= new JLabel();
	/** 
	 * Used ass a text label. <br>
	 * Text: &gt 0
	 */
	public JLabel widthLabel0 			= new JLabel();
	/** 
	 * Used ass a text label. <br>
	 * Text: &gt 0
	 */
	public JLabel heightLabel0 			= new JLabel();
	/** 
	 * Used ass a text label. <br>
	 * Text: (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1)
	 */
	public JLabel widthLabelValue		= new JLabel();
	/** 
	 * Used ass a text label. <br>
	 * Text: (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1)
	 */
	public JLabel heightLabelValue		= new JLabel();
	/** 
	 * Shows the selected density. <br>
	 * Text: User Based width and height.
	 */
	public JLabel densitySelectedLabel 	= new JLabel();
	/** 
	 * Shows what step the step panel is. <br>
	 * Text: Step 3/4
	 */
	public JLabel stepLabel 			= new JLabel(STEP_TEXT_GRAY);
	/** 
	 * Labels showing a warning. <br>
	 * Note: Higher density equals longer scanning process.
	 */
	public JLabel noteLabel  			= new JLabel(NOTE_TEXT);
	
	/* Dimensions */
	/** 
	 * When density panel is active step container is: <br>
	 * 50 Width, 360 Height 
	 */
	private Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 360);
	/** 
	 * When density panel is inactive but an area has been selected is: <br>
	 * 50 Width, 80 Height 
	 */
	private Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	/** 
	 * When density panel is inactive and an area has not been selected is: <br>
	 * 50 Width, 40 Height 
	 */
	private Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	/** 
	 * When density panel is active step container is: <br>
	 * 322 Width, 320 Height 
	 */
	private Dimension DENSITY_PANEL_DIMENSION_ACTIVE 			= new Dimension(322, 320);
	/** 
	 * When density panel is inactive but an area has been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	private Dimension DENSITY_PANEL_DIMENSION_DONE 				= new Dimension(322, 40);
	/**
	 * When header and density panel is inactive and an area has not been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	private Dimension HEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	/** 
	 * When density panel is active header and panel container is: <br>
	 * 322 Width, 360 Height 
	 */
	private Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 360);
	/** 
	 * When density panel is inactive but an area has been selected is: <br>
	 * 322 Width, 80 Height 
	 */
	private Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);

	/**
	 * The step Labels dimension used for showing what step we are on <br>
	 * 50 Width, 40 Height 
	 */
	private Dimension STEP_LABEL_DIMENSION 						= new Dimension(50, 40);
	
	/** 
	 * Note Labels dimension: <br>
	 * 200 Width, 40 Height 
	 */
	private Dimension NOTE_LABEL_DIMENSION 						= new Dimension(200, 40);
	/** 
	 * imputFeildsContainer: <br>
	 * 160 Width, 85 Height 
	 */
	private Dimension INPUT_FEILDS_CONTAINER_DIMENSION 			= new Dimension(160, 85);
	/** 
	 * When density panel is inactive but an area has been selected is: <br>
	 * 30 Width, 20 Height 
	 */
	private Dimension DENSITY_INPUT_TEXT_FIELD_DIMENSION 		= new Dimension(30, 20);
	
	/* Panels- Containers for setting up GUI */
	/**
	 * Container containing: <br>
	 * stepLabel					NORTH<br>
	 */
	public JPanel stepContiner 				  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * headerButton					NORTH<br>
	 */
	public JPanel headerAndPanelContiner 	  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * getInputFeildsAButtons		SOUTH<br>
	 * colorDensityVideoPanel		CENTER<br>
	 */
	public JPanel densityPanel				  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * nextButton					EAST<br>
	 * backButton					WEST<br>
	 */
	public JPanel buttonContainer 				  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * noteLabel					NORTH<br>
	 * scanDensityLabel				WEST<br>
	 * densityButtonContainer		CENTER<br>
	 * imputFeildsContainer4		EAST
	 */
	private JPanel imputFeildsContainer  	  = new JPanel(new BorderLayout());

	/**
	 * Container containing: <br>
	 * imputFeildsContainerValue1	WEST<br>
	 * widthDensityInputTextField	CENTER<br>
	 * widthLabel0					EAST<br>
	 */
	private JPanel imputFeildsContainer2  	  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * imputFeildsContainerValue2	WEST<br>
	 * heightDensityInputTextField	CENTER<br>
	 * heightLabel0					EAST<br>
	 */
	private JPanel imputFeildsContainer3  	  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * imputFeildsContainer2		NORTH<br>
	 * imputFeildsContainer3		SOUTH<br>
	 */
	private JPanel imputFeildsContainer4  	  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * widthLabel					WEST<br>
	 * widthLabelValue				EAST<br>
	 */
	private JPanel imputFeildsContainerValue1 = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * heightLabel					WEST<br>
	 * heightLabelValue				EAST<br>
	 */
	private JPanel imputFeildsContainerValue2 = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * buttonContainer				SOUTH<br>
	 * imputFeildsContainer			NORTH<br>
	 */
	private JPanel inputFeildsAButtons 		  = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * densityMillimeter			NORTH<br>
	 * densityNumberOfSteps			SOUTH<br>
	 */
	private JPanel densityButtonContainer 	  = new JPanel(new BorderLayout());
	
	/* JTextField */
	/**
	 * Input density field.
	 * Input: Height
	 */
	public DensityTextField heightDensityInputTextField = new DensityTextField();
	/**
	 * Input density field.
	 * Input: Width
	 */
	public DensityTextField widthDensityInputTextField  = new DensityTextField();
	
	/**
	 * Density Sub Panel GUI
	 */
	public DensitySettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(SettingsPanel.SUB_PANEL_MINIMUM_DIMENSION);
		
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());
        
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(getSTEP_CONTINER_DIMENSION_OFF());
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(getHEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF());
		
		/* Panel for the frequency input */
		densityPanel.setPreferredSize(getHEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF());
		
		threadDisplayDensityVideo = new FrameGrabberThread(STAGE, "densityVideo");
        threadDisplayDensityVideo.setDaemon(true);
        threadDisplayDensityVideo.start();
        
        /* Sets container backgrounds to white instead of gray for contrast */
        colorDensityVideoPanel.setBackground(Color.WHITE);
		buttonContainer.setBackground(Color.WHITE);
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
        
		buttonContainer.add(nextButton, BorderLayout.EAST);
		buttonContainer.add(backButton, BorderLayout.WEST);
		
        getInputFeildsAButtons().add(buttonContainer, BorderLayout.SOUTH);
        getInputFeildsAButtons().add(imputFeildsContainer, BorderLayout.NORTH);

		densityPanel.add(getInputFeildsAButtons(), BorderLayout.SOUTH);
		densityPanel.add(colorDensityVideoPanel, BorderLayout.CENTER);
		
		densityPanel.add(densitySelectedLabel, BorderLayout.EAST);
		densitySelectedLabel.setVisible(NOT_VISIBLE);
		
		headerAndPanelContiner.add(densityPanel, BorderLayout.SOUTH);
		densityPanel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getInputFeildsAButtons() {
		return inputFeildsAButtons;
	}
	/**
	 * 
	 * @param inputFeildsAButtons
	 */
	public void setInputFeildsAButtons(JPanel inputFeildsAButtons) {
		this.inputFeildsAButtons = inputFeildsAButtons;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSTEP_CONTINER_DIMENSION_ACTIVE() {
		return STEP_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @param sTEP_CONTINER_DIMENSION_ACTIVE
	 */
	public void setSTEP_CONTINER_DIMENSION_ACTIVE(
			Dimension sTEP_CONTINER_DIMENSION_ACTIVE) {
		STEP_CONTINER_DIMENSION_ACTIVE = sTEP_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getDENSITY_PANEL_DIMENSION_ACTIVE() {
		return DENSITY_PANEL_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @param dENSITY_PANEL_DIMENSION_ACTIVE
	 */
	public void setDENSITY_PANEL_DIMENSION_ACTIVE(
			Dimension dENSITY_PANEL_DIMENSION_ACTIVE) {
		DENSITY_PANEL_DIMENSION_ACTIVE = dENSITY_PANEL_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSTEP_CONTINER_DIMENSION_DONE() {
		return STEP_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @param sTEP_CONTINER_DIMENSION_DONE
	 */
	public void setSTEP_CONTINER_DIMENSION_DONE(
			Dimension sTEP_CONTINER_DIMENSION_DONE) {
		STEP_CONTINER_DIMENSION_DONE = sTEP_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getDENSITY_PANEL_DIMENSION_DONE() {
		return DENSITY_PANEL_DIMENSION_DONE;
	}
	/**
	 * 
	 * @param dENSITY_PANEL_DIMENSION_DONE
	 */
	public void setDENSITY_PANEL_DIMENSION_DONE(
			Dimension dENSITY_PANEL_DIMENSION_DONE) {
		DENSITY_PANEL_DIMENSION_DONE = dENSITY_PANEL_DIMENSION_DONE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE() {
		return HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @param hEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE
	 */
	public void setHEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE(
			Dimension hEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE) {
		HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = hEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_AND_PANEL_CONTINER_DIMENSION_DONE() {
		return HEADER_AND_PANEL_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @param hEADER_AND_PANEL_CONTINER_DIMENSION_DONE
	 */
	public void setHEADER_AND_PANEL_CONTINER_DIMENSION_DONE(
			Dimension hEADER_AND_PANEL_CONTINER_DIMENSION_DONE) {
		HEADER_AND_PANEL_CONTINER_DIMENSION_DONE = hEADER_AND_PANEL_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF() {
		return HEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF;
	}
	/**
	 * 
	 * @param hEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF
	 */
	public void setHEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF(
			Dimension hEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF) {
		HEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF = hEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSTEP_CONTINER_DIMENSION_OFF() {
		return STEP_CONTINER_DIMENSION_OFF;
	}
	/**
	 * 
	 * @param sTEP_CONTINER_DIMENSION_OFF
	 */
	public void setSTEP_CONTINER_DIMENSION_OFF(
			Dimension sTEP_CONTINER_DIMENSION_OFF) {
		STEP_CONTINER_DIMENSION_OFF = sTEP_CONTINER_DIMENSION_OFF;
	}
}

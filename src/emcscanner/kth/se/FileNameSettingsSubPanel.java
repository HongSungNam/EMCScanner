package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Jonas
 *
 */
public class FileNameSettingsSubPanel extends JPanel {

	/**
	 * File Name Settings Sub Panel ID
	 */
	private static final long serialVersionUID = 2183576868824285642L;
	
	/* Panels- Containers for setting up GUI */
	/** 
	 * Container containing the step label.
	 */
	public JPanel stepContiner 				= new JPanel(new BorderLayout());
	/** 
	 * Container containing fileNamePanel.
	 */
	public JPanel headerAndPanelContiner 	= new JPanel(new BorderLayout());
	/** 
	 * Container containing:<br>
	 * getInputFeildsAButtons		SOUTH<br>
	 * fileNameSelectedLabel		EAST
	 */
	public JPanel fileNamePanel 			= new JPanel(new BorderLayout());
	/** 
	 * Container containing:<br>
	 * backButton		WEST<br>
	 * nextButton		EAST
	 */
	public JPanel buttonsContainer 				= new JPanel(new BorderLayout());
	/** 
	 * Container containing:<br>
	 * noteLabel			NORTH<br>
	 * getInputContainer	SOUTH<br><br>
	 * getImputFeildsContainer <br>
	 * setImputFeildsContainer
	 */
	private JPanel imputFeildsContainer  	= new JPanel(new BorderLayout());
	/** 
	 * Container containing:<br>
	 * buttonsContainer			SOUTH<br>
	 * getImputFeildsContainer	NORTH<br><br>
	 * getInputFeildsAButtons <br>
	 * setInputFeildsAButtons
	 */
	private JPanel inputFeildsAButtons 		= new JPanel(new BorderLayout());
	/** 
	 * Container containing:<br>
	 * fileNameInputTextField<br><br>
	 * getInputContainer <br>
	 * setInputContainer
	 */
	private JPanel inputContainer 			= new JPanel();
	/**
	 * Input field for user to select a file name for the PDF.
	 */
	public FileNameTextField fileNameInputTextField = new FileNameTextField();
	/** 
	 * Stage of file name panel is 4.
	 */
	public static int STAGE = 4;
	
	/* Strings */
	/**
	 * Used by:stepLabel<br>
	 * Color: Gray (Read Green Blue(120,120,120))<br>
	 * Text:Step 4/4
	 */
	public String STEP_TEXT_GRAY	 							= "<html> <font color = rgb(120,120,120)>Step 4/4</font></html>";
	/**
	 * Used by:stepLabel<br>
	 * Color: Light Blue (Read Green Blue(100,150,255))<br>
	 * Text:Step 4/4
	 */
	public String STEP_TEXT_LIGHT_BLUE  						= "<html> <font color = rgb(100,150,255)>Step 4/4</font></html>";
	/**
	 * Used by:stepLabel<br>
	 * Color: Dark Green (Read Green Blue(120,200,40))<br>
	 * Text:Step 4/4
	 */
	public String STEP_TEXT_DARK_GREEN  						= "<html> <font color = rgb(120,200,40)>Step 4/4</font></html>";
	/**
	 * Used by: noteLabel <br>
	 * Text: Note: Write a name for the result file that will contain the scan result. <br>
	 * The file name shall not contain any of these characters ?, \\, /, *, &lt, &gt, :, \", |, _, -
	 */
	public String NOTE_TEXT										= "<html><p align=center><font color = rgb(255,0,0)> Note: " +
																  "</font>Write a name for the result file that will contain the scan result.<br>" + 
																  "<font color = rgb(255,0,0)>OBS!&nbsp</font>The file name shall not contain any of these characters ?, \\, /, *, &lt, &gt, :, \", |, _, -</align><html>";
	/**
	 * True: File name has been selected 
	 * False: File name has not been selected 
	 */
	public static boolean HEADER_BUTTON_ENABLED 				= false;
	
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

	/* Labels */
	/** 
	 * Label showing on what step you are on. <br>
	 * Step: 4 <br>
	 * Text: Step 4/4
	 */
	public JLabel stepLabel 									= new JLabel(STEP_TEXT_GRAY); 
	/** 
	 * Label showing what name the suer has selected when the file name is selected. <br>
	 * Text: User selected
	 */
	public JLabel fileNameSelectedLabel 						= new JLabel();
	/**
	 * Label showing a note to the user. 
	 * Text: Note: Write a name for the result file that will contain the scan result. 
	 * The file name shall not contain any of these characters ?, \\, /, *, <, >, :, \", |, _, -
	 */
	public JLabel noteLabel										= new JLabel(NOTE_TEXT);

	/* Dimensions */
	/** 
	 * When file name panel is active step container is: <br>
	 * 50 Width, 200 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 200);
	/** 
	 * When file name panel is inactive but an area has been selected is: <br>
	 * 50 Width, 80 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	/** 
	 * When file name panel is inactive and an area has not been selected is: <br>
	 * 50 Width, 40 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);

	/** 
	 * When file name panel is active step container is: <br>
	 * 322 Width, 160 Height 
	 */
	public Dimension FILE_NAME_PANEL_DIMENSION_ACTIVE 			= new Dimension(322, 160);
	/** 
	 * When file name panel is inactive but an area has been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	public Dimension FILE_NAME_DENSITY_PANEL_DIMENSION_DONE 	= new Dimension(322, 40);
	/**
	 * When file name panel is inactive and an area has not been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	public Dimension FILE_NAME_DENSITY_PANEL_DIMENSION_OFF 		= new Dimension(322, 40);
	
	/** 
	 * When file name panel is active header and panel container is: <br>
	 * 322 Width, 200 Height 
	 */
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 200);
	/** 
	 * When file name panel is inactive but an area has been selected is: <br>
	 * 322 Width, 80 Height 
	 */
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);
	/**
	 * When file name panel is inactive and an area has not been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF 	= new Dimension(322, 40);
	
	/**
	 * The step Labels dimension used for showing what step we are on <br>
	 * 50 Width, 40 Height 
	 */
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);
	/**
	 * Note for the user  <br>
	 * 322 Width, 80 Height 
	 */
	public Dimension NOTE_LABEL_DIMENSION					 	= new Dimension(322, 80);

	/**
	 * File Name GUI
	 * 
	 * Extends JPanel
	 */
	public FileNameSettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(SettingsPanel.SUB_PANEL_MINIMUM_DIMENSION);
		
		/* note label */
		noteLabel.setPreferredSize(NOTE_LABEL_DIMENSION);
				
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
		
		/* Panel for the frequency input */
		fileNamePanel.setPreferredSize(FILE_NAME_DENSITY_PANEL_DIMENSION_OFF);
        
		/* Setting containers Layouts for the right GUI look. */
		buttonsContainer.add(backButton, BorderLayout.WEST);
		buttonsContainer.add(nextButton, BorderLayout.EAST);
		
        getImputFeildsContainer().setBackground(Color.WHITE);
		buttonsContainer.setBackground(Color.WHITE);
		fileNamePanel.setBackground(Color.WHITE);
		getInputFeildsAButtons().setBackground(Color.WHITE);
		getInputContainer().setBackground(Color.WHITE);

		getInputContainer().add(fileNameInputTextField);
		
		getImputFeildsContainer().add(noteLabel, BorderLayout.NORTH);
        getImputFeildsContainer().add(getInputContainer(), BorderLayout.SOUTH);
        
        getInputFeildsAButtons().add(buttonsContainer, BorderLayout.SOUTH);
        getInputFeildsAButtons().add(getImputFeildsContainer(), BorderLayout.NORTH);
		fileNamePanel.add(getInputFeildsAButtons(), BorderLayout.SOUTH);
		fileNamePanel.add(fileNameSelectedLabel, BorderLayout.EAST);
		headerAndPanelContiner.add(fileNamePanel, BorderLayout.SOUTH);
		
		fileNamePanel.setVisible(false);
		fileNameSelectedLabel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * Container containing: <br>
	 * noteLabel NORTH <br>
	 * getInputContainer SOUTH <br>
	 * 
	 * @return imputFeildsContainer
	 */
	public JPanel getImputFeildsContainer() {
		return imputFeildsContainer;
	}
	/**
	 * Container containing: <br>
	 * noteLabel NORTH <br>
	 * getInputContainer SOUTH <br>
	 * 
	 * @param imputFeildsContainer
	 */
	public void setImputFeildsContainer(JPanel imputFeildsContainer) {
		this.imputFeildsContainer = imputFeildsContainer;
	}
	/**
	 * Container containing:<br>
	 * buttonsContainer SOUTH<br>
	 * getImputFeildsContainer NORTH
	 * 
	 * @return inputFeildsAButtons
	 */
	public JPanel getInputFeildsAButtons() {
		return inputFeildsAButtons;
	}
	/**
	 * Container containing:<br>
	 * buttonsContainer SOUTH<br>
	 * getImputFeildsContainer NORTH
	 * 
	 * @param inputFeildsAButtons
	 */
	public void setInputFeildsAButtons(JPanel inputFeildsAButtons) {
		this.inputFeildsAButtons = inputFeildsAButtons;
	}
	/**
	 * Container containing:<br>
	 * fileNameInputTextField<br>
	 * 
	 * @return inputContainer
	 */
	public JPanel getInputContainer() {
		return inputContainer;
	}
	/**
	 * Container containing:<br>
	 * fileNameInputTextField<br>
	 * 
	 * @param inputContainer
	 */
	public void setInputContainer(JPanel inputContainer) {
		this.inputContainer = inputContainer;
	}
}

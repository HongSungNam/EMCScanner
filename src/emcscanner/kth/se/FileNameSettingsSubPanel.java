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
	public JPanel stepContiner 				= new JPanel(new BorderLayout());
	public JPanel headerAndPanelContiner 	= new JPanel(new BorderLayout());
	public JPanel fileNamePanel 			= new JPanel(new BorderLayout());
	public JPanel continer1 				= new JPanel(new BorderLayout());

	private JPanel imputFeildsContainer  	= new JPanel(new BorderLayout());
	private JPanel inputFeildsAButtons 		= new JPanel(new BorderLayout());
	private JPanel inputContainer 			= new JPanel();
	
	/* JTextField */
	public FileNameTextField fileNameInputTextField = new FileNameTextField();
		
	/* Integers */
	public static int STAGE = 4;
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 					= "Press to reselect the file name ";
	
	public String STEP_TEXT_GRAY	 							= "<html> <font color = rgb(120,120,120)>Step 4/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  						= "<html> <font color = rgb(100,150,255)>Step 4/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  						= "<html> <font color = rgb(120,200,40)>Step 4/4</font></html>";
	
	public String NOTE_TEXT										= "<html><p align=center><font color = rgb(255,0,0)> Note: " +
																  "</font>Write a name for the result file that will contain the scan result.<br>" + 
																  "<font color = rgb(255,0,0)>OBS!&nbsp</font>The file name shall not contain eny of thes caracters ?, \\, /, *, &lt, &gt, :, \", |, _, -</align><html>";
	/* Boolean */
	public static boolean HEADER_BUTTON_ENABLED 				= false;
	
	/* Buttons */
	public static HeaderButton headerButton 					= new HeaderButton(STAGE);
	public static NextButton nextButton 						= new NextButton(STAGE);
	public static BackButton backButton 						= new BackButton(STAGE);

	/* Labels */
	public JLabel stepLabel 									= new JLabel(STEP_TEXT_GRAY); 
	public JLabel fileNameSelectedLabel 						= new JLabel();
	public JLabel noteLabel										= new JLabel(NOTE_TEXT);

	/* Dimensions */
	public Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 200);
	public Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	
	public Dimension FILE_NAME_PANEL_DIMENSION_ACTIVE 			= new Dimension(322, 160);
	public Dimension FILE_NAME_DENSITY_PANEL_DIMENSION_DONE 	= new Dimension(322, 40);
	public Dimension FILE_NAME_DENSITY_PANEL_DIMENSION_OFF 		= new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 200);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF 	= new Dimension(322, 40);

	public Dimension NOTE_LABEL_DIMENSION					 	= new Dimension(322, 80);

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
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		
        getImputFeildsContainer().setBackground(Color.WHITE);
		continer1.setBackground(Color.WHITE);
		fileNamePanel.setBackground(Color.WHITE);
		getInputFeildsAButtons().setBackground(Color.WHITE);
		getInputContainer().setBackground(Color.WHITE);

		getInputContainer().add(fileNameInputTextField);
		
		getImputFeildsContainer().add(noteLabel, BorderLayout.NORTH);
        getImputFeildsContainer().add(getInputContainer(), BorderLayout.SOUTH);
        
        getInputFeildsAButtons().add(continer1, BorderLayout.SOUTH);
        getInputFeildsAButtons().add(getImputFeildsContainer(), BorderLayout.NORTH);
		fileNamePanel.add(getInputFeildsAButtons(), BorderLayout.SOUTH);
		fileNamePanel.add(fileNameSelectedLabel, BorderLayout.EAST);
		headerAndPanelContiner.add(fileNamePanel, BorderLayout.SOUTH);
		
		fileNamePanel.setVisible(false);
		fileNameSelectedLabel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getImputFeildsContainer() {
		return imputFeildsContainer;
	}
	/**
	 * 
	 * @param imputFeildsContainer
	 */
	public void setImputFeildsContainer(JPanel imputFeildsContainer) {
		this.imputFeildsContainer = imputFeildsContainer;
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
	public JPanel getInputContainer() {
		return inputContainer;
	}
	/**
	 * 
	 * @param inputContainer
	 */
	public void setInputContainer(JPanel inputContainer) {
		this.inputContainer = inputContainer;
	}
}

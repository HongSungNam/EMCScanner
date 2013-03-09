package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
	public JPanel stepContiner 				= new JPanel();
	public JPanel headerAndPanelContiner 	= new JPanel();
	public JPanel fileNamePanel 			= new JPanel();
	public JPanel continer1 				= new JPanel();

	private JPanel imputFeildsContainer  	= new JPanel(new BorderLayout());
	private JPanel inputFeildsAButtons 		= new JPanel(new BorderLayout());
	private JPanel inputContainer 			= new JPanel();
	
	/* JTextField */
	public JTextField fileNameInputTextField = new JTextField(10);
		
	/* Integers */
	public int fileNameLengthLimit = 10;
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
	public Dimension THIS_MINIMUM_DIMENSION 					= new Dimension(400, 100);
	public Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);

	public Dimension INPUT_TEXT_FEILD_DIMENSION 				= new Dimension(20,20);
	
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
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);
		
		/* note label */
		noteLabel.setPreferredSize(NOTE_LABEL_DIMENSION);
				
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.setLayout(new BorderLayout());
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.setLayout(new BorderLayout());
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
		
		/* Panel for the frequency input */
		fileNamePanel.setLayout(new BorderLayout());
		fileNamePanel.setPreferredSize(FILE_NAME_DENSITY_PANEL_DIMENSION_OFF);
		
		/* INPUT field for width */
        fileNameInputTextField.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
        fileNameInputTextField.setDocument(new LengthRestrictedDocument(fileNameLengthLimit));
        fileNameInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
        fileNameInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
        char[] r = {'?', '\\', '/', '*', '<',':', '>','"','|','_', '-'};
        fileNameInputTextField.addKeyListener(new InvalidCharListener(r));
        
        fileNameInputTextField.getDocument().addDocumentListener(new DocumentListener () {
			public void insertUpdate(DocumentEvent aEvent) {
				checkInt();
		    }
		    public void removeUpdate(DocumentEvent aEvente) {
		    	checkInt();
		    }
		    public void changedUpdate(DocumentEvent aEvent) {
		    	checkInt();
		    }
		    public void checkInt()        
		    {
		    	String name = fileNameInputTextField.getText();
		    	if (name.isEmpty())
		    	{
		    		SettingsPanel.setFILE_NAME_SELECTED(false);
		    		nextButton.setEnabled(false);
		    	}
		    	else
		    	{
		    		nextButton.setEnabled(true);

		    		SettingsPanel.setFILE_NAME(name);
		    	}
		    }
		});

		
		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
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
	
	public JPanel getImputFeildsContainer() {
		return imputFeildsContainer;
	}
	public void setImputFeildsContainer(JPanel imputFeildsContainer) {
		this.imputFeildsContainer = imputFeildsContainer;
	}
	public JPanel getInputFeildsAButtons() {
		return inputFeildsAButtons;
	}
	public void setInputFeildsAButtons(JPanel inputFeildsAButtons) {
		this.inputFeildsAButtons = inputFeildsAButtons;
	}
	public JPanel getInputContainer() {
		return inputContainer;
	}
	public void setInputContainer(JPanel inputContainer) {
		this.inputContainer = inputContainer;
	}
}

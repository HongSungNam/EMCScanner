package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FileNameSettingsSubPanel extends JPanel {

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
	
	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 				= new ImageIcon("image/PanelGreenFileName.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 		= new ImageIcon("image/PanelGreenFileNameRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 			= new ImageIcon("image/PanelGrayFileName.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 			= new ImageIcon("image/PanelGreenFileNamePrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 			= new ImageIcon("image/PanelBlueFileName.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 		= new ImageIcon("image/PanelDarkGreenFileName.png"); 
	
	/* Integers */
	public int fileNameLengthLimit = 10;
	public int STAGE = 4;
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 					= "Press to reselect the file name ";
	public String PANEL_TOOL_TIP_TEXT 							= "This is where you select the file name you want to save the result as.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 					= "You need to select an a valid file name before you can continue";
	
	public String STEP_TEXT_GRAY	 							= "<html> <font color = rgb(120,120,120)>Step 4/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  						= "<html> <font color = rgb(100,150,255)>Step 4/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  						= "<html> <font color = rgb(120,200,40)>Step 4/4</font></html>";

	public String NOTE_TEXT										= "<html><p align=center><font color = rgb(255,0,0)> Note: </font>Write a name for the result file that will contain the scan result.<br>"
																+ "<font color = rgb(255,0,0)>OBS!&nbsp</font>The file name shall not contain eny of thes caracters ?, \\, /, *, &lt, &gt, :, \", |, _, -</align><html>";
	/* Boolean */
	public static boolean HEADER_BUTTON_ENABLED 				= false;
	
	/* Buttons */
	public static JButton headerButton 							= new JButton();
	public static JButton nextButton 							= new JButton();
	public static JButton backButton 							= new JButton();

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
		
		/* Sets creation values for the header button */
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setPreferredSize(HEADER_BUTTON_DIMENSION);
		headerButton.setToolTipText(PANEL_TOOL_TIP_TEXT);
		headerButton.setOpaque(false);
		headerButton.setContentAreaFilled(false);
		headerButton.setBorderPainted(false);
		headerButton.setIcon(HEADER_ENABLED_IMAGE_ICON);
		headerButton.setDisabledIcon(HEADER_DISABLED_GRAY_IMAGE_ICON);
		headerButton.setPressedIcon(HEADER_ENABLED_PREST_IMAGE_ICON);
		headerButton.setRolloverIcon(HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		headerButton.addActionListener(new HeaderButtonActionListener(this.STAGE));
		
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
		
		/* Next JButton */
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText(NEXT_BUTTON_TOOL_TIP_TEXT);
		nextButton.setPreferredSize(Program.BUTTON_DIMENSION);
		nextButton.setEnabled(false);
		nextButton.setIcon(Program.NEXT_BUTTON_ENABLED_IMAGE_ICON);
		nextButton.setDisabledIcon(Program.NEXT_BUTTON_DISABLED_IMAGE_ICON);
		nextButton.setPressedIcon(Program.NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
		nextButton.setDisabledSelectedIcon(Program.NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		nextButton.addActionListener(new NextActionListener());
		
		/* Back on step JButton */
		backButton.setEnabled(true);
		backButton.setPreferredSize(Program.BUTTON_DIMENSION);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new BackActionListener());
		
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
		    		SettingsPanel.FILE_NAME_SELECTED = false;
		    		nextButton.setEnabled(false);
		    	}
		    	else
		    	{
		    		nextButton.setEnabled(true);

		    		SettingsPanel.FILE_NAME = name;
		    	}
		    }
		});

		
		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		
        imputFeildsContainer.setBackground(Color.WHITE);
		continer1.setBackground(Color.WHITE);
		fileNamePanel.setBackground(Color.WHITE);
		inputFeildsAButtons.setBackground(Color.WHITE);
		inputContainer.setBackground(Color.WHITE);

		inputContainer.add(fileNameInputTextField);
		
		imputFeildsContainer.add(noteLabel, BorderLayout.NORTH);
        imputFeildsContainer.add(inputContainer, BorderLayout.SOUTH);
        
        inputFeildsAButtons.add(continer1, BorderLayout.SOUTH);
        inputFeildsAButtons.add(imputFeildsContainer, BorderLayout.NORTH);
		fileNamePanel.add(inputFeildsAButtons, BorderLayout.SOUTH);
		fileNamePanel.add(fileNameSelectedLabel, BorderLayout.EAST);
		headerAndPanelContiner.add(fileNamePanel, BorderLayout.SOUTH);
		
		fileNamePanel.setVisible(false);
		fileNameSelectedLabel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * ACTIVE
	 */
	public void fileNamePanelActive() {
		
		MainPanel.setLeftStage(Program.imagePanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(false);
		Program.imagePanel.setVisible(true);
		
		SettingsPanel.setStage(this.STAGE);
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);
		
		
		/* Sets active color Blue for header, labels and borders*/
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setDisabledIcon(HEADER_DISABLED_BLUE_IMAGE_ICON);
		stepLabel.setText(STEP_TEXT_LIGHT_BLUE);
		fileNamePanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Shows buttons and labels */
		nextButton.setVisible(true);
		backButton.setVisible(true);
		fileNameInputTextField.setVisible(true);
		imputFeildsContainer.setVisible(true);
		inputFeildsAButtons.setVisible(true);
		inputContainer.setVisible(true);
		
		/* Changing size of panels when button has been pressed*/	
		fileNamePanel.setPreferredSize(FILE_NAME_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Turns on Panel */
		fileNamePanel.setVisible(true);
		
		/* Doesn't show label */
		fileNameSelectedLabel.setVisible(false);
		
	}
	/**
	 * NOT ACTIVE
	 */
	public void fileNamePanelNotActive(){		
		/* Sets header button to enabled and green with a new tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
			
		/* Sets video and buttons not visible */
		nextButton.setVisible(false);
		backButton.setVisible(false);
		fileNameInputTextField.setVisible(false);
		imputFeildsContainer.setVisible(false);
		inputFeildsAButtons.setVisible(false);
		inputContainer.setVisible(false);
		
		if (SettingsPanel.FILE_NAME_SELECTED)
		{
			/* Sets step label green when button has been pressed */
			stepLabel.setText(STEP_TEXT_DARK_GREEN);

			/* Sets density panel to visible */
			fileNamePanel.setVisible(true);
			
			/* AreaPanel and header Green */
			fileNamePanel.setBorder(Program.GREEN_BORDER);
			fileNamePanel.setVisible(true);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			fileNamePanel.setPreferredSize(FILE_NAME_DENSITY_PANEL_DIMENSION_DONE);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_DONE);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_DONE);
			
			fileNameSelectedLabel.setText("<html><font color = rgb(120,200,40)>Choosen file name: </font>" + fileNameInputTextField.getText() + "&nbsp</html>");
			
			fileNameSelectedLabel.setVisible(true);
		}
		else
		{
			/* Sets step label gray when button has been pressed */
			stepLabel.setText(STEP_TEXT_GRAY);
			
			/* Sets density panel to invisible */
			fileNamePanel.setVisible(false);
			
			/* Sets Header button gray */
			headerButton.setDisabledIcon(HEADER_DISABLED_GRAY_IMAGE_ICON);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
			
			/* Changing size of panels when button has been pressed*/	
			fileNamePanel.setPreferredSize(FILE_NAME_DENSITY_PANEL_DIMENSION_OFF);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		}
	}
}

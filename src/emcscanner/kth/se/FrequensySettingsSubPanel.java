package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.googlecode.javacv.FrameGrabber.Exception;
/**
 * 
 * @author Jonas
 *
 */
public class FrequensySettingsSubPanel extends JPanel{

	/* Boolean */
	public static boolean FREQUENCY_SELECTED = false;
	
	/* Panels */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel frequencyPanel = new JPanel();
	
	/* Buttons */
	public JButton headerButton = new JButton();
	public static JButton nextButton = new JButton();
	
	/* JTextField */
	public JTextField floatInputTextField = new JTextField(4);
	
	/* String */
	public String STEP_TEXT_GRAY = "<html> <font color = rgb(120,120,120)>Step 1/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 1/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 1/4</font></html>";
	
	public String PANEL_INFORMATION = "This is where you can shoose the desired frequency.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT = "You need to write a number between 0.1 and 6000 befor you can continue";
	
	public String HEADER_BUTTON_TOOL_TIP_TEXT = "Press to reselect The frequency";
	
	public String TEXT_NOTE_NORMAL_TEXT = "<html> <font color = rgb(255, 0, 0)> Note</font>: The signal generator’s " + 
		 	 						   "<br>" + " Intervall is 0.1 – 6000 MHz. </html>";
	public String TEXT_MORE_THEN_NORMAL_TEXT = "<html>0.1 ≤ </html>";
	public String TEXT_LESS_THEN_NORMAL_TEXT = "<html><font color = rgb(100,150,255)>MHz</font>"+" ≤ 6000</html>";
	public String TEXT_MORE_THEN_RED_TEXT = "<html><font color = rgb(255,0,0)>0.1 ≤</font></html>";
	public String TEXT_LESS_THEN_RED_TEXT = "<html><font color = rgb(100,150,255)>MHz</font>"+"<font color = rgb(255,0,0)> ≤ 6000</font></html>";

	/* JLabel */
	public JLabel stepLabel = new JLabel(STEP_TEXT_LIGHT_BLUE);
	
	public JLabel textNote  = new JLabel(TEXT_NOTE_NORMAL_TEXT);
	public JLabel textMoreThen = new JLabel(TEXT_MORE_THEN_NORMAL_TEXT);
	public JLabel textLessThen = new JLabel(TEXT_LESS_THEN_NORMAL_TEXT);

	public JLabel frequencyLabel = new JLabel();
	
	/* Imports the different images for the different button stages. */	
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/PanelGreenFrequency.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON = new ImageIcon("image/PanelGreenFrequencyRollover.png");
	public ImageIcon HEADER_DISABLED_IMAGE_ICON 		= new ImageIcon("image/PanelBlueFrequency.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON	= new ImageIcon("image/PanelGreenFrequencyPrest.png");
	public ImageIcon HEADER_DISABLED_RED_IMAGE_ICON		= new ImageIcon("image/PanelRedFrequency.png");
	
	public ImageIcon NECT_BUTTON_ENABLED_ICON			= new ImageIcon("image/ButtonBlueNext.png");
	public ImageIcon NEXT_BUTTON_DISABLED_ICON 			= new ImageIcon("image/ButtonGrayNext.png");
	public ImageIcon NEXT_BUTTON_BLUE_NEXT_PREST_ICON	= new ImageIcon("image/ButtonBlueNextPrest.png");
	public ImageIcon NEXT_BUTTON_GRAY_NEXT_PREST_ICON	= new ImageIcon("image/ButtonGrayNextPrest.png");
	
	/* Used Colors */
	public static Color LIGHT_BLUE_COLOR = new Color(100,150,255); 
	public static Color RED_COLOR = new Color(255,0,0); 
	public static Color LIGHT_GREEN_COLOR = new Color(150,255,80); 
	public static Color DARK_GREEN_COLOR = new Color(120,200,40);
	public static Color LIGHT_GRAY_COLOR = new Color(120,120,120);
	
	/* Light blue border for the float input text field */
	public Border LIGHT_BLUE_BORDER = BorderFactory.createLineBorder(LIGHT_BLUE_COLOR);
	public Border RED_BORDER = BorderFactory.createLineBorder(RED_COLOR);
	public Border GREEN_BORDER = BorderFactory.createLineBorder(LIGHT_GREEN_COLOR);
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);
	
	public Dimension STEP_CONTINER_DIMENSION_ON = new Dimension(50, 180);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 80);
	
	public Dimension FREQUENCY_PANEL_DIMENSION_ON = new Dimension(322, 140);
	public Dimension FREQUENCY_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ON = new Dimension(322, 180);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 80);
	
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension NEXT_BUTTON_DIMENSION = new Dimension(90, 50);
	public Dimension FLOAT_INPUT_TEXT_FEILD_DIMENSION = new Dimension(20,20);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	/**
	 * 
	 */
	public FrequensySettingsSubPanel(){
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);
		
		/* Sets creation values for the header button */
		this.headerButton.setEnabled(false);
		this.headerButton.setPreferredSize(HEADER_BUTTON_DIMENSION);
		this.headerButton.setToolTipText(PANEL_INFORMATION);
		this.headerButton.setOpaque(false);
		this.headerButton.setContentAreaFilled(false);
		this.headerButton.setBorderPainted(false);
		this.headerButton.setIcon(HEADER_ENABLED_IMAGE_ICON);
		this.headerButton.setDisabledIcon(HEADER_DISABLED_IMAGE_ICON);
		this.headerButton.setPressedIcon(HEADER_ENABLED_PREST_IMAGE_ICON);
		this.headerButton.setRolloverIcon(HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		this.headerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Sets header back to blue */
	    		headerButton.setToolTipText(PANEL_INFORMATION);
	    		headerButton.setEnabled(false);
				
	    		/* Turns on */ 
	    		frequencyPanel.setBorder(LIGHT_BLUE_BORDER);
				floatInputTextField.setVisible(true);
				textNote.setVisible(true);
				textMoreThen.setVisible(true);
				textLessThen.setVisible(true);
				nextButton.setVisible(true);
				stepLabel.setText(STEP_TEXT_LIGHT_BLUE);
	    		frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_ON);
				headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ON);
				stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ON);
				
				/* Turns off */
				frequencyLabel.setVisible(false);
				FREQUENCY_SELECTED = false;
				
				/* Removing next step */
				SettingsPanel.areaPanel.headerAndPanelContiner.remove(SettingsPanel.areaPanel.areaPanel);
				SettingsPanel.areaPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_GRAY_IMAGE_ICON);
				SettingsPanel.areaPanel.stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_GRAY);
				
				AreaSettingsSubPanel.DISPLAY_AREA_HELP_VIDEO = false;

				/* Don´t show the selected area */
				Program.frame.GET_AREA_BOOLEAN = false;
				Program.frame.glass.setVisible(false);
				Program.frame.MOUSE_RELEASED_BOOLEAN = false;
				Program.settingsPanel.areaPanel.nextButton.setEnabled(false);
			}
		});
		
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.setLayout(new BorderLayout());
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ON);
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.setLayout(new BorderLayout());
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ON);
		
		/* Constants for the FrequencyPanel */
		int frequencyInputLimit = 6;
		
		/* Panel for the frequency input */
		frequencyPanel.setLayout(new BorderLayout());
		frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_ON);
		
		/* Warning Label with text centered */
		textNote.setHorizontalAlignment(SwingConstants.CENTER);
		
		/* Boundary explanatory labels */
		textMoreThen.setHorizontalAlignment(SwingConstants.CENTER);
		textLessThen.setHorizontalAlignment(SwingConstants.CENTER);
		
		/* Next JButton */
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText(NEXT_BUTTON_TOOL_TIP_TEXT);
		nextButton.setPreferredSize(NEXT_BUTTON_DIMENSION);
		nextButton.setEnabled(false);
		nextButton.setIcon(NECT_BUTTON_ENABLED_ICON);
		nextButton.setDisabledIcon(NEXT_BUTTON_DISABLED_ICON);
		nextButton.setPressedIcon(NEXT_BUTTON_BLUE_NEXT_PREST_ICON);
		nextButton.setDisabledSelectedIcon(NEXT_BUTTON_GRAY_NEXT_PREST_ICON);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Sets the frequency that have been selected to SettingsPanels global variables */
				SettingsPanel.FREQUENCY = Float.valueOf(floatInputTextField.getText());
				
				/* Sets header button to enabled and green with a new tool tip */
				headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
				headerButton.setEnabled(true);
				
				frequencyPanel.setBorder(GREEN_BORDER);

				/* Sets step button, text and text input not visible when when next button has been prest*/
				floatInputTextField.setVisible(false);
				textNote.setVisible(false);
				textMoreThen.setVisible(false);
				textLessThen.setVisible(false);
				nextButton.setVisible(false);
				
				/* Sets step Label green when button has been prest*/
				stepLabel.setText(STEP_TEXT_DARK_GREEN);
				
				/* Changing size of panels when button has been prest*/	
				frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_OFF);
				headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
				stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
				
				/* Label that shows the frequency that the user has selected */
				frequencyLabel.setText("<html><font color = rgb(120,200,40)>Selected frequency: </font>" + SettingsPanel.FREQUENCY + " MHz</html>");
				frequencyLabel.setVisible(true);
				frequencyPanel.add(frequencyLabel, BorderLayout.EAST);
				
				FREQUENCY_SELECTED = true;
				
				/* Adding the next step */
				SettingsPanel.areaPanel.headerAndPanelContiner.add(SettingsPanel.areaPanel.areaPanel, BorderLayout.SOUTH);
				SettingsPanel.areaPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				SettingsPanel.areaPanel.stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_LIGHT_BLUE);

				AreaSettingsSubPanel.DISPLAY_AREA_HELP_VIDEO = true;
				
				/* Glass Panel at the cameraPanel */
				//Program.frame.glass.setVisible(true);
				Program.frame.GET_AREA_BOOLEAN = true;
			}
		});
		
		/* Text field for importing frequency from user only values from 0.1 to 6000 */
		floatInputTextField.setPreferredSize(FLOAT_INPUT_TEXT_FEILD_DIMENSION);
		floatInputTextField.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		floatInputTextField.setBorder(LIGHT_BLUE_BORDER);
		floatInputTextField.getDocument().addDocumentListener(new DocumentListener () {
			public void insertUpdate(DocumentEvent aEvent) {
				checkFloat();
		    }
		    public void removeUpdate(DocumentEvent aEvente) {
		    	checkFloat();
		    }
		    public void changedUpdate(DocumentEvent aEvent) {
		    	checkFloat();
		    }
		    public void checkFloat(){
		    	try{
		    		float value = Float.valueOf(floatInputTextField.getText());
					if ((0.1 <= value) && (value <= 6000))
			    	{
						/* Sets next button enabled values */
			    		nextButton.setEnabled(true);
			    		
			    		/* Sets header button enabled values */
			    		headerButton.setDisabledIcon(HEADER_DISABLED_IMAGE_ICON);
			    		headerButton.setEnabled(false);
			    		
			    		/* Set borders light blue when enabled */
						frequencyPanel.setBorder(LIGHT_BLUE_BORDER);
						floatInputTextField.setBorder(LIGHT_BLUE_BORDER);
						
						/* Changes text back to normal wrong values have been entered before */
						textMoreThen.setText(TEXT_MORE_THEN_NORMAL_TEXT);
						textLessThen.setText(TEXT_LESS_THEN_NORMAL_TEXT);
			    	}
					else{
						/* Sets next button disabled values have been entered */
						nextButton.setEnabled(false);
						
						/* Sets header button disabled values have been entered */
						headerButton.setDisabledIcon(HEADER_DISABLED_RED_IMAGE_ICON);
						headerButton.setEnabled(false);
						
						/* Set borders red when wrong values have been entered */
						frequencyPanel.setBorder(RED_BORDER);
						floatInputTextField.setBorder(RED_BORDER);
						
						/* wrong values have been entered changes the text so user knows what they have entered wrongly */
						if (0.1 > value)
				    	{
							textMoreThen.setText(TEXT_MORE_THEN_RED_TEXT);
				    	}
						if (6000 < value)
				    	{
							textLessThen.setText(TEXT_LESS_THEN_RED_TEXT);
				    	}
					}
		    	} catch (NumberFormatException e) {		    		
		    		/* Sets next button disabled when wrong values have been entered */
					nextButton.setEnabled(false);
					
					/* Sets header button disabled values have been entered */
					headerButton.setDisabledIcon(HEADER_DISABLED_RED_IMAGE_ICON);
					headerButton.setEnabled(false);
					
					/* Set borders red when wrong values have been entered */
					frequencyPanel.setBorder(RED_BORDER);
					floatInputTextField.setBorder(RED_BORDER);
		    	}
		    }
		});
		
		
		
		/* Containers for setting up GUI */
		JPanel continer1 = new JPanel();
		JPanel continer2 = 	new JPanel();
		JPanel continer3 = 	new JPanel();
		JPanel continer4 = 	new JPanel();
		JPanel continer5 = 	new JPanel();
		
		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer2.setLayout(new BorderLayout());
		continer3.setLayout(new FlowLayout());
		continer4.setLayout(new FlowLayout());
		continer5.setLayout(new BorderLayout());
		
		/* Sets container backgrounds to white instead of gray for contrast */
		continer1.setBackground(Color.WHITE);
		continer2.setBackground(Color.WHITE);
		continer3.setBackground(Color.WHITE);
		continer4.setBackground(Color.WHITE);
		continer5.setBackground(Color.WHITE);
		frequencyPanel.setBackground(Color.WHITE);
		
		/* Containers for GUI look */
		continer1.add(nextButton, BorderLayout.EAST);
		continer2.add(textNote, BorderLayout.CENTER);
		continer3.add(floatInputTextField, BorderLayout.CENTER);
		continer4.add(textMoreThen, FlowLayout.LEFT);
		continer4.add(continer3, FlowLayout.CENTER); 
		continer4.add(textLessThen, FlowLayout.RIGHT);
		continer5.add(continer2, BorderLayout.NORTH);
		continer5.add(continer4, BorderLayout.CENTER);
		frequencyPanel.add(continer5, BorderLayout.NORTH);
		frequencyPanel.add(continer1, BorderLayout.SOUTH);
		
		frequencyPanel.setBorder(LIGHT_BLUE_BORDER);
		
		headerAndPanelContiner.add(frequencyPanel, BorderLayout.EAST);
		
		this.add(headerAndPanelContiner);
	}
}

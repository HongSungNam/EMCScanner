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
	public static boolean WRONG_FLOAT_INPUT = false;
	public static boolean NEXT_BUTTON_ENABLED = false;
	
	/* Panels- Containers for setting up GUI */
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
	
	/* Constants for the FrequencyPanel */
	public int frequencyInputLimit = 6;

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
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 180);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 80);
	
	public Dimension FREQUENCY_PANEL_DIMENSION_ACTIVE = new Dimension(322, 140);
	public Dimension FREQUENCY_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 180);
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
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaSelectionNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();

				MainPanel.setLeftStage(Program.cameraPanel);
			}
		});
		
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.setLayout(new BorderLayout());
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.setLayout(new BorderLayout());
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		
		/* Panel for the frequency input */
		frequencyPanel.setLayout(new BorderLayout());
		frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_ACTIVE);
		
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
		nextButton.setPreferredSize(Program.BUTTON_DIMENSION);
		nextButton.setEnabled(false);
		nextButton.setIcon(Program.NEXT_BUTTON_ENABLED_IMAGE_ICON);
		nextButton.setDisabledIcon(Program.NEXT_BUTTON_DISABLED_IMAGE_ICON);
		nextButton.setPressedIcon(Program.NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
		nextButton.setDisabledSelectedIcon(Program.NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsPanel.FREQUENCY_SELECTED = true;
				/* Adding the next step */
				SettingsPanel.areaPanel.areaSelectionActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			}
		});
		
		/* Text field for importing frequency from user only values from 0.1 to 6000 */
		floatInputTextField.setPreferredSize(FLOAT_INPUT_TEXT_FEILD_DIMENSION);
		floatInputTextField.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		floatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
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
		    	boolean AREA_HEADER_BUTTON_TEMP_DISABLED = false;
	    		boolean DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
	    		boolean FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
		    	try
		    	{
		    		float value = Float.valueOf(floatInputTextField.getText());
		    		if ((0.1 <= value) && (value <= 6000))
			    	{
						/* Sets next button enabled values */
			    		nextButton.setEnabled(NEXT_BUTTON_ENABLED = true);
			    		SettingsPanel.FREQUENCY_SELECTED = true;
			    		
			    		/* Sets header button enabled values */
			    		headerButton.setDisabledIcon(HEADER_DISABLED_IMAGE_ICON);
			    		headerButton.setEnabled(false);
			    		
			    		/* Set borders light blue when enabled */
						frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
						floatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
						
						/* Changes text back to normal wrong values have been entered before */
						textMoreThen.setText(TEXT_MORE_THEN_NORMAL_TEXT);
						textLessThen.setText(TEXT_LESS_THEN_NORMAL_TEXT);
						
						if(AREA_HEADER_BUTTON_TEMP_DISABLED)
						{
							AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED);
							AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_ENABLED_IMAGE_ICON);
							AREA_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						if(DENSITY_HEADER_BUTTON_TEMP_DISABLED)
						{
							DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED);
							DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_ENABLED_IMAGE_ICON);
							DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						if(FILE_NAME_HEADER_BUTTON_TEMP_DISABLED)
						{
							FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED);
							FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_ENABLED_IMAGE_ICON);
							FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						
			    	}
					else
					{
						/* Sets next button disabled values have been entered */
						nextButton.setEnabled(NEXT_BUTTON_ENABLED = false);
						
						/* Sets header button disabled values have been entered */
						headerButton.setDisabledIcon(HEADER_DISABLED_RED_IMAGE_ICON);
						headerButton.setEnabled(false);
						
						/* Set borders red when wrong values have been entered */
						frequencyPanel.setBorder(Program.RED_BORDER);
						floatInputTextField.setBorder(Program.RED_BORDER);
						
						/* wrong values have been entered changes the text so user knows what they have entered wrongly */
						if (0.1 > value)
				    	{
							textMoreThen.setText(TEXT_MORE_THEN_RED_TEXT);
				    	}
						if (6000 < value)
				    	{
							textLessThen.setText(TEXT_LESS_THEN_RED_TEXT);
				    	}
						
						if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							AREA_HEADER_BUTTON_TEMP_DISABLED = true;
						}
						if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
						}
						if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
						}
					}
		    	} 
				catch (NumberFormatException e) {		    		
		    		/* Sets next button disabled when wrong values have been entered */
					nextButton.setEnabled(NEXT_BUTTON_ENABLED = false);
					
					/* Sets header button disabled values have been entered */
					headerButton.setDisabledIcon(HEADER_DISABLED_RED_IMAGE_ICON);
					headerButton.setEnabled(false);
					
					/* Set borders red when wrong values have been entered */
					frequencyPanel.setBorder(Program.RED_BORDER);
					floatInputTextField.setBorder(Program.RED_BORDER);
					
					if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						AreaSettingsSubPanel.headerButton.setEnabled(false);
						AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						AREA_HEADER_BUTTON_TEMP_DISABLED = true;
					}
					if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						DensitySettingsSubPanel.headerButton.setEnabled(false);
						DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
					}
					if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
						FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
					}
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
		
		frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);

		frequencyPanel.add(frequencyLabel, BorderLayout.EAST);
		
		headerAndPanelContiner.add(frequencyPanel, BorderLayout.EAST);
		
		this.add(headerAndPanelContiner);
	}
	
	/**
	 * 
	 */
	public void frequencyPanelActive(){
		MainPanel.setLeftStage(Program.cameraPanel);


		Program.frame.glass.setVisible(false);
		
		/* Sets header back to blue */
		headerButton.setToolTipText(PANEL_INFORMATION);
		headerButton.setEnabled(false);
		
		/* Turns on */ 
		floatInputTextField.setVisible(true);
		textNote.setVisible(true);
		textMoreThen.setVisible(true);
		textLessThen.setVisible(true);
		nextButton.setVisible(true);
		
		/* New Active dimensions */
		frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Sets Active colors light blue to border and text */
		stepLabel.setText(STEP_TEXT_LIGHT_BLUE);
		frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Turns off */
		frequencyLabel.setVisible(false);
		SettingsPanel.FREQUENCY_SELECTED = false;
	}
	
	/**
	 * 
	 */
	public void frequencyPanelNotActive(){		
		/* Sets the frequency that have been selected to SettingsPanels global variables */
		SettingsPanel.FREQUENCY = Float.valueOf(floatInputTextField.getText());
		
		/* Sets header button to enabled and green with a new tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		headerButton.setEnabled(true);
		
		/* Green Borders */
		frequencyPanel.setBorder(Program.GREEN_BORDER);

		/* Sets step button, text and text input not visible when when next button has been pressed */
		floatInputTextField.setVisible(false);
		textNote.setVisible(false);
		textMoreThen.setVisible(false);
		textLessThen.setVisible(false);
		nextButton.setVisible(false);
		
		/* Sets step Label green when button has been pressed*/
		stepLabel.setText(STEP_TEXT_DARK_GREEN);
		
		/* Changing size of panels when button has been pressed*/	
		frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_OFF);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		
		/* Label that shows the frequency that the user has selected */
		frequencyLabel.setText("<html><font color = rgb(120,200,40)>Selected frequency: </font>" + SettingsPanel.FREQUENCY + " MHz</html>");
		frequencyLabel.setVisible(true);
	}
}
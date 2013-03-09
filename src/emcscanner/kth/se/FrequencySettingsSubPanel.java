package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * @author Jonas
 *
 */
public class FrequencySettingsSubPanel extends JPanel {
	
	/**
	 * Frequency Settings Sub Panel ID
	 */
	private static final long serialVersionUID = 5772427918605539001L;

	/* Threads */
	public static Thread DisplayFrequensyVideoThread;
	
	public static BufferedImage buffImg = null;
	/* Creates a ColorPanel and adds it to this camera panel */
	public static ColorPanel colorFrequensyVideoPanel = new ColorPanel(buffImg);
	

	/* Constants for the FrequencyPanel */
	public int frequencyInputLimit = 6;
	public int densityValue = 0;
	public int densityEndValue = 1;
	public static int STAGE = 1; //This stage
	
	/* Boolean */
	/* Display video*/
	public static boolean DISPLAY_VIDEO = true;
	public static boolean DISPLAY_HELP_VIDEO = true;
	
	/* other Booleans */
	public static boolean WRONG_FLOAT_INPUT = false;
	public static boolean NEXT_BUTTON_ENABLED = false;
	
	public static boolean START_FREQUENSY_SELECTED = false;
	public static boolean DENSITY_FREQUENSY_SELECTED = false;
	public static boolean END_FREQUENCY_SELECTED = false;
	
	/* Float */
	public float startValue = 0.1f;
	public float endValue = 0;
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel frequencyPanel = new JPanel();
	
	/* Containers for setting up GUI */
	public JPanel continer1 = 	new JPanel();
	public JPanel continer2 = 	new JPanel();
	public JPanel startTextFeildContainer 	= new JPanel();
	public JPanel endFrequensyContainer 	= new JPanel();
	public JPanel endTextFeildContainer 	= new JPanel();
	public JPanel continer4 = 	new JPanel();
	public JPanel continer5 = 	new JPanel();
	public JPanel continer6 = 	new JPanel();

	public JPanel startFrequensyContainer = new JPanel(new BorderLayout());
	public JPanel densityContainer = new JPanel(new FlowLayout());
	public JPanel densityEndFrequensyContainer = new JPanel(new BorderLayout());
	public JPanel frequencySummeryPanel = new JPanel(new BorderLayout());
	public JPanel frequencySummeryContainer = new JPanel();
	
	/* Buttons */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	public static NextButton nextButton = new NextButton(STAGE);
	public static BackButton backButton = new BackButton(STAGE);
	
	/* JTextField */
	public StartFloatInputTextField startFloatInputTextField = new StartFloatInputTextField(4);
	public JTextField endFloatInputTextField = new JTextField(4);
	public JTextField densityIntInputTextField = new JTextField(4);
	
	/* String */
	public String STEP_TEXT_GRAY = "<html> <font color = rgb(120,120,120)>Step 1/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 1/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 1/4</font></html>";
	
	public static String PANEL_INFORMATION = "This is where you can shoose the desired frequency.";
	
	public String HEADER_BUTTON_TOOL_TIP_TEXT = "Press to reselect The frequency";
	
	public String MORE_THEN_NORMAL_TEXT = "<html>0.1 ≤ </html>";
	public String LESS_THEN_NORMAL_TEXT = "<html><font color = rgb(100,150,255)>MHz</font>"+" ≤ 6000</html>";
	
	public String END_MORE_THEN_LIGHT_GRAY_TEXT = "<html><font color = rgb(110,110,110)>" + startValue + " ≤</font></html>";
	public String LESS_THEN_LIGHT_GRAY_TEXT = "<html><font color = rgb(110,110,110)>MHz ≤ 6000</font></html>";
	
	public String MORE_THEN_RED_TEXT = "<html><font color = rgb(255,0,0)>0.1 ≤</font></html>";
	public String LESS_THEN_RED_TEXT = "<html><font color = rgb(100,150,255)>MHz</font>"+"<font color = rgb(255,0,0)> ≤ 6000</font></html>";
	
	public String START_FREQUENSY_LIGHT_BLUE_TEXT = "<html><font color = rgb(100,150,255)>Start frequency: </font></html>";
	public String START_FREQUENSY_RED_TEXT = "<html><font color = rgb(255,0,0)>Start frequency: </font></html>";
	
	public String END_FREQUENSY_LIGHT_GRAY_TEXT = "<html><font color = rgb(120,120,120)>End frequency: </font></html>";
	public String END_FREQUENSY_LIGHT_BLUE_TEXT = "<html><font color = rgb(100,150,255)>End frequency: </font></html>";
	public String END_FREQUENSY_RED_TEXT = "<html><font color = rgb(255,0,0)>End frequency: </font></html>";
	
	public String DENSITY_FREQUENSY_LIGHT_GRAY_TEXT = "<html><font color = rgb(120,120,120)>Frequency density:</font></html>";
	public String DENSITY_FREQUENSY_LIGHT_BLUE_TEXT = "<html><font color = rgb(100,150,255)>Frequency density:</font></html>";
	public String DENSITY_FREQUENSY_RED_TEXT = "<html><font color = rgb(255,0,0)>Frequency density:</font></html>";
	
	public String DENSITY_MORE_THEN_TEXT = "<html><font color = rgb(110,110,110)> 1 ≤</font></html>";
	public String DENSITY_LESS_THEN_TEXT = "<html><font color = rgb(110,110,110)> ≤ </font></html>";
	
	
	/* JLabel */
	public JLabel stepLabel 			= new JLabel(STEP_TEXT_LIGHT_BLUE);
	
	public JLabel startMoreThenLabel	= new JLabel(MORE_THEN_NORMAL_TEXT);
	public JLabel startLessThenLabel 	= new JLabel(LESS_THEN_NORMAL_TEXT);
	
	public JLabel endMoreThenLabel		= new JLabel(END_MORE_THEN_LIGHT_GRAY_TEXT);
	public JLabel endLessThenLabel 		= new JLabel(LESS_THEN_LIGHT_GRAY_TEXT);
	
	public JLabel densityMoreThenLabel	= new JLabel(DENSITY_MORE_THEN_TEXT);
	public JLabel densityLessThenLabel	= new JLabel(DENSITY_LESS_THEN_TEXT);
	
	public JLabel frequencyLabel 		= new JLabel();
	public JLabel frequencyLabelStart 	= new JLabel();
	public JLabel frequencyLabelEnd 	= new JLabel();
	public JLabel frequencyLabelDensity = new JLabel();
	
	public JLabel startFrequensyLabel 	= new JLabel(START_FREQUENSY_LIGHT_BLUE_TEXT);
	public JLabel endFrequensyLabel 	= new JLabel(END_FREQUENSY_LIGHT_GRAY_TEXT);
	public JLabel densityFrequensyLabel = new JLabel(DENSITY_FREQUENSY_LIGHT_GRAY_TEXT);
		
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);
	
	public Dimension COLOR_PANEL_DIMENSION = new Dimension(322, 180);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 380);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 100);
	
	public Dimension FREQUENCY_PANEL_DIMENSION_ACTIVE = new Dimension(322, 340);
	public Dimension FREQUENCY_PANEL_DIMENSION_OFF = new Dimension(322, 60);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 380);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 100);
	
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension NEXT_BUTTON_DIMENSION = new Dimension(90, 50);
	public Dimension FLOAT_INPUT_TEXT_FEILD_DIMENSION = new Dimension(20,20);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	/**
	 * 
	 */
	public FrequencySettingsSubPanel(){
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);		

		colorFrequensyVideoPanel.setVisible(true);
		colorFrequensyVideoPanel.setPreferredSize(COLOR_PANEL_DIMENSION);

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
		
		/* Boundary explanatory labels */
		startMoreThenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startLessThenLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		DisplayFrequensyVideoThread = new FrameGrabberThread(STAGE,"threadDisplay");
        DisplayFrequensyVideoThread.setDaemon(true);
        DisplayFrequensyVideoThread.start();
		
		
		
		endFloatInputTextField.setPreferredSize(FLOAT_INPUT_TEXT_FEILD_DIMENSION);
		endFloatInputTextField.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		endFloatInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
		endFloatInputTextField.setEnabled(false);
		endFloatInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
		endFloatInputTextField.getDocument().addDocumentListener(new DocumentListener () {
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
		    		float value = Float.parseFloat(endFloatInputTextField.getText().toLowerCase().replace(',', '.').replace('f', 'x').replace('d', 'x'));
	    			endValue = value;
		    		if ((startValue <= value) && (value <= 6000))
			    	{
						endFloatInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
		    			END_FREQUENCY_SELECTED = true;
						
		    			densityEndValue = (int)((endValue-startValue) * 10 + 1.5);
			    		
						if (endValue < densityValue)
							densityIntInputTextField.setText(""+(int)1);
		    			
			    		/* Density selection  enabled */
		    			densityIntInputTextField.setEnabled(true);
						
		    			densityIntInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
		    			densityIntInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
		    			densityMoreThenLabel.setText("<html><font color = rgb(0,0,0)>" + 1 + " ≤</font></html>");
		    			densityLessThenLabel.setText("<html><font color = rgb(0,0,0)> ≤ " + densityEndValue + "</font></html>");
		    			densityFrequensyLabel.setText(DENSITY_FREQUENSY_LIGHT_BLUE_TEXT);
		    			
		    			/* Changes text back to normal wrong values have been entered before */
						endMoreThenLabel.setText("<html><font color = rgb(0,0,0)>" + startValue + " ≤</font></html>");
						endLessThenLabel.setText(LESS_THEN_NORMAL_TEXT);
		    			
			    		/* Sets header button enabled values */
			    		headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
			    		headerButton.setEnabled(false);
			    		
			    		/* Set borders light blue when enabled */
						frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
						endFloatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
						
						if(AREA_HEADER_BUTTON_TEMP_DISABLED)
						{
							AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED);
							AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_ENABLED_IMAGE_ICON);
							AREA_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						if(DENSITY_HEADER_BUTTON_TEMP_DISABLED)
						{
							DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED);
							DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_ENABLED_IMAGE_ICON);
							DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						if(FILE_NAME_HEADER_BUTTON_TEMP_DISABLED)
						{
							FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED);
							FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_ENABLED_IMAGE_ICON);
							FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						
			    	}
					else
					{
						endFloatInputTextField.setBackground(Program.LIGHT_RED_COLOR);
						END_FREQUENCY_SELECTED = false;
		    			
						if (endValue < densityValue)
							densityIntInputTextField.setText(""+(int)1);
						
						/* Sets next button disabled values have been entered */
		    			densityIntInputTextField.setEnabled(false);
		    			densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
		    			densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
		    			densityMoreThenLabel.setText("<html><font color = rgb(110,110,110)>" + 1 + " ≤</font></html>");
		    			densityLessThenLabel.setText("<html><font color = rgb(110,110,110)> ≤ " + densityEndValue + "</font></html>");
		    			densityFrequensyLabel.setText(DENSITY_FREQUENSY_LIGHT_GRAY_TEXT);
						
						/* Sets header button disabled values have been entered */
						headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
						headerButton.setEnabled(false);
						
						/* Set borders red when wrong values have been entered */
						frequencyPanel.setBorder(Program.RED_BORDER);
						endFloatInputTextField.setBorder(Program.RED_BORDER);
						
						/* wrong values have been entered changes the text so user knows what they have entered wrongly */
						if (startValue > value)
				    	{
							endMoreThenLabel.setText("<html><font color = rgb(255,0,0)>" + startValue + " ≤</font></html>");
				    	}
						if (6000 < value)
				    	{
							endLessThenLabel.setText(LESS_THEN_RED_TEXT);
				    	}
						
						if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							AREA_HEADER_BUTTON_TEMP_DISABLED = true;
						}
						if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
						}
						if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
						}
					}
		    	} 
				catch (NumberFormatException e) {
					END_FREQUENCY_SELECTED = false;
					endFloatInputTextField.setBackground(Program.LIGHT_RED_COLOR);
	    			
		    		/* Sets next button disabled when wrong values have been entered */
	    			densityIntInputTextField.setEnabled(false);
	    			densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
	    			/* Changes text back to normal wrong values have been entered before */
	    			densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
	    			densityMoreThenLabel.setText("<html><font color = rgb(110,110,110)>" + 1 + " ≤</font></html>");
	    			densityLessThenLabel.setText("<html><font color = rgb(110,110,110)> ≤ " + densityEndValue + "</font></html>");
	    			densityFrequensyLabel.setText(DENSITY_FREQUENSY_LIGHT_GRAY_TEXT);
					
					/* Sets header button disabled values have been entered */
					headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
					headerButton.setEnabled(false);
					
					/* Set borders red when wrong values have been entered */
					frequencyPanel.setBorder(Program.RED_BORDER);
					endFloatInputTextField.setBorder(Program.RED_BORDER);
					
					if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						AreaSettingsSubPanel.headerButton.setEnabled(false);
						AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						AREA_HEADER_BUTTON_TEMP_DISABLED = true;
					}
					if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						DensitySettingsSubPanel.headerButton.setEnabled(false);
						DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
					}
					if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
						FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
					}
		    	}
				if (START_FREQUENSY_SELECTED && DENSITY_FREQUENSY_SELECTED && END_FREQUENCY_SELECTED)
					nextButton.setEnabled(NEXT_BUTTON_ENABLED= true);
				else
					nextButton.setEnabled(NEXT_BUTTON_ENABLED= false);
		    }
		});
		
		densityIntInputTextField.setPreferredSize(FLOAT_INPUT_TEXT_FEILD_DIMENSION);
		densityIntInputTextField.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
		densityIntInputTextField.setEnabled(false);
		densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
		densityIntInputTextField.getDocument().addDocumentListener(new DocumentListener () {
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
		    		densityIntInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
		    		int value = Integer.valueOf(densityIntInputTextField.getText());
		    		densityValue = value;
		    		if ((1 <= value) && (value <= densityEndValue))
			    	{
		    			DENSITY_FREQUENSY_SELECTED = true;
		    			
						/* Sets next button enabled values */
			    		nextButton.setEnabled(NEXT_BUTTON_ENABLED = true);
			    		
			    		/* Sets header button enabled values */
			    		headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
			    		headerButton.setEnabled(false);
			    		
			    		/* Set borders light blue when enabled */
						frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
						densityIntInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
						
						/* Changes text back to normal wrong values have been entered before */
		    			densityMoreThenLabel.setText("<html><font color = rgb(0,0,0)>" + 1 + " ≤</font></html>");
		    			densityLessThenLabel.setText("<html><font color = rgb(0,0,0)> ≤ " + densityEndValue + "</font></html>");
						
						if(AREA_HEADER_BUTTON_TEMP_DISABLED)
						{
							AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED);
							AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_ENABLED_IMAGE_ICON);
							AREA_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						if(DENSITY_HEADER_BUTTON_TEMP_DISABLED)
						{
							DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED);
							DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_ENABLED_IMAGE_ICON);
							DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						if(FILE_NAME_HEADER_BUTTON_TEMP_DISABLED)
						{
							FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
							FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED);
							FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_ENABLED_IMAGE_ICON);
							FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
						}
						
			    	}
					else
					{
						densityIntInputTextField.setBackground(Program.LIGHT_RED_COLOR);
						DENSITY_FREQUENSY_SELECTED = false;
						/* Sets next button disabled values have been entered */
						nextButton.setEnabled(NEXT_BUTTON_ENABLED = false);
						
						/* Sets header button disabled values have been entered */
						headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
						headerButton.setEnabled(false);
						
						/* Set borders red when wrong values have been entered */
						frequencyPanel.setBorder(Program.RED_BORDER);
						densityIntInputTextField.setBorder(Program.RED_BORDER);
						
						/* wrong values have been entered changes the text so user knows what they have entered wrongly */
						if (1 > value)
				    	{
			    			densityMoreThenLabel.setText("<html><font color = rgb(255,0,0)>" + 1 + " ≤</font></html>");
				    	}
						if (densityEndValue < value)
				    	{
			    			densityLessThenLabel.setText("<html><font color = rgb(255,0,0)> ≤ " + densityEndValue + "</font></html>");
				    	}
						
						if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							AREA_HEADER_BUTTON_TEMP_DISABLED = true;
						}
						if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
						}
						if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
						{
							FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
							FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
							FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
						}
					}
		    	} 
				catch (NumberFormatException e) {
					densityIntInputTextField.setBackground(Program.LIGHT_RED_COLOR);
					DENSITY_FREQUENSY_SELECTED = false;		    		
		    		/* Sets next button disabled when wrong values have been entered */
					nextButton.setEnabled(NEXT_BUTTON_ENABLED = false);
					
					/* Sets header button disabled values have been entered */
					headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
					headerButton.setEnabled(false);
					
					/* Set borders red when wrong values have been entered */
					frequencyPanel.setBorder(Program.RED_BORDER);
					densityIntInputTextField.setBorder(Program.RED_BORDER);
					
					if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						AreaSettingsSubPanel.headerButton.setEnabled(false);
						AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						AREA_HEADER_BUTTON_TEMP_DISABLED = true;
					}
					if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						DensitySettingsSubPanel.headerButton.setEnabled(false);
						DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
					}
					if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
					{
						FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
						FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
						FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
					}
		    	}
		    	if (START_FREQUENSY_SELECTED && DENSITY_FREQUENSY_SELECTED && END_FREQUENCY_SELECTED)
					nextButton.setEnabled(NEXT_BUTTON_ENABLED= true);
				else
					nextButton.setEnabled(NEXT_BUTTON_ENABLED= false);
		    }
		});

		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer2.setLayout(new BorderLayout());
		startTextFeildContainer.setLayout(new FlowLayout());
		continer4.setLayout(new FlowLayout());
		continer6.setLayout(new BorderLayout());
		
		/* Sets container backgrounds to white instead of gray for contrast */
		continer1.setBackground(Color.WHITE);
		continer2.setBackground(Color.WHITE);
		continer4.setBackground(Color.WHITE);
		continer6.setBackground(Color.WHITE);
		continer5.setBackground(Color.WHITE);
		colorFrequensyVideoPanel.setBackground(Color.WHITE);
		densityEndFrequensyContainer.setBackground(Color.WHITE);
		densityContainer.setBackground(Color.WHITE);
		endTextFeildContainer.setBackground(Color.WHITE);
		endFrequensyContainer.setBackground(Color.WHITE);
		frequencyPanel.setBackground(Color.WHITE);
		frequencySummeryPanel.setBackground(Color.WHITE);
		frequencySummeryContainer.setBackground(Color.WHITE);
		startTextFeildContainer.setBackground(Color.WHITE);
		startFrequensyContainer.setBackground(Color.WHITE);
		startFrequensyContainer.setBackground(Color.WHITE);
		
		/* Containers for GUI look */
		continer2.add(colorFrequensyVideoPanel, BorderLayout.NORTH);

		continer1.add(nextButton, BorderLayout.EAST);
		continer1.add(backButton, BorderLayout.WEST);
		
		startTextFeildContainer.add(startFloatInputTextField, BorderLayout.CENTER);
		endTextFeildContainer.add(endFloatInputTextField, BorderLayout.CENTER);
		
		startFrequensyContainer.add(startFrequensyLabel, BorderLayout.WEST);
		startFrequensyContainer.add(startMoreThenLabel, BorderLayout.EAST);
		
		continer4.add(startFrequensyContainer, FlowLayout.LEFT);
		continer4.add(startTextFeildContainer, FlowLayout.CENTER); 
		continer4.add(startLessThenLabel, FlowLayout.RIGHT);
		
		endFrequensyContainer.add(endFrequensyLabel, BorderLayout.CENTER);
		endFrequensyContainer.add(endMoreThenLabel, BorderLayout.EAST);
		
		continer5.add(endFrequensyContainer, FlowLayout.LEFT);
		continer5.add(endTextFeildContainer, FlowLayout.CENTER); 
		continer5.add(endLessThenLabel, FlowLayout.RIGHT);
		
		densityContainer.add(densityFrequensyLabel);
		densityContainer.add(densityMoreThenLabel);
		densityContainer.add(densityIntInputTextField);
		densityContainer.add(densityLessThenLabel);
		
		densityEndFrequensyContainer.add(continer5, BorderLayout.NORTH);
		densityEndFrequensyContainer.add(densityContainer, BorderLayout.SOUTH);
		
		continer6.add(continer2, BorderLayout.NORTH);
		continer6.add(continer4, BorderLayout.CENTER);
		continer6.add(densityEndFrequensyContainer, BorderLayout.SOUTH);
		
		frequencyPanel.add(continer6, BorderLayout.NORTH);
		frequencyPanel.add(continer1, BorderLayout.SOUTH);
		
		frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		frequencySummeryPanel.add(frequencyLabelStart, BorderLayout.NORTH);
		frequencySummeryPanel.add(frequencyLabelEnd, BorderLayout.CENTER);
		frequencySummeryPanel.add(frequencyLabelDensity, BorderLayout.SOUTH);

		frequencySummeryContainer.add(frequencyLabel);
		frequencySummeryContainer.add(frequencySummeryPanel);
		
		frequencyPanel.add(frequencySummeryContainer, BorderLayout.EAST);
		
		headerAndPanelContiner.add(frequencyPanel, BorderLayout.EAST);
		
		this.add(headerAndPanelContiner);
	}
}
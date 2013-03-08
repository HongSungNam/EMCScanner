package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.OpenCVFrameGrabber;


public class DensitySettingsSubPanel extends JPanel {
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
	public int densityInputLimit = 5;
	public int STAGE = 3;
	
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
	public static JButton headerButton 	= new JButton();
	public static JButton nextButton 	= new JButton();
	public static JButton backButton 	= new JButton();
	public JButton densityMillimeter	= new JButton();
	public JButton densityNumberOfSteps	= new JButton();
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 	= "Press to reselect the density";
	public String PANEL_TOOL_TIP_TEXT 		  	= "This is where you select the density to scan";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 	= "You need to select an area before you can continue";
	
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
	
	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 				= new ImageIcon("image/PanelGreenDensity.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 		= new ImageIcon("image/PanelGreenDensityRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 			= new ImageIcon("image/PanelGrayDensity.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 			= new ImageIcon("image/PanelGreenDensityPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 			= new ImageIcon("image/PanelBlueDensity.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 		= new ImageIcon("image/PanelDarkGreenDensity.png"); 
	
	public static ImageIcon DENSITY_MM_BUTTON_ENABLED_IMAGE_ICON = new ImageIcon("image/ButtonStepMMBlue.png");
	public static ImageIcon DENSITY_MM_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonStepMMGray.png");
	public static ImageIcon DENSITY_MM_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonStepMMBluePrest.png");

	public static ImageIcon DENSITY_STEP_BUTTON_ENABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonNumberStepBlue.png");
	public static ImageIcon DENSITY_STEP_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonNumberStepGray.png");
	public static ImageIcon DENSITY_STEP_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonNumberStepBluePrest.png");
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION 					= new Dimension(400, 100);
	public Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);

	public Dimension INPUT_TEXT_FEILD_DIMENSION 				= new Dimension(20,20);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 360);
	public Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	
	public Dimension DENSITY_PANEL_DIMENSION_ACTIVE 			= new Dimension(322, 320);
	public Dimension DENSITY_PANEL_DIMENSION_DONE 				= new Dimension(322, 40);
	public Dimension DENSITY_PANEL_DIMENSION_OFF 				= new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 360);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF 	= new Dimension(322, 40);

	public Dimension NOTE_LABEL_DIMENSION 						= new Dimension(200, 40);
	public Dimension INPUT_FEILDS_CONTAINER_DIMENSION 			= new Dimension(160, 85);
	public Dimension DENSITY_INPUT_TEXT_FIELD_DIMENSION 		= new Dimension(30, 20);
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner 				= new JPanel();
	public JPanel headerAndPanelContiner 	= new JPanel();
	public JPanel densityPanel				= new JPanel();
	public JPanel continer1 				= new JPanel(new BorderLayout());

	private JPanel imputFeildsContainer  	= new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer1 	= new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer2  	= new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer3  	= new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer4  	= new JPanel(new BorderLayout());
	private JPanel imputFeildsContainer5  	= new JPanel(new BorderLayout());
	private JPanel imputFeildsContainerValue1 = new JPanel(new BorderLayout());
	private JPanel imputFeildsContainerValue2 = new JPanel(new BorderLayout());
	private JPanel inputFeildsAButtons 		= new JPanel(new BorderLayout());
	private JPanel densityButtonContainer 	= new JPanel(new BorderLayout());
	
	/* JTextField */
	public JTextField heightDensityInputTextField = new JTextField(5);
	public JTextField widthDensityInputTextField  = new JTextField(5);
	
	public DensitySettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);
		
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

		/* warning note */
        noteLabel.setText(NOTE_TEXT);
        
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
		densityPanel.setLayout(new BorderLayout());
		densityPanel.setPreferredSize(DENSITY_PANEL_DIMENSION_OFF);
		
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

		densityMillimeter.setEnabled(true);
		densityMillimeter.setOpaque(false);
		densityMillimeter.setContentAreaFilled(false);
		densityMillimeter.setBorderPainted(false);
		densityMillimeter.setPreferredSize(Program.BUTTON_SMAL_DIMENSION);
		densityMillimeter.setIcon(DENSITY_MM_BUTTON_ENABLED_IMAGE_ICON);
		densityMillimeter.setDisabledIcon(DENSITY_MM_BUTTON_DISABLED_IMAGE_ICON);
		densityMillimeter.setPressedIcon(DENSITY_MM_BUTTON_BLUE_PREST_IMAGE_ICON);
		/* Density makes it possible to select the density depending on the interested length in tiondels millimeter */
		densityMillimeter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				densityMillimeter.setEnabled(false);
				densityNumberOfSteps.setEnabled(true);
				inputStepBoolean = false;
				Program.frame.glass.repaint();
			}
		});

		densityNumberOfSteps.setEnabled(false);
		densityNumberOfSteps.setOpaque(false);
		densityNumberOfSteps.setContentAreaFilled(false);
		densityNumberOfSteps.setBorderPainted(false);
		densityNumberOfSteps.setPreferredSize(Program.BUTTON_SMAL_DIMENSION);
		densityNumberOfSteps.setIcon(DENSITY_STEP_BUTTON_ENABLED_IMAGE_ICON);
		densityNumberOfSteps.setDisabledIcon(DENSITY_STEP_BUTTON_DISABLED_IMAGE_ICON);
		densityNumberOfSteps.setPressedIcon(DENSITY_STEP_BUTTON_BLUE_PREST_IMAGE_ICON);
		densityNumberOfSteps.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				densityMillimeter.setEnabled(true);
				densityNumberOfSteps.setEnabled(false);
				inputStepBoolean = true;
				Program.frame.glass.repaint();
			}
		});
		
		threadDisplayDensityVideo = new FrameGrabberThread(STAGE, "densityVideo");
        threadDisplayDensityVideo.setDaemon(true);
        threadDisplayDensityVideo.start();
        
        /* INPUT field for width */
        widthDensityInputTextField.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
        widthDensityInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
        widthDensityInputTextField.setDocument(new LengthRestrictedDocument(densityInputLimit));
        widthDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
        widthDensityInputTextField.getDocument().addDocumentListener(new DocumentListener () {
			public void insertUpdate(DocumentEvent aEvent) {
				checkInt();
		    }
		    public void removeUpdate(DocumentEvent aEvente) {
		    	checkInt();
		    }
		    public void changedUpdate(DocumentEvent aEvent) {
		    	checkInt();
		    }
		});
        /* INPUT field for height */
        heightDensityInputTextField.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
        heightDensityInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
        heightDensityInputTextField.setDocument(new LengthRestrictedDocument(densityInputLimit));
        heightDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
        heightDensityInputTextField.getDocument().addDocumentListener(new DocumentListener () {
			public void insertUpdate(DocumentEvent aEvent) {
				checkInt2();
		    }
		    public void removeUpdate(DocumentEvent aEvente) {
		    	checkInt2();
		    }
		    public void changedUpdate(DocumentEvent aEvent) {
		    	checkInt2();
		    }
		});
        
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
    	
    	densityButtonContainer.setPreferredSize(new Dimension(40, 40));
        
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
	public void checkInt()
    {
    	try
    	{
    		int value = Integer.valueOf(widthDensityInputTextField.getText());
    		
			if (value > 0 && value <= ((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    		{
				NUMBER_OF_LINES_WIDTH = value - 1;
				widthLabel.setText("<html><font color = rgb(100,150,255)> Width: </font></html>");
				widthLabelValue.setText("<html>" + (int)((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
				widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
				
				widthDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
    			widthDensityInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
				WIDTH_ENTERD_CORRECTLY = true;
				if (HEIGHT_ENTERD_CORRECTLY && WIDTH_ENTERD_CORRECTLY)
					nextButton.setEnabled(true);
				
				Program.frame.glass.repaint();
    		}
    		else
    		{						
    			if (value > ((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    			{
					widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
					widthLabelValue.setText("<html><font color = rgb(255,0,0)>" + (int)((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</font></html>");
    				widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
    			}
				else if (value <= 0)
				{
					widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
					widthLabelValue.setText("<html>" + (int)((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL)  + " &gt&nbsp</html>");
					widthLabel0.setText("<html><font color = rgb(255,0,0)> &nbsp&gt 0&nbsp</font></html>");
				}
    			widthDensityInputTextField.setBorder(Program.RED_BORDER);
    			widthDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
    			WIDTH_ENTERD_CORRECTLY = false;
				nextButton.setEnabled(false);
				NUMBER_OF_LINES_WIDTH = 0;
				Program.frame.glass.repaint();
    		}
    	} 
		catch (NumberFormatException e) {
			widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
			widthLabelValue.setText("<html>" + (int)((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
			widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
			
			widthDensityInputTextField.setBorder(Program.RED_BORDER);
			widthDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
			WIDTH_ENTERD_CORRECTLY = false;
			nextButton.setEnabled(false);

			NUMBER_OF_LINES_WIDTH = 0;
			Program.frame.glass.repaint();
    	}
    }
	public void checkInt2()
    {
    	try
    	{
    		int value = Integer.valueOf(heightDensityInputTextField.getText());
    		if (value > 0 && value <= ((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    		{
				NUMBER_OF_LINES_HEIGHT = value - 1;
				
				heightLabel.setText("<html> <font color = rgb(100,150,255)> Height: </html> </font>");
				heightLabelValue.setText("<html>" + (int)((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
				heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");

		        heightDensityInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
    			heightDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);

				HEIGHT_ENTERD_CORRECTLY = true;
				if (HEIGHT_ENTERD_CORRECTLY && WIDTH_ENTERD_CORRECTLY)
					nextButton.setEnabled(true);

				Program.frame.glass.repaint();
    		}
    		else
    		{
    			if (value > ((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    			{
    				heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
    				heightLabelValue.setText("<html> <font color = rgb(255,0,0)>" + (int)((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</font></html>");
    				heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
    			}
    			else if (value <= 0)
    			{
    				heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
    				heightLabelValue.setText("<html>" + (int)((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
    				heightLabel0.setText("<html><font color = rgb(255,0,0)> &nbsp&gt 0&nbsp</font></html>");
    			}
				heightDensityInputTextField.setBorder(Program.RED_BORDER);
		        heightDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
				HEIGHT_ENTERD_CORRECTLY = false;
				nextButton.setEnabled(false);
				NUMBER_OF_LINES_HEIGHT = 0;

				Program.frame.glass.repaint();
    		}
    	} 
		catch (NumberFormatException e) {
			heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
			heightLabelValue.setText("<html>" + (int)((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
			heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
			
			heightDensityInputTextField.setBorder(Program.RED_BORDER);
	        heightDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
			HEIGHT_ENTERD_CORRECTLY = false;
			nextButton.setEnabled(false);
			NUMBER_OF_LINES_HEIGHT = 0;

			Program.frame.glass.repaint();
    	}
    }
	public JPanel getInputFeildsAButtons() {
		return inputFeildsAButtons;
	}
	public void setInputFeildsAButtons(JPanel inputFeildsAButtons) {
		this.inputFeildsAButtons = inputFeildsAButtons;
	}
}

package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	private static final String Density = null;
	/* Video player */
	/* Imports videos */
	public static OpenCVFrameGrabber grabber3 = new OpenCVFrameGrabber("video/test3.avi");
	public static OpenCVFrameGrabber grabber4 = new OpenCVFrameGrabber("video/test4.mp4");
	public static BufferedImage buffDensityImg = null;
	/* Creates a ColorPanel and adds it to this camera panel */
	public static ColorPanel colorDensityCameraPanel = new ColorPanel(buffDensityImg);
	/* Threads */
	public static Thread threadDisplayDensityVideo;
	
	/* Integers */
	public int MAX_LINES = 40;
	public int TIONDELS_MILLI_METER_PIXEL = 10;
	public int NUMBER_MAX_OF_LINES_WIDTH;
	public int NUMBER_MAX_OF_LINES_HEIGHT;
	public int NUMBER_OF_LINES_HEIGHT;
	public int NUMBER_OF_LINES_WIDTH;
	public int densityInputLimit = 5;
	
	/* Boolean */
	public static boolean DISPLAY_DENSITY_HELP_VIDEO 			= false;
	public static boolean DISPLAY_DENSITY_VIDEO 				= true;
	public static boolean HEADER_BUTTON_ENABLED 				= false;
	public static boolean WIDTH_ENTERD_CORRECTLY 				= false;
	public static boolean HEIGHT_ENTERD_CORRECTLY 				= false;
	public static boolean VISIBLE								= true;
	public static boolean NOT_VISIBLE							= false;
	
	/* Buttons */
	public static JButton headerButton 							= new JButton();
	public static JButton nextButton 							= new JButton();
	public static JButton backButton 							= new JButton();
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 					= "Press to reselect the density";
	public String PANEL_TOOL_TIP_TEXT 							= "This is where you select the density to scan";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 					= "You need to select an area before you can continue";
	
	public String STEP_TEXT_GRAY	 							= "<html> <font color = rgb(120,120,120)>Step 3/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  						= "<html> <font color = rgb(100,150,255)>Step 3/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  						= "<html> <font color = rgb(120,200,40)>Step 3/4</font></html>";
	
	public String SCAN_DENSITY_LIGHT_BLUE_LABEL 				= "<html> <font color = rgb(100,150,255)>Scan density: </font></html>";
	public String SCAN_DENSITY_DARK_GREEN_LABEL					= "<html> <font color = rgb(120,200,40)>Scan density: </font></html>";
	public String SCAN_DENSITY_RED_LABEL 						= "<html> <font color = rgb(255,0,0)>Scan density: </font></html>";
	public String WIDTH_LIGHT_BLUE_LABEL 						= "<html> <font color = rgb(100,150,255)> Width: </font></html>";
	public String WIDTH_DARK_GREEN_LABEL 						= "<html> <font color = rgb(120,200,40)> Width: </font></html>";
	public String WIDTH_RED_LABEL 								= "<html> <font color = rgb(255,0,0)> Width: </font></html>";
	
	public String HEIGHT_LIGHT_BLUE_LABEL 						= "<html> <font color = rgb(100,150,255)> Height: </font></html>";
	public String HEIGHT_DARK_GREEN_LABEL 						= "<html> <font color = rgb(120,200,40)> Height: </font></html>";
	public String HEIGHT_RED_LABEL 								= "<html> <font color = rgb(255,0,0)> Height: </font></html>";
	
	/* Labels */
	public JLabel scanDensityLabel 								= new JLabel(SCAN_DENSITY_LIGHT_BLUE_LABEL);
	
	public JLabel widthLabel 									= new JLabel();
	public JLabel heightLabel 									= new JLabel();
	public JLabel widthLabel0 									= new JLabel();
	public JLabel heightLabel0 									= new JLabel();
	public JLabel widthLabelValue								= new JLabel();
	public JLabel heightLabelValue								= new JLabel();
	
	public JLabel densitySelectedLabel 							= new JLabel();
	public JLabel stepLabel 									= new JLabel(STEP_TEXT_GRAY);
	
	
	
	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 				= new ImageIcon("image/PanelGreenDensity.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 		= new ImageIcon("image/PanelGreenDensityRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 			= new ImageIcon("image/PanelGrayDensity.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 			= new ImageIcon("image/PanelGreenDensityPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 			= new ImageIcon("image/PanelBlueDensity.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 		= new ImageIcon("image/PanelDarkGreenDensity.png"); 
	
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
	
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner 									= new JPanel();
	public JPanel headerAndPanelContiner 						= new JPanel();
	public JPanel densityPanel									= new JPanel();
	public JPanel continer1 									= new JPanel();
	
	/* JTextField */
	public JTextField heightDensityInputTextField 				= new JTextField(5);
	public JTextField widthDensityInputTextField 				= new JTextField(5);
	
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
		headerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FrequensySettingsSubPanel.NEXT_BUTTON_ENABLED)
				{
					SettingsPanel.FREQUENCY_SELECTED = true;
					SettingsPanel.areaPanel.areaSelectionNotActive();
					SettingsPanel.densityPanel.densityPanelActive();
					SettingsPanel.frequencyPanel.frequencyPanelNotActive();
					SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				}
			}
		});
		
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
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				SettingsPanel.DENSITY_SELECTED_WIDTH = NUMBER_OF_LINES_WIDTH + 1;
				SettingsPanel.DENSITY_SELECTED_HEIGHT = NUMBER_OF_LINES_HEIGHT + 1;
				SettingsPanel.DENSITY_SELECTED = true;
				densityPanelNotActive();
				SettingsPanel.areaPanel.areaSelectionNotActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
			}
		});
		
		/* Back on step JButton */
		backButton.setEnabled(true);
		backButton.setPreferredSize(Program.BUTTON_DIMENSION);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.areaPanel.areaSelectionActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();

				MainPanel.setLeftStage(Program.cameraPanel);
			}
		});
		
		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		
		threadDisplayDensityVideo = new Thread("threadDisplayDensity"){
        	public void run(){
	            while (true) {
	            	if (DISPLAY_DENSITY_VIDEO)
	            	{
	            		IplImage grabbedDensityImage = null;
		                try {
		                	if ((grabber3.getLengthInFrames()- 100) >= grabber3.getFrameNumber())
		                	{
		                		if (DISPLAY_DENSITY_HELP_VIDEO)
		                			grabbedDensityImage = grabber3.grab();
		                		else
		                			Thread.sleep(100);
		                	}
		                	else if((grabber4.getLengthInFrames()- 100) >= grabber4.getFrameNumber()) {
		                		if (DISPLAY_DENSITY_HELP_VIDEO)
		                			grabbedDensityImage = grabber4.grab();
		                		else
		                			Thread.sleep(100);
		                	}
		                	else
		                	{
		                		grabber3.restart();
	                			grabber4.restart();
			                	/*  Grabbed the image from the video */
	                			if (DISPLAY_DENSITY_HELP_VIDEO)
	                				grabbedDensityImage = grabber3.grab();
	                			else
	                				Thread.sleep(100);
		                	}
		                		
						} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
							continue;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		                if (grabbedDensityImage != null) {
		                	/* Turns the image so that it have a mirror effect */
		                	int widthFrame = colorDensityCameraPanel.getWidth();
		                    int heightFrame = colorDensityCameraPanel.getHeight();
		                    int widthCameraImage = grabbedDensityImage.width();
		                    int heightCameraImage = grabbedDensityImage.height();
	
		                    /* Creating dimensions for the camera and the panel area 
		                       Used later for deciding the new dimension that we will resize the image to */
		                    Dimension imgSize = new Dimension(widthCameraImage, heightCameraImage);
		                    Dimension boundary = new Dimension(widthFrame, heightFrame);
		                    
		                    /* Changing the scaling of the grabbed camera image */
		                    Dimension newImagebunderys = CameraPanel.getScaledDimension(imgSize, boundary);
		                    //System.out.println(newImagebunderys);
		                    
		                    BufferedImage bufferdWebcameraImage;
		                    
		                    if ((widthFrame > 0) || (heightFrame > 0))
		                    {
		                    	bufferdWebcameraImage = grabbedDensityImage.getBufferedImage();
								int type = bufferdWebcameraImage.getType() == 0? BufferedImage.TYPE_INT_ARGB
								        : bufferdWebcameraImage.getType();
	
								BufferedImage resizeImaged = CameraPanel.resizeImage(bufferdWebcameraImage, type, newImagebunderys.width, newImagebunderys.height);
	
								colorDensityCameraPanel.theCamera = resizeImaged;
		                    }
		                    else
		                    {
		                    	bufferdWebcameraImage = grabbedDensityImage.getBufferedImage();
		                    	colorDensityCameraPanel.theCamera = bufferdWebcameraImage;
		                    }
		                    
		                	/* Show image on window */
		                    colorDensityCameraPanel.repaint();
		                }
	            	}
	            	else
	            	{
	            		try {
							Thread.sleep(1000);
							} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            }
        	}
        };
        threadDisplayDensityVideo.setDaemon(true);
        threadDisplayDensityVideo.start();
        
        /* INPUT field for width */
        widthDensityInputTextField.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
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
		    public void checkInt()
		    {
		    	try
		    	{
		    		int value = Integer.valueOf(widthDensityInputTextField.getText());
	    			if (value > 0 && value < (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * TIONDELS_MILLI_METER_PIXEL))
		    		{
	    				NUMBER_OF_LINES_WIDTH = value - 1;
	    				widthLabel.setText("<html><font color = rgb(100,150,255)> Width: </font></html>");
						widthLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * TIONDELS_MILLI_METER_PIXEL) + " &gt</html>");
						widthLabel0.setText("<html> &gt 0 </html>");
						
	    				widthDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
	    				WIDTH_ENTERD_CORRECTLY = true;
						if (HEIGHT_ENTERD_CORRECTLY && WIDTH_ENTERD_CORRECTLY)
							nextButton.setEnabled(true);
						
						Program.frame.glass.repaint();
		    		}
		    		else
		    		{						
		    			if (value > (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * TIONDELS_MILLI_METER_PIXEL))
		    			{
							widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
							widthLabelValue.setText("<html><font color = rgb(255,0,0)>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * TIONDELS_MILLI_METER_PIXEL) + " &gt</font></html>");
							widthLabel0.setText("<html> &gt 0</html>");
		    			}
						else if (value <= 0)
						{
							widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
							widthLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * TIONDELS_MILLI_METER_PIXEL) + " &gt</html>");
							widthLabel0.setText("<html><font color = rgb(255,0,0)> &gt 0</font></html>");
						}
		    			widthDensityInputTextField.setBorder(Program.RED_BORDER);
		    			WIDTH_ENTERD_CORRECTLY = false;
						nextButton.setEnabled(false);
						NUMBER_OF_LINES_WIDTH = 0;
						Program.frame.glass.repaint();
		    		}
		    	} 
				catch (NumberFormatException e) {
					widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
					widthLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * TIONDELS_MILLI_METER_PIXEL) + " &gt</html>");
					widthLabel0.setText("<html> &gt 0</html>");
					
					widthDensityInputTextField.setBorder(Program.RED_BORDER);
					WIDTH_ENTERD_CORRECTLY = false;
					nextButton.setEnabled(false);

					NUMBER_OF_LINES_WIDTH = 0;
					Program.frame.glass.repaint();
		    	}
		    }
		});
        /* INPUT field for height */
        heightDensityInputTextField.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
        heightDensityInputTextField.setDocument(new LengthRestrictedDocument(densityInputLimit));
        heightDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
        heightDensityInputTextField.getDocument().addDocumentListener(new DocumentListener () {
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
		    	try
		    	{
		    		int value = Integer.valueOf(heightDensityInputTextField.getText());
		    		if (value > 0 && value < (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * TIONDELS_MILLI_METER_PIXEL))
		    		{
	    				NUMBER_OF_LINES_HEIGHT = value - 1;

	    				heightLabel.setText("<html> <font color = rgb(100,150,255)> Height: </html> </font>");
	    				heightLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * TIONDELS_MILLI_METER_PIXEL) + " &gt</html>");
						heightLabel0.setText("<html> &gt 0</html>");
						
		    			heightDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);

						HEIGHT_ENTERD_CORRECTLY = true;
						if (HEIGHT_ENTERD_CORRECTLY && WIDTH_ENTERD_CORRECTLY)
							nextButton.setEnabled(true);

    					Program.frame.glass.repaint();
								
		    		}
		    		else
		    		{
		    			if (value > (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * TIONDELS_MILLI_METER_PIXEL))
		    			{
		    				heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
		    				heightLabelValue.setText("<html> <font color = rgb(255,0,0)>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * TIONDELS_MILLI_METER_PIXEL) + " &gt</font></html>");
		    				heightLabel0.setText("<html> &gt 0</html>");
		    			}
		    			else if (value <= 0)
		    			{
		    				heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
		    				heightLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * TIONDELS_MILLI_METER_PIXEL) + " &gt</html>");
		    				heightLabel0.setText("<html><font color = rgb(255,0,0)> &gt 0</font></html>");
		    			}
						heightDensityInputTextField.setBorder(Program.RED_BORDER);
						HEIGHT_ENTERD_CORRECTLY = false;
						nextButton.setEnabled(false);
						NUMBER_OF_LINES_HEIGHT = 0;

    					Program.frame.glass.repaint();
		    		}
		    	} 
				catch (NumberFormatException e) {
    				heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
    				heightLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * TIONDELS_MILLI_METER_PIXEL) + " &gt</html>");
					heightLabel0.setText("<html> &gt 0</html>");
					
					
					heightDensityInputTextField.setBorder(Program.RED_BORDER);
					HEIGHT_ENTERD_CORRECTLY = false;
					nextButton.setEnabled(false);
					NUMBER_OF_LINES_HEIGHT = 0;

					Program.frame.glass.repaint();
		    	}
		    }
		});
        
        /* Sets container backgrounds to white instead of gray for contrast */
        colorDensityCameraPanel.setBackground(Color.WHITE);
        
        JPanel imputFeildsContainer  = new JPanel(new BorderLayout());
        
        
        JLabel noteLabel  = new JLabel("<html> <font color = rgb(255,0,0)> Note:</font> Higher " +
        																  "density equals " +
        																  "longer scanning " +
        																  "process.</html>");
        
        imputFeildsContainer.setPreferredSize(new Dimension(322, 80));
        imputFeildsContainer.add(noteLabel, BorderLayout.NORTH);
        imputFeildsContainer.add(scanDensityLabel, BorderLayout.CENTER);
        JPanel imputFeildsContainer2  = new JPanel();
        JPanel imputFeildsContainerValue1  = new JPanel();
        imputFeildsContainerValue1.add(widthLabel, BorderLayout.EAST);
        imputFeildsContainerValue1.add(widthLabelValue, BorderLayout.WEST);
        imputFeildsContainer2.add(imputFeildsContainerValue1, BorderLayout.WEST);
        imputFeildsContainer2.add(widthDensityInputTextField, BorderLayout.CENTER);
        imputFeildsContainer2.add(widthLabel0, BorderLayout.EAST);
        JPanel imputFeildsContainer3  = new JPanel();
        JPanel imputFeildsContainerValue2  = new JPanel();
        imputFeildsContainerValue2.add(heightLabel, BorderLayout.EAST );
        imputFeildsContainerValue2.add(heightLabelValue, BorderLayout.WEST);
        imputFeildsContainer3.add(imputFeildsContainerValue2, BorderLayout.WEST);
        imputFeildsContainer3.add(heightDensityInputTextField, BorderLayout.CENTER);
        imputFeildsContainer3.add(heightLabel0, BorderLayout.EAST);
        JPanel imputFeildsContainer4  = new JPanel(new BorderLayout());
        imputFeildsContainer4.add(imputFeildsContainer2, BorderLayout.NORTH);
        imputFeildsContainer4.add(imputFeildsContainer3, BorderLayout.SOUTH);
        imputFeildsContainer.add(imputFeildsContainer4, BorderLayout.EAST);
        
        
        imputFeildsContainer.setBackground(Color.WHITE);
        imputFeildsContainer2.setBackground(Color.WHITE);
        imputFeildsContainer3.setBackground(Color.WHITE);
        imputFeildsContainer4.setBackground(Color.WHITE);
        imputFeildsContainerValue1.setBackground(Color.WHITE);
        imputFeildsContainerValue2.setBackground(Color.WHITE);
        
        
        JPanel inputFeildsAButtons = new JPanel(new BorderLayout());
        inputFeildsAButtons.add(continer1, BorderLayout.SOUTH);
        inputFeildsAButtons.add(imputFeildsContainer, BorderLayout.NORTH);
        
		continer1.setBackground(Color.WHITE);
		densityPanel.setBackground(Color.WHITE);
		densityPanel.add(inputFeildsAButtons, BorderLayout.SOUTH);
		densityPanel.add(colorDensityCameraPanel, BorderLayout.CENTER);
		
		densityPanel.add(densitySelectedLabel, BorderLayout.EAST);
		densitySelectedLabel.setVisible(NOT_VISIBLE);
		
		headerAndPanelContiner.add(densityPanel, BorderLayout.SOUTH);
		densityPanel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * ACTIVE
	 */
	public void densityPanelActive() {
		MainPanel.setLeftStage(Program.imagePanel);
		
		SettingsPanel.stage = 3;
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(VISIBLE);
		
		/* Shows the help video when made active */
		DISPLAY_DENSITY_HELP_VIDEO = VISIBLE;
		DISPLAY_DENSITY_VIDEO = VISIBLE;
		
		/* Sets active color Blue for header, labels and borders*/
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setDisabledIcon(HEADER_DISABLED_BLUE_IMAGE_ICON);
		stepLabel.setText(STEP_TEXT_LIGHT_BLUE);
		densityPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Shows buttons and labels */
		nextButton.setVisible(VISIBLE);
		backButton.setVisible(VISIBLE);
		colorDensityCameraPanel.setVisible(VISIBLE);
		heightDensityInputTextField.setVisible(VISIBLE);
		widthDensityInputTextField.setVisible(VISIBLE);
		scanDensityLabel.setVisible(VISIBLE);
	    widthLabel.setVisible(VISIBLE);
	    heightLabel.setVisible(VISIBLE);
		
		/* Changing size of panels when button has been pressed*/	
		densityPanel.setPreferredSize(DENSITY_PANEL_DIMENSION_ACTIVE);

		/* Changing size of panels when button has been pressed*/	
		densityPanel.setPreferredSize(DENSITY_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Turns on Panel */
		densityPanel.setVisible(true);
		

		/* Doesn't show label */
		densitySelectedLabel.setVisible(NOT_VISIBLE);
		
	}
	/**
	 * NOT ACTIVE
	 */
	public void densityPanelNotActive(){		
		/* Sets header button to enabled and green with a new tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* For showing videos */
		DISPLAY_DENSITY_HELP_VIDEO = NOT_VISIBLE;
		DISPLAY_DENSITY_VIDEO = NOT_VISIBLE;
		
		/* Sets video and buttons not visible */
		nextButton.setVisible(NOT_VISIBLE);
		backButton.setVisible(NOT_VISIBLE);
		colorDensityCameraPanel.setVisible(NOT_VISIBLE);
		heightDensityInputTextField.setVisible(NOT_VISIBLE);
		widthDensityInputTextField.setVisible(NOT_VISIBLE);
		scanDensityLabel.setVisible(NOT_VISIBLE);
	    widthLabel.setVisible(NOT_VISIBLE);
	    heightLabel.setVisible(NOT_VISIBLE);
		
		if (SettingsPanel.DENSITY_SELECTED)
		{
			/* Sets step label green when button has been pressed */
			stepLabel.setText(STEP_TEXT_DARK_GREEN);

			/* Sets density panel to visible */
			densityPanel.setVisible(true);
			
			/* AreaPanel and header Green */
			densityPanel.setBorder(Program.GREEN_BORDER);
			densityPanel.setVisible(VISIBLE);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			densityPanel.setPreferredSize(DENSITY_PANEL_DIMENSION_DONE);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_DONE);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_DONE);
			
			densitySelectedLabel.setText("<html><font color = rgb(120,200,40)> Density selected:</font> Width: " + SettingsPanel.DENSITY_SELECTED_WIDTH + 
										 ", Height: " + SettingsPanel.DENSITY_SELECTED_HEIGHT + "</html>");
			
			densitySelectedLabel.setVisible(VISIBLE);
		}
		else
		{
			/* Sets step label gray when button has been pressed */
			stepLabel.setText(STEP_TEXT_GRAY);
			
			/* Sets density panel to invisible */
			densityPanel.setVisible(NOT_VISIBLE);
			
			/* Sets Header button gray */
			headerButton.setDisabledIcon(HEADER_DISABLED_GRAY_IMAGE_ICON);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
			
			/* Changing size of panels when button has been pressed*/	
			densityPanel.setPreferredSize(DENSITY_PANEL_DIMENSION_OFF);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		}
	}
}

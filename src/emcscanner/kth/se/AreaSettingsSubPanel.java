package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.OpenCVFrameGrabber;

public class AreaSettingsSubPanel extends JPanel {
	
	public static OpenCVFrameGrabber grabber = new OpenCVFrameGrabber("video/test.avi");
	public static OpenCVFrameGrabber grabber2 = new OpenCVFrameGrabber("video/test2.mp4");
	
	public static BufferedImage buffImg = null;
	/* Creates a ColorPanel and adds it to this camera panel */
	public static ColorPanel colorCameraPanel = new ColorPanel(buffImg);
	
	/* Threads */
	static Thread threadDisplayAreaSelectionVideo;
	
	/* Boolean */
	public static boolean DISPLAY_AREA_VIDEO = false;
	public static boolean DISPLAY_AREA_HELP_VIDEO = false;
	public static boolean HEADER_BUTTON_ENABLED = false;
	
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel areaPanel = new JPanel();
	public JPanel continer1 = new JPanel();
	
	/* Buttons */
	public static JButton headerButton = new JButton();
	public static JButton nextButton = new JButton();
	public static JButton backButton = new JButton();
	
	/* String */
	public String STEP_TEXT_GRAY	 	= "<html> <font color = rgb(120,120,120)>Step 2/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 2/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 2/4</font></html>";
	
	public String PANEL_TOOL_TIP_TEXT = "This is where you select the area to scan.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT = "You need to select an area before you can continue";

	public String AREA_NOT_SELECTED = "<html><font color = rgb(120,120,120)>Area not selected</font></html>";
	public String AREA_SELECTED = "<html><font color = rgb(100,150,255)>Area selected </html></font>";
	
	public String HEADER_BUTTON_TOOL_TIP_TEXT = "Press to reselect the area";
	
	public String AREA_SELECTED_LABEL = "<html><font color = rgb(120,200,40)>Selected area: Width </font>" + (int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X) + " le & "
			  + "<font color = rgb(120,200,40)> Hight </font>"+ (int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y) + " le </html>";
	
	/* JLabel */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	public JLabel areaNotSelectedLabel = new JLabel(AREA_NOT_SELECTED);
	public JLabel areaLabel = new JLabel();

	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/PanelGreenArea.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 	= new ImageIcon("image/PanelGreenAreaRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 		= new ImageIcon("image/PanelGrayArea.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/PanelGreenAreaPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 		= new ImageIcon("image/PanelBlueArea.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenArea.png");

	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);

	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 280);
	public Dimension STEP_CONTINER_DIMENSION_DONE = new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 40);
	
	public Dimension AREA_PANEL_DIMENSION_ACTIVE = new Dimension(322, 240);
	public Dimension AREA_PANEL_DIMENSION_DONE = new Dimension(322, 40);
	public Dimension AREA_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 280);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE = new Dimension(322, 80);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	public AreaSettingsSubPanel() {
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
					
					SettingsPanel.areaPanel.areaSelectionActive();
					SettingsPanel.frequencyPanel.frequencyPanelNotActive();
					SettingsPanel.densityPanel.densityPanelNotActive();
					SettingsPanel.fileNamePanel.fileNamePanelNotActive();
					
					MainPanel.setLeftStage(Program.cameraPanel);
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
		areaPanel.setLayout(new BorderLayout());
		areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_OFF);
		
		/* Turns off area Label showing the selected area */
		areaLabel.setVisible(false);
		
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
				SettingsPanel.AREA_SELECTED = true;
				if((Program.frame.glass.cursorPressed.x < Program.frame.glass.cursorReleased.x) )
	    		{
	    			if ((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
	    			{
        				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (Program.frame.glass.cursorReleased.x - Program.frame.glass.cursorPressed.x + 1) + 
								" x Hight: " +(int) (Program.frame.glass.cursorReleased.y - Program.frame.glass.cursorPressed.y + 1) + "</html>");
        				
        				SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorPressed.x;
        				SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorReleased.x;
        				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorPressed.y;
        				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorReleased.y;
        				SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION = Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION;
	    			}
	    			else
    				{
	    				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (Program.frame.glass.cursorReleased.x - Program.frame.glass.cursorPressed.x + 1) + 
								" x Hight: " +(int) (Program.frame.glass.cursorPressed.y - Program.frame.glass.cursorReleased.y + 1) + "</font></html>");	
	    				/* Saves area settings */
        				SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorPressed.x;
        				SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorReleased.x;
        				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorReleased.y;
        				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorPressed.y;
    				}
	    		}
				else
    			{
					if ((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
	    			{
	    				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (Program.frame.glass.cursorPressed.x - Program.frame.glass.cursorReleased.x + 1) + 
								" x Height: " +(int) (Program.frame.glass.cursorReleased.y - Program.frame.glass.cursorPressed.y + 1) + "</font></html>");	
	    				/* Saves area settings */
        				SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorReleased.x;
        				SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorPressed.x;
        				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorPressed.y;
        				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorReleased.y;
	    			}
					else
	    			{
	    				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (Program.frame.glass.cursorPressed.x - Program.frame.glass.cursorReleased.x + 1) + 
								" x Hight: " +(int) (Program.frame.glass.cursorPressed.y - Program.frame.glass.cursorReleased.y + 1) + "</font></html>");
	    				/* Saves area settings */
        				SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorReleased.x;
        				SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorPressed.x;
        				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorReleased.y;
        				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorPressed.y;
	    			}
    			}
				SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION = Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION;
				
				SettingsPanel.areaPanel.areaSelectionNotActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
						
				CameraPanel.SAVE_IMAGE = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Program.imagePanel.setPhoto();
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
				areaSelectionNotActive();
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			}
		});

		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		continer1.add(areaNotSelectedLabel, BorderLayout.CENTER);
		
		threadDisplayAreaSelectionVideo = new Thread("threadDisplay"){
        	public void run() {
	            while (true) {
	            	if (DISPLAY_AREA_VIDEO)
	            	{
	            		IplImage grabbedImage = null;
		                try {
		                	if ((grabber.getLengthInFrames()-100) >= grabber.getFrameNumber())
		                	{
		                		if (DISPLAY_AREA_HELP_VIDEO)
			                		grabbedImage = grabber.grab();
		                		else
		                			Thread.sleep(100);
		                	}
		                	else if ((grabber2.getLengthInFrames()-100) >= grabber2.getFrameNumber()) {
		                		if (DISPLAY_AREA_HELP_VIDEO)
			                		grabbedImage = grabber2.grab();
		                		else
		                			Thread.sleep(100);
		                	}
		                	else
		                	{
		                		grabber.restart();
	                			grabber2.restart();
			                	/* Grabbed the image from the video */
	                			if (DISPLAY_AREA_HELP_VIDEO)
	                				grabbedImage = grabber.grab();
	                			else
	                				Thread.sleep(100);
		                	}
		                		
						} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
							continue;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		                if (grabbedImage != null) {
		                	/* Turns the image so that it have a mirror effect */
		                	int widthFrame = colorCameraPanel.getWidth();
		                    int heightFrame = colorCameraPanel.getHeight();
		                    int widthCameraImage = grabbedImage.width();
		                    int heightCameraImage = grabbedImage.height();
	
		                    /* Creating dimensions for the camera and the panel area 
		                       Used later for deciding the new dimension that we will resize the image to */
		                    Dimension imgSize = new Dimension(widthCameraImage, heightCameraImage);
		                    Dimension boundary = new Dimension(widthFrame, heightFrame);
		                    
		                    /* Changing the scaling of the grabbed camera image */
		                    Dimension newImagebunderys = CameraPanel.getScaledDimension(imgSize, boundary);
		                    //System.out.println(newImagebunderys);
		                    
		                    BufferedImage bufferdWebcameraImage;
		                    
		                    if((widthFrame > 0) || (heightFrame > 0))
		                    {
		                    	
		                    	bufferdWebcameraImage = grabbedImage.getBufferedImage();
								int type = bufferdWebcameraImage.getType() == 0? BufferedImage.TYPE_INT_ARGB
								        : bufferdWebcameraImage.getType();
	
								BufferedImage resizeImaged = CameraPanel.resizeImage(bufferdWebcameraImage, type, newImagebunderys.width, newImagebunderys.height);
	
								colorCameraPanel.theCamera = resizeImaged;
		                    }
		                    else
		                    {
		                    	bufferdWebcameraImage = grabbedImage.getBufferedImage();
								colorCameraPanel.theCamera = bufferdWebcameraImage;
		                    }
		                    
		                	/* Show image on window */
		                    colorCameraPanel.repaint();
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
        threadDisplayAreaSelectionVideo.setDaemon(true);
        threadDisplayAreaSelectionVideo.start();
		
		/* Sets container backgrounds to white instead of gray for contrast */
		colorCameraPanel.setBackground(Color.WHITE);
		continer1.setBackground(Color.WHITE);
		areaPanel.setBackground(Color.WHITE);
		areaPanel.add(continer1, BorderLayout.SOUTH);
		areaPanel.add(colorCameraPanel, BorderLayout.CENTER);
		headerAndPanelContiner.add(areaPanel, BorderLayout.SOUTH);
		areaPanel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * ACTIVE
	 */
	public void areaSelectionActive(){
		MainPanel.setLeftStage(Program.cameraPanel);

		SettingsPanel.stage = 2;
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);
		
		/* Glass Panel visible and active */
		DISPLAY_AREA_VIDEO = true;
		MainFrame.GET_AREA_BOOLEAN = true;
		
		/* Shows the help video when made active */
		DISPLAY_AREA_HELP_VIDEO = true;
		
		/* Turns off header button */
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
		
		/* New tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Turns ON next back Lable video */
		nextButton.setVisible(true);
		backButton.setVisible(true);
		areaNotSelectedLabel.setVisible(true);
		colorCameraPanel.setVisible(true);
		areaPanel.setVisible(true);
		areaPanel.add(areaLabel, BorderLayout.EAST);
		
		/* Turns OFF area Label */
		areaLabel.setVisible(false);
		
		/* Changing size of panels when button has been pressed*/	
		areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Sets appropriate blue active colors */
		stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_LIGHT_BLUE);
		areaPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* If a area has been selected */
		if(Program.frame.glass.cursorReleased.x > 0 && Program.frame.glass.cursorReleased.y > 0)
		{
			nextButton.setEnabled(true);
			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
		}
	}
	/**
	 * NOT ACTIVE
	 */
	public void areaSelectionNotActive(){
		/* Don't show Glass Panel and turn inactive */
		DISPLAY_AREA_VIDEO = false;
		MainFrame.GET_AREA_BOOLEAN = false;

		/* Area help video disabled */
		DISPLAY_AREA_HELP_VIDEO = false;
		
		/* New tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Sets video and buttons not visible */
		nextButton.setVisible(false);
		backButton.setVisible(false);
		areaNotSelectedLabel.setVisible(false);
		colorCameraPanel.setVisible(false);
		
		/* Sets step label gray for not active */
		stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_GRAY);
		
		if (SettingsPanel.AREA_SELECTED)
		{
			/* Sets step label green when button has been pressed */
			stepLabel.setText(STEP_TEXT_DARK_GREEN);
			
			/* AreaPanel and header Green */
			areaPanel.setBorder(Program.GREEN_BORDER);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_DONE);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_DONE);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_DONE);
			
			/* Sets selected area and label visible */
			areaLabel.setVisible(true);
			areaPanel.setVisible(true);
		}
		else
		{
			/* Changing size of panels when button has been pressed*/	
			areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_OFF);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
			
			/* Sets selected area and label invisible */
			areaLabel.setVisible(false);
			areaPanel.setVisible(false);
			
			/* Sets Header button gray */
			headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_GRAY_IMAGE_ICON);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		}
	}
}

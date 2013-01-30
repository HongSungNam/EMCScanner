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

import com.googlecode.javacv.FrameGrabber.Exception;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.OpenCVFrameGrabber;


public class DensitySettingsSubPanel extends JPanel {
	
	/* Video player */
	/* Imports videos */
	public static OpenCVFrameGrabber grabber3 = new OpenCVFrameGrabber("video/test3.avi");
	public static OpenCVFrameGrabber grabber4 = new OpenCVFrameGrabber("video/test4.mp4");
	public static BufferedImage buffDensityImg = null;
	/* Creates a ColorPanel and adds it to this camera panel */
	public static ColorPanel colorDensityCameraPanel = new ColorPanel(buffDensityImg);
	/* Threads */
	public static Thread threadDisplayDensityVideo;
	
	/* Boolean */
	public static boolean DISPLAY_DENSITY_HELP_VIDEO = false;
	
	/* Buttons */
	public static JButton headerButton = new JButton();
	public static JButton nextButton = new JButton();
	public static JButton backButton = new JButton();
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT = "Press to reselect the density";
	public String PANEL_TOOL_TIP_TEXT = "This is where you select the density to scan";
	
	public String STEP_TEXT_GRAY	 	= "<html> <font color = rgb(120,120,120)>Step 3/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 3/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 3/4</font></html>";
	
	public String NEXT_BUTTON_TOOL_TIP_TEXT = "You need to select an area before you can continue";
	
	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/PanelGreenDensity.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON = new ImageIcon("image/PanelGreenDensityRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 	= new ImageIcon("image/PanelGrayDensity.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 	= new ImageIcon("image/PanelGreenDensityPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 	= new ImageIcon("image/PanelBlueDensity.png");
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 280);
	public Dimension STEP_CONTINER_DIMENSION_DONE = new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 40);
	
	public Dimension AREA_PANEL_DIMENSION_ACTIVE = new Dimension(322, 240);
	public Dimension AREA_PANEL_DIMENSION_DONE = new Dimension(322, 40);
	public Dimension AREA_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 280);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE = new Dimension(322, 80);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 40);
	
	/* JLabel */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel densityPanel = new JPanel();
	public JPanel continer1 = new JPanel();
	
	public DensitySettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);
		
		/* Sets creation values for the header button */
		headerButton.setEnabled(false);
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
		densityPanel.setPreferredSize(AREA_PANEL_DIMENSION_OFF);
		
		/* Next JButton */
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText(NEXT_BUTTON_TOOL_TIP_TEXT);
		nextButton.setPreferredSize(Program.NEXT_BUTTON_DIMENSION);
		nextButton.setEnabled(false);
		nextButton.setIcon(Program.NEXT_BUTTON_ENABLED_IMAGE_ICON);
		nextButton.setDisabledIcon(Program.NEXT_BUTTON_DISABLED_IMAGE_ICON);
		nextButton.setPressedIcon(Program.NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
		nextButton.setDisabledSelectedIcon(Program.NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		/* Back on step JButton */
		backButton.setEnabled(true);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		
		threadDisplayDensityVideo = new Thread("threadDisplayDensity"){
        	public void run(){
	            while (true) {
	            	
            		IplImage grabbedImage = null;/*
	                try {
	                	if ((grabber3.getLengthInFrames()-100) >= grabber3.getFrameNumber())
	                	{
	                		if (DISPLAY_DENSITY_HELP_VIDEO)
	                		{
		                		grabbedImage = grabber3.grab();
	                		}
	                		else{
		                		grabber3.restart();
	                			grabber4.restart();
	                		}
	                	}
	                	else if((grabber4.getLengthInFrames()-100) >= grabber4.getFrameNumber()) {
	                		if (DISPLAY_DENSITY_HELP_VIDEO)
	                		{
		                		grabbedImage = grabber4.grab();
	                		}
	                		else{
		                		grabber3.restart();
	                			grabber4.restart();
	                		}
	                	}
	                	else
	                	{
	                		grabber3.restart();
                			grabber4.restart();
		                	/* Grabbed the image from the video */
		       /*         	grabbedImage = grabber3.grab(); 
	                	}
	                		
					} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
						continue;
					}
	                if (grabbedImage != null) {
	                	/* Turns the image so that it have a mirror effect */
	                /*	int widthFrame = colorDensityCameraPanel.getWidth();
	                    int heightFrame = colorDensityCameraPanel.getHeight();
	                    int widthCameraImage = grabbedImage.width();
	                    int heightCameraImage = grabbedImage.height();

	                    /* Creating dimensions for the camera and the panel area 
	                       Used later for deciding the new dimension that we will resize the image to */
	                  /*  Dimension imgSize = new Dimension(widthCameraImage, heightCameraImage);
	                    Dimension boundary = new Dimension(widthFrame, heightFrame);
	                    
	                    /* Changing the scaling of the grabbed camera image */
	                  /*  Dimension newImagebunderys = CameraPanel.getScaledDimension(imgSize, boundary);
	                    //System.out.println(newImagebunderys);
	                    
	                    BufferedImage bufferdWebcameraImage;
	                    
	                    if((widthFrame > 0) || (heightFrame > 0))
	                    {
	                    	
	                    	bufferdWebcameraImage = grabbedImage.getBufferedImage();
							int type = bufferdWebcameraImage.getType() == 0? BufferedImage.TYPE_INT_ARGB
							        : bufferdWebcameraImage.getType();

							BufferedImage resizeImaged = CameraPanel.resizeImage(bufferdWebcameraImage, type, newImagebunderys.width, newImagebunderys.height);

							colorDensityCameraPanel.theCamera = resizeImaged;
	                    }
	                    else
	                    {
	                    	bufferdWebcameraImage = grabbedImage.getBufferedImage();
	                    	colorDensityCameraPanel.theCamera = bufferdWebcameraImage;
	                    }
	                    
	                	/* Show image on window */
	                /*    colorDensityCameraPanel.repaint();
	                }*/
	            	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
        	}
        };
        threadDisplayDensityVideo.setDaemon(true);
        threadDisplayDensityVideo.start();
		
        /* Sets container backgrounds to white instead of gray for contrast */
        colorDensityCameraPanel.setBackground(Color.WHITE);
		continer1.setBackground(Color.WHITE);
		densityPanel.setBackground(Color.WHITE);
		densityPanel.add(continer1, BorderLayout.SOUTH);
		densityPanel.add(colorDensityCameraPanel, BorderLayout.CENTER);
		headerAndPanelContiner.add(densityPanel, BorderLayout.SOUTH);
		densityPanel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	public void densitySelectionActive(){
		/* Shows the help video when made active */
		DISPLAY_DENSITY_HELP_VIDEO = true;
		
		if(Program.frame.glass.cursorReleased.x > 0 && Program.frame.glass.cursorReleased.y > 0)
		{
			nextButton.setEnabled(true);
			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
		}
		headerButton.setEnabled(false);
		
		headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
		
		nextButton.setVisible(true);
		backButton.setVisible(true);
		colorDensityCameraPanel.setVisible(true);
		
		/* Changing size of panels when button has been pressed*/	
		densityPanel.setPreferredSize(AREA_PANEL_DIMENSION_ACTIVE);

		/* Changing size of panels when button has been pressed*/	
		densityPanel.setPreferredSize(AREA_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		densityPanel.setVisible(true);
		
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_LIGHT_BLUE);
		densityPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		Program.frame.glass.setVisible(true);
	}
}

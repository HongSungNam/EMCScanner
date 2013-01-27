package emcscanner.kth.se;

import static com.googlecode.javacv.cpp.opencv_core.cvFlip;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_GAUSSIAN;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvSmooth;

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
import javax.swing.JComponent;
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
	
	static Thread threadDisplayVideo;
	
	/* Boolean */
	public static boolean DISPLAY_AREA_HELP_VIDEO = false;
	
	/* Panels */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel areaPanel = new JPanel();
	/* Containers for setting up GUI */
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
	
	/* JLabel */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	public JLabel areaNotSelectedLabel = new JLabel(AREA_NOT_SELECTED);

	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/PanelGreenArea.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON = new ImageIcon("image/PanelGreenAreaRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 	= new ImageIcon("image/PanelGrayArea.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 	= new ImageIcon("image/PanelGreenAreaPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 	= new ImageIcon("image/PanelBlueArea.png");
	/* Import the images for the NEXT button */
	public ImageIcon NEXT_BUTTON_ENABLED_IMAGE_ICON		= new ImageIcon("image/ButtonBlueNext.png");
	public ImageIcon NEXT_BUTTON_DISABLED_IMAGE_ICON	= new ImageIcon("image/ButtonGrayNext.png");
	public ImageIcon NEXT_BUTTON_BLUE_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueNextPrest.png");
	public ImageIcon NEXT_BUTTON_GRAY_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonGrayNextPrest.png");
	/* Import the images for the BACK button */
	ImageIcon BACK_BUTTON_ENABLED_IMAGE_ICON = new ImageIcon("image/ButtonBlueBack.png");
	ImageIcon BACK_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonBlueBackPrest.png");
	
	/* Used Colors */
	public Color LIGHT_BLUE_COLOR = new Color(100,150,255); 
	public Color RED_COLOR = new Color(255,0,0); 
	public Color LIGHT_GREEN = new Color(150,255,80); 
	public Color DARK_GREEN_COLOR = new Color(120,200,40);
	public Color LIGHT_GRAY_COLOR = new Color(120,120,120);

	/* Light blue border for the float input text field */
	public Border LIGHT_BLUE_BORDER = BorderFactory.createLineBorder(LIGHT_BLUE_COLOR);
	public Border GREEN_BORDER = BorderFactory.createLineBorder(LIGHT_GREEN);
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);

	public Dimension STEP_CONTINER_DIMENSION_ON = new Dimension(50, 280);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 80);
	
	public Dimension AREA_PANEL_DIMENSION_ON = new Dimension(322, 240);
	public Dimension AREA_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ON = new Dimension(322, 280);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 80);
	
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension NEXT_BUTTON_DIMENSION = new Dimension(90, 50);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	public AreaSettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);

		/* Sets creation values for the header button */
		AreaSettingsSubPanel.headerButton.setEnabled(false);
		AreaSettingsSubPanel.headerButton.setPreferredSize(HEADER_BUTTON_DIMENSION);
		AreaSettingsSubPanel.headerButton.setToolTipText(PANEL_TOOL_TIP_TEXT);
		AreaSettingsSubPanel.headerButton.setOpaque(false);
		AreaSettingsSubPanel.headerButton.setContentAreaFilled(false);
		AreaSettingsSubPanel.headerButton.setBorderPainted(false);
		AreaSettingsSubPanel.headerButton.setIcon(HEADER_ENABLED_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.setDisabledIcon(HEADER_DISABLED_GRAY_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.setPressedIcon(HEADER_ENABLED_PREST_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.setRolloverIcon(HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				DISPLAY_AREA_HELP_VIDEO = false;
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
		
		/* Panel for the frequency input */
		areaPanel.setLayout(new BorderLayout());
		areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_ON);
		
		
		/* Next JButton */
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText(NEXT_BUTTON_TOOL_TIP_TEXT);
		nextButton.setPreferredSize(NEXT_BUTTON_DIMENSION);
		nextButton.setEnabled(false);
		nextButton.setIcon(NEXT_BUTTON_ENABLED_IMAGE_ICON);
		nextButton.setDisabledIcon(NEXT_BUTTON_DISABLED_IMAGE_ICON);
		nextButton.setPressedIcon(NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
		nextButton.setDisabledSelectedIcon(NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DISPLAY_AREA_HELP_VIDEO = false;
			}
		});
		
		backButton.setEnabled(true);
		backButton.setIcon(BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Sets header back to blue */
				SettingsPanel.frequencyPanel.headerButton.setToolTipText(SettingsPanel.frequencyPanel.PANEL_INFORMATION);
				SettingsPanel.frequencyPanel.headerButton.setEnabled(false);
				
	    		/* Turns on */ 
	    		SettingsPanel.frequencyPanel.frequencyPanel.setBorder(SettingsPanel.frequencyPanel.LIGHT_BLUE_BORDER);
	    		SettingsPanel.frequencyPanel.floatInputTextField.setVisible(true);
				SettingsPanel.frequencyPanel.textNote.setVisible(true);
				SettingsPanel.frequencyPanel.textMoreThen.setVisible(true);
				SettingsPanel.frequencyPanel.textLessThen.setVisible(true);
				FrequensySettingsSubPanel.nextButton.setVisible(true);
				SettingsPanel.frequencyPanel.stepLabel.setText(SettingsPanel.frequencyPanel.STEP_TEXT_LIGHT_BLUE);
				SettingsPanel.frequencyPanel.frequencyPanel.setPreferredSize(SettingsPanel.frequencyPanel.FREQUENCY_PANEL_DIMENSION_ON);
				SettingsPanel.frequencyPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.frequencyPanel.HEADER_AND_PANEL_CONTINER_DIMENSION_ON);
				SettingsPanel.frequencyPanel.stepContiner.setPreferredSize(SettingsPanel.frequencyPanel.STEP_CONTINER_DIMENSION_ON);
				
				/* Turns off */
				SettingsPanel.frequencyPanel.frequencyLabel.setVisible(false);
				FrequensySettingsSubPanel.FREQUENCY_SELECTED = false;
				
				/* Removing next step */
				headerAndPanelContiner.remove(SettingsPanel.areaPanel.areaPanel);
				headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_GRAY_IMAGE_ICON);
				stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_GRAY);
				
				DISPLAY_AREA_HELP_VIDEO = false;
			}
		});

		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		/* Containers for GUI look */
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		continer1.add(areaNotSelectedLabel, BorderLayout.CENTER);
		
		threadDisplayVideo = new Thread("threadDisplay"){
        	public void run(){
	            while (true) {
            		IplImage grabbedImage = null;
	                try {
	                	if ((grabber.getLengthInFrames()-100) >= grabber.getFrameNumber())
	                	{
	                		if (DISPLAY_AREA_HELP_VIDEO)
	                		{
		                		//System.out.println(grabber.getLengthInFrames() + " : " + grabber.getFrameNumber());
		                		grabbedImage = grabber.grab();
	                		}
	                		else{
		                		grabber.restart();
	                			grabber2.restart();
	                		}
	                	}
	                	else if((grabber2.getLengthInFrames()-100) >= grabber2.getFrameNumber()) {
	                		if (DISPLAY_AREA_HELP_VIDEO)
	                		{
		                		//System.out.println(grabber2.getLengthInFrames() + " : " + grabber2.getFrameNumber());
		                		grabbedImage = grabber2.grab();
	                		}
	                		else{
		                		grabber.restart();
	                			grabber2.restart();
	                		}
	                	}
	                	else
	                	{
	                		grabber.restart();
                			grabber2.restart();
		                	/* Grabbed the image from the video */
		                	grabbedImage = grabber.grab(); 
	                	}
	                		
					} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
						continue;
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
        	}
        };
        threadDisplayVideo.setDaemon(true);
        threadDisplayVideo.start();
		
		/* Sets container backgrounds to white instead of gray for contrast */
		continer1.setBackground(Color.WHITE);
		areaPanel.setBackground(Color.WHITE);
		areaPanel.setBorder(LIGHT_BLUE_BORDER);
		areaPanel.add(continer1, BorderLayout.SOUTH);
		areaPanel.add(colorCameraPanel, BorderLayout.CENTER);
		
		
		
		this.add(headerAndPanelContiner);
	}
}

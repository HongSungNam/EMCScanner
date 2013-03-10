package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
/**
 * 
 * @author Jonas
 *
 */
public class CameraPanel extends JPanel{

	/**
	 * Camera Panel ID
	 */
	private static final long serialVersionUID = 992833980333183668L;

	/**
	 * Shows the input feed from the camera
	 */
	public static ColorPanel colorCameraPanel;
	/**
	 * Input buffered image from the camera.
	 */
	public static BufferedImage buffImg = null;
	/**
	 * Camera input thread.
	 */
	public static Thread threadDisplayCamera;
	
	/**
	 * Grabber for grabbing the frames from the web camera. <br>
	 * There are different frame grabbers but from what i have noticed this is the best one for this camera.
	 */
	public FrameGrabber grabber;
	/**
	 * New camera image dimensions when camera has been fitted to color frame.
	 */
	public Dimension CAMERA_VIEW_BOUNDERYS_DIMENSION;
	
	/**
	 * Photo taken from the camera.
	 */
	public IplImage phototaken;
	
	/**
	 * True: Shows the web camera input 
	 * False dosen't show the web camera input.
	 */
	public static boolean DISPLAY_WEB_CAMERA_INPUT = true;
	/**
	 * True: Stops camera from grabbing any more images.
	 * False: Runs the camera.
	 */
	public static boolean stopCamera = false;
	/**
	 * True: Saves an image to phototaken(IplImage).
	 */
	public static boolean SAVE_IMAGE = false;
	
	/**
	 * Camera Panel Dimension
	 * Value type: Integer
	 * Width: 2*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3
	 * Height: Not specified
	 */
	private Dimension cameraPanelDimension = new Dimension((int) (2*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3), 0);
	
	/**
	 * Runns the camera when started 
	 */
	public CameraPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(getCameraPanelDimension());
        try {
        	/* There exists many different types of frame grabbers. 
        	   This one is the one that is working for this camera. */
        	grabber = new OpenCVFrameGrabber(0);

        	int width = 4096; // Is at the moment only Full HD butt when I will find out how to take a photo in 12MP
        	int height = 3072; // Is at the moment only Full HD butt when I will find out how to take a photo in 12MP
        	
        	/* Sets the grabbers resolution */
        	grabber.setImageHeight(height);
        	grabber.setImageWidth(width);
        	
        	/* Starts the camera */
        	grabber.start();
        	
        	/* Creates a ColorPanel and adds it to this camera panel */
            this.add(colorCameraPanel = new ColorPanel(buffImg));
            
            threadDisplayCamera = new Thread("threadDisplayCamera"){
            	public void run(){
		            while (true) {
		            	IplImage grabbedImage = null;
		            	if (!stopCamera)
		            	{
			                try {
			                	/* Grabbed the image from the camera */
			                	if(DISPLAY_WEB_CAMERA_INPUT)
			                		grabbedImage = grabber.grab();
			                	else 
			                		grabbedImage = null;
							} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
								continue;
							}
			                if(SAVE_IMAGE)
		                    {
			                    //cvFlip(grabbedImage, grabbedImage, 1);
			                    //cvSmooth(grabbedImage, grabbedImage, CV_GAUSSIAN, 3);
                    			phototaken = grabbedImage;
                    			try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                    	SAVE_IMAGE = false;
		                    }
			                if (grabbedImage != null) {
			                	/* Turns the image so that it have a mirror effect */
			                    cvFlip(grabbedImage, grabbedImage, 1);
			                    cvSmooth(grabbedImage, grabbedImage, CV_GAUSSIAN, 3);
			                    
			                    int widthFrame = Program.cameraPanel.getWidth();
			                    int heightFrame = Program.cameraPanel.getHeight();
			                    int widthCameraImage = grabbedImage.width();
			                    int heightCameraImage = grabbedImage.height();
			                    
			                    /* Creating dimensions for the camera and the panel area 
			                       Used later for deciding the new dimension that we will resize the image to */
			                    Dimension imgSize = new Dimension(widthCameraImage, heightCameraImage);
			                    Dimension boundary = new Dimension(widthFrame, heightFrame);
			                    
			                    /* Changing the scaling of the grabbed camera image */
			                    Dimension newImagebunderys = getScaledDimension(imgSize, boundary);
			                    
			                    /* Camera view dimension is being updated all the time so when we chose an area we will know where on the table we have chosen*/ 
			                    CAMERA_VIEW_BOUNDERYS_DIMENSION = new Dimension(newImagebunderys.width, newImagebunderys.height);
			                    
			                    if((widthFrame > 0) || (heightFrame > 0))
			                    {
			                    	
			                    	BufferedImage bufferdWebcameraImage = grabbedImage.getBufferedImage();
									int type = bufferdWebcameraImage.getType() == 0? BufferedImage.TYPE_INT_ARGB
									        : bufferdWebcameraImage.getType();
	
									BufferedImage resizeImaged = resizeImage(bufferdWebcameraImage, type, newImagebunderys.width, newImagebunderys.height);
	
									colorCameraPanel.theCamera = resizeImaged;
			                    }
			                    try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
			                	/* Show image on window */
			                    colorCameraPanel.repaint();
			                }
			            }
		            	else
		            	{
		            		try {
								grabber.stop();
							} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
								e.printStackTrace();
							}
		            	}
		            }
            	}
            };
            threadDisplayCamera.setDaemon(true);
            threadDisplayCamera.start();
        } catch (Exception e) {
        }
        
	}
	/**
	 * Resizes image
	 * 
	 * @param originalImage
	 * @param type
	 * @param img_width
	 * @param img_height
	 * @return
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int type, Integer img_width, Integer img_height){
		BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, img_width, img_height, null);
		g.dispose();
		
		return resizedImage;
	}
	/**
	 * Scales to new dimension.
	 * 
	 * @param imgSize
	 * @param boundary
	 * @return
	 */
	public static Dimension getScaledDimension(Dimension section, Dimension screen) {
		double scaleX = screen.getWidth() / section.getWidth();
		double scaleY = screen.getHeight() / section.getHeight();
		double scale = Math.min(scaleX, scaleY);
		 
		int x = (int)(scale * section.width); /* floor */
		int y = (int)(scale * section.height);
		
		return new Dimension(x <= 0 ? 1 : x, y <= 0 ? 1 : y);
	}
	/**
	 * Stops the camera.
	 */
	public void stopCameraFunktion(){

		try {
			grabber.stop();
		} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void cordinates(){
		if(Program.frame.glass.cursorPressed.x < Program.frame.glass.cursorReleased.x)
		{
			if(Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y)
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.setAREA_SELECTED_END_X(0);
				else
					SettingsPanel.setAREA_SELECTED_END_X(Program.frame.glass.cursorReleased.x);
				
				SettingsPanel.setAREA_SELECTED_START_X(Program.frame.glass.cursorPressed.x);
				SettingsPanel.setAREA_SELECTED_START_Y(Program.frame.glass.cursorPressed.y);
				SettingsPanel.setAREA_SELECTED_END_Y(Program.frame.glass.cursorReleased.y);
			}
			else
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.setAREA_SELECTED_END_X(0);
				else
					SettingsPanel.setAREA_SELECTED_END_X(Program.frame.glass.cursorReleased.x);
				
				SettingsPanel.setAREA_SELECTED_START_X(Program.frame.glass.cursorPressed.x);
				SettingsPanel.setAREA_SELECTED_START_Y(Program.frame.glass.cursorReleased.y);
				SettingsPanel.setAREA_SELECTED_END_Y(Program.frame.glass.cursorPressed.y);
			}
		}
		else
		{
			if(Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y)
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.setAREA_SELECTED_START_X(0);
				else
					SettingsPanel.setAREA_SELECTED_START_X(Program.frame.glass.cursorReleased.x);
				
				SettingsPanel.setAREA_SELECTED_END_X(Program.frame.glass.cursorPressed.x);
				SettingsPanel.setAREA_SELECTED_START_Y(Program.frame.glass.cursorPressed.y);
				SettingsPanel.setAREA_SELECTED_END_Y(Program.frame.glass.cursorReleased.y);
			}
			else
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.setAREA_SELECTED_START_X(0);
				else
					SettingsPanel.setAREA_SELECTED_START_X(Program.frame.glass.cursorReleased.x);
					
				SettingsPanel.setAREA_SELECTED_END_X(Program.frame.glass.cursorPressed.x);
				
				SettingsPanel.setAREA_SELECTED_START_Y(Program.frame.glass.cursorReleased.y);
				SettingsPanel.setAREA_SELECTED_END_Y(Program.frame.glass.cursorPressed.y);
			}
		}
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getCameraPanelDimension() {
		return cameraPanelDimension;
	}
	/**
	 * 
	 * @param cameraPanelDimension
	 */
	public void setCameraPanelDimension(Dimension cameraPanelDimension) {
		this.cameraPanelDimension = cameraPanelDimension;
	}
}
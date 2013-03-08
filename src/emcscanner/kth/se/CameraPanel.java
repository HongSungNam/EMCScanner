package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
/**
 * 
 * @author Jonas
 *
 */
public class CameraPanel extends JPanel{

	public static ColorPanel colorCameraPanel;
	
	public static BufferedImage buffImg = null;
	public static Thread threadDisplayCamera;
	
	public FrameGrabber grabber;
	public Dimension CAMERA_VIEW_BOUNDERYS_DIMENSION;
	
	public IplImage phototaken;
	
	public static boolean DISPLAY_WEB_CAMERA_INPUT = true;
	public static boolean stopCamera = false;
	public static boolean SAVE_IMAGE = false;
	
	private Dimension cameraPanelDimension = new Dimension((int) (2*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3), 0);
	
	public CameraPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(getCameraPanelDimension());
        try {
        	/* There exists many different types of frame grabbers. 
        	   This one is the one that is working for this camera. */
        	grabber = new OpenCVFrameGrabber(0);
        	
        	int width = 4096;
        	int height = 3072;
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
									// TODO Auto-generated catch block
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
								// TODO Auto-generated catch block
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
        this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override 
			public void mousePressed(MouseEvent arg0) {
				if (MainFrame.GET_AREA_BOOLEAN)
				{
		        	if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
		        	{
		        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
		        		{
		        			Program.frame.glass.setVisible(true);
		        			Program.frame.glass.cursorPressed = new Point(arg0.getPoint());
			        		Program.frame.glass.cursorReleased.x = Program.frame.glass.cursorPressed.x;
			        		Program.frame.glass.cursorReleased.y = Program.frame.glass.cursorPressed.y;
			        		Program.frame.MOUSE_RELEASED_BOOLEAN = false;
		        		}
		        	}
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (MainFrame.GET_AREA_BOOLEAN)
				{
		        	if(Program.frame.MOUSE_RELEASED_BOOLEAN == false)
		        	{
						if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
			        	{
			        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() )//+ MainFrame.menuBar.getHeight()
			        		{			        			
			        			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
			        			Program.frame.glass.cursorReleased = new Point(arg0.getPoint());
			        			Program.frame.glass.repaint();
			        			
			        			cordinates();
			        		}
			        		else if(arg0.getY() <= 0)
			        		{
			        			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
			        			Program.frame.glass.cursorReleased.x = arg0.getX();
			        			Program.frame.glass.cursorReleased.y = 0;
			        			Program.frame.glass.repaint();

			        			cordinates();
			        		}
			        		else
			        		{
			        			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
			        			Program.frame.glass.cursorReleased.x = arg0.getX();
			        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
			        			Program.frame.glass.repaint();

			        			cordinates();
			        		}
			        	}
			        	else
			        	{
			        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
			        		{
			        			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
			        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth(); 
			        			Program.frame.glass.cursorReleased.y = arg0.getY();
			        			Program.frame.glass.repaint();

			        			cordinates();
			        		}
			        		else if(arg0.getY() <= 0)
			        		{
			        			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
			        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			Program.frame.glass.cursorReleased.y = 0;
			        			Program.frame.glass.repaint();

			        			cordinates();
			        		}
			        		else
			        		{
			        			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
			        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
			        			Program.frame.glass.repaint();

			        			cordinates();
			        		}
			        	}

        				AreaSettingsSubPanel.nextButton.setEnabled(true);
        				if (Program.frame.glass.cursorPressed != null) 
        		        {
	        				if((Program.frame.glass.cursorPressed.x < Program.frame.glass.cursorReleased.x) )
	        	    		{
	        	    			if((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
	        	    			{
			        				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
											" Width: " + (int) (Program.frame.glass.cursorReleased.x - Program.frame.glass.cursorPressed.x + 1) + 
											"<br/> Hight: " +(int) (Program.frame.glass.cursorReleased.y - Program.frame.glass.cursorPressed.y + 1) + "</p align></html>");
	        	    			}
	        	    			else
	            				{
	        	    				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
											" Width: " + (int) (Program.frame.glass.cursorReleased.x - Program.frame.glass.cursorPressed.x + 1) + 
											"<br/> Hight: " +(int) (Program.frame.glass.cursorPressed.y - Program.frame.glass.cursorReleased.y + 1) + "</p align></html>");
	            				}
	        	    		}
	        				else
	            			{
	        					if((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
	        	    			{
	        	    				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
											" Width: " + (int) (Program.frame.glass.cursorPressed.x - Program.frame.glass.cursorReleased.x + 1) + 
											"<br/> Hight: " +(int) (Program.frame.glass.cursorReleased.y - Program.frame.glass.cursorPressed.y + 1) + "</p align></html>");
	        	    			}
	        					else
	        	    			{
	        	    				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
											" Width: " + (int) (Program.frame.glass.cursorPressed.x - Program.frame.glass.cursorReleased.x + 1) + 
											"<br/> Hight: " +(int) (Program.frame.glass.cursorPressed.y - Program.frame.glass.cursorReleased.y + 1) + "</p align></html>");
	        	    			}
	            			}
        		        }	
		        	}
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent evt) {
			}
			public void mouseDragged(MouseEvent arg0){
				if (MainFrame.GET_AREA_BOOLEAN)
				{
					if (Program.frame.MOUSE_RELEASED_BOOLEAN == false)
					{
						if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
			        	{
			        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
			        		{
			        			Program.frame.glass.cursorReleased = new Point(arg0.getPoint());
			        			Program.frame.glass.repaint();
			        		}
			        		else if(arg0.getY() <= 0)
			        		{
			        			Program.frame.glass.cursorReleased.x = arg0.getX();
			        			Program.frame.glass.cursorReleased.y = 0;
			        			Program.frame.glass.repaint();
			        		}
			        		else
			        		{
			        			Program.frame.glass.cursorReleased.x = arg0.getX();
			        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
			        			Program.frame.glass.repaint();
			        		}
			        	}
			        	else
			        	{
			        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
			        		{
			        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth(); 
			        			Program.frame.glass.cursorReleased.y = arg0.getY();
			        			Program.frame.glass.repaint();
			        		}
			        		else if(arg0.getY() <= 0)
			        		{
			        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			Program.frame.glass.cursorReleased.y = 0;
			        			Program.frame.glass.repaint();
			        		}
			        		else
			        		{
			        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
			        			Program.frame.glass.repaint();
			        		}
			        	}
					}
				}
			}
		});
	}
	/**
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
	public void stopCameraFunktion(){

		try {
			grabber.stop();
		} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
			e.printStackTrace();
		}
	}
	private void cordinates(){
		if(Program.frame.glass.cursorPressed.x < Program.frame.glass.cursorReleased.x)
		{
			if(Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y)
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.AREA_SELECTED_END_X = 0;
				else
					SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorReleased.x;
				
				SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorPressed.x;
				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorPressed.y;
				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorReleased.y;
			}
			else
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.AREA_SELECTED_END_X = 0;
				else
					SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorReleased.x;
				
				SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorPressed.x;
				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorReleased.y;
				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorPressed.y;
			}
		}
		else
		{
			if(Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y)
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.AREA_SELECTED_START_X = 0;
				else
					SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorReleased.x;
				
				SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorPressed.x;
				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorPressed.y;
				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorReleased.y;
			}
			else
			{
				if (Program.frame.glass.cursorReleased.x < 0)
					SettingsPanel.AREA_SELECTED_START_X = 0;
				else
					SettingsPanel.AREA_SELECTED_START_X = Program.frame.glass.cursorReleased.x;
					
				SettingsPanel.AREA_SELECTED_END_X = Program.frame.glass.cursorPressed.x;
				
				SettingsPanel.AREA_SELECTED_START_Y = Program.frame.glass.cursorReleased.y;
				SettingsPanel.AREA_SELECTED_END_Y = Program.frame.glass.cursorPressed.y;
			}
		}
	}
	public Dimension getCameraPanelDimension() {
		return cameraPanelDimension;
	}
	public void setCameraPanelDimension(Dimension cameraPanelDimension) {
		this.cameraPanelDimension = cameraPanelDimension;
	}
}
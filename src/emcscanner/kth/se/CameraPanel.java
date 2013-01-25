package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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
	BufferedImage buffImg = null;
	static Thread threadDisplayCamera;
	
	public FrameGrabber grabber;
	public Dimension CAMERA_VIEW_BOUNDERYS_DIMENSION;
	
	public CameraPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));

        try {
        	/* There exists many different types of frame grabbers. 
        	   This one is the one that is working for this camera. */
        	grabber = new OpenCVFrameGrabber(0);
        	
        	/* Sets the grabbers resolution */
        	grabber.setImageHeight(1080);
        	grabber.setImageWidth(1920);
        	
        	/* Starts the camera */
        	grabber.start();
        	
        	/* Creates a ColorPanel and adds it to this camera panel */
            final ColorPanel colorCameraPanel;
            this.add(colorCameraPanel = new ColorPanel(buffImg));
            
            threadDisplayCamera = new Thread("threadDisplay"){
            	public void run(){
		            while (true) {
		            	IplImage grabbedImage;
		                try {
		                	/* Grabbed the image from the camera */
		                	grabbedImage = grabber.grab();
						} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
							continue;
						}
		                if (grabbedImage != null) {
		                	/* Turns the image so that it have a mirror effect */
		                    cvFlip(grabbedImage, grabbedImage, 1);// l - r = 90_degrees_steps_anti_clockwise
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
		                    CAMERA_VIEW_BOUNDERYS_DIMENSION = newImagebunderys;
		                    
		                    if((widthFrame > 0) || (heightFrame > 0))
		                    {
		                    	
		                    	BufferedImage bufferdWebcameraImage = grabbedImage.getBufferedImage();
								int type = bufferdWebcameraImage.getType() == 0? BufferedImage.TYPE_INT_ARGB
								        : bufferdWebcameraImage.getType();

								BufferedImage resizeImaged = resizeImage(bufferdWebcameraImage, type, newImagebunderys.width, newImagebunderys.height);

								colorCameraPanel.theCamera = resizeImaged;
		                    }
		                    
		                	/* Show image on window */
		                    colorCameraPanel.repaint();
		                	//cvSaveImage((i++)+"-aa.jpg", img);
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
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = 0;
	    int new_height = 0;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
}
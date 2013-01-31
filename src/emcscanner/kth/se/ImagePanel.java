package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.CvBox2D;
import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.CvPoint2D32f;
import com.googlecode.javacv.cpp.opencv_core.CvSize;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class ImagePanel extends JPanel{
	public ColorPanel colorPanel;
	
	public static BufferedImage buffImg = null;
	
	public Dimension PHOTO_VIEW_BOUNDERYS_DIMENSION;
	
	public ImagePanel(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
	}
	public void setPhoto(){
		IplImage photo = cvLoadImage("webcam photo/WEBCAM_PHOTO.jpg");
		CvMat rectangleImage;
				
		rectangleImage = cvCreateMat((int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X),
									 (int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y), 
									 		CV_32SC2);
		
		CvRect rect = cvRect((int) SettingsPanel.AREA_SELECTED_START_X, (int) SettingsPanel.AREA_SELECTED_START_Y, 
							 (int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X), 
							 (int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y));
		
		
		colorPanel = new ColorPanel(buffImg);
		
		int widthPhoto = SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.width;
        int heightPhoto = SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.height;
        int widthCameraImage = photo.width();
        int heightCameraImage = photo.height();
        
        /* Creating dimensions for the camera and the panel area 
           Used later for deciding the new dimension that we will resize the image to */
        Dimension imgSize = new Dimension(widthCameraImage, heightCameraImage);
        Dimension boundary = SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION;
        
        /* Changing the scaling of the grabbed camera image */
        Dimension newImagebunderys = CameraPanel.getScaledDimension(imgSize, boundary);
        
        /* Camera view dimension is being updated all the time so when we chose an area we will know where on the table we have chosen*/ 
        //PHOTO_VIEW_BOUNDERYS_DIMENSION = newImagebunderys;
        
        if((widthPhoto > 0) || (heightPhoto > 0))
        {
        	
        	BufferedImage bufferdWebcameraImage = photo.getBufferedImage();
			int type = bufferdWebcameraImage.getType() == 0? BufferedImage.TYPE_INT_ARGB
			        : bufferdWebcameraImage.getType();

			BufferedImage resizeImaged = CameraPanel.resizeImage(bufferdWebcameraImage, type, newImagebunderys.width, newImagebunderys.height);
			
			photo = IplImage.createFrom(resizeImaged);
			
			CvMat photo2 = cvGetSubRect(photo.asCvMat(), rectangleImage ,rect); 
			
			BufferedImage buffPhoto2 = photo2.asIplImage().getBufferedImage();
			
			
			
			int widthFrame = Program.cameraPanel.getWidth();
	        int heightFrame =Program.cameraPanel.getHeight();
	        
	        System.out.println(widthFrame);
	        System.out.println(heightFrame);
	        int widthCameraPhoto = buffPhoto2.getWidth();
	        int heightCameraPhoto = buffPhoto2.getHeight();
	        System.out.println(widthCameraPhoto);
	        System.out.println(heightCameraPhoto);
	        
	        Dimension imgSize2 = new Dimension(widthCameraPhoto, heightCameraPhoto);
	        Dimension boundary2 = new Dimension(widthFrame, heightFrame);
	        
	        /* Changing the scaling of the grabbed camera image */
	        Dimension newImagebunderys2 = CameraPanel.getScaledDimension(imgSize2, boundary2);
			
	        /* Camera view dimension is being updated all the time so when we chose an area we will know where on the table we have chosen*/ 
	        PHOTO_VIEW_BOUNDERYS_DIMENSION = newImagebunderys2;

	        if((widthFrame > 0) || (heightFrame > 0))
	        {
	        	BufferedImage bufferdWebcameraPhoto = buffPhoto2;
				int type1 = bufferdWebcameraPhoto.getType() == 0? BufferedImage.TYPE_INT_ARGB
				        : bufferdWebcameraPhoto.getType();
				
				BufferedImage resizeImaged2 = CameraPanel.resizeImage(bufferdWebcameraPhoto, type1, newImagebunderys2.width, newImagebunderys2.height);
	        
				colorPanel.theCamera = resizeImaged2;
	        }
        }
		
        colorPanel.repaint();
		this.add(colorPanel);
	}
}

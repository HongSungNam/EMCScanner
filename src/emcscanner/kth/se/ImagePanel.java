package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

/**
 * 
 * @author Jonas
 *
 */
public class ImagePanel extends JPanel{
	/**
	 * Image Panel ID
	 */
	private static final long serialVersionUID = -1479410513118668133L;
	
	public static BufferedImage buffImg = null;
	public static IplImage showPhoto;
	public ColorPanel colorPanel = new ColorPanel(buffImg);
	
	public IplImage photo;
	public IplImage iplPhoto2;
	private static boolean imsgrTaken = false;
	private static boolean firstTimeRezised = true;
	
	public int newWidthPhoto;
	public int newHeightPhoto;
	/**
	 * 
	 */
	public ImagePanel(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(Program.cameraPanel.getCameraPanelDimension());
	}
	/**
	 * 
	 */
	public void setPhoto(){
		this.photo = Program.cameraPanel.phototaken;
		
		SettingsPanel.setPhoto(this.photo);
        cvFlip(this.photo, this.photo, 1);
        cvSmooth(this.photo, this.photo, CV_GAUSSIAN, 3);
        
		/* Create new area selection values depending on the image size. */
    	SettingsPanel.setAREA_SELECTED_IMAGE_DEPENDENT_START_X(((SettingsPanel.getAREA_SELECTED_START_X() * this.photo.width())  / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.width));
    	SettingsPanel.setAREA_SELECTED_IMAGE_DEPENDENT_START_Y(((SettingsPanel.getAREA_SELECTED_START_Y() * this.photo.height()) / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.height));
    	SettingsPanel.setAREA_SELECTED_IMAGE_DEPENDENT_END_X(((SettingsPanel.getAREA_SELECTED_END_X()     * this.photo.width() ) / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.width ));
    	SettingsPanel.setAREA_SELECTED_IMAGE_DEPENDENT_END_Y(((SettingsPanel.getAREA_SELECTED_END_Y()     * this.photo.height()) / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.height));
    	SettingsPanel.setAREA_SELECTED_IMAGE_DEPENDENT_WIDTH((SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_END_X()  - SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_START_X()));
    	SettingsPanel.setAREA_SELECTED_IMAGE_DEPENDENT_HEIGHT((SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_END_Y() - SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_START_Y()));
    	
		CvMat rectangleImage = cvCreateMat((int) SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_WIDTH(),
										   (int) SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_HEIGHT(), 
										   CV_32SC2);
		
		CvRect rect = cvRect((int) SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_START_X(),
							 (int) SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_START_Y(),
							 (int) SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_WIDTH(),
							 (int) SettingsPanel.getAREA_SELECTED_IMAGE_DEPENDENT_HEIGHT());
		
		
		CvMat photo2 = cvGetSubRect(this.photo.asCvMat(), rectangleImage, rect); 

		iplPhoto2 = photo2.asIplImage();
		
    	cvSaveImage("webcam photo/WebCameraPhoto.png", iplPhoto2);
    	
		int widthPhotoArea = Program.cameraPanel.getWidth();
        int heightPhotoArea = Program.cameraPanel.getHeight();
        int widthCroptImage = iplPhoto2.width();
        int heightCroptImage = iplPhoto2.height();
        
        /* CROPT_IMAGE SIZE */ 
        SettingsPanel.CROPT_PHOTO_DIMENSION = new Dimension(widthCroptImage, heightCroptImage);
        
        /* Creating dimensions for the camera and the panel area 
           Used later for deciding the new dimension that we will resize the image to */
        Dimension imgSize2 = new Dimension(widthCroptImage, heightCroptImage);
        Dimension boundary2 = new Dimension(widthPhotoArea, heightPhotoArea);
        /* Changing the scaling of the grabbed camera image */
        Dimension newImagebunderys1 = CameraPanel.getScaledDimension(imgSize2, boundary2);
        
        /* Dimension is being updated all the time so we know where to draw the lines  */ 
        SettingsPanel.PHOTO_VIEW_DIMENSION = newImagebunderys1;

        newWidthPhoto  = newImagebunderys1.width;
        newHeightPhoto  = newImagebunderys1.height;
        
        IplImage ipl = IplImage.create(newWidthPhoto, newHeightPhoto, photo.depth(), photo.nChannels());
		
		cvResize(iplPhoto2, ipl, CV_INTER_LANCZOS4);
		
		this.photo = ipl;
        
		colorPanel.theCamera = ipl.getBufferedImage();
		
		ImagePanel.setImageTaken(true);

        this.colorPanel.repaint();
        
		this.add(colorPanel);
	}
	/**
	 * Resizes the Photo when needed to 
	 */
	public void resizePhoto(){
		int widthFrame = this.getWidth();
		int heightFrame = this.getHeight();
        int widthCameraPhoto = this.photo.width();
        int heightCameraPhoto = this.photo.height();

        Dimension imgSize2 = new Dimension(widthCameraPhoto, heightCameraPhoto);
        Dimension boundary2 = new Dimension(widthFrame, heightFrame);
        /* Changing the scaling of the grabbed camera image */
        Dimension newImagebunderys1 = CameraPanel.getScaledDimension(imgSize2, boundary2);

        int width  = newImagebunderys1.width;
        int height  = newImagebunderys1.height;
        
        /* Dimension is being updated all the time so we know where to draw the lines  */ 
        SettingsPanel.PHOTO_VIEW_DIMENSION = newImagebunderys1;
        
        IplImage ipl = IplImage.create(width, height, photo.depth(), photo.nChannels());
        
        cvResize(this.photo, ipl, CV_INTER_LANCZOS4);

		colorPanel.theCamera = ipl.getBufferedImage();
        this.colorPanel.repaint();
	}
	/**
	 * 
	 * @return
	 */
	public static boolean isImageTaken() {
		return imsgrTaken;
	}
	/**
	 * 
	 * @param i
	 */
	public static void setImageTaken(boolean i) {
		imsgrTaken = i;
	}
	/**
	 * 
	 * @return
	 */
	public static boolean isFirstTimeRezised() {
		return firstTimeRezised;
	}
	/**
	 * 
	 * @param i
	 */
	public static void setFirstTimeRezised(boolean i) {
		firstTimeRezised = i;
	}
}

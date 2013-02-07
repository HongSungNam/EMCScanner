package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;


public class ImagePanel extends JPanel{
	public static BufferedImage buffImg = null;
	public ColorPanel colorPanel = new ColorPanel(buffImg);
	
	public IplImage photo;
	public static boolean IMAGE_TAKEN = false;
	public static boolean FIRST_TIME_REZISED = true;
	
	public ImagePanel(){
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
	}
	public void setPhoto(){
		this.photo = cvLoadImage("webcam photo/WEBCAM_PHOTO.jpg");
        cvFlip(this.photo, this.photo, 1);
        cvSmooth(this.photo, this.photo, CV_GAUSSIAN, 3);

		/* Create new area selection values depending on the image size. */
    	SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_X = ((SettingsPanel.AREA_SELECTED_START_X * this.photo.width())  / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.width);
    	SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_Y = ((SettingsPanel.AREA_SELECTED_START_Y * this.photo.height()) / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.height);
    	SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_X   = ((SettingsPanel.AREA_SELECTED_END_X   * this.photo.width() ) / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.width );
    	SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_Y   = ((SettingsPanel.AREA_SELECTED_END_Y   * this.photo.height()) / SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION.height);
    	SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH   = (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_X  - SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_X + 1);
    	SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT  = (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_Y  - SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_Y + 1);
    	
    	
    	/* Sets values of Max density height and width */
    	SettingsPanel.densityPanel.widthLabel.setText("<html><font color = rgb(100,150,255)> Width: </font></html>");
    	SettingsPanel.densityPanel.widthLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH * SettingsPanel.densityPanel.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
    	SettingsPanel.densityPanel.widthLabel0.setText("<html> &nbsp&gt 0&nbsp </html>");
    	SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(100,150,255)> Height: </font></html> ");
    	SettingsPanel.densityPanel.heightLabelValue.setText("<html>" + (int)(SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT * SettingsPanel.densityPanel.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
    	SettingsPanel.densityPanel.heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
		   			
		CvMat rectangleImage = cvCreateMat((int) SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH,
										   (int) SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT, 
										   CV_32SC2);
		
		CvRect rect = cvRect((int) SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_X,
							 (int) SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_Y,
							 (int) SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH,
							 (int) SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT);
		
		
		CvMat photo2 = cvGetSubRect(this.photo.asCvMat(), rectangleImage, rect); 
		
		IplImage iplPhoto2 = photo2.asIplImage();
		
		int widthPhotoArea = Program.cameraPanel.getWidth();
        int heightPhotoArea = Program.cameraPanel.getHeight();
        int widthCroptImage = iplPhoto2.width();
        int heightCroptImage = iplPhoto2.height();
        
        /* Creating dimensions for the camera and the panel area 
           Used later for deciding the new dimension that we will resize the image to */
        Dimension imgSize2 = new Dimension(widthCroptImage, heightCroptImage);
        Dimension boundary2 = new Dimension(widthPhotoArea, heightPhotoArea);
        /* Changing the scaling of the grabbed camera image */
        Dimension newImagebunderys1 = CameraPanel.getScaledDimension(imgSize2, boundary2);
        
        /* CROPT_IMAGE SIZE */ 
        SettingsPanel.CROPT_PHOTO_DIMENSION = boundary2;
        
        /* Dimension is being updated all the time so we know where to draw the lines  */ 
        SettingsPanel.PHOTO_VIEW_DIMENSION = newImagebunderys1;

        int width  = newImagebunderys1.width;
        int height  = newImagebunderys1.height;
        
        IplImage ipl = IplImage.create(width, height, photo.depth(), photo.nChannels());
		
		cvResize(iplPhoto2, ipl, CV_INTER_LANCZOS4);
		
		this.photo = ipl;
		
		colorPanel.theCamera = ipl.getBufferedImage();
		
		ImagePanel.IMAGE_TAKEN = true;

        this.colorPanel.repaint();
        
		this.add(colorPanel);
	}
	/*
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
}

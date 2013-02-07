package emcscanner.kth.se;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class FrameGrabberThread extends Thread {
	private boolean DISPLAY_VIDEO;
	private boolean DISPLAY_HELP_VIDEO;
	private int stage;
	
	/* Frame grabbers for video */
	public OpenCVFrameGrabber grabber;
	public OpenCVFrameGrabber grabber2;
	
	public FrameGrabberThread(int stage, String string){
		this.stage = stage;
		if (this.stage == 1)
		{
    		DISPLAY_VIDEO = FrequensySettingsSubPanel.DISPLAY_VIDEO;
    		DISPLAY_HELP_VIDEO = FrequensySettingsSubPanel.DISPLAY_HELP_VIDEO;
			grabber = new OpenCVFrameGrabber("video/test3.avi");
			grabber2 = new OpenCVFrameGrabber("video/test4.avi");
			grabber.setFrameRate(25);
			grabber2.setFrameRate(25);
		}
		else if (this.stage == 2)
		{
    		DISPLAY_VIDEO = AreaSettingsSubPanel.DISPLAY_VIDEO;
    		DISPLAY_HELP_VIDEO = AreaSettingsSubPanel.DISPLAY_HELP_VIDEO;
			grabber = new OpenCVFrameGrabber("video/test3.avi");
			grabber2 = new OpenCVFrameGrabber("video/test4.avi");
			grabber.setFrameRate(25);
			grabber2.setFrameRate(25);
		}
		else if (this.stage == 3)
		{
    		DISPLAY_VIDEO = DensitySettingsSubPanel.DISPLAY_VIDEO;
    		DISPLAY_HELP_VIDEO = DensitySettingsSubPanel.DISPLAY_HELP_VIDEO;
			grabber = new OpenCVFrameGrabber("video/test3.avi");
			grabber2 = new OpenCVFrameGrabber("video/test4.avi");
			grabber.setFrameRate(25);
			grabber2.setFrameRate(25);
		}
	}
	public void run() {
        while (DISPLAY_VIDEO) {
    		IplImage grabbedImage = null;
    		try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
            	if ((grabber.getLengthInFrames()-100) >= grabber.getFrameNumber() && DISPLAY_HELP_VIDEO)
            	{
                	grabbedImage = grabber.grab();
            	}
            	else if ((grabber2.getLengthInFrames()-100) >= grabber2.getFrameNumber() && DISPLAY_HELP_VIDEO){
                	grabbedImage = grabber2.grab();
            	}
            	else if (!DISPLAY_HELP_VIDEO)
            	{
            		if (stage == 1)
                	{
                		DISPLAY_VIDEO = FrequensySettingsSubPanel.DISPLAY_VIDEO;
                		DISPLAY_HELP_VIDEO = FrequensySettingsSubPanel.DISPLAY_HELP_VIDEO;
                	}
                	else if (stage == 2)
                	{
                		DISPLAY_VIDEO = AreaSettingsSubPanel.DISPLAY_VIDEO;
                		DISPLAY_HELP_VIDEO = AreaSettingsSubPanel.DISPLAY_HELP_VIDEO;
                	}
                	else if (stage == 3)
                	{
                		DISPLAY_VIDEO = DensitySettingsSubPanel.DISPLAY_VIDEO;
                		DISPLAY_HELP_VIDEO = DensitySettingsSubPanel.DISPLAY_HELP_VIDEO;
                	}
            		grabber.restart();
        			grabber2.restart();
            		Thread.sleep(1000);
            	}
            	else
            	{
            		grabber.restart();
        			grabber2.restart();
            	}
            		
			} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
				continue;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            if (grabbedImage != null) {
            	/* Turns the image so that it have a mirror effect */
            	int widthFrame = 0;
            	int heightFrame = 0;
            	if(stage == 1 )
            	{
                	widthFrame = FrequensySettingsSubPanel.colorFrequensyVideoPanel.getWidth();
                    heightFrame = FrequensySettingsSubPanel.colorFrequensyVideoPanel.getHeight();
            	}
                else if(stage == 2 )
            	{
                	widthFrame = AreaSettingsSubPanel.colorAreaVideoPanel.getWidth();
                    heightFrame = AreaSettingsSubPanel.colorAreaVideoPanel.getHeight();
            	}
                else if(stage == 3 )
            	{
                	widthFrame = DensitySettingsSubPanel.colorDensityVideoPanel.getWidth();
                    heightFrame = DensitySettingsSubPanel.colorDensityVideoPanel.getHeight();
            	}
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
					if(stage == 1 )
                	{
						FrequensySettingsSubPanel.colorFrequensyVideoPanel.theCamera = resizeImaged;
                	}
                    else if(stage == 2 )
                	{
                    	AreaSettingsSubPanel.colorAreaVideoPanel.theCamera = resizeImaged;
                	}
                    else if(stage == 3 )
                	{
                    	DensitySettingsSubPanel.colorDensityVideoPanel.theCamera = resizeImaged;
                	}
                }
                else
                {
                	bufferdWebcameraImage = grabbedImage.getBufferedImage();
                	if(stage == 1 )
                	{
                		FrequensySettingsSubPanel.colorFrequensyVideoPanel.theCamera = bufferdWebcameraImage;
                	}
                    else if(stage == 2 )
                	{
                    	AreaSettingsSubPanel.colorAreaVideoPanel.theCamera = bufferdWebcameraImage;
                	}
                    else if(stage == 3 )
                	{
                    	DensitySettingsSubPanel.colorDensityVideoPanel.theCamera = bufferdWebcameraImage;
                	}
                }
            	/* Show image on window */
                if(stage == 1 )
            	{
                	FrequensySettingsSubPanel.colorFrequensyVideoPanel.repaint();
            	}
                else if(stage == 2 )
            	{
                	AreaSettingsSubPanel.colorAreaVideoPanel.repaint();
            	}
                else if(stage == 3 )
            	{
                	DensitySettingsSubPanel.colorDensityVideoPanel.repaint();
            	}
            }
        }
	}
}

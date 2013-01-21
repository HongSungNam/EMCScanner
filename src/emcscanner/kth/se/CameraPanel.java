package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class CameraPanel extends JPanel{
	BufferedImage buffImg = null;
	public FrameGrabber grabber;
	
	public CameraPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		
        try {
        	
        	
        	/* There exists many different types of frame grabbers. 
        	   This one is the one that is working for this camera. */
        	grabber = new OpenCVFrameGrabber(0);
        	
        	/* Sets the grabbers resolution */
        	grabber.setImageHeight(1080);
        	grabber.setImageWidth(1480);
        	
        	
        	grabber.start();
            final ColorPanel camerapanel;
            this.add(camerapanel = new ColorPanel(buffImg));
            
            Thread threadDisplay = new Thread("threadDisplay"){
            	public void run(){
            		
		            while (true) {
		            	IplImage img;
		                try {
							img = grabber.grab();
						} catch (com.googlecode.javacv.FrameGrabber.Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e1) {
								return;
							}
							continue;
						}
		                
		                if (img != null) {
		                    //cvFlip(img, img, 1);// l - r = 90_degrees_steps_anti_clockwise
		                    //cvSaveImage((i++)+"-aa.jpg", img);
		                    
		                	/* Show image on window */
		                	camerapanel.theCamera = img.getBufferedImage();
		                	camerapanel.repaint();
		                }   
		            }
            	}
            };
            threadDisplay.setDaemon(true);
            threadDisplay.start();
        } catch (Exception e) {
        }
	}
}
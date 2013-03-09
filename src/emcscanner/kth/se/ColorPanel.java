package emcscanner.kth.se;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * 
 * @author Jonas
 *
 */
public class ColorPanel extends JPanel{
	/**
	 * ColorPanel ID
	 */
	private static final long serialVersionUID = 6948165950015731934L;
	public BufferedImage theCamera;
	/**
	 * 
	 * @param image
	 */
	public ColorPanel(BufferedImage image){
		theCamera = image;
	}
	/**
	 * 
	 */
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(theCamera, null, 0, 0);
	}
}

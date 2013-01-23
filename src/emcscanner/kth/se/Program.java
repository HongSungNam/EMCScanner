package emcscanner.kth.se;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;

import com.googlecode.javacv.FrameGrabber.Exception;

/**
 * Main class for the program 
 * EMC-Scanner
 */
public class Program {	
	public static CameraPanel cameraPanel;
	public static SettingsPanel settingsPanel;
	public static DimentionPanel dimentionPanel;
	public static ConclutionPanel conclutionPanel;
	public static ImageScannedPanel imageScannedPanel;
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args) {
    	final MainFrame frame = new MainFrame();
		if (Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH))
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		else
		{
			int w = frame.getPreferredSize().width;
			int h = frame.getPreferredSize().height;
			frame.setPreferredSize(new Dimension(Math.max(800, w), Math.max(600, h)));
		} 
		
		KeyListener l;
    	
    	frame.addKeyListener(l = new KeyListener() {
    		@Override 
    		public void keyReleased(KeyEvent e) {
    			//Nothing is spouse to happen here;
		    }
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isConsumed())
					return;
				if (e.getExtendedKeyCode() == KeyEvent.VK_F11)
				{
			    	if (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getFullScreenWindow() == null)
						GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
			    	else
						GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
					
				}
				else if (e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE)
				{ 
					try {
						cameraPanel.grabber.stop();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}
				else
					return;
				e.consume();
			}
			@Override
			public void keyTyped(KeyEvent e) {
				//Nothing is spouse to happen here;
			}
    	});
    	cameraPanel = new CameraPanel();
    	settingsPanel = new SettingsPanel();
    	dimentionPanel = new DimentionPanel();
    	conclutionPanel = new ConclutionPanel();
    	imageScannedPanel = new ImageScannedPanel();
    	//startControlPanel = new StartControlPanel();
    	//manualPanel = new ManualPanel();
    	
    	ArrayList<JComponent> queue = new ArrayList<JComponent>();
    	queue.add(cameraPanel);
    	queue.add(settingsPanel);
    	queue.add(dimentionPanel);
    	queue.add(conclutionPanel);
    	queue.add(imageScannedPanel);
    	//queue.add(startControlPanel);
    	//queue.add(manualPanel);
    	
    	for (Component subcomp : frame.getComponents())
    		if (subcomp instanceof JComponent)
    			queue.add((JComponent)subcomp);
    	while (queue.isEmpty() == false)
    	{
    		JComponent comp = queue.remove(0);
    		for (Component subcomp : comp.getComponents())
    			if (subcomp instanceof JComponent)
    				queue.add((JComponent)subcomp);
    		comp.addKeyListener(l);
    	}
		//Show it the frame
		frame.setVisible(true);
    	GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        
	}
}
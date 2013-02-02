package emcscanner.kth.se;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public class MyGlassPanePhoto  extends JComponent {
	public MainFrame frame;
	public Point cursorPressed;
	public Point cursorReleased;
	
	public MyGlassPanePhoto(MainFrame frame) {
		this.frame = frame;
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		
        g2.dispose(); 
	}
}
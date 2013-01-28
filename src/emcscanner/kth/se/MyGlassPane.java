package emcscanner.kth.se;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenuBar;

/**
 * Our own glass pane so that it can paint.
 */
public class MyGlassPane extends JComponent {
	public MainFrame frame;
	public Point cursorPressed;
	public Point cursorReleased;
	
	public MyGlassPane(MainFrame frame, JMenuBar menuBar, JButton headerButton, final JButton backButton) {
		this.frame = frame;
		cursorPressed = new Point();
		cursorReleased = new Point();
		
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		if (Program.frame.MOUSE_RELEASED_BOOLEAN)
			g2.setColor(FrequensySettingsSubPanel.LIGHT_GREEN_COLOR);
		else
			g2.setColor(FrequensySettingsSubPanel.LIGHT_BLUE_COLOR);
        if (cursorPressed != null) 
        { 
        	if(cursorPressed.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth() && cursorPressed.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
        	{
        		if((cursorPressed.x < cursorReleased.x) )
        		{
        			if((cursorPressed.y < cursorReleased.y))
        			{
        				g2.drawRect(cursorPressed.x, cursorPressed.y, (cursorReleased.x-cursorPressed.x), (cursorReleased.y-cursorPressed.y));
        				if (Program.frame.MOUSE_RELEASED_BOOLEAN == false)
        					g2.setColor(new Color(100,150,255,80));
        				else
        					g2.setColor(new Color(150,255,80,80));
        				g2.fillRect(cursorPressed.x, cursorPressed.y, (cursorReleased.x-cursorPressed.x), (cursorReleased.y-cursorPressed.y));
        			}
        			else
        			{
        				g2.drawRect(cursorPressed.x, cursorReleased.y, (cursorReleased.x-cursorPressed.x), (cursorPressed.y-cursorReleased.y));
        				if (Program.frame.MOUSE_RELEASED_BOOLEAN == false)
        					g2.setColor(new Color(100,150,255,80));
        				else
        					g2.setColor(new Color(150,255,80,80));
        				g2.fillRect(cursorPressed.x, cursorReleased.y, (cursorReleased.x-cursorPressed.x), (cursorPressed.y-cursorReleased.y));
        			}
        		}
        		else
        		{
        			if((cursorPressed.y < cursorReleased.y))
        			{
        				g2.drawRect(cursorReleased.x, cursorPressed.y, (cursorPressed.x-cursorReleased.x), (cursorReleased.y-cursorPressed.y));
        				if (Program.frame.MOUSE_RELEASED_BOOLEAN == false)
        					g2.setColor(new Color(100,150,255,80));
        				else
        					g2.setColor(new Color(150,255,80,80));
        				g2.fillRect(cursorReleased.x, cursorPressed.y, (cursorPressed.x-cursorReleased.x), (cursorReleased.y-cursorPressed.y));
        			}
        			else
        			{
        				g2.drawRect(cursorReleased.x, cursorReleased.y, (cursorPressed.x-cursorReleased.x), (cursorPressed.y-cursorReleased.y));
        				if (Program.frame.MOUSE_RELEASED_BOOLEAN == false)
        					g2.setColor(new Color(100,150,255,80));
        				else 
        					g2.setColor(new Color(150,255,80,80));
        				g2.fillRect(cursorReleased.x, cursorReleased.y, (cursorPressed.x-cursorReleased.x), (cursorPressed.y-cursorReleased.y));
        			}
        		}
        	}
        } 
        g2.dispose(); 
	}
}
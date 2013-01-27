package emcscanner.kth.se;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 * Our own glass pane so that it can paint.
 */
public class MyGlassPane extends JComponent {
	public MainFrame frame;
	public Point cursorPressed;
	public Point cursorReleased;
	public boolean mouseReleasedBoolean = false;
	
	public MyGlassPane(MainFrame frame, JMenuBar menuBar, JButton headerButton, final JButton backButton) {
		this.frame = frame;
		setOpaque(false);
		cursorPressed = new Point();
		cursorReleased = new Point();
		
	}
	/*
	public void eventDispatched(AWTEvent event){
		if(event instanceof MouseEvent)
		{
			MouseEvent me =(MouseEvent) event;
			if (!SwingUtilities.isDescendingFrom(me.getComponent(), frame)) { 
                return; 
            }
			if (me.getID() == MouseEvent.MOUSE_EXITED && me.getComponent() == frame) 
			{ 
				point = null; 
			}
			else { 
                MouseEvent converted = SwingUtilities.convertMouseEvent(me.getComponent(), me, frame.getGlassPane()); 
                point = converted.getPoint(); 
            } 
		}
	}*/
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		if (mouseReleasedBoolean)
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
        				if (mouseReleasedBoolean == false)
        					g2.setColor(new Color(100,150,255,80));
        				else
        					g2.setColor(new Color(150,255,80,80));
        				g2.fillRect(cursorPressed.x, cursorPressed.y, (cursorReleased.x-cursorPressed.x), (cursorReleased.y-cursorPressed.y));
        			}
        			else
        			{
        				g2.drawRect(cursorPressed.x, cursorReleased.y, (cursorReleased.x-cursorPressed.x), (cursorPressed.y-cursorReleased.y));
        				if (mouseReleasedBoolean == false)
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
        				if (mouseReleasedBoolean == false)
        					g2.setColor(new Color(100,150,255,80));
        				else
        					g2.setColor(new Color(150,255,80,80));
        				g2.fillRect(cursorReleased.x, cursorPressed.y, (cursorPressed.x-cursorReleased.x), (cursorReleased.y-cursorPressed.y));
        			}
        			else
        			{
        				g2.drawRect(cursorReleased.x, cursorReleased.y, (cursorPressed.x-cursorReleased.x), (cursorPressed.y-cursorReleased.y));
        				if (mouseReleasedBoolean == false)
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
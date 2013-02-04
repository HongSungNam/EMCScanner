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
	
	public MyGlassPane(MainFrame frame) {
		this.frame = frame;
		cursorPressed = new Point(0,0);
		cursorReleased = new Point(0,0);
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		if (SettingsPanel.stage == 2)
		{
			if (Program.frame.MOUSE_RELEASED_BOOLEAN)
				g2.setColor(Program.LIGHT_GREEN_COLOR);
			else
				g2.setColor(Program.LIGHT_BLUE_COLOR);
	        if (cursorPressed != null) 
	        {
	    		if((cursorPressed.x < cursorReleased.x) )
	    		{
	    			if((cursorPressed.y < cursorReleased.y))
	    			{
	    				g2.drawRect(cursorPressed.x, cursorPressed.y + MainFrame.menuBar.getHeight(), (cursorReleased.x-cursorPressed.x), (cursorReleased.y-cursorPressed.y));
	    				if (Program.frame.MOUSE_RELEASED_BOOLEAN)
	    				{
	    					g2.setColor(new Color(150,255,80,80));
	    				}
	    				else
	    				{
	    					g2.setColor(new Color(100,150,255,80));
	    				}
	    				g2.fillRect(cursorPressed.x, cursorPressed.y + MainFrame.menuBar.getHeight(), (cursorReleased.x-cursorPressed.x), (cursorReleased.y-cursorPressed.y));
	    			}
	    			else
	    			{
	    				g2.drawRect(cursorPressed.x, cursorReleased.y + MainFrame.menuBar.getHeight(), (cursorReleased.x-cursorPressed.x), (cursorPressed.y-cursorReleased.y));
	    				if (Program.frame.MOUSE_RELEASED_BOOLEAN)
	    				{
	    					g2.setColor(new Color(150,255,80,80));
	    				}
	    				else
	    				{
	    					g2.setColor(new Color(100,150,255,80));
	    				}
	    				g2.fillRect(cursorPressed.x, cursorReleased.y + MainFrame.menuBar.getHeight(), (cursorReleased.x-cursorPressed.x), (cursorPressed.y-cursorReleased.y));
	    			}
	    		}
	    		else
	    		{
	    			if((cursorPressed.y < cursorReleased.y))
	    			{
	    				g2.drawRect(cursorReleased.x, cursorPressed.y + MainFrame.menuBar.getHeight(), (cursorPressed.x-cursorReleased.x), (cursorReleased.y-cursorPressed.y));
	    				if (Program.frame.MOUSE_RELEASED_BOOLEAN)
	    				{
	    					g2.setColor(new Color(150,255,80,80));
	    				}
	    				else
	    				{
	    					g2.setColor(new Color(100,150,255,80));
	    				}
	    				g2.fillRect(cursorReleased.x, cursorPressed.y + MainFrame.menuBar.getHeight(), (cursorPressed.x-cursorReleased.x), (cursorReleased.y-cursorPressed.y));
	    			}
	    			else
	    			{
	    				g2.drawRect(cursorReleased.x, cursorReleased.y + MainFrame.menuBar.getHeight(), (cursorPressed.x-cursorReleased.x), (cursorPressed.y-cursorReleased.y));
	    				if (Program.frame.MOUSE_RELEASED_BOOLEAN)
	    				{
	    					g2.setColor(new Color(150,255,80,80));
	    				}
	    				else
	    				{
	    					g2.setColor(new Color(100,150,255,80));
	    				}
	    				g2.fillRect(cursorReleased.x, cursorReleased.y + MainFrame.menuBar.getHeight(), (cursorPressed.x-cursorReleased.x), (cursorPressed.y-cursorReleased.y));
	    			}
	    		}
	        }
		}
		else if (SettingsPanel.stage == 3)
		{
			g2.setColor(new Color(100,150,255));

			if (SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH != 0)
			{
				int LINE_X, LINE_Y1 = MainFrame.menuBar.getHeight();
				int LINE_Y2 = LINE_Y1 + SettingsPanel.PHOTO_VIEW_DIMENSION.height;
				int n = SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1;
				for (int i = 1; i < n; i++)
				{
					LINE_X = SettingsPanel.PHOTO_VIEW_DIMENSION.width * i / n;
					g2.drawLine(LINE_X, LINE_Y1, LINE_X, LINE_Y2);
				}
			}
			
			if (SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT != 0)
			{
				int LINE_Y, LINE_X1 = 0;
				int LINE_X2 = LINE_X1 + SettingsPanel.PHOTO_VIEW_DIMENSION.width;
				int n = SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT + 1;
				for (int i = 1; i < n; i++)
				{
					LINE_Y = MainFrame.menuBar.getHeight() + SettingsPanel.PHOTO_VIEW_DIMENSION.height * i / n;
					g2.drawLine(LINE_X1, LINE_Y, LINE_X2, LINE_Y);
				}
			}
		}
        g2.dispose(); 
	}
}
package emcscanner.kth.se;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CameraPanelMouseMotionAdapter extends MouseMotionAdapter{
	public void mouseMoved(MouseEvent evt) {
	}
	public void mouseDragged(MouseEvent arg0){
		if (MainFrame.isGET_AREA_BOOLEAN())
		{
			if (Program.frame.isMOUSE_RELEASED_BOOLEAN() == false)
			{
				if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
	        	{
	        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
	        		{
	        			Program.frame.glass.cursorReleased = new Point(arg0.getPoint());
	        			Program.frame.glass.repaint();
	        		}
	        		else if(arg0.getY() <= 0)
	        		{
	        			Program.frame.glass.cursorReleased.x = arg0.getX();
	        			Program.frame.glass.cursorReleased.y = 0;
	        			Program.frame.glass.repaint();
	        		}
	        		else
	        		{
	        			Program.frame.glass.cursorReleased.x = arg0.getX();
	        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
	        			Program.frame.glass.repaint();
	        		}
	        	}
	        	else
	        	{
	        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
	        		{
	        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth(); 
	        			Program.frame.glass.cursorReleased.y = arg0.getY();
	        			Program.frame.glass.repaint();
	        		}
	        		else if(arg0.getY() <= 0)
	        		{
	        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
	        			Program.frame.glass.cursorReleased.y = 0;
	        			Program.frame.glass.repaint();
	        		}
	        		else
	        		{
	        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
	        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
	        			Program.frame.glass.repaint();
	        		}
	        	}
			}
		}
	}
}

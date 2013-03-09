package emcscanner.kth.se;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CameraPanelMouseListener extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override 
	public void mousePressed(MouseEvent arg0) {
		if (MainFrame.isGET_AREA_BOOLEAN())
		{
        	if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
        	{
        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
        		{
        			Program.frame.glass.setVisible(true);
        			Program.frame.glass.cursorPressed = new Point(arg0.getPoint());
	        		Program.frame.glass.cursorReleased.x = Program.frame.glass.cursorPressed.x;
	        		Program.frame.glass.cursorReleased.y = Program.frame.glass.cursorPressed.y;
	        		Program.frame.setMOUSE_RELEASED_BOOLEAN(false);
        		}
        	}
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (MainFrame.isGET_AREA_BOOLEAN())
		{
        	if(Program.frame.isMOUSE_RELEASED_BOOLEAN() == false)
        	{
				if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
	        	{
	        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() )//+ MainFrame.menuBar.getHeight()
	        		{			        			
	        			Program.frame.setMOUSE_RELEASED_BOOLEAN(true);
	        			Program.frame.glass.cursorReleased = new Point(arg0.getPoint());
	        			Program.frame.glass.repaint();
	        			
	        			Program.cameraPanel.cordinates();
	        		}
	        		else if(arg0.getY() <= 0)
	        		{
	        			Program.frame.setMOUSE_RELEASED_BOOLEAN(true);
	        			Program.frame.glass.cursorReleased.x = arg0.getX();
	        			Program.frame.glass.cursorReleased.y = 0;
	        			Program.frame.glass.repaint();

	        			Program.cameraPanel.cordinates();
	        		}
	        		else
	        		{
	        			Program.frame.setMOUSE_RELEASED_BOOLEAN(true);
	        			Program.frame.glass.cursorReleased.x = arg0.getX();
	        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
	        			Program.frame.glass.repaint();

	        			Program.cameraPanel.cordinates();
	        		}
	        	}
	        	else
	        	{
	        		if(arg0.getY() >= 0 && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight())
	        		{
	        			Program.frame
								.setMOUSE_RELEASED_BOOLEAN(true);
	        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth(); 
	        			Program.frame.glass.cursorReleased.y = arg0.getY();
	        			Program.frame.glass.repaint();

	        			Program.cameraPanel.cordinates();
	        		}
	        		else if(arg0.getY() <= 0)
	        		{
	        			Program.frame.setMOUSE_RELEASED_BOOLEAN(true);
	        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
	        			Program.frame.glass.cursorReleased.y = 0;
	        			Program.frame.glass.repaint();

	        			Program.cameraPanel.cordinates();
	        		}
	        		else
	        		{
	        			Program.frame.setMOUSE_RELEASED_BOOLEAN(true);
	        			Program.frame.glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
	        			Program.frame.glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight();
	        			Program.frame.glass.repaint();

	        			Program.cameraPanel.cordinates();
	        		}
	        	}

				AreaSettingsSubPanel.nextButton.setEnabled(true);
				if (Program.frame.glass.cursorPressed != null) 
		        {
    				if((Program.frame.glass.cursorPressed.x < Program.frame.glass.cursorReleased.x) )
    	    		{
    	    			if((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
    	    			{
	        				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
									" Width: " + (int) (Program.frame.glass.cursorReleased.x - Program.frame.glass.cursorPressed.x + 1) + 
									"<br/> Hight: " +(int) (Program.frame.glass.cursorReleased.y - Program.frame.glass.cursorPressed.y + 1) + "</p align></html>");
    	    			}
    	    			else
        				{
    	    				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
									" Width: " + (int) (Program.frame.glass.cursorReleased.x - Program.frame.glass.cursorPressed.x + 1) + 
									"<br/> Hight: " +(int) (Program.frame.glass.cursorPressed.y - Program.frame.glass.cursorReleased.y + 1) + "</p align></html>");
        				}
    	    		}
    				else
        			{
    					if((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
    	    			{
    	    				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
									" Width: " + (int) (Program.frame.glass.cursorPressed.x - Program.frame.glass.cursorReleased.x + 1) + 
									"<br/> Hight: " +(int) (Program.frame.glass.cursorReleased.y - Program.frame.glass.cursorPressed.y + 1) + "</p align></html>");
    	    			}
    					else
    	    			{
    	    				SettingsPanel.areaPanel.areaNotSelectedLabel.setText("<html><p align=center><font color = rgb(100,150,255)>Area Selected:</font><br/>" +
									" Width: " + (int) (Program.frame.glass.cursorPressed.x - Program.frame.glass.cursorReleased.x + 1) + 
									"<br/> Hight: " +(int) (Program.frame.glass.cursorPressed.y - Program.frame.glass.cursorReleased.y + 1) + "</p align></html>");
    	    			}
        			}
		        }	
        	}
		}
	}
}

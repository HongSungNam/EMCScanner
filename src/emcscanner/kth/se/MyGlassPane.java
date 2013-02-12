package emcscanner.kth.se;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.RescaleOp;

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
	
	public Boolean glasPanelActive = false;
	
	public MyGlassPane(MainFrame frame) {
		this.frame = frame;
		cursorPressed = new Point(0,0);
		cursorReleased = new Point(0,0);
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		if (glasPanelActive)
		{
			if (SettingsPanel.getStage() ==  1 || SettingsPanel.getStage() == 2)
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
			else if (SettingsPanel.getStage() == 3 || SettingsPanel.getStage() == 4)
			{
				g2.setColor(new Color(100,150,255));
				if (DensitySettingsSubPanel.inputStepBoolean)
				{
					if (SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH != 0)
					{
						int LINE_X, LINE_Y1 = MainFrame.menuBar.getHeight();
						int LINE_Y2 = LINE_Y1 + SettingsPanel.PHOTO_VIEW_DIMENSION.height;
						int n = SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1;

						/* Adds number of steps and size to SettingsPanel for controlling the number of measurements */
						SettingsPanel.numberOfStepsWidth = n;
						SettingsPanel.stepSizeWidth =  (int) (((SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_X - SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL) / (SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1));
						
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

						/* Adds number of steps and size to SettingsPanel for controlling the number of measurements */
						SettingsPanel.numberOfStepsHeight = n;
						SettingsPanel.stepSizeHeight = (int) (((SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_Y - SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL) / (SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1));
						
						for (int i = 1; i < n; i++)
						{
							LINE_Y = MainFrame.menuBar.getHeight() + SettingsPanel.PHOTO_VIEW_DIMENSION.height * i / n;
							g2.drawLine(LINE_X1, LINE_Y, LINE_X2, LINE_Y);
						}
					}
				}
				else
				{
					if (SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH != 0)
					{
						int y1 = MainFrame.menuBar.getHeight();
						int y2 = SettingsPanel.PHOTO_VIEW_DIMENSION.height + y1;

						/* Length of the photo symbolizing reality */
						int w = SettingsPanel.PHOTO_VIEW_DIMENSION.width * Program.TIONDELS_MILLI_METER_PIXEL;

						/* Number of pixels to move every time */
						int s = (int) (((SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1) * w )
								 / ((((SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) * Program.TIONDELS_MILLI_METER_PIXEL))));
						
						/* Adds number of steps and size to SettingsPanel for controlling the number of measurements */
						SettingsPanel.numberOfStepsWidth = 0;
						SettingsPanel.stepSizeWidth = SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1;
						
						for (int x = s - 1; x < w; x += s)
						{
							SettingsPanel.numberOfStepsWidth++;
							g2.drawLine(x / 10, y1, x / 10, y2);
						}
					}
					if (SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT != 0)
					{
						int x1 = 0;
						int x2 = SettingsPanel.PHOTO_VIEW_DIMENSION.width + x1;

						/* Length of the photo symbolizing reality */
						int h = SettingsPanel.PHOTO_VIEW_DIMENSION.height * Program.TIONDELS_MILLI_METER_PIXEL;
						
						/* Number of pixels to move every time */
						int s = (int) (((SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT + 1) * h )
								 / ((((SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) * Program.TIONDELS_MILLI_METER_PIXEL))));

						/* Adds number of steps and size to SettingsPanel for controlling the number of measurements */
						SettingsPanel.numberOfStepsHeight = 0;
						SettingsPanel.stepSizeHeight = SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT + 1;
						
						for (int y = s - 1; y < h; y += s)
						{
							SettingsPanel.numberOfStepsHeight++;
							int $y = y / 10 + MainFrame.menuBar.getHeight();
							g2.drawLine(x1, $y, x2, $y);
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else if (SettingsPanel.getStage() == 5)
			{
				g2.drawImage(SettingsPanel.scanPanel.rezicedBuffImage, null, 0, MainFrame.menuBar.getHeight());
			}
			g2.dispose();
		}
	}
}
package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextActionListener implements ActionListener{
	private int stage;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (SettingsPanel.getStage() == 1 && Program.frame.glass.glasPanelActive){
			if (SettingsPanel.frequencyPanel.nextButton.isEnabled())
			{
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.FREQUENCY_START_SELECTED_VALUE = SettingsPanel.frequencyPanel.startValue;
				SettingsPanel.FREQUENCY_END_SELECTED_VALUE = SettingsPanel.frequencyPanel.endValue;
				SettingsPanel.FREQUENCY_DENSITY_SELECTED_VALUE = SettingsPanel.frequencyPanel.densityValue;
				
				/* Adding the next step */
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				AreaSettingsSubPanel.backButton.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 2 && Program.frame.glass.glasPanelActive)
		{
			if (SettingsPanel.areaPanel.nextButton.isEnabled())
			{
				SettingsPanel.AREA_SELECTED = true;
				if((Program.frame.glass.cursorPressed.x < Program.frame.glass.cursorReleased.x) )
	    		{
	    			if ((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
	    			{
        				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) + 
								" x Hight: " +(int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) + "&nbsp</html>");

	    			}
	    			else
    				{
	    				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) + 
								" x Hight: " +(int) (SettingsPanel.AREA_SELECTED_START_Y - SettingsPanel.AREA_SELECTED_END_Y + 1) + "&nbsp</html>");	
    				}
	    		}
				else
    			{
					if ((Program.frame.glass.cursorPressed.y < Program.frame.glass.cursorReleased.y))
	    			{
	    				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (SettingsPanel.AREA_SELECTED_START_X - SettingsPanel.AREA_SELECTED_END_X + 1) + 
								" x Height: " +(int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) + "&nbsp</html>");
	    			}
					else
	    			{
	    				SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
								" Width: " + (int) (SettingsPanel.AREA_SELECTED_START_X - SettingsPanel.AREA_SELECTED_END_X + 1) + 
								" x Hight: " +(int) (SettingsPanel.AREA_SELECTED_START_Y - SettingsPanel.AREA_SELECTED_END_Y + 1) + "&nbsp</html>");
	    			}
    			}
				SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION = Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION;
				
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
						
				CameraPanel.SAVE_IMAGE = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Program.imagePanel.setPhoto();
				
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 3 && Program.frame.glass.glasPanelActive)
		{
			if (SettingsPanel.densityPanel.nextButton.isEnabled())
			{
				SettingsPanel.DENSITY_SELECTED_WIDTH = SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1;
				SettingsPanel.DENSITY_SELECTED_HEIGHT = SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT + 1;
				SettingsPanel.DENSITY_SELECTED = true;
				
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive)
		{
			if (SettingsPanel.fileNamePanel.nextButton.isEnabled())
			{
				SettingsPanel.FILE_NAME_SELECTED = true;
				
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelActive();
			}
		}
		else if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive)
		{
			
		}
		else
		{
			MainFrame.mainPanel.add(Program.settingsPanel, BorderLayout.EAST);
			MainFrame.mainPanel.add(Program.cameraPanel, BorderLayout.CENTER);
			Program.frame.glass.glasPanelActive = true;
			if (SettingsPanel.getStage() == 1)
			{
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
			}
			else if (SettingsPanel.getStage() == 2)
			{
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				AreaSettingsSubPanel.backButton.grabFocus();
			}
			else if (SettingsPanel.getStage() == 3)
			{
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
			}
			else if (SettingsPanel.getStage() == 4)
			{
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
			else if (SettingsPanel.getStage() == 5)
			{
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelActive();
			}
		}
	}
}

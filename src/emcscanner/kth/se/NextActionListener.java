package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextActionListener implements ActionListener{
	private int stage;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (SettingsPanel.getStage() == 1 && Program.frame.glass.glasPanelActive){
			if (FrequencySettingsSubPanel.nextButton.isEnabled())
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = true;
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.frequencyStartUserSelectedFloat = SettingsPanel.frequencyPanel.startValue;
				SettingsPanel.frequencyEndUserSelectedFloat = SettingsPanel.frequencyPanel.endValue;
				SettingsPanel.frequencyDensityUserSelectedInt = SettingsPanel.frequencyPanel.densityValue;
				
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
			if (AreaSettingsSubPanel.nextButton.isEnabled())
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
				SettingsPanel.AREA_SELECTED = true;
				
        		SettingsPanel.areaPanel.areaLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
						" Width: " + (int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X + 1) + 
						" x Hight: " +(int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y + 1) + "&nbsp</html>");

				SettingsPanel.AREA_SELECTED_CAMERA_DIMENSION = Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION;
				
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
						
				CameraPanel.SAVE_IMAGE = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				Program.imagePanel.setPhoto();
				
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();

				SettingsPanel.scanPanel.scanActiveChange();
			}
		}
		else if (SettingsPanel.getStage() == 3 && Program.frame.glass.glasPanelActive)
		{
			if (DensitySettingsSubPanel.nextButton.isEnabled())
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
				SettingsPanel.DENSITY_SELECTED_WIDTH = SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1;
				SettingsPanel.DENSITY_SELECTED_HEIGHT = SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT + 1;
				SettingsPanel.DENSITY_SELECTED = true;
				
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive)
		{
			if (FileNameSettingsSubPanel.nextButton.isEnabled())
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
				SettingsPanel.FILE_NAME_SELECTED = true;
				
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelActive();
				SettingsPanel.scanPanel.resizeAPaint();
			}
		}
		else if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive)
		{
			if (ScanSettingsSubPanel.startScanButton.isEnabled())
			{
				SettingsPanel.scanPanel.scanNeverStarted = false;
				if (!SettingsPanel.scanPanel.scanStoped && !SettingsPanel.scanPanel.pauseScanX)
				{
					//createColorPalet();
					SettingsPanel.scanPanel.headersInactive();
					SettingsPanel.scanPanel.setBuffImageAlpha();
					SettingsPanel.scanPanel.buttonsScanActiveStarted();
					SettingsPanel.backButton.setEnabled(false);
					SettingsPanel.scanPanel.delayedStartScan();
					
					SettingsPanel.scanPanel.scanActive = true;
				}
				else if (!SettingsPanel.scanPanel.scanStoped && SettingsPanel.scanPanel.pauseScanX)
				{
					SettingsPanel.scanPanel.pauseScanX = false;
					SettingsPanel.scanPanel.buttonsScanActiveStarted();
				}
				else if(SettingsPanel.scanPanel.scanStoped)
				{
					SettingsPanel.scanPanel.pauseScanX = false;
					SettingsPanel.scanPanel.changeWay = false;
					SettingsPanel.scanPanel.restartScanValues();
					//SettingsPanel.scanPanel.createColorPalet();
					SettingsPanel.scanPanel.headersInactive();
					SettingsPanel.scanPanel.delayedStartScan();
					SettingsPanel.scanPanel.buttonsScanActiveStarted();
					SettingsPanel.backButton.setEnabled(false);
					SettingsPanel.scanPanel.scanActive = true;
				}
			}
		}
		else
		{
			MainFrame.mainPanel.add(Program.settingsPanel, BorderLayout.EAST);
			MainFrame.mainPanel.add(Program.cameraPanel, BorderLayout.CENTER);
			Program.frame.glass.glasPanelActive = true;
			if (SettingsPanel.getStage() == 1)
			{
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
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
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
			}
			else if (SettingsPanel.getStage() == 4)
			{
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
			else if (SettingsPanel.getStage() == 5)
			{
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelActive();
			}
		}
	}
}

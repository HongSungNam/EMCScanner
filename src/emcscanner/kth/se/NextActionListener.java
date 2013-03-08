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
				SettingsPanel.setFREQUENCY_SELECTED(true);
				
				SettingsPanel.frequencyStartUserSelectedFloat = SettingsPanel.frequencyPanel.startValue;
				SettingsPanel.frequencyEndUserSelectedFloat = SettingsPanel.frequencyPanel.endValue;
				SettingsPanel.frequencyDensityUserSelectedInt = SettingsPanel.frequencyPanel.densityValue;
				
				/* Adding the next step */
				StageActive.stageActive(2);
				
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

				StageActive.stageActive(3);
						
				CameraPanel.SAVE_IMAGE = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				Program.imagePanel.setPhoto();
				
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();

				SettingsPanel.scanPanel.scan.scanActiveChange();
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

				StageActive.stageActive(4);
				
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive)
		{
			if (FileNameSettingsSubPanel.nextButton.isEnabled())
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
				SettingsPanel.FILE_NAME_SELECTED = true;
				
				StageActive.stageActive(5);
				
				SettingsPanel.scanPanel.scan.resizeAPaint();
			}
		}
		else if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive)
		{
			if (ScanSettingsSubPanel.startScanButton.isEnabled())
			{
				SettingsPanel.scanPanel.scan.numberOfScans();
				SettingsPanel.scanPanel.scan.scanNeverStarted = false;
				if (!SettingsPanel.scanPanel.scan.isScanStoped() && !SettingsPanel.scanPanel.scan.pauseScanX)
				{
					//createColorPalet();
					SettingsPanel.scanPanel.scan.headersInactive();
					SettingsPanel.scanPanel.scan.setBuffImageAlpha();
					SettingsPanel.scanPanel.scan.buttonsScanActiveStarted();
					EndSubSettingsPanel.backButton.setEnabled(false);
					SettingsPanel.scanPanel.scan.delayedStartScan();
					
					SettingsPanel.scanPanel.scan.scanActive = true;
				}
				else if (!SettingsPanel.scanPanel.scan.isScanStoped() && SettingsPanel.scanPanel.scan.pauseScanX)
				{
					SettingsPanel.scanPanel.scan.pauseScanX = false;
					SettingsPanel.scanPanel.scan.buttonsScanActiveStarted();
				}
				else if(SettingsPanel.scanPanel.scan.isScanStoped())
				{
					SettingsPanel.scanPanel.scan.pauseScanX = false;
					SettingsPanel.scanPanel.scan.changeWay = false;
					SettingsPanel.scanPanel.scan.restartScanValues();
					//SettingsPanel.scanPanel.createColorPalet();
					SettingsPanel.scanPanel.scan.headersInactive();
					SettingsPanel.scanPanel.scan.delayedStartScan();
					SettingsPanel.scanPanel.scan.buttonsScanActiveStarted();
					EndSubSettingsPanel.backButton.setEnabled(false);
					SettingsPanel.scanPanel.scan.scanActive = true;
				}
			}
		}
		else
		{			
			MainFrame.mainPanel.add(Program.settingsPanel, BorderLayout.EAST);
			MainFrame.mainPanel.add(Program.cameraPanel, BorderLayout.CENTER);
			Program.frame.glass.glasPanelActive = true;
			if (!SettingsPanel.scanPanel.scan.headersInactive)
			{
				if (SettingsPanel.getStage() == 1)
				{
					StageActive.stageActive(1);
					SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
				}
				else if (SettingsPanel.getStage() == 2)
				{
					StageActive.stageActive(2);
					AreaSettingsSubPanel.backButton.grabFocus();
				}
				else if (SettingsPanel.getStage() == 3)
				{
					StageActive.stageActive(3);
					SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
				}
				else if (SettingsPanel.getStage() == 4)
				{
					StageActive.stageActive(4);
					SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
				}
				else if (SettingsPanel.getStage() == 5)
					StageActive.stageActive(5);
			}
			else
				SetPanelActive.scanPanelActive();
		}
	}
}

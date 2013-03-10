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
				SettingsPanel.setFREQUENCY_SELECTED(true);
				SettingsPanel.setFrequencyStartUserSelectedFloat(SettingsPanel.frequencyPanel.startValue);
				SettingsPanel.setFrequencyEndUserSelectedFloat(SettingsPanel.frequencyPanel.endValue);
				SettingsPanel.setFrequencyDensityUserSelectedInt(SettingsPanel.frequencyPanel.densityStartValue);
				StageActive.stageActive(2);
				AreaSettingsSubPanel.backButton.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 2 && Program.frame.glass.glasPanelActive)
		{
			if (AreaSettingsSubPanel.nextButton.isEnabled())
			{
				SettingsPanel.setAREA_SELECTED(true);
        		SettingsPanel.areaPanel.areaResultLabel.setText("<html><font color = rgb(120,200,40)>Area Selected:</font>" +
						" Width: " + (int) (SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) + 
						" x Hight: " +(int) (SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) + 
						"&nbsp</html>");

				SettingsPanel.setAREA_SELECTED_CAMERA_DIMENSION(Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION);
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
				SettingsPanel.setDENSITY_SELECTED_WIDTH(SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH + 1);
				SettingsPanel.setDENSITY_SELECTED_HEIGHT(SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT + 1);
				SettingsPanel.setDENSITY_SELECTED(true);
				StageActive.stageActive(4);
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
		}
		else if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive)
		{
			if (FileNameSettingsSubPanel.nextButton.isEnabled())
			{
				SettingsPanel.setFILE_NAME_SELECTED(true);
				StageActive.stageActive(5);
				SettingsPanel.scanPanel.scan.resizeAPaint();
			}
		}
		else if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive)
		{
			if (ScanSettingsSubPanel.startScanButton.isEnabled())
			{
				SettingsPanel.scanPanel.scan.numberOfScans();
				SettingsPanel.scanPanel.scan.setScanNeverStarted(false);
				ScanButtonActive.buttonsScanActiveStarted();
				//SettingsPanel.scanPanel.createColorPalet();
				if (!SettingsPanel.scanPanel.scan.isScanStoped() && !SettingsPanel.scanPanel.scan.isPauseScanX())
				{
					Headers.headersInactive();
					SetImageAlpha.setBuffImageAlpha();
					EndSubSettingsPanel.backButton.setEnabled(false);
					SettingsPanel.scanPanel.scan.delayedStartScan();
					SettingsPanel.scanPanel.scan.setScanActive(true);
				}
				else if (!SettingsPanel.scanPanel.scan.isScanStoped() && SettingsPanel.scanPanel.scan.isPauseScanX())
					SettingsPanel.scanPanel.scan.setPauseScanX(false);
				else if(SettingsPanel.scanPanel.scan.isScanStoped())
				{
					SettingsPanel.scanPanel.scan.setPauseScanX(false);
					SettingsPanel.scanPanel.scan.setChangeWay(false);
					SettingsPanel.scanPanel.scan.restartScanValues();
					Headers.headersInactive();
					SettingsPanel.scanPanel.scan.delayedStartScan();
					EndSubSettingsPanel.backButton.setEnabled(false);
					SettingsPanel.scanPanel.scan.setScanActive(true);
				}
			}
		}
		else
		{			
			MainFrame.mainPanel.add(Program.settingsPanel, BorderLayout.EAST);
			MainFrame.mainPanel.add(Program.cameraPanel, BorderLayout.CENTER);
			Program.frame.glass.glasPanelActive = true;
			if (!SettingsPanel.scanPanel.scan.isHeadersInactive())
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
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
}

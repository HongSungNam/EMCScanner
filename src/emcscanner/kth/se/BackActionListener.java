package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		if (SettingsPanel.getStage() == 1 && Program.frame.glass.glasPanelActive)
		{
			Program.manualPanel.setVisible(true);
			Program.startControlPanel.setVisible(true);
			Program.cameraPanel.setVisible(false);
			Program.imagePanel.setVisible(false);
			Program.settingsPanel.setVisible(false);
			
			Program.frame.glass.glasPanelActive = false;
			Program.startControlPanel.getNextButton().grabFocus();
		}
		else if (SettingsPanel.getStage() == 2 && Program.frame.glass.glasPanelActive)
		{
			StageActive.stageActive(1);
			SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
		}
		else if (SettingsPanel.getStage() == 3 && Program.frame.glass.glasPanelActive)
		{
			StageActive.stageActive(2);
			AreaSettingsSubPanel.backButton.grabFocus();
		}
		else if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive)
		{
			StageActive.stageActive(3);
			SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
		}
		else if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive)
		{
			//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
			if (SettingsPanel.scanPanel.scan.scanActive)
			{
				SettingsPanel.scanPanel.scan.setScanX(false);
				SettingsPanel.scanPanel.scan.setScanY(false);
				
				//SettingsPanel.scanPanel.scan.setHeadersInactive(false);
				
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.frequencyPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_DISABLED_BLUE_IMAGE_ICON);

				FrequencySettingsSubPanel.headerButton.setEnabled(true);
				AreaSettingsSubPanel.headerButton.setEnabled(true);
				DensitySettingsSubPanel.headerButton.setEnabled(true);
				FileNameSettingsSubPanel.headerButton.setEnabled(true);
				
				ScanSettingsSubPanel.startScanButton.setEnabled(true);
				ScanSettingsSubPanel.pauseScanButton.setEnabled(false);
				ScanSettingsSubPanel.stopScanButton.setEnabled(false);
				
				SettingsPanel.scanPanel.scan.setScanStoped(true);
				
				EndSubSettingsPanel.backButton.setEnabled(true);
			}
			else
			{
				StageActive.stageActive(1);
				SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
			}
		}
	}
}

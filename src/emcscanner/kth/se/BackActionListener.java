package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		if (SettingsPanel.getStage() == 1 && Program.frame.glass.glasPanelActive)
		{
			//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
			Program.manualPanel.setVisible(true);
			Program.startControlPanel.setVisible(true);
			Program.cameraPanel.setVisible(false);
			Program.imagePanel.setVisible(false);
			Program.settingsPanel.setVisible(false);
			
			Program.frame.glass.glasPanelActive = false;
			Program.startControlPanel.nextButton.grabFocus();
		}
		if (SettingsPanel.getStage() == 2 && Program.frame.glass.glasPanelActive)
		{
			//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = true;
			SettingsPanel.areaPanel.areaPanelNotActive();
			SettingsPanel.frequencyPanel.frequencyPanelActive();
			SettingsPanel.densityPanel.densityPanelNotActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
		}
		if (SettingsPanel.getStage() == 3 && Program.frame.glass.glasPanelActive)
		{
			//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = true;
			SettingsPanel.areaPanel.areaPanelActive();
			SettingsPanel.frequencyPanel.frequencyPanelNotActive();
			SettingsPanel.densityPanel.densityPanelNotActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			AreaSettingsSubPanel.backButton.grabFocus();
		}
		if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive)
		{
			//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
			SettingsPanel.frequencyPanel.frequencyPanelNotActive();
			SettingsPanel.areaPanel.areaPanelNotActive();
			SettingsPanel.densityPanel.densityPanelActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
		}
		if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive)
		{
			//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
			if (SettingsPanel.scanPanel.scanActive)
			{
				SettingsPanel.scanPanel.setScanX(false);
				SettingsPanel.scanPanel.setScanY(false);
				
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
				
				SettingsPanel.scanPanel.scanStoped = true;
				
				SettingsPanel.backButton.setEnabled(true);
			}
			else
			{
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
			}
		}
	}
}

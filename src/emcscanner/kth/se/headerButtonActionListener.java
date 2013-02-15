package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeaderButtonActionListener implements ActionListener{
	private int stage;
	public HeaderButtonActionListener(int stage)
	{
		this.stage = stage;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED)
		{
			if (this.stage == 1)
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = true;
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
					
			}
			if (this.stage == 2)
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = true;
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.areaPanel.areaPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				AreaSettingsSubPanel.backButton.grabFocus();
			}
			if (this.stage == 3)
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
			}
			if (this.stage == 4)
			{
				//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
			if (this.stage == 5)
			{
				SettingsPanel.areaPanel.areaPanelNotActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelActive();
			}
		}
	}
}

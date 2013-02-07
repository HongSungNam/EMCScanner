package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class headerButtonActionListener implements ActionListener{
	private int stage;
	public headerButtonActionListener(int stage)
	{
		this.stage = stage;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrequensySettingsSubPanel.NEXT_BUTTON_ENABLED)
		{
			if (this.stage == 1)
			{
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
			}
			if (this.stage == 2)
			{
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
				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
			}
			if (this.stage == 4)
			{

				SettingsPanel.FREQUENCY_SELECTED = true;
				
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
		}
	}
}

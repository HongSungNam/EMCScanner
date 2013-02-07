package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		if (SettingsPanel.getStage() == 1 && Program.frame.glass.glasPanelActive){

			Program.manualPanel.setVisible(true);
			Program.startControlPanel.setVisible(true);
			Program.cameraPanel.setVisible(false);
			Program.imagePanel.setVisible(false);
			Program.settingsPanel.setVisible(false);
			
			Program.frame.glass.glasPanelActive = false;
			Program.startControlPanel.nextButton.grabFocus();
		}
		if (SettingsPanel.getStage() == 2 && Program.frame.glass.glasPanelActive){
			SettingsPanel.areaPanel.areaNotPanelActive();
			SettingsPanel.frequencyPanel.frequencyPanelActive();
			SettingsPanel.densityPanel.densityPanelNotActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
		}
		if (SettingsPanel.getStage() == 3 && Program.frame.glass.glasPanelActive){
			SettingsPanel.areaPanel.areaPanelActive();
			SettingsPanel.frequencyPanel.frequencyPanelNotActive();
			SettingsPanel.densityPanel.densityPanelNotActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			SettingsPanel.areaPanel.backButton.grabFocus();
		}
		if (SettingsPanel.getStage() == 4 && Program.frame.glass.glasPanelActive){
			SettingsPanel.frequencyPanel.frequencyPanelNotActive();
			SettingsPanel.areaPanel.areaNotPanelActive();
			SettingsPanel.densityPanel.densityPanelActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
		}
		if (SettingsPanel.getStage() == 5 && Program.frame.glass.glasPanelActive){
			SettingsPanel.areaPanel.areaNotPanelActive();
			SettingsPanel.frequencyPanel.frequencyPanelActive();
			SettingsPanel.densityPanel.densityPanelNotActive();
			SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			SettingsPanel.scanPanel.scanPanelNotActive();
			SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
		}
	}
}

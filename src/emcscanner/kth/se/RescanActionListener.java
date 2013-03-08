package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RescanActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		SettingsPanel.scanPanel.scan.setScanDone(false);
		SettingsPanel.scanPanel.scan.setScanStoped(true);
		SetPanelActive.scanPanelActive();
		
		SettingsPanel.scanPanel.scan.setScanX(false);
		SettingsPanel.scanPanel.scan.setScanY(false);
		
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
		
		SettingsPanel.scanPanel.scan.pauseScanX = false;
		SettingsPanel.scanPanel.scan.changeWay = false;
		SettingsPanel.scanPanel.scan.restartScanValues();
	}
}

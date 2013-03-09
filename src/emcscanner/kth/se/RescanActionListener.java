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
		
		/***************************************************************************************************************************/
		FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_BLUE_IMAGE_ICON);
		DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_BLUE_IMAGE_ICON);
		FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_BLUE_IMAGE_ICON);

		FrequencySettingsSubPanel.headerButton.setEnabled(true);
		AreaSettingsSubPanel.headerButton.setEnabled(true);
		DensitySettingsSubPanel.headerButton.setEnabled(true);
		FileNameSettingsSubPanel.headerButton.setEnabled(true);
		
		ScanSettingsSubPanel.startScanButton.setEnabled(true);
		ScanSettingsSubPanel.pauseScanButton.setEnabled(false);
		ScanSettingsSubPanel.stopScanButton.setEnabled(false);
		
		SettingsPanel.scanPanel.scan.setPauseScanX(false);
		SettingsPanel.scanPanel.scan.setChangeWay(false);
		SettingsPanel.scanPanel.scan.restartScanValues();
	}
}

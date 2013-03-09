package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScanActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ScanSettingsSubPanel.startScanButton.setEnabled(true);
		ScanSettingsSubPanel.pauseScanButton.setEnabled(false);

		SettingsPanel.scanPanel.scan.setPauseScanX(true);
	}		
}
package emcscanner.kth.se;

public class ScanButtonActive {

	/**
	 * Changes the buttons so that they are correctly enabled.
	 * 
	 * start button disabled
	 * pause button enabled 
	 * stop button enabled
	 */
	public static void buttonsScanActiveStarted() {
		ScanSettingsSubPanel.startScanButton.setEnabled(false);
		ScanSettingsSubPanel.stopScanButton.setEnabled(true);
		ScanSettingsSubPanel.pauseScanButton.setEnabled(true);
	}
}

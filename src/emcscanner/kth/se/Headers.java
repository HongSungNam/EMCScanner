package emcscanner.kth.se;

public class Headers {

	/**
	 * Sets the headers to disabled when scan has started.
	 */
	public static void headersInactive() {
		/***************************************************************************************************************************/
		FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		
		FrequencySettingsSubPanel.headerButton.setEnabled(false);
		AreaSettingsSubPanel.headerButton.setEnabled(false);
		DensitySettingsSubPanel.headerButton.setEnabled(false);
		FileNameSettingsSubPanel.headerButton.setEnabled(false);
		
		SettingsPanel.scanPanel.scan.setHeadersInactive(true);
	}
}

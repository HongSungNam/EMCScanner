package emcscanner.kth.se;

import javax.swing.ImageIcon;

public class HeaderButton extends Buttons {
	/**
	 * HEADERS ID
	 */
	private static final long serialVersionUID = -5281658824456614703L;

	public static String PANEL_INFORMATION 		= "This is where you can shoose the desired frequency.";
	public String AREA_PANEL_TOOL_TIP_TEXT 		= "This is where you select the area to scan.";
	public String DENSITY_PANEL_TOOL_TIP_TEXT 	= "This is where you select the density to scan";
	public String FILE_NAME_PANEL_TOOL_TIP_TEXT = "This is where you select the file name you want to save the result as.";
	public String SCAN_PANEL_TOOL_TIP_TEXT 		= "This is where you start the scan.";
	
	public ImageIcon FREQUENCY_HEADER_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/PanelGreenFrequency.png");
	public ImageIcon FREQUENCY_HEADER_ENABLED_ROLLOVER_IMAGE_ICON 	= new ImageIcon("image/PanelGreenFrequencyRollover.png");
	public ImageIcon FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON 		= new ImageIcon("image/PanelBlueFrequency.png");
	public ImageIcon FREQUENCY_HEADER_ENABLED_PREST_IMAGE_ICON		= new ImageIcon("image/PanelGreenFrequencyPrest.png");
	public ImageIcon FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON		= new ImageIcon("image/PanelRedFrequency.png");
	public ImageIcon FREQUENCY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON= new ImageIcon("image/PanelDarkGreenFrequency.png");
	
	public ImageIcon AREA_HEADER_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/PanelGreenArea.png");
	public ImageIcon AREA_HEADER_ENABLED_ROLLOVER_IMAGE_ICON 		= new ImageIcon("image/PanelGreenAreaRollover.png");
	public ImageIcon AREA_HEADER_DISABLED_GRAY_IMAGE_ICON 			= new ImageIcon("image/PanelGrayArea.png");
	public ImageIcon AREA_HEADER_ENABLED_PREST_IMAGE_ICON 			= new ImageIcon("image/PanelGreenAreaPrest.png");
	public ImageIcon AREA_HEADER_DISABLED_BLUE_IMAGE_ICON 			= new ImageIcon("image/PanelBlueArea.png");
	public ImageIcon AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenArea.png");
	
	public ImageIcon DENSITY_HEADER_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/PanelGreenDensity.png");
	public ImageIcon DENSITY_HEADER_ENABLED_ROLLOVER_IMAGE_ICON 	= new ImageIcon("image/PanelGreenDensityRollover.png");
	public ImageIcon DENSITY_HEADER_DISABLED_GRAY_IMAGE_ICON 		= new ImageIcon("image/PanelGrayDensity.png");
	public ImageIcon DENSITY_HEADER_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/PanelGreenDensityPrest.png");
	public ImageIcon DENSITY_HEADER_DISABLED_BLUE_IMAGE_ICON 		= new ImageIcon("image/PanelBlueDensity.png");
	public ImageIcon DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenDensity.png"); 
	
	public ImageIcon FILE_NAME_HEADER_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/PanelGreenFileName.png");
	public ImageIcon FILE_NAME_HEADER_ENABLED_ROLLOVER_IMAGE_ICON 	= new ImageIcon("image/PanelGreenFileNameRollover.png");
	public ImageIcon FILE_NAME_HEADER_DISABLED_GRAY_IMAGE_ICON 		= new ImageIcon("image/PanelGrayFileName.png");
	public ImageIcon FILE_NAME_HEADER_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/PanelGreenFileNamePrest.png");
	public ImageIcon FILE_NAME_HEADER_DISABLED_BLUE_IMAGE_ICON 		= new ImageIcon("image/PanelBlueFileName.png");
	public ImageIcon FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON= new ImageIcon("image/PanelDarkGreenFileName.png"); 
	
	public ImageIcon SCAN_HEADER_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/PanelGreenScan.png");
	public ImageIcon SCAN_HEADER_ENABLED_ROLLOVER_IMAGE_ICON 		= new ImageIcon("image/PanelGreenScanRollover.png");
	public ImageIcon SCAN_HEADER_DISABLED_GRAY_IMAGE_ICON 			= new ImageIcon("image/PanelGrayScan.png");
	public ImageIcon SCAN_HEADER_ENABLED_PREST_IMAGE_ICON 			= new ImageIcon("image/PanelGreenScanPrest.png");
	public ImageIcon SCAN_HEADER_DISABLED_BLUE_IMAGE_ICON 			= new ImageIcon("image/PanelBlueScan.png");
	public ImageIcon SCAN_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenScan.png"); 
	
	public HeaderButton(int stage){
		super(4);
		
		if (stage == 1)
		{
			/* Sets creation values for the header button */
			this.setEnabled(false);
			this.setToolTipText(PANEL_INFORMATION);
			this.setIcon(FREQUENCY_HEADER_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
			this.setPressedIcon(FREQUENCY_HEADER_ENABLED_PREST_IMAGE_ICON);
			this.setRolloverIcon(FREQUENCY_HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		}
		else if (stage == 2)
		{
			/* Sets creation values for the header button */
			this.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			this.setToolTipText(AREA_PANEL_TOOL_TIP_TEXT);
			this.setIcon(AREA_HEADER_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(AREA_HEADER_DISABLED_GRAY_IMAGE_ICON);
			this.setPressedIcon(AREA_HEADER_ENABLED_PREST_IMAGE_ICON);
			this.setRolloverIcon(AREA_HEADER_ENABLED_ROLLOVER_IMAGE_ICON);	
		}
		else if (stage == 3)
		{
			this.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			this.setToolTipText(DENSITY_PANEL_TOOL_TIP_TEXT);
			this.setIcon(DENSITY_HEADER_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(DENSITY_HEADER_DISABLED_GRAY_IMAGE_ICON);
			this.setPressedIcon(DENSITY_HEADER_ENABLED_PREST_IMAGE_ICON);
			this.setRolloverIcon(DENSITY_HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		}
		else if (stage == 4)
		{
			this.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			this.setToolTipText(FILE_NAME_PANEL_TOOL_TIP_TEXT);
			this.setIcon(FILE_NAME_HEADER_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(FILE_NAME_HEADER_DISABLED_GRAY_IMAGE_ICON);
			this.setPressedIcon(FILE_NAME_HEADER_ENABLED_PREST_IMAGE_ICON);
			this.setRolloverIcon(FILE_NAME_HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		}
		else if (stage == 5)
		{
			this.setEnabled(ScanSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			this.setToolTipText(SCAN_PANEL_TOOL_TIP_TEXT);
			this.setIcon(SCAN_HEADER_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(SCAN_HEADER_DISABLED_GRAY_IMAGE_ICON);
			this.setPressedIcon(SCAN_HEADER_ENABLED_PREST_IMAGE_ICON);
			this.setRolloverIcon(SCAN_HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		}
	}
}

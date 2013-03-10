package emcscanner.kth.se;

import javax.swing.ImageIcon;

public class ScanButtons extends Buttons{

	/**
	 * Scan Buttons ID
	 */
	private static final long serialVersionUID = 3046019044813813153L;
	
	public ImageIcon START_SCAN_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/ButtonStartScan.png");
	public ImageIcon START_SCAN_ENABLED_PREST_IMAGE_ICON	= new ImageIcon("image/ButtonStartScanPrest.png");
	public ImageIcon START_SCAN_DISABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonScanStarted.png");
	
	public ImageIcon PAUSED_SCAN_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonPauseScan.png");
	public ImageIcon PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON	= new ImageIcon("image/ButtonPauseScanPrest.png");
	public ImageIcon PAUSED_SCAN_DISABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonPauseScanNotEnabled.png"); 
	
	public ImageIcon STOP_SCAN_ENABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonStopScan.png");
	public ImageIcon STOP_SCAN_ENABLED_PREST_IMAGE_ICON		= new ImageIcon("image/ButtonStopScanPrest.png");
	public ImageIcon STOP_SCAN_DISABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonStopScanNotEnabled.png");
	
	public ImageIcon RESCAN_ENABLED_IMAGE_ICON				= new ImageIcon("image/Rescan.png");
	public ImageIcon RESCAN_PREST_ENABLED_IMAGE_ICON 		= new ImageIcon("image/RescanPrest.png");
	
	public ImageIcon SAVE_ENABLED_IMAGE_ICON				= new ImageIcon("image/Save.png");
	public ImageIcon SAVE_PREST_ENABLED_IMAGE_ICON 			= new ImageIcon("image/SavePrest.png");

	public String START_BUTTON_TOOL_TIP_TEXT 	= "Just start the scan";
	
	public ScanButtons(String type){
		super(Buttons.LARG_BUTTON);
		
		if (type.equals("START")){
			this.setToolTipText(START_BUTTON_TOOL_TIP_TEXT);
			this.setEnabled(true);
			this.setIcon(START_SCAN_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(START_SCAN_DISABLED_IMAGE_ICON);
			this.setPressedIcon(START_SCAN_ENABLED_PREST_IMAGE_ICON);
		}
		if (type.equals("PAUSE")){
			this.setEnabled(false);
			this.setIcon(PAUSED_SCAN_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(PAUSED_SCAN_DISABLED_IMAGE_ICON);
			this.setPressedIcon(PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON);
		}
		if (type.equals("STOP")){
			this.setEnabled(false);
			this.setIcon(STOP_SCAN_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(STOP_SCAN_DISABLED_IMAGE_ICON);
			this.setPressedIcon(STOP_SCAN_ENABLED_PREST_IMAGE_ICON);
		}
		if (type.equals("RESCAN")){
			this.setEnabled(true);
			this.setIcon(RESCAN_ENABLED_IMAGE_ICON);
			this.setPressedIcon(RESCAN_PREST_ENABLED_IMAGE_ICON);
		}
		if (type.equals("SAVE")){
			this.setEnabled(true);
			this.setIcon(SAVE_ENABLED_IMAGE_ICON);
			this.setPressedIcon(SAVE_PREST_ENABLED_IMAGE_ICON);
		}
	}
}

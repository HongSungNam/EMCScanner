package emcscanner.kth.se;

import javax.swing.ImageIcon;

public class BackButton extends MediumSizeButton {
	
	/**
	 * Back Button ID
	 */
	private static final long serialVersionUID = 7465473110621953115L;
	/* Import the images for the BACK button */
	public static ImageIcon BACK_BUTTON_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonBlueBack.png");
	public static ImageIcon BACK_BUTTON_BLUE_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueBackPrest.png");
	
	public BackButton(int stage) {
		this.setIcon(BACK_BUTTON_ENABLED_IMAGE_ICON);
		this.setPressedIcon(BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		this.setEnabled(true);
	}
}

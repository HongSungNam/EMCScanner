package emcscanner.kth.se;

import javax.swing.ImageIcon;

public class BackButton extends Buttons {
	
	/**
	 * Back Button ID
	 */
	private static final long serialVersionUID = 7465473110621953115L;
	/* Import the images for the BACK button */
	/**
	 * Enabled <br>
	 * Type: PNG <br>
	 * Destination: image/ButtonBlueBack.png
	 */
	public static ImageIcon BACK_BUTTON_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonBlueBack.png");
	/**
	 * Enabled and Pressed <br>
	 * Type: PNG <br>
	 * Destination: image/ButtonBlueBackPrest.png
	 */
	public static ImageIcon BACK_BUTTON_BLUE_PREST_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueBackPrest.png");
	
	/**
	 * super BUTTON_REGULAR_DIMENSION
	 * @param stage
	 */
	public BackButton(int stage) {
		super(Buttons.MEDIUM_BUTTON);
		this.setIcon(BACK_BUTTON_ENABLED_IMAGE_ICON);
		this.setPressedIcon(BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		this.setEnabled(true);
	}
}

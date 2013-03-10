package emcscanner.kth.se;

import javax.swing.ImageIcon;

public class QuitButton extends Buttons{

	/**
	 * Quit Button ID
	 */
	private static final long serialVersionUID = 3280005354361861219L;
	/* Imports the different images for the different button stages. */	
	/**
	 * Active 
	 * Location: image/Quit.png
	 */
	public ImageIcon QUIT_ENABLED_IMAGE_ICON				= new ImageIcon("image/Quit.png");
	/**
	 * Active Pressed
	 * Location: image/QuitPrest.png
	 */
	public ImageIcon QUIT_PRESDED_ENABLED_IMAGE_ICON 			= new ImageIcon("image/QuitPrest.png");
	/**
	 * Quit Button
	 * super() = LARG_BUTTON
	 */
	public QuitButton() {
		super(Buttons.LARG_BUTTON);
		/* Back on step JButton */
		this.setEnabled(true);
		this.setIcon(QUIT_ENABLED_IMAGE_ICON);
		this.setPressedIcon(QUIT_PRESDED_ENABLED_IMAGE_ICON);
	}
}

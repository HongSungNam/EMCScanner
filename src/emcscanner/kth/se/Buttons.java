package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JButton;

public class Buttons extends JButton {

	/**
	 * Buttons
	 */
	private static final long serialVersionUID = 363542866695093707L;
	/* Dimension */
	public static Dimension BUTTON_DIMENSION 		= new Dimension(85, 50);
	public static Dimension MEDIUM_BUTTON_DIMENSION = new Dimension(100, 50);
	public static Dimension BUTTON_SMAL_DIMENSION 	= new Dimension(20, 20);


	public Buttons() {
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
}

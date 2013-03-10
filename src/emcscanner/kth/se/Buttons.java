package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JButton;

public class Buttons extends JButton {

	/**
	 * Buttons
	 */
	private static final long serialVersionUID = 363542866695093707L;
	/* Dimension */
	public Dimension HEADER_BUTTON_DIMENSION 		= new Dimension(355, 40);
	public static Dimension BUTTON_DIMENSION 		= new Dimension(85, 50);
	public static Dimension MEDIUM_BUTTON_DIMENSION = new Dimension(100, 50);
	public static Dimension BUTTON_SMAL_DIMENSION 	= new Dimension(20, 20);

	public Buttons(int size) {
		if (size == 1)
			this.setPreferredSize(BUTTON_SMAL_DIMENSION);
		else if (size == 2)
			this.setPreferredSize(BUTTON_DIMENSION);
		else if (size == 3)
			this.setPreferredSize(MEDIUM_BUTTON_DIMENSION);
		else if (size == 4)
			this.setPreferredSize(HEADER_BUTTON_DIMENSION);
		
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
}

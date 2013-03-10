package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JButton;

/**
 * 
 * @author Jonas
 *
 */
public class Buttons extends JButton {
	/**
	 * Buttons ID
	 */
	private static final long serialVersionUID = 363542866695093707L;
	/* Dimension */
	/**
	 * Header button dimension <br>
	 * 355 Width, 40 Height 
	 */
	public static Dimension HEADER_BUTTON_DIMENSION  = new Dimension(355, 40);
	/**
	 * Used by back button <br>
	 * 85 Width, 50 Height 
	 */
	public static Dimension MEDIUM_BUTTON_DIMENSION = new Dimension(85, 50);
	/**
	 * Used by Scan buttons <br>
	 * 100 Width, 50 Height 
	 */
	public static Dimension LARGE_BUTTON_DIMENSION  = new Dimension(100, 50);
	/**
	 * Used by density buttons <br>
	 * 20 Width, 20 Height 
	 */
	public static Dimension BUTTON_SMAL_DIMENSION 	 = new Dimension(20, 20);
	
	/**
	 * Small Size Button
	 * Width: 20 <br>
	 * Height: 20
	 */
	public static String SMALL_BUTTON = "SMAL_BUTTON";
	/**
	 * Medium Size Button
	 * Width: 85 <br>
	 * Height: 50
	 */
	public static String MEDIUM_BUTTON = "MEDIUM_BUTTON";
	/**
	 * Large Size Button
	 * Width: 100 <br>
	 * Height: 50
	 */
	public static String LARG_BUTTON = "LARG_BUTTON";
	/**
	 * Header Size Button
	 * Width: 355 <br>
	 * Height: 40
	 */
	public static String HEADER_BUTTON = "HEADER_BUTTON";

	/**
	 * Function: Sets dimensions
	 * 
	 * setOpaque = false
	 * setContentAreaFilled = false
	 * setBorderPainted = false
	 * @param size
	 */
	public Buttons(String size) {
		if (size.equals(SMALL_BUTTON))
			this.setPreferredSize(BUTTON_SMAL_DIMENSION);
		else if (size.equals(MEDIUM_BUTTON))
			this.setPreferredSize(MEDIUM_BUTTON_DIMENSION);
		else if (size.equals(LARG_BUTTON))
			this.setPreferredSize(LARGE_BUTTON_DIMENSION);
		else if (size.equals(HEADER_BUTTON))
			this.setPreferredSize(HEADER_BUTTON_DIMENSION);
		
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
	}
}

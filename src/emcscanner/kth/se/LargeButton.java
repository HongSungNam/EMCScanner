package emcscanner.kth.se;

import java.awt.Dimension;

public class LargeButton extends Buttons {
	/**
	 * Large Button ID
	 */
	private static final long serialVersionUID = 6526954067545514100L;
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	
	public LargeButton() {
		this.setPreferredSize(HEADER_BUTTON_DIMENSION);
	}
}

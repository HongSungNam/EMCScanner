package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JTextField;

public class DensityTextField extends JTextField {
	/**
	 * Density Text Field ID
	 */
	private static final long serialVersionUID = 2318749999109384926L;
	
	public int densityInputLimit = 5;
	
	public Dimension INPUT_TEXT_FEILD_DIMENSION = new Dimension(20, 20);

	public DensityTextField() {
		super(5);
		
		this.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
		this.setBackground(Program.LIGHT_BLUE_COLOR2);
		this.setDocument(new LengthRestrictedDocument(densityInputLimit));
		this.setBorder(Program.LIGHT_BLUE_BORDER);
	}
}

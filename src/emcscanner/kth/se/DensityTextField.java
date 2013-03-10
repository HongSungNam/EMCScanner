package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JTextField;

/**
 * 
 * @author Jonas
 *
 */
public class DensityTextField extends JTextField {
	/**
	 * Density Text Field ID
	 */
	private static final long serialVersionUID = 2318749999109384926L;
	
	/**
	 * Number of characters  = 5.
	 */
	public int densityInputLimit = 5;
	
	/**
	 * Text field dimensions. <br>
	 * Width: 20 <br>
	 * Height: 20
	 */
	public Dimension INPUT_TEXT_FEILD_DIMENSION = new Dimension(20, 20);

	/**
	 * Super: Max number of lines
	 */
	public DensityTextField() {
		super(5);
		
		this.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
		this.setBackground(Program.LIGHT_BLUE_COLOR2);
		this.setDocument(new LengthRestrictedDocument(densityInputLimit));
		this.setBorder(Program.LIGHT_BLUE_BORDER);
	}
}

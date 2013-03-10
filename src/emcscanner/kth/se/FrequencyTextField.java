package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JTextField;

public class FrequencyTextField extends JTextField {

	/**
	 * Text Field ID
	 */
	private static final long serialVersionUID = -31097942201085907L;

	public int frequencyInputLimit = 6;
	
	public Dimension FLOAT_INPUT_TEXT_FEILD_DIMENSION = new Dimension(20,20);
	
	public FrequencyTextField(int type) {
		super(4);

		this.setPreferredSize(FLOAT_INPUT_TEXT_FEILD_DIMENSION);
		this.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		
		if (type == 1){
			/* Text field for importing frequency from user only values from 0.1 to 600 */
			this.setBorder(Program.LIGHT_BLUE_BORDER);
			this.setBackground(Program.LIGHT_BLUE_COLOR2);
		}
		else if (type == 2){
			this.setBorder(Program.LIGHT_GRAY_BORDER);
			this.setBackground(Program.LIGHT_GRAY_COLOR2);
			this.setEnabled(false);
		}
		else if (type == 3){
			this.setBorder(Program.LIGHT_GRAY_BORDER);
			this.setBackground(Program.LIGHT_GRAY_COLOR2);
			this.setEnabled(false);
		}
	}
}

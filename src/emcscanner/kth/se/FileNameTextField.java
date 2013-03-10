package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.JTextField;
/**
 * 
 * @author Jonas
 *
 */
public class FileNameTextField extends JTextField {
	/**
	 * File Name Text Field ID
	 */
	private static final long serialVersionUID = -3545219919589093429L;
	
	public Dimension INPUT_TEXT_FEILD_DIMENSION = new Dimension(20, 20);
	public static int fileNameLengthLimit = 10;
	/**
	 * 
	 */
	public FileNameTextField(){
		super(fileNameLengthLimit);
		
		/* INPUT field for width */
	    this.setPreferredSize(INPUT_TEXT_FEILD_DIMENSION);
	    this.setDocument(new LengthRestrictedDocument(fileNameLengthLimit));
	    this.setBorder(Program.LIGHT_BLUE_BORDER);
	    this.setBackground(Program.LIGHT_BLUE_COLOR2);
	}

}

package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.ImageIcon;

public class NextButton extends Buttons {

	/**
	 * Next Button ID
	 */
	private static final long serialVersionUID = -4341684682808629018L;
	
	public static Dimension BUTTON_DIMENSION = new Dimension(85, 50);

	public String FREQUENCY_NEXT_BUTTON_TOOL_TIP_TEXT 	= "You need to write a number between 0.1 and 6000 befor you can continue";	
	public String AREA_NEXT_BUTTON_TOOL_TIP_TEXT 		= "You need to select an area before you can continue";
	public String DENSITY_NEXT_BUTTON_TOOL_TIP_TEXT 	= "You need to select an area before you can continue";
	public String FILE_NAME_NEXT_BUTTON_TOOL_TIP_TEXT 	= "You need to select an a valid file name before you can continue";
	
	/* Image Icon next button */
	public static ImageIcon NEXT_BUTTON_ENABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueNext.png");
	public static ImageIcon NEXT_BUTTON_DISABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonGrayNext.png");
	public static ImageIcon NEXT_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonBlueNextPrest.png");
	public static ImageIcon NEXT_BUTTON_GRAY_PREST_IMAGE_ICON = new ImageIcon("image/ButtonGrayNextPrest.png");

	public NextButton(int stage){
		super(Buttons.MEDIUM_BUTTON);
		this.setIcon(NEXT_BUTTON_ENABLED_IMAGE_ICON);
		this.setDisabledIcon(NEXT_BUTTON_DISABLED_IMAGE_ICON);
		this.setPressedIcon(NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
		this.setDisabledSelectedIcon(NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		this.setEnabled(false);
		
		if (stage == 1)
			this.setToolTipText(FREQUENCY_NEXT_BUTTON_TOOL_TIP_TEXT);
		else if (stage == 2)
			this.setToolTipText(AREA_NEXT_BUTTON_TOOL_TIP_TEXT);
		else if (stage == 3)
			this.setToolTipText(DENSITY_NEXT_BUTTON_TOOL_TIP_TEXT);
		else if (stage == 4)
			this.setToolTipText(FILE_NAME_NEXT_BUTTON_TOOL_TIP_TEXT);
	}
}

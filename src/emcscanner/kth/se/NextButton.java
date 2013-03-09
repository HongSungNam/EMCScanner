package emcscanner.kth.se;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NextButton extends JButton {

	/**
	 * Next Button ID
	 */
	private static final long serialVersionUID = -4341684682808629018L;
	
	public static Dimension BUTTON_DIMENSION = new Dimension(85, 50);

	public String FREQUENCY_NEXT_BUTTON_TOOL_TIP_TEXT = "You need to write a number between 0.1 and 6000 befor you can continue";
	
	/* Image Icon next button */
	public static ImageIcon FREQUENCY_NEXT_BUTTON_ENABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonBlueNext.png");
	public static ImageIcon FREQUENCY_NEXT_BUTTON_DISABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonGrayNext.png");
	public static ImageIcon FREQUENCY_NEXT_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonBlueNextPrest.png");
	public static ImageIcon FREQUENCY_NEXT_BUTTON_GRAY_PREST_IMAGE_ICON = new ImageIcon("image/ButtonGrayNextPrest.png");

	public NextButton(int stage){
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setPreferredSize(BUTTON_DIMENSION);
		
		if (stage == 1)
		{
			this.setToolTipText(FREQUENCY_NEXT_BUTTON_TOOL_TIP_TEXT);
			this.setEnabled(false);
			this.setIcon(FREQUENCY_NEXT_BUTTON_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(FREQUENCY_NEXT_BUTTON_DISABLED_IMAGE_ICON);
			this.setPressedIcon(FREQUENCY_NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
			this.setDisabledSelectedIcon(FREQUENCY_NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		}
		else if (stage == 2)
		{
			
		}
		else if (stage == 3)
		{
			
		}
		else if (stage == 4)
		{
			
		}
		else if (stage == 5)
		{
			
		}
	}
}

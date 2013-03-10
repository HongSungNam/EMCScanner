package emcscanner.kth.se;

import javax.swing.ImageIcon;
/**
 * 
 * @author Jonas
 *
 */
public class DensityButton extends Buttons{

	/**
	 * Density Button ID
	 */
	private static final long serialVersionUID = 6891789755912252704L;
	/**
	 * Used by the millimeter button <br>
	 * Destination: image/ButtonStepMMBlue.png
	 */
	public static ImageIcon DENSITY_MM_BUTTON_ENABLED_IMAGE_ICON = new ImageIcon("image/ButtonStepMMBlue.png");
	/**
	 * Used by the millimeter button <br>
	 * Destination: image/ButtonStepMMGray.png
	 */
	public static ImageIcon DENSITY_MM_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonStepMMGray.png");
	/**
	 * Used by the millimeter button <br>
	 * Destination: image/ButtonStepMMBluePrest.png
	 */
	public static ImageIcon DENSITY_MM_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonStepMMBluePrest.png");

	/**
	 * Used by the step button <br>
	 * Destination: image/ButtonNumberStepBlue.png
	 */
	public static ImageIcon DENSITY_STEP_BUTTON_ENABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonNumberStepBlue.png");
	/**
	 * Used by the step button <br>
	 * Destination: image/ButtonNumberStepGray.png
	 */
	public static ImageIcon DENSITY_STEP_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonNumberStepGray.png");
	/**
	 * Used by the step button <br>
	 * Destination: image/ButtonNumberStepBluePrest.png
	 */
	public static ImageIcon DENSITY_STEP_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonNumberStepBluePrest.png");
	
	/**
	 * Extends Buttons:
	 * @param type
	 */
	public DensityButton(int type){
		super(Buttons.SMALL_BUTTON);
		if (type == 1)
		{
			this.setEnabled(true);
			this.setIcon(DENSITY_MM_BUTTON_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(DENSITY_MM_BUTTON_DISABLED_IMAGE_ICON);
			this.setPressedIcon(DENSITY_MM_BUTTON_BLUE_PREST_IMAGE_ICON);
		}
		if (type == 2)
		{
			this.setEnabled(false);
			this.setIcon(DENSITY_STEP_BUTTON_ENABLED_IMAGE_ICON);
			this.setDisabledIcon(DENSITY_STEP_BUTTON_DISABLED_IMAGE_ICON);
			this.setPressedIcon(DENSITY_STEP_BUTTON_BLUE_PREST_IMAGE_ICON);	
		}
	}
}

package emcscanner.kth.se;

import javax.swing.ImageIcon;

public class DensityButton extends Buttons{

	/**
	 * Density Button ID
	 */
	private static final long serialVersionUID = 6891789755912252704L;
	
	public static ImageIcon DENSITY_MM_BUTTON_ENABLED_IMAGE_ICON = new ImageIcon("image/ButtonStepMMBlue.png");
	public static ImageIcon DENSITY_MM_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonStepMMGray.png");
	public static ImageIcon DENSITY_MM_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonStepMMBluePrest.png");

	public static ImageIcon DENSITY_STEP_BUTTON_ENABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonNumberStepBlue.png");
	public static ImageIcon DENSITY_STEP_BUTTON_DISABLED_IMAGE_ICON = new ImageIcon("image/ButtonNumberStepGray.png");
	public static ImageIcon DENSITY_STEP_BUTTON_BLUE_PREST_IMAGE_ICON = new ImageIcon("image/ButtonNumberStepBluePrest.png");
	
	public DensityButton(int type){
		super(1);
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

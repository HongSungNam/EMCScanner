package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/***
 * 
 * @author Jonas
 *
 */
public class DensityActionListener implements ActionListener {
	/**
	 * 1: Millimeter <br>
	 * 2: Step
	 */
	int action;
	/**
	 * Decides if it is millimeter or step.
	 * @param i
	 */
	public DensityActionListener(int i){
		this.action = i;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.action == 1)
		{
			DensitySettingsSubPanel.densityMillimeter.setEnabled(false);
			DensitySettingsSubPanel.densityNumberOfSteps.setEnabled(true);
			DensitySettingsSubPanel.inputStepBoolean = false;
			Program.frame.glass.repaint();
		}
		else if (this.action == 2)
		{
			DensitySettingsSubPanel.densityMillimeter.setEnabled(true);
			DensitySettingsSubPanel.densityNumberOfSteps.setEnabled(false);
			DensitySettingsSubPanel.inputStepBoolean = true;
			Program.frame.glass.repaint();
		}
	}
}

package emcscanner.kth.se;
/**
 * 
 * @author Jonas
 * Sets the stage
 */
public class StageActive {
	/**
	 * Sets the stage that the parameter represent
	 * @param i for what stage is to be set 
	 */
	public static void stageActive(int i) {
		if (i == 1)
		{
			/* ACTIVE */
			SetPanelActive.frequencyPanelActive();
			/* IN ACTIVE */
			SetPanelInactive.areaPanelNotActive();
			SetPanelInactive.densityPanelNotActive();
			SetPanelInactive.fileNamePanelNotActive();
			SetPanelInactive.scanPanelNotActive();
		}
		if (i == 2)
		{
			/* IN ACTIVE */
			SetPanelInactive.frequencyPanelNotActive();
			/* ACTIVE */
			SetPanelActive.areaPanelActive();
			/* IN ACTIVE */
			SetPanelInactive.densityPanelNotActive();
			SetPanelInactive.fileNamePanelNotActive();
			SetPanelInactive.scanPanelNotActive();
		}
		if (i == 3)
		{
			/* IN ACTIVE */
			SetPanelInactive.frequencyPanelNotActive();
			SetPanelInactive.areaPanelNotActive();
			/* ACTIVE */
			SetPanelActive.densityPanelActive();
			/* IN ACTIVE */
			SetPanelInactive.fileNamePanelNotActive();
			SetPanelInactive.scanPanelNotActive();
		}
		if (i == 4)
		{
			/* IN ACTIVE */
			SetPanelInactive.densityPanelNotActive();
			SetPanelInactive.areaPanelNotActive();
			SetPanelInactive.frequencyPanelNotActive();
			/* ACTIVE */
			SetPanelActive.fileNamePanelActive();
			/* IN ACTIVE */
			SetPanelInactive.scanPanelNotActive();
		}
		if (i == 5)
		{
			SetPanelInactive.frequencyPanelNotActive();
			SetPanelInactive.areaPanelNotActive();
			SetPanelInactive.densityPanelNotActive();
			SetPanelInactive.fileNamePanelNotActive();
			/* ACTIVE */
			SetPanelActive.scanPanelActive();
		}
	}
}

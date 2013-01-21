package emcscanner.kth.se;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel{
	public static int step = 1;
	public static SettingsSubPanel frequencyPanel;
	public static SettingsSubPanel areaPanel;
	public static SettingsSubPanel scanDensityPanel;
	public static SettingsSubPanel fileNamePanel;
	
	public SettingsPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(0,0));
		
		frequencyPanel = new SettingsSubPanel("Step 1/4", "  Choose the frequency ", "This is where you can shoose the desired frequency.", 1);
		areaPanel = new SettingsSubPanel("Step 2/4", "  Choose the area to scan ", "This is where you select the area to scan.", 2);
		scanDensityPanel = new SettingsSubPanel("Step 3/4", "  Choose scan density ", "This is where you select the density between eatch scanned point.", 3);
		fileNamePanel = new SettingsSubPanel("Step 4/4", "  Choose file Name ", "This is where you select the file name that you want to have.", 4);
		
		this.setFocusable(true);
		this.add(frequencyPanel);
		this.add(areaPanel);
		this.add(scanDensityPanel);
		this.add(fileNamePanel);
	}
}

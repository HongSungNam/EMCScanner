package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author Jonas
 *
 */
public class SettingsPanel extends JPanel{
	/* User selected values */
	public static float frequency;
	public static float areaSelected;
	
	/* Global values used by the program */
	public static int step = 1;
	public static SettingsSubPanel frequencyPanel;
	public static SettingsSubPanel areaPanel;
	public static SettingsSubPanel scanDensityPanel;
	public static SettingsSubPanel fileNamePanel;
	
	/**
	 * SettingsPanel
	 * 
	 * 
	 */
	public SettingsPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(0,0));
		
		/* Container made for GUI to be able to add the back button in the right corner */
		JPanel settingsContiner  = 	new JPanel();
		settingsContiner.setLayout(new BoxLayout(settingsContiner, BoxLayout.Y_AXIS));
		
		/* Creates the different sub panels for the settingsPanel */
		frequencyPanel = new SettingsSubPanel("Step 1/4", "This is where you can shoose the desired frequency.", 1);
		areaPanel = new SettingsSubPanel("Step 2/4", "This is where you select the area to scan.", 2);
		scanDensityPanel = new SettingsSubPanel("Step 3/4", "This is where you select the density between eatch scanned point.", 3);
		fileNamePanel = new SettingsSubPanel("Step 4/4", "This is where you select the file name that you want to have.", 4);
		settingsContiner.add(frequencyPanel);
		settingsContiner.add(areaPanel);
		settingsContiner.add(scanDensityPanel);
		settingsContiner.add(fileNamePanel);
		
		/* Import the images for the back button */
		ImageIcon backButtonEnabledIcon = new ImageIcon("image/ButtonBlueBack.png");
		ImageIcon backButtonBlueNextPrestIcon = new ImageIcon("image/ButtonBlueBackPrest.png");
		
		/* Button made for going back to previous views */
		final JButton backButton = new JButton();
		backButton.setEnabled(true);
		backButton.setIcon(backButtonEnabledIcon);
		backButton.setPressedIcon(backButtonBlueNextPrestIcon);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.setStages(Program.startControlPanel, Program.manualPanel);
			}
		});
		
		/* continer made for setting backButton in the corner to the bottom */
		JPanel continer  = 	new JPanel();
		continer.setLayout(new BorderLayout());
		continer.add(backButton, BorderLayout.WEST);
		
		this.add(settingsContiner, BorderLayout.NORTH);
		this.add(continer, BorderLayout.SOUTH);
	}
}

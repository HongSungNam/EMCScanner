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
	public static float FREQUENCY;
	public static boolean AREA_SELECTED = false;
	public static float AREA_SELECTED_START_X;
	public static float AREA_SELECTED_START_Y;
	public static float AREA_SELECTED_END_X;
	public static float AREA_SELECTED_END_Y;
	public static Dimension AREA_SELECTED_CAMERA_DIMENSION;
	
	/* Global values used by the program */
	public static FrequensySettingsSubPanel frequencyPanel;
	public static AreaSettingsSubPanel areaPanel;
	public static DensitySettingsSubPanel densityPanel;
	//public static SettingsSubPanel fileNamePanel;
	
	public JButton backButton = new JButton();
	
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
		frequencyPanel = new FrequensySettingsSubPanel();
		areaPanel = new AreaSettingsSubPanel();
		densityPanel = new DensitySettingsSubPanel();
		//fileNamePanel = new SettingsSubPanel("Step 4/4", "This is where you select the file name that you want to have.", 4);
		settingsContiner.add(frequencyPanel);
		settingsContiner.add(areaPanel);
		settingsContiner.add(densityPanel);
		//settingsContiner.add(fileNamePanel);
		
		
		
		/* Button made for going back to previous views */
		backButton.setEnabled(true);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AreaSettingsSubPanel.DISPLAY_AREA_HELP_VIDEO = false;
				MainPanel.setStages(Program.startControlPanel, Program.manualPanel);
			}
		});
		
		/* Container made for setting backButton in the corner to the bottom */
		JPanel continer  = 	new JPanel();
		continer.setLayout(new BorderLayout());
		continer.add(backButton, BorderLayout.WEST);
		
		this.add(settingsContiner, BorderLayout.NORTH);
		this.add(continer, BorderLayout.SOUTH);
	}
}

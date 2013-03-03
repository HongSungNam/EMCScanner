package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainBackActionListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		//Program.cameraPanel.DISPLAY_WEB_CAMERA_INPUT = false;
		Program.manualPanel.setVisible(true);
		Program.startControlPanel.setVisible(true);
		Program.cameraPanel.setVisible(false);
		Program.imagePanel.setVisible(false);
		Program.settingsPanel.setVisible(false);

		Program.frame.glass.glasPanelActive = false;
		Program.startControlPanel.nextButton.grabFocus();
	}
}

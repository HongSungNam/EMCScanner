package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @param None
 */
public class StartControlPanel extends JPanel {
	public StartControlPanel() {	
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0));
		
		/* Imports the different images for the different button stages. */
		ImageIcon nextButtonEnabledIcon = new ImageIcon("image/ButtonBlueNext.png");
		ImageIcon nextButtonBlueNextPrestIcon = new ImageIcon("image/ButtonBlueNextPrest.png");
		
		/* Creates a next button and adds it in the corner */
		JButton nextButton = new JButton();
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText("You need to write a number between 0.1 and 6000 befor you can continue");
		nextButton.setPreferredSize(new Dimension(90, 50));
		nextButton.setEnabled(true);
		nextButton.setIcon(nextButtonEnabledIcon);
		nextButton.setPressedIcon(nextButtonBlueNextPrestIcon);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.setStages(Program.cameraPanel, Program.settingsPanel);
				if (FrequensySettingsSubPanel.FREQUENCY_SELECTED)
					AreaSettingsSubPanel.DISPLAY_AREA_HELP_VIDEO = true;
			}
		});
		
		/* Panel made for setting button at the bottom right corner */
		JPanel container = new JPanel(new BorderLayout());
		
		/* Adds Next Button in the right corner at the bottom */
		container.add(nextButton, BorderLayout.SOUTH);
		this.add(container, BorderLayout.EAST);
	}
}


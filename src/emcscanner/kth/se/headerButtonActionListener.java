package emcscanner.kth.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeaderButtonActionListener implements ActionListener{
	private int stage;
	public HeaderButtonActionListener(int stage) {
		this.stage = stage;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED)
		{
			if (this.stage == 1)
			{
				StageActive.stageActive(1);
				SettingsPanel.frequencyPanel.startFloatInputTextField.grabFocus();
			}
			if (this.stage == 2)
			{
				SettingsPanel.setFREQUENCY_SELECTED(true);
				StageActive.stageActive(2);
				AreaSettingsSubPanel.backButton.grabFocus();
			}
			if (this.stage == 3)
			{
				SettingsPanel.setFREQUENCY_SELECTED(true);
				StageActive.stageActive(3);
				SettingsPanel.densityPanel.widthDensityInputTextField.grabFocus();
			}
			if (this.stage == 4)
			{
				SettingsPanel.setFREQUENCY_SELECTED(true);
				StageActive.stageActive(4);
				SettingsPanel.fileNamePanel.fileNameInputTextField.grabFocus();
			}
			if (this.stage == 5)
				StageActive.stageActive(5);
		}
	}
}

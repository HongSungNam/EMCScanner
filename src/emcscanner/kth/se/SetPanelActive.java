package emcscanner.kth.se;

import java.awt.BorderLayout;
/**
 * 
 * @author Jonas
 *
 */
public class SetPanelActive {
	/**
	 * Sets the Frequency Panel to ACTIVE stage
	 */
	public static void frequencyPanelActive() {
		SettingsPanel.setStage(FrequencySettingsSubPanel.STAGE);
		
		FrequencySettingsSubPanel.DISPLAY_HELP_VIDEO = true;

		Program.settingsPanel.setVisible(true);
		Program.cameraPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.imagePanel.setVisible(false);
		
		MainPanel.setLeftStage(Program.cameraPanel);
		Program.frame.glass.setVisible(false);
		
		/* Sets header back to blue */
		FrequencySettingsSubPanel.headerButton.setToolTipText(FrequencySettingsSubPanel.getPanelInformation());
		FrequencySettingsSubPanel.headerButton.setEnabled(false);
		
		/* Turns on */ 
		SettingsPanel.frequencyPanel.startFloatInputTextField.setVisible(true);
		SettingsPanel.frequencyPanel.getStartMoreThenLabel().setVisible(true);
		SettingsPanel.frequencyPanel.getStartLessThenLabel().setVisible(true);
		FrequencySettingsSubPanel.nextButton.setVisible(true);
		/* Containers for setting up GUI */
		SettingsPanel.frequencyPanel.continer1.setVisible(true);
		SettingsPanel.frequencyPanel.continer2.setVisible(true);
		SettingsPanel.frequencyPanel.startTextFeildContainer.setVisible(true);
		SettingsPanel.frequencyPanel.continer4.setVisible(true);
		SettingsPanel.frequencyPanel.continer5.setVisible(true);
		SettingsPanel.frequencyPanel.continer6.setVisible(true);
		SettingsPanel.frequencyPanel.startFrequensyContainer.setVisible(true);
		FrequencySettingsSubPanel.backButton.setVisible(true);
		SettingsPanel.frequencyPanel.endFrequensyContainer.setVisible(true);
		FrequencySettingsSubPanel.colorFrequensyVideoPanel.setVisible(true);
		
		/* New Active dimensions */
		SettingsPanel.frequencyPanel.frequencyPanel.setPreferredSize(SettingsPanel.frequencyPanel.FREQUENCY_PANEL_DIMENSION_ACTIVE);
		SettingsPanel.frequencyPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.frequencyPanel.HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		SettingsPanel.frequencyPanel.stepContiner.setPreferredSize(SettingsPanel.frequencyPanel.STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Sets Active colors light blue to border and text */
		SettingsPanel.frequencyPanel.getStepLabel().setText(SettingsPanel.frequencyPanel.getStepLightBlueText());
		SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Turns off */
		SettingsPanel.frequencyPanel.getFrequencyLabel().setVisible(false);
		SettingsPanel.setFREQUENCY_SELECTED(false);
	}
	/**
	 * Sets the Area Panel to a ACTIVE stage
	 */
	public static void areaPanelActive(){
		
		AreaSettingsSubPanel.DISPLAY_HELP_VIDEO = true;
		
		MainPanel.setLeftStage(Program.cameraPanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(true);
		Program.imagePanel.setVisible(false);

		SettingsPanel.setStage(AreaSettingsSubPanel.STAGE);
		
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);
		
		/* Glass Panel visible and active */
		MainFrame.setGET_AREA_BOOLEAN(true);
		
		/* Turns off header button */
		AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
		/***************************************************************************************************************************/
		AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_BLUE_IMAGE_ICON);
		
		/* New tool tip */
		AreaSettingsSubPanel.headerButton.setToolTipText(SettingsPanel.areaPanel.HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Turns ON next back label video */
		AreaSettingsSubPanel.nextButton.setVisible(true);
		AreaSettingsSubPanel.backButton.setVisible(true);
		SettingsPanel.areaPanel.areaNotSelectedLabel.setVisible(true);
		AreaSettingsSubPanel.colorAreaVideoPanel.setVisible(true);
		SettingsPanel.areaPanel.continer1.setVisible(true);
		SettingsPanel.areaPanel.areaSelectedContainer.setVisible(true);
		
		SettingsPanel.areaPanel.areaPanel.setVisible(true);
		SettingsPanel.areaPanel.areaPanel.add(SettingsPanel.areaPanel.areaLabel, BorderLayout.EAST);
		
		/* Turns OFF area Label */
		SettingsPanel.areaPanel.areaLabel.setVisible(false);
		
		/* Changing size of panels when button has been pressed*/	
		SettingsPanel.areaPanel.areaPanel.setPreferredSize(SettingsPanel.areaPanel.AREA_PANEL_DIMENSION_ACTIVE);
		SettingsPanel.areaPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.areaPanel.HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		SettingsPanel.areaPanel.stepContiner.setPreferredSize(SettingsPanel.areaPanel.STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Sets appropriate blue active colors */
		SettingsPanel.areaPanel.stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_LIGHT_BLUE);
		SettingsPanel.areaPanel.areaPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* If a area has been selected */
		if(Program.frame.glass.cursorReleased.x > 0 && Program.frame.glass.cursorReleased.y > 0)
		{
			AreaSettingsSubPanel.nextButton.setEnabled(true);
			Program.frame.setMOUSE_RELEASED_BOOLEAN(true);
		}
	}
	/**
	 * Sets the Density Panel to a ACTIVE stage
	 */
	public static void densityPanelActive() {
		DensitySettingsSubPanel.DISPLAY_HELP_VIDEO = true;
		
		MainPanel.setLeftStage(Program.imagePanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(false);
		Program.imagePanel.setVisible(true);
		
		SettingsPanel.setStage(DensitySettingsSubPanel.STAGE);
		
		Program.frame.glass.setVisible(DensitySettingsSubPanel.VISIBLE);
		
		/* Shows the help video when made active */
		DensitySettingsSubPanel.DISPLAY_HELP_VIDEO = DensitySettingsSubPanel.VISIBLE;
		
		/* Sets active color Blue for header, labels and borders*/
		/***************************************************************************************************************************/
		DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
		DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_BLUE_IMAGE_ICON);
		SettingsPanel.densityPanel.stepLabel.setText(SettingsPanel.densityPanel.STEP_TEXT_LIGHT_BLUE);
		SettingsPanel.densityPanel.densityPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Shows buttons and labels */
		DensitySettingsSubPanel.nextButton.setVisible(DensitySettingsSubPanel.VISIBLE);
		DensitySettingsSubPanel.backButton.setVisible(DensitySettingsSubPanel.VISIBLE);
		DensitySettingsSubPanel.colorDensityVideoPanel.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.heightDensityInputTextField.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.widthDensityInputTextField.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.scanDensityLabel.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.widthLabel.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.heightLabel.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.widthLabel0.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.heightLabel0.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.heightLabelValue.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.widthLabelValue.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.noteLabel.setVisible(DensitySettingsSubPanel.VISIBLE);
		SettingsPanel.densityPanel.getInputFeildsAButtons().setVisible(DensitySettingsSubPanel.VISIBLE);
		
		/* Changing size of panels when button has been pressed*/	
		SettingsPanel.densityPanel.densityPanel.setPreferredSize(SettingsPanel.densityPanel.DENSITY_PANEL_DIMENSION_ACTIVE);

		/* Changing size of panels when button has been pressed*/	
		SettingsPanel.densityPanel.densityPanel.setPreferredSize(SettingsPanel.densityPanel.DENSITY_PANEL_DIMENSION_ACTIVE);
		SettingsPanel.densityPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.densityPanel.HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		SettingsPanel.densityPanel.stepContiner.setPreferredSize(SettingsPanel.densityPanel.STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Turns on Panel */
		SettingsPanel.densityPanel.densityPanel.setVisible(true);

		/* Doesn't show label */
		SettingsPanel.densityPanel.densitySelectedLabel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		
		int value = (SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH+1);
		int value2 = (SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT+1);
		if (value > ((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
		{
			SettingsPanel.densityPanel.widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
			SettingsPanel.densityPanel.widthLabelValue.setText("<html><font color = rgb(255,0,0)>" + 
															  (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * 
																	  Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</font></html>");
			SettingsPanel.densityPanel.widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
			SettingsPanel.densityPanel.widthDensityInputTextField.setBorder(Program.RED_BORDER);
			DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY = false;
		}
		else if (value <= 0)
		{
			SettingsPanel.densityPanel.widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
			SettingsPanel.densityPanel.widthLabelValue.setText("<html>" + 
																(int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * 
																		Program.TIONDELS_MILLI_METER_PIXEL)  + 
																		" &gt&nbsp</html>");
			SettingsPanel.densityPanel.widthLabel0.setText("<html><font color = rgb(255,0,0)> &nbsp&gt 0&nbsp</font></html>");
			SettingsPanel.densityPanel.widthDensityInputTextField.setBorder(Program.RED_BORDER);
			DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY = false;
		}
		else
		{
			SettingsPanel.densityPanel.widthLabel.setText("<html><font color = rgb(100,150,255)> Width: </font></html>");
			SettingsPanel.densityPanel.widthLabelValue.setText("<html>" + 
																(int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1)* 
																		Program.TIONDELS_MILLI_METER_PIXEL) + 
																		" &gt&nbsp</html>");
			SettingsPanel.densityPanel.widthLabel0.setText("<html> &nbsp&gt 0&nbsp </html>");
    	}
		if (value2 > ((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
		{
			SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
			SettingsPanel.densityPanel.heightLabelValue.setText("<html> <font color = rgb(255,0,0)>" + 
																(int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
																		Program.TIONDELS_MILLI_METER_PIXEL) + 
																		" &gt&nbsp</font></html>");
			SettingsPanel.densityPanel.heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
			SettingsPanel.densityPanel.heightDensityInputTextField.setBorder(Program.RED_BORDER);
			DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY = false;
		}
		else if (value2 <= 0)
		{
			SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
			SettingsPanel.densityPanel.heightLabelValue.setText("<html>" + 
																(int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
																			Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
			SettingsPanel.densityPanel.heightLabel0.setText("<html><font color = rgb(255,0,0)> &nbsp&gt 0&nbsp</font></html>");
			SettingsPanel.densityPanel.heightDensityInputTextField.setBorder(Program.RED_BORDER);
			DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY = false;
		}
		else
		{
			SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(100,150,255)> Height: </font></html> ");
			SettingsPanel.densityPanel.heightLabelValue.setText("<html>" + 
																	(int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
																			Program.TIONDELS_MILLI_METER_PIXEL) + " &gt&nbsp</html>");
			SettingsPanel.densityPanel.heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
		}
		Program.frame.glass.repaint();
	}
	/**
	 * Sets the File Name Panel to a ACTIVE stage
	 */
	public static void fileNamePanelActive() {
		
		MainPanel.setLeftStage(Program.imagePanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(false);
		Program.imagePanel.setVisible(true);
		
		SettingsPanel.setStage(FileNameSettingsSubPanel.STAGE);
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);
		
		
		/* Sets active color Blue for header, labels and borders*/
		FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
		FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_BLUE_IMAGE_ICON);
		SettingsPanel.fileNamePanel.stepLabel.setText(SettingsPanel.fileNamePanel.STEP_TEXT_LIGHT_BLUE);
		SettingsPanel.fileNamePanel.fileNamePanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Shows buttons and labels */
		FileNameSettingsSubPanel.nextButton.setVisible(true);
		FileNameSettingsSubPanel.backButton.setVisible(true);
		SettingsPanel.fileNamePanel.fileNameInputTextField.setVisible(true);
		SettingsPanel.fileNamePanel.getImputFeildsContainer().setVisible(true);
		SettingsPanel.fileNamePanel.getInputFeildsAButtons().setVisible(true);
		SettingsPanel.fileNamePanel.getInputContainer().setVisible(true);
		
		/* Changing size of panels when button has been pressed*/	
		SettingsPanel.fileNamePanel.fileNamePanel.setPreferredSize(SettingsPanel.fileNamePanel.FILE_NAME_PANEL_DIMENSION_ACTIVE);
		SettingsPanel.fileNamePanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.fileNamePanel.HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		SettingsPanel.fileNamePanel.stepContiner.setPreferredSize(SettingsPanel.fileNamePanel.STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Turns on Panel */
		SettingsPanel.fileNamePanel.fileNamePanel.setVisible(true);
		
		/* Doesn't show label */
		SettingsPanel.fileNamePanel.fileNameSelectedLabel.setVisible(false);
		
	}
	/**
	 * ACTIVE
	 */
	public static void scanPanelActive() {
		MainPanel.setLeftStage(Program.imagePanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(false);
		Program.imagePanel.setVisible(true);
		
		SettingsPanel.setStage(ScanSettingsSubPanel.STAGE);
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);

		/* Shows buttons and labels */
		ScanSettingsSubPanel.startScanButton.setVisible(true);
		ScanSettingsSubPanel.pauseScanButton.setVisible(true);
		ScanSettingsSubPanel.stopScanButton.setVisible(true);
		ScanSettingsSubPanel.rescanButton.setVisible(true);
		ScanSettingsSubPanel.saveButton.setVisible(true);
		
		/* Changing size of panels when button has been pressed*/	
		SettingsPanel.scanPanel.scanPanel.setPreferredSize(SettingsPanel.scanPanel.getSCAN_PANEL_DIMENSION_ACTIVE());
		SettingsPanel.scanPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.scanPanel.getHEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE());
		SettingsPanel.scanPanel.stepContiner.setPreferredSize(SettingsPanel.scanPanel.getSTEP_CONTINER_DIMENSION_ACTIVE());
		
		/* Turns on Panel */
		SettingsPanel.scanPanel.scanPanel.setVisible(true);

		if (SettingsPanel.scanPanel.scan.getScanDone())
		{
			SettingsPanel.scanPanel.getInputFeildsAButtons().remove(SettingsPanel.scanPanel.buttonContiner1);
			SettingsPanel.scanPanel.getInputFeildsAButtons().add(SettingsPanel.scanPanel.buttonContiner2, BorderLayout.SOUTH);
			
			/* Sets active color Blue for header, labels and borders*/
			ScanSettingsSubPanel.headerButton.setEnabled(ScanSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			ScanSettingsSubPanel.headerButton.setDisabledIcon(ScanSettingsSubPanel.headerButton.SCAN_HEADER_ENABLED_IMAGE_ICON);

			/* panel and step label color blue */
			SettingsPanel.scanPanel.stepLabel.setText(SettingsPanel.scanPanel.STEP_TEXT_DARK_GREEN);
			SettingsPanel.scanPanel.scanPanel.setBorder(Program.GREEN_BORDER);
			
			EndSubSettingsPanel.backButton.setEnabled(true);
			
			SettingsPanel.scanPanel.getTimeNorthPanel().setBackground(Program.LIGHT_GREEN_ALPHA_COLOR);
			SettingsPanel.scanPanel.getTimeNorthCeneterPanel().setBackground(Program.LIGHT_GREEN_ALPHA_COLOR1);
			SettingsPanel.scanPanel.getTimeNorthLablePanel().setBackground(Program.LIGHT_GREEN_ALPHA_COLOR1);
			
			SettingsPanel.scanPanel.getStreamingContinaer().setBackground(Program.LIGHT_RED_ALPHA_COLOR);
			
			SettingsPanel.scanPanel.getTimeNorthPanel().setBorder(Program.GREEN_BORDER);
			SettingsPanel.scanPanel.getStreamingContinaer().setBorder(Program.RED_BORDER);
		}
		else 
		{
			SettingsPanel.scanPanel.getInputFeildsAButtons().remove(SettingsPanel.scanPanel.buttonContiner2);
			SettingsPanel.scanPanel.getInputFeildsAButtons().add(SettingsPanel.scanPanel.buttonContiner1, BorderLayout.SOUTH);
			
			/* Sets active color Blue for header, labels and borders*/
			ScanSettingsSubPanel.headerButton.setEnabled(ScanSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			ScanSettingsSubPanel.headerButton.setDisabledIcon(ScanSettingsSubPanel.headerButton.SCAN_HEADER_DISABLED_BLUE_IMAGE_ICON);
			
			/* panel and step label color blue */
			SettingsPanel.scanPanel.stepLabel.setText(SettingsPanel.scanPanel.STEP_TEXT_LIGHT_BLUE);
			SettingsPanel.scanPanel.scanPanel.setBorder(Program.LIGHT_BLUE_BORDER);
			
			SettingsPanel.scanPanel.getTimeNorthPanel().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
			SettingsPanel.scanPanel.getTimeNorthCeneterPanel().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
			SettingsPanel.scanPanel.getTimeNorthLablePanel().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
			SettingsPanel.scanPanel.getStreamingContinaer().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
			
			SettingsPanel.scanPanel.getTimeNorthPanel().setBorder(Program.LIGHT_BLUE_BORDER);
			SettingsPanel.scanPanel.getStreamingContinaer().setBorder(Program.LIGHT_BLUE_BORDER);
		}
	}
}

package emcscanner.kth.se;

public class SetPanelInactive {
	/**
	 * Sets Frequency Panel stage to NOT ACTIVE 
	 */
	public static void frequencyPanelNotActive(){
		FrequencySettingsSubPanel.DISPLAY_HELP_VIDEO = false;
		
		/* Sets the frequency that have been selected to SettingsPanels global variables */
		SettingsPanel.setFrequencyStartUserSelectedFloat(SettingsPanel.frequencyPanel.startValue);
		SettingsPanel.setFrequencyEndUserSelectedFloat(SettingsPanel.frequencyPanel.endValue);
		SettingsPanel.setFrequencyDensityUserSelectedInt(SettingsPanel.frequencyPanel.densityStartValue);
		
		/* Sets header button to enabled and green with a new tool tip */
		FrequencySettingsSubPanel.headerButton.setToolTipText(SettingsPanel.frequencyPanel.getHeaderButtonToolTipText());
		FrequencySettingsSubPanel.headerButton.setEnabled(true);
		
		/* Green Borders */
		SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.GREEN_BORDER);

		/* Sets step button, text and text input not visible when when next button has been pressed */
		SettingsPanel.frequencyPanel.startFloatInputTextField.setVisible(false);
		SettingsPanel.frequencyPanel.getStartMoreThenLabel().setVisible(false);
		SettingsPanel.frequencyPanel.getStartLessThenLabel().setVisible(false);
		FrequencySettingsSubPanel.nextButton.setVisible(false);
		
		/* Containers for setting up GUI */
		SettingsPanel.frequencyPanel.continer1.setVisible(false);
		SettingsPanel.frequencyPanel.continer2.setVisible(false);
		SettingsPanel.frequencyPanel.startTextFeildContainer.setVisible(false);
		SettingsPanel.frequencyPanel.continer4.setVisible(false);
		SettingsPanel.frequencyPanel.continer5.setVisible(false);
		SettingsPanel.frequencyPanel.continer6.setVisible(false);
		SettingsPanel.frequencyPanel.startFrequensyContainer.setVisible(false);
		FrequencySettingsSubPanel.backButton.setVisible(false);
		SettingsPanel.frequencyPanel.endFrequensyContainer.setVisible(false);
		FrequencySettingsSubPanel.colorFrequensyVideoPanel.setVisible(false);
		
		/* Sets step Label green when button has been pressed*/
		SettingsPanel.frequencyPanel.getStepLabel().setText(SettingsPanel.frequencyPanel.getStepTextDarkGreen());
		
		/* Changing size of panels when button has been pressed*/	
		SettingsPanel.frequencyPanel.frequencyPanel.setPreferredSize(SettingsPanel.frequencyPanel.FREQUENCY_PANEL_DIMENSION_OFF);
		SettingsPanel.frequencyPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.frequencyPanel.HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
		SettingsPanel.frequencyPanel.stepContiner.setPreferredSize(SettingsPanel.frequencyPanel.STEP_CONTINER_DIMENSION_OFF);
		
		/* Label that shows the frequency that the user has selected */
		SettingsPanel.frequencyPanel.getFrequencyLabel().setText("<html><font color = rgb(120,200,40)>Selected frequency: </font></html>");
		SettingsPanel.frequencyPanel.getFrequencyLabelStart().setText("<html>Start = " + SettingsPanel.getFrequencyStartUserSelectedFloat() + " MHz,</html>");
		SettingsPanel.frequencyPanel.getFrequencyLabelEnd().setText("<html>End = " + SettingsPanel.getFrequencyEndUserSelectedFloat() + " MHz,</html>");
		SettingsPanel.frequencyPanel.getFrequencyLabelDensity().setText("<html>Density = "+ SettingsPanel.getFrequencyDensityUserSelectedInt() + "</html>");
		SettingsPanel.frequencyPanel.getFrequencyLabel().setVisible(true);
	}
	/**
	 * Sets Area Panel stage to NOT ACTIVE 
	 */
	public static void areaPanelNotActive(){
		/* Don't show Glass Panel and turn inactive */
		MainFrame.setGET_AREA_BOOLEAN(false);

		/* Area help video disabled */
		AreaSettingsSubPanel.DISPLAY_HELP_VIDEO = false;
		
		/* New tool tip */
		AreaSettingsSubPanel.headerButton.setToolTipText(AreaSettingsSubPanel.headerButton.HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Sets video and buttons not visible */
		AreaSettingsSubPanel.nextButton.setVisible(false);
		AreaSettingsSubPanel.backButton.setVisible(false);
		SettingsPanel.areaPanel.areaNotSelectedLabel.setVisible(false);
		AreaSettingsSubPanel.colorAreaVideoPanel.setVisible(false);
		SettingsPanel.areaPanel.continer1.setVisible(false);
		SettingsPanel.areaPanel.areaSelectedContainer.setVisible(false);
		
		if (SettingsPanel.isAREA_SELECTED())
		{
			/* Sets step label green when button has been pressed */
			SettingsPanel.areaPanel.stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_DARK_GREEN);
			
			/* AreaPanel and header Green */
			SettingsPanel.areaPanel.areaPanel.setBorder(Program.GREEN_BORDER);
			AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.areaPanel.areaPanel.setPreferredSize(SettingsPanel.areaPanel.AREA_PANEL_DIMENSION_DONE);
			SettingsPanel.areaPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.areaPanel.HEADER_AND_PANEL_DIMENSION_DONE);
			SettingsPanel.areaPanel.stepContiner.setPreferredSize(SettingsPanel.areaPanel.STEP_CONTINER_DIMENSION_DONE);
			
			/* Sets selected area and label visible */
			SettingsPanel.areaPanel.areaResultLabel.setVisible(true);
		}
		else
		{
			/* Sets step label gray for not active */
			SettingsPanel.areaPanel.stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_GRAY);
			
			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.areaPanel.areaPanel.setPreferredSize(SettingsPanel.areaPanel.AREA_PANEL_DIMENSION_OFF);
			SettingsPanel.areaPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.areaPanel.HEADER_AND_PANEL_DIMENSION_OFF);
			SettingsPanel.areaPanel.stepContiner.setPreferredSize(SettingsPanel.areaPanel.STEP_CONTINER_DIMENSION_OFF);
			
			/* Sets selected area and label invisible */
			SettingsPanel.areaPanel.areaResultLabel.setVisible(false);
			SettingsPanel.areaPanel.areaPanel.setVisible(false);
			
			/* Sets Header button gray */
			AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_GRAY_IMAGE_ICON);
			AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
		}
	}
	/**
	 * Sets Density Panel stage to NOT ACTIVE 
	 */
	public static void densityPanelNotActive(){
		DensitySettingsSubPanel.DISPLAY_HELP_VIDEO = false;
		/* Sets header button to enabled and green with a new tool tip */
		DensitySettingsSubPanel.headerButton.setToolTipText(DensitySettingsSubPanel.headerButton.HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* For showing videos */
		DensitySettingsSubPanel.DISPLAY_HELP_VIDEO = DensitySettingsSubPanel.NOT_VISIBLE;
		
		/* Sets video and buttons not visible */
		DensitySettingsSubPanel.nextButton.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		DensitySettingsSubPanel.backButton.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		DensitySettingsSubPanel.colorDensityVideoPanel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.heightDensityInputTextField.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.widthDensityInputTextField.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.scanDensityLabel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.widthLabel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.heightLabel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.widthLabel0.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.heightLabel0.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.heightLabelValue.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.widthLabelValue.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.noteLabel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		SettingsPanel.densityPanel.getInputFeildsAButtons().setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
		
		if (SettingsPanel.isDENSITY_SELECTED())
		{
			/* Sets step label green when button has been pressed */
			SettingsPanel.densityPanel.stepLabel.setText(SettingsPanel.densityPanel.STEP_TEXT_DARK_GREEN);
			
			/* AreaPanel and header Green */
			SettingsPanel.densityPanel.densityPanel.setBorder(Program.GREEN_BORDER);
			SettingsPanel.densityPanel.densityPanel.setVisible(DensitySettingsSubPanel.VISIBLE);
			DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.densityPanel.densityPanel.setPreferredSize(SettingsPanel.densityPanel.getDENSITY_PANEL_DIMENSION_DONE());
			SettingsPanel.densityPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.densityPanel.getHEADER_AND_PANEL_CONTINER_DIMENSION_DONE());
			SettingsPanel.densityPanel.stepContiner.setPreferredSize(SettingsPanel.densityPanel.getSTEP_CONTINER_DIMENSION_DONE());
			
			SettingsPanel.densityPanel.densitySelectedLabel.setText("<html><font color = rgb(120,200,40)> Density selected:</font> Width: " + 
																	SettingsPanel.getDENSITY_SELECTED_WIDTH() + 
																	", Height: " + SettingsPanel.getDENSITY_SELECTED_HEIGHT() + "&nbsp</html>");
			SettingsPanel.densityPanel.densitySelectedLabel.setVisible(DensitySettingsSubPanel.VISIBLE);
		}
		else
		{
			/* Sets step label gray when button has been pressed */
			SettingsPanel.densityPanel.stepLabel.setText(SettingsPanel.densityPanel.STEP_TEXT_GRAY);
			
			/* Sets density panel to invisible */
			SettingsPanel.densityPanel.densityPanel.setVisible(DensitySettingsSubPanel.NOT_VISIBLE);
			
			/* Sets Header button gray */
			DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_GRAY_IMAGE_ICON);
			DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			
			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.densityPanel.densityPanel.setPreferredSize(SettingsPanel.densityPanel.getHEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF());
			SettingsPanel.densityPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.densityPanel.getHEADER_AND_PANEL_AND_DENSITY_PANEL_DIMENSION_OFF());
			SettingsPanel.densityPanel.stepContiner.setPreferredSize(SettingsPanel.densityPanel.getSTEP_CONTINER_DIMENSION_OFF());
		}
	}
	/**
	 * Sets File Name Panel stage to NOT ACTIVE 
	 */
	public static void fileNamePanelNotActive(){		
		/* Sets header button to enabled and green with a new tool tip */
		FileNameSettingsSubPanel.headerButton.setToolTipText(FileNameSettingsSubPanel.headerButton.HEADER_BUTTON_TOOL_TIP_TEXT);
			
		/* Sets video and buttons not visible */
		FileNameSettingsSubPanel.nextButton.setVisible(false);
		FileNameSettingsSubPanel.backButton.setVisible(false);
		SettingsPanel.fileNamePanel.fileNameInputTextField.setVisible(false);
		SettingsPanel.fileNamePanel.getImputFeildsContainer().setVisible(false);
		SettingsPanel.fileNamePanel.getInputFeildsAButtons().setVisible(false);
		SettingsPanel.fileNamePanel.getInputContainer().setVisible(false);
		
		if (SettingsPanel.isFILE_NAME_SELECTED())
		{
			/* Sets step label green when button has been pressed */
			SettingsPanel.fileNamePanel.stepLabel.setText(SettingsPanel.fileNamePanel.STEP_TEXT_DARK_GREEN);

			/* Sets density panel to visible */
			SettingsPanel.fileNamePanel.fileNamePanel.setVisible(true);
			
			/* AreaPanel and header Green */
			SettingsPanel.fileNamePanel.fileNamePanel.setBorder(Program.GREEN_BORDER);
			SettingsPanel.fileNamePanel.fileNamePanel.setVisible(true);
			FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.fileNamePanel.fileNamePanel.setPreferredSize(SettingsPanel.fileNamePanel.FILE_NAME_DENSITY_PANEL_DIMENSION_DONE);
			SettingsPanel.fileNamePanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.fileNamePanel.HEADER_AND_PANEL_CONTINER_DIMENSION_DONE);
			SettingsPanel.fileNamePanel.stepContiner.setPreferredSize(SettingsPanel.fileNamePanel.STEP_CONTINER_DIMENSION_DONE);
			
			SettingsPanel.fileNamePanel.fileNameSelectedLabel.setText("<html><font color = rgb(120,200,40)>Choosen file name: </font>" + 
																		SettingsPanel.fileNamePanel.fileNameInputTextField.getText() + 
																		"&nbsp</html>");
			
			SettingsPanel.fileNamePanel.fileNameSelectedLabel.setVisible(true);
		}
		else
		{
			/* Sets step label gray when button has been pressed */
			SettingsPanel.fileNamePanel.stepLabel.setText(SettingsPanel.fileNamePanel.STEP_TEXT_GRAY);
			
			/* Sets density panel to invisible */
			SettingsPanel.fileNamePanel.fileNamePanel.setVisible(false);
			
			/* Sets Header button gray */
			FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_GRAY_IMAGE_ICON);
			FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
			
			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.fileNamePanel.fileNamePanel.setPreferredSize(SettingsPanel.fileNamePanel.FILE_NAME_DENSITY_PANEL_DIMENSION_OFF);
			SettingsPanel.fileNamePanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.fileNamePanel.HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
			SettingsPanel.fileNamePanel.stepContiner.setPreferredSize(SettingsPanel.fileNamePanel.STEP_CONTINER_DIMENSION_OFF);
		}
	}
	/**
	 * Sets Scan Panel stage to NOT ACTIVE 
	 */
	public static void scanPanelNotActive(){	
		/* Sets header button to enabled and green with a new tool tip */
		ScanSettingsSubPanel.headerButton.setToolTipText(SettingsPanel.scanPanel.HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Sets video and buttons not visible */
		ScanSettingsSubPanel.startScanButton.setVisible(false);
		ScanSettingsSubPanel.pauseScanButton.setVisible(false);
		ScanSettingsSubPanel.stopScanButton.setVisible(false);
		//restartButton.setVisible(false);
		ScanSettingsSubPanel.rescanButton.setVisible(false);
		ScanSettingsSubPanel.saveButton.setVisible(false);

		if (SettingsPanel.isScanDone())
		{
			/* Sets step label green when button has been pressed */
			SettingsPanel.scanPanel.stepLabel.setText(SettingsPanel.scanPanel.STEP_TEXT_DARK_GREEN);

			/* Sets density panel to visible */
			SettingsPanel.scanPanel.scanPanel.setVisible(true);
			
			/* AreaPanel and header Green */
			SettingsPanel.scanPanel.scanPanel.setBorder(Program.GREEN_BORDER);
			SettingsPanel.scanPanel.scanPanel.setVisible(true);
			ScanSettingsSubPanel.headerButton.setEnabled(ScanSettingsSubPanel.HEADER_BUTTON_ENABLED = true);
		}
		else
		{
			/* Sets step label gray when button has been pressed */
			SettingsPanel.scanPanel.stepLabel.setText(SettingsPanel.scanPanel.STEP_TEXT_GRAY);

			/* Sets density panel to invisible */
			SettingsPanel.scanPanel.scanPanel.setVisible(false);
			
			/* Sets Header button gray */
			ScanSettingsSubPanel.headerButton.setDisabledIcon(ScanSettingsSubPanel.headerButton.SCAN_HEADER_DISABLED_GRAY_IMAGE_ICON);
			ScanSettingsSubPanel.headerButton.setEnabled(ScanSettingsSubPanel.HEADER_BUTTON_ENABLED = false);

			/* Changing size of panels when button has been pressed*/	
			SettingsPanel.scanPanel.scanPanel.setPreferredSize(SettingsPanel.scanPanel.getSCAN_PANEL_DIMENSION_OFF());
			SettingsPanel.scanPanel.headerAndPanelContiner.setPreferredSize(SettingsPanel.scanPanel.getHEADER_AND_PANEL_CONTINER_DIMENSION_OFF());
			SettingsPanel.scanPanel.stepContiner.setPreferredSize(SettingsPanel.scanPanel.getSTEP_CONTINER_DIMENSION_OFF());
		}
	}
}

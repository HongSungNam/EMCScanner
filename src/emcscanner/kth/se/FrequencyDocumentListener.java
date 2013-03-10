package emcscanner.kth.se;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FrequencyDocumentListener implements DocumentListener {
	private int listener;
	public FrequencyDocumentListener(int i){
		this.listener = i;
	}
	public void insertUpdate(DocumentEvent aEvent) {
		if (listener == 1)
			checkStartFloat();
		else if (listener == 2)
			checkEndFloat();
		else if (listener == 3)
			checkFloatDensity();
    }
    public void removeUpdate(DocumentEvent aEvente) {
		if (listener == 1)
			checkStartFloat();
		else if (listener == 2)
			checkEndFloat();
		else if (listener == 3)
			checkFloatDensity();
    }
    public void changedUpdate(DocumentEvent aEvent) {
		if (listener == 1)
			checkStartFloat();
		else if (listener == 2)
			checkEndFloat();
		else if (listener == 3)
			checkFloatDensity();
    }
    public void checkStartFloat(){
    	boolean AREA_HEADER_BUTTON_TEMP_DISABLED = false;
		boolean DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
		boolean FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
    	try
    	{
    		float value = Float.parseFloat(SettingsPanel.frequencyPanel.startFloatInputTextField
    										.getText()
    										.toLowerCase()
    										.replace(',', '.')
    										.replace('f', 'x')
    										.replace('d', 'x'));
    		SettingsPanel.frequencyPanel.startValue = value;
    		if ((0.1 <= value) && (value <= 6000))
	    	{
    			FrequencySettingsSubPanel.START_FREQUENSY_SELECTED = true;
				
    			SettingsPanel.frequencyPanel.endFloatInputTextField.setEnabled(true);
    			SettingsPanel.frequencyPanel.endFloatInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
    			SettingsPanel.frequencyPanel.endFloatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
    			SettingsPanel.frequencyPanel.getEndMoreThenLabel().setText("<html><font color = rgb(0,0,0)>" + 
    																  SettingsPanel.frequencyPanel.startValue + 
    																  " ≤</font></html>");
    			SettingsPanel.frequencyPanel.getEndLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenNormalText());
    			SettingsPanel.frequencyPanel.getEndFrequensyLabel().setText(SettingsPanel.frequencyPanel.getEndFrequencyLightBlueText());
    			
				if(SettingsPanel.frequencyPanel.startValue == SettingsPanel.frequencyPanel.endValue)
					SettingsPanel.frequencyPanel.densityIntInputTextField.setText("" + 1);
				else if(SettingsPanel.frequencyPanel.startValue > SettingsPanel.frequencyPanel.endValue)
				{
					SettingsPanel.frequencyPanel.endFloatInputTextField.setText("" + (int) SettingsPanel.frequencyPanel.startValue);
					SettingsPanel.frequencyPanel.densityIntInputTextField.setText("" + 1);
				}
				else if (SettingsPanel.frequencyPanel.startValue < SettingsPanel.frequencyPanel.endValue)
				{
					SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(0,0,0)> ≤ " + (int)(SettingsPanel.frequencyPanel.endValue - SettingsPanel.frequencyPanel.startValue + 1) + "</font></html>");
				}
				else 
					SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(0,0,0)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");

				
	    		/* Density selection  enabled */
				SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(true);
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
				SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(0,0,0)>" + 1 + 
																		  " ≤</font></html>");
				SettingsPanel.frequencyPanel.getDensityFrequensyLabel().setText(SettingsPanel.frequencyPanel.getDensityFrequencyLightBlueText());
	    		
	    		/* Sets header button enabled values */
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
				FrequencySettingsSubPanel.headerButton.setEnabled(false);
	    		
	    		/* Set borders light blue when enabled */
				SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
				SettingsPanel.frequencyPanel.startFloatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
				
				/* Changes text back to normal wrong values have been entered before */
				SettingsPanel.frequencyPanel.getStartMoreThenLabel().setText(SettingsPanel.frequencyPanel.getMoreThenNormalText());
				SettingsPanel.frequencyPanel.getStartLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenNormalText());
				
				SettingsPanel.frequencyPanel.startFloatInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
				
				
				if(AREA_HEADER_BUTTON_TEMP_DISABLED)
				{
					AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED);
					AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_ENABLED_IMAGE_ICON);
					AREA_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				if(DENSITY_HEADER_BUTTON_TEMP_DISABLED)
				{
					DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED);
					DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_ENABLED_IMAGE_ICON);
					DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				if(FILE_NAME_HEADER_BUTTON_TEMP_DISABLED)
				{
					FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED);
					FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_ENABLED_IMAGE_ICON);
					FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				
	    	}
			else
			{
				SettingsPanel.frequencyPanel.startFloatInputTextField.setBackground(Program.LIGHT_RED_COLOR);
				
				FrequencySettingsSubPanel.START_FREQUENSY_SELECTED = false;
				
				if (SettingsPanel.frequencyPanel.startValue < SettingsPanel.frequencyPanel.endValue)
					SettingsPanel.frequencyPanel.endFloatInputTextField.setText(""+(int)1);
				
				/* Turns density and end value gray and inactive */
				FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
	    		
				SettingsPanel.frequencyPanel.endFloatInputTextField.setEnabled(false);
				SettingsPanel.frequencyPanel.endFloatInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
	    		SettingsPanel.frequencyPanel.endFloatInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
	    		SettingsPanel.frequencyPanel.getEndMoreThenLabel().setText("<html><font color = rgb(110,110,110)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
	    		SettingsPanel.frequencyPanel.getEndLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenLightGrayText());
	    		SettingsPanel.frequencyPanel.getEndFrequensyLabel().setText(SettingsPanel.frequencyPanel.getEndFrequencyLightGrayText());
				
	    		SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(false);
	    		SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
	    		SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
	    		SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(110,110,110)>" + 1 + " ≤</font></html>");
	    		SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(110,110,110)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
	    		SettingsPanel.frequencyPanel.getDensityFrequensyLabel().setText(SettingsPanel.frequencyPanel.getDensityFrequencyLightGrayText());
    			
				/* Sets next button disabled values have been entered */
	    		FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
				
				/* Sets header button disabled values have been entered */
	    		FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
	    		FrequencySettingsSubPanel.headerButton.setEnabled(false);
				
				/* Set borders red when wrong values have been entered */
	    		SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.RED_BORDER);
	    		SettingsPanel.frequencyPanel.startFloatInputTextField.setBorder(Program.RED_BORDER);
				
				/* wrong values have been entered changes the text so user knows what they have entered wrongly */
				if (0.1 > value)
		    	{
					SettingsPanel.frequencyPanel.getStartMoreThenLabel().setText(SettingsPanel.frequencyPanel.getMoreThenRedText());
		    	}
				if (6000 < value)
		    	{
					SettingsPanel.frequencyPanel.getStartLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenRedText());
		    	}
				
				if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					AREA_HEADER_BUTTON_TEMP_DISABLED = true;
				}
				if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
				}
				if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
				}
			}
    	} 
		catch (NumberFormatException e) {
			SettingsPanel.frequencyPanel.startFloatInputTextField.setBackground(Program.LIGHT_RED_COLOR);

			FrequencySettingsSubPanel.START_FREQUENSY_SELECTED = false;
			
			/* Turns density and end value gray and inactive */
			SettingsPanel.frequencyPanel.endFloatInputTextField.setEnabled(false);
			SettingsPanel.frequencyPanel.endFloatInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
			SettingsPanel.frequencyPanel.endFloatInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
			SettingsPanel.frequencyPanel.getEndMoreThenLabel().setText("<html><font color = rgb(110,110,110)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
			SettingsPanel.frequencyPanel.getEndLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenLightGrayText());
			SettingsPanel.frequencyPanel.getEndFrequensyLabel().setText(SettingsPanel.frequencyPanel.getEndFrequencyLightGrayText());
			
			SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(false);
			SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
			SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
			SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(110,110,110)>" + 1 + " ≤</font></html>");
			SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(110,110,110)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
			SettingsPanel.frequencyPanel.getDensityFrequensyLabel().setText(SettingsPanel.frequencyPanel.getDensityFrequencyLightGrayText());
			
    		/* Sets next button disabled when wrong values have been entered */
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
			
			/* Sets header button disabled values have been entered */
			FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
			FrequencySettingsSubPanel.headerButton.setEnabled(false);
			
			/* Set borders red when wrong values have been entered */
			SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.RED_BORDER);
			SettingsPanel.frequencyPanel.startFloatInputTextField.setBorder(Program.RED_BORDER);
			
			if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				AreaSettingsSubPanel.headerButton.setEnabled(false);
				AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				AREA_HEADER_BUTTON_TEMP_DISABLED = true;
			}
			if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				DensitySettingsSubPanel.headerButton.setEnabled(false);
				DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
			}
			if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
				FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
			}
    	}
		
		if (FrequencySettingsSubPanel.START_FREQUENSY_SELECTED && FrequencySettingsSubPanel.DENSITY_FREQUENSY_SELECTED && FrequencySettingsSubPanel.END_FREQUENCY_SELECTED)
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED= true);
		else
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED= false);
    }
    
    public void checkEndFloat(){
    	boolean AREA_HEADER_BUTTON_TEMP_DISABLED = false;
		boolean DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
		boolean FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
    	try
    	{
    		float value = Float.parseFloat(SettingsPanel.frequencyPanel.endFloatInputTextField.getText().toLowerCase()
    																	   								.replace(',', '.')
    																	   								.replace('f', 'x')
    																	   								.replace('d', 'x'));
    		SettingsPanel.frequencyPanel.endValue = value;
    		if ((SettingsPanel.frequencyPanel.startValue <= value) && (value <= 6000))
	    	{
    			SettingsPanel.frequencyPanel.endFloatInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
    			FrequencySettingsSubPanel.END_FREQUENCY_SELECTED = true;
				
    			SettingsPanel.frequencyPanel.densityEndValue = (int)((SettingsPanel.frequencyPanel.endValue-SettingsPanel.frequencyPanel.startValue) * 10 + 1.5);
	    		
				if (SettingsPanel.frequencyPanel.endValue < SettingsPanel.frequencyPanel.densityValue)
					SettingsPanel.frequencyPanel.densityIntInputTextField.setText(""+(int)1);
    			
	    		/* Density selection  enabled */
				SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(true);
				
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
				SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(0,0,0)>" + 1 + " ≤</font></html>");
				SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(0,0,0)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
				SettingsPanel.frequencyPanel.getDensityFrequensyLabel().setText(SettingsPanel.frequencyPanel.getDensityFrequencyLightBlueText());
    			
    			/* Changes text back to normal wrong values have been entered before */
				SettingsPanel.frequencyPanel.getEndMoreThenLabel().setText("<html><font color = rgb(0,0,0)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
				SettingsPanel.frequencyPanel.getEndLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenNormalText());
    			
	    		/* Sets header button enabled values */
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
				FrequencySettingsSubPanel.headerButton.setEnabled(false);
	    		
	    		/* Set borders light blue when enabled */
				SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
				SettingsPanel.frequencyPanel.endFloatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
				
				if(AREA_HEADER_BUTTON_TEMP_DISABLED)
				{
					AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED);
					AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_ENABLED_IMAGE_ICON);
					AREA_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				if(DENSITY_HEADER_BUTTON_TEMP_DISABLED)
				{
					DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED);
					DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_ENABLED_IMAGE_ICON);
					DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				if(FILE_NAME_HEADER_BUTTON_TEMP_DISABLED)
				{
					FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED);
					FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_ENABLED_IMAGE_ICON);
					FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
				}
	    	}
			else
			{
				SettingsPanel.frequencyPanel.endFloatInputTextField.setBackground(Program.LIGHT_RED_COLOR);
				FrequencySettingsSubPanel.END_FREQUENCY_SELECTED = false;
    			
				if (SettingsPanel.frequencyPanel.endValue < SettingsPanel.frequencyPanel.densityValue)
					SettingsPanel.frequencyPanel.densityIntInputTextField.setText(""+(int)1);
				
				/* Sets next button disabled values have been entered */
				SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(false);
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
				SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(110,110,110)>" + 1 + 
																		  " ≤</font></html>");
				SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(110,110,110)> ≤ " + 
																		  SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
				SettingsPanel.frequencyPanel.getDensityFrequensyLabel().setText(SettingsPanel.frequencyPanel.getDensityFrequencyLightGrayText());
				
				/* Sets header button disabled values have been entered */
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
				FrequencySettingsSubPanel.headerButton.setEnabled(false);
				
				/* Set borders red when wrong values have been entered */
				SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.RED_BORDER);
				SettingsPanel.frequencyPanel.endFloatInputTextField.setBorder(Program.RED_BORDER);
				
				/* wrong values have been entered changes the text so user knows what they have entered wrongly */
				if (SettingsPanel.frequencyPanel.startValue > value)
		    	{
					SettingsPanel.frequencyPanel.getEndMoreThenLabel().setText("<html><font color = rgb(255,0,0)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
		    	}
				if (6000 < value)
		    	{
					SettingsPanel.frequencyPanel.getEndLessThenLabel().setText(SettingsPanel.frequencyPanel.getLessThenRedText());
		    	}
				
				if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					AREA_HEADER_BUTTON_TEMP_DISABLED = true;
				}
				if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
				}
				if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
				}
			}
    	} 
		catch (NumberFormatException e) {
			FrequencySettingsSubPanel.END_FREQUENCY_SELECTED = false;
			SettingsPanel.frequencyPanel.endFloatInputTextField.setBackground(Program.LIGHT_RED_COLOR);
			
    		/* Sets next button disabled when wrong values have been entered */
			SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(false);
			SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
			/* Changes text back to normal wrong values have been entered before */
			SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
			SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(110,110,110)>" + 1 + 
																	  " ≤</font></html>");
			SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(110,110,110)> ≤ " + 
																	  SettingsPanel.frequencyPanel.densityEndValue + 
																	  "</font></html>");
			SettingsPanel.frequencyPanel.getDensityFrequensyLabel().setText(SettingsPanel.frequencyPanel.getDensityFrequencyLightGrayText());
			
			/* Sets header button disabled values have been entered */
			FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
			FrequencySettingsSubPanel.headerButton.setEnabled(false);
			
			/* Set borders red when wrong values have been entered */
			SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.RED_BORDER);
			SettingsPanel.frequencyPanel.endFloatInputTextField.setBorder(Program.RED_BORDER);
			
			if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				AreaSettingsSubPanel.headerButton.setEnabled(false);
				AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				AREA_HEADER_BUTTON_TEMP_DISABLED = true;
			}
			if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				DensitySettingsSubPanel.headerButton.setEnabled(false);
				DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
			}
			if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
				FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
			}
    	}
		if (FrequencySettingsSubPanel.START_FREQUENSY_SELECTED && FrequencySettingsSubPanel.DENSITY_FREQUENSY_SELECTED && FrequencySettingsSubPanel.END_FREQUENCY_SELECTED)
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = true);
		else
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
    }
    
    public void checkFloatDensity(){
    	boolean AREA_HEADER_BUTTON_TEMP_DISABLED = false;
		boolean DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
		boolean FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
    	try
    	{
    		SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
    		int value = Integer.valueOf(SettingsPanel.frequencyPanel.densityIntInputTextField.getText());
    		SettingsPanel.frequencyPanel.densityValue = value;
    		if ((1 <= value) && (value <= SettingsPanel.frequencyPanel.densityEndValue))
	    	{
    			FrequencySettingsSubPanel.DENSITY_FREQUENSY_SELECTED = true;
    			
				/* Sets next button enabled values */
    			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = true);
	    		
	    		/* Sets header button enabled values */
    			FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
    			FrequencySettingsSubPanel.headerButton.setEnabled(false);
	    		
	    		/* Set borders light blue when enabled */
    			SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
    			SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
				
				/* Changes text back to normal wrong values have been entered before */
    			SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(0,0,0)>" + 1 + 
    																	  " ≤</font></html>");
    			SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(0,0,0)> ≤ " + 
    																	  SettingsPanel.frequencyPanel.densityEndValue + 
    																	  "</font></html>");
				
				if(AREA_HEADER_BUTTON_TEMP_DISABLED)
				{
					AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED);
					AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_ENABLED_IMAGE_ICON);
					AREA_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				if(DENSITY_HEADER_BUTTON_TEMP_DISABLED)
				{
					DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED);
					DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_ENABLED_IMAGE_ICON);
					DENSITY_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				if(FILE_NAME_HEADER_BUTTON_TEMP_DISABLED)
				{
					FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = true;
					FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED);
					FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_ENABLED_IMAGE_ICON);
					FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = false;
				}
				
	    	}
			else
			{
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_RED_COLOR);
				FrequencySettingsSubPanel.DENSITY_FREQUENSY_SELECTED = false;
				/* Sets next button disabled values have been entered */
				FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
				
				/* Sets header button disabled values have been entered */
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
				FrequencySettingsSubPanel.headerButton.setEnabled(false);
				
				/* Set borders red when wrong values have been entered */
				SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.RED_BORDER);
				SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.RED_BORDER);
				
				/* wrong values have been entered changes the text so user knows what they have entered wrongly */
				if (1 > value)
		    	{
					SettingsPanel.frequencyPanel.getDensityMoreThenLabel().setText("<html><font color = rgb(255,0,0)>" + 1 + 
																			  " ≤</font></html>");
		    	}
				if (SettingsPanel.frequencyPanel.densityEndValue < value)
		    	{
					SettingsPanel.frequencyPanel.getDensityLessThenLabel().setText("<html><font color = rgb(255,0,0)> ≤ " + 
																			  SettingsPanel.frequencyPanel.densityEndValue + 
																			  "</font></html>");
		    	}
				
				if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					AreaSettingsSubPanel.headerButton.setEnabled(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					AREA_HEADER_BUTTON_TEMP_DISABLED = true;
				}
				if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					DensitySettingsSubPanel.headerButton.setEnabled(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
				}
				if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
				{
					FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
					FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
					FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
				}
			}
    	} 
		catch (NumberFormatException e) {
			SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_RED_COLOR);
			FrequencySettingsSubPanel.DENSITY_FREQUENSY_SELECTED = false;		    		
    		/* Sets next button disabled when wrong values have been entered */
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
			
			/* Sets header button disabled values have been entered */
			FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_RED_IMAGE_ICON);
			FrequencySettingsSubPanel.headerButton.setEnabled(false);
			
			/* Set borders red when wrong values have been entered */
			SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.RED_BORDER);
			SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.RED_BORDER);
			
			if(AreaSettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				AreaSettingsSubPanel.headerButton.setEnabled(false);
				AreaSettingsSubPanel.headerButton.setDisabledIcon(AreaSettingsSubPanel.headerButton.AREA_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				AREA_HEADER_BUTTON_TEMP_DISABLED = true;
			}
			if(DensitySettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				DensitySettingsSubPanel.headerButton.setEnabled(false);
				DensitySettingsSubPanel.headerButton.setDisabledIcon(DensitySettingsSubPanel.headerButton.DENSITY_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				DENSITY_HEADER_BUTTON_TEMP_DISABLED = true;
			}
			if(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED)
			{
				FileNameSettingsSubPanel.headerButton.setEnabled(FileNameSettingsSubPanel.HEADER_BUTTON_ENABLED = false);
				FileNameSettingsSubPanel.headerButton.setDisabledIcon(FileNameSettingsSubPanel.headerButton.FILE_NAME_HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				FILE_NAME_HEADER_BUTTON_TEMP_DISABLED = true;
			}
    	}
    	if (FrequencySettingsSubPanel.START_FREQUENSY_SELECTED && FrequencySettingsSubPanel.DENSITY_FREQUENSY_SELECTED && FrequencySettingsSubPanel.END_FREQUENCY_SELECTED)
    		FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = true);
		else
			FrequencySettingsSubPanel.nextButton.setEnabled(FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED = false);
    }
}

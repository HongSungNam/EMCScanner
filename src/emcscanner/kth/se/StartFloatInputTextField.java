package emcscanner.kth.se;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StartFloatInputTextField extends JTextField{
	
	public StartFloatInputTextField() {
		/* Text field for importing frequency from user only values from 0.1 to 6000 */
		this.setPreferredSize(FLOAT_INPUT_TEXT_FEILD_DIMENSION);
		this.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		this.setBorder(Program.LIGHT_BLUE_BORDER);
		this.setBackground(Program.LIGHT_BLUE_COLOR2);
		
		
		startFloatInputTextField.getDocument().addDocumentListener(new DocumentListener () {
			public void insertUpdate(DocumentEvent aEvent) {
				checkFloat();
		    }
		    public void removeUpdate(DocumentEvent aEvente) {
		    	checkFloat();
		    }
		    public void changedUpdate(DocumentEvent aEvent) {
		    	checkFloat();
		    }
		    public void checkFloat(){
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
		    			SettingsPanel.frequencyPanel.endMoreThenLabel.setText("<html><font color = rgb(0,0,0)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
		    			SettingsPanel.frequencyPanel.endLessThenLabel.setText(SettingsPanel.frequencyPanel.LESS_THEN_NORMAL_TEXT);
		    			SettingsPanel.frequencyPanel.endFrequensyLabel.setText(SettingsPanel.frequencyPanel.END_FREQUENSY_LIGHT_BLUE_TEXT);
		    			
						if(SettingsPanel.frequencyPanel.startValue == SettingsPanel.frequencyPanel.endValue)
							SettingsPanel.frequencyPanel.densityIntInputTextField.setText(""+1);
						else if(SettingsPanel.frequencyPanel.startValue > SettingsPanel.frequencyPanel.endValue)
						{
							SettingsPanel.frequencyPanel.endFloatInputTextField.setText(""+(int)SettingsPanel.frequencyPanel.startValue);
							SettingsPanel.frequencyPanel.densityIntInputTextField.setText(""+1);
						}
						else if (SettingsPanel.frequencyPanel.startValue < SettingsPanel.frequencyPanel.endValue)
						{
							SettingsPanel.frequencyPanel.densityLessThenLabel.setText("<html><font color = rgb(0,0,0)> ≤ " + (int)(SettingsPanel.frequencyPanel.endValue - SettingsPanel.frequencyPanel.startValue + 1) + "</font></html>");
						}
						else 
							SettingsPanel.frequencyPanel.densityLessThenLabel.setText("<html><font color = rgb(0,0,0)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
	
						
			    		/* Density selection  enabled */
						SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(true);
						SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
						SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
						SettingsPanel.frequencyPanel.densityMoreThenLabel.setText("<html><font color = rgb(0,0,0)>" + 1 + " ≤</font></html>");
						SettingsPanel.frequencyPanel.densityFrequensyLabel.setText(SettingsPanel.frequencyPanel.DENSITY_FREQUENSY_LIGHT_BLUE_TEXT);
			    		
			    		/* Sets header button enabled values */
						FrequencySettingsSubPanel.headerButton.setDisabledIcon(FrequencySettingsSubPanel.headerButton.FREQUENCY_HEADER_DISABLED_BLUE_IMAGE_ICON);
						FrequencySettingsSubPanel.headerButton.setEnabled(false);
			    		
			    		/* Set borders light blue when enabled */
						SettingsPanel.frequencyPanel.frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
						SettingsPanel.frequencyPanel.startFloatInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
						
						/* Changes text back to normal wrong values have been entered before */
						SettingsPanel.frequencyPanel.startMoreThenLabel.setText(SettingsPanel.frequencyPanel.MORE_THEN_NORMAL_TEXT);
						SettingsPanel.frequencyPanel.startLessThenLabel.setText(SettingsPanel.frequencyPanel.LESS_THEN_NORMAL_TEXT);
						
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
			    		SettingsPanel.frequencyPanel.endMoreThenLabel.setText("<html><font color = rgb(110,110,110)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
			    		SettingsPanel.frequencyPanel.endLessThenLabel.setText(SettingsPanel.frequencyPanel.LESS_THEN_LIGHT_GRAY_TEXT);
			    		SettingsPanel.frequencyPanel.endFrequensyLabel.setText(SettingsPanel.frequencyPanel.END_FREQUENSY_LIGHT_GRAY_TEXT);
						
			    		SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(false);
			    		SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
			    		SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
			    		SettingsPanel.frequencyPanel.densityMoreThenLabel.setText("<html><font color = rgb(110,110,110)>" + 1 + " ≤</font></html>");
			    		SettingsPanel.frequencyPanel.densityLessThenLabel.setText("<html><font color = rgb(110,110,110)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
			    		SettingsPanel.frequencyPanel.densityFrequensyLabel.setText(SettingsPanel.frequencyPanel.DENSITY_FREQUENSY_LIGHT_GRAY_TEXT);
		    			
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
							SettingsPanel.frequencyPanel.startMoreThenLabel.setText(SettingsPanel.frequencyPanel.MORE_THEN_RED_TEXT);
				    	}
						if (6000 < value)
				    	{
							SettingsPanel.frequencyPanel.startLessThenLabel.setText(SettingsPanel.frequencyPanel.LESS_THEN_RED_TEXT);
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
					SettingsPanel.frequencyPanel.endMoreThenLabel.setText("<html><font color = rgb(110,110,110)>" + SettingsPanel.frequencyPanel.startValue + " ≤</font></html>");
					SettingsPanel.frequencyPanel.endLessThenLabel.setText(SettingsPanel.frequencyPanel.LESS_THEN_LIGHT_GRAY_TEXT);
					SettingsPanel.frequencyPanel.endFrequensyLabel.setText(SettingsPanel.frequencyPanel.END_FREQUENSY_LIGHT_GRAY_TEXT);
					
					SettingsPanel.frequencyPanel.densityIntInputTextField.setEnabled(false);
					SettingsPanel.frequencyPanel.densityIntInputTextField.setBorder(Program.LIGHT_GRAY_BORDER);
					SettingsPanel.frequencyPanel.densityIntInputTextField.setBackground(Program.LIGHT_GRAY_COLOR2);
					SettingsPanel.frequencyPanel.densityMoreThenLabel.setText("<html><font color = rgb(110,110,110)>" + 1 + " ≤</font></html>");
					SettingsPanel.frequencyPanel.densityLessThenLabel.setText("<html><font color = rgb(110,110,110)> ≤ " + SettingsPanel.frequencyPanel.densityEndValue + "</font></html>");
					SettingsPanel.frequencyPanel.densityFrequensyLabel.setText(SettingsPanel.frequencyPanel.DENSITY_FREQUENSY_LIGHT_GRAY_TEXT);
	    			
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
		});
	}
}

package emcscanner.kth.se;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DensitInputDocumentListener implements DocumentListener {
	int type;
	public DensitInputDocumentListener(int type){
		this.type = type;
	}
	public void insertUpdate(DocumentEvent aEvent) {
		if (type == 1)
			checkInt();
		else
			checkInt2();
    }
    public void removeUpdate(DocumentEvent aEvente) {
    	if (type == 1)
			checkInt();
		else
			checkInt2();
    }
    public void changedUpdate(DocumentEvent aEvent) {
    	if (type == 1)
			checkInt();
		else
			checkInt2();
    }
    public void checkInt()
    {
    	try
    	{
    		int value = Integer.valueOf(SettingsPanel.densityPanel.widthDensityInputTextField.getText());
    		
			if (value > 0 && value <= ((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    		{
				SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH = value - 1;
				SettingsPanel.densityPanel.widthLabel.setText("<html><font color = rgb(100,150,255)> Width: </font></html>");
				SettingsPanel.densityPanel.widthLabelValue.setText("<html>" + 
																   (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * 
																		   		Program.TIONDELS_MILLI_METER_PIXEL) + 
																   " &gt&nbsp</html>");
				SettingsPanel.densityPanel.widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
				
				SettingsPanel.densityPanel.widthDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);
				SettingsPanel.densityPanel.widthDensityInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
				DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY = true;
				if (DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY && DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY)
					DensitySettingsSubPanel.nextButton.setEnabled(true);
				
				Program.frame.glass.repaint();
    		}
    		else
    		{						
    			if (value > ((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    			{
    				SettingsPanel.densityPanel.widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
    				SettingsPanel.densityPanel.widthLabelValue.setText("<html><font color = rgb(255,0,0)>" + 
    																  (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * 
    																  			Program.TIONDELS_MILLI_METER_PIXEL) + 
    																  " &gt&nbsp</font></html>");
    				SettingsPanel.densityPanel.widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
    			}
				else if (value <= 0)
				{
					SettingsPanel.densityPanel.widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
					SettingsPanel.densityPanel.widthLabelValue.setText("<html>" + 
										   (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * 
												   Program.TIONDELS_MILLI_METER_PIXEL)  + 
										   " &gt&nbsp</html>");
					SettingsPanel.densityPanel.widthLabel0.setText("<html><font color = rgb(255,0,0)> &nbsp&gt 0&nbsp</font></html>");
				}
    			SettingsPanel.densityPanel.widthDensityInputTextField.setBorder(Program.RED_BORDER);
    			SettingsPanel.densityPanel.widthDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
    			DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY = false;
    			DensitySettingsSubPanel.nextButton.setEnabled(false);
    			SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH = 0;
				Program.frame.glass.repaint();
    		}
    	} 
		catch (NumberFormatException e) {
			SettingsPanel.densityPanel.widthLabel.setText("<html> <font color = rgb(255,0,0)> Width: </font></html>");
			SettingsPanel.densityPanel.widthLabelValue.setText("<html>" + 
								   (int)((SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X() + 1) * 
										   Program.TIONDELS_MILLI_METER_PIXEL) + 
									" &gt&nbsp</html>");
			SettingsPanel.densityPanel.widthLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
			
			SettingsPanel.densityPanel.widthDensityInputTextField.setBorder(Program.RED_BORDER);
			SettingsPanel.densityPanel.widthDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
			DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY = false;
			DensitySettingsSubPanel.nextButton.setEnabled(false);

			SettingsPanel.densityPanel.NUMBER_OF_LINES_WIDTH = 0;
			Program.frame.glass.repaint();
    	}
    }
    public void checkInt2()
    {
    	try
    	{
    		int value = Integer.valueOf(SettingsPanel.densityPanel.heightDensityInputTextField.getText());
    		if (value > 0 && value <= ((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    		{
    			SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT = value - 1;
				
    			SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(100,150,255)> Height: </html> </font>");
    			SettingsPanel.densityPanel.heightLabelValue.setText("<html>" + 
										(int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
												Program.TIONDELS_MILLI_METER_PIXEL) + 
										" &gt&nbsp</html>");
    			SettingsPanel.densityPanel.heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");

    			SettingsPanel.densityPanel. heightDensityInputTextField.setBackground(Program.LIGHT_BLUE_COLOR2);
    			SettingsPanel.densityPanel.heightDensityInputTextField.setBorder(Program.LIGHT_BLUE_BORDER);

    			DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY = true;
				if (DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY && DensitySettingsSubPanel.WIDTH_ENTERD_CORRECTLY)
					DensitySettingsSubPanel.nextButton.setEnabled(true);

				Program.frame.glass.repaint();
    		}
    		else
    		{
    			if (value > ((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * Program.TIONDELS_MILLI_METER_PIXEL))
    			{
    				SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
    				SettingsPanel.densityPanel.heightLabelValue.setText("<html> <font color = rgb(255,0,0)>" + 
    																   (int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
    																		   Program.TIONDELS_MILLI_METER_PIXEL) + 
    																	" &gt&nbsp</font></html>");
    				SettingsPanel.densityPanel.heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
    			}
    			else if (value <= 0)
    			{
    				SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
    				SettingsPanel.densityPanel.heightLabelValue.setText("<html>" + 
    																	(int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
    																			Program.TIONDELS_MILLI_METER_PIXEL) + 
    																	" &gt&nbsp</html>");
    				SettingsPanel.densityPanel.heightLabel0.setText("<html><font color = rgb(255,0,0)> &nbsp&gt 0&nbsp</font></html>");
    			}
    			SettingsPanel.densityPanel.heightDensityInputTextField.setBorder(Program.RED_BORDER);
    			SettingsPanel.densityPanel.heightDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
    			DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY = false;
    			DensitySettingsSubPanel.nextButton.setEnabled(false);
    			SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT = 0;

				Program.frame.glass.repaint();
    		}
    	} 
		catch (NumberFormatException e) {
			SettingsPanel.densityPanel.heightLabel.setText("<html> <font color = rgb(255,0,0)> Height: </html> </font>");
			SettingsPanel.densityPanel.heightLabelValue.setText("<html>" + 
															   (int)((SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y() + 1) * 
																	   Program.TIONDELS_MILLI_METER_PIXEL) + 
																" &gt&nbsp</html>");
			SettingsPanel.densityPanel.heightLabel0.setText("<html> &nbsp&gt 0&nbsp</html>");
			
			SettingsPanel.densityPanel.heightDensityInputTextField.setBorder(Program.RED_BORDER);
			SettingsPanel.densityPanel. heightDensityInputTextField.setBackground(Program.LIGHT_RED_COLOR);
			DensitySettingsSubPanel.HEIGHT_ENTERD_CORRECTLY = false;
			DensitySettingsSubPanel.nextButton.setEnabled(false);
			SettingsPanel.densityPanel.NUMBER_OF_LINES_HEIGHT = 0;

			Program.frame.glass.repaint();
    	}
    }
}

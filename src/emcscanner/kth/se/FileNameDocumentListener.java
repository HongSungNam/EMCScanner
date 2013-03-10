package emcscanner.kth.se;

import javax.swing.event.DocumentEvent ;
import javax.swing.event.DocumentListener;

public class FileNameDocumentListener implements DocumentListener {
	public void insertUpdate(DocumentEvent aEvent) {
		checkInt();
    }
    public void removeUpdate(DocumentEvent aEvente) {
    	checkInt();
    }
    public void changedUpdate(DocumentEvent aEvent) {
    	checkInt();
    }
    public void checkInt()        
    {
    	String name = SettingsPanel.fileNamePanel.fileNameInputTextField.getText();
    	if (name.isEmpty())
    	{
    		SettingsPanel.setFILE_NAME_SELECTED(false);
    		FileNameSettingsSubPanel.nextButton.setEnabled(false);
    	}
    	else
    	{
    		FileNameSettingsSubPanel.nextButton.setEnabled(true);

    		SettingsPanel.setFILE_NAME(name);
    	}
    }
}

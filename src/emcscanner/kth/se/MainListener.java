package emcscanner.kth.se;

public class MainListener {
	public MainListener() {
	    Program.cameraPanel.addMouseListener(new CameraPanelMouseListener());
	    Program.cameraPanel.addMouseMotionListener(new CameraPanelMouseMotionAdapter());
	    
	    FrequencySettingsSubPanel.backButton.addActionListener(new BackActionListener());
	    AreaSettingsSubPanel.backButton.addActionListener(new BackActionListener());
	    DensitySettingsSubPanel.backButton.addActionListener(new BackActionListener());
	    FileNameSettingsSubPanel.backButton.addActionListener(new BackActionListener());
	    ScanSettingsSubPanel.stopScanButton.addActionListener(new BackActionListener());
	    MainFrame.backMenuItem.addActionListener(new BackActionListener());

		StartControlPanel.getNextButton().addActionListener(new NextActionListener());
		ScanSettingsSubPanel.startScanButton.addActionListener(new NextActionListener());
		MainFrame.nextMenuItem.addActionListener(new NextActionListener());
		FrequencySettingsSubPanel.nextButton.addActionListener(new NextActionListener());
		FileNameSettingsSubPanel.nextButton.addActionListener(new NextActionListener());
		DensitySettingsSubPanel.nextButton.addActionListener(new NextActionListener());
		AreaSettingsSubPanel.nextButton.addActionListener(new NextActionListener());
		
		MainFrame.getQuitMenuItem().addActionListener(new QuitActionListener());
		
		ScanSettingsSubPanel.headerButton.addActionListener(new HeaderButtonActionListener(ScanSettingsSubPanel.STAGE));
		FrequencySettingsSubPanel.headerButton.addActionListener(new HeaderButtonActionListener(FrequencySettingsSubPanel.STAGE));
		FileNameSettingsSubPanel.headerButton.addActionListener(new HeaderButtonActionListener(FileNameSettingsSubPanel.STAGE));
		DensitySettingsSubPanel.headerButton.addActionListener(new HeaderButtonActionListener(DensitySettingsSubPanel.STAGE));
		AreaSettingsSubPanel.headerButton.addActionListener(new HeaderButtonActionListener(AreaSettingsSubPanel.STAGE));
		
		EndSubSettingsPanel.backButton.addActionListener(new MainBackActionListener());
		
		SettingsPanel.frequencyPanel.startFloatInputTextField.getDocument().addDocumentListener(new FrequencyDocumentListener(1));
		SettingsPanel.frequencyPanel.endFloatInputTextField.getDocument().addDocumentListener(new FrequencyDocumentListener(2));
		SettingsPanel.frequencyPanel.densityIntInputTextField.getDocument().addDocumentListener(new FrequencyDocumentListener(3));
		
		DensitySettingsSubPanel.densityMillimeter.addActionListener(new DensityActionListener(1));
		DensitySettingsSubPanel.densityNumberOfSteps.addActionListener(new DensityActionListener(2));
		
		SettingsPanel.densityPanel.widthDensityInputTextField.getDocument().addDocumentListener(new DensitInputDocumentListener(1));
		SettingsPanel.densityPanel.heightDensityInputTextField.getDocument().addDocumentListener(new DensitInputDocumentListener(2));
		
		SettingsPanel.fileNamePanel.fileNameInputTextField.getDocument().addDocumentListener(new FileNameDocumentListener());
        char[] r = {'?', '\\', '/', '*', '<',':', '>','"','|','_', '-'};
        SettingsPanel.fileNamePanel.fileNameInputTextField.addKeyListener(new InvalidCharListener(r));
        
        ScanSettingsSubPanel.pauseScanButton.addActionListener(new PauseScanActionListener());
        ScanSettingsSubPanel.rescanButton.addActionListener(new RescanActionListener());
        ScanSettingsSubPanel.saveButton.addActionListener(new SaveActionListener());
        

		EndSubSettingsPanel.quitButton.addActionListener(new QuitActionListener());
        
	}
}

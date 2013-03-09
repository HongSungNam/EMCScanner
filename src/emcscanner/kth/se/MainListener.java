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
	}
}

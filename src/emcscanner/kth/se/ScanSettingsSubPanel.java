package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScanSettingsSubPanel extends JPanel {

	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner 									= new JPanel();
	public JPanel headerAndPanelContiner 						= new JPanel();
	public JPanel scanPanel 									= new JPanel();
	public JPanel continer1 									= new JPanel();
	
	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 				= new ImageIcon("image/PanelGreenScan.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 		= new ImageIcon("image/PanelGreenScanRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 			= new ImageIcon("image/PanelGrayScan.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 			= new ImageIcon("image/PanelGreenScanPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 			= new ImageIcon("image/PanelBlueScan.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 		= new ImageIcon("image/PanelDarkGreenScan.png"); 
	
	public ImageIcon START_SCAN_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/ButtonStartScan.png");
	public ImageIcon START_SCAN_ENABLED_PREST_IMAGE_ICON 	 	= new ImageIcon("image/ButtonStartScanPrest.png");
	public ImageIcon START_SCAN_DISABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonScanStarted.png");
	
	public ImageIcon PAUSED_SCAN_ENABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonPauseScan.png");
	public ImageIcon PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/ButtonPauseScanPrest.png");
	public ImageIcon PAUSED_SCAN_DISABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonPauseScanNotEnabled.png"); 
	
	public ImageIcon STOP_SCAN_ENABLED_IMAGE_ICON 				= new ImageIcon("image/ButtonStopScan.png");
	public ImageIcon STOP_SCAN_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/ButtonStopScanPrest.png");
	public ImageIcon STOP_SCAN_DISABLED_IMAGE_ICON 				= new ImageIcon("image/ButtonStopScanNotEnabled.png");
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 					= "Pres to scan ";
	public String PANEL_TOOL_TIP_TEXT 							= "This is where you start the scan.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 					= "Just start the scan";
	
	public String STEP_TEXT_GRAY	 							= "<html> <font color = rgb(120,120,120)>Scan</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  						= "<html> <font color = rgb(100,150,255)>Scan</font></html>";
	public String STEP_TEXT_DARK_GREEN  						= "<html> <font color = rgb(120,200,40)>Scan</font></html>";

	/* Boolean */
	public static boolean HEADER_BUTTON_ENABLED 				= false;
	
	/* Buttons */
	public static JButton headerButton 							= new JButton();
	public static JButton startScanButton						= new JButton();
	public static JButton stopScanButton 						= new JButton();
	public static JButton pauseScanButton 						= new JButton();
	
	/* Labels */
	public JLabel stepLabel 									= new JLabel(STEP_TEXT_GRAY);
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION 					= new Dimension(400, 100);
	public Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 140);
	public Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	
	public Dimension SCAN_PANEL_DIMENSION_ACTIVE 				= new Dimension(322, 100);
	public Dimension SCAN_DENSITY_PANEL_DIMENSION_DONE 			= new Dimension(322, 40);
	public Dimension SCAN_PANEL_DIMENSION_OFF 					= new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 140);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF 	= new Dimension(322, 40);

	public ScanSettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);

		/* Sets creation values for the header button */
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setPreferredSize(HEADER_BUTTON_DIMENSION);
		headerButton.setToolTipText(PANEL_TOOL_TIP_TEXT);
		headerButton.setOpaque(false);
		headerButton.setContentAreaFilled(false);
		headerButton.setBorderPainted(false);
		headerButton.setIcon(HEADER_ENABLED_IMAGE_ICON);
		headerButton.setDisabledIcon(HEADER_DISABLED_GRAY_IMAGE_ICON);
		headerButton.setPressedIcon(HEADER_ENABLED_PREST_IMAGE_ICON);
		headerButton.setRolloverIcon(HEADER_ENABLED_ROLLOVER_IMAGE_ICON);
		headerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FrequensySettingsSubPanel.NEXT_BUTTON_ENABLED)
				{
					SettingsPanel.areaPanel.areaNotPanelActive();
					SettingsPanel.densityPanel.densityPanelNotActive();
					SettingsPanel.frequencyPanel.frequencyPanelNotActive();
					SettingsPanel.fileNamePanel.fileNamePanelActive();
					SettingsPanel.scanPanel.scanPanelActive();
				}
			}
		});
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepLabel.setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.setLayout(new BorderLayout());
		stepContiner.add(stepLabel, BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		this.add(stepContiner, BorderLayout.WEST);

		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.setLayout(new BorderLayout());
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);

		/* Panel for the frequency input */
		scanPanel.setLayout(new BorderLayout());
		scanPanel.setPreferredSize(SCAN_PANEL_DIMENSION_OFF);
		
		/* Next JButton */
		startScanButton.setOpaque(false);
		startScanButton.setContentAreaFilled(false);
		startScanButton.setBorderPainted(false);
		startScanButton.setToolTipText(NEXT_BUTTON_TOOL_TIP_TEXT);
		startScanButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		startScanButton.setEnabled(true);		
		startScanButton.setIcon(START_SCAN_ENABLED_IMAGE_ICON);
		startScanButton.setDisabledIcon(START_SCAN_DISABLED_IMAGE_ICON);
		startScanButton.setPressedIcon(START_SCAN_ENABLED_PREST_IMAGE_ICON);
		startScanButton.setDisabledSelectedIcon(Program.NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		startScanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsPanel.FILE_NAME_SELECTED = true;
				
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
			}
		});
		
		/* Back on step JButton */
		pauseScanButton.setEnabled(false);
		pauseScanButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		pauseScanButton.setIcon(PAUSED_SCAN_ENABLED_IMAGE_ICON);
		pauseScanButton.setDisabledIcon(PAUSED_SCAN_DISABLED_IMAGE_ICON);
		pauseScanButton.setPressedIcon(PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON);
		pauseScanButton.setOpaque(false);
		pauseScanButton.setContentAreaFilled(false);
		pauseScanButton.setBorderPainted(false);
		pauseScanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/* Back on step JButton */
		stopScanButton.setEnabled(false);
		stopScanButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		stopScanButton.setIcon(STOP_SCAN_ENABLED_IMAGE_ICON);
		stopScanButton.setDisabledIcon(STOP_SCAN_DISABLED_IMAGE_ICON);
		stopScanButton.setPressedIcon(STOP_SCAN_ENABLED_PREST_IMAGE_ICON);
		stopScanButton.setOpaque(false);
		stopScanButton.setContentAreaFilled(false);
		stopScanButton.setBorderPainted(false);
		stopScanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsPanel.frequencyPanel.frequencyPanelActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelNotActive();
			}
		});
		

		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer1.add(stopScanButton, BorderLayout.WEST);
		continer1.add(pauseScanButton, BorderLayout.CENTER);
		continer1.add(startScanButton, BorderLayout.EAST);

        JPanel inputFeildsAButtons = new JPanel(new BorderLayout());
        inputFeildsAButtons.add(continer1, BorderLayout.SOUTH);
        
		continer1.setBackground(Color.WHITE);
		scanPanel.setBackground(Color.WHITE);
		
		scanPanel.add(inputFeildsAButtons, BorderLayout.SOUTH);
		
		headerAndPanelContiner.add(scanPanel, BorderLayout.SOUTH);
		scanPanel.setVisible(false);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * ACTIVE
	 */
	public void scanPanelActive() {
		MainPanel.setLeftStage(Program.imagePanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(false);
		Program.imagePanel.setVisible(true);
		
		Program.imagePanel.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		Program.settingsPanel.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		
		SettingsPanel.setStage(5);
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);
		
		/* Sets active color Blue for header, labels and borders*/
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setDisabledIcon(HEADER_DISABLED_BLUE_IMAGE_ICON);
		
		stepLabel.setText(STEP_TEXT_LIGHT_BLUE);
		scanPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* Shows buttons and labels */
		startScanButton.setVisible(true);
		pauseScanButton.setVisible(true);
		stopScanButton.setVisible(true);

		/* Changing size of panels when button has been pressed*/	
		scanPanel.setPreferredSize(SCAN_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Turns on Panel */
		scanPanel.setVisible(true);
	}
	/**
	 * NOT ACTIVE
	 */
	public void scanPanelNotActive(){	
		/* Sets header button to enabled and green with a new tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Sets video and buttons not visible */
		startScanButton.setVisible(false);
		pauseScanButton.setVisible(false);
		stopScanButton.setVisible(false);

		if (SettingsPanel.SCAN_DONE)
		{
			/* Sets step label green when button has been pressed */
			stepLabel.setText(STEP_TEXT_DARK_GREEN);

			/* Sets density panel to visible */
			scanPanel.setVisible(true);
			
			/* AreaPanel and header Green */
			scanPanel.setBorder(Program.GREEN_BORDER);
			scanPanel.setVisible(true);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = true);
			
		}
		else
		{
			/* Sets step label gray when button has been pressed */
			stepLabel.setText(STEP_TEXT_GRAY);

			/* Sets density panel to invisible */
			scanPanel.setVisible(false);
			
			/* Sets Header button gray */
			headerButton.setDisabledIcon(HEADER_DISABLED_GRAY_IMAGE_ICON);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);

			/* Changing size of panels when button has been pressed*/	
			scanPanel.setPreferredSize(SCAN_PANEL_DIMENSION_OFF);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);

		}
	}
}


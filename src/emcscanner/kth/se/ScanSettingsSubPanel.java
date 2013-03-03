package emcscanner.kth.se;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LANCZOS4;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class ScanSettingsSubPanel extends JPanel {
	public int STAGE = 5;
	
	/* Scan */
	public Scan scan = new Scan();
	
	/* Boolean */
	public static boolean HEADER_BUTTON_ENABLED = false;
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 	= "Pres to scan ";
	public String PANEL_TOOL_TIP_TEXT 			= "This is where you start the scan.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 	= "Just start the scan";
	
	public String STEP_TEXT_GRAY	 			= "<html> <font color = rgb(120,120,120)>Scan</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  		= "<html> <font color = rgb(100,150,255)>Scan</font></html>";
	public String STEP_TEXT_DARK_GREEN  		= "<html> <font color = rgb(120,200,40)>Scan</font></html>";

	private String SCAN_DONE 					= "<html><p align=center>Scaning Done</p></html>";

	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel(new BorderLayout());
	public JPanel headerAndPanelContiner = new JPanel(new BorderLayout());
	public JPanel scanPanel = new JPanel(new BorderLayout());
	public JPanel buttonContiner1 = new JPanel(new BorderLayout());
	public JPanel buttonContiner2 = new JPanel(new BorderLayout());
    private JPanel inputFeildsAButtons 	= new JPanel(new BorderLayout());
    private JPanel currentLevelContainer = new JPanel(new BorderLayout());
    private JPanel centerTimeStremContainer = new JPanel(new BorderLayout());
    private JPanel currentTimeStreamContainer = new JPanel(new BorderLayout());
    private JPanel timePanel = new JPanel();
    private JPanel streamingPanel = new JPanel();
    private JPanel timeNorthPanel = new JPanel();
    private JPanel timeNorthCeneterPanel = new JPanel(new BorderLayout());
    private JPanel timeNorthLablePanel = new JPanel();
    private JPanel currentLevelNorthContainer = new JPanel(new BorderLayout());
    private JPanel streamingContinaer = new JPanel();
	
	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/PanelGreenScan.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 	= new ImageIcon("image/PanelGreenScanRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 		= new ImageIcon("image/PanelGrayScan.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/PanelGreenScanPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 		= new ImageIcon("image/PanelBlueScan.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenScan.png"); 
	
	public ImageIcon START_SCAN_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/ButtonStartScan.png");
	public ImageIcon START_SCAN_ENABLED_PREST_IMAGE_ICON	= new ImageIcon("image/ButtonStartScanPrest.png");
	public ImageIcon START_SCAN_DISABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonScanStarted.png");
	
	public ImageIcon PAUSED_SCAN_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonPauseScan.png");
	public ImageIcon PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON	= new ImageIcon("image/ButtonPauseScanPrest.png");
	public ImageIcon PAUSED_SCAN_DISABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonPauseScanNotEnabled.png"); 
	
	public ImageIcon STOP_SCAN_ENABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonStopScan.png");
	public ImageIcon STOP_SCAN_ENABLED_PREST_IMAGE_ICON		= new ImageIcon("image/ButtonStopScanPrest.png");
	public ImageIcon STOP_SCAN_DISABLED_IMAGE_ICON 			= new ImageIcon("image/ButtonStopScanNotEnabled.png");
	

	public ImageIcon RESTART_ENABLED_IMAGE_ICON				= new ImageIcon("image/Restart.png");
	public ImageIcon RESTART_PREST_ENABLED_IMAGE_ICON 		= new ImageIcon("image/RestartPrest.png");

	public ImageIcon RESCAN_ENABLED_IMAGE_ICON				= new ImageIcon("image/Rescan.png");
	public ImageIcon RESCAN_PREST_ENABLED_IMAGE_ICON 		= new ImageIcon("image/RescanPrest.png");
	
	public ImageIcon SAVE_ENABLED_IMAGE_ICON				= new ImageIcon("image/Save.png");
	public ImageIcon SAVE_PREST_ENABLED_IMAGE_ICON 			= new ImageIcon("image/SavePrest.png");
	
	/* Buttons */
	public static JButton headerButton 	= new JButton();
	public static JButton startScanButton = new JButton();
	public static JButton stopScanButton = new JButton();
	public static JButton pauseScanButton = new JButton();
	public static JButton rescanButton = new JButton();
	public static JButton saveButton = new JButton();
	
	/* Labels */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	
	private JLabel CALCULATED_TIME_LEFT_LABEL = new JLabel("<html><p align=center>Calculated time left:</p></html>");
	private JLabel timeLabel = new JLabel(SCAN_DONE);
	
	private JLabel STREAMING_PROCESS_LABEL = new JLabel("<html><p align=center>Streming process to <br> www.___.kth.se</p></html>");
	
	private JLabel CurrentLevelLabel = new JLabel("<html><p align=center>&nbsp Current level<br> " +
												"&nbsp [dBm]</p></html>");
	
	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION 					= new Dimension(400, 100);
	public Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 240);
	public Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	
	public Dimension SCAN_PANEL_DIMENSION_ACTIVE 				= new Dimension(322, 200);
	public Dimension SCAN_DENSITY_PANEL_DIMENSION_DONE 			= new Dimension(322, 40);
	public Dimension SCAN_PANEL_DIMENSION_OFF 					= new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 240);
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
		headerButton.addActionListener(new HeaderButtonActionListener(this.STAGE));
		
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
		startScanButton.addActionListener(new NextActionListener());
		
		/* Back on step JButton */
		pauseScanButton.setEnabled(false);
		pauseScanButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		pauseScanButton.setIcon(PAUSED_SCAN_ENABLED_IMAGE_ICON);
		pauseScanButton.setDisabledIcon(PAUSED_SCAN_DISABLED_IMAGE_ICON);
		pauseScanButton.setPressedIcon(PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON);
		pauseScanButton.setOpaque(false);
		pauseScanButton.setContentAreaFilled(false);
		pauseScanButton.setBorderPainted(false);
		pauseScanButton.addActionListener(new PauseScanActionListener());
		
		/* Back on step JButton */
		stopScanButton.setEnabled(false);
		stopScanButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		stopScanButton.setIcon(STOP_SCAN_ENABLED_IMAGE_ICON);
		stopScanButton.setDisabledIcon(STOP_SCAN_DISABLED_IMAGE_ICON);
		stopScanButton.setPressedIcon(STOP_SCAN_ENABLED_PREST_IMAGE_ICON);
		stopScanButton.setOpaque(false);
		stopScanButton.setContentAreaFilled(false);
		stopScanButton.setBorderPainted(false);
		stopScanButton.addActionListener(new BackActionListener());
		
		/* Back on step JButton */
		rescanButton.setEnabled(true);
		rescanButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		rescanButton.setIcon(RESCAN_ENABLED_IMAGE_ICON);
		rescanButton.setPressedIcon(RESCAN_PREST_ENABLED_IMAGE_ICON);
		rescanButton.setOpaque(false);
		rescanButton.setContentAreaFilled(false);
		rescanButton.setBorderPainted(false);
		rescanButton.addActionListener(new RescanActionListener());
		
		/* Back on step JButton */
		saveButton.setEnabled(true);
		saveButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		saveButton.setIcon(SAVE_ENABLED_IMAGE_ICON);
		saveButton.setPressedIcon(SAVE_PREST_ENABLED_IMAGE_ICON);
		saveButton.setOpaque(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setBorderPainted(false);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
		/* Panel for the frequency input */
		scanPanel.setPreferredSize(SCAN_PANEL_DIMENSION_OFF);
		currentLevelNorthContainer.setPreferredSize(new Dimension(160, 40)); // fixa 
		currentLevelContainer.setPreferredSize(new Dimension(160, 40));// fixa 
		
		streamingContinaer.setPreferredSize(new Dimension(140, 60));// fixa 
		timeNorthPanel.setPreferredSize(new Dimension(140, 60));// fixa 
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);

		buttonContiner1.setBackground(Color.WHITE);
		buttonContiner2.setBackground(Color.WHITE);
		scanPanel.setBackground(Color.WHITE);
		inputFeildsAButtons.setBackground(Color.WHITE);
		currentLevelContainer.setBackground(Color.WHITE);
		centerTimeStremContainer.setBackground(Color.WHITE);
		currentTimeStreamContainer.setBackground(Color.WHITE);
		streamingPanel.setBackground(Color.WHITE);
		currentLevelNorthContainer.setBackground(Color.WHITE);
		timePanel.setBackground(Color.WHITE);

		timeNorthPanel.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
		timeNorthCeneterPanel.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
		timeNorthLablePanel.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
		
		streamingContinaer.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
		
		timeNorthPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		streamingContinaer.setBorder(Program.LIGHT_BLUE_BORDER);

		
		/* Setting containers Layouts for the right GUI look. */
		buttonContiner1.add(stopScanButton, BorderLayout.WEST);
		buttonContiner1.add(pauseScanButton, BorderLayout.CENTER);
		buttonContiner1.add(startScanButton, BorderLayout.EAST);
		
		buttonContiner2.add(rescanButton, BorderLayout.WEST);
		buttonContiner2.add(saveButton, BorderLayout.EAST);
		
		currentLevelContainer.add(CurrentLevelLabel);
		
		currentLevelNorthContainer.add(currentLevelContainer, BorderLayout.NORTH);
		currentTimeStreamContainer.add(currentLevelNorthContainer, BorderLayout.WEST);
		
		timeNorthLablePanel.add(timeLabel);
		
		timeNorthCeneterPanel.add(CALCULATED_TIME_LEFT_LABEL, BorderLayout.NORTH);
		timeNorthCeneterPanel.add(timeNorthLablePanel, BorderLayout.SOUTH);

		timeNorthPanel.add(timeNorthCeneterPanel, BorderLayout.SOUTH);
		timePanel.add(timeNorthPanel);

		streamingContinaer.add(STREAMING_PROCESS_LABEL);
		streamingPanel.add(streamingContinaer);
		
		centerTimeStremContainer.add(timePanel, BorderLayout.CENTER);
		centerTimeStremContainer.add(streamingPanel, BorderLayout.NORTH);
		currentTimeStreamContainer.add(centerTimeStremContainer, BorderLayout.CENTER);
        
		scanPanel.add(inputFeildsAButtons, BorderLayout.SOUTH);
		scanPanel.add(currentTimeStreamContainer, BorderLayout.CENTER);
		
		headerAndPanelContiner.add(scanPanel, BorderLayout.SOUTH);

		stepContiner.add(stepLabel, BorderLayout.NORTH );
		this.add(stepContiner, BorderLayout.WEST);
		this.add(headerAndPanelContiner);

		scanPanel.setVisible(false);
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
		
		SettingsPanel.setStage(this.STAGE);
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);

		/* Shows buttons and labels */
		startScanButton.setVisible(true);
		pauseScanButton.setVisible(true);
		stopScanButton.setVisible(true);
		rescanButton.setVisible(true);
		saveButton.setVisible(true);
		
		/* Changing size of panels when button has been pressed*/	
		scanPanel.setPreferredSize(SCAN_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Turns on Panel */
		scanPanel.setVisible(true);

		if (scan.getScanDone())
		{
	        inputFeildsAButtons.remove(buttonContiner1);
	        inputFeildsAButtons.add(buttonContiner2, BorderLayout.SOUTH);
			
			/* Sets active color Blue for header, labels and borders*/
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
			headerButton.setDisabledIcon(HEADER_ENABLED_IMAGE_ICON);

			/* panel and step label color blue */
			stepLabel.setText(STEP_TEXT_DARK_GREEN);
			scanPanel.setBorder(Program.GREEN_BORDER);
			
			EndSubSettingsPanel.backButton.setEnabled(true);
			
			timeNorthPanel.setBackground(Program.LIGHT_GREEN_ALPHA_COLOR);
			timeNorthCeneterPanel.setBackground(Program.LIGHT_GREEN_ALPHA_COLOR1);
			timeNorthLablePanel.setBackground(Program.LIGHT_GREEN_ALPHA_COLOR1);
			
			streamingContinaer.setBackground(Program.LIGHT_RED_ALPHA_COLOR);
			
			timeNorthPanel.setBorder(Program.GREEN_BORDER);
			streamingContinaer.setBorder(Program.RED_BORDER);
		}
		else 
		{
	        inputFeildsAButtons.remove(buttonContiner2);
	        inputFeildsAButtons.add(buttonContiner1, BorderLayout.SOUTH);
			
			/* Sets active color Blue for header, labels and borders*/
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
			headerButton.setDisabledIcon(HEADER_DISABLED_BLUE_IMAGE_ICON);
			
			/* panel and step label color blue */
			stepLabel.setText(STEP_TEXT_LIGHT_BLUE);
			scanPanel.setBorder(Program.LIGHT_BLUE_BORDER);
			
			timeNorthPanel.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
			timeNorthCeneterPanel.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
			timeNorthLablePanel.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
			streamingContinaer.setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
			
			timeNorthPanel.setBorder(Program.LIGHT_BLUE_BORDER);
			streamingContinaer.setBorder(Program.LIGHT_BLUE_BORDER);
		}
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
		//restartButton.setVisible(false);
		rescanButton.setVisible(false);
		saveButton.setVisible(false);

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


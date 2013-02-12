package emcscanner.kth.se;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LANCZOS4;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class ScanSettingsSubPanel extends JPanel {
	/* Positions taken from the table */
	private int MesurmentPosX = 10000;
	private int MesurmentPosY = 5000;
	
	private boolean moveFX;
	private boolean moveUY;

	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel(new BorderLayout());
	public JPanel headerAndPanelContiner = new JPanel(new BorderLayout());
	public JPanel scanPanel = new JPanel(new BorderLayout());
	public JPanel continer1 = new JPanel(new BorderLayout());
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
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 		= new ImageIcon("image/PanelGreenScan.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON = new ImageIcon("image/PanelGreenScanRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 	= new ImageIcon("image/PanelGrayScan.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 	= new ImageIcon("image/PanelGreenScanPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 	= new ImageIcon("image/PanelBlueScan.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenScan.png"); 
	
	public ImageIcon START_SCAN_ENABLED_IMAGE_ICON 	 	= new ImageIcon("image/ButtonStartScan.png");
	public ImageIcon START_SCAN_ENABLED_PREST_IMAGE_ICON= new ImageIcon("image/ButtonStartScanPrest.png");
	public ImageIcon START_SCAN_DISABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonScanStarted.png");
	
	public ImageIcon PAUSED_SCAN_ENABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonPauseScan.png");
	public ImageIcon PAUSED_SCAN_ENABLED_PREST_IMAGE_ICON= new ImageIcon("image/ButtonPauseScanPrest.png");
	public ImageIcon PAUSED_SCAN_DISABLED_IMAGE_ICON 	= new ImageIcon("image/ButtonPauseScanNotEnabled.png"); 
	
	public ImageIcon STOP_SCAN_ENABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonStopScan.png");
	public ImageIcon STOP_SCAN_ENABLED_PREST_IMAGE_ICON = new ImageIcon("image/ButtonStopScanPrest.png");
	public ImageIcon STOP_SCAN_DISABLED_IMAGE_ICON 		= new ImageIcon("image/ButtonStopScanNotEnabled.png");
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 	= "Pres to scan ";
	public String PANEL_TOOL_TIP_TEXT 			= "This is where you start the scan.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 	= "Just start the scan";
	
	public String STEP_TEXT_GRAY	 			= "<html> <font color = rgb(120,120,120)>Scan</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  		= "<html> <font color = rgb(100,150,255)>Scan</font></html>";
	public String STEP_TEXT_DARK_GREEN  		= "<html> <font color = rgb(120,200,40)>Scan</font></html>";

	private String SCAN_DONE 					= "<html><p align=center>Scaning Done</p></html>";
	
	/* Boolean */
	public static boolean HEADER_BUTTON_ENABLED = false;
	
	/* Buttons */
	public static JButton headerButton 	= new JButton();
	public static JButton startScanButton = new JButton();
	public static JButton stopScanButton = new JButton();
	public static JButton pauseScanButton = new JButton();
	
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
	
	/* Color image is going to contain colors from scanner*/
	public IplImage colorImage;
	public BufferedImage buffImage;
	public BufferedImage rezicedBuffImage;
	

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
				if (FrequencySettingsSubPanel.NEXT_BUTTON_ENABLED)
				{
					SettingsPanel.areaPanel.areaNotPanelActive();
					SettingsPanel.densityPanel.densityPanelNotActive();
					SettingsPanel.frequencyPanel.frequencyPanelNotActive();
					SettingsPanel.fileNamePanel.fileNamePanelActive();
					SettingsPanel.scanPanel.scanPanelActive();
				}
			}
		});
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
				
				colorImage = IplImage.create(SettingsPanel.CROPT_PHOTO_DIMENSION.width * Program.TIONDELS_MILLI_METER_PIXEL, SettingsPanel.CROPT_PHOTO_DIMENSION.height * Program.TIONDELS_MILLI_METER_PIXEL, 8/*SettingsPanel.photo.depth()*/, 4/*SettingsPanel.photo.nChannels()*/);
				buffImage = colorImage.getBufferedImage();
				int alpha = new java.awt.Color((int)255, (int)255, (int)255, (int)0).getRGB();

				for (int yi = 0; yi < buffImage.getHeight(); yi++) {
					for (int xi = 0; xi < buffImage.getWidth(); xi++){
						buffImage.setRGB(xi, yi, alpha);
					}
				}
				/*
				IplImage colorImage2 = IplImage.create(1000, 4000, SettingsPanel.photo.depth(), SettingsPanel.photo.nChannels());
				BufferedImage buffImage2 = colorImage2.getBufferedImage();
				
				double wave = 380.0;
				for (int xi = 0; xi < 1000; xi++){
					for (int yi = 0; yi < 4000; yi++) {
						buffImage2.setRGB(xi, yi, wavelengthToColorConverter(wave));
						wave += 0.1;
					}
					wave = 380;
				}

				colorImage2 = IplImage.createFrom(buffImage2);
		    	cvSaveImage("webcam photo/ColorPalet.png", colorImage2);
				*/
		        
		        SettingsPanel.FILE_NAME_SELECTED = true;

				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.frequencyPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
				
				FrequencySettingsSubPanel.headerButton.setEnabled(false);
				AreaSettingsSubPanel.headerButton.setEnabled(false);
				DensitySettingsSubPanel.headerButton.setEnabled(false);
				FileNameSettingsSubPanel.headerButton.setEnabled(false);

				startScanButton.setEnabled(false);
				stopScanButton.setEnabled(true);
				pauseScanButton.setEnabled(true);
				
				int delay = 100;

		        Timer timer = new Timer();
		       
		        timer.schedule( new TimerTask(){
		        	public void run() { 
		        		scan();
		        	}
		       	}, delay);
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
				startScanButton.setEnabled(true);
				pauseScanButton.setEnabled(false);
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
				SettingsPanel.frequencyPanel.frequencyPanelNotActive();
				SettingsPanel.areaPanel.areaNotPanelActive();
				SettingsPanel.densityPanel.densityPanelNotActive();
				SettingsPanel.fileNamePanel.fileNamePanelNotActive();
				SettingsPanel.scanPanel.scanPanelActive();
				
				FrequencySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.frequencyPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
				
				AreaSettingsSubPanel.headerButton.setEnabled(true);
				DensitySettingsSubPanel.headerButton.setEnabled(true);
				FileNameSettingsSubPanel.headerButton.setEnabled(true);
				
				startScanButton.setEnabled(true);
				pauseScanButton.setEnabled(false);
				stopScanButton.setEnabled(false);
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

		continer1.setBackground(Color.WHITE);
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
		continer1.add(stopScanButton, BorderLayout.WEST);
		continer1.add(pauseScanButton, BorderLayout.CENTER);
		continer1.add(startScanButton, BorderLayout.EAST);
		
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

        inputFeildsAButtons.add(continer1, BorderLayout.SOUTH);
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
	public void scan(){
		frequencyOutput();
		moveStartPosToMesurmentPos();
		scanValues();
	}
	private void frequencyOutput(){
		/* There will be a function sending a string with the frequency that is to be scanned with */
		System.out.println("Frequency: "+ SettingsPanel.FREQUENCY_START_SELECTED_VALUE);
	}
	private int scanInput(){
		/* Function with an string input and then convert it to integer and return */
		/* We will get values between 100 and -100 i think */
		int convertedFromStringInput = (int) (Math.random() * 400 + 380 );
		return convertedFromStringInput;
	}
	private void moveMotorForwordX(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" + X " + i);
	}
	private void moveMotorUppY(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" + Y " + i);
	}
	private void moveMotorBackX(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" - X " + i);
	}
	private void moveMotorDownY(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" - Y " + i);
	}
	private void moveStartPosToMesurmentPos(){
		int mPX = MesurmentPosX;
		int mPY = MesurmentPosY;
		int eX = (int) (SettingsPanel.TABLE_WIDTH - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_X * Program.TIONDELS_MILLI_METER_PIXEL));
		int eY = (int) (SettingsPanel.TABLE_HEIGHT - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_Y * Program.TIONDELS_MILLI_METER_PIXEL));
		int sX = (int) (SettingsPanel.TABLE_WIDTH - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_X * Program.TIONDELS_MILLI_METER_PIXEL));
		int sY = (int) (SettingsPanel.TABLE_HEIGHT - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_Y * Program.TIONDELS_MILLI_METER_PIXEL));
		
		if(Math.abs(mPX - eX) > Math.abs(mPX - sX))
		{
			if(Math.abs(mPY - eY) > Math.abs(mPY - sY))
			{
				moveUY = false;
				for (int i = 0; i <= Math.abs(mPY - sY); i++){
					if ((mPY - sY) > 0){
						moveMotorUppY(i);
					}
					else if ((mPY - sY) == 0){
						// Do nothing
					}
					else {
						moveMotorDownY(i);
					}
				}
			}
			else
			{
				moveUY = true;
				for (int i = 0; i <= Math.abs(mPY - eY); i++){
					if ((mPY - eY) > 0){
						moveMotorUppY(i);
					}
					else if ((mPY - eY) == 0){
						// Do nothing
					}
					else {
						moveMotorDownY(i);
					}
				}
			}
			moveFX = false;
			for (int i = 0; i <= Math.abs(mPX - sX); i++){
				if ((mPX - sX) > 0){
					moveMotorForwordX(i);
				}
				else if ((mPX - sX) == 0){
					// Do nothing
				}
				else {
					moveMotorBackX(i);
				}
			}
		}
		else
		{
			if(Math.abs(mPY - eY) > Math.abs(mPY - sY))
			{
				moveUY = false;
				for (int i = 0; i <= Math.abs(mPY - sY); i++){
					if ((mPY - sY) > 0){
						moveMotorUppY(i);
					}
					else if ((mPY - sY) == 0){
						// Do nothing
					}
					else {
						moveMotorDownY(i);
					}
				}
			}
			else
			{
				moveUY = true;
				for (int i = 0; i <= Math.abs(mPY - eY); i++){
					if ((mPY - eY) > 0){
						moveMotorUppY(i);
					}
					else if ((mPY - eY) == 0){
						// Do nothing
					}
					else {
						moveMotorDownY(i);
					}
				}
			}
			moveFX = true;
			for (int i = 0; i <= Math.abs(mPX - eX); i++){
				if ((mPX - eX) > 0){
					moveMotorForwordX(i);
				}
				else if ((mPX - eX) == 0){
					// Do nothing
				}
				else {
					moveMotorBackX(i);
				}
			}
		}
	}
	private void scanValues(){
		int hy = buffImage.getHeight();
		int wx = buffImage.getWidth();
		int nX = SettingsPanel.numberOfStepsWidth;
		int nY = SettingsPanel.numberOfStepsHeight;
		int sizeY;
		int sizeX;
		if (DensitySettingsSubPanel.inputStepBoolean)
		{
			sizeY = (int)(buffImage.getHeight() / SettingsPanel.numberOfStepsWidth);
			sizeX = (int)(buffImage.getWidth() / SettingsPanel.numberOfStepsHeight);
		}
		else
		{
			sizeY = SettingsPanel.stepSizeHeight;
			sizeX = SettingsPanel.stepSizeWidth;
		}
		/* Two for loops that moves the axis to the middle of the first area */
		for (int y = 0; y < sizeY / 2; y++)
		{
			if (moveUY)
				moveMotorUppY(y);
			else
				moveMotorDownY(y);
		}
		for (int x = 0; x < sizeX / 2; x++)
		{
			if (moveFX)
				moveMotorForwordX(x);
			else
				moveMotorBackX(x);
		}

		/* scan the rest of the card algorithm */
		boolean changeWay = false;
		for(int y = 0; y < hy; y += sizeY){
			int colorX = 0;
			int stepX = 0;
			for(int x = 0; x < wx; x += sizeX ){
				if(moveFX)
				{
					if(changeWay)
					{
						if (x == stepX)
						{
							colorX = wavelengthToColorConverter(scanInput());
							stepX = stepX + sizeX;
						}
						if(moveUY)
						{
							for(int xi = 0; xi < sizeX; xi++){
								if (x + xi < wx)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if ((hy - y) - yi - 1 > 0)
										{
											buffImage.setRGB(xi + x, ((hy - y) - yi - 1), colorX);
										}
									}
								}
								moveMotorBackX(x);
							}
						}
						else
						{
							for(int xi = 0; xi < sizeX; xi++){
								if (x + xi < wx)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (y + yi < hy)
										{
											buffImage.setRGB(xi + x, y + yi, colorX);
										}
									}
								}
								moveMotorBackX(x);
							}
						}
					}
					else
					{
						if (x == stepX)
						{
							colorX = wavelengthToColorConverter(scanInput());
							stepX = stepX + sizeX;
						}
						if(moveUY)
						{
							for(int xi = 0; xi < sizeX; xi++){
								if ((wx - xi - x) - 1 > 0)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (((hy - y) - yi - 1) > 0)
										{
											buffImage.setRGB((wx - xi - x) - 1, ((hy - y) - yi - 1), colorX);
										}
									}
								}
								moveMotorForwordX(x);
							}
						}
						else
						{
							for(int xi = 0; xi < sizeX; xi++){
								if ((wx - xi - x) - 1 > 0)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (y + yi < hy) 
										{
											buffImage.setRGB(((wx - xi - x)) - 1, y + yi, colorX);
										}
									}
								}
								moveMotorForwordX(x);
							}
						}
					}
				}
				else
				{
					if(changeWay)
					{
						if (x == stepX)
						{
							colorX = wavelengthToColorConverter(scanInput());
							stepX = stepX + sizeX;
						}
						if(moveUY)
						{
							for(int xi = 0; xi < sizeX; xi++){
								if ((wx - xi - x) - 1 > 0)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (((hy - y) - yi - 1) > 0)
										{
											buffImage.setRGB(((wx - x - xi)) - 1, ((hy - y) - yi - 1), colorX);
										}
									}
								}
								moveMotorForwordX(x);
							}
						}
						else
						{
							for(int xi = 0; xi < sizeX; xi++) {
								if ((wx - xi - x) - 1 > 0)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (y + yi < hy)
										{
											buffImage.setRGB(((wx - x - xi)) - 1, y + yi, colorX);
										}
									}
								}
								moveMotorForwordX(x);
							}
						}	
					}
					else
					{
						if (x == stepX)
						{
							colorX = wavelengthToColorConverter(scanInput());
							stepX = stepX + sizeX;
						}
						if(moveUY)
						{
							for(int xi = 0; xi < sizeX; xi++) {
								if (x + xi < wx)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (((hy - y) - yi - 1) > 0)
										{
											buffImage.setRGB(x + xi, (hy - y) - yi - 1, colorX);
										}
									}
								}
								moveMotorBackX(x);
							}
						}
						else
						{
							for(int xi = 0; xi < sizeX; xi++) {
								if (x + xi < wx)
								{
									for(int yi = 0; yi < sizeY; yi++) {
										if (y + yi < hy)
										{
											buffImage.setRGB(x + xi, y + yi, colorX);
										}
									}
								}
								moveMotorBackX(x);
							}
						}
					}
				}
				resizeAndPaint();
			}
			if (moveUY)
			{
				for(int yi = 0; yi < sizeY; yi++ )
					moveMotorUppY(y);
			}
			else
			{
				for(int yi = 0; yi < sizeY; yi++ )
					moveMotorDownY(y);
			}

			if (changeWay)
				changeWay = false;
			else
				changeWay = true;
			
			
		}
		System.out.println("Area is scaned!");
		
		colorImage = IplImage.createFrom(buffImage);
    	cvSaveImage("webcam photo/Color" + SettingsPanel.FILE_NAME + ".png", colorImage);

	}
	private int wavelengthToColorConverter(double wave){
		/* Constants */
		double waveLength = wave;
		double intensityMax = 255;	
		double gamma = 0.80;	
		/* Variables */
		double R = 0;
		double G = 0;
		double B = 0;
		double A = 80;
		
		if ( 379.9 < waveLength && waveLength < 440)
		{
			R = -(waveLength - 440) / (440 - 380);
			G = 0.0;
			B = 1.0;
		}
		else if ( 440 <= waveLength && waveLength < 490)
		{
			R = 0.0;
			G = (waveLength - 440) / (490 - 440);
			B = 1.0;
		}
		else if ( 490 <= waveLength && waveLength < 510)
		{
			R = 0.0;
			G = 1.0;
			B = -(waveLength - 510) / (510 - 490);
		}
		else if ( 510 <= waveLength && waveLength < 580)
		{
			R = (waveLength - 510) / (580 - 510);
			G = 1.0;
			B = 0.0;
		}
		else if (580 <= waveLength && waveLength < 645)
		{
			R = 1.0;
			G = -(waveLength - 645) / (645 - 580);
			B = 0.0;
		}
		else if ( 645 <= waveLength && waveLength < 780.1)
		{
			R = 1.0;
			G = 0.0;
			B = 0.0;
		}
		else
		{
			R = 0.0;
	   		G = 0.0;
	    	B = 0.0;
		}
		double factor; 
		if (380 <= waveLength && waveLength < 420)
			factor = 0.3 + 0.7 * (waveLength - 380) / (420 - 380);
		else if (420 <= waveLength && waveLength < 701)
			factor = 1.0;
		else if (701 <= waveLength && waveLength <= 780)
			factor = 0.3 + 0.7 * (780 - waveLength) / (780 - 700);
		else
			factor = 0.0;
		
		R = adjust(R, factor, intensityMax, gamma);
		G = adjust(G, factor, intensityMax, gamma);
		B = adjust(B, factor, intensityMax, gamma);
		
		System.out.println(wave);
		
		return new java.awt.Color((int)R, (int)G, (int)B, (int)A).getRGB();
	}
	private double adjust(double color, double factor, double intensityMax, double gamma){
		if (color == 0.0)
			return 0.0;
		else
			return (intensityMax * Math.pow(color * factor, gamma));
	}
	private void resizeAndPaint() {

		int widthFrame = Program.cameraPanel.getWidth();
		int heightFrame = Program.cameraPanel.getHeight();
        int widthCameraPhoto = buffImage.getWidth();
        int heightCameraPhoto = buffImage.getHeight();

        Dimension imgSize2 = new Dimension(widthCameraPhoto, heightCameraPhoto);
        Dimension boundary2 = new Dimension(widthFrame, heightFrame);
        /* Changing the scaling of the grabbed camera image */
        Dimension newImagebunderys1 = CameraPanel.getScaledDimension(imgSize2, boundary2);

        int width  = newImagebunderys1.width;
        int height  = newImagebunderys1.height;
        
        /* Dimension is being updated all the time so we know where to draw the lines  */ 
        SettingsPanel.PHOTO_VIEW_DIMENSION = newImagebunderys1;
        
        IplImage ipl = IplImage.create(width, height, colorImage.depth(), colorImage.nChannels());
        
        cvResize(IplImage.createFrom(buffImage), ipl, CV_INTER_LANCZOS4);

        rezicedBuffImage = ipl.getBufferedImage();
		        
		Program.frame.glass.repaint();
	}
}


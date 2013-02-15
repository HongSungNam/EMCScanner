package emcscanner.kth.se;

import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LANCZOS4;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class ScanSettingsSubPanel extends JPanel {
	/* Positions taken from the table */
	private int MesurmentPosX = 10000;
	private int MesurmentPosY = 5000;
	private int ALPHA = 200;
	
	/* Array with frequency to be scanned */
	float[] frequency = null; 
	
	/* scan variables */
	private int hy;
	private int wx;
	private int nX;
	private int nY;
	private int sizeY;
	private int sizeX;
	public int x = 0;
	public int y = 0;
	
	public int STAGE = 5;
	
	private int xMoved = 0;
	private int yMoved = 0;
	
	/* Boolean */
	public boolean scanAreaChanged = false;
	private boolean moveStartPosToMesurmentPosBoolean = false;
	
	public boolean scanActive = false;
	
	public static boolean HEADER_BUTTON_ENABLED = false;
	
	public boolean scanStoped = false;
	
	public boolean changeWay = false;
	
	private boolean moveFX;
	private boolean moveUY;
	
	private boolean scanX = true;
	private boolean scanY = true;
	
	public boolean pauseScanX = false;
	
	public boolean scanNeverStarted = true;

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
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 	= "Pres to scan ";
	public String PANEL_TOOL_TIP_TEXT 			= "This is where you start the scan.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT 	= "Just start the scan";
	
	public String STEP_TEXT_GRAY	 			= "<html> <font color = rgb(120,120,120)>Scan</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  		= "<html> <font color = rgb(100,150,255)>Scan</font></html>";
	public String STEP_TEXT_DARK_GREEN  		= "<html> <font color = rgb(120,200,40)>Scan</font></html>";

	private String SCAN_DONE 					= "<html><p align=center>Scaning Done</p></html>";
	
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
		pauseScanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startScanButton.setEnabled(true);
				pauseScanButton.setEnabled(false);

				pauseScanX = true;
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
		stopScanButton.addActionListener(new BackActionListener());

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
		
		SettingsPanel.setStage(this.STAGE);
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
	/**
	 * Calls the right methods and starts the scan 
	 */
	@SuppressWarnings("null")
	public void startScan(){
		numberOfScans();
		initializeScanValues();
		if (scanStoped && !scanAreaChanged)
		{
			if (yMoved > 0)
				for (int yi = 0; yi < yMoved; yi++)
					moveMotorDownY(yi);
			else if (yMoved < 0)
				for (int yi = yMoved; yi < 0; yi++)
					moveMotorUppY(yi);
			if (xMoved > 0)
				for (int xi = 0; xi < xMoved; xi++)
					moveMotorBackX(xi);
			else if (xMoved < 0)
				for (int xi = xMoved; xi < 0; xi++)
					moveMotorForwordX(xi);
			
			scanStoped = false;
		}
		else
		{
			scanStoped = false;
			moveStartPosToMesurmentPos();
			moveToStartCeneter();
			scanAreaChanged = false;
		}
		for (int i = 0; i < frequency.length; i++) {
			frequencyOutput(frequency[i]);
			scanValues();
			if (scanStoped)
				break;
			else 
			{
				restartScanValues();
				moveUY = !moveUY;
			}
		}
	}
	private void frequencyOutput(float frequency){
		/* There will be a function sending a string with the frequency that is to be scanned with */
		System.out.println("Frequency: "+ frequency);
	}
	private int scanInput(){
		/* Function with an string input and then convert it to integer and return */
		/* We will get values between 100 and -100 i think */
		int convertedFromStringInput = (int) (Math.random() * 400 + 380 );
		return convertedFromStringInput;
	}
	/**
	 * 
	 * @param i
	 */
	private void moveMotorForwordX(int i){
		/* function for moving the motors 1 step */
		if (!moveStartPosToMesurmentPosBoolean)
		{
			xMoved += 1;  
		//	System.out.println("xMovedForword: " + xMoved);
		}
	}
	/**
	 * 
	 * @param i
	 */
	private void moveMotorUppY(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" + Y " + i);
		if (!moveStartPosToMesurmentPosBoolean)
		{
			yMoved += 1;
		//	System.out.println("yMovedUpp: " + yMoved);
		}
	}
	/**
	 * 
	 * @param i
	 */
	private void moveMotorBackX(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" - X " + i);
		if (!moveStartPosToMesurmentPosBoolean)
		{
			xMoved -= 1;
		//	System.out.println("xMovedBackword: " + xMoved);
		}
	}
	/**
	 * 
	 * @param i
	 */
	private void moveMotorDownY(int i){
		/* function for moving the motors 1 step */
		//System.out.println(" - Y " + i);
		if (!moveStartPosToMesurmentPosBoolean)
		{
			yMoved -= 1;
		//	System.out.println("yMovedDown: " + yMoved);
		}
	}
	/**
	 * Moves the closest corner of the selected area to the scanners position.
	 */
	private void moveStartPosToMesurmentPos(){
		int mPX = MesurmentPosX;
		int mPY = MesurmentPosY;
		int eX = (int) (SettingsPanel.TABLE_WIDTH - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_X * Program.TIONDELS_MILLI_METER_PIXEL));
		int eY = (int) (SettingsPanel.TABLE_HEIGHT - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_END_Y * Program.TIONDELS_MILLI_METER_PIXEL));
		int sX = (int) (SettingsPanel.TABLE_WIDTH - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_X * Program.TIONDELS_MILLI_METER_PIXEL));
		int sY = (int) (SettingsPanel.TABLE_HEIGHT - (SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_START_Y * Program.TIONDELS_MILLI_METER_PIXEL));
		moveStartPosToMesurmentPosBoolean = true;
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
	/**
	 * Scanning Values of each position and gives the user feedback, in the way of colors
	 */
	private void scanValues(){
		/* scan the rest of the card algorithm */
		while (!scanStoped && scanY) {
			int colorX = 0;
			int stepX = 0;
			x = 0;
			setScanX(true);
			while (!scanStoped && scanX) {
				if (!pauseScanX)
				{
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
								Graphics g = buffImage.getGraphics();
								g.setColor(new Color(colorX));
								g.fillRect(x, y, sizeX, sizeY);
								for(int xi = 0; xi < sizeX; xi++){
									if (x + xi < wx)
									{
										for(int yi = 0; yi < sizeY; yi++) {
											if ((this.hy - this.y) - yi - 1 > 0)
												buffImage.setRGB(xi + x, ((hy - y) - yi - 1), colorX);
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
											if (this.y + yi < hy)
												buffImage.setRGB(xi + x, y + yi, colorX);
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
											if (((this.hy - this.y) - yi - 1) > 0)
												buffImage.setRGB((wx - xi - x) - 1, ((hy - y) - yi - 1), colorX);
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
											if (this.y + yi < hy) 
												buffImage.setRGB(((wx - xi - x)) - 1, y + yi, colorX);
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
											if (((this.hy - this.y) - yi - 1) > 0)
												buffImage.setRGB(((wx - x - xi)) - 1, ((hy - y) - yi - 1), colorX);
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
											if (this.y + yi < hy)
												buffImage.setRGB(((wx - x - xi)) - 1, y + yi, colorX);
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
											if (((this.hy - this.y) - yi - 1) > 0)
												buffImage.setRGB(x + xi, (this.hy - this.y) - yi - 1, colorX);
										}
									moveMotorBackX(x);
									}
								}
							}
							else
							{
								for(int xi = 0; xi < sizeX; xi++) {
									if (x + xi < wx)
									{
										for(int yi = 0; yi < sizeY; yi++) {
											if (y + yi < this.hy)
												buffImage.setRGB(x + xi, y + yi, colorX);
										}
									moveMotorBackX(x);
									}
								}
							}
						}
					}
	
					if ((x > wx && moveFX) || (wx - x < 0 && !moveFX))
					{
						x = 0;
						scanX = false;
						resizeAPaint();
					}
					else {
						x += sizeX;
						resizeAPaint();
					}
				}
				else
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			if (moveUY)
			{
				for(int yi = 0; yi < sizeY; yi++ )
					moveMotorUppY(this.y);
			}
			else
			{
				for(int yi = 0; yi < sizeY; yi++ )
					moveMotorDownY(this.y);
			}
			changeWay = !changeWay;
			
			y += sizeY;
			
			if (y >= hy)
				setScanY(false);
		}
		if (!scanStoped)
		{
			System.out.println("Area is scaned!");
			try {
				File outputfile = new File("webcam photo/Color" + SettingsPanel.FILE_NAME + ".png");
				ImageIO.write(buffImage, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Takes in a value and returns the corresponding color value.
	 * 
	 * @param wave
	 * @return
	 */
	private int wavelengthToColorConverter(double wave){
		/* Constants */
		double waveLength = wave;
		double intensityMax = 255;	
		double gamma = 0.80;	
		/* Variables */
		double R = 0;
		double G = 0;
		double B = 0;
		double A = ALPHA;
		
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
		
		//System.out.println(wave);
		
		return new java.awt.Color((int)R, (int)G, (int)B, (int)A).getRGB();
	}
	/**
	 * Adjust the color just by a alitle bitt in the ends so it looks as it should
	 * @param color
	 * @param factor
	 * @param intensityMax
	 * @param gamma
	 * @return
	 */
	private double adjust(double color, double factor, double intensityMax, double gamma){
		if (color == 0.0)
			return 0.0;
		else
			return (intensityMax * Math.pow(color * factor, gamma));
	}
	/**
	 * Resizes the image with the in scanned colors.
	 */
	public void resizeAPaint() {
		if (!scanNeverStarted) 
		{
	        IplImage ipl = IplImage.create(Program.imagePanel.newWidthPhoto, Program.imagePanel.newHeightPhoto, 16, 4);
	        
	        if (scanStoped)
	        	setBuffImageAlpha();
	        	
	        cvResize(IplImage.createFrom(buffImage), ipl, CV_INTER_LANCZOS4);
	        rezicedBuffImage = ipl.getBufferedImage();
			        
			Program.frame.glass.repaint();
		}
	}
	/**
	 * Moves the scanner point to the center of the first area that we want to scan.
	 */
	private void moveToStartCeneter() {
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

		moveStartPosToMesurmentPosBoolean = false;
	}
	/**
	 * Gets the user selected values for the scan.
	 */
	private void initializeScanValues() {
		hy = buffImage.getHeight();
		wx = buffImage.getWidth();
		
		nX = SettingsPanel.numberOfStepsWidth;
		nY = SettingsPanel.numberOfStepsHeight;
		
		if (DensitySettingsSubPanel.inputStepBoolean)
		{
			sizeY = (int)(buffImage.getHeight() / SettingsPanel.numberOfStepsHeight);
			sizeX = (int)(buffImage.getWidth() / SettingsPanel.numberOfStepsWidth);
		}
		else
		{
			sizeY = SettingsPanel.stepSizeHeight;
			sizeX = SettingsPanel.stepSizeWidth;
		}
	}
	/**
	 * Sets the scan background to invisible so that we can see the image bellow 
	 */
	public void setBuffImageAlpha() {
		colorImage = IplImage.create(SettingsPanel.CROPT_PHOTO_DIMENSION.width * Program.TIONDELS_MILLI_METER_PIXEL, SettingsPanel.CROPT_PHOTO_DIMENSION.height * Program.TIONDELS_MILLI_METER_PIXEL, 16/*SettingsPanel.photo.depth()*/, 4/*SettingsPanel.photo.nChannels()*/);
		buffImage = colorImage.getBufferedImage();
		int alpha = new java.awt.Color((int)255, (int)255, (int)255, (int)0).getRGB();

		for (int yi = 0; yi < buffImage.getHeight(); yi++) {
			for (int xi = 0; xi < buffImage.getWidth(); xi++){
				buffImage.setRGB(xi, yi, alpha);
			}
		}
	}
	/**
	 * Function that creates a color pellet for all wave lengths
	 */
	public void createColorPalet() {
		IplImage colorImage2 = IplImage.create(100, 400, SettingsPanel.photo.depth(), SettingsPanel.photo.nChannels());
		BufferedImage buffImage2 = colorImage2.getBufferedImage();
		
		double wave = 380.0;
		for (int xi = 0; xi < 100; xi++){
			for (int yi = 0; yi < 400; yi++) {
				buffImage2.setRGB(xi, yi, wavelengthToColorConverter(wave));
				wave += 1;
			}
			wave = 380;
		}
		try {
			File outputfile = new File("webcam photo/ColorPalet" + SettingsPanel.FILE_NAME + ".png");
			ImageIO.write(buffImage2, "png", outputfile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Sets the headers to disabled when scan has started.
	 */
	public void headersInactive() {
		FrequencySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.frequencyPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		AreaSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		DensitySettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.densityPanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		FileNameSettingsSubPanel.headerButton.setDisabledIcon(SettingsPanel.fileNamePanel.HEADER_DISABLED_DARK_GREEN_IMAGE_ICON);
		
		FrequencySettingsSubPanel.headerButton.setEnabled(false);
		AreaSettingsSubPanel.headerButton.setEnabled(false);
		DensitySettingsSubPanel.headerButton.setEnabled(false);
		FileNameSettingsSubPanel.headerButton.setEnabled(false);
	}
	/**
	 * Changes the buttons so that they are correctly enabled.
	 * 
	 * start button disabled
	 * pause button enabled 
	 * stop button enabled
	 */
	public void buttonsScanActiveStarted() {
		startScanButton.setEnabled(false);
		stopScanButton.setEnabled(true);
		pauseScanButton.setEnabled(true);
	}
	/**
	 * Starts the scan but but lets the headers set enable false
	 */
	public void delayedStartScan() {
		int DELAY = 100;
	    Timer timer = new Timer();
	    timer.schedule( new TimerTask(){
	    	public void run() { 
	    		startScan();
	    	}
	   	}, DELAY);
	}
	/**
	 * 
	 */
	public void scanActiveChange(){
		if (scanActive)
		{
			scanAreaChanged = true;
			scanActive = false;
		}
	}
	public void numberOfScans() {
		float startF = SettingsPanel.frequencyStartUserSelectedFloat;
		float endF = SettingsPanel.frequencyEndUserSelectedFloat;
		int densityI = SettingsPanel.frequencyDensityUserSelectedInt;
		
		System.err.println(startF);
		System.err.println(endF);
		System.err.println(densityI);
		
		if ( startF == endF)
		{
			frequency = new float[1];
			frequency[0] = startF;
		}
		else if (startF < endF && densityI == 1)
		{
			frequency = new float[2];
			frequency[0] = startF;
			frequency[1] = endF;
		}
		else
		{
			frequency = new float[densityI + 1];
			for (int i = 0; i <= densityI; i++) {
				frequency[i] = 	startF + i * ((endF - startF) / densityI);
			}
		}
	}
	public void restartScanValues() {
		setScanX(true);
		setScanY(true);
		x = 0;
		y = 0;
		setBuffImageAlpha();
	}
	
	
	/**
	 * 
	 * @param b
	 */
	public void setScanActive(boolean b) {
		// TODO Auto-generated method stub
	}
	/**
	 * 
	 * @return
	 */
	public boolean isScanX() {
		return scanX;
	}
	/**
	 * 
	 * @param scanX
	 */
	public void setScanX(boolean scanX) {
		this.scanX = scanX;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isScanY() {
		return scanY;
	}
	/**
	 * 
	 * @param scanY
	 */
	public void setScanY(boolean scanY) {
		this.scanY = scanY;
	}
}


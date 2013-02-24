package emcscanner.kth.se;

import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LANCZOS4;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class Scan {
	/* Array with frequency to be scanned */
	float[] frequency = null; 
	
	/* Positions taken from the table */
	private int MesurmentPosX = 10000;
	private int MesurmentPosY = 5000;
	private int ALPHA = 200;
	
	/* scan variables */
	private int hy;
	private int wx;
	private int nX;
	private int nY;
	private int sizeY;
	private int sizeX;

	/* Scanning position x and y */
	public int x = 0;
	public int y = 0;
	/* How much we have moved in Y and X from start */
	private int xMoved = 0;
	private int yMoved = 0;
	
	/* Boolean */
	public boolean scanAreaChanged = false;
	private boolean moveStartPosToMesurmentPosBoolean = false;
	public boolean scanActive = false;
	public boolean scanStoped = false;
	public boolean changeWay = false;
	private boolean moveFX;
	private boolean moveUY;
	private boolean scanX = true;
	private boolean scanY = true;
	public boolean pauseScanX = false;
	public boolean scanNeverStarted = true;
	
	/* Strings */
	protected static String DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION = "user data/frequency ";

	/* Color image is going to contain colors from scanner*/
	public IplImage colorImage;
	public BufferedImage buffImage;
	public BufferedImage rezicedBuffImage;
	
	/* File Creation */
	private FileOutputStream[] fileFrequencyOutputArray;
    private PrintStream[] fileFrequencyPrint;
    
	/**
	 * Calls the right methods and starts the scan 
	 */
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
		scanValues();
	}
	private void frequencyOutput(float frequency){
		/* There will be a function sending a string with the frequency that is to be scanned with */
		System.out.println("Frequency: " + frequency);
	}
	private int scanInput(int f){
		/* Function with an string input and then convert it to integer and return */
		/* We will get values between 100 and -100 i think */
		int convertedFromStringInput = (int) (Math.random() * 400 + 380 );
		
		fileFrequencyPrint[f].print(convertedFromStringInput + " ");
		return convertedFromStringInput;
	}
	
	private void moveMotorForwordX(int i){
		moveMotorForwordX(i, 1);
		/*try {
			Thread.sleep(83);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	private void moveMotorBackX(int i){
		moveMotorBackX(i, 1);
		/*try {
			Thread.sleep(75);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	/**
	 * 
	 * @param i
	 */
	private void moveMotorForwordX(int i, int n){
		/* function for moving the motors 1 step */
		for (int j = 0; j < n; j++)
			if (!moveStartPosToMesurmentPosBoolean)
			{
				xMoved += 1;  
				//	System.out.println("xMovedForword: " + xMoved);
				/*try {
					Thread.sleep(83);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
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
			/*try {
				Thread.sleep(83);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	/**
	 * 
	 * @param i
	 */
	private void moveMotorBackX(int i, int n){
		/* function for moving the motors 1 step */
		//System.out.println(" - X " + i);
		for (int j = 0; j < n; j++)
			if (!moveStartPosToMesurmentPosBoolean)
			{
				xMoved -= 1;
				//	System.out.println("xMovedBackword: " + xMoved);
				/*try {
					Thread.sleep(83);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
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
		/*	try {
				Thread.sleep(83);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
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
		Graphics2D g = buffImage.createGraphics();
		while (!scanStoped && scanY) {
			int colorX = 0;
			x = 0;
			setScanX(true);
			while (!scanStoped && scanX) {
				if (!pauseScanX)
				{
					for (int i = 0; i < frequency.length; i++) {
						frequencyOutput(frequency[i]);
						if (frequency.length == 1 && i == 0)
							colorX = wavelengthToColorConverter(scanInput(i));
						else if (frequency.length == 2 && i == 0)
							colorX = wavelengthToColorConverter(scanInput(i));
						else if (i == (int)((frequency.length / 2) - 1))
							colorX = wavelengthToColorConverter(scanInput(i));
					}
					g.setColor(new Color(colorX & 255, (colorX >> 8) & 255, (colorX >> 16) & 255, colorX >>> 24));
					if (moveFX ^ changeWay)
					{
						g.fillRect(x, moveUY ? hy - y - 1 : y, sizeX, sizeY);
						moveMotorBackX(x, sizeX);
					}
					else
					{
						g.fillRect(wx - x - 1, moveUY ? hy - y - 1 : y, sizeX, sizeY);
						moveMotorForwordX(x, sizeX);
					}
					
					if ((x > wx - sizeX && moveFX) || (wx - x < sizeX && !moveFX))
					{
						x = 0;
						scanX = false;
					}
					else
						x += sizeX;
					resizeAPaint();
				}
				else
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			
			if (moveUY)
			{
				for (int yi = 0; yi < sizeY; yi++)
					moveMotorUppY(this.y);
			}
			else
			{
				for (int yi = 0; yi < sizeY; yi++)
					moveMotorDownY(this.y);
			}
			changeWay = !changeWay;
			
			if (y > hy - sizeY)
				setScanY(false);

			for (int i = 0; i < frequency.length; i++)
				fileFrequencyPrint[i].println("");
			y += sizeY;
		}
		if (!scanStoped)
		{
			System.out.println("Area is scaned!");
			for (int i = 0; i < frequency.length; i++) {
				fileFrequencyPrint[i].flush();
				fileFrequencyPrint[i].close();
			}
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
	        IplImage ipl = IplImage.create(Program.imagePanel.newWidthPhoto, Program.imagePanel.newHeightPhoto, 8, 4);
	        
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
			sizeY = buffImage.getHeight() / nY;
			sizeX = buffImage.getWidth() / nX;
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
		colorImage = IplImage.create(SettingsPanel.CROPT_PHOTO_DIMENSION.width * Program.TIONDELS_MILLI_METER_PIXEL, SettingsPanel.CROPT_PHOTO_DIMENSION.height * Program.TIONDELS_MILLI_METER_PIXEL, 8/*SettingsPanel.photo.depth()*/, 4/*SettingsPanel.photo.nChannels()*/);
		buffImage = colorImage.getBufferedImage();
		buffImage.setRGB(0, 0, buffImage.getWidth(), buffImage.getHeight(), new int[buffImage.getWidth() * buffImage.getHeight()], 0, buffImage.getWidth());
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
		ScanSettingsSubPanel.startScanButton.setEnabled(false);
		ScanSettingsSubPanel.stopScanButton.setEnabled(true);
		ScanSettingsSubPanel.pauseScanButton.setEnabled(true);
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
		
		if ( startF == endF && densityI == 1)
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
			frequency = new float[densityI];
			for (int i = 0; i < densityI; i++) {
				frequency[i] = 	startF + i * ((endF - startF) / (densityI - 1));
			}
		}
		fileFrequencyOutputArray = new FileOutputStream[frequency.length];
		fileFrequencyPrint = new PrintStream[frequency.length];
		for (int f = 0; f < frequency.length; f++) {
			try {
				fileFrequencyOutputArray[f] = new FileOutputStream(DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION + SettingsPanel.FILE_NAME + " " + frequency[f] + ".txt", true);
				fileFrequencyPrint[f] = new PrintStream(fileFrequencyOutputArray[f]);
				
			} catch (IOException e) {
				 System.err.println ("Error writing to file");
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

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
	private int MesurmentPosX = 1000 * Program.TIONDELS_MILLI_METER_PIXEL;
	private int MesurmentPosY = 500 * Program.TIONDELS_MILLI_METER_PIXEL;
	private int ALPHA = 100;
	
	/* scan variables */
	private int hy;
	private int wx;
	private int nX;
	private int nY;
	private int sizeY;
	private int sizeX;
	public int frequencyShow;

	/* Scanning position x and y */
	public int x;
	public int y;
	/* How much we have moved in Y and X from start */
	private int xMoved;
	private int yMoved;
	
	/* Boolean */
	public boolean scanAreaChanged;
	private boolean moveStartPosToMesurmentPosBoolean;
	public boolean scanActive;
	private boolean scanStoped;
	public boolean changeWay;
	private boolean moveFX;
	private boolean moveUY;
	private boolean scanX;
	private boolean scanY;
	public boolean pauseScanX;
	public boolean scanNeverStarted;
	private boolean scanDone;
	public boolean headersInactive;
	
	/* Strings */
	protected static String DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION = "user data/frequency ";

	/* Color image is going to contain colors from scanner*/
	public IplImage colorImage;
	public BufferedImage[] buffImage;
	public BufferedImage rezicedBuffImage;
	
	/* File Creation */
	private FileOutputStream[] fileFrequencyOutputArray;
    private PrintStream[] fileFrequencyPrint;
    
    public Scan() {
    	/* Scanning position x and y */
    	x = 0;
    	y = 0;
    	/* How much we have moved in Y and X from start */
    	xMoved = 0;
    	yMoved = 0;

    	/* Boolean */
    	scanAreaChanged = false;
    	moveStartPosToMesurmentPosBoolean = false;
    	scanActive = false;
    	setScanStoped(false);
    	changeWay = false;
    	scanX = true;
    	scanY = true;
    	pauseScanX = false;
    	scanNeverStarted = true;
    	setScanDone(false);

    	setHeadersInactive(false);
    }
	/**
	 * Calls the right methods and starts the scan 
	 */
	public void startScan(){
		initializeScanValues();
		if (isScanStoped() && !scanAreaChanged)
		{
			if (yMoved > 0)
				for (int yi = 0; yi < yMoved; yi++)
					moveMotorDY(yi);
			else if (yMoved < 0)
				for (int yi = yMoved; yi < 0; yi++)
					moveMotorUY(yi);
			if (xMoved > 0)
				for (int xi = 0; xi < xMoved; xi++)
					moveMotorBX(xi);
			else if (xMoved < 0)
				for (int xi = xMoved; xi < 0; xi++)
					moveMotorFX(xi);
			
			setScanStoped(false);
		}
		else
		{
			setScanStoped(false);
			moveStartPosToMesurmentPos();
			moveToStartCeneter();
			scanAreaChanged = false;
		}
		scanValues();
	}
	/**
	 * 
	 * @param frequency
	 */
	private void frequencyOutput(float frequency){
		/* There will be a function sending a string with the frequency that is to be scanned with */
		//System.out.println("Frequency: " + frequency);
	}
	/**
	 * 
	 * @param f
	 * @return
	 */
	private int scanInput(int f){
		/* Function with an string input and then convert it to integer and return */
		/* We will get values between 100 and -100 i think */
		int convertedFromStringInput = (int) (Math.random() * 400 + 380 );
		
		fileFrequencyPrint[f].print(convertedFromStringInput + " ");
		return convertedFromStringInput;
	}
	
	private void doubleSleep(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void singleSleep(){
		try {
			Thread.sleep(83);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Function for moving the motors 1 step up and 1 step forward
	 */
	private void moveMotorFXUY(){
		//doubleSleep();
	}
	/**
	 * Function for moving the motors 1 step down and 1 step forward
	 */
	private void moveMotorFXDY(){
		//doubleSleep();
	}
	/**
	 * Function for moving the motors 1 step up and 1 step backward
	 */
	private void moveMotorBXUY(){
		//doubleSleep();
	}
	/**
	 * Function for moving the motors 1 step down and 1 step backward
	 */
	private void moveMotorBXDY(){
		//doubleSleep();
	}
	
	/**
	 * Function for moving the motors 1 step up Y
	 * @param i
	 */
	private void moveMotorUY(int i){
		if (!moveStartPosToMesurmentPosBoolean)
			yMoved += 1;
		//singleSleep();
	}
	/**
	 * Function for moving the motors 1 step down Y
	 * @param i
	 */
	private void moveMotorDY(int i){
		if (!moveStartPosToMesurmentPosBoolean)
			yMoved -= 1;
		//singleSleep();
	}
	/**
	 * @param i
	 */
	private void moveMotorFX(int i){
		moveMotorFX(i, 1);
	}
	/**
	 * Function for moving the motors 1 step forward X
	 * @param i
	 */
	private void moveMotorFX(int i, int n){
		for (int j = 0; j < n; j++)
			if (!moveStartPosToMesurmentPosBoolean)
				xMoved += 1;
		//singleSleep();
	}
	/**
	 * @param i
	 */
	private void moveMotorBX(int i){
		moveMotorBX(i, 1);
	}
	/**
	 * Function for moving the motors n steps back X
	 * @param i
	 */
	private void moveMotorBX(int i, int n){
		for (int j = 0; j < n; j++)
			if (!moveStartPosToMesurmentPosBoolean)
				xMoved -= 1;
		//singleSleep();
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
		
		int dEX = mPX - eX;
		int dSX = mPX - sX;
		int dEY = mPY - eY;
		int dSY = mPY - sY;
		int dY;
		int dX;
		int x = 0;
		int y = 0;	
		
		if ((Math.abs(dEX) < (Math.abs(dSX))))
		{
			moveFX = false;
			dX = dEX;
		}
		else
		{
			moveFX = true;
			dX = dSX;
		}
		if ((Math.abs(dEY) < (Math.abs(dSY))))
		{
			moveUY = true;
			dY = dEY;
		}
		else 
		{
			moveUY = false;
			dY = dSY;
		}
		boolean moveXStoped = false;
		boolean moveYStoped = false;
		while (!moveXStoped || !moveYStoped){
			if (x < Math.abs(dX) && y < Math.abs(dY))
			{
				if (moveFX && moveUY)
					moveMotorFXUY();
				else if (!moveFX && moveUY)
					moveMotorBXUY();
				else if (moveFX && !moveUY)
					moveMotorFXDY();
				else
					moveMotorBXDY();
				x++;
				y++;
			}
			else
			{
				if (x < Math.abs(dX))
				{
					if (moveFX)
						moveMotorFX(x);
					else
						moveMotorBX(x);
					x++;
				}
				else 
					moveXStoped = true;
				if (y < Math.abs(dY))
				{	
					if (moveUY)
						moveMotorUY(y);
					else
						moveMotorDY(y);
					y++;
				}
				else
					moveYStoped = true;
			}
		}
	}
	/**
	 * Scanning Values of each position and gives the user feedback, in the way of colors
	 */
	private void scanValues(){
		boolean startX = true;
		int dx = 0;
		int minXD = 0;
		int maxDX = (int)(wx / sizeX);;

		int dy = 0;
		int minYD = 0;
		int maxDY = (int)(hy / sizeY);
		
		
		/* scan the rest of the card algorithm */
		Graphics2D[] g = new Graphics2D[frequency.length];
		for (int i = 0; i < frequency.length; i++)
			g[i] = buffImage[i].createGraphics();
		
		if (moveUY)
			dy = maxDY - 1;
		else
			dy = minYD;
		
		if (moveFX)
			dx = minXD;
		else 
			dx = maxDX;
		
		while (!isScanStoped() && scanY) {
			int colorX = 0;
			setScanX(true);
			
			while (!isScanStoped() && scanX) {
				if (!pauseScanX)
				{
					for (int i = 0; i < frequency.length; i++) {
						frequencyOutput(frequency[i]);
						colorX = wavelengthToColorConverter(scanInput(i));
						g[i].setColor(new Color(colorX & 255, (colorX >> 8) & 255, (colorX >> 16) & 255, colorX >>> 24));
						
						if (moveFX ^ changeWay)
						{
							g[i].fillRect(dx * sizeX, dy * sizeY, sizeX, sizeY);			
							if (dx + 1 == maxDX)
							{
								g[i].fillRect((dx + 1) * sizeX, dy * sizeY, sizeX, sizeY);
								if (dy == maxDY - 1)
									g[i].fillRect((dx + 1) * sizeX, (dy + 1) * sizeY, sizeX, sizeY);
							}
								
							if (dy == maxDY - 1)
								g[i].fillRect(dx * sizeX, (dy + 1) * sizeY, sizeX, sizeY);
							
							moveMotorBX(dx*sizeX, sizeX * 10);
						}
						else
						{
							g[i].fillRect(dx * sizeX - sizeX, dy * sizeY, sizeX, sizeY);
							if (startX)
							{
								g[i].fillRect(dx * sizeX, dy * sizeY, sizeX, sizeY);
								if (dy == maxDY - 1)
									g[i].fillRect(dx * sizeX, (dy + 1) * sizeY, sizeX, sizeY);
							}

							if (dy == maxDY - 1)
								g[i].fillRect(dx * sizeX - sizeX, (dy + 1) * sizeY, sizeX, sizeY);
							
							moveMotorFX(dx * sizeX, sizeX * 10);
						}
					}
					if (moveFX ^ changeWay)
						dx += 1;
					else
						dx -= 1;
					
					if (moveFX ^ changeWay && dx == maxDX || dx == minXD) 
					{
						scanX = false;
						//d -= 1;
					}
					if (moveFX ^ changeWay && dx == maxDX || dx == minXD)
					{
						scanX = false;
						//d += 1;
					}
					startX = false;
				
					resizeAPaint();
				}
				else
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
			
			startX = true;
			
			if (moveUY)
				for (int yi = 0; yi < sizeY; yi++)
					moveMotorUY(dy * 10);
			else
				for (int yi = 0; yi < sizeY; yi++)
					moveMotorDY(dy * 10);
			
			changeWay = !changeWay;

			if (moveUY)
				dy -= 1;
			else
				dy += 1;
			
			if (moveUY && dy < minYD)
				setScanY(false);
			else if (!moveUY && dy > maxDY - 1)
				setScanY(false);
				
			
			
			for (int i = 0; i < frequency.length; i++)
				fileFrequencyPrint[i].println("");

		}
		if (!isScanStoped())
		{
			setScanDone(true);
			SettingsPanel.scanPanel.scanPanelActive();
			new CreatePdf();
			for (int i = 0; i < frequency.length; i++) {
				fileFrequencyPrint[i].flush();
				fileFrequencyPrint[i].close();
			}
			try {
				File outputfile = new File("webcam photo/Color" + SettingsPanel.FILE_NAME + ".png");
				ImageIO.write(buffImage[frequency.length/2], "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void setScanDone(boolean b) {
		scanDone = b;
	}
	public boolean getScanDone() {
		return scanDone;
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
	        
	        if (isScanStoped())
	        	setBuffImageAlpha();
	        	
	        cvResize(IplImage.createFrom(buffImage[frequency.length/2]), ipl, CV_INTER_LANCZOS4);
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
				moveMotorUY(y);
			else
				moveMotorDY(y);
		}
		for (int x = 0; x < sizeX / 2; x++)
		{
			if (moveFX)
				moveMotorFX(x);
			else
				moveMotorBX(x);
		}

		moveStartPosToMesurmentPosBoolean = false;
	}
	/**
	 * Gets the user selected values for the scan.
	 */
	private void initializeScanValues() {
		hy = buffImage[frequencyShow].getHeight();
		wx = buffImage[frequencyShow].getWidth();
		
		nX = SettingsPanel.numberOfStepsWidth;
		nY = SettingsPanel.numberOfStepsHeight;
		
		if (DensitySettingsSubPanel.inputStepBoolean)
		{
			sizeY = buffImage[frequencyShow].getHeight() / nY;
			sizeX = buffImage[frequencyShow].getWidth() / nX;
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
		buffImage = new BufferedImage[frequency.length];
		
		colorImage = IplImage.create(SettingsPanel.CROPT_PHOTO_DIMENSION.width * Program.TIONDELS_MILLI_METER_PIXEL, SettingsPanel.CROPT_PHOTO_DIMENSION.height * Program.TIONDELS_MILLI_METER_PIXEL, 8/*SettingsPanel.photo.depth()*/, 4/*SettingsPanel.photo.nChannels()*/);
		
		for (int i = 0; i < frequency.length; i++) { 
			buffImage[i] = colorImage.clone().getBufferedImage();
			buffImage[i].setRGB(0, 0, buffImage[i].getWidth(), buffImage[i].getHeight(), new int[buffImage[i].getWidth() * buffImage[i].getHeight()], 0, buffImage[i].getWidth());
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
		
		setHeadersInactive(true);
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
			frequencyShow = 0;
		}
		else if (startF < endF && densityI == 1)
		{
			frequency = new float[2];
			frequency[0] = startF;
			frequency[1] = endF;
			frequencyShow = 0;
		}
		else
		{
			frequency = new float[densityI];
			for (int i = 0; i < densityI; i++) {
				frequency[i] = 	startF + i * ((endF - startF) / (densityI - 1));
			}
			frequencyShow = densityI/2;
		}
		fileFrequencyOutputArray = new FileOutputStream[frequency.length];
		fileFrequencyPrint = new PrintStream[frequency.length];
		for (int f = 0; f < frequency.length; f++) {
			try {
				fileFrequencyOutputArray[f] = new FileOutputStream(DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION + SettingsPanel.FILE_NAME + " " + frequency[f] + ".txt", false);
				fileFrequencyPrint[f] = new PrintStream(fileFrequencyOutputArray[f]);
			} catch (IOException e) {
				 System.err.println ("Error writing to file");
			}
		}
	}
	/**
	 * When restarting the scan you need to restart these values
	 */
	public void restartScanValues() {
		setScanX(true);
		setScanY(true);
		x = 0;
		y = 0;
		setBuffImageAlpha();
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
	/**
	 * 
	 * @return
	 */
	public boolean isScanStoped() {
		return scanStoped;
	}
	/**
	 * 
	 * @param scanStoped
	 */
	public void setScanStoped(boolean scanStoped) {
		this.scanStoped = scanStoped;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isHeadersInactive() {
		return headersInactive;
	}
	/**
	 * 
	 * @param headersInactive
	 */
	public void setHeadersInactive(boolean headersInactive) {
		this.headersInactive = headersInactive;
	}
}

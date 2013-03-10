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
/**
 * 
 * @author Jonas
 *
 */
public class Scan {
	/* Array with frequency to be scanned */
	float[] frequency = null; 
	
	/* Positions taken from the table */
	private static int MesurmentPosX = 1000 * Program.TIONDELS_MILLI_METER_PIXEL;
	private static int MesurmentPosY = 500 * Program.TIONDELS_MILLI_METER_PIXEL;
	
	/* scan variables */
	private int hy;
	private int wx;
	private int nX;
	private int nY;
	private int sizeY;
	private int sizeX;

	/* Scanning position x and y */
	private int x;
	private int y;
	/* How much we have moved in Y and X from start */
	private int xMoved;
	private int yMoved;
	
	/* Boolean */
	private boolean scanAreaChanged;
	private boolean moveStartPosToMesurmentPosBoolean;
	private boolean scanActive;
	private boolean scanStoped;
	private boolean changeWay;
	private boolean moveFX;
	private boolean moveUY;
	private boolean scanX;
	private boolean scanY;
	private boolean pauseScanX;
	private boolean scanNeverStarted;
	private boolean scanDone;
	private boolean headersInactive;
	
	/* Strings */
	protected static String DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION = "user data/frequency ";

	/* Color image is going to contain colors from scanner*/
	public IplImage colorImage;
	private BufferedImage[] buffImage;
	private BufferedImage rezicedBuffImage;
	
	/* File Creation */
	private FileOutputStream[] fileFrequencyOutputArray;
    private PrintStream[] fileFrequencyPrint;
    /**
     * 
     */
    public Scan() {
    	/* Scanning position x and y */
    	setX(0);
    	setY(0);
    	/* How much we have moved in Y and X from start */
    	setxMoved(0);
    	setyMoved(0);

    	/* Boolean */
    	scanAreaChanged = false;
    	setMoveStartPosToMesurmentPosBoolean(false);
    	setScanActive(false);
    	setScanStoped(false);
    	setChangeWay(false);
    	scanX = true;
    	scanY = true;
    	setPauseScanX(false);
    	setScanNeverStarted(true);
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
			if (getyMoved() > 0)
				for (int yi = 0; yi < getyMoved(); yi++)
					Motor.moveMotorDY(yi);
			else if (getyMoved() < 0)
				for (int yi = getyMoved(); yi < 0; yi++)
					Motor.moveMotorUY(yi);
			if (getxMoved() > 0)
				for (int xi = 0; xi < getxMoved(); xi++)
					Motor.moveMotorBX(xi);
			else if (getxMoved() < 0)
				for (int xi = getxMoved(); xi < 0; xi++)
					Motor.moveMotorFX(xi);
			
			setScanStoped(false);
		}
		else
		{
			setScanStoped(false);
			StartPosition.moveStartPosToMesurmentPos();
			StartPosition.moveToStartCeneter();
			scanAreaChanged = false;
		}
		scanValues();
	}
	/**
	 * Scanning Values of each position and gives the user feedback, in the way of colors
	 */
	private void scanValues(){
		boolean startX = true;
		int dx = 0;
		int minXD = 0;
		int maxDX = (int)(wx / getSizeX());;

		int dy = 0;
		int minYD = 0;
		int maxDY = (int)(hy / getSizeY());
		
		
		/* scan the rest of the card algorithm */
		Graphics2D[] g = new Graphics2D[frequency.length];
		for (int i = 0; i < frequency.length; i++)
			g[i] = getBuffImage()[i].createGraphics();
		
		if (isMoveUY())
			dy = maxDY - 1;
		else
			dy = minYD;
		
		if (isMoveFX())
			dx = minXD;
		else 
			dx = maxDX;
		
		while (!isScanStoped() && scanY) {
			int colorX = 0;
			setScanX(true);
			
			while (!isScanStoped() && scanX) {
				if (!isPauseScanX())
				{
					for (int i = 0; i < frequency.length; i++) {
						FrequensyOutput.frequencyOutput(frequency[i]);
						colorX = WaveLengthToColorConverter.wavelengthToColorConverter(ScanInput.scanInput(i));
						g[i].setColor(new Color(colorX & 255, (colorX >> 8) & 255, (colorX >> 16) & 255, colorX >>> 24));
						
						if (isMoveFX() ^ isChangeWay())
						{
							g[i].fillRect(dx * getSizeX(), dy * getSizeY(), getSizeX(), getSizeY());			
							if (dx + 1 == maxDX)
							{
								g[i].fillRect((dx + 1) * getSizeX(), dy * getSizeY(), getSizeX(), getSizeY());
								if (dy == maxDY - 1)
									g[i].fillRect((dx + 1) * getSizeX(), (dy + 1) * getSizeY(), getSizeX(), getSizeY());
							}
								
							if (dy == maxDY - 1)
								g[i].fillRect(dx * getSizeX(), (dy + 1) * getSizeY(), getSizeX(), getSizeY());
							
							Motor.moveMotorBX(dx*getSizeX(), getSizeX() * 10);
						}
						else
						{
							g[i].fillRect(dx * getSizeX() - getSizeX(), dy * getSizeY(), getSizeX(), getSizeY());
							if (startX)
							{
								g[i].fillRect(dx * getSizeX(), dy * getSizeY(), getSizeX(), getSizeY());
								if (dy == maxDY - 1)
									g[i].fillRect(dx * getSizeX(), (dy + 1) * getSizeY(), getSizeX(), getSizeY());
							}

							if (dy == maxDY - 1)
								g[i].fillRect(dx * getSizeX() - getSizeX(), (dy + 1) * getSizeY(), getSizeX(), getSizeY());
							
							Motor.moveMotorFX(dx * getSizeX(), getSizeX() * 10);
						}
					}
					if (isMoveFX() ^ isChangeWay())
						dx += 1;
					else
						dx -= 1;
					
					if (isMoveFX() ^ isChangeWay() && dx == maxDX || dx == minXD) 
					{
						scanX = false;
						//d -= 1;
					}
					if (isMoveFX() ^ isChangeWay() && dx == maxDX || dx == minXD)
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
			
			if (isMoveUY())
				for (int yi = 0; yi < getSizeY(); yi++)
					Motor.moveMotorUY(dy * 10);
			else
				for (int yi = 0; yi < getSizeY(); yi++)
					Motor.moveMotorDY(dy * 10);
			
			setChangeWay(!isChangeWay());

			if (isMoveUY())
				dy -= 1;
			else
				dy += 1;
			
			if (isMoveUY() && dy < minYD)
				setScanY(false);
			else if (!isMoveUY() && dy > maxDY - 1)
				setScanY(false);
				
			for (int i = 0; i < frequency.length; i++)
				getFileFrequencyPrint()[i].println("");

		}
		if (!isScanStoped())
		{
			setScanDone(true);
			SetPanelActive.scanPanelActive();
			new CreatePdf();
			for (int i = 0; i < frequency.length; i++) {
				getFileFrequencyPrint()[i].flush();
				getFileFrequencyPrint()[i].close();
			}
			try {
				File outputfile = new File("webcam photo/Color" + SettingsPanel.getFILE_NAME() + ".png");
				ImageIO.write(getBuffImage()[frequency.length/2], "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Resizes the image with the in scanned colors.
	 */
	public void resizeAPaint() {
		if (!isScanNeverStarted()) 
		{
	        IplImage ipl = IplImage.create(Program.imagePanel.newWidthPhoto, Program.imagePanel.newHeightPhoto, 8, 4);
	        
	        if (isScanStoped())
	        	SetImageAlpha.setBuffImageAlpha();
	        	
	        cvResize(IplImage.createFrom(getBuffImage()[frequency.length/2]), ipl, CV_INTER_LANCZOS4);
	        setRezicedBuffImage(ipl.getBufferedImage());
			        
			Program.frame.glass.repaint();
		}
	}
	/**
	 * Gets the user selected values for the scan.
	 */
	private void initializeScanValues() {
		hy = getBuffImage()[frequency.length/2].getHeight();
		wx = getBuffImage()[frequency.length/2].getWidth();
		
		nX = SettingsPanel.getNumberOfStepsWidth();
		nY = SettingsPanel.getNumberOfStepsHeight();
		
		if (DensitySettingsSubPanel.inputStepBoolean)
		{
			setSizeY(getBuffImage()[frequency.length/2].getHeight() / nY);
			setSizeX(getBuffImage()[frequency.length/2].getWidth() / nX);
		}
		else
		{
			setSizeY(SettingsPanel.getStepSizeHeight());
			setSizeX(SettingsPanel.getStepSizeWidth());
		}
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
		if (isScanActive())
		{
			scanAreaChanged = true;
			setScanActive(false);
		}
	}
	/**
	 * Sets The number of scans needed.
	 */
	public void numberOfScans() {
		float startF = SettingsPanel.getFrequencyStartUserSelectedFloat();
		float endF = SettingsPanel.getFrequencyEndUserSelectedFloat();
		int densityI = SettingsPanel.getFrequencyDensityUserSelectedInt();
		
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
		setFileFrequencyPrint(new PrintStream[frequency.length]);
		for (int f = 0; f < frequency.length; f++) {
			try {
				fileFrequencyOutputArray[f] = new FileOutputStream(DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION + SettingsPanel.getFILE_NAME() + " " + frequency[f] + ".txt", false);
				getFileFrequencyPrint()[f] = new PrintStream(fileFrequencyOutputArray[f]);
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
		setX(0);
		setY(0);
		SetImageAlpha.setBuffImageAlpha();
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
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}
	/**
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isPauseScanX() {
		return pauseScanX;
	}
	/**
	 * 
	 * @param pauseScanX
	 */
	public void setPauseScanX(boolean pauseScanX) {
		this.pauseScanX = pauseScanX;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isChangeWay() {
		return changeWay;
	}
	/**
	 * 
	 * @param changeWay
	 */
	public void setChangeWay(boolean changeWay) {
		this.changeWay = changeWay;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isScanActive() {
		return scanActive;
	}
	/**
	 * 
	 * @param scanActive
	 */
	public void setScanActive(boolean scanActive) {
		this.scanActive = scanActive;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isScanNeverStarted() {
		return scanNeverStarted;
	}
	/**
	 * 
	 * @param scanNeverStarted
	 */
	public void setScanNeverStarted(boolean scanNeverStarted) {
		this.scanNeverStarted = scanNeverStarted;
	}
	/**
	 * 
	 * @return
	 */
	public BufferedImage getRezicedBuffImage() {
		return rezicedBuffImage;
	}
	/**
	 * 
	 * @param rezicedBuffImage
	 */
	public void setRezicedBuffImage(BufferedImage rezicedBuffImage) {
		this.rezicedBuffImage = rezicedBuffImage;
	}
	/**
	 * 
	 * @return
	 */
	public BufferedImage[] getBuffImage() {
		return buffImage;
	}
	/**
	 * 
	 * @param buffImage
	 */
	public void setBuffImage(BufferedImage[] buffImage) {
		this.buffImage = buffImage;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isMoveStartPosToMesurmentPosBoolean() {
		return moveStartPosToMesurmentPosBoolean;
	}
	/**
	 * 
	 * @param moveStartPosToMesurmentPosBoolean
	 */
	public void setMoveStartPosToMesurmentPosBoolean(
			boolean moveStartPosToMesurmentPosBoolean) {
		this.moveStartPosToMesurmentPosBoolean = moveStartPosToMesurmentPosBoolean;
	}
	/**
	 * 
	 * @return
	 */
	public int getyMoved() {
		return yMoved;
	}
	/**
	 * 
	 * @param yMoved
	 */
	public void setyMoved(int yMoved) {
		this.yMoved = yMoved;
	}
	/**
	 * 
	 * @return
	 */
	public int getxMoved() {
		return xMoved;
	}
	/**
	 * 
	 * @param xMoved
	 */
	public void setxMoved(int xMoved) {
		this.xMoved = xMoved;
	}
	/**
	 * 
	 * @return
	 */
	public static int getMesurmentPosX() {
		return MesurmentPosX;
	}
	/**
	 * 
	 * @param mesurmentPosX
	 */
	public void setMesurmentPosX(int mesurmentPosX) {
		MesurmentPosX = mesurmentPosX;
	}
	/**
	 * 
	 * @return
	 */
	public static int getMesurmentPosY() {
		return MesurmentPosY;
	}
	/**
	 * 
	 * @param mesurmentPosY
	 */
	public void setMesurmentPosY(int mesurmentPosY) {
		MesurmentPosY = mesurmentPosY;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isMoveFX() {
		return moveFX;
	}
	/**
	 * 
	 * @param moveFX
	 */
	public void setMoveFX(boolean moveFX) {
		this.moveFX = moveFX;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isMoveUY() {
		return moveUY;
	}
	/**
	 * 
	 * @param moveUY
	 */
	public void setMoveUY(boolean moveUY) {
		this.moveUY = moveUY;
	}
	/**
	 * 
	 * @param b
	 */
	public void setScanDone(boolean b) {
		scanDone = b;
	}
	/**
	 * 
	 * @return
	 */
	public boolean getScanDone() {
		return scanDone;
	}
	/**
	 * 
	 * @return
	 */
	public int getSizeY() {
		return sizeY;
	}
	/**
	 * 
	 * @param sizeY
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	/**
	 * 
	 * @return
	 */
	public int getSizeX() {
		return sizeX;
	}
	/**
	 * 
	 * @param sizeX
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	/**
	 * 
	 * @return
	 */
	public PrintStream[] getFileFrequencyPrint() {
		return fileFrequencyPrint;
	}
	/**
	 * 
	 * @param fileFrequencyPrint
	 */
	public void setFileFrequencyPrint(PrintStream[] fileFrequencyPrint) {
		this.fileFrequencyPrint = fileFrequencyPrint;
	}
}

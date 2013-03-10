package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jonas
 *
 */
public class ScanSettingsSubPanel extends JPanel {
	/**
	 * Scan Settings Sub Panel ID
	 */
	private static final long serialVersionUID = 481270079447897262L;

	public static int STAGE = 5;
	
	/* Scan */
	public Scan scan = new Scan();
	
	/* Boolean */
	public static boolean HEADER_BUTTON_ENABLED = false;
	
	/* Strings */
	public String HEADER_BUTTON_TOOL_TIP_TEXT 	= "Press to scan ";
	
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
	
	/* Buttons */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	public static ScanButtons startScanButton = new ScanButtons("START");
	public static ScanButtons pauseScanButton = new ScanButtons("PAUSE");
	public static ScanButtons stopScanButton = new ScanButtons("STOP");
	public static ScanButtons rescanButton = new ScanButtons("RESCAN");
	public static ScanButtons saveButton = new ScanButtons("SAVE");
	
	/* Labels */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	
	private JLabel CALCULATED_TIME_LEFT_LABEL = new JLabel("<html><p align=center>Calculated time left:</p></html>");
	private JLabel timeLabel = new JLabel(SCAN_DONE);
	
	private JLabel STREAMING_PROCESS_LABEL = new JLabel("<html><p align=center>Streming process to <br> www.___.kth.se</p></html>");
	
	private JLabel CurrentLevelLabel = new JLabel("<html><p align=center>&nbsp Current level<br> " +
												"&nbsp [dBm]</p></html>");
	
	/* Dimensions */
	private Dimension THIS_MINIMUM_DIMENSION 					= new Dimension(400, 100);
	private Dimension HEADER_BUTTON_DIMENSION 					= new Dimension(355, 40);
	private Dimension STEP_LABEL_DIMENSION 						= new Dimension(50,40);
	
	private Dimension STEP_CONTINER_DIMENSION_ACTIVE 			= new Dimension(50, 240);
	private Dimension STEP_CONTINER_DIMENSION_DONE 				= new Dimension(50, 80);
	private Dimension STEP_CONTINER_DIMENSION_OFF 				= new Dimension(50, 40);
	
	private Dimension SCAN_PANEL_DIMENSION_ACTIVE 				= new Dimension(322, 200);
	private Dimension SCAN_DENSITY_PANEL_DIMENSION_DONE 		= new Dimension(322, 40);
	private Dimension SCAN_PANEL_DIMENSION_OFF 					= new Dimension(322, 40);
	
	private Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE= new Dimension(322, 240);
	private Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE 	= new Dimension(322, 80);
	private Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF 	= new Dimension(322, 40);
	
	/**
	 * 
	 */
	public ScanSettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);
		
		/* Creates a Label for the step numbers. */
		stepLabel.setPreferredSize(STEP_LABEL_DIMENSION);
		stepContiner.setPreferredSize(getSTEP_CONTINER_DIMENSION_OFF());
		/* Panel for the frequency input */
		scanPanel.setPreferredSize(getSCAN_PANEL_DIMENSION_OFF());
		currentLevelNorthContainer.setPreferredSize(new Dimension(160, 40)); // fixa 
		currentLevelContainer.setPreferredSize(new Dimension(160, 40));// fixa 
		
		getStreamingContinaer().setPreferredSize(new Dimension(140, 60));// fixa 
		getTimeNorthPanel().setPreferredSize(new Dimension(140, 60));// fixa 
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(getHEADER_AND_PANEL_CONTINER_DIMENSION_OFF());

		buttonContiner1.setBackground(Color.WHITE);
		buttonContiner2.setBackground(Color.WHITE);
		scanPanel.setBackground(Color.WHITE);
		getInputFeildsAButtons().setBackground(Color.WHITE);
		currentLevelContainer.setBackground(Color.WHITE);
		centerTimeStremContainer.setBackground(Color.WHITE);
		currentTimeStreamContainer.setBackground(Color.WHITE);
		streamingPanel.setBackground(Color.WHITE);
		currentLevelNorthContainer.setBackground(Color.WHITE);
		timePanel.setBackground(Color.WHITE);

		getTimeNorthPanel().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
		getTimeNorthCeneterPanel().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
		getTimeNorthLablePanel().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR1);
		
		getStreamingContinaer().setBackground(Program.LIGHT_BLUE_ALPHA_COLOR);
		
		getTimeNorthPanel().setBorder(Program.LIGHT_BLUE_BORDER);
		getStreamingContinaer().setBorder(Program.LIGHT_BLUE_BORDER);

		/* Setting containers Layouts for the right GUI look. */
		buttonContiner1.add(stopScanButton, BorderLayout.WEST);
		buttonContiner1.add(pauseScanButton, BorderLayout.CENTER);
		buttonContiner1.add(startScanButton, BorderLayout.EAST);
		
		buttonContiner2.add(rescanButton, BorderLayout.WEST);
		buttonContiner2.add(saveButton, BorderLayout.EAST);
		
		currentLevelContainer.add(CurrentLevelLabel);
		
		currentLevelNorthContainer.add(currentLevelContainer, BorderLayout.NORTH);
		currentTimeStreamContainer.add(currentLevelNorthContainer, BorderLayout.WEST);
		
		getTimeNorthLablePanel().add(timeLabel);
		
		getTimeNorthCeneterPanel().add(CALCULATED_TIME_LEFT_LABEL, BorderLayout.NORTH);
		getTimeNorthCeneterPanel().add(getTimeNorthLablePanel(), BorderLayout.SOUTH);

		getTimeNorthPanel().add(getTimeNorthCeneterPanel(), BorderLayout.SOUTH);
		timePanel.add(getTimeNorthPanel());

		getStreamingContinaer().add(STREAMING_PROCESS_LABEL);
		streamingPanel.add(getStreamingContinaer());
		
		centerTimeStremContainer.add(timePanel, BorderLayout.CENTER);
		centerTimeStremContainer.add(streamingPanel, BorderLayout.NORTH);
		currentTimeStreamContainer.add(centerTimeStremContainer, BorderLayout.CENTER);
        
		scanPanel.add(getInputFeildsAButtons(), BorderLayout.SOUTH);
		scanPanel.add(currentTimeStreamContainer, BorderLayout.CENTER);
		
		headerAndPanelContiner.add(scanPanel, BorderLayout.SOUTH);

		stepContiner.add(stepLabel, BorderLayout.NORTH );
		this.add(stepContiner, BorderLayout.WEST);
		this.add(headerAndPanelContiner);

		scanPanel.setVisible(false);
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getTimeNorthPanel() {
		return timeNorthPanel;
	}
	/**
	 * 
	 * @param timeNorthPanel
	 */
	public void setTimeNorthPanel(JPanel timeNorthPanel) {
		this.timeNorthPanel = timeNorthPanel;
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getStreamingContinaer() {
		return streamingContinaer;
	}
	/**
	 * 
	 * @param streamingContinaer
	 */
	public void setStreamingContinaer(JPanel streamingContinaer) {
		this.streamingContinaer = streamingContinaer;
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getInputFeildsAButtons() {
		return inputFeildsAButtons;
	}
	/**
	 * 
	 * @param inputFeildsAButtons
	 */
	public void setInputFeildsAButtons(JPanel inputFeildsAButtons) {
		this.inputFeildsAButtons = inputFeildsAButtons;
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getTimeNorthCeneterPanel() {
		return timeNorthCeneterPanel;
	}
	/**
	 * 
	 * @param timeNorthCeneterPanel
	 */
	public void setTimeNorthCeneterPanel(JPanel timeNorthCeneterPanel) {
		this.timeNorthCeneterPanel = timeNorthCeneterPanel;
	}
	/**
	 * 
	 * @return
	 */
	public JPanel getTimeNorthLablePanel() {
		return timeNorthLablePanel;
	}
	/**
	 * 
	 * @param timeNorthLablePanel
	 */
	public void setTimeNorthLablePanel(JPanel timeNorthLablePanel) {
		this.timeNorthLablePanel = timeNorthLablePanel;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_BUTTON_DIMENSION() {
		return HEADER_BUTTON_DIMENSION;
	}
	/**
	 * 
	 * @param hEADER_BUTTON_DIMENSION
	 */
	public void setHEADER_BUTTON_DIMENSION(Dimension hEADER_BUTTON_DIMENSION) {
		HEADER_BUTTON_DIMENSION = hEADER_BUTTON_DIMENSION;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSTEP_CONTINER_DIMENSION_ACTIVE() {
		return STEP_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @param sTEP_CONTINER_DIMENSION_ACTIVE
	 */
	public void setSTEP_CONTINER_DIMENSION_ACTIVE(
			Dimension sTEP_CONTINER_DIMENSION_ACTIVE) {
		STEP_CONTINER_DIMENSION_ACTIVE = sTEP_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSTEP_CONTINER_DIMENSION_DONE() {
		return STEP_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @param sTEP_CONTINER_DIMENSION_DONE
	 */
	public void setSTEP_CONTINER_DIMENSION_DONE(
			Dimension sTEP_CONTINER_DIMENSION_DONE) {
		STEP_CONTINER_DIMENSION_DONE = sTEP_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSCAN_DENSITY_PANEL_DIMENSION_DONE() {
		return SCAN_DENSITY_PANEL_DIMENSION_DONE;
	}
	/**
	 * 
	 * @param sCAN_DENSITY_PANEL_DIMENSION_DONE
	 */
	public void setSCAN_DENSITY_PANEL_DIMENSION_DONE(
			Dimension sCAN_DENSITY_PANEL_DIMENSION_DONE) {
		SCAN_DENSITY_PANEL_DIMENSION_DONE = sCAN_DENSITY_PANEL_DIMENSION_DONE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSCAN_PANEL_DIMENSION_ACTIVE() {
		return SCAN_PANEL_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @param sCAN_PANEL_DIMENSION_ACTIVE
	 */
	public void setSCAN_PANEL_DIMENSION_ACTIVE(
			Dimension sCAN_PANEL_DIMENSION_ACTIVE) {
		SCAN_PANEL_DIMENSION_ACTIVE = sCAN_PANEL_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE() {
		return HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @param hEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE
	 */
	public void setHEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE(
			Dimension hEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE) {
		HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = hEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_AND_PANEL_CONTINER_DIMENSION_DONE() {
		return HEADER_AND_PANEL_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @param hEADER_AND_PANEL_CONTINER_DIMENSION_DONE
	 */
	public void setHEADER_AND_PANEL_CONTINER_DIMENSION_DONE(
			Dimension hEADER_AND_PANEL_CONTINER_DIMENSION_DONE) {
		HEADER_AND_PANEL_CONTINER_DIMENSION_DONE = hEADER_AND_PANEL_CONTINER_DIMENSION_DONE;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSCAN_PANEL_DIMENSION_OFF() {
		return SCAN_PANEL_DIMENSION_OFF;
	}
	/**
	 * 
	 * @param sCAN_PANEL_DIMENSION_OFF
	 */
	public void setSCAN_PANEL_DIMENSION_OFF(Dimension sCAN_PANEL_DIMENSION_OFF) {
		SCAN_PANEL_DIMENSION_OFF = sCAN_PANEL_DIMENSION_OFF;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getHEADER_AND_PANEL_CONTINER_DIMENSION_OFF() {
		return HEADER_AND_PANEL_CONTINER_DIMENSION_OFF;
	}
	/**
	 * 
	 * @param hEADER_AND_PANEL_CONTINER_DIMENSION_OFF
	 */
	public void setHEADER_AND_PANEL_CONTINER_DIMENSION_OFF(
			Dimension hEADER_AND_PANEL_CONTINER_DIMENSION_OFF) {
		HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = hEADER_AND_PANEL_CONTINER_DIMENSION_OFF;
	}
	/**
	 * 
	 * @return
	 */
	public Dimension getSTEP_CONTINER_DIMENSION_OFF() {
		return STEP_CONTINER_DIMENSION_OFF;
	}
	/**
	 * 
	 * @param sTEP_CONTINER_DIMENSION_OFF
	 */
	public void setSTEP_CONTINER_DIMENSION_OFF(
			Dimension sTEP_CONTINER_DIMENSION_OFF) {
		STEP_CONTINER_DIMENSION_OFF = sTEP_CONTINER_DIMENSION_OFF;
	}
}


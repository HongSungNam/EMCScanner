package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * @author Jonas
 *
 */
public class FrequencySettingsSubPanel extends JPanel {
	/**
	 * Frequency Settings Sub Panel ID
	 */
	private static final long serialVersionUID = 5772427918605539001L;

	/** 
	 * Threads for displaying the frequency help video. 
	 */
	public static Thread DisplayFrequensyVideoThread;

	/** 
	 * The buffered received from the camera. 
	 */
	public static BufferedImage buffImg = null;
	/** 
	 * The color panel is a panel where we show the video for the frequency help video
	 */
	public static ColorPanel colorFrequensyVideoPanel = new ColorPanel(buffImg);
	
	/* Constants for the FrequencyPanel */
	
	public int densityStartValue = 0;
	public int densityEndValue = 1;
	
	public static int STAGE = 1;
	
	/* Boolean */
	/* Display video*/
	public static boolean DISPLAY_VIDEO = true;
	public static boolean DISPLAY_HELP_VIDEO = true;
	
	/* other Booleans */
	public static boolean WRONG_FLOAT_INPUT = false;
	public static boolean NEXT_BUTTON_ENABLED = false;
	
	public static boolean START_FREQUENSY_SELECTED = false;
	public static boolean DENSITY_FREQUENSY_SELECTED = false;
	public static boolean END_FREQUENCY_SELECTED = false;
	
	/* Float */
	public float startValue = 0.1f;
	public float endValue = 0;
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel(new BorderLayout());
	public JPanel headerAndPanelContiner = new JPanel(new BorderLayout());
	public JPanel frequencyPanel = new JPanel(new BorderLayout());
	
	/* Containers for setting up GUI */
	public JPanel continer1 = 	new JPanel(new BorderLayout());
	public JPanel continer2 = 	new JPanel(new BorderLayout());
	public JPanel startTextFeildContainer = new JPanel(new FlowLayout());
	public JPanel endFrequensyContainer = new JPanel();
	public JPanel endTextFeildContainer = new JPanel();
	public JPanel continer4 = new JPanel(new FlowLayout());
	public JPanel continer5 = new JPanel();
	public JPanel continer6 = new JPanel(new BorderLayout());
	public JPanel startFrequensyContainer = new JPanel(new BorderLayout());
	public JPanel densityContainer = new JPanel(new FlowLayout());
	public JPanel densityEndFrequensyContainer = new JPanel(new BorderLayout());
	public JPanel frequencySummeryPanel = new JPanel(new BorderLayout());
	public JPanel frequencySummeryContainer = new JPanel();
	
	/* Buttons */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	public static NextButton nextButton = new NextButton(STAGE);
	public static BackButton backButton = new BackButton(STAGE);
	
	/* JTextField */
	public FrequencyTextField startFloatInputTextField = new FrequencyTextField(1);
	public FrequencyTextField endFloatInputTextField = new FrequencyTextField(2);
	public FrequencyTextField densityIntInputTextField = new FrequencyTextField(3);
	
	/* String */
	private String stepGrayText = "<html> <font color = rgb(120,120,120)>Step 1/4</font></html>";
	private String stepLightBlueText  = "<html> <font color = rgb(100,150,255)>Step 1/4</font></html>";
	private String stepTextDarkGreen  = "<html> <font color = rgb(120,200,40)>Step 1/4</font></html>";
	
	private static String panelInformation = "This is where you can shoose the desired frequency.";
	
	private String headerButtonToolTipText = "Press to reselect The frequency";
	
	private String moreThenNormalText = "<html>0.1 ≤ </html>";
	private String lessThenNormalText = "<html><font color = rgb(100,150,255)>MHz</font>"+" ≤ 6000</html>";
	
	private String endMoreThenLightGrayText = "<html><font color = rgb(110,110,110)>" + startValue + " ≤</font></html>";
	private String lessThenLightGrayText = "<html><font color = rgb(110,110,110)>MHz ≤ 6000</font></html>";
	
	private String moreThenRedText = "<html><font color = rgb(255,0,0)>0.1 ≤</font></html>";
	private String lessThenRedText = "<html><font color = rgb(100,150,255)>MHz</font>"+"<font color = rgb(255,0,0)> ≤ 6000</font></html>";
	
	private String startFrequencyLightBlueText = "<html><font color = rgb(100,150,255)>Start frequency: </font></html>";
	private String startFrequencyRedText = "<html><font color = rgb(255,0,0)>Start frequency: </font></html>";
	
	private String endFrequencyLightGrayText = "<html><font color = rgb(120,120,120)>End frequency: </font></html>";
	private String endFrequencyLightBlueText = "<html><font color = rgb(100,150,255)>End frequency: </font></html>";
	private String endFrequencyRedText = "<html><font color = rgb(255,0,0)>End frequency: </font></html>";
	
	private String densityFrequencyLightGrayText = "<html><font color = rgb(120,120,120)>Frequency density:</font></html>";
	private String densityFrequencyLightBlueText = "<html><font color = rgb(100,150,255)>Frequency density:</font></html>";
	private String densityFrequencyRedText = "<html><font color = rgb(255,0,0)>Frequency density:</font></html>";
	
	private String densityMoreThenText = "<html><font color = rgb(110,110,110)> 1 ≤</font></html>";
	private String densityLessThenText = "<html><font color = rgb(110,110,110)> ≤ </font></html>";
	
	/* JLabel */
	private JLabel stepLabel 			= new JLabel(getStepLightBlueText());
	
	private JLabel startMoreThenLabel	= new JLabel(getMoreThenNormalText());
	private JLabel startLessThenLabel 	= new JLabel(getLessThenNormalText());
	
	private JLabel endMoreThenLabel		= new JLabel(endMoreThenLightGrayText);
	private JLabel endLessThenLabel 	= new JLabel(getLessThenLightGrayText());
	
	private JLabel densityMoreThenLabel	= new JLabel(densityMoreThenText);
	private JLabel densityLessThenLabel	= new JLabel(densityLessThenText);
	
	private JLabel frequencyLabel 		= new JLabel();
	private JLabel frequencyLabelStart 	= new JLabel();
	private JLabel frequencyLabelEnd 	= new JLabel();
	private JLabel frequencyLabelDensity= new JLabel();
	
	private JLabel startFrequensyLabel 	= new JLabel(startFrequencyLightBlueText);
	private JLabel endFrequensyLabel 	= new JLabel(getEndFrequencyLightGrayText());
	private JLabel densityFrequensyLabel= new JLabel(getDensityFrequencyLightGrayText());
		
	/* Dimensions */	
	public Dimension COLOR_PANEL_DIMENSION = new Dimension(322, 180);
	
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 380);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 100);
	
	public Dimension FREQUENCY_PANEL_DIMENSION_ACTIVE = new Dimension(322, 340);
	public Dimension FREQUENCY_PANEL_DIMENSION_OFF = new Dimension(322, 60);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 380);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 100);
	
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension NEXT_BUTTON_DIMENSION = new Dimension(90, 50);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	/**
	 * 
	 */
	public FrequencySettingsSubPanel(){
		this.setLayout(new FlowLayout());
		this.setMinimumSize(SettingsPanel.SUB_PANEL_MINIMUM_DIMENSION);		

		colorFrequensyVideoPanel.setVisible(true);
		colorFrequensyVideoPanel.setPreferredSize(COLOR_PANEL_DIMENSION);

		/* Creates a Label for the step numbers. */
		getStepLabel().setPreferredSize(STEP_LABEL_DIMENSION);
		getStepLabel().setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		stepContiner.add(getStepLabel(), BorderLayout.NORTH );
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		this.add(stepContiner, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.add(headerButton, BorderLayout.NORTH );
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		
		/* Panel for the frequency input */
		frequencyPanel.setPreferredSize(FREQUENCY_PANEL_DIMENSION_ACTIVE);
		
		/* Boundary explanatory labels */
		getStartMoreThenLabel().setHorizontalAlignment(SwingConstants.CENTER);
		getStartLessThenLabel().setHorizontalAlignment(SwingConstants.CENTER);
		
		DisplayFrequensyVideoThread = new FrameGrabberThread(STAGE,"threadDisplay");
        DisplayFrequensyVideoThread.setDaemon(true);
        DisplayFrequensyVideoThread.start();
        		
		/* Sets container backgrounds to white instead of gray for contrast */
		continer1.setBackground(Color.WHITE);
		continer2.setBackground(Color.WHITE);
		continer4.setBackground(Color.WHITE);
		continer6.setBackground(Color.WHITE);
		continer5.setBackground(Color.WHITE);
		colorFrequensyVideoPanel.setBackground(Color.WHITE);
		densityEndFrequensyContainer.setBackground(Color.WHITE);
		densityContainer.setBackground(Color.WHITE);
		endTextFeildContainer.setBackground(Color.WHITE);
		endFrequensyContainer.setBackground(Color.WHITE);
		frequencyPanel.setBackground(Color.WHITE);
		frequencySummeryPanel.setBackground(Color.WHITE);
		frequencySummeryContainer.setBackground(Color.WHITE);
		startTextFeildContainer.setBackground(Color.WHITE);
		startFrequensyContainer.setBackground(Color.WHITE);
		startFrequensyContainer.setBackground(Color.WHITE);
		
		/* Containers for GUI look */
		continer2.add(colorFrequensyVideoPanel, BorderLayout.NORTH);

		continer1.add(nextButton, BorderLayout.EAST);
		continer1.add(backButton, BorderLayout.WEST);
		
		startTextFeildContainer.add(startFloatInputTextField, BorderLayout.CENTER);
		endTextFeildContainer.add(endFloatInputTextField, BorderLayout.CENTER);
		
		startFrequensyContainer.add(startFrequensyLabel, BorderLayout.WEST);
		startFrequensyContainer.add(getStartMoreThenLabel(), BorderLayout.EAST);
		
		continer4.add(startFrequensyContainer, FlowLayout.LEFT);
		continer4.add(startTextFeildContainer, FlowLayout.CENTER); 
		continer4.add(getStartLessThenLabel(), FlowLayout.RIGHT);
		
		endFrequensyContainer.add(getEndFrequensyLabel(), BorderLayout.CENTER);
		endFrequensyContainer.add(getEndMoreThenLabel(), BorderLayout.EAST);
		
		continer5.add(endFrequensyContainer, FlowLayout.LEFT);
		continer5.add(endTextFeildContainer, FlowLayout.CENTER); 
		continer5.add(getEndLessThenLabel(), FlowLayout.RIGHT);
		
		densityContainer.add(getDensityFrequensyLabel());
		densityContainer.add(getDensityMoreThenLabel());
		densityContainer.add(densityIntInputTextField);
		densityContainer.add(getDensityLessThenLabel());
		
		densityEndFrequensyContainer.add(continer5, BorderLayout.NORTH);
		densityEndFrequensyContainer.add(densityContainer, BorderLayout.SOUTH);
		
		continer6.add(continer2, BorderLayout.NORTH);
		continer6.add(continer4, BorderLayout.CENTER);
		continer6.add(densityEndFrequensyContainer, BorderLayout.SOUTH);
		
		frequencyPanel.add(continer6, BorderLayout.NORTH);
		frequencyPanel.add(continer1, BorderLayout.SOUTH);
		
		frequencyPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		frequencySummeryPanel.add(getFrequencyLabelStart(), BorderLayout.NORTH);
		frequencySummeryPanel.add(getFrequencyLabelEnd(), BorderLayout.CENTER);
		frequencySummeryPanel.add(getFrequencyLabelDensity(), BorderLayout.SOUTH);

		frequencySummeryContainer.add(getFrequencyLabel());
		frequencySummeryContainer.add(frequencySummeryPanel);
		
		frequencyPanel.add(frequencySummeryContainer, BorderLayout.EAST);
		
		headerAndPanelContiner.add(frequencyPanel, BorderLayout.EAST);
		
		this.add(headerAndPanelContiner);
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getStepLabel() {
		return stepLabel;
	}
	/**
	 * 
	 * @param stepLabel
	 */
	public void setStepLabel(JLabel stepLabel) {
		this.stepLabel = stepLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getStartMoreThenLabel() {
		return startMoreThenLabel;
	}
	/**
	 * 
	 * @param startMoreThenLabel
	 */
	public void setStartMoreThenLabel(JLabel startMoreThenLabel) {
		this.startMoreThenLabel = startMoreThenLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getStartLessThenLabel() {
		return startLessThenLabel;
	}
	/**
	 * 
	 * @param startLessThenLabel
	 */
	public void setStartLessThenLabel(JLabel startLessThenLabel) {
		this.startLessThenLabel = startLessThenLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getEndMoreThenLabel() {
		return endMoreThenLabel;
	}
	/**
	 * 
	 * @param endMoreThenLabel
	 */
	public void setEndMoreThenLabel(JLabel endMoreThenLabel) {
		this.endMoreThenLabel = endMoreThenLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getEndLessThenLabel() {
		return endLessThenLabel;
	}
	/**
	 * 
	 * @param endLessThenLabel
	 */
	public void setEndLessThenLabel(JLabel endLessThenLabel) {
		this.endLessThenLabel = endLessThenLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getEndFrequensyLabel() {
		return endFrequensyLabel;
	}
	/**
	 * 
	 * @param endFrequensyLabel
	 */
	public void setEndFrequensyLabel(JLabel endFrequensyLabel) {
		this.endFrequensyLabel = endFrequensyLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getDensityLessThenLabel() {
		return densityLessThenLabel;
	}
	/**
	 * 
	 * @param densityLessThenLabel
	 */
	public void setDensityLessThenLabel(JLabel densityLessThenLabel) {
		this.densityLessThenLabel = densityLessThenLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getDensityMoreThenLabel() {
		return densityMoreThenLabel;
	}
	/**
	 * 
	 * @param densityMoreThenLabel
	 */
	public void setDensityMoreThenLabel(JLabel densityMoreThenLabel) {
		this.densityMoreThenLabel = densityMoreThenLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getDensityFrequensyLabel() {
		return densityFrequensyLabel;
	}
	/**
	 * 
	 * @param densityFrequensyLabel
	 */
	public void setDensityFrequensyLabel(JLabel densityFrequensyLabel) {
		this.densityFrequensyLabel = densityFrequensyLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getFrequencyLabel() {
		return frequencyLabel;
	}
	/**
	 * 
	 * @param frequencyLabel
	 */
	public void setFrequencyLabel(JLabel frequencyLabel) {
		this.frequencyLabel = frequencyLabel;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getFrequencyLabelStart() {
		return frequencyLabelStart;
	}
	/**
	 * 
	 * @param frequencyLabelStart
	 */
	public void setFrequencyLabelStart(JLabel frequencyLabelStart) {
		this.frequencyLabelStart = frequencyLabelStart;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getFrequencyLabelEnd() {
		return frequencyLabelEnd;
	}
	/**
	 * 
	 * @param frequencyLabelEnd
	 */
	public void setFrequencyLabelEnd(JLabel frequencyLabelEnd) {
		this.frequencyLabelEnd = frequencyLabelEnd;
	}
	/**
	 * 
	 * @return
	 */
	public JLabel getFrequencyLabelDensity() {
		return frequencyLabelDensity;
	}
	/**
	 * 
	 * @param frequencyLabelDensity
	 */
	public void setFrequencyLabelDensity(JLabel frequencyLabelDensity) {
		this.frequencyLabelDensity = frequencyLabelDensity;
	}
	/**
	 * 
	 * @return
	 */
	public String getStepGrayText() {
		return stepGrayText;
	}
	/**
	 * 
	 * @param stepGrayText
	 */
	public void setStepGrayText(String stepGrayText) {
		this.stepGrayText = stepGrayText;
	}
	/**
	 * 
	 * @return
	 */
	public String getStepTextDarkGreen() {
		return stepTextDarkGreen;
	}
	/**
	 * 
	 * @param stepTextDarkGreen
	 */
	public void setStepTextDarkGreen(String stepTextDarkGreen) {
		this.stepTextDarkGreen = stepTextDarkGreen;
	}
	/**
	 * 
	 * @return
	 */
	public static String getPanelInformation() {
		return panelInformation;
	}
	/**
	 * 
	 * @param panelInformation
	 */
	public static void setPanelInformation(String panelInformation) {
		FrequencySettingsSubPanel.panelInformation = panelInformation;
	}
	/**
	 * 
	 * @return
	 */
	public String getHeaderButtonToolTipText() {
		return headerButtonToolTipText;
	}
	/**
	 * 
	 * @param headerButtonToolTipText
	 */
	public void setHeaderButtonToolTipText(String headerButtonToolTipText) {
		this.headerButtonToolTipText = headerButtonToolTipText;
	}
	/**
	 * 
	 * @return
	 */
	public String getMoreThenRedText() {
		return moreThenRedText;
	}
	/**
	 * 
	 * @param moreThenRedText
	 */
	public void setMoreThenRedText(String moreThenRedText) {
		this.moreThenRedText = moreThenRedText;
	}
	/**
	 * 
	 * @return
	 */
	public String getLessThenRedText() {
		return lessThenRedText;
	}
	/**
	 * 
	 * @param lessThenRedText
	 */
	public void setLessThenRedText(String lessThenRedText) {
		this.lessThenRedText = lessThenRedText;
	}
	/**
	 * 
	 * @return
	 */
	public String getStartFrequencyRedText() {
		return startFrequencyRedText;
	}
	/**
	 * 
	 * @param startFrequencyRedText
	 */
	public void setStartFrequencyRedText(String startFrequencyRedText) {
		this.startFrequencyRedText = startFrequencyRedText;
	}
	/**
	 * 
	 * @return
	 */
	public String getDensityFrequencyLightBlueText() {
		return densityFrequencyLightBlueText;
	}
	/**
	 * 
	 * @param densityFrequencyLightBlueText
	 */
	public void setDensityFrequencyLightBlueText(
			String densityFrequencyLightBlueText) {
		this.densityFrequencyLightBlueText = densityFrequencyLightBlueText;
	}
	/**
	 * 
	 * @return
	 */
	public String getDensityFrequencyRedText() {
		return densityFrequencyRedText;
	}
	/**
	 * 
	 * @param densityFrequencyRedText
	 */
	public void setDensityFrequencyRedText(String densityFrequencyRedText) {
		this.densityFrequencyRedText = densityFrequencyRedText;
	}
	/**
	 * 
	 * @return
	 */
	public String getEndFrequencyLightBlueText() {
		return endFrequencyLightBlueText;
	}
	/**
	 * 
	 * @param endFrequencyLightBlueText
	 */
	public void setEndFrequencyLightBlueText(String endFrequencyLightBlueText) {
		this.endFrequencyLightBlueText = endFrequencyLightBlueText;
	}
	/**
	 * 
	 * @return
	 */
	public String getEndFrequencyRedText() {
		return endFrequencyRedText;
	}
	/**
	 * 
	 * @param endFrequencyRedText
	 */
	public void setEndFrequencyRedText(String endFrequencyRedText) {
		this.endFrequencyRedText = endFrequencyRedText;
	}
	/**
	 * 
	 * @return
	 */
	public String getLessThenLightGrayText() {
		return lessThenLightGrayText;
	}
	/**
	 * 
	 * @param lessThenLightGrayText
	 */
	public void setLessThenLightGrayText(String lessThenLightGrayText) {
		this.lessThenLightGrayText = lessThenLightGrayText;
	}
	/**
	 * 
	 * @return
	 */
	public String getEndFrequencyLightGrayText() {
		return endFrequencyLightGrayText;
	}
	/**
	 * 
	 * @param endFrequencyLightGrayText
	 */
	public void setEndFrequencyLightGrayText(String endFrequencyLightGrayText) {
		this.endFrequencyLightGrayText = endFrequencyLightGrayText;
	}
	/**
	 * 
	 * @return
	 */
	public String getDensityFrequencyLightGrayText() {
		return densityFrequencyLightGrayText;
	}
	/**
	 * 
	 * @param densityFrequencyLightGrayText
	 */
	public void setDensityFrequencyLightGrayText(
			String densityFrequencyLightGrayText) {
		this.densityFrequencyLightGrayText = densityFrequencyLightGrayText;
	}
	/**
	 * 
	 * @return
	 */
	public String getLessThenNormalText() {
		return lessThenNormalText;
	}
	/**
	 * 
	 * @param lessThenNormalText
	 */
	public void setLessThenNormalText(String lessThenNormalText) {
		this.lessThenNormalText = lessThenNormalText;
	}
	/**
	 * 
	 * @return
	 */
	public String getMoreThenNormalText() {
		return moreThenNormalText;
	}
	/**
	 * 
	 * @param moreThenNormalText
	 */
	public void setMoreThenNormalText(String moreThenNormalText) {
		this.moreThenNormalText = moreThenNormalText;
	}
	/**
	 * 
	 * @return
	 */
	public String getStepLightBlueText() {
		return stepLightBlueText;
	}
	/**
	 * 
	 * @param stepLightBlueText
	 */
	public void setStepLightBlueText(String stepLightBlueText) {
		this.stepLightBlueText = stepLightBlueText;
	}
}
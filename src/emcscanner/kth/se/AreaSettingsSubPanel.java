package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Jonas
 *
 */
public class AreaSettingsSubPanel extends JPanel {
	/** 
	 * Area Settings Sub Panel ID
	 */
	private static final long serialVersionUID = -1566067668166465634L;
	/** 
	 * The buffered received from the camera. 
	 */
	public static BufferedImage buffImg = null;
	/** 
	 * The color panel is a panel where we show the video for the area help video
	 */
	public static ColorPanel colorAreaVideoPanel = new ColorPanel(buffImg);
	
	/** 
	 * For displaying the area help video. 
	 */
	static Thread threadDisplayAreaSelectionVideo;
	
	/** 
	 * True for showing the area display video.<br>
	 * False if no video is to be shown
	 */
	public static boolean DISPLAY_VIDEO = true;
	/** 
	 * True if help area video is to be shown.<br>
	 * False if area video is to be paused.
	 */
	public static boolean DISPLAY_HELP_VIDEO = false;
	/** 
	 * True if header is enabled. <br>
	 * False if header is disabled
	 */
	public static boolean HEADER_BUTTON_ENABLED = false;

	/** 
	 * Stage of Area panel is 2.
	 */
	public static int STAGE = 2;
	
	/* Panels- Containers for setting up GUI */
	/** 
	 * Container containing the step label.
	 */
	public JPanel stepContiner = new JPanel();
	/** 
	 * Container containing area panel and the header button.
	 */
	public JPanel headerAndPanelContiner = new JPanel();
	/** 
	 * Container containing area panel colorAreaVideoPanel and container 1.
	 */
	public JPanel areaPanel = new JPanel();
	/** 
	 * Container containing areaSelectedContainer
	 */
	public JPanel continer1 = new JPanel();
	/** 
	 * Container containing areaNotSelectedLabel
	 */
	public JPanel areaSelectedContainer = new JPanel();
	
	/* Buttons */
	/** 
	 * Header Button is a button for the area panels header. <br>
	 * Clickable and has image icons on it.
	 */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	/** 
	 * Next Button is not be used for going to the next stage when enabled. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static NextButton nextButton = new NextButton(STAGE);
	/** 
	 * Back Button is not be used for going to the to the previous stage when enabled. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static BackButton backButton = new BackButton(STAGE);
	
	/* String */
	/** 
	 * Used by the step label. <br>
	 * Color: Gray (Read Green Blue(120,120,120))<br>
	 * Text: Step 2/4
	 */
	public String STEP_TEXT_GRAY	 	= "<html> <font color = rgb(120,120,120)>Step 2/4</font></html>";
	/** 
	 * Used by the step label. <br>
	 * Color: Light Blue (Read Green Blue(100,150,255)) <br>
	 * Text: Step 2/4
	 */
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 2/4</font></html>";
	/** 
	 * Used by the step label. <br>
	 * Color: Dark Green (Read Green Blue(120,200,40))<br>
	 * Text: Step 2/4
	 */
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 2/4</font></html>";
	
	/** 
	 * Used by the areaNotSelectedLabel label. <br>
	 * Color: Gray (Read Green Blue(120,120,120))<br>
	 * Text: Area not selected
	 */
	public String AREA_NOT_SELECTED = "<html><font color = rgb(120,120,120)>Area not selected</font></html>";
	/** 
	 * Used by the areaNotSelectedLabel label. <br>
	 * Color: Light Blue (Read Green Blue(100,150,255))<br>
	 * Text: Area selected 
	 */
	public String AREA_SELECTED_LABEL = "<html><font color = rgb(100,150,255)>Area selected </html></font>";
	
	/* JLabel */
	/** 
	 * Label showing on what step you are on. <br>
	 * Step: 2 <br>
	 * Text: Step 2/4
	 */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	/** 
	 * Label showing if you have selected an area. <br>
	 * Text: Area not selected ELLER Area selected 
	 */
	public JLabel areaNotSelectedLabel = new JLabel(AREA_NOT_SELECTED);
	/** 
	 * Label showing the area you have selected and its result <br>
	 * Text: Width and Height 
	 */
	public JLabel areaResultLabel = new JLabel();

	/* Dimensions */
	/** 
	 * When area panel is active step container is: <br>
	 * 50 Width, 280 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 280);
	/** 
	 * When area panel is inactive but an area has been selected is: <br>
	 * 50 Width, 80 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION_DONE = new Dimension(50, 80);
	/** 
	 * When area panel is inactive and an area has not been selected is: <br>
	 * 50 Width, 40 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 40);
	
	/** 
	 * When area panel is active step container is: <br>
	 * 322 Width, 240 Height 
	 */
	public Dimension AREA_PANEL_DIMENSION_ACTIVE = new Dimension(322, 240);
	/** 
	 * When area panel is inactive but an area has been selected is: <br>
	 * 322 Width, 240 Height 
	 */
	public Dimension AREA_PANEL_DIMENSION_DONE = new Dimension(322, 40);
	/**
	 * When area panel is inactive and an area has not been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	public Dimension AREA_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	/** 
	 * When area panel is active header and panel container is: <br>
	 * 322 Width, 240 Height 
	 */
	public Dimension HEADER_AND_PANEL_DIMENSION_ACTIVE = new Dimension(322, 280);
	/** 
	 * When area panel is inactive but an area has been selected is: <br>
	 * 322 Width, 80 Height 
	 */
	public Dimension HEADER_AND_PANEL_DIMENSION_DONE = new Dimension(322, 80);
	/**
	 * When area panel is inactive and an area has not been selected is: <br>
	 * 322 Width, 40 Height 
	 */
	public Dimension HEADER_AND_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	/**
	 * The step Labels dimension used for showing what step we are on <br>
	 * 50 Width, 40 Height 
	 */
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	/**
	 * When area panel has ben created
	 */
	public AreaSettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(SettingsPanel.SUB_PANEL_MINIMUM_DIMENSION);
		
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
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_DIMENSION_OFF);
		
		/* Panel for the frequency input */
		areaPanel.setLayout(new BorderLayout());
		areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_OFF);
		
		/* Turns off area Label showing the selected area */
		areaResultLabel.setVisible(false);
		
		threadDisplayAreaSelectionVideo = new FrameGrabberThread(STAGE,"threadDisplayArea");
        threadDisplayAreaSelectionVideo.setDaemon(true);
        threadDisplayAreaSelectionVideo.start();

		/* Sets container backgrounds to white instead of gray for contrast */
		colorAreaVideoPanel.setBackground(Color.WHITE);
		continer1.setBackground(Color.WHITE);
		areaPanel.setBackground(Color.WHITE);
		areaSelectedContainer.setBackground(Color.WHITE);
		
		/* Setting containers Layouts for the right GUI look. */
		continer1.setLayout(new BorderLayout());
		continer1.add(backButton, BorderLayout.WEST);
		continer1.add(nextButton, BorderLayout.EAST);
		
		areaSelectedContainer.add(areaNotSelectedLabel, BorderLayout.CENTER);
		
		continer1.add(areaSelectedContainer, BorderLayout.CENTER);
		
		areaPanel.add(continer1, BorderLayout.SOUTH);
		areaPanel.add(colorAreaVideoPanel, BorderLayout.CENTER);
		
		headerAndPanelContiner.add(areaPanel, BorderLayout.SOUTH);
		
		this.add(headerAndPanelContiner);
		
		/* Sets video and buttons not visible */
		nextButton.setVisible(false);
		backButton.setVisible(false);
		areaNotSelectedLabel.setVisible(false);
		colorAreaVideoPanel.setVisible(false);
		continer1.setVisible(false);
		areaSelectedContainer.setVisible(false);
	}
}
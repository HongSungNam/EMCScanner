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
	
	public static BufferedImage buffImg = null;
	/* Creates a ColorPanel and adds it to this camera panel */
	public static ColorPanel colorAreaVideoPanel = new ColorPanel(buffImg);
	
	/* Threads */
	static Thread threadDisplayAreaSelectionVideo;
	
	/* Boolean */
	public static boolean DISPLAY_VIDEO = true;
	public static boolean DISPLAY_HELP_VIDEO = false;
	public static boolean HEADER_BUTTON_ENABLED = false;

	/* Integers */
	public static int STAGE = 2; //This stage
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel areaPanel = new JPanel();
	public JPanel continer1 = new JPanel();
	public JPanel areaSelectedContainer = new JPanel();
	
	/* Buttons */
	public static HeaderButton headerButton = new HeaderButton(STAGE);
	public static NextButton nextButton = new NextButton(STAGE);
	public static BackButton backButton = new BackButton(STAGE);
	
	/* String */
	public String STEP_TEXT_GRAY	 	= "<html> <font color = rgb(120,120,120)>Step 2/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 2/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 2/4</font></html>";

	public String AREA_NOT_SELECTED = "<html><font color = rgb(120,120,120)>Area not selected</font></html>";
	public String AREA_SELECTED = "<html><font color = rgb(100,150,255)>Area selected </html></font>";
	
	public String HEADER_BUTTON_TOOL_TIP_TEXT = "Press to reselect the area";
	
	public String AREA_SELECTED_LABEL = "<html><font color = rgb(120,200,40)>Selected area: Width </font>" + (int) (SettingsPanel.getAREA_SELECTED_END_X() - SettingsPanel.getAREA_SELECTED_START_X()) + " le & "
			  + "<font color = rgb(120,200,40)> Hight </font>"+ (int) (SettingsPanel.getAREA_SELECTED_END_Y() - SettingsPanel.getAREA_SELECTED_START_Y()) + " le </html>";
	
	/* JLabel */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	public JLabel areaNotSelectedLabel = new JLabel(AREA_NOT_SELECTED);
	public JLabel areaLabel = new JLabel();

	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);

	public Dimension STEP_CONTINER_DIMENSION_ACTIVE = new Dimension(50, 280);
	public Dimension STEP_CONTINER_DIMENSION_DONE = new Dimension(50, 80);
	public Dimension STEP_CONTINER_DIMENSION_OFF = new Dimension(50, 40);
	
	public Dimension AREA_PANEL_DIMENSION_ACTIVE = new Dimension(322, 240);
	public Dimension AREA_PANEL_DIMENSION_DONE = new Dimension(322, 40);
	public Dimension AREA_PANEL_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE = new Dimension(322, 280);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_DONE = new Dimension(322, 80);
	public Dimension HEADER_AND_PANEL_CONTINER_DIMENSION_OFF = new Dimension(322, 40);
	
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	public AreaSettingsSubPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);
		
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
		areaPanel.setLayout(new BorderLayout());
		areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_OFF);
		
		/* Turns off area Label showing the selected area */
		areaLabel.setVisible(false);
		
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
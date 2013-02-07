package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AreaSettingsSubPanel extends JPanel {
	
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
	public int STAGE = 2; //This stage
	
	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel();
	public JPanel headerAndPanelContiner = new JPanel();
	public JPanel areaPanel = new JPanel();
	public JPanel continer1 = new JPanel();
	public JPanel areaSelectedContainer = new JPanel();
	
	/* Buttons */
	public static JButton headerButton = new JButton();
	public static JButton nextButton = new JButton();
	public static JButton backButton = new JButton();
	
	/* String */
	public String STEP_TEXT_GRAY	 	= "<html> <font color = rgb(120,120,120)>Step 2/4</font></html>";
	public String STEP_TEXT_LIGHT_BLUE  = "<html> <font color = rgb(100,150,255)>Step 2/4</font></html>";
	public String STEP_TEXT_DARK_GREEN  = "<html> <font color = rgb(120,200,40)>Step 2/4</font></html>";
	
	public String PANEL_TOOL_TIP_TEXT = "This is where you select the area to scan.";
	public String NEXT_BUTTON_TOOL_TIP_TEXT = "You need to select an area before you can continue";

	public String AREA_NOT_SELECTED = "<html><font color = rgb(120,120,120)>Area not selected</font></html>";
	public String AREA_SELECTED = "<html><font color = rgb(100,150,255)>Area selected </html></font>";
	
	public String HEADER_BUTTON_TOOL_TIP_TEXT = "Press to reselect the area";
	
	public String AREA_SELECTED_LABEL = "<html><font color = rgb(120,200,40)>Selected area: Width </font>" + (int) (SettingsPanel.AREA_SELECTED_END_X - SettingsPanel.AREA_SELECTED_START_X) + " le & "
			  + "<font color = rgb(120,200,40)> Hight </font>"+ (int) (SettingsPanel.AREA_SELECTED_END_Y - SettingsPanel.AREA_SELECTED_START_Y) + " le </html>";
	
	/* JLabel */
	public JLabel stepLabel = new JLabel(STEP_TEXT_GRAY);
	public JLabel areaNotSelectedLabel = new JLabel(AREA_NOT_SELECTED);
	public JLabel areaLabel = new JLabel();

	/* Imports the different images for the different button stages. */	
	/* Import the images for the header button */
	public ImageIcon HEADER_ENABLED_IMAGE_ICON 	 			= new ImageIcon("image/PanelGreenArea.png");
	public ImageIcon HEADER_ENABLED_ROLLOVER_IMAGE_ICON 	= new ImageIcon("image/PanelGreenAreaRollover.png");
	public ImageIcon HEADER_DISABLED_GRAY_IMAGE_ICON 		= new ImageIcon("image/PanelGrayArea.png");
	public ImageIcon HEADER_ENABLED_PREST_IMAGE_ICON 		= new ImageIcon("image/PanelGreenAreaPrest.png");
	public ImageIcon HEADER_DISABLED_BLUE_IMAGE_ICON 		= new ImageIcon("image/PanelBlueArea.png");
	public ImageIcon HEADER_DISABLED_DARK_GREEN_IMAGE_ICON 	= new ImageIcon("image/PanelDarkGreenArea.png");

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
	
	public Dimension HEADER_BUTTON_DIMENSION = new Dimension(355, 40);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	
	public AreaSettingsSubPanel() {
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
		headerButton.addActionListener(new headerButtonActionListener(this.STAGE));
		
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
		
		/* Next JButton */
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText(NEXT_BUTTON_TOOL_TIP_TEXT);
		nextButton.setPreferredSize(Program.BUTTON_DIMENSION);
		nextButton.setEnabled(false);
		nextButton.setIcon(Program.NEXT_BUTTON_ENABLED_IMAGE_ICON);
		nextButton.setDisabledIcon(Program.NEXT_BUTTON_DISABLED_IMAGE_ICON);
		nextButton.setPressedIcon(Program.NEXT_BUTTON_BLUE_PREST_IMAGE_ICON);
		nextButton.setDisabledSelectedIcon(Program.NEXT_BUTTON_GRAY_PREST_IMAGE_ICON);
		nextButton.addActionListener(new NextActionListener());

		/* Back on step JButton */
		backButton.setEnabled(true);
		backButton.setPreferredSize(Program.BUTTON_DIMENSION);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new BackActionListener());
		
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
	/**
	 * ACTIVE
	 */
	public void areaPanelActive(){
		DISPLAY_HELP_VIDEO = true;
		
		MainPanel.setLeftStage(Program.cameraPanel);

		Program.settingsPanel.setVisible(true);
		Program.manualPanel.setVisible(false);
		Program.startControlPanel.setVisible(false);
		Program.cameraPanel.setVisible(true);
		Program.imagePanel.setVisible(false);
		
		Program.cameraPanel.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		Program.settingsPanel.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));

		SettingsPanel.setStage(this.STAGE);
		
		Program.frame.glass.repaint();
		Program.frame.glass.setVisible(true);
		
		/* Glass Panel visible and active */
		MainFrame.GET_AREA_BOOLEAN = true;
		
		/* Turns off header button */
		headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_BLUE_IMAGE_ICON);
		
		/* New tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Turns ON next back label video */
		nextButton.setVisible(true);
		backButton.setVisible(true);
		areaNotSelectedLabel.setVisible(true);
		colorAreaVideoPanel.setVisible(true);
		continer1.setVisible(true);
		areaSelectedContainer.setVisible(true);
		
		areaPanel.setVisible(true);
		areaPanel.add(areaLabel, BorderLayout.EAST);
		
		/* Turns OFF area Label */
		areaLabel.setVisible(false);
		
		/* Changing size of panels when button has been pressed*/	
		areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_ACTIVE);
		headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_ACTIVE);
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_ACTIVE);
		
		/* Sets appropriate blue active colors */
		stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_LIGHT_BLUE);
		areaPanel.setBorder(Program.LIGHT_BLUE_BORDER);
		
		/* If a area has been selected */
		if(Program.frame.glass.cursorReleased.x > 0 && Program.frame.glass.cursorReleased.y > 0)
		{
			nextButton.setEnabled(true);
			Program.frame.MOUSE_RELEASED_BOOLEAN = true;
		}
	}
	/**
	 * NOT ACTIVE
	 */
	public void areaNotPanelActive(){
		DISPLAY_HELP_VIDEO = false;
		
		/* Don't show Glass Panel and turn inactive */
		MainFrame.GET_AREA_BOOLEAN = false;

		/* Area help video disabled */
		DISPLAY_HELP_VIDEO = false;
		
		/* New tool tip */
		headerButton.setToolTipText(HEADER_BUTTON_TOOL_TIP_TEXT);
		
		/* Sets video and buttons not visible */
		nextButton.setVisible(false);
		backButton.setVisible(false);
		areaNotSelectedLabel.setVisible(false);
		colorAreaVideoPanel.setVisible(false);
		continer1.setVisible(false);
		areaSelectedContainer.setVisible(false);
		
		
		/* Sets step label gray for not active */
		stepLabel.setText(SettingsPanel.areaPanel.STEP_TEXT_GRAY);
		
		if (SettingsPanel.AREA_SELECTED)
		{
			/* Sets step label green when button has been pressed */
			stepLabel.setText(STEP_TEXT_DARK_GREEN);
			
			/* AreaPanel and header Green */
			areaPanel.setBorder(Program.GREEN_BORDER);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = true);
			
			/* Changing size of panels when button has been pressed*/	
			areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_DONE);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_DONE);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_DONE);
			
			/* Sets selected area and label visible */
			areaLabel.setVisible(true);
			areaPanel.setVisible(true);
		}
		else
		{
			/* Changing size of panels when button has been pressed*/	
			areaPanel.setPreferredSize(AREA_PANEL_DIMENSION_OFF);
			headerAndPanelContiner.setPreferredSize(HEADER_AND_PANEL_CONTINER_DIMENSION_OFF);
			stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION_OFF);
			
			/* Sets selected area and label invisible */
			areaLabel.setVisible(false);
			areaPanel.setVisible(false);
			
			/* Sets Header button gray */
			headerButton.setDisabledIcon(SettingsPanel.areaPanel.HEADER_DISABLED_GRAY_IMAGE_ICON);
			headerButton.setEnabled(HEADER_BUTTON_ENABLED = false);
		}
	}
}
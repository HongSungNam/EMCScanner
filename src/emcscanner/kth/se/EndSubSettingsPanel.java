package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * 
 * @author Jonas
 *
 * Extends JPanel
 */
public class EndSubSettingsPanel extends JPanel {
	/**
	 * End Sub Settings Panel ID
	 */
	private static final long serialVersionUID = 7983722947920631252L;
	
	/**
	 * End sub panel stage = 6
	 */
	public static int STAGE = 6;
	
	/* Buttons */
	/** 
	 * Back Button is not be used for going to the to the previous stage when enabled. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static BackButton backButton = new BackButton(STAGE);
	/** 
	 * Quit Button is used to quit the program. <br>
	 * Nothing happen when when you click on it when it is not enabled
	 */
	public static QuitButton quitButton = new QuitButton();

	/* Dimensions */
	/** 
	 * Step Label container: <br>
	 * 50 Width, 40 Height 
	 */
	public Dimension STEP_CONTINER_DIMENSION = new Dimension(50, 40);
	/** 
	 * When end panel is active(always active) : <br>
	 * 322 Width, 40 Height 
	 */
	public Dimension END_PANEL_DIMENSION = new Dimension(322, 40);

	/* Panels- Containers for setting up GUI */
	/**
	 * Container containing: <br>
	 * Null
	 */
	public JPanel stepContiner = new JPanel(new BorderLayout());
	/**
	 * Container containing: <br>
	 * backButton					WEST<br>
	 * quitButton					EAST
	 */
	public JPanel headerAndPanelContiner = new JPanel(new BorderLayout());
	
	/**
	 * End Sub Settings Panel <br>
	 * Extends JPanel
	 */
	public EndSubSettingsPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(SettingsPanel.SUB_PANEL_MINIMUM_DIMENSION);

		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.setPreferredSize(END_PANEL_DIMENSION);
		headerAndPanelContiner.add(backButton, BorderLayout.WEST);
		headerAndPanelContiner.add(quitButton, BorderLayout.EAST);
		
		this.add(stepContiner, BorderLayout.WEST);
		this.add(headerAndPanelContiner);
		
	}
}

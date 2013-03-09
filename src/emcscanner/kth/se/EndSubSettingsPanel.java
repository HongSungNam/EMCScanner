package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Jonas
 *
 */
public class EndSubSettingsPanel extends JPanel {
	/**
	 * End Sub Settings Panel ID
	 */
	private static final long serialVersionUID = 7983722947920631252L;
	
	/* Buttons */
	public static JButton backButton = new JButton();
	public static JButton quitButton = new JButton();

	/* Dimensions */
	public Dimension THIS_MINIMUM_DIMENSION = new Dimension(400, 100);
	public Dimension STEP_LABEL_DIMENSION = new Dimension(50,40);
	public Dimension STEP_CONTINER_DIMENSION = new Dimension(50, 40);
	public Dimension END_PANEL_DIMENSION = new Dimension(322, 40);

	/* Imports the different images for the different button stages. */	
	public ImageIcon QUIT_ENABLED_IMAGE_ICON				= new ImageIcon("image/Quit.png");
	public ImageIcon QUIT_PREST_ENABLED_IMAGE_ICON 			= new ImageIcon("image/QuitPrest.png");

	/* Panels- Containers for setting up GUI */
	public JPanel stepContiner = new JPanel(new BorderLayout());
	public JPanel headerAndPanelContiner = new JPanel(new BorderLayout());
	
	public EndSubSettingsPanel() {
		this.setLayout(new FlowLayout());
		this.setMinimumSize(THIS_MINIMUM_DIMENSION);

		/* Button made for going back to previous views */
		backButton.setEnabled(true);
		backButton.setPreferredSize(Program.BUTTON_DIMENSION);
		backButton.setIcon(Program.BACK_BUTTON_ENABLED_IMAGE_ICON);
		backButton.setPressedIcon(Program.BACK_BUTTON_BLUE_PREST_IMAGE_ICON);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new MainBackActionListener());

		/* Back on step JButton */
		quitButton.setEnabled(true);
		quitButton.setPreferredSize(Program.MEDIUM_BUTTON_DIMENSION);
		quitButton.setIcon(QUIT_ENABLED_IMAGE_ICON);
		quitButton.setPressedIcon(QUIT_PREST_ENABLED_IMAGE_ICON);
		quitButton.setOpaque(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setBorderPainted(false);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.quit();
			}
		});
		stepContiner.setPreferredSize(STEP_CONTINER_DIMENSION);
		
		/* A panel for the Header and the sup settings panels. */
		headerAndPanelContiner.setPreferredSize(END_PANEL_DIMENSION);
		headerAndPanelContiner.add(backButton, BorderLayout.WEST);
		headerAndPanelContiner.add(quitButton, BorderLayout.EAST);
		
		this.add(stepContiner, BorderLayout.WEST);
		this.add(headerAndPanelContiner);
		
	}
}

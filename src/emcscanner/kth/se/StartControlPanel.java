package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Jonas
 *
 */
public class StartControlPanel extends JPanel {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -8735554660590401730L;

	private boolean buttonLook = false;
	
	private String buttonToolTipText = "You need to write a number between 0.1 and 6000 befor you can continue";
	
	private static JButton nextButton = new JButton();
	
	/* Panel made for setting button at the bottom right corner */
	private JPanel container = new JPanel(new BorderLayout());
	
	/* Imports the different images for the different button stages. */
	private ImageIcon nextButtonEnabledIcon = new ImageIcon("image/ButtonBlueNext.png");
	private ImageIcon nextButtonBlueNextPrestIcon = new ImageIcon("image/ButtonBlueNextPrest.png");
	
	private Dimension startControlPanelDimension = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0);
	
	private BorderLayout startControlPanelBorderLayout = new BorderLayout();
	
	public StartControlPanel() {	
		this.setLayout(startControlPanelBorderLayout);
		this.setPreferredSize(startControlPanelDimension);
		
		/* Creates a next button and adds it in the corner */
		getNextButton().setOpaque(buttonLook);
		getNextButton().setContentAreaFilled(buttonLook);
		getNextButton().setBorderPainted(buttonLook);
		getNextButton().setPreferredSize(Buttons.BUTTON_DIMENSION);
		getNextButton().setToolTipText(buttonToolTipText);
		getNextButton().setEnabled(true);
		getNextButton().setFocusable(true);
		getNextButton().setIcon(nextButtonEnabledIcon);
		getNextButton().setPressedIcon(nextButtonBlueNextPrestIcon);
		
		/* Adds Next Button in the right corner at the bottom */
		container.add(getNextButton(), BorderLayout.SOUTH);
		this.add(container, BorderLayout.EAST);
	}
	public static JButton getNextButton() {
		return nextButton;
	}
	public void setNextButton(JButton nextButton) {
		StartControlPanel.nextButton = nextButton;
	}
}


package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
/**
 * 
 */
public class ConclutionPanel extends JPanel {
	/**
	 * ConclutionPanel ID
	 */
	private static final long serialVersionUID = -5095117504405294654L;
	/**
	 * 
	 */
	public ConclutionPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0));
	}
}

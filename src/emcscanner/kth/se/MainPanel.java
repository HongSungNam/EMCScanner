package emcscanner.kth.se;

import java.awt.*;

import javax.swing.*;

/**
 * 
 * @author Jonas
 *
 */
public class MainPanel extends JPanel {
	/**
	 * ID
	 */
	private static final long serialVersionUID = 7551801394252802143L;
	
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	public MainPanel(){	
		this.setLayout(new BorderLayout());
	}
	public static void setLeftStage(JPanel left){
		MainFrame.mainPanel.add(left, BorderLayout.WEST);
	}
	public static void setRightStage(JPanel right){
		MainFrame.mainPanel.add(right, BorderLayout.EAST);
	}
	public static void setStages(JPanel left, JPanel right){
		MainFrame.mainPanel.add(left, BorderLayout.WEST);
		MainFrame.mainPanel.add(right, BorderLayout.EAST);
	}
	
	/*
	 * Fixa så att det inte är dubbelt upp.
	 */
	public JPanel getLeftPanel() {
		return leftPanel;
	}
	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}
	public JPanel getRightPanel() {
		return rightPanel;
	}
	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
}

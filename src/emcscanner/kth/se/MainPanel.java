package emcscanner.kth.se;

import java.awt.*;

import javax.swing.*;


/**
 * @param argsW
 */
public class MainPanel extends JPanel {
	public JPanel leftPanel = new JPanel();
	public JPanel rightPanel = new JPanel();
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
}

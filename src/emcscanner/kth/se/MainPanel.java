package emcscanner.kth.se;

import java.awt.*;

import javax.swing.*;


/**
 * @param args
 */
public class MainPanel extends JPanel   {
	public static JSplitPane split;
	
	public MainPanel(){	
		this.split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false);
		setStages(Program.startControlPanel, Program.manualPanel);
		
		this.setLayout(new BorderLayout());
		this.add(this.split, BorderLayout.CENTER);
	}
	public static void setLeftStage(JPanel left){
		split.setLeftComponent(left);
	}
	public static void setRightStage(JPanel right){
		split.setRightComponent(right);
	}
	public static void setStages(JPanel left, JPanel right){
		split.setRightComponent(right);
		split.setLeftComponent(left);
	}
}

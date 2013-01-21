package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
 * @param args
 */
public class MainFrame extends JFrame {
	
	public MainFrame(){
		super("EMC-Scanner");
		
		//Sets what will happen when the frame closes
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		this.add(new MainPanel(), BorderLayout.CENTER);
		
		//Size the frame
		this.pack();
	}
	
}

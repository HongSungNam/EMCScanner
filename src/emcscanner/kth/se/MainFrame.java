package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
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
		
		MenuBar menuBar  = new MenuBar();
		Menu quitMenu = new Menu("Quit F11"); 
		menuBar.add(quitMenu);
		this.setMenuBar(menuBar);
		
		
		this.add(new MainPanel(), BorderLayout.CENTER);
		
		//Size the frame
		this.pack();
	}
	
}

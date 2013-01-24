package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.googlecode.javacv.FrameGrabber.Exception;

/**
 * @param args
 */
public class MainFrame extends JFrame {

	private boolean toggleFullScreen = false;
	public MainFrame(){
		super("EMC-Scanner");
		
		//Sets what will happen when the frame closes
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		setUndecorated(true);
		
		
		JMenuBar menuBar  = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_ESCAPE);
		menuBar.add(menu);
		JMenuItem quitMenuItem = new JMenuItem("Quit",
                KeyEvent.VK_ESCAPE);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_ESCAPE, 0));
		JMenuItem settingsMenuItem = new JMenuItem("Settings",
                KeyEvent.VK_S);
		settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, 0));
		final JMenuItem fullScreenToggleMenuItem = new JMenuItem("Quit Full Screen",
                KeyEvent.VK_F11);
		fullScreenToggleMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F11, 0));
		
		menu.add(settingsMenuItem);
		menu.add(fullScreenToggleMenuItem);
		menu.add(quitMenuItem);
		
		this.setJMenuBar(menuBar);
		
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Program.cameraPanel.grabber.stop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		fullScreenToggleMenuItem.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
				if (toggleFullScreen)
				{
		    		fullScreenToggleMenuItem.setText("Leave Full Screen");
		    		dispose();
		    		setUndecorated(true);
		    		setVisible(true);
		    		toggleFullScreen = false;
		    		pack();
		    		Program.settingsPanel.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		    		Program.cameraPanel.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		    	}
		    	else
		    	{
		    		fullScreenToggleMenuItem.setText("Enter Full Screen"); 
		    		dispose();
		    		setUndecorated(false);
		    		setVisible(true);
		    		toggleFullScreen = true;
		    		pack();
		    		Program.settingsPanel.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		    		Program.cameraPanel.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		    	}
		    	if (Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH))
		    	{
        			Program.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		    	}
		    	else
        		{
        			int w = Program.frame.getPreferredSize().width;
        			int h = Program.frame.getPreferredSize().height;
        			Program.frame.setPreferredSize(new Dimension(Math.max(800, w), Math.max(600, h)));
        		}
			}
		});
		this.add(new MainPanel(), BorderLayout.CENTER);
		
		//Size the frame
		this.pack();
	}
	
}

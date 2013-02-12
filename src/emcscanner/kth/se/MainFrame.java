package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.*;

import com.googlecode.javacv.FrameGrabber.Exception;

/**
 * @param args
 */
public class MainFrame extends JFrame {
	public static MainPanel mainPanel;
	
	public MyGlassPane glass;
	private boolean toggleFullScreen = false;
	public static JMenuBar menuBar;
	public boolean MOUSE_RELEASED_BOOLEAN = false;
	public static boolean GET_AREA_BOOLEAN = false;
	
	public MainFrame(){
		super("EMC-Scanner");
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);
		
		menuBar  = new JMenuBar();
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
		JMenuItem nextMenuItem = new JMenuItem("Next",
                KeyEvent.VK_ENTER);
		nextMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_ENTER, 0));
		
		JMenuItem backMenuItem = new JMenuItem("Back",
                KeyEvent.VK_CONTROL);
		backMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_ENTER, KeyEvent.VK_CONTROL ));
		
		final JMenuItem fullScreenToggleMenuItem = new JMenuItem("Quit Full Screen",
                KeyEvent.VK_F11);
		fullScreenToggleMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F11, 0));
		
		menu.add(backMenuItem);
		menu.add(nextMenuItem);
		menu.add(settingsMenuItem);
		menu.add(fullScreenToggleMenuItem);
		menu.add(quitMenuItem);
		
		this.setJMenuBar(menuBar);
		
		backMenuItem.addActionListener(new BackActionListener());
		nextMenuItem.addActionListener(new NextActionListener());
		
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		
		fullScreenToggleMenuItem.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			if (toggleFullScreen)
				{
		    		fullScreenToggleMenuItem.setText("Leave Full Screen");
		    		dispose();
		    		
		    		changePanelSize();
		    		
		    		setUndecorated(true);
		    		setVisible(true);
		    		toggleFullScreen = false;
		    		pack();
		    	}
		    	else
		    	{
		    		fullScreenToggleMenuItem.setText("Enter Full Screen"); 
		    		dispose();
		    		changePanelSize();
		    		setUndecorated(false);
		    		setVisible(true);
		    		toggleFullScreen = true;
		    		pack();
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
		mainPanel = new MainPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		
		WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                   quit();
                }
            }
        };
        this.addWindowListener(exitListener);
		
		//Size the frame
		this.pack();
		
		glass = new MyGlassPane(Program.frame);

		this.setGlassPane(glass);
	}
	public void changePanelSize() {
		if (SettingsPanel.getStage() == 1 || SettingsPanel.getStage() == 2)
			Program.cameraPanel.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		else if (SettingsPanel.getStage() == 3 || SettingsPanel.getStage() == 4)
			Program.imagePanel.setPreferredSize(new Dimension((int) (3*Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
		Program.settingsPanel.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4), 0));
	}
	public void quit(){
		try {
			Thread.sleep(100);
			CameraPanel.stopCamera = true;
			Thread.sleep(100);
        	FrequencySettingsSubPanel.DISPLAY_VIDEO = false ;
			Thread.sleep(100);
        	AreaSettingsSubPanel.DISPLAY_VIDEO = false ;
			Thread.sleep(100);
        	DensitySettingsSubPanel.DISPLAY_VIDEO = false ;
			Thread.sleep(100);
			System.exit(0);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		} 
	}
}

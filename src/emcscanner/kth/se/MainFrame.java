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

import javax.swing.*;

/**
 * @param args
 */
public class MainFrame extends JFrame {
	/**
	 * ID
	 */
	private static final long serialVersionUID = 1579347342338912156L;

	public static MainPanel mainPanel;
	
	public MyGlassPane glass;
	
	private boolean toggleFullScreen = false;
	private boolean MOUSE_RELEASED_BOOLEAN = false;
	private static boolean GET_AREA_BOOLEAN = false;
	

	private String LeavefullScreenToggleMenuText = "Leave Full Screen";
	private String EnterfullScreenToggleMenuText = "Enter Full Screen";
	private String QuitfullScreenToggleMenuText  = "Quit Full Screen";
	private static String BackText = "Back";
	private static String NextText = "Next";
	private String SettingsText = "Settings";
	private static String QuitText = "Quit";
	private String MenuText = "Menu";
	
	private JMenu menu = new JMenu(MenuText);
	public static JMenuBar menuBar = new JMenuBar();
	
	private BorderLayout mainFramLayout = new BorderLayout();
	
	public static JMenuItem backMenuItem = new JMenuItem(BackText, KeyEvent.VK_CONTROL);
	public static JMenuItem nextMenuItem = new JMenuItem(NextText, KeyEvent.VK_ENTER);
	private final JMenuItem fullScreenToggleMenuItem = new JMenuItem(QuitfullScreenToggleMenuText, KeyEvent.VK_F11);
	private JMenuItem settingsMenuItem = new JMenuItem(SettingsText, KeyEvent.VK_S);
	private static JMenuItem quitMenuItem = new JMenuItem(QuitText, KeyEvent.VK_ESCAPE);
	
	public MainFrame(){
		super("EMC-Scanner");
		this.setLayout(mainFramLayout);
		this.setUndecorated(true);
		
		menu.setMnemonic(KeyEvent.VK_ESCAPE);
		menuBar.add(menu);
		getQuitMenuItem().setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_ESCAPE, 0));
		settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, 0));
		nextMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_ENTER, 0));
		backMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_ENTER, KeyEvent.VK_CONTROL ));
		fullScreenToggleMenuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F11, 0));
		
		menu.add(backMenuItem);
		menu.add(nextMenuItem);
		menu.add(settingsMenuItem);
		menu.add(fullScreenToggleMenuItem);
		menu.add(getQuitMenuItem());
		
		this.setJMenuBar(menuBar);
		
		fullScreenToggleMenuItem.addActionListener(new ActionListener() {
    		@Override
			public void actionPerformed(ActionEvent e) {
    			if (toggleFullScreen)
				{
		    		fullScreenToggleMenuItem.setText(LeavefullScreenToggleMenuText);
		    		dispose();
		    		
		    		changePanelSize();
		    		
		    		setUndecorated(true);
		    		setVisible(true);
		    		toggleFullScreen = false;
		    		pack();
		    	}
		    	else
		    	{
		    		fullScreenToggleMenuItem.setText(EnterfullScreenToggleMenuText); 
		    		dispose();
		    		changePanelSize();
		    		setUndecorated(false);
		    		setVisible(true);
		    		toggleFullScreen = true;
		    		pack();
		    	}
		    	if (Toolkit.getDefaultToolkit().isFrameStateSupported(Frame.MAXIMIZED_BOTH))
        			Program.frame.setExtendedState(Frame.MAXIMIZED_BOTH);
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
                int confirm = JOptionPane.showOptionDialog(null, 
                											"Are You Sure to Close Application?", 
                											"Exit Confirmation", 
                											JOptionPane.YES_NO_OPTION, 
                											JOptionPane.QUESTION_MESSAGE, 
                											null, 
                											null, 
                											null);
                if (confirm == 0)
                   quit();
            }
        };
        this.addWindowListener(exitListener);
		
		//Size the frame
		this.pack();
		
		glass = new MyGlassPane(Program.frame);
		this.setGlassPane(glass);
	}
	/**
	 * Method for quitting the program
	 */
	public static void quit(){
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
	/**
	 * Changing the panel size.
	 */
	public void changePanelSize() {
		if (SettingsPanel.getStage() == 1 || SettingsPanel.getStage() == 2)
			Program.cameraPanel.setPreferredSize(Program.cameraPanel.getCameraPanelDimension());
		else if (SettingsPanel.getStage() == 3 || SettingsPanel.getStage() == 4)
			Program.imagePanel.setPreferredSize(Program.cameraPanel.getCameraPanelDimension());
		Program.settingsPanel.setPreferredSize(Program.settingsPanel.getPreferredSize());
	}
	public boolean isMOUSE_RELEASED_BOOLEAN() {
		return MOUSE_RELEASED_BOOLEAN;
	}
	public void setMOUSE_RELEASED_BOOLEAN(boolean mOUSE_RELEASED_BOOLEAN) {
		MOUSE_RELEASED_BOOLEAN = mOUSE_RELEASED_BOOLEAN;
	}
	public static boolean isGET_AREA_BOOLEAN() {
		return GET_AREA_BOOLEAN;
	}
	public static void setGET_AREA_BOOLEAN(boolean gET_AREA_BOOLEAN) {
		GET_AREA_BOOLEAN = gET_AREA_BOOLEAN;
	}
	public String getBackText() {
		return BackText;
	}
	public void setBackText(String backText) {
		BackText = backText;
	}
	public String getNextText() {
		return NextText;
	}
	public void setNextText(String nextText) {
		NextText = nextText;
	}
	public static JMenuItem getQuitMenuItem() {
		return quitMenuItem;
	}
	public void setQuitMenuItem(JMenuItem quitMenuItem) {
		MainFrame.quitMenuItem = quitMenuItem;
	}
}

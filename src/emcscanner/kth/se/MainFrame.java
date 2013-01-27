package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

import com.googlecode.javacv.FrameGrabber.Exception;

/**
 * @param args
 */
public class MainFrame extends JFrame {
	public MyGlassPane glass;
	private boolean toggleFullScreen = false;
	public static JMenuBar menuBar;
	public boolean MOUSE_RELEASED_BOOLEAN = false;
	public static boolean GET_AREA_BOOLEAN = false;
	
	public MainFrame(){
		super("EMC-Scanner");
		
		//Sets what will happen when the frame closes
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					CameraPanel.threadDisplayCamera.stop();
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
		
		glass = new MyGlassPane(Program.frame, menuBar, Program.settingsPanel.frequencyPanel.headerButton, Program.settingsPanel.areaPanel.backButton);
		this.setGlassPane(glass);
		
		this.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override 
			public void mousePressed(MouseEvent arg0) {
				if (GET_AREA_BOOLEAN)
				{
		        	if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
		        	{
		        		if(arg0.getY() >= MainFrame.menuBar.getHeight() && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight())
		        		{
		        			glass.setVisible(true);
			        		glass.cursorPressed = new Point(arg0.getPoint());
			        		glass.cursorReleased.x = glass.cursorPressed.x;
			        		glass.cursorReleased.y = glass.cursorPressed.y;
			        		MOUSE_RELEASED_BOOLEAN = false;
		        		}
		        		
		        	}
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (GET_AREA_BOOLEAN)
				{
		        	if(MOUSE_RELEASED_BOOLEAN == false)
		        	{
						if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
			        	{
			        		if(arg0.getY() >= MainFrame.menuBar.getHeight() && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight())
			        		{
				        		MOUSE_RELEASED_BOOLEAN = true;
				        		glass.cursorReleased = new Point(arg0.getPoint());
								glass.repaint();
			        		}
			        		else if(arg0.getY() <= MainFrame.menuBar.getHeight())
			        		{
			        			MOUSE_RELEASED_BOOLEAN = true;
			        			glass.cursorReleased.x = arg0.getX();
			        			glass.cursorReleased.y = (int) MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        		else
			        		{
			        			MOUSE_RELEASED_BOOLEAN = true;
			        			glass.cursorReleased.x = arg0.getX();
			        			glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        	}
			        	else
			        	{
			        		if(arg0.getY() >= MainFrame.menuBar.getHeight() && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight())
			        		{
				        		MOUSE_RELEASED_BOOLEAN = true;
				        		glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth(); 
				        		glass.cursorReleased.y = arg0.getY();
								glass.repaint();
			        		}
			        		else if(arg0.getY() <= MainFrame.menuBar.getHeight())
			        		{
			        			MOUSE_RELEASED_BOOLEAN = true;
			        			glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			glass.cursorReleased.y = (int) MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        		else
			        		{
			        			//OUT_OF_BOUNDS = true;
			        			MOUSE_RELEASED_BOOLEAN = true;
			        			glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight()+ MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        	}
		        	}
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent evt) {
			}
			public void mouseDragged(MouseEvent arg0){
				if (GET_AREA_BOOLEAN)
				{
					if (MOUSE_RELEASED_BOOLEAN == false)
					{
						if(arg0.getX() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth())
			        	{
			        		if(arg0.getY() >= MainFrame.menuBar.getHeight() && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight())
			        		{
			        			glass.cursorReleased = new Point(arg0.getPoint());
								glass.repaint();
			        		}
			        		else if(arg0.getY() <= MainFrame.menuBar.getHeight())
			        		{
			        			glass.cursorReleased.x = arg0.getX();
			        			glass.cursorReleased.y = (int) MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        		else
			        		{
			        			glass.cursorReleased.x = arg0.getX();
			        			glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        	}
			        	else
			        	{
			        		if(arg0.getY() >= MainFrame.menuBar.getHeight() && arg0.getY() <= Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight() + MainFrame.menuBar.getHeight())
			        		{
			        			glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth(); 
			        			glass.cursorReleased.y = arg0.getY();
								glass.repaint();
			        		}
			        		else if(arg0.getY() <= MainFrame.menuBar.getHeight())
			        		{
			        			glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			glass.cursorReleased.y = (int) MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        		else
			        		{
			        			glass.cursorReleased.x = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getWidth();
			        			glass.cursorReleased.y = (int) Program.cameraPanel.CAMERA_VIEW_BOUNDERYS_DIMENSION.getHeight()+ MainFrame.menuBar.getHeight();
								glass.repaint();
			        		}
			        	}
					}
				}
			}
		});
	}
}

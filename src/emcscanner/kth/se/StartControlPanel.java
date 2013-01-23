package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @param args
 */
public class StartControlPanel extends JPanel {
	public StartControlPanel() {	
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2), 0));
			
		/* Creates a next button and butts it in the corner */
		JButton nextButton = new JButton("Next");
		nextButton.setPreferredSize(new Dimension(100, 40));
			
		nextButton.addActionListener(new ActionListener() 
		{
	        public void actionPerformed(ActionEvent e)
	        {
	            /* Execute when button is pressed */
	        	MainPanel.setStages(Program.cameraPanel, Program.settingsPanel);
	        }
	    });
		JPanel holder = new JPanel(new BorderLayout());
		
		this.add(holder, BorderLayout.PAGE_END);
		holder.add(nextButton, BorderLayout.LINE_END);
	}
}


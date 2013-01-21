package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SettingsSubPanel extends JPanel{
	public ImageIcon icon;
	public SettingsSubPanel(String stepText, String panelText, String panelInformation, int panelNumber){
		this.setLayout(new BorderLayout());

		if (SettingsPanel.step == panelNumber ){
			icon = new ImageIcon("image/PanelBlue.png");
		}
		else if (SettingsPanel.step > panelNumber){
			icon = new ImageIcon("image/PanelGreen.png");
		}
		else {
			icon = new ImageIcon("image/PanelGray.png");
		}
		
		JLabel step = new JLabel(stepText);
		step.setPreferredSize(new Dimension(50,40));
		
		JLabel chooseFrequency = new JLabel(){
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 4, null);
		        super.paintComponent(g);
			}
		};
		
		chooseFrequency.setText(panelText);

		chooseFrequency.setHorizontalAlignment( SwingConstants.CENTER );
		chooseFrequency.setPreferredSize(new Dimension(350, 40));
		chooseFrequency.setToolTipText(panelInformation);
		chooseFrequency.setOpaque(false);
		
		
		JPanel continer = new JPanel();
		continer.setLayout(new BorderLayout());
		continer.add(step, BorderLayout.NORTH );
		continer.setPreferredSize(new Dimension(50, 50));
		this.add(continer, BorderLayout.WEST);
		
		JPanel continer2 = new JPanel();
		continer2.setLayout(new BorderLayout());
		continer2.add(chooseFrequency, BorderLayout.NORTH );
		continer2.setPreferredSize(new Dimension(320, 200));
		this.add(continer2, BorderLayout.EAST);
		
		if ( panelNumber == 1 ){
			continer2.add(FrequencyPanel(), BorderLayout.CENTER);
		}
		else if ( panelNumber == 2 ){
			
		}
		else if ( panelNumber == 3 ){
			
		}
		else {
			
		}
	}

	private JPanel FrequencyPanel(){
		JPanel frequencyPanel = new JPanel();
		frequencyPanel.setLayout(new BorderLayout());
		frequencyPanel.setPreferredSize(new Dimension(50, 50));
		
		JLabel text = new JLabel("<html> Note: The signal generator’s " + "<br>"+
								" Intervall is 0.1 – 6000 MHz. </html>");
		text.setHorizontalAlignment( SwingConstants.CENTER );
		
		final ImageIcon buttonIcon = new ImageIcon("image/ButtonGray.png");
		
		JLabel nextButton = new JLabel("<html><font color=rgb(152,152,152)>Next</font></html>"){
			public void paintComponent(Graphics g) {
				g.drawImage(buttonIcon.getImage(), 0, 10, null);
		        super.paintComponent(g);
			}
		};
		nextButton.setHorizontalAlignment( SwingConstants.CENTER );
		nextButton.setPreferredSize(new Dimension(90, 50));

		JPanel continer = new JPanel();
		continer.setLayout(new BorderLayout());
		continer.add(nextButton, BorderLayout.EAST);
		
		JPanel continer2 = new JPanel();
		continer2.setLayout(new BorderLayout());
		continer2.add(text, BorderLayout.CENTER);
		
		JLabel text3 = new JLabel("<html> 0.1 ≤ </html>");
		
		
		JPanel continer3 = new JPanel();
		continer2.setLayout(new BorderLayout());
		JLabel text2 = new JLabel("<html> <font color=rgb(51,102,152)> MHz </font> ≤ 6000 </html>");
		continer3.add(text3);
		continer3.add(text2);

		
		frequencyPanel.add(continer2, BorderLayout.NORTH);
		frequencyPanel.add(continer3, BorderLayout.NORTH);
		frequencyPanel.add(continer, BorderLayout.SOUTH);
		
		return frequencyPanel;
		
	}
}

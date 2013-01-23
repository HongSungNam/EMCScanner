package emcscanner.kth.se;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * SettingsSubPanel
 * 
 * SubPanel for SettingsPanel
 * Used for deciding the scanners settings before scanning.
 */
public class SettingsSubPanel extends JPanel{
	/* User selected values */
	public float frequency;
	
	/* Global values used by the program */
	public ImageIcon stepHeaderEnabledImageIcon;
	public ImageIcon stepHeaderDisabledImageIcon;
	public JButton header;
	public Border border;
	public JPanel continerHeaderAndPanel;
	
	/** 
	 * SettingsSubPanel
	 * 
	 * @param String stepText
	 * @param String panelInformation
	 * @param int panelNumber
	 */
	public SettingsSubPanel(String stepText, String panelInformation, int panelNumber){
		this.setLayout(new FlowLayout());
		this.setMinimumSize(new Dimension(400, 230));
		
		/* Header for the different sub settings panels. */
		header = new JButton();
		if (SettingsPanel.step == panelNumber ){
			if (panelNumber == 1){
				this.stepHeaderEnabledImageIcon = new ImageIcon("image/PanelBlueFrequency.png");
				this.stepHeaderDisabledImageIcon = new ImageIcon("image/PanelGrayFrequency.png");
				this.header.setEnabled(true);
			}
			this.border = BorderFactory.createLineBorder(new Color(100,150,255));
		}
		else {
			if (panelNumber == 2)
			{
				this.stepHeaderEnabledImageIcon = new ImageIcon("image/PanelBlueArea.png");
				this.stepHeaderDisabledImageIcon = new ImageIcon("image/PanelGrayArea.png");
				this.header.setEnabled(false);
			}
			else if (panelNumber == 3)
			{
				this.stepHeaderEnabledImageIcon = new ImageIcon("image/PanelBlueDensity.png");
				this.stepHeaderDisabledImageIcon = new ImageIcon("image/PanelGrayDensity.png");
				this.header.setEnabled(false);
			}
			else if (panelNumber == 4)
			{
				this.stepHeaderEnabledImageIcon = new ImageIcon("image/PanelBlueFileName.png");
				this.stepHeaderDisabledImageIcon = new ImageIcon("image/PanelGrayFileName.png");
				this.header.setEnabled(false);
			}
			this.border = BorderFactory.createLineBorder(new Color(152,152,152));
		}
		this.header.setPreferredSize(new Dimension(355, 40));
		this.header.setToolTipText(panelInformation);
		this.header.setOpaque(false);
		this.header.setContentAreaFilled(false);
		this.header.setBorderPainted(false);
		this.header.setIcon(stepHeaderEnabledImageIcon);
		this.header.setDisabledIcon(stepHeaderDisabledImageIcon);
		
		/* Creates a Label for the step numbers. */
		JLabel step = new JLabel(stepText);
		step.setPreferredSize(new Dimension(50,40));
		step.setLayout(new BorderLayout());
		
		/* Container to make it possible for the step label to be to 
		   the south and north of the header and the settings panels */
		JPanel continerStep = new JPanel();
		continerStep.setLayout(new BorderLayout());
		continerStep.add(step, BorderLayout.NORTH );
		continerStep.setPreferredSize(new Dimension(50, 180));
		this.add(continerStep, BorderLayout.WEST);
		
		/* A panel for the Header and the sup settings panels. */
		continerHeaderAndPanel = new JPanel();
		continerHeaderAndPanel.setLayout(new BorderLayout());
		continerHeaderAndPanel.add(header, BorderLayout.NORTH );
		continerHeaderAndPanel.setPreferredSize(new Dimension(322, 180));
		
		/* Adds the frequency panel to the container */
		if ( panelNumber == 1 ){
			continerHeaderAndPanel.add(FrequencyPanel(), BorderLayout.NORTH);
		}
		
		this.add(continerHeaderAndPanel, BorderLayout.EAST);
	}

	private JPanel FrequencyPanel(){
		final JPanel frequencyPanel = new JPanel();
		frequencyPanel.setLayout(new BorderLayout());
		frequencyPanel.setPreferredSize(new Dimension(50, 50));
		
		/* Constants for the FrequencyPanel */
		int frequencyInputLimit = 6;
		boolean nextButtonEnebeld = false;
		
		final JTextField floatInputTextField = new JTextField(4);
		
		/* Boundary explanatory labels */
		final JLabel text2 = new JLabel("<html> 0.1 ≤ </html>");
		final JLabel text3 = new JLabel("<html><font color = rgb(100,150,255)>MHz</font>"+" ≤ 6000 </html>");
		text2.setHorizontalAlignment( SwingConstants.CENTER );
		text3.setHorizontalAlignment( SwingConstants.CENTER );
		
		/* Warning Label with text centered */
		final JLabel text = new JLabel("<html> <font color = rgb(255, 0, 0)> Note</font>: The signal generator’s " + "<br>"+
								" Intervall is 0.1 – 6000 MHz. </html>");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		
		/* Imports the different images for the different button stages. */
		ImageIcon nextButtonEnabledIcon = new ImageIcon("image/ButtonBlueNext.png");
		ImageIcon nextButtonDisabledIcon = new ImageIcon("image/ButtonGrayNext.png");
		ImageIcon nextButtonBlueNextPrestIcon = new ImageIcon("image/ButtonBlueNextPrest.png");
		ImageIcon nextButtonGrayNextPrestIcon = new ImageIcon("image/ButtonGrayNextPrest.png");
		
		/* Next JButton */
		final JButton nextButton = new JButton();
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setToolTipText("You need to write a number between 0.1 and 6000 befor you can continue");
		nextButton.setPreferredSize(new Dimension(90, 50));
		nextButton.setEnabled(false);
		nextButton.setIcon(nextButtonEnabledIcon);
		nextButton.setDisabledIcon(nextButtonDisabledIcon);
		nextButton.setPressedIcon(nextButtonBlueNextPrestIcon);
		nextButton.setDisabledSelectedIcon(nextButtonGrayNextPrestIcon);
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon greenBar = new ImageIcon("image/PanelGreenFrequency.png");
	    		header.setIcon(greenBar);

	    		Border greenBorder = BorderFactory.createLineBorder(new Color(150,255,80));
				frequencyPanel.setBorder(greenBorder);
				frequencyPanel.setPreferredSize(new Dimension(20, 20));
				
				floatInputTextField.setVisible(false);
				text.setVisible(false);
				text2.setVisible(false);
				text3.setVisible(false);
				nextButton.setVisible(false);
				
				continerHeaderAndPanel.setPreferredSize(new Dimension(322, 100));
			}
		});
		
		/* Containers for setting up GUI */
		JPanel continer  = 	new JPanel();
		JPanel continer2 = 	new JPanel();
		JPanel continer3 = 	new JPanel();
		JPanel continer4 = 	new JPanel();
		JPanel continer5 = 	new JPanel();
		
		/* Setting containers Layouts for the right GUI look. */
		continer.setLayout(new BorderLayout());
		continer2.setLayout(new BorderLayout());
		continer3.setLayout(new FlowLayout());
		continer4.setLayout(new FlowLayout());
		continer5.setLayout(new BorderLayout());
		
		
		/* Light blue border for the float input text field */
		Border borderIntInput = BorderFactory.createLineBorder(new Color(100,150,255));
		
		/* Text field for importing frequency from user only values from 0.1 to 6000 */
		floatInputTextField.setPreferredSize(new Dimension(20,20));
		floatInputTextField.setDocument(new LengthRestrictedDocument(frequencyInputLimit));
		floatInputTextField.setBorder(borderIntInput);
		floatInputTextField.getDocument().addDocumentListener(new DocumentListener () {
        	public void insertUpdate(DocumentEvent aEvent) {
        		checkFloat();
	        }
	        public void removeUpdate(DocumentEvent aEvente) {
	        	checkFloat();
	        }
	        public void changedUpdate(DocumentEvent aEvent) {
	        	checkFloat();
	        }
	        public void checkFloat(){
	        	try{
	        		float value = Float.valueOf(floatInputTextField.getText());;
		        	
					if ((0.1 <= value) && (value <= 6000))
			    	{
			    		nextButton.setEnabled(true);
			    		
			    		ImageIcon blueBar = new ImageIcon("image/PanelBlueFrequency.png");
			    		header.setIcon(blueBar);
			    		Border blueBorder = BorderFactory.createLineBorder(new Color(100,150,255));
						frequencyPanel.setBorder(blueBorder);
						floatInputTextField.setBorder(blueBorder);
						text2.setText("<html>0.1 ≤ </html>");
						text3.setText("<html><font color = rgb(100,150,255)>MHz</font>"+" ≤ 6000</html>");
			    	}
					else{
						nextButton.setEnabled(false);
			    		ImageIcon redBar = new ImageIcon("image/PanelRedFrequency.png");
			    		header.setIcon(redBar);
						Border redBorder = BorderFactory.createLineBorder(new Color(255,0,0));
						frequencyPanel.setBorder(redBorder);
						floatInputTextField.setBorder(redBorder);
						if (0.1 > value)
				    	{
							text2.setText("<html><font color = rgb(255,0,0)>0.1 ≤</font></html>");
				    	}
						if (6000 < value)
				    	{
							text3.setText("<html><font color = rgb(100,150,255)>MHz</font>"+"<font color = rgb(255,0,0)> ≤ 6000</font></html>");
				    	}
					}
	        	} catch (Exception e) {
	        		nextButton.setEnabled(false);
		    		ImageIcon redBar = new ImageIcon("image/PanelRedFrequency.png");
		    		header.setIcon(redBar);
					Border redBorder = BorderFactory.createLineBorder(new Color(255,0,0));
					frequencyPanel.setBorder(redBorder);
					floatInputTextField.setBorder(redBorder);
	        	}
	        }
	    });
		
		/* Sets containers sizes */
		//continer2.setPreferredSize(new Dimension(200,50));
		//continer3.setPreferredSize(new Dimension(50,30));
		
		/* Sets container backgrounds to white instead of gray for contrast */
		continer5.setBackground(Color.WHITE);
		continer4.setBackground(Color.WHITE);
		continer3.setBackground(Color.WHITE);
		continer2.setBackground(Color.WHITE);
		continer.setBackground(Color.WHITE);
		frequencyPanel.setBackground(Color.WHITE);
		
		/* Adds Buttons and labels to containers and adds containers
		   to containers and in the ends the containers to the panel */
		continer.add(nextButton, BorderLayout.EAST);
		continer2.add(text, BorderLayout.CENTER);
		continer3.add((floatInputTextField), BorderLayout.CENTER);
		continer4.add(text2, FlowLayout.LEFT);
		continer4.add(continer3, FlowLayout.CENTER); 
		continer4.add(text3, FlowLayout.RIGHT);
		continer5.add(continer2, BorderLayout.NORTH);
		continer5.add(continer4, BorderLayout.CENTER);
		frequencyPanel.add(continer5, BorderLayout.NORTH);
		frequencyPanel.add(continer, BorderLayout.SOUTH);
		frequencyPanel.setBorder(border);
		
		
		return frequencyPanel;
	}
	
	//nextButton.setDisabledSelectedIcon(disabledSelectedIcon);
	//nextButton.setRolloverIcon(rolloverIcon);
	//nextButton.setRolloverSelectedIcon(rolloverSelectedIcon);
	//nextButton.setSelectedIcon(selectedIcon);
	
	//setRolloverEnabled
	//setIcon
	//setEnabled
			
}
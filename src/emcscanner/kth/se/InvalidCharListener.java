package emcscanner.kth.se;

import java.awt.event.*;
  
public class InvalidCharListener extends KeyAdapter {  
	private char[] invalidChars = null;  
	public InvalidCharListener(char[] chars) {  
		invalidChars = chars;  
	}  
	public void keyTyped(KeyEvent e) {  
		char c = e.getKeyChar();  
		for(int i = 0; i < invalidChars.length; i++) 
		{  
			if(c == invalidChars[i]) 
			{  
				java.awt.Toolkit.getDefaultToolkit().beep();  
				e.consume();  
			}  
		}  
	}  
}    
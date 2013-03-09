package emcscanner.kth.se;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public final class LengthRestrictedDocument extends PlainDocument {
	/**
	 * ID
	 */
	private static final long serialVersionUID = 2211458772860445004L;
	private final int limit;
	
	  public LengthRestrictedDocument(int limit) {
		  this.limit = limit;
	  }
		
	  @Override
	  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str == null)
				return;	
			if ((getLength() + str.length()) <= limit) 
				super.insertString(offs, str, a);
	  }
}
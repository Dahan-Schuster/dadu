package objetos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Documento extends PlainDocument {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String regex;
	private int tam;
	
	public Documento(String regex, int tam) {
		super();
		this.regex = regex;
		this.tam = tam;
	}
	
	@Override
	public void insertString(int arg0, String str, AttributeSet arg2) throws BadLocationException {
		if (this.getLength() + str.length() > tam) {
			super.insertString(arg0, "", arg2);
		} else {
			super.insertString(arg0, str.replaceAll(regex, ""), arg2);
		}
		
	}
}

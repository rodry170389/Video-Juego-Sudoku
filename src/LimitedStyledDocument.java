
import javax.swing.text.*; 
import java.awt.Toolkit;

/**
 * Clase LimitedStyledDocument: Clase que limita los caracteres 
 * de las areas de texto y que extiende de DefaultStyledDocument.
 * @see DefaulStyledDocument
 */

@SuppressWarnings("serial")
public class LimitedStyledDocument extends DefaultStyledDocument  {
	
	/** Almacena el maximo de caracteres que puede tener un area de texto */
	private int maxCharacters;
	
	/**
     * Constructor que inicializa el atributo maxCharacter.
     * @param maxChars
     */
    public LimitedStyledDocument(int maxChars) {
        maxCharacters = maxChars;
    }
    
    /**
	 * Método que limita los carácteres. 
	 * @param off
	 * @param str
	 * @param a
	 */	
    public void insertString(int offs, String str, AttributeSet a) 
        throws BadLocationException {
        if ((getLength() + str.length()) <= maxCharacters)
            super.insertString(offs, str, a);
        else
            Toolkit.getDefaultToolkit().beep();
    }
}// Fin de la clase

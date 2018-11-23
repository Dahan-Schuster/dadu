package objetos;

public class ValorIncorretoException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1702039703718132312L;

	public ValorIncorretoException(String metodo) {
		super("Valor incorreto no método "+metodo);
	}
}

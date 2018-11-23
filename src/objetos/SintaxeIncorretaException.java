package objetos;

public class SintaxeIncorretaException extends ArithmeticException {
	
	private static final long serialVersionUID = 5344379719930932529L;
	
	public SintaxeIncorretaException(String msg) {
		super("ERRO: "+msg);
	}	
}

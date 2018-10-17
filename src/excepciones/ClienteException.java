package excepciones;

public class ClienteException extends Exception {

	private static final long serialVersionUID = 3197334867086748501L;

	public ClienteException(String mensaje){
		super(mensaje);
	}
}

package excepciones;

public class NegocioException extends Exception {
	
	private static final long serialVersionUID = -6245766774882521361L;

	public NegocioException(String mensaje){
		super(mensaje);
	}

}
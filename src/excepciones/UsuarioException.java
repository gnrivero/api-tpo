package excepciones;

public class UsuarioException extends Exception {

	private static final long serialVersionUID = -5681818342699360460L;
	
	public UsuarioException(String mensaje){
		super(mensaje);
	}
}

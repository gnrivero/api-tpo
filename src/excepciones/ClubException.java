package excepciones;

public class ClubException extends Exception {

	private static final long serialVersionUID = -5681818342699360460L;
	
	public ClubException(String mensaje){
		super(mensaje);
	}
}

package view;

public class AuditoriaReclamoView {
	
	private String datoAnterior;
	private String datoNuevo;
	private String username;
	private String fecha; 
	
	public AuditoriaReclamoView(){}
	
	public AuditoriaReclamoView(String datoAnterior, String datoNuevo, String username, String fecha){
		this.datoAnterior = datoAnterior;
		this.datoNuevo = datoNuevo;
		this.username = username;
		this.fecha = fecha; 
	}
	
	@Override
	public String toString() {
		return "[" + this.fecha + "]El reclamo cambio de " + this.datoAnterior + " a " + this.datoNuevo + " por Usr: " + this.username ;
	}

	public String getDatoAnterior() {
		return datoAnterior;
	}

	public void setDatoAnterior(String datoAnterior) {
		this.datoAnterior = datoAnterior;
	}

	public String getDatoNuevo() {
		return datoNuevo;
	}

	public void setDatoNuevo(String datoNuevo) {
		this.datoNuevo = datoNuevo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}	
}
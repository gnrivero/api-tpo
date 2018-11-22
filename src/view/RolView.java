package view;

public class RolView {
	
	private Integer idRol;
	private String descripcion;
	
	public RolView(Integer idRol, String descripcion){
		 this.idRol = idRol;
		 this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {			
		if (this.idRol == null)
			return this.descripcion;
		
		return "ID: " + this.idRol+ " - " + this.descripcion;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

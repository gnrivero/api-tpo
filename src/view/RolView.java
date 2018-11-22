package view;

public class RolView {
	
	public Integer idRol;
	public String descripcion;
	
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
}

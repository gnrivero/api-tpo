package model;

import java.util.List;

public class Rol {
	
	private int idRol;
	private String descripcion;
	private List<TipoDeReclamo> tiposDeReclamo;
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<TipoDeReclamo> getTiposDeReclamo() {
		return tiposDeReclamo;
	}
	public void setTiposDeReclamo(List<TipoDeReclamo> tiposDeReclamo) {
		this.tiposDeReclamo = tiposDeReclamo;
	}

	public boolean soy(int idRol){		
		return true;
	}

} 

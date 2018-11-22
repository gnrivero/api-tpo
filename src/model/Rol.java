package model;

import java.util.List;

import view.RolView;

public class Rol {
	
	private Integer idRol;
	private String descripcion;
	private List<TipoDeReclamo> tiposDeReclamo;
	
	public Rol(Integer idRol, String descripcion, List<TipoDeReclamo> tiposDeReclamo){
		this(descripcion, tiposDeReclamo);
		this.idRol = idRol;
	}
	
	public Rol(String descripcion, List<TipoDeReclamo> tiposDeReclamo){
		this.descripcion = descripcion;
		this.tiposDeReclamo = tiposDeReclamo;		 
	}
	
	public Rol(Integer idRol, String descripcion){
		this.idRol = idRol;
		this.descripcion = descripcion;		 
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
	
	public List<TipoDeReclamo> getTiposDeReclamo() {
		return tiposDeReclamo;
	}
	
	public void setTiposDeReclamo(List<TipoDeReclamo> tiposDeReclamo) {
		this.tiposDeReclamo = tiposDeReclamo;
	}

	public boolean soy(int idRol){		
		return true;
	}
	
	public void guardar(){
		
	}
	
	public RolView toView(){
		
		return new RolView(this.idRol, this.descripcion);
	}

} 

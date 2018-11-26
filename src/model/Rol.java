package model;

import java.util.List;

import view.RolView;

public class Rol {
	
	private Integer idRol;
	private String descripcion;
	private List<TipoDeReclamo> tiposDeReclamo;
	private List<Permiso> permisos;
	
	private static final Integer ADMIN = 1;
	
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
	
	//Metodos
	public boolean tienePermiso(Modulo modulo, int modoDeAcceso){
		
		if(ADMIN.equals(this.idRol))
			return true;
		
		for(Permiso permiso : this.permisos){
			if(modulo.equals(permiso.getModulo())&& permiso.getValor().equals(modoDeAcceso)){				
				return true;
			}
		}
		
		return false;
	}
	
	
	//Getters & Setters
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

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}
	
	public RolView toView(){
		return new RolView(this.idRol, this.descripcion);
	}

} 
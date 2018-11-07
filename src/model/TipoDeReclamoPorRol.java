package model;

import dao.RolTipoDeReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class TiposDeReclamoPorRol {
	
	private Integer nroRol;
	private Integer nroTipoDeReclamo;
	
	public TiposDeReclamoPorRol(Integer nroRol, Integer nroTipoDeReclamo){
		this.nroRol = nroRol;
		this.nroTipoDeReclamo = nroTipoDeReclamo;
	}
	
	public Integer getNroRol() {
		return nroRol;
	}
	public void setNroRol(Integer nroRol) {
		this.nroRol = nroRol;
	}
	public Integer getIdTipoDeReclamo() {
		return nroTipoDeReclamo;
	}
	public void setIdTipoDeReclamo(Integer nroTipoDeReclamo) {
		this.nroTipoDeReclamo = nroTipoDeReclamo;
	}
	
	public void guardar() throws ConexionException, AccesoException {
		TiposDeReclamoPorRolDAO.getInstancia().crear(this);
	}
	
}
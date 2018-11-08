package model;

import excepciones.AccesoException;
import excepciones.ConexionException;

public class RolPorUsuario {
	
	private Integer nroRol;
	private Integer nroUsuario;
	
	public RolPorUsuario(Integer nroRol, Integer nroUsuario){
		this.nroRol = nroRol;
		this.nroUsuario = nroUsuario;
	}
	
	public Integer getNroRol() {
		return nroRol;
	}
	public void setNroRol(Integer nroRol) {
		this.nroRol = nroRol;
	}
	public Integer getNroUsuario() {
		return nroUsuario;
	}
	public void setNroUsuario(Integer nroUsuario) {
		this.nroUsuario = nroUsuario;
	}
	
	public void guardar() throws ConexionException, AccesoException {
		//RolUsuarioDAO.getInstancia().crear(this);
	}
	
}
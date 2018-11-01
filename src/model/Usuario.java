package model;

import java.util.Date;

import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;

public class Usuario {
	
	private Integer idUsuario;
	private String username;
	private String password;
	private Date fechaBaja;
	private Rol rol;
	
	public Usuario(String username, String password, Rol rol){
		this.username = username;
		this.password = password;
		this.rol = rol;
	}
	
	public Usuario(Integer idUsuario, String username, String password, Rol rol, Date fechaBaja) {
		this(username, password, rol);
		this.setIdUsuario(idUsuario);
		this.setFechaBaja(fechaBaja);
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public void guardar() throws ConexionException, AccesoException, NegocioException {
		if (this.getIdUsuario() == null){
			UsuarioDAO.getInstancia().crearUsuario(this);
		}else{
			UsuarioDAO.getInstancia().actualizarUsuario(this);
		}
	}
	
}
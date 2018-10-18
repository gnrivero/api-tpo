package model;

import java.util.Date;

import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Usuario {
	
	private Integer idUsuario;
	private String username;
	private String password;
	private Date fechaBaja;
	
	public Usuario(){}
	
	public Usuario(Integer idUsuario, String username, String password, Date fechaBaja) {		
		this.setIdUsuario(idUsuario);
		this.setUsername(username);
		this.setPassword(password);
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
		
	public void guardar(Usuario usuario) {
		
		try {
			UsuarioDAO.getInstancia().grabarUsuario(usuario);
		} catch (ConexionException | AccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}
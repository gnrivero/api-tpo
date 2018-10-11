package model;

import java.util.Date;

public class Usuario {
	
	private Integer idIsuario;
	private String username;
	private String password;
	private Date fechaBaja;
	
	public Integer getIdIsuario() {
		return idIsuario;
	}
	public void setIdIsuario(Integer idIsuario) {
		this.idIsuario = idIsuario;
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
	
}
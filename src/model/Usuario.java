package model;

import java.text.DateFormat;
import java.util.Date;

public class Usuario {
	
	private Integer idUsuario;
	private String username;
	private String password;
	private Date fechaBaja;
	
	public Usuario(int idUsuario, String username, String password, String fechaBaja) {
		DateFormat df = DateFormat.getDateInstance();
		
		this.setIdUsuario(idUsuario);
		this.setUsername(username);
		this.setPassword(password);
		
		try {
			Date d = df.parse(fechaBaja);
			this.setFechaBaja(d);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	
}
package view;

import model.Rol;

public class UsuarioView {
	public Integer idUsuario;
	public String username;
	public String password;
	public String fechaBaja;
	public Rol rol;
	
	public UsuarioView() { }
	
	public UsuarioView(Integer idUsuario, String username, String password, String fechaBaja, Rol rol){
		 this.idUsuario = idUsuario;
		 this.username = username;
		 this.password = password;
		 this.fechaBaja = fechaBaja;
		 this.rol = rol;
	}
	
	public UsuarioView(String username) {
		this.username = username;
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

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		if (this.idUsuario == null)
			return this.username;
		
		return "ID: " + this.idUsuario + " - " + this.username;
	}
}

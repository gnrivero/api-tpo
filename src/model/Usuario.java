package model;

import java.util.Date;

import dao.DAOhelper;
import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import view.UsuarioView;

public class Usuario {
	
	private Integer idUsuario;
	private String username;
	private String password;
	private Date fechaBaja;
	private Rol rol;
	
	public Usuario(Integer idUsuario, String username, String password, Date fechaBaja){
		this.idUsuario = idUsuario;
		this.username = username;
		this.password = password;
		this.fechaBaja = fechaBaja;
	}
	
	public Usuario(String username, String password, Rol rol){
		this.username = username;
		this.password = password;
		this.rol = rol;
	}
	
	public Usuario(Integer idUsuario, String username, String password, Date fechaBaja, Rol rol) {
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
	
	public Integer guardar() throws ConexionException, AccesoException, NegocioException {
		if (this.getIdUsuario() == null){
			this.idUsuario = UsuarioDAO.getInstancia().crearUsuario(this);
		}else{
			UsuarioDAO.getInstancia().actualizarUsuario(this);
		}
		
		return this.idUsuario;
	}
	
	public UsuarioView toView(){
		
		String fechaBaja = (this.fechaBaja == null) ? null :  DAOhelper.getDiaMesAnioDateFormat().format(this.fechaBaja);
		
		return new UsuarioView(this.idUsuario, this.username, this.password, fechaBaja, this.rol);
	}
	
}
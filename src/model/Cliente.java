package model;

import java.util.Date;

import dao.ClienteDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Cliente {
	
	private Integer idCliente;
	private String nombre;
	private String domicilio;
	private String telefono;
	private String mail;
	private Date fechaBaja;
	
	public Cliente(String nombre, String domicilio, String telefono, String mail) {		
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
	}
	
	public Cliente(Integer idCliente, String nombre, String domicilio, String telefono, String mail) {		
		this(nombre, domicilio, telefono, mail);
		this.idCliente = idCliente;	
	}
	
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMail() {
		return mail;
	}
	
	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	public void darDeBaja() throws ConexionException, AccesoException{
		
		this.setFechaBaja(new Date());
		
		this.guardar();
	}

	public void guardar() throws ConexionException, AccesoException{		
		if(this.getIdCliente() == null){
			ClienteDAO.getInstancia().crearCliente(this);
		}else{
			ClienteDAO.getInstancia().actualizarCliente(this);
		}		
	}
}

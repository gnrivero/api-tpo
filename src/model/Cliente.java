package model;

public class Cliente {

	private Integer idCliente;
	private String nombre;
	private String domicilio;
	private String telefono;
	private String mail;
	
	public Cliente(int idCliente, String nombre, String domicilio, String telefono, String mail) {
		this.setIdCliente(idCliente);
		this.setNombre(nombre);
		this.setDomicilio(domicilio);
		this.setTelefono(telefono);
		this.setMail(mail);
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
}

package view;

public class ClienteView {
	
	private Integer idCliente;
	private String nombre;
	private String cuit;
	private String domicilio;
	private String telefono;
	private String mail;
	private String fechaBaja;
	
	public ClienteView(){ }
	
	public ClienteView(Integer idCliente, String nombre, String cuit, String domicilio, String telefono, String mail, String fechaBaja){
		 this.idCliente = idCliente;
		 this.nombre = nombre;
		 this.cuit = cuit;
		 this.domicilio = domicilio;
		 this.telefono = telefono;
		 this.mail = mail;
		 this.fechaBaja = fechaBaja;
	}
	
	public ClienteView(String nombre){
		this.nombre = nombre;
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	@Override
	public String toString() {
		if (this.idCliente==null)
			return this.nombre;
		
		return "ID: " + this.idCliente + " - " + this.nombre;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
	        return true;
	    }
	    if (obj instanceof ClienteView) {
	    	ClienteView obj2 = (ClienteView) obj;    	    	
	        return this.getIdCliente() == obj2.getIdCliente();
	    }
	    return false;
	}
}
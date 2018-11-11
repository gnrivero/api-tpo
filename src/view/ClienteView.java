package view;

public class ClienteView {
	
	public Integer idCliente;
	public String nombre;
	public String domicilio;
	public String telefono;
	public String mail;
	public String fechaBaja;
	
	public ClienteView(Integer idCliente, String nombre, String domicilio, String telefono, String mail, String fechaBaja){
		 this.idCliente = idCliente;
		 this.nombre = nombre;
		 this.domicilio = domicilio;
		 this.telefono = telefono;
		 this.mail = mail;
		 this.fechaBaja = fechaBaja;
	}
	
	@Override
	public String toString() {		
		return "ID: " + this.idCliente + " - " + this.nombre;
	}

}
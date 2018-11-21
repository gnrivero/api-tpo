package view;

public class ReclamoView {
	
	public Integer nroReclamo;
	public String descripcion;
	public String tipoDeReclamo;
	public String estadoDeReclamo;
	public String fechaDeReclamo;
	public String fechaDeCierre;
	public String cliente;
	
	public String zona;
	
	public String producto;
	public String cantidad;
	
	@Override
	public String toString() {
		return "[Nro. Reclamo: " + this.nroReclamo + 
				" Descripcion: " + this.descripcion +
				" Tipo: " + this.tipoDeReclamo +
				" Estado: " +  this.estadoDeReclamo +
				" Fecha: " +  this.fechaDeReclamo + 
				" Fecha de Cierre: " + this.fechaDeCierre +
				" Cliente: " +  this.cliente +
				"]";
	}
		
}
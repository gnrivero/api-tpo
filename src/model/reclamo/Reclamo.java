package model.reclamo;

import java.util.Date;

import model.Cliente;
import model.Producto;

public abstract class Reclamo {
	
	//protected List<AuditoriaReclamo> auditoria;
	protected Integer nroReclamo;
	protected Date fecha;
	protected Date fechaCierre;
	protected Cliente cliente;
	protected String Descripcion;
	protected String estado;
	protected Producto producto;
	protected Integer tipoDeReclamo;
	
	public Integer getNroReclamo() {
		return nroReclamo;
	}
	public void setNroReclamo(Integer nroReclamo) {
		this.nroReclamo = nroReclamo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getTipoDeReclamo() {
		return tipoDeReclamo;
	}
	public void setTipoDeReclamo(Integer tipoDeReclamo) {
		this.tipoDeReclamo = tipoDeReclamo;
	}
	
	//Métodos
	public boolean soy(Integer nroReclamo){		
		return (this.nroReclamo.equals(nroReclamo));
	}
	
	//Relacionados con composite
	abstract public void addHoja(Reclamo reclamo);
	
	abstract public void removeHoja(Reclamo reclamo);
	
	abstract public void getReclamos(Reclamo reclamo);
	
	
}

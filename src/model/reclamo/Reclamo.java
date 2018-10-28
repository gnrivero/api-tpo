package model.reclamo;

import java.util.Date;

import model.Cliente;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;

public abstract class Reclamo {
	
	//protected List<AuditoriaReclamo> auditoria;
	protected Integer nroReclamo;
	protected String descripcion;
	protected TipoDeReclamo tipoDeReclamo;
	protected EstadoDeReclamo estado;
	protected Date fecha;
	protected Date fechaCierre;
	protected Cliente cliente;
	
	//Constructor Default
	public Reclamo(){}
	
	//Constructor para nuevos reclamos
	public Reclamo(String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente){
		this.descripcion = descripcion;
		this.tipoDeReclamo = tipoDeReclamo;
		this.estado = EstadoDeReclamo.INGRESADO;
		this.fecha = new Date();
		this.cliente = cliente;
	}
	
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
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public EstadoDeReclamo getEstado() {
		return estado;
	}
	public void setEstado(EstadoDeReclamo estado) {
		this.estado = estado;
	}
	public TipoDeReclamo getTipoDeReclamo() {
		return tipoDeReclamo;
	}
	public void setTipoDeReclamo(TipoDeReclamo tipoDeReclamo) {
		this.tipoDeReclamo = tipoDeReclamo;
	}
	
	//Métodos
	public boolean soy(Integer nroReclamo){		
		return (this.nroReclamo.equals(nroReclamo));
	}
	
	public boolean estaCerrado(){
		return EstadoDeReclamo.CERRADO.equals(this.estado);
	}
	
	public void tratar() {
		this.setEstado(EstadoDeReclamo.EN_TRATAMIENTO);
	}
	
	public void cerrar() throws Exception {
		if(EstadoDeReclamo.EN_TRATAMIENTO.equals(this.estado)){
			this.setEstado(EstadoDeReclamo.CERRADO);
			this.setFechaCierre(new Date());
		}else{
			throw new Exception("No se puede pasar del estado actual a Cerrado");
		}
	}	
	
	
	abstract public void guardar();
	
	//Relacionados con composite
	abstract public void addHoja(Reclamo reclamo);
	
	abstract public void removeHoja(Reclamo reclamo);
	
	abstract public void getReclamos(Reclamo reclamo);
	
	
}
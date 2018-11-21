package model.reclamo;

import java.util.Date;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;
import view.ReclamoView;

public abstract class Reclamo {
	
	//protected List<AuditoriaReclamo> auditoria;
	protected Integer nroReclamo;
	protected String descripcion;
	protected TipoDeReclamo tipoDeReclamo;
	protected EstadoDeReclamo estado;
	protected Date fecha;
	protected Date fechaCierre;
	protected Cliente cliente;
	protected Integer nroReclamoCompuesto;
	
	//Constructor Default
	public Reclamo(){}
	
	//Constructor para nuevos reclamos
	public Reclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente){
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
	public Integer getNroReclamoCompuesto() {
		return nroReclamoCompuesto;
	}
	
	//Métodos
	public boolean soy(Integer nroReclamo){		
		return (this.nroReclamo.equals(nroReclamo));
	}
	
	public boolean estaCerrado(){
		return EstadoDeReclamo.CERRADO.equals(this.estado);
	}
	
	public void comenzarTratamiento() throws ConexionException, AccesoException, NegocioException {
		this.setEstado(EstadoDeReclamo.EN_TRATAMIENTO);
		
		this.guardar();
	}
	
	public void cerrar() throws NegocioException, ConexionException, AccesoException {
		if(EstadoDeReclamo.EN_TRATAMIENTO.equals(this.estado)){
			this.setEstado(EstadoDeReclamo.CERRADO);
			this.setFechaCierre(new Date());
			
			this.guardar();
		}else{
			throw new NegocioException("No se puede pasar del estado " + this.estado.getDenominacion() + " a " + EstadoDeReclamo.CERRADO.getDenominacion());
		}
	}	
	
	
	abstract public Integer guardar() throws ConexionException, AccesoException, NegocioException;
	
	abstract public ReclamoView toView();
	
	//Relacionados con composite
	abstract public void addHoja(Reclamo reclamo);
	
	abstract public void removeHoja(Reclamo reclamo);
	
	abstract public void getReclamos(Reclamo reclamo);
	
	
}
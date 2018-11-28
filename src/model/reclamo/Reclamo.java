package model.reclamo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DAOhelper;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.AuditoriaReclamo;
import model.Cliente;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;
import view.ReclamoView;

public abstract class Reclamo {
	

	protected Integer nroReclamo;
	protected String descripcion;
	protected TipoDeReclamo tipoDeReclamo;
	protected EstadoDeReclamo estado;
	protected Date fecha;
	protected Date fechaCierre;
	protected Cliente cliente;
	protected Integer nroReclamoCompuesto;
	protected List<AuditoriaReclamo> auditoria;
	
	//Constructor Default
	public Reclamo(){}
	
	//Constructor para nuevos reclamos
	public Reclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente){
		this.descripcion = descripcion;
		this.tipoDeReclamo = tipoDeReclamo;
		this.estado = EstadoDeReclamo.INGRESADO;
		this.fecha = new Date();
		this.cliente = cliente;
		this.auditoria = new ArrayList<AuditoriaReclamo>();
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
	public void setNroReclamoCompuesto(Integer nroReclamoCompuesto) {
		this.nroReclamoCompuesto = nroReclamoCompuesto;
	}
	
	public List<AuditoriaReclamo> getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(List<AuditoriaReclamo> auditoria) {
		this.auditoria = auditoria;
	}

	//Metodos
	public boolean estaEnTratamiento(){
		return EstadoDeReclamo.EN_TRATAMIENTO.equals(this.estado);
	}
		
	public boolean estaSolucionado(){
		return EstadoDeReclamo.SOLUCIONADO.equals(this.estado);
	}
	
	public boolean estaCerrado(){
		return EstadoDeReclamo.CERRADO.equals(this.estado);
	}
	
	public void pasarEstadoEnTratamiento() throws ConexionException, AccesoException, NegocioException {		
		this.setEstado(EstadoDeReclamo.EN_TRATAMIENTO);		
	}
	
	public void pasarEstadoSolucionado() throws ConexionException, AccesoException, NegocioException {
		if(EstadoDeReclamo.EN_TRATAMIENTO.equals(this.estado)){
			this.setEstado(EstadoDeReclamo.SOLUCIONADO);
			this.setFechaCierre(new Date());
		}else{
			throw new NegocioException("No se puede pasar del estado " + this.estado.getDenominacion() + " a " + EstadoDeReclamo.SOLUCIONADO.getDenominacion());
		}		
	}
	
	public void pasarEstadoCerrado() throws NegocioException, ConexionException, AccesoException {
		if(EstadoDeReclamo.EN_TRATAMIENTO.equals(this.estado)){
			this.setEstado(EstadoDeReclamo.CERRADO);
			this.setFechaCierre(new Date());
		}else{
			throw new NegocioException("No se puede pasar del estado " + this.estado.getDenominacion() + " a " + EstadoDeReclamo.CERRADO.getDenominacion());
		}
	}
	
	public void validar() throws NegocioException{
		
		if(this.descripcion.isEmpty()){
			throw new NegocioException("La descripcion no puede estar vacia");
		}else if(this.descripcion.length() > 25){
			throw new NegocioException("La descripcion no puede superar los 25 caracteres");
		}
		
		if(this.cliente == null){
			throw new NegocioException("Debe seleccionar un cliente");
		}
		
	}
	
	public ReclamoView toView(){
		
		ReclamoView view = new ReclamoView();
		
		view.setNroReclamo(this.nroReclamo);
		view.setDescripcion(this.descripcion);
		view.setTipoDeReclamo(this.tipoDeReclamo);
		view.setEstadoDeReclamo(this.estado);
		view.setFechaDeReclamo(DAOhelper.getDiaMesAnioHoraDateFormat().format(this.fecha));
		view.setCliente(this.cliente.toView());
		
		if(this.fechaCierre != null)
			view.setFechaDeCierre(DAOhelper.getDiaMesAnioHoraDateFormat().format(this.fechaCierre));
		
		if (this.auditoria != null)
			this.auditoria.forEach(a -> view.getAuditorias().add(a.toView()));
		
		return view;
	};
	

	abstract public Integer guardar() throws ConexionException, AccesoException, NegocioException;
	
	
	//Relacionados con composite
	abstract public void addHoja(Reclamo reclamo);
	
	abstract public void removeHoja(Reclamo reclamo);
	
	abstract public void getReclamos(Reclamo reclamo);
	
	
}
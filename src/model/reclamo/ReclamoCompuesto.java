package model.reclamo;

import java.util.ArrayList;
import java.util.List;

import controller.Sistema;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.AuditoriaReclamo;
import model.Cliente;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;
import view.ReclamoView;

public class ReclamoCompuesto extends Reclamo {
	
	public ReclamoCompuesto(){}
	
	public ReclamoCompuesto(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente) {
		super(descripcion, tipoDeReclamo, cliente);
	}
	
	public ReclamoCompuesto(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente) {
		super(descripcion, tipoDeReclamo, cliente);
		this.nroReclamo = nroReclamo;
		this.estado = estado;		
	}
	
	public ReclamoCompuesto(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente, List<Reclamo> reclamos) {
		this(nroReclamo, descripcion, tipoDeReclamo, estado, cliente);
		this.reclamosHijos.addAll(reclamos);
	}

	private List<Reclamo> reclamosHijos = new ArrayList<Reclamo>();

	@Override
	public void addHoja(Reclamo reclamo) {
		reclamosHijos.add(reclamo);
	}

	@Override
	public void removeHoja(Reclamo reclamo) {
		reclamosHijos.remove(reclamo);
	}

//	@Override
//	public void getReclamos(Reclamo reclamo) {
//		// TODO Auto-generated method stub		
//	}
	
	@Override
	public void pasarEstadoEnTratamiento() throws ConexionException, AccesoException, NegocioException {
		for(Reclamo reclamoHijo : reclamosHijos){
			if(!reclamoHijo.estaEnTratamiento()){
				throw new NegocioException("No se puede pasar el reclamo a " + EstadoDeReclamo.SOLUCIONADO.getDenominacion() + ", existen reclamos dependientes abiertos");
			}
		}
		super.pasarEstadoEnTratamiento();
	}
	
	
	@Override
	public void pasarEstadoSolucionado() throws ConexionException, AccesoException, NegocioException {
		for(Reclamo reclamoHijo : reclamosHijos){
			if(!reclamoHijo.estaSolucionado()){
				throw new NegocioException("No se puede pasar el reclamo a " + EstadoDeReclamo.SOLUCIONADO.getDenominacion() + ", existen reclamos dependientes abiertos");
			}
		}
		super.pasarEstadoSolucionado();
	}

	@Override
	public void pasarEstadoCerrado() throws NegocioException, ConexionException, AccesoException  {
		
		for(Reclamo reclamoHijo : reclamosHijos){
			if(!reclamoHijo.estaCerrado()){
				throw new NegocioException("No se puede pasar el reclamo a " + EstadoDeReclamo.CERRADO.getDenominacion() + ", existen reclamos dependientes abiertos");
			}
		}
		
		super.pasarEstadoCerrado();
	}

	@Override
	public Integer guardar() throws ConexionException, AccesoException, NegocioException {
		
		if (this.nroReclamo == null) {
			this.nroReclamo = ReclamoDAO.getInstancia().crearReclamoCompuesto(this);
			
			AuditoriaReclamo auditoria = new AuditoriaReclamo(this, 
					"", 
					this.getEstado().getDenominacion(), 
						Sistema.getInstance().getUsuarioLogueado());
			auditoria.guardar();
			
		}else{
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}
		
		for(Reclamo reclamoHijo : reclamosHijos){
			reclamoHijo.setNroReclamoCompuesto(this.nroReclamo);
			reclamoHijo.guardar();
		}
		
		return this.nroReclamo;
	}

	@Override
	public ReclamoView toView() {
		
		ReclamoView view = super.toView();		
		this.reclamosHijos.forEach(rh -> view.getReclamosHijos().add(rh.toView()));
		
		return view;
	}
}
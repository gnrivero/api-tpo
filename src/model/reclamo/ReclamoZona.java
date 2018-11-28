package model.reclamo;

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

public class ReclamoZona extends Reclamo {	
	
	private String zona;
	
	public ReclamoZona(){}
	
	public ReclamoZona(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, String zona) {
		super(descripcion, tipoDeReclamo, cliente);
		this.zona = zona;
	}
	
	public ReclamoZona(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente, String zona) {
		super(descripcion, tipoDeReclamo, cliente);
		this.nroReclamo = nroReclamo;
		this.estado = estado;
		this.zona = zona;
	}
	
	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Override
	public void addHoja(Reclamo reclamo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeHoja(Reclamo reclamo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getReclamos(Reclamo reclamo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void validar() throws NegocioException {
		super.validar();
		
		if(this.zona.isEmpty()){
			throw new NegocioException("Debe especificar una zona");
		}			
	}

	@Override
	public Integer guardar() throws ConexionException, AccesoException {		
		if (this.nroReclamo == null){
			this.nroReclamo = ReclamoDAO.getInstancia().crearReclamoZona(this);
			AuditoriaReclamo auditoria = new AuditoriaReclamo(this, 
					"", 
					this.getEstado().getDenominacion(), 
						Sistema.getInstance().getUsuarioLogueado());
			auditoria.guardar();
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}
		
		return this.nroReclamo;
	}
	
	@Override
	public ReclamoView toView() {
		
		ReclamoView view = super.toView();
		view.setZona(this.zona);
				
		return view;	
	}

}
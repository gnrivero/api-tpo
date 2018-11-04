package model.reclamo;

import dao.DAOhelper;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Cliente;
import model.TipoDeReclamo;
import view.ReclamoView;

public class ReclamoZona extends Reclamo {	
	
	private String zona;
		
	
	public ReclamoZona(){}
	
	public ReclamoZona(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, String zona) {
		super(descripcion, tipoDeReclamo, cliente);
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
	public void cerrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar() throws ConexionException, AccesoException {
		if (this.nroReclamo == null){
			ReclamoDAO.getInstancia().crearReclamoZona(this);
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}	
	}
	
	@Override
	public ReclamoView toView() {
		
		ReclamoView view = new ReclamoView();
		view.nroReclamo = this.nroReclamo;
		view.descripcion = this.descripcion;
		view.tipoDeReclamo = this.tipoDeReclamo.getDenominacion();
		view.estadoDeReclamo = this.estado.getDenominacion();
		view.fechaDeReclamo = DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fecha);
		if(this.fechaCierre != null)
			view.fechaDeCierre = DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fechaCierre);
		view.cliente = this.cliente.getNombre();
		
		return view;	
	}

}
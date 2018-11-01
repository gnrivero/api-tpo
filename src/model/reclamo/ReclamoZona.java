package model.reclamo;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Cliente;
import model.TipoDeReclamo;

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

}
package model;

import dao.FacturaReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.reclamo.Reclamo;

public class FacturaReclamo {
	
	private Factura factura;
	private Reclamo reclamo;
	
	public FacturaReclamo(Factura factura, Reclamo reclamo){
		this.factura = factura;
		this.reclamo = reclamo;
	}
	
	public void guardar() throws ConexionException, AccesoException {
		FacturaReclamoDAO.getInstancia().borrar(this);		
		FacturaReclamoDAO.getInstancia().crear(this);
	}
	
	public Factura getFactura() {
		return factura;
	}
	
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	public Reclamo getReclamo() {
		return reclamo;
	}
	
	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}
	
}
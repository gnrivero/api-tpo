package model;

import dao.FacturaReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class FacturaReclamo {
	
	private Integer nroFactura;
	private Integer nroReclamo;
	
	public FacturaReclamo(Integer nroFactura, Integer nroReclamo){
		this.nroFactura = nroFactura;
		this.nroReclamo = nroReclamo;
	}
	
	public Integer getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Integer getNroReclamo() {
		return nroReclamo;
	}
	public void setNroReclamo(Integer nroReclamo) {
		this.nroReclamo = nroReclamo;
	}
	
	public void guardar() throws ConexionException, AccesoException {
		FacturaReclamoDAO.getInstancia().crear(this);
	}
	
}
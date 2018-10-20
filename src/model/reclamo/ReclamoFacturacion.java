package model.reclamo;

import java.util.List;

import model.Factura;

public class ReclamoFacturacion extends Reclamo {
	
	private List<Factura> facturas;

	public List<Factura> getNroFacturas() {
		return facturas;
	}

	public void setNroFacturas(List<Factura> nroFacturas) {
		this.facturas = nroFacturas;
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

}

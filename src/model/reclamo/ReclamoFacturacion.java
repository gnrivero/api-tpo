package model.reclamo;

import java.util.List;

import model.Cliente;
import model.Factura;

public class ReclamoFacturacion extends Reclamo {
		
	private List<Factura> facturasReclamadas;
	
	public ReclamoFacturacion(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Factura> facturas) {
		super(descripcion, tipoDeReclamo, cliente);
		
		this.facturasReclamadas = facturas;
	}

	public List<Factura> getNroFacturas() {
		return facturasReclamadas;
	}

	public void setNroFacturas(List<Factura> nroFacturas) {
		this.facturasReclamadas = nroFacturas;
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
	public void guardar() {
		// TODO Auto-generated method stub
		
	}

}

package model.reclamo;

import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Cliente;
import model.Factura;
import model.TipoDeReclamo;

public class ReclamoFacturacion extends Reclamo {
		
	private List<Factura> facturasReclamadas;
	
	public ReclamoFacturacion(){}
	
	public ReclamoFacturacion(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Factura> facturas) {
		super(descripcion, tipoDeReclamo, cliente);
		
		this.facturasReclamadas = facturas;
	}

	public List<Factura> getFacturas() {
		return facturasReclamadas;
	}

	public void setFacturas(List<Factura> facturasReclamadas) {
		this.facturasReclamadas = facturasReclamadas;
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
	public void guardar() throws ConexionException, AccesoException {
		if (this.nroReclamo == null){
			ReclamoDAO.getInstancia().crearReclamoFacturacion(this);
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}	
	}

}

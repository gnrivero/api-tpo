package model.reclamo;

import java.util.List;

import controller.Sistema;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.AuditoriaReclamo;
import model.Cliente;
import model.Factura;
import model.TipoDeReclamo;
import view.ReclamoView;

public class ReclamoFacturacion extends Reclamo {
		
	private List<Factura> facturasReclamadas;
	
	public ReclamoFacturacion(){}
	
	public ReclamoFacturacion(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Factura> facturas) {
		super(descripcion, tipoDeReclamo, cliente);
		
		this.facturasReclamadas = facturas;
		this.nroReclamo = nroReclamo;
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
	public Integer guardar() throws ConexionException, AccesoException {
		if (this.nroReclamo == null){
			this.nroReclamo  = ReclamoDAO.getInstancia().crearReclamoFacturacion(this);
			
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
		
		if(this.facturasReclamadas != null)
			this.facturasReclamadas.forEach(fr -> view.getFacturasReclamadas().add(fr.toView()));		
		
		return view;	
	}

}

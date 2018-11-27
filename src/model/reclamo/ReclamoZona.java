package model.reclamo;

import dao.DAOhelper;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
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
	public Integer guardar() throws ConexionException, AccesoException {		
		if (this.nroReclamo == null){
			this.nroReclamo = ReclamoDAO.getInstancia().crearReclamoZona(this);
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}
		
		return this.nroReclamo;
	}
	
	@Override
	public ReclamoView toView() {
		
		ReclamoView view = new ReclamoView();
		
		view.setNroReclamo(this.nroReclamo);
		view.setDescripcion(this.descripcion);
		view.setTipoDeReclamo(this.tipoDeReclamo);
		view.setEstadoDeReclamo(this.estado);
		view.setFechaDeReclamo(DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fecha));
		
		if(this.fechaCierre != null)
			view.setFechaDeCierre(DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fechaCierre));
		
		if(this.nroReclamoCompuesto != null)
			view.setNroReclamoCompuesto(this.nroReclamoCompuesto);

		view.setCliente(this.cliente.toView());
		view.setZona(this.zona);
		
		if (this.auditoria != null)
			this.auditoria.forEach(a -> view.getAuditorias().add(a.toView()));
	
		
		return view;	
	}

}
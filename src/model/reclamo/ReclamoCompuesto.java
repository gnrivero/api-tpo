package model.reclamo;

import java.util.ArrayList;
import java.util.List;

import dao.DAOhelper;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.EstadoDeReclamo;
import model.TipoDeReclamo;
import view.ReclamoView;

public class ReclamoCompuesto extends Reclamo {
	
	public ReclamoCompuesto(){}
	
	public ReclamoCompuesto(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente) {
		super(descripcion, tipoDeReclamo, cliente);
	}
	
	public ReclamoCompuesto(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente) {
		super(descripcion, tipoDeReclamo, cliente);
		this.nroReclamo = nroReclamo;
		this.estado = estado;		
	}
	
	public ReclamoCompuesto(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente, List<Reclamo> reclamos) {
		this(nroReclamo, descripcion, tipoDeReclamo, estado, cliente);
		this.reclamosHijos.addAll(reclamos);
	}

	private List<Reclamo> reclamosHijos = new ArrayList<Reclamo>();

	@Override
	public void addHoja(Reclamo reclamo) {
		reclamosHijos.add(reclamo);
	}

	@Override
	public void removeHoja(Reclamo reclamo) {
		reclamosHijos.remove(reclamo);
	}

	@Override
	public void getReclamos(Reclamo reclamo) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void cerrar() throws NegocioException, ConexionException, AccesoException  {
		
		for(Reclamo reclamoHijo : reclamosHijos){
			if(!reclamoHijo.estaCerrado()){
				throw new NegocioException("No se puede cerrar el reclamo, existen reclamos dependientes abiertos");
			}
		}
		
		super.cerrar();
	}

	@Override
	public Integer guardar() throws ConexionException, AccesoException, NegocioException {
		
		if (this.nroReclamo == null) {
			this.nroReclamo = ReclamoDAO.getInstancia().crearReclamoCompuesto(this);
		}else{
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}			
		
		for(Reclamo reclamoHijo : reclamosHijos){
			reclamoHijo.setNroReclamo(this.nroReclamoCompuesto);
			reclamoHijo.guardar();
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
		
		view.setCliente(this.cliente.toView());
		
		return view;
	}
}
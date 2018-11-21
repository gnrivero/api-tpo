package model.reclamo;

import java.util.ArrayList;
import java.util.List;

import dao.DAOhelper;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.TipoDeReclamo;
import view.ReclamoView;

public class ReclamoCompuesto extends Reclamo {
	
	public ReclamoCompuesto(){}
	
	public ReclamoCompuesto(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Reclamo> reclamos) {
		super(descripcion, tipoDeReclamo, cliente);
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
	public void guardar() throws ConexionException, AccesoException, NegocioException {
		
		Integer nroReclamoCompuesto = null;
		if (this.nroReclamo == null) {
			nroReclamoCompuesto = ReclamoDAO.getInstancia().crearReclamoCompuesto(this);
		}else{
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}			
		
		for(Reclamo reclamoHijo : reclamosHijos){
			reclamoHijo.nroReclamoCompuesto = nroReclamoCompuesto;
			reclamoHijo.guardar();
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
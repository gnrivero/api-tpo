package model.reclamo;

import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.TipoDeReclamo;

public class ReclamoCompuesto extends Reclamo {
	
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
		
		if (this.nroReclamo == null) {
			ReclamoDAO.getInstancia().crearReclamo(this);
		}else{
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}			
		
		for(Reclamo reclamoHijo : reclamosHijos){
			reclamoHijo.guardar();
		}
	}
}
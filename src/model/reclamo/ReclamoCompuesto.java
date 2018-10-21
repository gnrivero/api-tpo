package model.reclamo;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;

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
	public void cerrar() throws Exception {
		
		for(Reclamo reclamoHijo : reclamosHijos){
			if(!reclamoHijo.estaCerrado()){
				throw new Exception("No se puede cerrar el reclamo, existen reclamos dependientes abiertos");
			}
		}
		
		super.cerrar();
	}

	@Override
	public void guardar() {
		
		super.guardar();				
		
		for(Reclamo reclamoHijo : reclamosHijos){
			reclamoHijo.guardar();
		}
	}
}
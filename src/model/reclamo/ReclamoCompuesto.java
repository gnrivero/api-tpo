package model.reclamo;

import java.util.ArrayList;
import java.util.List;

public class ReclamoCompuesto extends Reclamo {
	
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

	

}
package model;


public class EstadoDeReclamoFactory {
	
	public static EstadoDeReclamo get(Integer idEstado) {
		for (EstadoDeReclamo estado : EstadoDeReclamo.values()){
			if (estado.getId().equals(idEstado))
				return estado;
		}
		
		throw new Error("El estado de reclamo no es valido");
	}

}
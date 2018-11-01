package model;

public class TipoDeReclamoFactory {
	
	public static TipoDeReclamo get(Integer idEstado) {
		for (TipoDeReclamo estado : TipoDeReclamo.values()){
			if (estado.getId().equals(idEstado))
				return estado;
		}
		
		throw new Error("El tipo de reclamo no es valido");
	}

}
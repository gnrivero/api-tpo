package model.reclamo;

import model.TipoDeReclamo;

public class ReclamoFactory {
	
	public static Reclamo getReclamo(TipoDeReclamo tipoDeReclamo){

		Reclamo reclamo = null;
		
		switch(tipoDeReclamo){
			case ZONA:
				reclamo = new ReclamoZona();
			break;
			case FACTURACION:
				reclamo = new ReclamoFacturacion();
			break;
			case CANTIDADES: 
			case FALTANTES:
			case PRODUCTO:
				reclamo = new ReclamoDistribucion();
			break;
			case COMPUESTO:
				reclamo = new ReclamoCompuesto();
			break;	
			default:
				throw new RuntimeException("El tipo de reclamo indicado no existe");					
		}
					
		
		return reclamo;
	}
	
}

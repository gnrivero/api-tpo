package dao;

import model.reclamo.Reclamo;

public class ReclamoDAO {
	
	private static ReclamoDAO instancia;
	
	private ReclamoDAO(){}
	
	public static ReclamoDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoDAO();
		}
		return instancia;
	}
	
	public void crearReclamo(Reclamo reclamo){
		
	}
	
	public void actualizarReclamo(Reclamo reclamo){
		
	}
	
//	public Reclamo obtenerReclamo(Integer nroReclamo){		
//		
//	}	

}
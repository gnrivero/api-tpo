package dao;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.FacturaReclamo;

public class FacturaReclamoDAO extends DAO {
	
	private static FacturaReclamoDAO instancia = null;
	
	private FacturaReclamoDAO(){}
	
	public static FacturaReclamoDAO getInstancia(){
		if (instancia == null)
			instancia = new FacturaReclamoDAO();
		
		return instancia;
	}
	
	public void crear(FacturaReclamo facturaReclamo) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO facturareclamo VALUES ( " 
					 + facturaReclamo.getNroFactura() +", " 
					 + facturaReclamo.getNroReclamo() + ")";  
		
		crear(sql);		
	}

}
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
		crear("INSERT INTO facturasreclamos VALUES (" + facturaReclamo.getFactura().getNroFactura() +", " + facturaReclamo.getReclamo().getNroReclamo() + ")");		
	}
	
	public void borrar(FacturaReclamo facturaReclamo) throws ConexionException, AccesoException{				
		actualizar("DELETE FROM facturasreclamos WHERE nroreclamo = " + facturaReclamo.getReclamo().getNroReclamo());
	}
}
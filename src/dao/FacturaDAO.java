package dao;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Factura;

public class FacturaDAO extends DAO {
	
	private static FacturaDAO instancia;
	
	private FacturaDAO(){}
	
	public static FacturaDAO getInstancia(){
		if (instancia == null)
			instancia = new FacturaDAO();
		
		return instancia;
	}
	
	public void agregarFactura(Factura factura) throws ConexionException, AccesoException{
		
		String sql = "INSERT INTO facturas (fechafactura) VALUE ";
		
		crear(sql);		
	}
	
}
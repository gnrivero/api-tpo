package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Factura;
import model.ItemFactura;

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
	
	public Factura obtenerFactura(Integer nroFactura) throws AccesoException, ConexionException, NegocioException{
		
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;		
		
		try {
			con = ConnectionFactory.getInstancia().getConection();
		}
		catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}					
		
		String SQL = "SELECT * FROM facturas WHERE nrofactura = " + nroFactura;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				
				List<ItemFactura> itemsFactura = ItemFacturaDAO.getInstancia().obtenerItemsPorNroFactura(nroFactura);
				
				Factura factura = new Factura(rs.getInt("nrofactura"), rs.getDate("fechafactura"), itemsFactura);
				
				return factura;
			}
			else {
				throw new NegocioException("La factura " + nroFactura + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}								
				
	}
	
}
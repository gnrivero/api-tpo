package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.ItemFactura;
import model.Producto;

public class ItemFacturaDAO extends DAO {
	
	private static ItemFacturaDAO instancia = null;
	
	public static ItemFacturaDAO getInstancia(){
		if(instancia == null)
			instancia = new ItemFacturaDAO();
		
		return instancia;
	}
	
	
	public List<ItemFactura> obtenerItemsPorNroFactura(Integer nroFactura) throws NegocioException, ConexionException, AccesoException{
		
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
		
		String SQL = "SELECT * FROM itemsfacturas WHERE nrofactura =" + nroFactura;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			
			List<ItemFactura> itemsFactura = new ArrayList<ItemFactura>();
			
			while(rs.next()){
				Producto producto = ProductoDAO.getInstancia().obtenerProductoPorId(rs.getInt("idproducto")); 
				
				ItemFactura itemFactura = new ItemFactura(rs.getInt("iditemfactura"), rs.getFloat("montoitem"), rs.getInt("cantidad"), producto);				
				itemsFactura.add(itemFactura);
			}
						
			if(itemsFactura.isEmpty())
				throw new NegocioException("La factura " + nroFactura + "no posee items");
			
			return itemsFactura;
						
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
		
	}

}
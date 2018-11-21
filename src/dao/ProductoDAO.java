package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Producto;

public class ProductoDAO extends DAO {
	
	private static ProductoDAO instancia;
	
	private ProductoDAO(){}
	
	public static ProductoDAO getInstancia(){
		if(instancia == null){
			instancia = new ProductoDAO();
		}
		return instancia;
	}
	
	public void borrar(Integer idProducto) throws ConexionException, AccesoException{
	
		String sql = "DELETE FROM productos where idproducto = " + idProducto;
	
		crear(sql);
	}
	
	
	public Producto obtenerProductoPorId(Integer idProducto) throws NegocioException, ConexionException, AccesoException {
		
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
		
		String sql = "SELECT * FROM productos WHERE idproducto = " + idProducto;
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {	
			if(rs.next()){
				return new Producto(rs.getInt("idproducto"), rs.getString("codigo"), rs.getString("titulo"), rs.getString("descripcion"), rs.getFloat("precio"));
			}
			else{
				throw new NegocioException("El producto id: " + idProducto + " no existe");
			}			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public void crearProducto(Producto producto) throws ConexionException, AccesoException{
		
		String sql = "INSERT INTO productos (codigo, titulo, descripcion, precio) VALUES "
				+ "('" + producto.getCodigo() + "', "
				+ "'" + producto.getTitulo() + "', "
				+ "'" + producto.getDescripcion() + "', "				
				+ producto.getPrecio() + ")";
		
		crear(sql);
	}

}
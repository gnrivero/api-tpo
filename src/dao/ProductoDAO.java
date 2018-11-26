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
	
	
	/**
	 * 
	 * @param producto
	 * @throws ConexionException
	 * @throws AccesoException
	 */
	public Integer crearProducto(Producto producto) throws ConexionException, AccesoException{
		
		String sql = "INSERT INTO productos (codigo, titulo, descripcion, precio) VALUES "
				+ "('" + producto.getCodigo() + "', "
				+ "'" + producto.getTitulo() + "', "
				+ "'" + producto.getDescripcion() + "', "				
				+ producto.getPrecio() + ")";
		
		return crear(sql);
	}
	
	public void actualizarProducto(Producto producto) throws ConexionException, AccesoException{
		
		String sql = "UPDATE productos SET "
					+ " codigo = '" + producto.getCodigo() + "', "
					+ " titulo = '" + producto.getTitulo() + "', "
					+ " descripcion = '" + producto.getDescripcion() + "', "
					+ " precio = '" + producto.getPrecio() + "' ";
						
					sql += " WHERE idproducto = " + producto.getIdProducto();  
					
		actualizar(sql);
	}
	
	/**
	 * Elimina fisicamente el producto de la base de datos
	 * 
	 * @param idProducto
	 * @throws ConexionException
	 * @throws AccesoException
	 */
	public void borrar(Integer idProducto) throws ConexionException, AccesoException{
		
		String sql = "DELETE FROM productos where idproducto = " + idProducto;
	
		actualizar(sql);
	}
	
	/**
	 * Obtiene el producto por su Id
	 * 
	 * @param idProducto
	 * @return
	 * @throws NegocioException
	 * @throws ConexionException
	 * @throws AccesoException
	 */
	public Producto obtenerProductoPorId(Integer idProducto) throws NegocioException, ConexionException, AccesoException {
		
		String sql = "SELECT * FROM productos WHERE idproducto = " + idProducto;
		
		return obtenerProductos(sql).get(0);
	}
	
	/**
	 * Obtiene todos los productos existentes
	 * 
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 */
	public List<Producto> obtenerTodosLosProductos() throws ConexionException, AccesoException{
		
		String sql = "SELECT * FROM productos";
		
		return obtenerProductos(sql);
	}
	
	/**
	 * Metodo general para la obtencion de productos. 
	 * 
	 * @param sql
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 */
	private List<Producto> obtenerProductos(String sql) throws ConexionException, AccesoException{
		
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
		
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		
		List<Producto> productos = new ArrayList<Producto>();
		try {
			while(rs.next()){
				Producto producto = new Producto(rs.getInt("idproducto"), rs.getString("codigo"), rs.getString("titulo"), rs.getString("descripcion"), rs.getFloat("precio"));				
				productos.add(producto);
			}						
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
		
		return productos;
	}

}
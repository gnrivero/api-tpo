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
import model.Cliente;

public class ClienteDAO extends DAO {
	
	private static ClienteDAO instancia;
	
	private ClienteDAO(){}
	
	public static ClienteDAO getInstancia(){
		if(instancia == null){
			instancia = new ClienteDAO();
		}
		return instancia;
	}
	
	private List<Cliente> obtenerClientes(String SQL) throws AccesoException, ConexionException, NegocioException{
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
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		List<Cliente> clientes = new ArrayList<>();
		try {
			while(rs.next()){
				Cliente cliente = new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("cuit"), rs.getString("domicilio"), rs.getString("telefono"), rs.getString("mail"), rs.getDate("fechabaja"));
				clientes.add(cliente);
			}			
			return clientes;
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}

	public Cliente obtenerClientePorId(int idCliente) throws ConexionException, AccesoException, NegocioException {  
		
		String SQL = "SELECT * FROM clientes WHERE idcliente = " + idCliente;
				
		return obtenerClientes(SQL).get(0);
	}
	
	public List<Cliente> obtenerTodosLosClientes(boolean filtrarDeshabilitados) throws AccesoException, ConexionException, NegocioException {
		
		String sql = "SELECT * FROM clientes ";
		
		if(filtrarDeshabilitados){
			sql += " WHERE fechabaja IS NULL";
		}
		
		return obtenerClientes(sql);
	}
	
	public Integer crearCliente(Cliente cliente) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO clientes (nombre, cuit, domicilio, telefono, mail) VALUES "
					+ "('" + cliente.getNombre() + "', "  
					+ " '" + cliente.getCuit() + "', "
					+ " '" + cliente.getDomicilio() + "', "
					+ " '" + cliente.getTelefono() +"', " 
					+ " '" + cliente.getMail() + "')";
		
		return crear(sql);
	}
	
	public void actualizarCliente(Cliente cliente) throws ConexionException, AccesoException{
		
		String sql = "UPDATE clientes SET "
					+ " nombre = '" + cliente.getNombre() + "', "
					+ " cuit = '" + cliente.getCuit() + "', "
					+ " domicilio = '" + cliente.getDomicilio() + "', "
					+ " telefono = '" + cliente.getTelefono() + "', "
					+ " mail = '" + cliente.getMail() + "' ";
		
					if(cliente.getFechaBaja()!= null){
						sql += ", fechabaja = '" + DAOhelper.getAnioMesDiaHoraDateFormat().format(cliente.getFechaBaja()) +"' ";
					}else{
						sql += ", fechabaja = NULL ";
					}
						
					sql += " WHERE idcliente = " + cliente.getIdCliente();  
					
		actualizar(sql);
	}
	
}
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;

public class ClienteDAO {
	
	private static ClienteDAO instancia;
	
	private ClienteDAO(){}
	
	public static ClienteDAO getInstancia(){
		if(instancia == null){
			instancia = new ClienteDAO();
		}
		return instancia;
	}

	public Cliente obtenerClientePorId(int idCliente) throws ConexionException, AccesoException, NegocioException {  
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
		String SQL = "SELECT * FROM clientes where id_cliente = " + idCliente;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				return cliente;
			}
			else{
				throw new NegocioException("El cliente id = " + idCliente + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public void crearCliente(Cliente cliente) throws ConexionException, AccesoException {
		
		Connection con;
		try {
			con = ConnectionFactory.getInstancia().getConection();
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		} 
		
		Statement stm;
		try {
			stm = con.createStatement();
		} catch (SQLException e) {
			throw new AccesoException("Error de acceso");
		}
		
		String sentencia = "insert into clientes values (" + cliente.getIdCliente() + ",'" + cliente.getNombre() + "'," + cliente.getDomicilio() + cliente.getTelefono() + cliente.getMail() + ")";
		try {
			stm.execute(sentencia);
		} catch (SQLException e) {
			throw new AccesoException("No se pudo guardar");
		}
	}
	
	public void actualizarCliente(Cliente cliente){
		
	}
	
}
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		String SQL = "SELECT * FROM clientes WHERE idcliente = " + idCliente;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Cliente cliente = new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("domicilio"), rs.getString("telefono"), rs.getString("mail"));
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
		
		String sql = "INSERT INTO clientes (nombre, domicilio, telefono, mail) VALUES "
					+ "('" + cliente.getNombre() + "', "  
					+ " '" + cliente.getDomicilio() + "', " 
					+ " '" + cliente.getTelefono() +"', " 
					+ " '" + cliente.getMail() + "')";
		
		crear(sql);
	}
	
	public void actualizarCliente(Cliente cliente) throws ConexionException, AccesoException{
		
		String sql = "UPDATE clientes SET "
					+ " nombre = '" + cliente.getNombre() + "', "
					+ " domicilio = '" + cliente.getDomicilio() + "', "
					+ " telefono = '" + cliente.getTelefono() + "', "
					+ " mail = '" + cliente.getMail() + "', "
					+ " fechabaja = '" + DAOhelper.getAnioMesDiaHoraDateFormat().format(cliente.getFechaBaja()) +"' "
					+ " WHERE idcliente = " + cliente.getIdCliente();  
				
		
		actualizar(sql);
	}
	
}
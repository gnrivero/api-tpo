package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;


/**
 * Abstraccion de la clase DAO.
 * 	
 * La idea es seguir los metodos de CRUD
 * 
 * Create: crear()
 * Retrieve	: no está definido ni implementado porque no se como hacerlo devolviendo objetos genericos. Lo genera cada clase.
 * Update: actualizar()
 * Delete: no está definido. (no se si debería)
 */
public abstract class DAO {
	
	protected void crear(String sql) throws ConexionException, AccesoException {
		
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
			throw new AccesoException("No se pudo crear sentencia SQL");
		}
		
		try {
			stm.execute(sql);
		} catch (SQLException e) {			
			throw new AccesoException("No se pudo guardar");
		}
		
	}
	
	protected void actualizar(String sql) throws ConexionException, AccesoException {
		
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
				
		try {
			stm.execute(sql);
		} catch (SQLException e) {
			throw new AccesoException("No se pudo actualizar");
		}
		
	}

}
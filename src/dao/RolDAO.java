package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Rol;

public class RolDAO extends DAO {
	
	private static RolDAO instancia;
	
	public static RolDAO getInstancia(){
		if(instancia == null)
			instancia = new RolDAO();
		
		return instancia;
	}
	
	public void crearRol(Rol rol) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO roles (descripcion) VALUES ('" + rol.getDescripcion() + "' )"; 
		
		crear(sql);
	}
	
	public void actualizarRol(Rol rol) throws ConexionException, AccesoException {
		
		String sql = "UPDATE roles SET "
				+ " idrol = " + rol.getIdRol() + ", "
				+ " descripcion = '" + rol.getDescripcion() + "' "
				+ " WHERE idrol = " + rol.getIdRol();
		
		actualizar(sql);
	}
	
	
	public Rol obtenerRolPorId(Integer idRol) throws ConexionException, AccesoException, NegocioException {
		
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
		
		String SQL = "SELECT * FROM roles WHERE idrol = " + idRol;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Rol rol = new Rol(rs.getInt("idrol"), rs.getString("descripcion"));				
				return rol;
			}
			else {
				throw new NegocioException("El rol se pudo cargar");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}		
	}

}
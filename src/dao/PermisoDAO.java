package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Modulo;
import model.Permiso;

public class PermisoDAO extends DAO {
	
	private static PermisoDAO instance; 
	
	public static PermisoDAO getInstance(){
		if(instance==null)
			instance = new PermisoDAO();
		
		return instance;
	}
	
	private PermisoDAO(){ }
	
	public List<Permiso> obtenerPermisosPorRol(Integer idRol) throws ConexionException, AccesoException{
		
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
			rs = stmt.executeQuery("SELECT modulo, valor FROM permisos WHERE idrol = " + idRol);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			List<Permiso> permisos = new ArrayList<Permiso>();						
			
			while(rs.next()){
				
				Permiso permiso = new Permiso();							
				permiso.setModulo(Modulo.valueOf(rs.getString("modulo")));
				permiso.setValor(rs.getInt("valor"));
				
				permisos.add(permiso);
			}
			
			return permisos;
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
		
	}

}

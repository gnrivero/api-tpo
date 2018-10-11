package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.UsuarioException;
import excepciones.ConexionException;
import negocio.Usuario;

public class UsuarioDAO {

	public Usuario obtenerUsuarioPorId(int idUsuario) throws ConexionException, UsuarioException, AccesoException{  
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
		String SQL = "SELECT * FROM usuarios where id_usuario = " + idUsuario;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Usuario usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				return usuario;
			}
			else{
				throw new UsuarioException("El usuario id = " + idUsuario + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}

	public void grabarUsuario(Usuario usuario) throws ConexionException, AccesoException {
		
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
		
		String sentencia = "insert into usuarios values (" + usuario.getIdUsuario() + ",'" + usuario.getUsername() + "'," + usuario.getPassword() + usuario.getFechaBaja() + ")";
		try {
			stm.execute(sentencia);
		} catch (SQLException e) {
			throw new AccesoException("No se pudo guardar");
		}
	}
}

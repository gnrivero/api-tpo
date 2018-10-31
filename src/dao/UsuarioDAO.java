package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Rol;
import model.Usuario;

public class UsuarioDAO extends DAO {
	
	private static UsuarioDAO instancia;
		
	private UsuarioDAO(){}
	
	public static UsuarioDAO getInstancia(){
		if(instancia == null){
			instancia = new UsuarioDAO();
		}
		return instancia;
	}
	
	public Usuario buscarUsuarioPorUsernameYpassword(String username, String password) throws ConexionException, AccesoException, NegocioException {
				
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
		
		String SQL = "SELECT * FROM usuarios WHERE username = '" +  username + "' AND password = '" + password + "'";
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				
				Rol rol = RolDAO.getInstancia().obtenerRolPorId(rs.getInt("idrol"));				
				
				Usuario usuario = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"), rol, rs.getDate("fechabaja"));
				
				return usuario;
			}
			else {
				throw new NegocioException("El usuario " + username + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}								
		
	}
	
	public Usuario obtenerUsuarioPorId(int idUsuario) throws ConexionException, AccesoException, NegocioException {  
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
		String SQL = "SELECT * FROM usuarios WHERE idusuario = " + idUsuario;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				
				Rol rol = RolDAO.getInstancia().obtenerRolPorId(rs.getInt("idrol"));				
				
				Usuario usuario = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"), rol, rs.getDate("fechabaja"));
				return usuario;
			}
			else{
				throw new NegocioException("El usuario id: " + idUsuario + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}

	public void crearUsuario(Usuario usuario) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO usuarios (username, password, idrol) VALUES ("
				   + "'" + usuario.getUsername() + "', "
				   + "'" + usuario.getPassword() + "', "
				   + "'" + usuario.getRol().getIdRol() + "')";
		
		crear(sql);
	}
	
	public void actualizarUsuario(Usuario usuario) throws NegocioException {
		throw new NegocioException("Unimplemented");
	}
}

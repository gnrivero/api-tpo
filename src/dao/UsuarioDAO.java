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
				Usuario usuario = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"), rs.getDate("fechabaja"), rol);
				return usuario;
			}
			else {
				throw new NegocioException("El usuario " + username + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}								
		
	}
	
	
	/**
	 * Metodo para obtener un Usuario por su ID. 
	 * Obtiene solamente la informacion referida al usuario, no carga su Rol. 
	 * 
	 * @param idUsuario
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public Usuario obtenerUsuarioPorIdLazy(int idUsuario) throws ConexionException, AccesoException, NegocioException {  
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
				Usuario usuario = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"), rs.getDate("fechabaja"));
				return usuario;
			}
			else{
				throw new NegocioException("El usuario id: " + idUsuario + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}

	public Integer crearUsuario(Usuario usuario) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO usuarios (username, password, idrol) VALUES ("
				   + "'" + usuario.getUsername() + "', "
				   + "'" + usuario.getPassword() + "', "
				   + "'" + usuario.getRol().getIdRol() + "')";
		return crear(sql);
	}
	
	public void actualizarUsuario(Usuario usuario) throws NegocioException, ConexionException, AccesoException {
		//throw new NegocioException("Unimplemented");
		String sql = "UPDATE usuarios SET "
				+ " username = '" + usuario.getUsername() + "', "
				+ " password = '" + usuario.getPassword() + "', "
				+ " idrol = '" + usuario.getRol().getIdRol() + "' ";
		
		if(usuario.getFechaBaja()!= null){
			sql += ", fechabaja = '" + DAOhelper.getAnioMesDiaHoraDateFormat().format(usuario.getFechaBaja()) +"' ";
		}else{
			sql += ", fechabaja = NULL ";
		}
			
		sql += " WHERE idusuario = " + usuario.getIdUsuario();
		
		actualizar(sql);
	}

	private List<Usuario> obtenerUsuarios(String SQL) throws AccesoException, ConexionException, NegocioException{
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
		
		List<Usuario> usuarios = new ArrayList<>();
		try {
			while(rs.next()){
				Rol rol = RolDAO.getInstancia().obtenerRolPorId(rs.getInt("idrol"));
				Usuario usuario = new Usuario(rs.getInt("idusuario"), rs.getString("username"), rs.getString("password"), rs.getDate("fechabaja"), rol);
				usuarios.add(usuario);
			}			
			return usuarios;
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public List<Usuario> obtenerTodosLosUsuarios(boolean filtrarDeshabilitados) throws AccesoException, ConexionException, NegocioException{
		String sql = "SELECT * FROM usuarios ";
		if(filtrarDeshabilitados){
			sql += " WHERE fechabaja IS NULL";
		}
		return obtenerUsuarios(sql);
	}
	
}

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.AuditoriaReclamo;
import model.Usuario;

public class AuditoriaReclamoDAO extends DAO {
	
	private static AuditoriaReclamoDAO instance;
	
	private AuditoriaReclamoDAO(){ }
	
	public static AuditoriaReclamoDAO getInstance(){
		if (instance == null)
			instance = new AuditoriaReclamoDAO();
		
		return instance;
	}
	
	public void crearAuditoria(AuditoriaReclamo auditoria) throws ConexionException, AccesoException{
		
		String sql =  "INSERT INTO auditoriasreclamos (nroreclamo, datoanterior, datonuevo, idusuario, fecha, idtiporeclamo) "
					+ "VALUES (" + auditoria.getReclamo().getNroReclamo() + ", "
					+ "'" + auditoria.getDatoAnterior() + "', "
					+ "'" + auditoria.getDatoNuevo() + "', "
					+ auditoria.getUsuario().getIdUsuario() + ", "
					+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(new Date()) + "', "
					+ auditoria.getReclamo().getTipoDeReclamo().getId() + ")";  
		
		crear(sql);
	}
		
	public List<AuditoriaReclamo> obtenerAuditoriaPorReclamo(Integer nroReclamo, Integer idTipoDeReclamo) throws ConexionException, AccesoException, NegocioException{
		
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
			rs = stmt.executeQuery("SELECT * FROM auditoriasreclamos WHERE nroreclamo = " + nroReclamo + " AND idtiporeclamo = " + idTipoDeReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			List<AuditoriaReclamo> auditorias = new ArrayList<AuditoriaReclamo>();						
			
			while(rs.next()){
				
				Usuario usuario = UsuarioDAO.getInstancia().obtenerUsuarioPorIdLazy(rs.getInt("idusuario"));
				
				
				Date fecha = new Date(rs.getTimestamp("fecha").getTime());
				
				AuditoriaReclamo auditoria = new AuditoriaReclamo(rs.getString("datoanterior"), 
																	rs.getString("datonuevo"), 
																		usuario, 
																			fecha);				
				
							
				auditorias.add(auditoria);
			}
			
			return auditorias;
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}		
	}
	
}
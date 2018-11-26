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
import model.Usuario;

public class AuditoriasReclamosDAO extends DAO {
	
	private static AuditoriasReclamosDAO instance;
	
	private AuditoriasReclamosDAO(){ }
	
	public static AuditoriasReclamosDAO getInstance(){
		if (instance == null)
			instance = new AuditoriasReclamosDAO();
		
		return instance;
	}
	
	//TODO: crear auditoria
	
	public List<AuditoriaReclamo> obtenerAuditoriaPorReclamo(Integer nroReclamo) throws ConexionException, AccesoException, NegocioException{
		
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
			rs = stmt.executeQuery("SELECT * FROM auditoriasreclamos WHERE nroreclamo = " + nroReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			List<AuditoriaReclamo> auditorias = new ArrayList<AuditoriaReclamo>();						
			
			while(rs.next()){
				
				Usuario usuario = UsuarioDAO.getInstancia().obtenerUsuarioPorIdLazy(rs.getInt("idusuario"));
				
				AuditoriaReclamo auditoria = new AuditoriaReclamo(rs.getString("datoanterior"), 
																	rs.getString("idusuario"), 
																		usuario, 
																			rs.getDate("fecha"));				
				
							
				auditorias.add(auditoria);
			}
			
			return auditorias;
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}		
	}
	
}
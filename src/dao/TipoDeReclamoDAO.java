package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.TipoDeReclamo;
import model.TipoDeReclamoFactory;

public class TipoDeReclamoDAO extends DAO {

	private static TipoDeReclamoDAO instancia;

	public static TipoDeReclamoDAO getInstancia() {
		if (instancia == null)
			instancia = new TipoDeReclamoDAO();

		return instancia;
	}

	public List<TipoDeReclamo> getTiposdeReclamoPorRol(Integer idRol) throws ConexionException, AccesoException {
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
		
		String SQL = "SELECT * FROM tiposdereclamoporroles WHERE idrol = " + idRol;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			List<TipoDeReclamo> tiposDeReclamoPorRol = new ArrayList<TipoDeReclamo>();
			
			while(rs.next()){
				tiposDeReclamoPorRol.add(TipoDeReclamoFactory.get(rs.getInt("idtipodereclamo")));
			}
			
			return tiposDeReclamoPorRol;
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}	
	}
}

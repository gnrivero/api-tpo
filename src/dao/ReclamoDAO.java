package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.reclamo.Reclamo;

public class ReclamoDAO {
	
	private static ReclamoDAO instancia;
	
	private ReclamoDAO(){}
	
	public static ReclamoDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoDAO();
		}
		return instancia;
	}
	
	public void crearReclamo(Reclamo reclamo) throws ConexionException, AccesoException {
		
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
		
		
		StringBuilder sqlBuilder = new StringBuilder("INSERT INTO reclamos (descripcion, idtiporeclamo, idestadoreclamo, fecha, idcliente) VALUES (");
		sqlBuilder.append("'").append(reclamo.getDescripcion()).append("'), ")
		.append(reclamo.getTipoDeReclamo()).append(", ")
		.append(reclamo.getEstado()).append(", ")
		.append(reclamo.getFecha()).append(", ")
		.append(reclamo.getCliente().getIdCliente());
		
		try {
			stm.execute(sqlBuilder.toString());
		} catch (SQLException e) {
			throw new AccesoException("No se pudo guardar Reclamo");
		}
	}
	
	public void actualizarReclamo(Reclamo reclamo) throws AccesoException, ConexionException{
		
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
		
		StringBuilder sqlBuilder = new StringBuilder("UPDATE reclamos SET ");
		sqlBuilder.append("descripcion = ").append("'").append(reclamo.getDescripcion()).append("', ")
		.append("idtiporeclamo = ").append(reclamo.getTipoDeReclamo()).append(", ")
		.append("idestadoreclamo = ").append(reclamo.getEstado()).append(", ")
		.append("fechacierre = ").append(reclamo.getFechaCierre()).append(", ")
		.append("idcliente = ").append(reclamo.getCliente().getIdCliente())
		.append(" WHERE idreclamo = ").append(reclamo.getNroReclamo());
		
		
		try {
			stm.execute(sqlBuilder.toString());
		} catch (SQLException e) {
			throw new AccesoException("No se pudo guardar Reclamo");
		}		
	}
	
//	public Reclamo obtenerReclamo(Integer nroReclamo){		
//		
//	}	

}
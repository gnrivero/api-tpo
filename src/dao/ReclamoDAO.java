package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoDistribucion;

public class ReclamoDAO {
	
	private static ReclamoDAO instancia;
	
	private ReclamoDAO(){}
	
	public static ReclamoDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoDAO();
		}
		return instancia;
	}
	
	private static final String INSERT_RECLAMOS_ABSTRACT = "INSERT INTO reclamos (descripcion, idtiporeclamo, idestadoreclamo, fecha, idcliente";
	private static final String INSERT_RECLAMO_DIST = INSERT_RECLAMOS_ABSTRACT + ", idproducto, cantidad)";
	private static final String INSERT_RECLAMO_ZONA = INSERT_RECLAMOS_ABSTRACT + ", zona)";
	private static final String INSERT_RECLAMO_FACTURACION = INSERT_RECLAMOS_ABSTRACT + ")";
	
	public void crearReclamo(ReclamoDistribucion reclamo) throws ConexionException, AccesoException {

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
			throw new AccesoException("No se pudo ");
		}
		
		
		String sql = INSERT_RECLAMOS_ABSTRACT + INSERT_RECLAMO_DIST
				+ " VALUES "		
				+ "('" + reclamo.getDescripcion() + "', "
				+ "'" + reclamo.getTipoDeReclamo() + "', "
				+ "'" + reclamo.getEstado() + "', "
				+ "'" + reclamo.getFecha() + "', "
				+ reclamo.getCliente().getIdCliente() + ", "
				+ reclamo.getProducto().getIdProducto() +", "
				+ reclamo.getCantidad() +")";

		try {
			stm.execute(sql.toString());
		} catch (SQLException e) {
			throw new AccesoException("No se pudo guardar" );
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
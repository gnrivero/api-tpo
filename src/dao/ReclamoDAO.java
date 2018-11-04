package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.EstadoDeReclamo;
import model.EstadoDeReclamoFactory;
import model.Factura;
import model.FacturaReclamo;
import model.Producto;
import model.TipoDeReclamo;
import model.TipoDeReclamoFactory;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoCompuesto;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.ReclamoFacturacion;
import model.reclamo.ReclamoZona;

public class ReclamoDAO extends DAO {
	
	private static ReclamoDAO instancia;
	
	private ReclamoDAO(){}
	
	public static ReclamoDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoDAO();
		}
		return instancia;
	}
	
	private static final String INSERT_RECLAMO = "INSERT INTO reclamos (descripcion, idtiporeclamo, idestadoreclamo, fecha, idcliente";
	private static final String INSERT_RECLAMO_DIST = INSERT_RECLAMO + ", idproducto, cantidad)";
	private static final String INSERT_RECLAMO_ZONA = INSERT_RECLAMO + ", zona)";	
	private static final String INSERT_RECLAMO_COMP = "INSERT INTO reclamoscompuestos (descripcion, idtiporeclamo, idestadoreclamo, fecha, idcliente)";
	
	
	public Integer crearReclamo(ReclamoCompuesto reclamo) throws ConexionException, AccesoException, NegocioException {
		String sql = INSERT_RECLAMO_COMP
				+ " VALUES "
				+ "('" + reclamo.getDescripcion() + "', "
				+ reclamo.getTipoDeReclamo().getId() + ", "
				+ reclamo.getEstado().getId() + ", "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ")";
		
		return crear(sql);		
	}	
	
	public void crearReclamoDistribucion(ReclamoDistribucion reclamo) throws ConexionException, AccesoException {
		
		String sql = INSERT_RECLAMO_DIST
				+ " VALUES "
				+ "('" + reclamo.getDescripcion() + "', "
				+ reclamo.getTipoDeReclamo().getId() + ", "
				+ reclamo.getEstado().getId() + ", "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ", "
				+ reclamo.getProducto().getIdProducto() +", "
				+ reclamo.getCantidad() +")";
		
		crear(sql);
	}
	
	
	public void crearReclamoZona(ReclamoZona reclamo) throws ConexionException, AccesoException {
		
		String sql = INSERT_RECLAMO_ZONA
				+ " VALUES "
				+ "('" + reclamo.getDescripcion() + "', "
				+ reclamo.getTipoDeReclamo().getId() + ", "
				+ reclamo.getEstado().getId() + ", "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ", "
				+ "'" + reclamo.getZona() + "')";		
		
		crear(sql);
	}
	
	public void crearReclamoFacturacion(ReclamoFacturacion reclamo) throws ConexionException, AccesoException {
		
		String sql = INSERT_RECLAMO + ")" 
				+ " VALUES "
				+ "('" + reclamo.getDescripcion() + "', "
				+ "'" + reclamo.getTipoDeReclamo() + "', "
				+ "'" + reclamo.getEstado() + "', "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ")";
		
		crear(sql);
		
		for (Factura factura : reclamo.getFacturas()){
			FacturaReclamo fr = new FacturaReclamo(factura.getNroFactura(), reclamo.getNroReclamo());
			fr.guardar();			
		}		
	}
	
	public void actualizarReclamo(Reclamo reclamo) throws AccesoException, ConexionException {
		
		String sql = "UPDATE reclamos SET "
				+ " descripcion = " + reclamo.getDescripcion()
				+ " idtiporeclamo = " + reclamo.getTipoDeReclamo().getId() 
				+ " idestadoreclamo = " + reclamo.getEstado().getId() 
				+ " fechacierre = " + reclamo.getFechaCierre() 
				+ " idcliente = " + reclamo.getCliente().getIdCliente()
				+ " WHERE nroreclamo = " + reclamo.getNroReclamo();
		
		actualizar(sql);
	}
	
	public ReclamoCompuesto obtenerReclamoCompuesto(Integer nroReclamo) throws AccesoException, ConexionException, NegocioException {
		
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
			rs = stmt.executeQuery("SELECT * FROM reclamos WHERE nroreclamo = " + nroReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			ReclamoCompuesto reclamo = new ReclamoCompuesto();
			
			if(rs.next()){
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtipodereclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadodereclamo"));				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));
								
				reclamo.setNroReclamo(rs.getInt("nroreclamo"));
				reclamo.setDescripcion(rs.getString("descripcion"));
				reclamo.setEstado(estado);
				reclamo.setTipoDeReclamo(tipoDeReclamo);
				reclamo.setCliente(cliente);
				reclamo.setFecha(rs.getDate("fecha"));
				reclamo.setFechaCierre(rs.getDate("fechacierre"));
				
				return reclamo;
			}
			else{
				throw new NegocioException("El cliente id = " + reclamo.getNroReclamo() + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}			
	}

	public ReclamoDistribucion obtenerReclamoDistribucion(Integer nroReclamo) throws AccesoException, ConexionException, NegocioException {
		
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
			rs = stmt.executeQuery("SELECT * FROM reclamos WHERE nroreclamo = " + nroReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			ReclamoDistribucion reclamo = new ReclamoDistribucion();
			
			if(rs.next()){
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtipodereclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadodereclamo"));				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));
				Producto producto = ProductoDAO.getInstancia().obtenerProductoPorId(rs.getInt("idproducto"));
								
				reclamo.setNroReclamo(rs.getInt("nroreclamo"));
				reclamo.setDescripcion(rs.getString("descripcion"));
				reclamo.setEstado(estado);
				reclamo.setTipoDeReclamo(tipoDeReclamo);
				reclamo.setCliente(cliente);
				reclamo.setProducto(producto);
				reclamo.setCantidad(rs.getInt("cantidad"));
				reclamo.setFecha(rs.getDate("fecha"));
				reclamo.setFechaCierre(rs.getDate("fechacierre"));
				
				return reclamo;
			}
			else{
				throw new NegocioException("El cliente id = " + reclamo.getNroReclamo() + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}			
	}
	
	public ReclamoZona obtenerReclamoZona(Integer nroReclamo) throws AccesoException, ConexionException, NegocioException {
		
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
			rs = stmt.executeQuery("SELECT * FROM reclamos WHERE nroreclamo = " + nroReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			ReclamoZona reclamo = new ReclamoZona();
			
			if(rs.next()){
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtipodereclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadodereclamo"));				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));			
				
				reclamo.setNroReclamo(rs.getInt("nroreclamo"));
				reclamo.setDescripcion(rs.getString("descripcion"));
				reclamo.setEstado(estado);
				reclamo.setTipoDeReclamo(tipoDeReclamo);
				reclamo.setCliente(cliente);	
				reclamo.setZona(rs.getString("zona"));
				reclamo.setFecha(rs.getDate("fecha"));
				reclamo.setFechaCierre(rs.getDate("fechacierre"));
				
				return reclamo;
			}
			else{
				throw new NegocioException("El cliente id = " + reclamo.getNroReclamo() + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}			
	}

	public ReclamoFacturacion obtenerReclamoFacturacion(Integer nroReclamo) throws AccesoException, ConexionException, NegocioException {
		
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
			rs = stmt.executeQuery("SELECT * FROM reclamos WHERE nroreclamo = " + nroReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			ReclamoFacturacion reclamo = new ReclamoFacturacion();
			
			if(rs.next()){
				
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadodereclamo"));				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtipodereclamo"));
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));			
				
				reclamo.setNroReclamo(rs.getInt("nroreclamo"));
				reclamo.setDescripcion(rs.getString("descripcion"));
				reclamo.setEstado(estado);
				reclamo.setTipoDeReclamo(tipoDeReclamo);
				reclamo.setCliente(cliente);
				reclamo.setFecha(rs.getDate("fecha"));
				reclamo.setFechaCierre(rs.getDate("fechacierre"));
				
				return reclamo;
			}
			else{
				throw new NegocioException("El cliente id = " + reclamo.getNroReclamo() + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}			
	}

}
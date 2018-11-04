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
	private static final String INSERT_RECLAMO_DIST = INSERT_RECLAMO + ", idproducto, cantidad, nroreclamocompuesto)";
	private static final String INSERT_RECLAMO_ZONA = INSERT_RECLAMO + ", zona, nroreclamocompuesto)";
	private static final String INSERT_RECLAMO_FACT = INSERT_RECLAMO + ", nroreclamocompuesto)";
	private static final String INSERT_RECLAMO_COMP = "INSERT INTO reclamoscompuestos (descripcion, idtiporeclamo, idestadoreclamo, fecha, idcliente)";
	
	
	//Creacion de Reclamos
	public Integer crearReclamoCompuesto(ReclamoCompuesto reclamo) throws ConexionException, AccesoException, NegocioException {
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
				+ reclamo.getCantidad() +", "
				+ reclamo.getNroReclamoCompuesto() + ")";
		
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
				+ "'" + reclamo.getZona() + "', "
				+ reclamo.getNroReclamoCompuesto() + ")";		
		
		crear(sql);
	}
	
	public void crearReclamoFacturacion(ReclamoFacturacion reclamo) throws ConexionException, AccesoException {
		
		String sql = INSERT_RECLAMO_FACT  
				+ " VALUES " 
				+ "('" + reclamo.getDescripcion() + "', "
				+ reclamo.getTipoDeReclamo().getId() + ", "
				+ reclamo.getEstado().getId() + ", "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ", "
				+ reclamo.getNroReclamoCompuesto() + ")";
		
		Integer nroReclamoFacturacion = crear(sql);
		
		for (Factura factura : reclamo.getFacturas()){
			FacturaReclamo fr = new FacturaReclamo(factura.getNroFactura(), nroReclamoFacturacion);
			fr.guardar();			
		}		
	}
	
	public void actualizarReclamo(Reclamo reclamo) throws AccesoException, ConexionException {
		
		String sql = "UPDATE reclamos SET "
				+ " descripcion = '" + reclamo.getDescripcion() + "', "
				+ " idtiporeclamo = " + reclamo.getTipoDeReclamo().getId() + ", " 
				+ " idestadoreclamo = " + reclamo.getEstado().getId() + ", ";
		
				if(reclamo.getFechaCierre() != null)
					sql += " fechacierre = '" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFechaCierre()) + "', ";
				
				sql +=  " idcliente = " + reclamo.getCliente().getIdCliente()
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
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtiporeclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadoreclamo"));				
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
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtiporeclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadoreclamo"));				
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
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtiporeclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadoreclamo"));				
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
				
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadoreclamo"));				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtiporeclamo"));
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));			
				
				reclamo.setNroReclamo(rs.getInt("nroreclamo"));
				reclamo.setDescripcion(rs.getString("descripcion"));
				reclamo.setEstado(estado);
				reclamo.setTipoDeReclamo(tipoDeReclamo);
				reclamo.setCliente(cliente);
				reclamo.setFecha(rs.getDate("fecha"));
				reclamo.setFechaCierre(rs.getDate("fechacierre"));
				
				//TODO: cargar facturas
				
				return reclamo;
			}
			else{
				throw new NegocioException("El cliente id = " + reclamo.getNroReclamo() + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}			
	}
	
	
	/**
	 * Metodo Genérico para obtener reclamos
	 * 
	 * @param sql
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	private List<Reclamo> obtenerReclamos(String sql) throws ConexionException, AccesoException, NegocioException {
		
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
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			List<Reclamo> reclamos = new ArrayList<Reclamo>();						
			
			while(rs.next()){
				
				TipoDeReclamo tipoDeReclamo = TipoDeReclamoFactory.get(rs.getInt("idtiporeclamo"));
				EstadoDeReclamo estado = EstadoDeReclamoFactory.get(rs.getInt("idestadoreclamo"));				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));
				
				Reclamo reclamo = null;				

				if (TipoDeReclamo.ZONA.equals(tipoDeReclamo)){
					reclamo = new ReclamoZona();					
				} else if (TipoDeReclamo.FACTURACION.equals(tipoDeReclamo)){					
					reclamo = new ReclamoFacturacion();					
				}else if(TipoDeReclamo.CANTIDADES.equals(tipoDeReclamo) 
							|| TipoDeReclamo.FALTANTES.equals(tipoDeReclamo)
								|| TipoDeReclamo.PRODUCTO.equals(tipoDeReclamo)){
					reclamo = new ReclamoDistribucion();
				} else {
					reclamo = new ReclamoCompuesto();
				}
											
				reclamo.setNroReclamo(rs.getInt("nroreclamo"));
				reclamo.setDescripcion(rs.getString("descripcion"));
				reclamo.setEstado(estado);
				reclamo.setTipoDeReclamo(tipoDeReclamo);
				reclamo.setCliente(cliente);
				reclamo.setFecha(rs.getDate("fecha"));
				reclamo.setFechaCierre(rs.getDate("fechacierre"));
				
				reclamos.add(reclamo);
			}
			
			return reclamos;
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}	
	
	
	/**
	 * Obtengo reclamos por su {@link TipoDeReclamo}
	 * @param tipo
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public List<Reclamo> obtenerReclamosPorTipo(TipoDeReclamo tipo) throws ConexionException, AccesoException, NegocioException{
		
		String tabla = (tipo.equals(TipoDeReclamo.COMPUESTO)) ? "reclamoscompuestos" : "reclamos";
		String sql = "SELECT * FROM " + tabla +" WHERE idtiporeclamo = " + tipo.getId();
		
		return obtenerReclamos(sql);
	}
	
	
	/**
	 * Obtengo una lista de reclamos en base al reclamo Compuesto que lo contiene
	 * 
	 * @param nroReclamoCompuesto
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public List<Reclamo> obtenerReclamosPorReclamoCompuesto(Integer nroReclamoCompuesto) throws ConexionException, AccesoException, NegocioException{
		
		String sql = "SELECT * FROM reclamos WHERE nroreclamocompuesto = " + nroReclamoCompuesto;
		
		return obtenerReclamos(sql);
	}
	
	
	/**
	 * Obtengo un reclamo por su nro identificador
	 * 
	 * @param nroDeReclamo
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public Reclamo obtenerReclamoPorNroDeReclamo(Integer nroDeReclamo) throws ConexionException, AccesoException, NegocioException{
		String sql = "SELECT * FROM reclamos WHERE nroreclamo = " + nroDeReclamo;
		
		return obtenerReclamos(sql).get(0);
	}

}
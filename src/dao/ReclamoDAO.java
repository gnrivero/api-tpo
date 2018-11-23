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
import model.reclamo.ReclamoFactory;
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
	
	
	//Creacion
	
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
	
	public Integer crearReclamoDistribucion(ReclamoDistribucion reclamo) throws ConexionException, AccesoException {
		
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
		
		return crear(sql);
	}
	
	
	public Integer crearReclamoZona(ReclamoZona reclamo) throws ConexionException, AccesoException {
		
		String sql = INSERT_RECLAMO_ZONA
				+ " VALUES "
				+ "('" + reclamo.getDescripcion() + "', "
				+ reclamo.getTipoDeReclamo().getId() + ", "
				+ reclamo.getEstado().getId() + ", "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ", "
				+ "'" + reclamo.getZona() + "', "
				+ reclamo.getNroReclamoCompuesto() + ")";		
		
		return crear(sql);
	}
	
	public Integer crearReclamoFacturacion(ReclamoFacturacion reclamo) throws ConexionException, AccesoException {
		
		String sql = INSERT_RECLAMO_FACT  
				+ " VALUES " 
				+ "('" + reclamo.getDescripcion() + "', "
				+ reclamo.getTipoDeReclamo().getId() + ", "
				+ reclamo.getEstado().getId() + ", "
				+ "'" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFecha()) + "', "
				+ reclamo.getCliente().getIdCliente() + ", "
				+ reclamo.getNroReclamoCompuesto() + ")";
		
		Integer nroReclamoFacturacion = crear(sql);
		
		reclamo.setNroReclamo(nroReclamoFacturacion);
		
		for (Factura factura : reclamo.getFacturas()){
			FacturaReclamo fr = new FacturaReclamo(factura, reclamo);
			fr.guardar();			
		}
		
		return nroReclamoFacturacion;
	}
	
	
	//Actualizacion 
	
	public void actualizarReclamo(Reclamo reclamo) throws AccesoException, ConexionException {
		
		String tabla = "reclamos";
		
		String sentenciaSet = " SET "
				   + " descripcion = '" + reclamo.getDescripcion() + "', "
				   + " idtiporeclamo = " + reclamo.getTipoDeReclamo().getId() + ", " 
			 	   + " idestadoreclamo = " + reclamo.getEstado().getId() + ", ";
				
				if(reclamo.getNroReclamoCompuesto() != null)
					sentenciaSet +=" nroreclamocompuesto = " +  reclamo.getNroReclamoCompuesto() + ", ";
		
				if(reclamo.getFechaCierre() != null)
					sentenciaSet += " fechacierre = '" + DAOhelper.getAnioMesDiaHoraDateFormat().format(reclamo.getFechaCierre()) + "', ";
				
				sentenciaSet += " idcliente = " + reclamo.getCliente().getIdCliente();
				
				
				switch(reclamo.getTipoDeReclamo()){
				
					case ZONA:				
						
						ReclamoZona rzona = (ReclamoZona) reclamo;
						
						if(rzona.getZona() != null)
							sentenciaSet += ", zona = '" + rzona.getZona() + "' ";
						
					break;
					case CANTIDADES:
					case FALTANTES:
					case PRODUCTO:
						
						ReclamoDistribucion rdist = (ReclamoDistribucion) reclamo;
						if(rdist.getProducto() != null)
							sentenciaSet += ", idproducto = " + rdist.getProducto().getIdProducto(); 
						
						if(rdist.getCantidad() != null)
							sentenciaSet	+= " , cantidad = " + rdist.getCantidad();  
						
					break;
					case FACTURACION:
						
						ReclamoFacturacion rFact = (ReclamoFacturacion) reclamo;										
						
						for (Factura factura : rFact.getFacturas()){
							FacturaReclamo fr = new FacturaReclamo(factura, reclamo);
							fr.guardar();			
						}
						
					break;
					case COMPUESTO:
						tabla = "reclamoscompuestos";
					break;
					default:
						throw new RuntimeException("El tipo de reclamo indicado no existe");
						
				}
												
				String sentenciaWhere = " WHERE nroreclamo = " + reclamo.getNroReclamo();
				
				String sql = "UPDATE " + tabla + sentenciaSet + sentenciaWhere;
		
		actualizar(sql);		
	}
	
	
	//Obtencion
	
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
			rs = stmt.executeQuery("SELECT * FROM reclamoscompuestos WHERE nroreclamo = " + nroReclamo);
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
				throw new NegocioException("El reclamo Nro.: " + reclamo.getNroReclamo() + " no existe");
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
				throw new NegocioException("El reclamo Nro.: " + nroReclamo + " no existe");
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
				throw new NegocioException("El reclamo Nro.: " + nroReclamo + " no existe");
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
				
				reclamo.setFacturas(FacturaReclamoDAO.getInstancia().obtenerFacturasPorNroReclamo(nroReclamo));
				
				return reclamo;
			}
			else{
				throw new NegocioException("El reclamo Nro.: " + nroReclamo + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}			
	}
	
	
	/**
	 * Metodo Genérico para obtener reclamos.
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
				
				Reclamo reclamo = ReclamoFactory.getReclamo(tipoDeReclamo);
				
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
	 * Obtengo reclamos por su {@link TipoDeReclamo}. Principalmente para los listados.
	 * @param tipo
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public List<Reclamo> obtenerReclamosPorTipo(List<TipoDeReclamo> tipos) throws ConexionException, AccesoException, NegocioException{
		
		if(tipos.isEmpty())
			throw new NegocioException("Falta indicar los tipos de reclamos que se desean ver");
		
		List<Reclamo> resultado;
				
		List<Integer> idsTiposDeReclamo = new ArrayList<Integer>();
		
		boolean esCompuesto = false;
		for (TipoDeReclamo tipo : tipos){
			if(TipoDeReclamo.COMPUESTO.equals(tipo)){				
				idsTiposDeReclamo.add(tipo.getId());
				esCompuesto = true;
			}else{
				idsTiposDeReclamo.add(tipo.getId());
			}			
		}
		
		String sql = "SELECT * FROM reclamos WHERE idtiporeclamo " + DAOhelper.escribirSentenciaIn(idsTiposDeReclamo);
		
		resultado = obtenerReclamos(sql);
		
		if (esCompuesto){
			sql = "SELECT * FROM reclamoscompuestos";			
			resultado.addAll(obtenerReclamos(sql));
		}					
		
		return resultado;
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
	
	
	/**
	 * Devuelve reclamos en base a su numero y tipo
	 * 
	 * @param nroReclamo
	 * @param tipo
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public Reclamo obtenerReclamosPorNumeroYtipo(Integer nroReclamo, TipoDeReclamo tipoDeReclamo) throws ConexionException, AccesoException, NegocioException{
		
		String tabla = (tipoDeReclamo.equals(TipoDeReclamo.COMPUESTO)) ? "reclamoscompuestos" : "reclamos";
		
		String sql = "SELECT * FROM " + tabla +" WHERE idtiporeclamo = " + tipoDeReclamo.getId() + " AND nroreclamo = " + nroReclamo;
		
		Reclamo reclamo = obtenerReclamos(sql).get(0);
		
		if (tipoDeReclamo.equals(TipoDeReclamo.COMPUESTO)){
			List<Reclamo> reclamosHoja = this.obtenerReclamosPorReclamoCompuesto(reclamo.getNroReclamo());			
			reclamosHoja.forEach(r -> reclamo.addHoja(r));
		}
				
		return reclamo;
	}

}
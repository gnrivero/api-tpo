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
import model.Factura;
import model.ItemFactura;

public class FacturaDAO extends DAO {
	
	private static FacturaDAO instancia;
	
	private FacturaDAO(){}
	
	public static FacturaDAO getInstancia(){
		if (instancia == null)
			instancia = new FacturaDAO();
		
		return instancia;
	}
	
	public void agregarFactura(Factura factura) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO facturas (fechafactura) VALUE ";
		
		crear(sql);
	}
	
	
	/**
	 * Metodo generico para cargar facturas. 
	 * 
	 * @param sql
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	private List<Factura> obtenerFacturas(String sql) throws ConexionException, AccesoException, NegocioException{
			
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
			
			List<Factura> facturas = new ArrayList<Factura>();						
			
			while(rs.next()){
				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));
				
				Factura factura = new Factura(rs.getInt("nrofactura"), rs.getDate("fecha"), cliente);				
				facturas.add(factura);
			}
			
			return facturas;
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}		
	}
	
	/**
	 * Carga una Factura completa, con sus ItemsFactura.
	 * 
	 * @param nroFactura
	 * @return
	 * @throws AccesoException
	 * @throws ConexionException
	 * @throws NegocioException
	 */
	public Factura obtenerFactura(Integer nroFactura) throws AccesoException, ConexionException, NegocioException{
		
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
		
		String SQL = "SELECT * FROM facturas WHERE nrofactura = " + nroFactura;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				
				List<ItemFactura> itemsFactura = ItemFacturaDAO.getInstancia().obtenerItemsPorNroFactura(nroFactura);
				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));
				
				Factura factura = new Factura(rs.getInt("nrofactura"), rs.getDate("fechafactura"), cliente, itemsFactura);
				
				return factura;
			}
			else {
				throw new NegocioException("La factura " + nroFactura + " no existe");
			}
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}								
				
	}
	
	/**
	 * Obtiene las facturas indicadas en la lista que se recibe como parámetro
	 * 
	 * @param nrosFacturas
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public List<Factura> obtenerFacturasPorNro(List<Integer> nrosFacturas) throws ConexionException, AccesoException, NegocioException{
		
		String sql = "SELECT * FROM facturas  WHERE nrofactura " + DAOhelper.escribirSentenciaIn(nrosFacturas);
		
		return this.obtenerFacturas(sql);
	}
		
	/**
	 * Obtiene todas las facturas para un cliente
	 * 
	 * @param idCliente
	 * @return
	 * @throws ConexionException
	 * @throws AccesoException
	 * @throws NegocioException
	 */
	public List<Factura> obtenerFacturasPorCliente(Integer idCliente) throws ConexionException, AccesoException, NegocioException{
		
		String sql = "SELECT * FROM facturas WHERE idcliente = " + idCliente;
		
		return this.obtenerFacturas(sql);
	}	
}
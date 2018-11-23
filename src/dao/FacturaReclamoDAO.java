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
import model.TipoDeReclamo;
import model.TipoDeReclamoFactory;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoFactory;

public class FacturaReclamoDAO extends DAO {
	
	private static FacturaReclamoDAO instancia = null;
	
	private FacturaReclamoDAO(){}
	
	public static FacturaReclamoDAO getInstancia(){
		if (instancia == null)
			instancia = new FacturaReclamoDAO();
		
		return instancia;
	}
	
	public void crear(FacturaReclamo facturaReclamo) throws ConexionException, AccesoException {						
		crear("INSERT INTO facturasreclamos VALUES (" + facturaReclamo.getFactura().getNroFactura() +", " + facturaReclamo.getReclamo().getNroReclamo() + ")");		
	}
	
	public void borrar(FacturaReclamo facturaReclamo) throws ConexionException, AccesoException{				
		actualizar("DELETE FROM facturasreclamos WHERE nroreclamo = " + facturaReclamo.getReclamo().getNroReclamo());
	}
	
	public List<Factura> obtenerFacturasPorNroReclamo(Integer nroReclamo) throws ConexionException, AccesoException, NegocioException{
		
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
			rs = stmt.executeQuery("SELECT * FROM facturas f INNER JOIN facturasreclamos fr ON fr.nrofactura = f.nrofactura WHERE fr.nroreclamo = " + nroReclamo);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		
		try {
			
			List<Factura> facturas = new ArrayList<Factura>();						
			
			while(rs.next()){				
				Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(rs.getInt("idcliente"));				
				Factura factura = new Factura(rs.getInt("nrofactura"), rs.getDate("fechafactura"), cliente);				
				facturas.add(factura);
			}
			
			return facturas;
			
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
}
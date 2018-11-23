package test;

import java.util.ArrayList;
import java.util.List;

import controller.Sistema;
import dao.FacturaDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.Factura;
import model.Producto;
import model.TipoDeReclamo;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.ReclamoFacturacion;
import view.ClienteView;
import view.ReclamoView;

public class ReclamosTest {
		
	public static void main(String[] args) {
		
		//generacionDeReclamos();
		
		obtencionDeReclamos();
		
		try {
			Sistema.getInstance().cerrarReclamo(9, TipoDeReclamo.COMPUESTO);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		comenzarTratamientoReclamo(11);
//		cerrarReclamo(11);
		
		System.exit(0);	
	}
	
	public static void generacionDeReclamos(){
		generarReclamoDistribucion();
		
		try {
			generarReclamoCompuesto();
		} catch (NegocioException e) {
			e.printStackTrace();
		} catch (AccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		generarReclamoFacturacion();
	}
	
	
	public static void obtencionDeReclamos(){
				
		//Obtener reclamos zona
//		try {
//			List<ReclamoView> reclamosViews = Sistema.getInstance().obtenerReclamosPorTipo(TipoDeReclamo.COMPUESTO);
//			
//			reclamosViews.forEach(rv -> System.out.println(rv));
//			
//		} catch (NegocioException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public static void generarReclamoZona(){
		
//		//Cargo cliente
//		ClienteView clienteView = null;
//		try {
//			clienteView = Sistema.getInstance().obtenerCliente(1);
//		} catch (NegocioException e1) {
//			e1.printStackTrace();
//		}	
//		
//		Cliente cliente = new Cliente(clienteView.getNombre(), clienteView.getCuit(), clienteView.getDomicilio(), clienteView.getTelefono(), clienteView.getMail());
//		
//		try {
//			Sistema.getInstance().registrarReclamo("Hay alguien vendiendo en mi zona", TipoDeReclamo.ZONA, cliente, "Villa Urquiza");
//		} catch (NegocioException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void generarReclamoDistribucion(){
		
//		//Cargo producto
//		try {
//			detergente = Sistema.getInstance().obtenerProducto(1);
//		} catch (NegocioException e) {
//			e.printStackTrace();
//		}
//		
//		
//		//Cargo cliente
//		try {
//			cliente = Sistema.getInstance().obtenerCliente(1);
//		} catch (NegocioException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}		
//		
//		//Creo el reclamo
//		try {
//			Sistema.getInstance().registrarReclamo("Me piden detergente", TipoDeReclamo.PRODUCTO, cliente, detergente, 2);
//			System.out.println("Generé el reclamo ");
//		} catch (NegocioException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}				
	}
	
	public static void generarReclamoCompuesto() throws NegocioException, AccesoException, ConexionException{
		
//		//Cargo cliente
//		ClienteView clienteView = Sistema.getInstance().obtenerCliente(1);
//		Cliente cliente = new Cliente(clienteView.getNombre(), clienteView.getCuit(), clienteView.getDomicilio(), clienteView.getTelefono(), clienteView.getMail());
//		
//		//Cargo producto
//		Producto producto = Sistema.getInstance().obtenerProducto(2);//Papel
//		
//		//Reclamos Hoja
//		Reclamo rFaltantes = new ReclamoDistribucion("Falta Papel", TipoDeReclamo.FALTANTES, cliente, producto, 12);
//		Reclamo rProducto = new ReclamoDistribucion("Me piden Papel", TipoDeReclamo.PRODUCTO, cliente, producto, 1);
//		
//		
//		Factura fact_2 = FacturaDAO.getInstancia().obtenerFactura(2);
//		Factura fact_3 = FacturaDAO.getInstancia().obtenerFactura(3);
//				
//		List<Factura> facturas = new ArrayList<Factura>();
//		facturas.add(fact_2);
//		facturas.add(fact_3);
//		
//		Reclamo rFacturacion = new ReclamoFacturacion("Refacturar", TipoDeReclamo.FACTURACION, cliente, facturas);			
//		
//		List<Reclamo> reclamos = new ArrayList<Reclamo>();		
//		reclamos.add(rFaltantes);
//		reclamos.add(rProducto);
//		reclamos.add(rFacturacion);
//		
//		//Creo reclamo compuesto		
//		try {
//			Sistema.getInstance().registrarReclamoCompuesto("Reclamo Compuesto", TipoDeReclamo.COMPUESTO, cliente, reclamos);
//		} catch (NegocioException e) {
//			e.printStackTrace();
//		}		
	}
	
	public static void generarReclamoFacturacion(){
				
//		List<Factura> facturas = new ArrayList<Factura>();
//		facturas.add(factura);
//		
//		System.out.println("El subtotal de la factura es " + factura.calcularSubTotal());
//		System.out.println("El total de la factura es " + factura.calcularTotal());
//		
//		try {
//			Sistema.getInstance().registrarReclamo("Reclamo Factura", model.TipoDeReclamo.FACTURACION, cliente, facturas);
//		} catch (NegocioException e) {
//			e.printStackTrace();
		
//		}		
	}
	
	public static void comenzarTratamientoReclamo(Integer nroReclamo){
//		try {
//			Sistema.getInstance().comenzarTratamientoReclamo(nroReclamo);
//		} catch (NegocioException e) {
//			e.printStackTrace();
//		}
	}	
	
	public static void cerrarReclamo(Integer nroReclamo){
		try {
			Sistema.getInstance().cerrarReclamo(nroReclamo);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}

}
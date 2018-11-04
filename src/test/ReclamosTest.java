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

public class ReclamosTest {
		
	public static void main(String[] args) {
		
		//generarReclamoZona();
		
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
		
		System.exit(0);	
	}
	
	public static void generarReclamoZona(){
		
		//Cargo cliente
		Cliente cliente = null;
		try {
			cliente = Sistema.getInstance().obtenerCliente(1);
		} catch (NegocioException e1) {
			e1.printStackTrace();
		}	
		
		try {
			Sistema.getInstance().registrarReclamo("Hay alguien vendiendo en mi zona", TipoDeReclamo.ZONA, cliente, "Villa Urquiza");
		} catch (NegocioException e) {
			e.printStackTrace();
		}
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
		
		//Cargo cliente
		Cliente cliente = Sistema.getInstance().obtenerCliente(1);
		
		//Cargo producto
		Producto producto = Sistema.getInstance().obtenerProducto(2);//Papel
		
		//Reclamos Hoja
		Reclamo rFaltantes = new ReclamoDistribucion("Falta Papel", TipoDeReclamo.FALTANTES, cliente, producto, 12);
		Reclamo rProducto = new ReclamoDistribucion("Me piden Papel", TipoDeReclamo.PRODUCTO, cliente, producto, 1);
		
		
		Factura fact_2 = FacturaDAO.getInstancia().obtenerFactura(2);
		Factura fact_3 = FacturaDAO.getInstancia().obtenerFactura(3);
				
		List<Factura> facturas = new ArrayList<Factura>();
		facturas.add(fact_2);
		facturas.add(fact_3);
		
		Reclamo rFacturacion = new ReclamoFacturacion("Refacturar", TipoDeReclamo.FACTURACION, cliente, facturas);			
		
		List<Reclamo> reclamos = new ArrayList<Reclamo>();		
		reclamos.add(rFaltantes);
		reclamos.add(rProducto);
		reclamos.add(rFacturacion);
		
		//Creo reclamo compuesto		
		try {
			Sistema.getInstance().registrarReclamoCompuesto("Reclamo Compuesto", TipoDeReclamo.COMPUESTO, cliente, reclamos);
		} catch (NegocioException e) {
			e.printStackTrace();
		}		
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

}
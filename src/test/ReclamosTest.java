package test;

import java.util.ArrayList;
import java.util.List;

import controller.Sistema;
import excepciones.NegocioException;
import model.Cliente;
import model.Factura;
import model.ItemFactura;
import model.Producto;
import model.TipoDeReclamo;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoDistribucion;

public class ReclamosTest {
		
	public static void main(String[] args) {
		
		generarReclamoZona();
		
		//generarReclamoDistribucion();
		
		//generarReclamoCompuesto();
		
		//generarReclamoFacturacion();
		
		System.exit(0);	
	}
		
	static Cliente cliente = new Cliente(1000, "ClienteDePrueba", "Cabrera 5400", "4700-0000", "prueba@mock.com");
	static Producto detergente = new Producto("ABC78A", "Detergente","Lava cosas",50);
	static Producto papelSecar = new Producto("BOC433", "Papel","Seca cosas",34);
	static Producto matafuegos = new Producto("FE8EOQ", "Matafuegos","Apaga el fuego",500);
	static Factura factura = new Factura();
	
	static{
		
		ItemFactura itemF_1 = new ItemFactura(4, detergente);
		ItemFactura itemF_2 = new ItemFactura(2, matafuegos);
		
		factura.getItemFactura().add(itemF_1);
		factura.getItemFactura().add(itemF_2);		
	}
	
	public static void generarReclamoZona(){
		
		//Cargo cliente
		try {
			cliente = Sistema.getInstance().obtenerCliente(1);
		} catch (NegocioException e1) {
			e1.printStackTrace();
		}	
		
		try {
			Sistema.getInstance().registrarReclamo("Hay alguien vendiendo en mi zona", TipoDeReclamo.ZONA, cliente, "Almagro");
		} catch (NegocioException e) {
			e.printStackTrace();
		}
	}
	
	public static void generarReclamoDistribucion(){
		
		//Cargo producto
		try {
			detergente = Sistema.getInstance().obtenerProducto(1);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		
		//Cargo cliente
		try {
			cliente = Sistema.getInstance().obtenerCliente(1);
		} catch (NegocioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		//Creo el reclamo
		try {
			Sistema.getInstance().registrarReclamo("Me piden detergente", TipoDeReclamo.PRODUCTO, cliente, detergente, 2);
			System.out.println("Generé el reclamo ");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public static void generarReclamoCompuesto(){
		
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
		
		Reclamo rFaltantes = new ReclamoDistribucion("Falta detergente", TipoDeReclamo.FALTANTES, cliente, detergente, 1);
		Reclamo rProducto = new ReclamoDistribucion("Me piden detergente", TipoDeReclamo.PRODUCTO, cliente, detergente, 1);
		
		reclamos.add(rFaltantes);
		reclamos.add(rProducto);
		
		try {
			Sistema.getInstance().registrarReclamoCompuesto("Reclamo Compuesto", TipoDeReclamo.COMPUESTO, cliente, reclamos);
		} catch (NegocioException e) {
			e.printStackTrace();
		}		
	}
	
	public static void generarReclamoFacturacion(){
		
		List<Factura> facturas = new ArrayList<Factura>();
		facturas.add(factura);
		
		System.out.println("El subtotal de la factura es " + factura.calcularSubTotal());
		System.out.println("El total de la factura es " + factura.calcularTotal());
		
		try {
			Sistema.getInstance().registrarReclamo("Reclamo Factura", model.TipoDeReclamo.FACTURACION, cliente, facturas);
		} catch (NegocioException e) {
			e.printStackTrace();
		}		
	}
	
	
	
}

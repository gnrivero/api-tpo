package test;

import java.util.ArrayList;
import java.util.List;

import controller.Sistema;
import model.Cliente;
import model.Factura;
import model.ItemFactura;
import model.Producto;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.Reclamo.TipoDeReclamo;

public class ReclamosTest {
	
	static Cliente cliente = new Cliente(1000, "ClienteDePrueba", "Cabrera 5400", "4700-0000", "prueba@mock.com");
	static Producto producto_1 = new Producto("Detergente","Lava cosas",50);
	static Producto producto_2 = new Producto("Papel","Seca cosas",34);
	static Producto producto_3 = new Producto("Matafuegos","Apaga el fuego",500);
	static Factura factura = new Factura();
	
	static{
		
		ItemFactura itemF_1 = new ItemFactura(4, producto_1);
		ItemFactura itemF_2 = new ItemFactura(2, producto_3);
		
		factura.getItemFactura().add(itemF_1);
		factura.getItemFactura().add(itemF_2);		
	}
	
	public static void generarReclamoCompuesto(){
		
		List<Reclamo> reclamos = new ArrayList<Reclamo>();
		
		Reclamo rFaltantes = new ReclamoDistribucion("Falta detergente", TipoDeReclamo.FALTANTES, cliente, producto_1, 1);
		Reclamo rProducto = new ReclamoDistribucion("Me piden detergente", TipoDeReclamo.PRODUCTO, cliente, producto_1, 1);
		
		reclamos.add(rFaltantes);
		reclamos.add(rProducto);
		
		Sistema.getInstance().registrarReclamoCompuesto("Reclamo Compuesto", TipoDeReclamo.COMPUESTO, cliente, reclamos);		
	}
	
	public static void generarReclamoFacturacion(){
		
		List<Factura> facturas = new ArrayList<Factura>();
		facturas.add(factura);
		
		System.out.println("El subtotal de la factura es " + factura.calcularSubTotal());
		System.out.println("El total de la factura es " + factura.calcularTotal());
		
		Sistema.getInstance().registrarReclamo("Reclamo Factura", TipoDeReclamo.FACTURACION, cliente, facturas);		
	}
	
	
	public static void main(String[] args) {
		
		generarReclamoCompuesto();
		
		generarReclamoFacturacion();
		
		System.exit(0);	
	}
}

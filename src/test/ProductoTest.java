package test;

import controller.Sistema;

public class ProductoTest {

	public static void main(String[] args) {
		
		crearProductos();
		
		System.exit(0);
	}
	
	public static void crearProductos(){
		
		try{
			
			Sistema.getInstance().agregarProducto("ABC78A", "Detergente","Lava cosas",50);
			Sistema.getInstance().agregarProducto("BOC433", "Papel","Seca cosas",34);
			Sistema.getInstance().agregarProducto("FE8EOQ", "Matafuegos","Apaga el fuego",500);
			
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}

}

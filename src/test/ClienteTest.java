package test;

import controller.Sistema;
import excepciones.NegocioException;

public class ClienteTest {

	public static void main(String[] args) {
		
		crearCliente();
		
		System.exit(0);
	}
	
	public static void crearCliente(){		
		
		try {
			Sistema.getInstance().agregarCliente("ClienteDePrueba", "Cabrera 5400", "4700-0000", "prueba@mock.com");
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
	}

}

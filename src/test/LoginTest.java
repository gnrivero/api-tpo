package test;

import controller.Sistema;
import excepciones.NegocioException;

public class LoginTest {
	
	public static void main(String[] args) {
			
		try {
			Sistema.getInstance().loguearUsuario("grivero", "123456");			
			System.out.println("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
			Sistema.getInstance().desloguearUsuario();
		} catch (NegocioException e) {
			System.out.println("Error al hacer login");
			e.printStackTrace();
		}
		
		try {				
			Sistema.getInstance().loguearUsuario("fruta", "123456");					
			System.out.println("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
			Sistema.getInstance().desloguearUsuario();			
		} catch (NegocioException e) {
			System.out.println("Error al hacer login");
			e.printStackTrace();
		}	
		
		try {
			Sistema.getInstance().loguearUsuario("gerentedistribucion", "123456");					
			System.out.println("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
			Sistema.getInstance().desloguearUsuario();
		} catch (NegocioException e) {
			System.out.println("Error al hacer login");
			e.printStackTrace();
		}	
	}

}

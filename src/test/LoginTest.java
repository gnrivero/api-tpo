package test;

import controller.Sistema;
import excepciones.NegocioException;

public class LoginTest {
	
	public static void main(String[] args) {
			
		try {
			
			Sistema.getInstance().loguearUsuario("grivero", "grivero");			
			System.out.println("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
			
			Sistema.getInstance().loguearUsuario("fruta", "123456");					
			System.out.println("Hola " + Sistema.getInstance().getUsuarioLogueado().getUsername());
			
		} catch (NegocioException e) {
			System.out.println("Error al hacer login");
			e.printStackTrace();
		}	
	}

}

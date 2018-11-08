package test;

import controller.Sistema;
import excepciones.NegocioException;
import model.Rol;

public class UsuarioTest {
	
	static Rol rolAdmin;
	
	static {		
		rolAdmin = new Rol(1, "Admin");		
	}
	
	public static void main(String[] args) {
		
		crearNuevoUsuario();

		editarUsuario();
		
		System.exit(0);
	}
	
	
	public static void crearNuevoUsuario(){
		
		try {
			Sistema.getInstance().crearNuevoUsuario("grivero", "grivero", rolAdmin);
		} catch (NegocioException e) {			
			e.printStackTrace();
		}
		
	}
	
	public static void editarUsuario(){
		
	}	

}

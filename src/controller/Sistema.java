package controller;

import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.UsuarioException;
import model.Usuario;

public class Sistema {
	
	private static Sistema instance;
	
	private Sistema(){}
	
	public static Sistema getInstance(){
		
		if (instance == null)
			instance = new Sistema();
		
		return instance;
	}
	
	
	public void loguearUsuario(String username, String password){
		
		try {
			Usuario usuario = UsuarioDAO.getInstancia().buscarUsuarioPorUsernameYpassword(username, password);
		} catch (ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AccesoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}

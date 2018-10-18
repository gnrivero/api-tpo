package controller;

import java.util.HashMap;
import java.util.Map;

import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.UsuarioException;
import model.Usuario;

public class Sistema {
		
	//Singleton
	private static Sistema instance;
	
	private Sistema(){
		usuariosLogueados = new HashMap<Integer, Usuario>();
	}
	
	public static Sistema getInstance(){
		
		if (instance == null)
			instance = new Sistema();
		
		return instance;
	}
	//Fin Singleton
	
	private Map<Integer, Usuario> usuariosLogueados;
	
	
	
	//Login
	private void agregarUsuarioLogueado(Usuario usuario){
		this.usuariosLogueados.put(usuario.getIdUsuario(), usuario);
	}
	
	private void quitarUsuarioLogueado(Usuario usuario){
		this.usuariosLogueados.remove(usuario.getIdUsuario());
	}
	
	
	public void loguearUsuario(String username, String password){
		
		try {
			Usuario usuario = UsuarioDAO.getInstancia().buscarUsuarioPorUsernameYpassword(username, password);			
			this.agregarUsuarioLogueado(usuario);
			
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
	
	public void desloguearUsuario(Usuario usuario){
		this.quitarUsuarioLogueado(usuario);
	}
	//Fin Login

}

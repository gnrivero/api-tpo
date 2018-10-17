package controller;

import dao.UsuarioDAO;

public class Sistema {
	
	
	public void loguearUsuario(String username, String password){
		
		new UsuarioDAO().buscarUsuarioPorUsernameYpassword(username, password);
		
		
		
	}

}

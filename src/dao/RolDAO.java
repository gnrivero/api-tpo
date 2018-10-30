package dao;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Rol;

public class RolDAO extends DAO {
	
	private static RolDAO instancia;
	
	public static RolDAO getInstancia(){
		if(instancia == null)
			instancia = new RolDAO();
		
		return instancia;
	}
	
	public void crearRol(Rol rol) throws ConexionException, AccesoException {
		
		String sql = "INSERT INTO roles (descripcion) VALUES ('" + rol.getDescripcion() + "' )"; 
		
		crear(sql);
	}

}
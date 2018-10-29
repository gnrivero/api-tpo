package dao;

import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Rol;

public class RolDAO extends DAO {
	
	private static RolDAO instancia;
	
	public void crearRol(Rol rol) throws ConexionException, AccesoException{
		
		String sql = "INSERT INTO roles (descripcion) VALUES ('" + rol.getDescripcion() + "' )"; 
		
		crear(sql);
	}

}
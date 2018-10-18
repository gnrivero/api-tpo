package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.UsuarioException;
import model.Rol;
import model.RolPorUsuario;
import model.Tablero;
import model.Usuario;
import view.ClienteView;
import view.ProductoView;
import view.ReclamoView;

public class Sistema {
		
	//Singleton
	private static Sistema instance;
	private Map<Integer, Usuario> usuariosLogueados;
	private Tablero tablero;
	private List<Rol> roles;
	private List<RolPorUsuario> rolesPorUsuario;
	
	private Sistema(){
		usuariosLogueados = new HashMap<Integer, Usuario>();
	}
	
	public static Sistema getInstance(){
		
		if (instance == null)
			instance = new Sistema();
		
		return instance;
	}
	//Fin Singleton	
	
	/*USUARIO*/
	public void loguearUsuario(String username, String password){
		
		try {
			Usuario usuario = UsuarioDAO.getInstancia().buscarUsuarioPorUsernameYpassword(username, password);			
			this.agregarUsuarioLogueado(usuario);
			
		} catch (ConexionException e) {
			e.printStackTrace();
		} catch (AccesoException e) {
			e.printStackTrace();
		} catch (UsuarioException e) {
			e.printStackTrace();
		}	
	}
	
	public void desloguearUsuario(Usuario usuario){
		this.usuariosLogueados.remove(usuario.getIdUsuario());
	}
	
	private void agregarUsuarioLogueado(Usuario usuario){
		this.usuariosLogueados.put(usuario.getIdUsuario(), usuario);
	}
	
	private void quitarUsuarioLogueado(String username) {
		
	}
	
	private Usuario buscarUsuarioPorUsername(String username) {
		return null;
	}
	/*USUARIO*/
	
	/*ROL*/
	public void asociarRolUsuario(Integer idUsuario, Integer idRol) {
		
	}
	
	public void crearNuevoRol(String nombre, List<Integer> tiposDeReclamo) {
		
	}
	
	private List<RolPorUsuario> buscarRolPorUsuario(Integer idUsuario, Integer idRol) {
		return null;
	}
	
	private void agregarNuevoRol(Rol rol) {
		
	}
	/*ROL*/
	
	/*CLIENTE*/
	public void agregarCliente(ClienteView cliente) {
		
	}
	public void modificarCliente(Integer idCliente, ClienteView cliente) {
		
	}
	public void eliminarCliente(Integer idCliente) {
		
	}
	/*CLIENTE*/
	
	/*PRODUCTO*/
	public void agregarProducto(ProductoView producto) {
		
	}
	public void modificarProducto(Integer idProducto, ProductoView producto) {
		
	}
	public void eliminarProducto(Integer idProducto) {
		
	}
	/*PRODUCTO*/
	
	/*RECLAMO*/
	public void registrarReclamo(ReclamoView reclamo) {
		
	}
	
	public void cambiarEstadoDeReclamo(Integer nroReclamo, String estadoReclamoNuevo) {
		
	}
	
	public void agregarDescripcionReclamo(Integer nroReclamo, String descripcion) {
		
	}
	
	public void registrarReclamoCompuesto(List<ReclamoView> reclamos) {
		
	}
	/*RECLAMO*/
}

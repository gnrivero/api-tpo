package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.UsuarioException;
import model.Cliente;
import model.Factura;
import model.Producto;
import model.Rol;
import model.RolPorUsuario;
import model.Tablero;
import model.Usuario;
import model.reclamo.Reclamo;
import model.reclamo.Reclamo.TipoDeReclamo;
import model.reclamo.ReclamoCompuesto;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.ReclamoFacturacion;
import model.reclamo.ReclamoZona;
import view.ClienteView;
import view.ProductoView;

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
	private Tablero tablero;
	private List<Rol> roles;
	private List<RolPorUsuario> rolesPorUsuario;
	
	
	//Login
	private void agregarUsuarioLogueado(Usuario usuario){
		this.usuariosLogueados.put(usuario.getIdUsuario(), usuario);
	}
	
	private void quitarUsuarioLogueado(Usuario usuario){
		this.usuariosLogueados.remove(usuario.getIdUsuario());
	}
	
	
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
		this.quitarUsuarioLogueado(usuario);
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
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, Producto producto, int cantidad){
		Reclamo reclamoAcrear = new ReclamoDistribucion(descripcion, tipoDeReclamo, cliente, producto, cantidad);		
		reclamoAcrear.guardar();
	}
	
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente,String zona){
		Reclamo reclamoAcrear = new ReclamoZona(descripcion, tipoDeReclamo, cliente, zona);
		reclamoAcrear.guardar();
	}
	
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Factura> facturas){
		Reclamo reclamoAcrear = new ReclamoFacturacion(descripcion, tipoDeReclamo, cliente, facturas);
		reclamoAcrear.guardar();
	}
	
	public void cambiarEstadoDeReclamo(Integer nroReclamo, String estadoReclamoNuevo) {
		
	}
	
	public void agregarDescripcionReclamo(Integer nroReclamo, String descripcion) {
		
	}
	
	public void registrarReclamoCompuesto(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Reclamo> reclamos) { 
		
		Reclamo reclamoCompuesto = new ReclamoCompuesto(descripcion, tipoDeReclamo, cliente, reclamos);
		
		reclamoCompuesto.guardar();		
	}
	/*RECLAMO*/
}

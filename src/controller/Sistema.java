package controller;

import java.util.List;

import dao.ClienteDAO;
import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.Factura;
import model.Producto;
import model.Rol;
import model.RolPorUsuario;
import model.Tablero;
import model.TipoDeReclamo;
import model.Usuario;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoCompuesto;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.ReclamoFacturacion;
import model.reclamo.ReclamoZona;
import view.ProductoView;

public class Sistema {
		
	//Singleton
	private static Sistema instance;

	private Sistema(){		
		this.usuarioLogueado = null;
		this.tablero = new Tablero();
	}
	
	public static Sistema getInstance(){
		
		if (instance == null)
			instance = new Sistema();
		
		return instance;
	}
	//Fin: Singleton
	
	//Miembros de Sistema
	private Usuario usuarioLogueado;
	private Tablero tablero;
		
	public Usuario getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(Usuario usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	//Fin Miembros de Sistema
	
	//Usuario
	public void loguearUsuario(String username, String password) throws NegocioException {
		
		try {
			Usuario usuario = UsuarioDAO.getInstancia().buscarUsuarioPorUsernameYpassword(username, password);
			this.setUsuarioLogueado(usuario);
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("Error de autenticacion. Verifique Username y Contraseña"); 
		}						
	}
	
	public void desloguearUsuario(){
		this.usuarioLogueado =  null;
	}
	
	public void crearNuevoUsuario(String username, String password, Rol rol) throws NegocioException{
		
		Usuario nuevoUsuario = new Usuario(username, password, rol);
		try {
			nuevoUsuario.guardar();
		} catch (ConexionException | AccesoException e) {			
			e.printStackTrace();
			throw new NegocioException("No se pudo crear usuario");
		}
		
	}
	/*Fin: Usuario*/
	
	//Roles
	public void asociarRolUsuario(Integer idUsuario, Integer idRol) {
		
	}
	
	public void crearNuevoRol(String nombre, List<Integer> tiposDeReclamo) {
		
	}
	
	private List<RolPorUsuario> buscarRolPorUsuario(Integer idUsuario, Integer idRol) {
		return null;
	}
	
	private void agregarNuevoRol(Rol rol) {
		
	}
	//Fin: Roles
	
	/* CLIENTE */
	public void agregarCliente(String nombre, String domicilio, String telefono, String mail) throws NegocioException {
		Cliente nuevoCliente = new Cliente(nombre, domicilio, telefono, mail);
		
		try {
			nuevoCliente.guardar();
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("No se pudo guardar el cliente");
		}			
	}
	
	public void modificarCliente(Integer idCliente, String nombre, String domicilio, String telefono, String mail) throws NegocioException {
		Cliente cliente = new Cliente(nombre, domicilio, telefono, mail);
		try {
			cliente.guardar();
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("No se pudo guardar el cliente");
		}
	}
		
	public void eliminarCliente(Integer idCliente) throws NegocioException{
		Cliente cliente;
		try {
			cliente = ClienteDAO.getInstancia().obtenerClientePorId(idCliente);
			cliente.darDeBaja();		
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("No se pudo eliminar cliente");
		}
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
		try {
			reclamoAcrear.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente,String zona){
		Reclamo reclamoAcrear = new ReclamoZona(descripcion, tipoDeReclamo, cliente, zona);
		try {
			reclamoAcrear.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Factura> facturas){
		Reclamo reclamoAcrear = new ReclamoFacturacion(descripcion, tipoDeReclamo, cliente, facturas);
		try {
			reclamoAcrear.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {
			
			e.printStackTrace();
		}
	}
	
	public void tratarReclamo(Integer nroReclamo) {
		
	}
	
	public void cerrarReclamo(Integer nroReclamo) {
		
	}
	
	public void agregarDescripcionReclamo(Integer nroReclamo, String descripcion) {
		
	}
	
	public void registrarReclamoCompuesto(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Reclamo> reclamos) { 
		
		Reclamo reclamoCompuesto = new ReclamoCompuesto(descripcion, tipoDeReclamo, cliente, reclamos);
		
		try {
			reclamoCompuesto.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {			
			e.printStackTrace();
		}		
	}
	/*RECLAMO*/
}

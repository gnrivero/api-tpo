package controller;

import java.util.ArrayList;
import java.util.List;
import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.ReclamoDAO;
import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.Factura;
import model.Producto;
import model.Rol;
import model.Tablero;
import model.TipoDeReclamo;
import model.Usuario;
import model.reclamo.Reclamo;
import model.reclamo.ReclamoCompuesto;
import model.reclamo.ReclamoDistribucion;
import model.reclamo.ReclamoFacturacion;
import model.reclamo.ReclamoZona;
import view.ProductoView;
import view.ReclamoView;

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
			throw new NegocioException("Error de autenticacion. Verifique Username y Contraseña"); 
		}						
	}
	
	public void desloguearUsuario(){
		this.setUsuarioLogueado(null);		
	}
	
	public void crearNuevoUsuario(String username, String password, Rol rol) throws NegocioException {
		
		Usuario nuevoUsuario = new Usuario(username, password, rol);
		try {
			nuevoUsuario.guardar();
		} catch (ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo crear usuario");
		}
		
	}
	/*Fin: Usuario*/
	
	//Roles
	public void crearNuevoRol(String nombre, List<Integer> idsTiposReclamo) {
		
	}
	
	//Fin: Roles
	
	//Cliente
	public void agregarCliente(String nombre, String domicilio, String telefono, String mail) throws NegocioException {
		Cliente nuevoCliente = new Cliente(nombre, domicilio, telefono, mail);		
		try {
			nuevoCliente.guardar();
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("No se pudo crear el cliente");
		}			
	}
	
	public void modificarCliente(Integer idCliente, String nombre, String domicilio, String telefono, String mail) throws NegocioException {
		Cliente cliente = new Cliente(idCliente, nombre, domicilio, telefono, mail);
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
	
	//TODO: Esto deberia devolver un ClienteView
	public Cliente obtenerCliente(Integer idCliente) throws NegocioException{
		try {
			return ClienteDAO.getInstancia().obtenerClientePorId(idCliente);
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo cargar cliente");
		}
	}
	//Fin: Cliente
	
	//Producto
	public void agregarProducto(String codigo, String titulo, String descripcion, float precio) throws NegocioException {		
		Producto producto = new Producto(codigo, titulo, descripcion, precio);
		try {
			producto.guardar();
		} catch (ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo crear producto");
		}
	}
	
	public void modificarProducto(Integer idProducto, ProductoView producto) {
		
	}
	
	public void eliminarProducto(Integer idProducto) {
		
	}
	
	public Producto obtenerProducto(Integer idProducto) throws NegocioException{		
		try {
			return ProductoDAO.getInstancia().obtenerProductoPorId(idProducto);
		} catch (NegocioException | ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo cargar producto");
		}		
	}
	//Fin: Producto
	
	//Reclamos
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, Producto producto, int cantidad) throws NegocioException{
		Reclamo reclamoAcrear = new ReclamoDistribucion(descripcion, tipoDeReclamo, cliente, producto, cantidad);
		try {
			reclamoAcrear.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);
		}
	}
	
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente,String zona) throws NegocioException{
		Reclamo reclamoAcrear = new ReclamoZona(descripcion, tipoDeReclamo, cliente, zona);
		try {
			reclamoAcrear.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);
		}
	}
	
	public void registrarReclamo(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Factura> facturas) throws NegocioException{
		Reclamo reclamoAcrear = new ReclamoFacturacion(descripcion, tipoDeReclamo, cliente, facturas);
		try {
			reclamoAcrear.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);			
		}
	}
	
	public void registrarReclamoCompuesto(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, List<Reclamo> reclamos) throws NegocioException { 
		
		Reclamo reclamoCompuesto = new ReclamoCompuesto(descripcion, tipoDeReclamo, cliente, reclamos);		
		try {
			reclamoCompuesto.guardar();
		} catch (ConexionException | AccesoException | NegocioException e) {			
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);
		}		
	}
	
	public List<ReclamoView> obtenerReclamosPorTipo(TipoDeReclamo tipo) throws NegocioException {
		try {
			
			List<Reclamo> reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(tipo);							
			List<ReclamoView> reclamosViews = new ArrayList<ReclamoView>();
			
			reclamos.forEach(r -> reclamosViews.add(r.toView()));
						
			return reclamosViews;
		} catch (ConexionException | AccesoException | NegocioException e) {			
			throw new NegocioException("No se pudo cargar reclamos " + tipo.getDenominacion());
		}		
	}
	
	
	public void comenzarTratamientoReclamo(Integer nroReclamo) throws NegocioException {
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamoPorNroDeReclamo(nroReclamo);
			reclamo.comenzarTratamiento();
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo pasar reclamo a Tratamiento " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}	
	}
	
	public void cerrarReclamo(Integer nroReclamo) throws NegocioException {
		
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamoPorNroDeReclamo(nroReclamo);
			reclamo.cerrar();
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo cerrar reclamo " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}				
	}
	
	public void cerrarReclamo(Integer nroReclamo, TipoDeReclamo tipoDeReclamo) throws NegocioException {
		
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamosPorNumeroYtipo(nroReclamo, tipoDeReclamo);
			reclamo.cerrar();
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo cerrar reclamo " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}				
	}
	//Fin: Reclamos
}

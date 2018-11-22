
package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ClienteDAO;
import dao.FacturaDAO;
import dao.ProductoDAO;
import dao.ReclamoDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
import model.Cliente;
import model.EstadoDeReclamo;
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
import observer.Observado;
import view.ClienteView;
import view.FacturaView;
import view.ProductoView;
import view.ReclamoView;
import view.RolView;
import view.UsuarioView;

public class Sistema extends Observado {
		
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
			
			this.notificarObservadores();			
		} catch (ConexionException | AccesoException e) {			
			throw new NegocioException("Error de autenticacion. Verifique Username y Contraseña"); 
		}						
	}
	
	public void desloguearUsuario(){
		this.setUsuarioLogueado(null);		
	}
	
	public Integer crearNuevoUsuario(String username, String password, Integer idRol) throws NegocioException {
		
		try {		
			Rol rol = RolDAO.getInstancia().obtenerRolPorId(idRol);			
			Usuario nuevoUsuario = new Usuario(username, password, rol);
			return nuevoUsuario.guardar();			
		} catch (ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo crear usuario");
		}		
	}
	
	public List<UsuarioView> obtenerTodosLosUsuarios(boolean filtrarDeshabilitados) throws NegocioException{
		try {			
			List<Usuario> usuarios = UsuarioDAO.getInstancia().obtenerTodosLosUsuarios(filtrarDeshabilitados);			
			List<UsuarioView> usuariosViews = new ArrayList<UsuarioView>();
			usuarios.forEach(u -> usuariosViews.add(u.toView()));			
			return usuariosViews;
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo cargar usuario");
		}
	}
	
	public void modificarUsuario(Integer idUsuario, String username, String password, Date fechaBaja, Integer idRol) throws NegocioException {
		try {			
			Rol rol = RolDAO.getInstancia().obtenerRolPorId(idRol);						
			Usuario usuario = new Usuario(idUsuario, username, password, fechaBaja, rol);
			usuario.guardar();
			this.notificarObservadores();
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("No se pudo guardar el usuario");
		}
	}

	/*Fin: Usuario*/
	
	//Roles
	public void crearNuevoRol(String nombre, List<Integer> idsTiposReclamo) {
		
	}
	
	public List<RolView> obtenerRoles() throws NegocioException{
		try {
			List<Rol> roles = (List<Rol>) RolDAO.getInstancia().obtenerTodosLosRoles();
			List<RolView> rolesViews = new ArrayList<RolView>();
			roles.forEach(r -> rolesViews.add(r.toView()));	
			return rolesViews;
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo cargar rol");
		}
	}
	
	//Fin: Roles
	
	//Cliente
	public Integer agregarCliente(String nombre, String cuit, String domicilio, String telefono, String mail) throws NegocioException {
		Cliente nuevoCliente = new Cliente(nombre, cuit, domicilio, telefono, mail);	
		
		Integer idClienteNuevo = null;
		try {
			idClienteNuevo = nuevoCliente.guardar();			
			this.notificarObservadores();
		} catch (ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo crear el cliente");
		}
		
		return idClienteNuevo;
	}
	
	public void modificarCliente(Integer idCliente, String nombre, String cuit, String domicilio, String telefono, String mail, Date fechaBaja) throws NegocioException {
		Cliente cliente = new Cliente(idCliente, nombre, cuit, domicilio, telefono, mail, fechaBaja);
		try {
			cliente.guardar();
			this.notificarObservadores();
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
	
	public ClienteView obtenerCliente(Integer idCliente) throws NegocioException{
		try {
			Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(idCliente);			
			return cliente.toView();
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo cargar cliente");
		}
	}
	
	public List<ClienteView> obtenerTodosLosClientes(boolean filtrarDeshabilitados) throws NegocioException{
		try {			
			List<Cliente> clientes = ClienteDAO.getInstancia().obtenerTodosLosClientes(filtrarDeshabilitados);			
			List<ClienteView> clientesViews = new ArrayList<ClienteView>();			
			clientes.forEach(c -> clientesViews.add(c.toView()));			
			return clientesViews;
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
	
	public void modificarProducto(Integer idProducto, String codigo, String titulo, String descripcion, float precio) throws NegocioException {
		Producto product = new Producto(idProducto, codigo, titulo, descripcion, precio);
		try 
		{
			product.guardar();
		}
			catch (ConexionException | AccesoException e) 
			{
			e.printStackTrace();
			throw new NegocioException("No se pudo actualizar el producto");
			}	
		}	


	public void eliminarProducto(Integer idProducto) throws NegocioException {
		Producto producto;
		try {//encuentro el producto
			producto = ProductoDAO.getInstancia().obtenerProductoPorId(idProducto);
			producto.borrar(); //eliminar		
		} catch (ConexionException | AccesoException e) {
			e.printStackTrace();
			throw new NegocioException("No se pudo eliminar producto");
		}
	}
		
	public Producto obtenerProducto(Integer idProducto) throws NegocioException{		
		try {
			return ProductoDAO.getInstancia().obtenerProductoPorId(idProducto);
		} catch (NegocioException | ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo cargar producto");
		}		
	}
	
	public List<ProductoView> obtenerProductos() throws NegocioException{		
		try {
			List<Producto> productos = ProductoDAO.getInstancia().obtenerTodosLosProductos();
			
			List<ProductoView> productosViews = new ArrayList<ProductoView>();			
			productos.forEach(p -> productosViews.add(p.toView()));
			
			return productosViews; 
		} catch (ConexionException | AccesoException e) {
			throw new NegocioException("No se pudo cargar producto");
		}		
	}
	//Fin: Producto
	
	//Reclamos
	
	
	//Creacion y actualizacion de reclamos
	
	//Distribucion
	public Integer registrarReclamo(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado,Integer idCliente, Integer idProducto, Integer cantidad) throws NegocioException{
		
		try {
			Producto producto = ProductoDAO.getInstancia().obtenerProductoPorId(idProducto);
			Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(idCliente);
			
			Reclamo reclamo = new ReclamoDistribucion(nroReclamo, descripcion, tipoDeReclamo, estado, cliente, producto, cantidad);
			
			nroReclamo = reclamo.guardar();
			
			this.notificarObservadores();
			
			return nroReclamo;
			
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);
		}
	}
	
	//Zona
	public Integer registrarReclamo(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Integer idCliente, String zona) throws NegocioException {
		try {
			
			Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(idCliente);			
			Reclamo reclamo = new ReclamoZona(nroReclamo, descripcion, tipoDeReclamo, estado, cliente, zona);			
			
			nroReclamo = reclamo.guardar();
			
			this.notificarObservadores();
			
			return nroReclamo;
			
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);
		}
	}
	
	//Facturacion
	public Integer registrarReclamo(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, Integer idCliente, List<Integer> nrosFacturas) throws NegocioException{
		try {
			
			Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(idCliente);
			
			List<Factura> facturas = FacturaDAO.getInstancia().obtenerFacturasPorNro(nrosFacturas);
			
			Reclamo reclamoAcrear = new ReclamoFacturacion(descripcion, tipoDeReclamo, cliente, facturas);
			
			nroReclamo = reclamoAcrear.guardar();
			
			this.notificarObservadores();
			
			return nroReclamo;
			
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);			
		}
	}
	
	//Compuesto
	public Integer registrarReclamoCompuesto(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Integer idCliente) throws NegocioException { 
		
		try {
			Cliente cliente = ClienteDAO.getInstancia().obtenerClientePorId(idCliente);
			
			Reclamo reclamoCompuesto = new ReclamoCompuesto(nroReclamo, descripcion, tipoDeReclamo, estado, cliente);
			
			nroReclamo = reclamoCompuesto.guardar();
			
			this.notificarObservadores();
			
			return nroReclamo;
			
		} catch (ConexionException | AccesoException | NegocioException e) {			
			throw new NegocioException("No se pudo generar reclamo " + tipoDeReclamo);
		}		
	}
	
	public void agregarReclamoHoja(Integer nroReclamoHoja, Integer nroReclamoCompuesto) throws NegocioException{
		
		try {
			
			Reclamo hoja = ReclamoDAO.getInstancia().obtenerReclamoPorNroDeReclamo(nroReclamoHoja);			
			Reclamo reclamoCompuesto = ReclamoDAO.getInstancia().obtenerReclamosPorNumeroYtipo(nroReclamoCompuesto, TipoDeReclamo.COMPUESTO);			
			reclamoCompuesto.addHoja(hoja);
			
			reclamoCompuesto.guardar();
			
		} catch (ConexionException | AccesoException | NegocioException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	
	
	//Obtencion de Reclamos
	
	
	/**
	 * Obtiene un reclamo del tipo Zona
	 * 
	 * @param nroReclamo
	 * @return
	 * @throws NegocioException
	 */
	public ReclamoView obtenerReclamoZona(Integer nroReclamo) throws NegocioException {
		
		try {
			ReclamoZona reclamo = ReclamoDAO.getInstancia().obtenerReclamoZona(nroReclamo);			
			return reclamo.toView();
		} catch (AccesoException | ConexionException | NegocioException e) {
			throw new NegocioException ("No se pudo obtener reclamo de Zona Nro.: " +  nroReclamo);
		}		
	}
	
	/**
	 * Obtiene un reclamo del tipo Distribucion
	 * 
	 * @param nroReclamo
	 * @return
	 * @throws NegocioException
	 */
	public ReclamoView obtenerReclamoDistribucion(Integer nroReclamo) throws NegocioException {
		
		try {
			ReclamoDistribucion reclamo = ReclamoDAO.getInstancia().obtenerReclamoDistribucion(nroReclamo);			
			return reclamo.toView();
		} catch (AccesoException | ConexionException | NegocioException e) {
			throw new NegocioException ("No se pudo obtener Reclamo Nro.: " +  nroReclamo);
		}		
	}
	
	/**
	 * Obtiene un reclamo del tipo Facturacion
	 * 
	 * @param nroReclamo
	 * @return
	 * @throws NegocioException
	 */
	public ReclamoView obtenerReclamoFacturacion(Integer nroReclamo) throws NegocioException {
		
		try {
			ReclamoFacturacion reclamo = ReclamoDAO.getInstancia().obtenerReclamoFacturacion(nroReclamo);			
			return reclamo.toView();
		} catch (AccesoException | ConexionException | NegocioException e) {
			throw new NegocioException ("No se pudo obtener Reclamo Nro.: " +  nroReclamo);
		}		
	}
	
	/**
	 * Obtiene reclamos segun el tipo indicado. <br>
	 * Util para los listados donde se debe mostrar el titulo del reclamo sin importar el detalle.
	 * 
	 * @param tipo
	 * @return
	 * @throws NegocioException
	 */
	public List<ReclamoView> obtenerReclamosPorTipo(List<TipoDeReclamo> tipos) throws NegocioException {
		try {
			
			List<Reclamo> reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(tipos);							
			List<ReclamoView> reclamosViews = new ArrayList<ReclamoView>();
			
			reclamos.forEach(r -> reclamosViews.add(r.toView()));
						
			return reclamosViews;
		} catch (ConexionException | AccesoException e) {			
			throw new NegocioException("No se pudieron cargar los reclamos");
		} catch (NegocioException e1){
			throw new NegocioException(e1.getMessage());
		}
	}
	
	public ReclamoView obtenerReclamosPorNumeroYTipo(Integer nroReclamo, TipoDeReclamo tipo) throws NegocioException {
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamosPorNumeroYtipo(nroReclamo, tipo);						
			return reclamo.toView();
		} catch (ConexionException | AccesoException | NegocioException e) {			
			throw new NegocioException("No se pudo cargar reclamos " + tipo.getDenominacion());
		}		
	}
	
	
	//Tratamiento de reclamos 
		
	/**
	 * Inicia el tratamiento del reclamo indicado
	 * 
	 * @param nroReclamo
	 * @throws NegocioException
	 */
	public void comenzarTratamientoReclamo(Integer nroReclamo, TipoDeReclamo tipoDeReclamo) throws NegocioException {
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamosPorNumeroYtipo(nroReclamo, tipoDeReclamo);
			reclamo.pasarEstadoEnTratamiento();
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo pasar reclamo a Tratamiento " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}	
	}
	
	/**
	 * Marca el reclamo como solucionado.
	 * 
	 * @param nroReclamo
	 * @param tipoDeReclamo
	 * @throws NegocioException
	 */
	public void marcarReclamoComoSolucionado(Integer nroReclamo, TipoDeReclamo tipoDeReclamo) throws NegocioException{
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamosPorNumeroYtipo(nroReclamo, tipoDeReclamo);			
			reclamo.pasarEstadoSolucionado();						
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo pasar reclamo a Solucionado " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}
	}
	
	
	/**
	 * Cierra el reclamo indicado
	 * 
	 * @param nroReclamo
	 * @throws NegocioException
	 */
	public void cerrarReclamo(Integer nroReclamo) throws NegocioException {
		
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamoPorNroDeReclamo(nroReclamo);
			reclamo.pasarEstadoCerrado();
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo cerrar reclamo " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}				
	}
	
	
	//TODO: ver si vale la pena
	public void cerrarReclamo(Integer nroReclamo, TipoDeReclamo tipoDeReclamo) throws NegocioException {
		
		try {
			Reclamo reclamo = ReclamoDAO.getInstancia().obtenerReclamosPorNumeroYtipo(nroReclamo, tipoDeReclamo);
			reclamo.pasarEstadoCerrado();
		} catch (ConexionException | AccesoException ae) {
			throw new NegocioException("No se pudo cerrar reclamo " + nroReclamo);
		} catch (NegocioException e){
			throw e;
		}				
	}
	
	//Fin: Reclamos
	
	public List<FacturaView> obenerFacturasPorCliente(Integer idCliente) throws NegocioException {
		
		try {
			List<Factura> facturas = FacturaDAO.getInstancia().obtenerFacturasPorCliente(idCliente);
			List<FacturaView> facturasViews = new ArrayList<>();
			facturas.forEach(f -> facturasViews.add(f.toView()));
			
			return facturasViews;
			
		} catch (ConexionException | AccesoException  ae) {
			throw new NegocioException("No se puede cargar las facturas para el cliente: " + idCliente);
		} catch (NegocioException e){
			throw e;
		}		
	}
}

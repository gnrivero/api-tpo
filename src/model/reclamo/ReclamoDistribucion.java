package model.reclamo;

import dao.DAOhelper;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Cliente;
import model.EstadoDeReclamo;
import model.Producto;
import model.TipoDeReclamo;
import view.ReclamoView;

public class ReclamoDistribucion extends Reclamo {
	
	private Producto producto;
	private Integer cantidad;
	
	public ReclamoDistribucion(){}
	
	public ReclamoDistribucion(String descripcion, TipoDeReclamo tipoDeReclamo, Cliente cliente, Producto producto, Integer cantidad) {
		super(descripcion, tipoDeReclamo, cliente);
		this.producto = producto;
		this.cantidad = cantidad;	
	}
	
	public ReclamoDistribucion(Integer nroReclamo, String descripcion, TipoDeReclamo tipoDeReclamo, EstadoDeReclamo estado, Cliente cliente, Producto producto, Integer cantidad) {
		this(descripcion, tipoDeReclamo, cliente, producto, cantidad);
		this.nroReclamo = nroReclamo;
		this.estado = estado;
	}
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public void addHoja(Reclamo reclamo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeHoja(Reclamo reclamo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getReclamos(Reclamo reclamo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Integer guardar() throws ConexionException, AccesoException {			
		if (this.nroReclamo == null){
			this.nroReclamo = ReclamoDAO.getInstancia().crearReclamoDistribucion(this);
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}	
		
		return this.nroReclamo;
	}
	
	@Override
	public ReclamoView toView() {
		
		ReclamoView view = new ReclamoView();
		
		view.setNroReclamo(this.nroReclamo);
		view.setDescripcion(this.descripcion);
		view.setTipoDeReclamo(this.tipoDeReclamo);
		view.setEstadoDeReclamo(this.estado.getDenominacion());
		view.setFechaDeReclamo(DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fecha));
		
		if(this.fechaCierre != null)
			view.setFechaDeCierre(DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fechaCierre));
		
		if(this.nroReclamoCompuesto != null)
			view.setNroReclamoCompuesto(this.nroReclamoCompuesto);
		
		view.setCliente(this.cliente.toView());		
		view.setProducto(this.producto.toView());
		view.setCantidad(this.cantidad.toString());
		
		return view;	
	}

}
package model.reclamo;

import controller.Sistema;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.AuditoriaReclamo;
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
						
			AuditoriaReclamo auditoria = new AuditoriaReclamo(this, 
						"", 
						this.getEstado().getDenominacion(), 
							Sistema.getInstance().getUsuarioLogueado());
			auditoria.guardar();
						
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}	
		
		return this.nroReclamo;
	}
	
	@Override
	public ReclamoView toView() {
		
		ReclamoView view = super.toView();
		
		if(this.producto != null)
			view.setProducto(this.producto.toView());
		
		if(this.cantidad != null)
			view.setCantidad(this.cantidad.toString());
		
		return view;	
	}

}
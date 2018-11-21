package model.reclamo;

import dao.DAOhelper;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Cliente;
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
	public void guardar() throws ConexionException, AccesoException {			
		if (this.nroReclamo == null){
			ReclamoDAO.getInstancia().crearReclamoDistribucion(this);
		} else {
			ReclamoDAO.getInstancia().actualizarReclamo(this);
		}									
	}
	
	@Override
	public ReclamoView toView() {
		
		ReclamoView view = new ReclamoView();
		view.nroReclamo = this.nroReclamo;
		view.descripcion = this.descripcion;
		view.tipoDeReclamo = this.tipoDeReclamo.getDenominacion();
		view.estadoDeReclamo = this.estado.getDenominacion();
		view.fechaDeReclamo = DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fecha);
		if(this.fechaCierre != null)
			view.fechaDeCierre = DAOhelper.getAnioMesDiaHoraDateFormat().format(this.fechaCierre);
		view.cliente = this.cliente.getNombre();
		
		return view;	
	}

}
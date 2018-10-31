package model.reclamo;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import model.Cliente;
import model.Producto;
import model.TipoDeReclamo;

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

}
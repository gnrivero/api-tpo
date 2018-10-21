package model.reclamo;

import model.Cliente;
import model.Producto;

public class ReclamoDistribucion extends Reclamo {
	
	private Producto producto;
	private Integer cantidad;
		
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
	public void cerrar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar() {
		System.out.println("Guardando reclamo de " + this.tipoDeReclamo);		
	}	

}
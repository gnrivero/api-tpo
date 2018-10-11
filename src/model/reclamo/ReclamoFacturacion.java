package model.reclamo;

import java.util.Date;
import java.util.List;

import model.Producto;

public class ReclamoFacturacion extends Reclamo {
	
	private Date fechaFactura;
	private List<Integer> nroFacturas;
	private Producto producto;
	private Integer cantidad;

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public List<Integer> getNroFacturas() {
		return nroFacturas;
	}

	public void setNroFacturas(List<Integer> nroFacturas) {
		this.nroFacturas = nroFacturas;
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

}

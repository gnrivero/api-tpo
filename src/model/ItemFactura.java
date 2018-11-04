package model;

public class ItemFactura {
	
	private Integer idItemFactura;
	private float montoItem;
	private int cantidad;	
	private Producto producto;
	
	public ItemFactura(Integer idItemFactura, float montoItem, int cantidad, Producto producto){
		this.idItemFactura = idItemFactura;
		this.montoItem = montoItem;
		this.cantidad = cantidad;
		this.producto = producto;
	}
	
	public Integer getIdItemFactura() {
		return idItemFactura;
	}

	public void setIdItemFactura(Integer idItemFactura) {
		this.idItemFactura = idItemFactura;
	}

	public float getMontoItem() {
		return montoItem;
	}

	public void setMontoItem(float montoItem) {
		this.montoItem = montoItem;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public float calcularTotal(){		
		return this.montoItem * cantidad;
	}
	
}
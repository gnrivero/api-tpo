package model;

public class ItemFactura {

	private float montoItem;
	private int cantidad;
	
	
	
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
	
	public float calcularTotal()
	{
		float total=0;
		total = montoItem*cantidad;
		
		return total;
	}
}

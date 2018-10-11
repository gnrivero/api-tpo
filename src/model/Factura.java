package model;


import java.util.Date;
import java.util.List;


public class Factura {
	
	private int nroFactura;
	private Date fechaFactura;
	private float montoTotal;
	private static float IVA;
	private List <ItemFactura> itemFactura;
	

	public float calcularTotal()
	{
		float total=0;
		
		for(ItemFactura item: this.itemFactura){
			total+=item.getMontoItem();
		}
		
		return total;
	}
	public float calcularSubTotal()
	{
		float subTotal=0;
		return subTotal;
	}

	
	
	
	public int getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public float getIVA() {
		return IVA;
	}
	public void setIVA(float iVA) {
		IVA = iVA;
	}

}

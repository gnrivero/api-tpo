package model;


import java.util.Date;
import java.util.List;


public class Factura {
	
	private Integer nroFactura;
	private Date fechaFactura;
	private static float IVA = 21;
	private List<ItemFactura> itemFactura;
	
	
	public Factura(List<ItemFactura> itemFactura){
		this.fechaFactura = new Date();
		this.itemFactura = itemFactura;
	}
	
	public Factura(Integer nroFactura, Date fechaFactura, List<ItemFactura> itemFactura){
		this.nroFactura = nroFactura;
		this.fechaFactura = fechaFactura;
		this.itemFactura = itemFactura;
	}
	
	
	public Integer getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public List<ItemFactura> getItemFactura() {
		return itemFactura;
	}
	public void setItemFactura(List<ItemFactura> itemFactura) {
		this.itemFactura = itemFactura;
	}

	public float getIVA() {
		return IVA;
	}	
	
	public float calcularTotal(){
		return this.calcularSubTotal() * ((IVA/100) + 1);
	}
	
	public float calcularSubTotal(){
		float subTotal=0;
		for(ItemFactura item : this.itemFactura){
			subTotal+= item.calcularTotal();
		}
		return subTotal;
	}
	
	public void guardar(){
		if (this.nroFactura==null) {
			
		} else {
			
		}		
	}

}

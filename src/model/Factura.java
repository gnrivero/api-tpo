package model;


import java.util.Date;
import java.util.List;

import dao.DAOhelper;
import view.FacturaView;


public class Factura {
	
	private Integer nroFactura;
	private Date fechaFactura;
	private Cliente cliente;
	private static float IVA = 21;
	private List<ItemFactura> itemFactura;
	
	public Factura(Integer nroFactura, Date fechaFactura, Cliente cliente){
		this.nroFactura = nroFactura;
		this.fechaFactura = fechaFactura;
		this.cliente = cliente;
	}
	
	public Factura(Integer nroFactura, Date fechaFactura, Cliente cliente, List<ItemFactura> itemFactura){
		this.nroFactura = nroFactura;
		this.fechaFactura = fechaFactura;
		this.cliente = cliente;
		this.itemFactura = itemFactura;
	}
	
	public Integer getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public FacturaView toView(){
		
		FacturaView view = new FacturaView();
		view.setNroFactura(this.nroFactura);
		view.setCliente(this.cliente.getNombre());
		view.setFecha(DAOhelper.getDiaMesAnioHoraDateFormat().format(this.fechaFactura));
		
		return view;
	}

}

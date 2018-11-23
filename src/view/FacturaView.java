package view;

public class FacturaView {
	
	private Integer nroFactura;
	private String cliente;
	private String fecha;
	
	public FacturaView(){}

	public Integer getNroFactura() {
		return nroFactura;
	}

	public void setNroFactura(Integer nroFactura) {
		this.nroFactura = nroFactura;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "(Nro: " +  this.nroFactura + ") Cliente : " + this.cliente;
	}
	
}

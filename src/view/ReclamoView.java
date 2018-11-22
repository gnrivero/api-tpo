package view;

import java.util.List;

import model.EstadoDeReclamo;
import model.TipoDeReclamo;

public class ReclamoView {
	
	private Integer nroReclamo;
	private String descripcion;
	private TipoDeReclamo tipoDeReclamo;
	private EstadoDeReclamo estadoDeReclamo;
	private String fechaDeReclamo;
	private String fechaDeCierre;
	private ClienteView cliente;
	private Integer nroReclamoCompuesto;
	
	private List<ReclamoView> reclamosHijos;//TODO: ver si sirve
	
	//Zona
	private String zona;
	
	//Distribucion
	private ProductoView producto;
	private String cantidad;
	
	public ReclamoView(){ }
	
	public Integer getNroReclamo() {
		return nroReclamo;
	}

	public void setNroReclamo(Integer nroReclamo) {
		this.nroReclamo = nroReclamo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoDeReclamo getTipoDeReclamo() {
		return tipoDeReclamo;
	}

	public void setTipoDeReclamo(TipoDeReclamo tipoDeReclamo) {
		this.tipoDeReclamo = tipoDeReclamo;
	}

	public EstadoDeReclamo getEstadoDeReclamo() {
		return estadoDeReclamo;
	}

	public void setEstadoDeReclamo(EstadoDeReclamo estadoDeReclamo) {
		this.estadoDeReclamo = estadoDeReclamo;
	}

	public String getFechaDeReclamo() {
		return fechaDeReclamo;
	}

	public void setFechaDeReclamo(String fechaDeReclamo) {
		this.fechaDeReclamo = fechaDeReclamo;
	}

	public String getFechaDeCierre() {
		return fechaDeCierre;
	}

	public void setFechaDeCierre(String fechaDeCierre) {
		this.fechaDeCierre = fechaDeCierre;
	}

	public ClienteView getCliente() {
		return cliente;
	}

	public void setCliente(ClienteView cliente) {
		this.cliente = cliente;
	}

	public Integer getNroReclamoCompuesto() {
		return nroReclamoCompuesto;
	}

	public void setNroReclamoCompuesto(Integer nroReclamoCompuesto) {
		this.nroReclamoCompuesto = nroReclamoCompuesto;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public ProductoView getProducto() {
		return producto;
	}

	public void setProducto(ProductoView producto) {
		this.producto = producto;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Nro: " + this.nroReclamo +
				" - " + this.descripcion +
				" - " + this.tipoDeReclamo +
				" - " +  this.fechaDeReclamo;				
	}
		
}
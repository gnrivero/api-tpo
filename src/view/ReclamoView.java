package view;

import java.util.ArrayList;
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
	
	//Compuesto
	private List<ReclamoView> reclamosHijos;
	
	//Zona
	private String zona;
	
	//Distribucion
	private ProductoView producto;
	private String cantidad;
	
	//Facturacion
	private List<FacturaView> facturasReclamadas;
	
	public ReclamoView(){ 
		facturasReclamadas = new ArrayList<FacturaView>();
		reclamosHijos = new ArrayList<ReclamoView>();
	}
	
	@Override
	public String toString() {
		return "Nro: " + this.nroReclamo + " (" + this.tipoDeReclamo + ") " + " - " + this.descripcion + " - " + this.fechaDeReclamo;				
	}
	
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

	public List<FacturaView> getFacturasReclamadas() {
		return facturasReclamadas;
	}

	public void setFacturasReclamadas(List<FacturaView> facturasReclamadas) {
		this.facturasReclamadas = facturasReclamadas;
	}

	public List<ReclamoView> getReclamosHijos() {
		return reclamosHijos;
	}

	public void setReclamosHijos(List<ReclamoView> reclamosHijos) {
		this.reclamosHijos = reclamosHijos;
	}
	
}
package model;

public enum TipoDeReclamo {
	
	CANTIDADES(1, "Reclamo de Cantidad"), 
	FALTANTES(2, "Reclamo de Faltantes"), 
	PRODUCTO(3, "Reclamo de Producto"), 
	ZONA(4, "Reclamo de Zona"), 
	FACTURACION(5, "Reclamo de Facturacion"), 
	COMPUESTO(6, "Reclamo Compuesto");
	
	private Integer id;
	private String denominacion;
	
	TipoDeReclamo(Integer id, String denominacion){
		this.id = id;
		this.denominacion = denominacion;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	public String getDenominacion(){
		return this.denominacion;
	}
}
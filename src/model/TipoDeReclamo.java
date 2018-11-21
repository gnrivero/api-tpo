package model;

public enum TipoDeReclamo {
	
	ZONA(1, "Reclamo de Zona"),
	CANTIDADES(2, "Reclamo de Cantidad"), 
	FALTANTES(3, "Reclamo de Faltantes"), 
	PRODUCTO(4, "Reclamo de Producto"), 
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
	
	@Override
	public String toString() {		
		return this.denominacion;
	}
}
package model;

public enum TipoDeReclamo {
	
	ZONA(1, "Zona"),
	CANTIDADES(2, "Cantidad"), 
	FALTANTES(3, "Faltantes"), 
	PRODUCTO(4, "Producto"), 
	FACTURACION(5, "Facturacion"), 
	COMPUESTO(6, "Compuesto");
	
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
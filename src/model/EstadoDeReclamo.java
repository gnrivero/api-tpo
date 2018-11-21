package model;

public enum EstadoDeReclamo {
	
	INGRESADO(1,"Ingresado"), 
	EN_TRATAMIENTO(2,"En Tratamiento"), 
	CERRADO(3,"Cerrado"), 
	SOLUCIONADO(4, "Solucionado");
	
	private Integer id;
	private String denominacion;
	
	EstadoDeReclamo(Integer id, String denominacion){
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
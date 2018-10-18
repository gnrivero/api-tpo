package model;
import java.util.List;

public class Rol {

	private int idRol;
	private String denominacion;
	private List <Integer> tiposDeReclamo;
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public List<Integer> getTiposDeReclamo() {
		return tiposDeReclamo;
	}
	public void setTiposDeReclamo(List<Integer> tiposDeReclamo) {
		this.tiposDeReclamo = tiposDeReclamo;
	}

	public boolean soy(int idRol)
	{
					
			return true;
	}


} 

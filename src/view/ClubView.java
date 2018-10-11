package view;

public class ClubView {

	private int idClub;
	private String nombre;
	private int zona;
	
	public ClubView(int idClub, String nombre, int zona) {
		this.idClub = idClub;
		this.nombre = nombre;
		this.zona = zona;
	}
	
	public int getIdClub() {
		return idClub;
	}
	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getZona() {
		return zona;
	}
	public void setZona(int zona) {
		this.zona = zona;
	}
}

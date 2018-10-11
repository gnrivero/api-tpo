package view;

public class JugadorView {
	
	private String tipo;
	private Integer numero;
	private String nombre;
	private int categoria;
	private String club;
	
	public JugadorView(String tipo, Integer numero, String nombre, int categoria, String club) {
		this.tipo = tipo;
		this.numero = numero;
		this.nombre = nombre;
		this.categoria = categoria;
		this.club = club;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public String getClub(){
		return club;
	}
	
	public String toString(){
		return tipo + " " + numero + " - " + nombre + " " + club;
	}
}

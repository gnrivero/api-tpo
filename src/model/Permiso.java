package model;

public class Permiso {
	
	private Modulo modulo;
	private Integer valor;
	
	public static final int LECTURA = 4;
	public static final int ESCRITURA = 2;
	
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
}
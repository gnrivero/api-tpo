package model;

/**
 * @author Maria
 *
 */
public class Producto {

	private int idProducto;
	private int codigo;
	private String titulo;
	private String descripcion;
	private float precio;
	
	public Producto(String titulo, String descripcion, float precio){
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public void addProducto(Producto prod)
	{
		
	}
	
	public void removeProducto(Producto prod)
	{
		
	}
	
	public void editProducto(Producto prod)
	{
		
	}
	
	
	
	
	
}

package model;

import dao.ProductoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ProductoView;

/**
 * @author Maria
 *
 */
public class Producto {

	private Integer idProducto;
	private String codigo;
	private String titulo;
	private String descripcion;
	private float precio;
	
	public Producto(Integer idProducto,  String codigo , String titulo, String descripcion, float precio){
		this(codigo, titulo, descripcion, precio);		
		this.idProducto = idProducto;		
	}
	
	public Producto(String codigo , String titulo, String descripcion, float precio){
		this.codigo = codigo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
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
	
	public void guardar() throws ConexionException, AccesoException {
		ProductoDAO.getInstancia().crearProducto(this);
	}
	
	public void borrar() throws ConexionException, AccesoException {
		ProductoDAO.getInstancia().borrar(idProducto);
	}
	
	public ProductoView toView(){
		return new ProductoView(this.idProducto, this.codigo, this.titulo, this.descripcion, this.precio);
	}
	
}

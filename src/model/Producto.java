package model;

import dao.ProductoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.NegocioException;
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
	
	public void validar() throws NegocioException {
		if (this.codigo.isEmpty()){
			throw new NegocioException("El Codigo de producto no puede estar vacio");
		}		
		if (this.titulo.isEmpty()){
			throw new NegocioException("El Titulo de producto no puede estar vacio");
		}		
		if (this.descripcion.isEmpty()){
			throw new NegocioException("La descripcion de producto no puede estar vacia");
		}		
		if(this.precio <= 0F){
			throw new NegocioException("El precio del producto no puede ser 0 o inferior");
		}
	}
	
	public Integer guardar() throws ConexionException, AccesoException {
		if(this.getIdProducto() == null){
			this.idProducto = ProductoDAO.getInstancia().crearProducto(this);
		}else{
			ProductoDAO.getInstancia().actualizarProducto(this);
		}
		
		return this.idProducto;
	}
	
	public void borrar() throws ConexionException, AccesoException {
		ProductoDAO.getInstancia().borrar(idProducto);
	}
	
	public ProductoView toView(){
		return new ProductoView(this.idProducto, this.codigo, this.titulo, this.descripcion, this.precio);
	}
	
}

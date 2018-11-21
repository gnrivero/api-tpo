package view;

public class ProductoView {

	public Integer idProducto;
	public String codigo;
	public String titulo;
	public String descripcion;
	public float precio;
	
	
	public ProductoView(Integer idProducto, String codigo, String titulo, String descripcion, float precio){
		this.idProducto=idProducto;
		this.codigo = codigo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;

	}
	
	public ProductoView (String titulo){
		this.titulo = titulo;
	}

	
	@Override
	public String toString(){
		if(this.idProducto == null)
			return this.titulo;
		return "ID: " + this.idProducto + " - "+ this.titulo;
	}

}

package view;

public class ProductoView {

	private Integer idProducto;
	private String codigo;
	private String titulo;
	private String descripcion;
	private float precio;
	

	public ProductoView(){ }
	
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

	@Override
	public String toString(){
		if(this.idProducto == null)
			return this.titulo;
		return "ID: " + this.idProducto + " - "+ this.titulo;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
	        return true;
	    }
	    if (obj instanceof ProductoView) {
	    	ProductoView obj2 = (ProductoView) obj;    	    	
	        return this.getIdProducto() == obj2.getIdProducto();
	    }
	    return false;
	}
	
}
package cosas;

public class Pedido {
	private int idProducto;
	private String descripcion;
	private int cantidadProducto;
	
	public Pedido(int idProducto, int cantidadProducto) {
		this.idProducto = idProducto;
		this.cantidadProducto = cantidadProducto;
	}

	// alt + shift + S . Genra COnstructores y Gets and Sets
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	
	
	
}

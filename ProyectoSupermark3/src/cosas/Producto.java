package cosas;

public class Producto {
	private int id;
	private String descripcion;
	private double precio;
	private int stock;
	
	public Producto() {
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Producto(int id,String descripcion,double precio, int stock) {
		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	public String toString () {
		return "ID: "+id+" Precio: "+precio + "Stock: " + stock;
	}
	public double getPrecio() {
		return precio;
	}
	
	public int getId() {
		return id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getStock() {
		return stock;
	}
	
	
}

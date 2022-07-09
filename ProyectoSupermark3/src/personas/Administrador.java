package personas;

import java.sql.SQLException;
import java.util.Scanner;

import baseDeDatos.DataBase;
import cosas.Producto;

public class Administrador extends Usuario{
	private int id;
	private String apellido;
    private String nombre;
    private Producto newproducto;
    public Administrador() {
    	
    }
    public Administrador(int id) {
    	this.id = id;
    }
    public Administrador(int id,String apellido, String nombre) {
    	this.apellido = apellido;
    	this.nombre = nombre;
    	this.id = id;
    }
    public void cargarProducto() throws SQLException {
    	System.out.println("----------Cargar nuevo producto----------");
    	Scanner sc = new Scanner(System.in);
    	System.out.println("1 - Nombre del nuevo producto");
		String newDescripcion = sc.nextLine();
		System.out.println("2 - Introduzca precio");
		double newPrecio = sc.nextDouble();
		System.out.println("3 - Introduzca cantidad de stock");
		int newStock = sc.nextInt();
		newproducto = new Producto(0,newDescripcion,newPrecio,newStock);
		DataBase.addProducto(newproducto);
		System.out.println ("¡Producto ingresado correctamente!");
    }
    public void modificarProducto() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("----------Modificación del producto----------");
		System.out.println("1- Seleccione ID"); 
		int productoId = sc.nextInt();
		System.out.println("2- Introduzca el precio del Producto a modificado");
		double nuevoPrecio =sc.nextDouble();
		System.out.println("3- Introduzca stock del Producto a modificar");
		int nuevoStock = sc.nextInt();
		Producto modproducto= new Producto(productoId,null,nuevoPrecio,nuevoStock);
		DataBase.modificarProducto(modproducto);
		System.out.println("Modificacion realizada");
    }
    public void eliminarProducto() throws SQLException {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("----------Eliminación del producto----------"); 
		System.out.println("1- Seleccione ID");
		int productoId = sc.nextInt();
		Producto eliminarProducto = new Producto(productoId,null,0,0);
		DataBase.eliminarProducto(eliminarProducto);
		System.out.println("Eliminación Exitosa");
    }
    public void listaUsuariosQueCompraron() throws SQLException {
    	System.out.println("Lista de usuarios y compras");
		DataBase.compraCliente();
    }
    public void listaComprasDeUnCliente() throws SQLException {
    	String idTemporal;
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Lista de compra de los usuarios");
		DataBase.compraCliente();
		System.out.println("  - Introduzca el ID del usuario: ");
		idTemporal = sc.nextLine();
		DataBase.listaComprasUnCliente(idTemporal);
    }
}

package personas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import baseDeDatos.DataBase;
import cosas.Pedido;

public class Cliente extends Usuario{
	private int id;
    private String nombre;
    private String dni;
    private String telefono;
	private String apellido;
    private String domicilio;
    private int edad;
    private ArrayList<Pedido> carrito;
    private String opcionCliente;
    private int cantidadTotal;
	public Cliente() {
	}
	public Cliente(int id) {
		this.id = id;
		carrito = new ArrayList<Pedido>();
		cantidadTotal = 0;
	}
	public Cliente(int id, String nombre, String dni, String telefono, String apellido, String domicilio, int edad) {
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.edad = edad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidadTotal() {
		return cantidadTotal;
	}
	public void agregarACantidadTotal() {
		this.cantidadTotal += cantidadCarrito(carrito);;
	}
	public void verListadoProductos() throws SQLException {
		DataBase.verProductos();
	}
	public void agregarAlCarrito(String opcion) throws SQLException {
		Pedido pedido = null;
		int opcionInt = 0;	
		String cantidad = "";
		int posPedido = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese la cantidad: ");
		cantidad = sc.nextLine();
		opcionInt = Integer.parseInt(opcion);
		pedido = new Pedido(opcionInt,Integer.parseInt(cantidad));
		pedido.setDescripcion(DataBase.getNombre(opcionInt));
		if(cantidadCarrito(carrito)+pedido.getCantidadProducto()<=30) {
			if(DataBase.hayStock(opcionInt,pedido.getCantidadProducto())) {
				posPedido = estaEnPedido(carrito,pedido);
				if(estaEnPedido(carrito,pedido) >= 0) {
					pedido.setCantidadProducto(carrito.get(posPedido).getCantidadProducto()+pedido.getCantidadProducto());
					carrito.set(posPedido, pedido);
				}else {
					carrito.add(pedido);
				}
				System.out.println("Se añadió al carrito");
			}else {
				System.out.println("No hay stock");
			}
		}else {
			System.out.println("Excedió los 30 productos");
		}
	}
	public void verCarritoCompra() {
		System.out.println("----------------Productos----------------");
		for(Pedido verProducto : carrito) {
			System.out.println("Id: "+verProducto.getIdProducto()+ "  Producto: " + verProducto.getDescripcion()  +" Cantidad: "+verProducto.getCantidadProducto());
		}
	}
	public void hacerPedidoProductos() throws SQLException{
		DataBase.hacerPedidoProductos(carrito, this.id);
	}
	public void vaciarCarrito() {
		carrito.clear();
	}
	private int cantidadCarrito(ArrayList<Pedido> carrito) {
		if(!carrito.isEmpty()) {
			int cantidadCarrito = 0;
			for(Pedido pedidoActualizar : carrito) {
				cantidadCarrito+=pedidoActualizar.getCantidadProducto();
			}
			return cantidadCarrito;
		}else {
			return 0;
		}
	}
	private int estaEnPedido(ArrayList<Pedido> carrito, Pedido pedido) {
		int estaEnPedido = -1;
		for(int i=0 ; i<carrito.size() ; i++) {
			if(carrito.get(i).getIdProducto() == pedido.getIdProducto()) {
				estaEnPedido = i;
				System.out.println("Si esta en el carrito");
			}
		}
		return estaEnPedido;
	}
}

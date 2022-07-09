package menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import baseDeDatos.DataBase;
import cosas.Producto;
import personas.Administrador;
import personas.Cliente;
//Menu del cliente y menu del administrador, donde cada uno muestra que puede hacer de acuerdo al rol
public class menuOpciones {
	public static void opcionesDelCliente(Cliente cliente) throws SQLException {
		String opcion = "n";
		Scanner sc = new Scanner(System.in);
		do {
			if(!opcion.equals("3")) {
				System.out.println("-----------Bienvenido Cliente-----------");
				System.out.println("1 -> Seleccionar productos");
				System.out.println("2 -> Ver listado de productos seleccionados");
				System.out.println("3 -> Autorizar la compra de los productos seleccionados");
				System.out.println("Cualquier numero/letra -> Salir");
				System.out.println("Seleccione una opción:");
				opcion = sc.nextLine();
			}
			switch (opcion) {
			case "1":
				cliente.verListadoProductos();
				System.out.println("----------Seleccionar Productos----------");
				do {
					System.out.println("  - Seleccione un id de producto");
					System.out.println("S - Volver para atrás");
					System.out.println("T - Terminar de seleccionar");
					opcion = sc.nextLine();
					if(!opcion.equals("S") && !opcion.equals("s") && !opcion.equals("T") && !opcion.equals("t")) {
						//
						cliente.agregarAlCarrito(opcion);
					}
				}while(!opcion.equals("S") && !opcion.equals("s") && !opcion.equals("T") && !opcion.equals("t"));
				if(opcion.equals("T") || opcion.equals("t")) {
					opcion = "3";
				}else {
					opcion = "g";
				}
				break;
			case "2":
				do {
					//
					cliente.verCarritoCompra();
					System.out.println("S - Volver para atrás");
					opcion = sc.nextLine();
				}while(!opcion.equals("S") && !opcion.equals("s"));
				opcion = "g";
				break;
			case "3":
				System.out.println("---------------Autorizar----------------");
				System.out.println("1 - Si");
				System.out.println("2 - No (Vaciar carrito)");
				System.out.println("S - Volver para atrás");
				opcion = sc.nextLine();
				if(opcion.equals("1")) {
					//
					cliente.hacerPedidoProductos();
					System.out.println("Compra realizada");
					cliente.agregarACantidadTotal();
					cliente.vaciarCarrito();
					System.out.println("Se vació el carrito");
				}else if(opcion.equals("2")) {
					cliente.vaciarCarrito();
					System.out.println("Se vació el carrito");
				}
				opcion = "g";
				break;
			default:
				opcion = "0";
			}
		}while(!opcion.equals("0"));
	}
	public static void opcionesDelAdministrador(Administrador administrador) throws SQLException {
		String opcion = "n";
		Producto newproducto=null;
		String idTemporal = "";
		do {
			System.out.println("-----------Bienvenido Administrador-----------");
			System.out.println("1 - Cargar Nuevos Productos");
			System.out.println("2 - Modificar Producto");
			System.out.println("3 - Eliminar Producto");
			System.out.println("4 - Ver clientes que realizaron una compra");
			System.out.println("5 - Ver lista de compras de un cliente");
			System.out.println("0 - Salir");
			System.out.println("Seleccione una opcion:");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextLine();
			switch (opcion) {
			case "1":
				do {
					//
					administrador.cargarProducto();
					System.out.println("Cargar otro producto?");
					System.out.println("1 - Si");
					System.out.println("2 - No");
					sc.nextLine();
					opcion = sc.nextLine();
					}while(!opcion.equals("S") && !opcion.equals("s") && !opcion.equals("2"));
				opcion = "g";
				break;
			case "2":
				DataBase.verProductos();
				do {
					administrador.modificarProducto();
					System.out.println("Modificar otro producto?");
					System.out.println("1 - Si");
					System.out.println("2 - No");
					opcion = sc.nextLine();
				}while(!opcion.equals("S") && !opcion.equals("s") && !opcion.equals("2"));
				opcion = "g";
				break;
			case "3":
				DataBase.verProductos();
				do {
					//
					administrador.eliminarProducto();
					System.out.println("Desea eliminar otro producto?");
					System.out.println("1 - Si");
					System.out.println("2 - No");
					sc.nextLine();
					opcion = sc.nextLine();
				}while(!opcion.equals("S") && !opcion.equals("s") && !opcion.equals("2"));
				opcion = "g";
				break;
			case "4":
				do{
					//
					administrador.listaUsuariosQueCompraron();
					System.out.println("S - Volver para atras");
				opcion = sc.nextLine();
				}while(!opcion.equals("S") && !opcion.equals("s"));
				opcion = "g";
				break;
			case "5":
				do {
					//
					administrador.listaComprasDeUnCliente();
					System.out.println("S - Salir");
					opcion = sc.nextLine();
				}while(!opcion.equals("S") && !opcion.equals("s"));
				opcion = "g";
				break;
			default:
				opcion = "0";
			}
		}while(!opcion.equals("0"));
	}
}
	
	

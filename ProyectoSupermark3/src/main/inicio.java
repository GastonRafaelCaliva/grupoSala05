package main;

import java.util.Scanner;

import baseDeDatos.DataBase;
import menu.menuOpciones;
import personas.Administrador;
import personas.Cliente;
import personas.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//Esta clase es el main, de aquí parte el programa y se realiza un inicio de sesion o registro, sería la interfaz gráfica por consola que da la bienvenida
//a un potencial cliente nuevo o bien para el inicio de sesion tanto de un cliente como de un administrador
public class inicio {
	public static void main(String[] args) throws SQLException {
		DataBase.inciarDB();
		String opcion = "0";
		boolean comprobar = false;
		Usuario newUser = null;
		String registroUsuario;
		Cliente cliente = null;
		Administrador administrador = null;
		do {
			System.out.println("---------------Bienvenido--------------");
			System.out.println("1 - Inicio de sesión");
			System.out.println("2 - Registrarse"); //
			System.out.println("0 - Salir");
			System.out.println("Seleccione una opción:");
			Scanner sc = new Scanner(System.in);
			opcion = sc.nextLine();
			switch (opcion) {
			case "1":
				 // Función de inicio de sesión
				System.out.println("------------Inicio de sesión------------");
				System.out.print ("Introduzca el nombre de usuario:");
				String nombreUsuario = sc.nextLine();
				System.out.print ("Introduzca la contraseña:");
				String contraseñaUsuario = sc.nextLine();
				newUser = new Usuario(nombreUsuario,contraseñaUsuario);
				comprobar = DataBase.estaRegistrado(newUser);
				System.out.println("Esta registrado"+comprobar);
				if (comprobar) {
					 System.out.println ("¡Inicio de sesión exitoso!");
					 if (DataBase.esAdministrador(newUser)) {
						 administrador = new Administrador(DataBase.getIdUsuario(newUser));
						 menuOpciones.opcionesDelAdministrador(administrador);
					 }
					 else  {
						 cliente = new Cliente(DataBase.getIdCliente(newUser));
					     menuOpciones.opcionesDelCliente(cliente);
					 }
				} else {
					 System.out.println ("¡El nombre de usuario o la contraseña son incorrectos! ¡Vuelva a iniciar sesión!");
				}
				break;
			case "2":
				 // Función de registro
				System.out.println("----------------Registro----------------");
				System.out.print ("Introduzca el nombre de usuario:");
				String newUsername = sc.nextLine();
				System.out.print ("Introduzca la contraseña:");
				String newPassword = sc.nextLine();
				newUser = new Usuario(newUsername,newPassword);
				comprobar = DataBase.estaRegistrado(newUser);
				if(!comprobar) {
					registroUsuario = DataBase.registroNuevo(newUser);
					System.out.println ("¡Registrado correctamente!");
					DataBase.ingresarDatos(registroUsuario,newUser);
				}else {
					System.out.println("Ya esta registrado");
				}
				registroUsuario = "";
				break;
			default:
				System.out.println("----------------Saliendo----------------");
				opcion = "0";
				break;
			}
		}while(!opcion.equals("0"));
	}

}

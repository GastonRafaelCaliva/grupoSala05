package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
//Clase que realiza la interrogación a la base de datos, inserción y modificación en tablas

import cosas.Pedido;
import cosas.Producto;
import personas.Cliente;
import personas.Usuario;

public class DataBase {
	private static Connection conn = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt= null;
	//al iniciarDB() se conecta a la base de datos ubicada en usuarioDBPassword.DB_URL, y según el nombre y contraseña se realizará dicha conexión
	public static void inciarDB() throws SQLException {
		DataBase.conn = DriverManager.getConnection(usuarioDBPassword.DB_URL, usuarioDBPassword.USER, usuarioDBPassword.PASS);
		DataBase.stmt = conn.createStatement();
	}
	// => Registrarse
	//realizamos un registro temporal del usuario
	public static String registroNuevo(Usuario usuario) throws SQLException {
		String sql;
		String userName = "\'"+usuario.getUsername()+"\'";
		String password = "\'"+usuario.getPassword()+"\'";
		sql = "insert into t_usuario values(null,"+userName+","+password+",'cliente');";
		return sql;
	}
	// => Registrarse
	//si el usuario registrado temporalmente completa todos sus datos, se vuelve un usuario de tipo cliente
	//caso contrario, no se realizará su registro
	public static void ingresarDatos(String registroSql, Usuario usuario) throws SQLException {
		DataBase.pstmt = conn.prepareStatement(registroSql);
		DataBase.pstmt.executeUpdate();
		DataBase.pstmt = null;
		String sql = "";
		Cliente newUser = new Cliente();
		Scanner sc = new Scanner(System.in);
		System.out.println("-Ingrese sus datos para ser un cliente-");
		System.out.println("1- Nombre"); 
		newUser.setNombre(sc.nextLine());
		System.out.println("2- Apellido");
		newUser.setApellido(sc.nextLine());
		System.out.println("3- Dni");
		newUser.setDni(sc.nextLine());
		System.out.println("3- Edad");
		newUser.setEdad(sc.nextInt());
		sc.nextLine();
		System.out.println("3- Telefono");
		newUser.setTelefono(sc.nextLine());
		System.out.println("3- Domicilio");
		newUser.setDomicilio(sc.nextLine());
		sql = "insert into t_cliente values(null,\'"+newUser.getNombre()+"\',\'"+newUser.getApellido()+"\',"
		+"\'"+newUser.getDni()+"\',"+newUser.getEdad()+",\'"+newUser.getTelefono()+"\',\'"+newUser.getDomicilio()+"\',"+DataBase.getIdUsuario(usuario)+");";
		DataBase.pstmt = conn.prepareStatement(sql);
		DataBase.pstmt.executeUpdate();
		DataBase.pstmt = null;
		System.out.println("Perfecto, ahora inicie sesión");
	}
	// => Iniciar Sesion // => Registrarse 
	//método que comprueba si un Usuario está registrado
	public static boolean estaRegistrado(Usuario usuario) throws SQLException {
		String sql = "select nombre, contra from t_usuario";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		String userName = null;
		String password = null;
		boolean bandera = false;
		while(rs.next()) {
			 userName = rs.getString("nombre");
			 password = rs.getString("contra");
			 if(userName.equals(usuario.getUsername())) {
				 bandera = true;
			 }
		}
		rs.close();
		return bandera;
	}
	// => Iniciar Sesion
	//método que comprueba si un Usuario es Administrador
	public static boolean esAdministrador(Usuario usuario) throws SQLException {
		String sql = "select nombre from t_usuario where rol = 'admin';";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		boolean esAdmin = false;
		while(rs.next()) {
			 if(usuario.getUsername().equals(rs.getString("nombre"))) {
				 esAdmin = true;
			 }
		}
		rs.close();
		return esAdmin;
	}
	//método que retorna el nombre/descripcion de un producto mediante un id
	public static String getNombre(int id) throws SQLException {
		String sql = "select descripcion from t_producto where id_producto = "+id+";";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		rs.next();
		String nombre = rs.getString("descripcion");
		rs.close();
		return nombre;
	}
	//método que comprueba si hay stock de un producto
	//si hay 3 stock y pido 4, retorna false, si hay 3 stock y pido 3, retorna true
	//cant <= stock -> true
	public static boolean hayStock(int id, int cant) throws SQLException {
		String sql = "select stock from t_producto where id_producto ="+id+";";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		rs.next();
		return rs.getInt("stock")>=cant;
	}
	//Retorna el id de un usuario mediante su nombre
	public static int getIdUsuario(Usuario usuario) throws SQLException {
		String sql = "select id_usuario from t_usuario where nombre = '"+usuario.getUsername()+"';";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		rs.next();
		int idUsuario = rs.getInt("id_usuario");
		rs.close();
		return idUsuario;
	}
	public static int getIdCliente(Usuario usuario) throws SQLException {
		String sql = "select id_cliente,id_usuario from t_cliente, t_usuario where fk_id_usuario = '"+DataBase.getIdUsuario(usuario)+"';";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		rs.next();
		int idUsuario = rs.getInt("id_cliente");
		rs.close();
		return idUsuario;
	}
	// => Seleccionar productos
	//muestra todos los productos de la tabla producto
	public static void verProductos() throws SQLException {
		String sql = "select id_producto, descripcion, precio, stock from t_producto;";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println("ID: "+rs.getInt("id_producto")+" Precio: "+rs.getDouble("precio")+" (Stock: "+rs.getInt("stock")+") Producto: "+rs.getString("descripcion"));
		}
		rs.close();
	}
	// => Autorizar la compra de los productos seleccionados
	//Modifica la tabla producto, reduciendo el stock de dicho producto seleccionado
	private static void hacerPedidoProducto(Pedido pedido,int cant) throws SQLException {
		String sql = "update t_producto set stock = "+cant+" where id_producto = "+pedido.getIdProducto()+";";
		DataBase.pstmt = conn.prepareStatement(sql);
		DataBase.pstmt.executeUpdate();
		DataBase.pstmt = null;
	}
	// => Autorizar la compra de los productos seleccionados
	//Modifica la tabla producto, reduciendo el stock de todos los productos dependiendo de lo que tenga el cliente en el carrito
	//realiza el llamado a hacerPedidoProducto() las veces que sea necesario
	//Además realiza la insersión en la tabla compras
	public static void hacerPedidoProductos(ArrayList<Pedido> carrito,int id) throws SQLException {
		String sql = "select id_producto, stock from t_producto;";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		int cant = 0;
		while(rs.next()) {
			for(Pedido pedidoActualizar : carrito) {
				if(pedidoActualizar.getIdProducto() == rs.getInt("id_producto")) {
					cant = rs.getInt("stock") - pedidoActualizar.getCantidadProducto();
					hacerPedidoProducto(pedidoActualizar,cant);
					sql = "insert into t_compras values("+id+","+pedidoActualizar.getIdProducto()+","+pedidoActualizar.getCantidadProducto()+");";
					DataBase.pstmt = conn.prepareStatement(sql);
					DataBase.pstmt.executeUpdate();
					DataBase.pstmt = null;
				}
			}
		}
		rs.close();
	}
	
	//administrador
	// => Cargar productos a la aplicación
	public static void addProducto(Producto producto) throws SQLException{
		String sql;
		System.out.println(producto.toString());
		int productoId = producto.getId();
		String productoDescripcion = producto.getDescripcion();
		double productoPrecio = producto.getPrecio();
		int productoStock =producto.getStock();
		sql = "insert into t_producto values (null,\'"+productoDescripcion+"\',"+productoPrecio+","+productoStock+");";
		DataBase.pstmt = conn.prepareStatement(sql);
		DataBase.pstmt.executeUpdate();
		DataBase.pstmt = null;
	}
	
	// => Modificar los datos de los productos cargados
	public static void modificarProducto(Producto producto)throws SQLException{
		String sql;
		System.out.println(producto.toString());
		int productoId = producto.getId();
		double productoPrecio = producto.getPrecio();
		int productoStock = producto.getStock();
		sql="update t_producto set precio="+productoPrecio+","+"stock="+productoStock+" where id_producto="+ productoId;
		DataBase.pstmt = conn.prepareStatement(sql);
		DataBase.pstmt.executeUpdate();
		DataBase.pstmt = null;
	}
	// => Modificar los datos de los productos cargados
	public static void eliminarProducto(Producto producto) throws SQLException{
		int productoId = producto.getId();
		String sql = "update t_producto set stock = 0 where id_producto = "+productoId+";";
		DataBase.pstmt = conn.prepareStatement(sql);
		DataBase.pstmt.executeUpdate();
		DataBase.pstmt = null;
	}
	// => Ver todos los usuarios que realizaron una compra
	public static void compraCliente() throws SQLException {
		String sql = "select id_cliente,nombre, apellido from t_compras, t_cliente where fk_id_cliente = id_cliente group by nombre;";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		while(rs.next()) {
			System.out.println("ID: "+rs.getInt("id_cliente")+" Nombre y Apellido: "+rs.getString("nombre")+" "+rs.getString("apellido"));
		}
		rs.close();
	}
	// => Ver listado de productos seleccionados por el usuario
	public static void listaComprasUnCliente(String id) throws SQLException {
		String sql = "select fk_id_cliente, nombre, apellido, fk_id_producto, descripcion, id_producto, precio ,compras_cantidad from t_compras,"
				+ " t_producto, t_cliente where fk_id_producto = id_producto and fk_id_cliente = id_cliente and id_cliente = "+id+";";
		ResultSet rs = DataBase.stmt.executeQuery(sql);
		rs.next();
		System.out.println("El cliente "+rs.getString("nombre")+" "+rs.getString("apellido"));
		System.out.println("- compró "+rs.getInt("compras_cantidad")+" "+rs.getString("descripcion")+"[id: "+rs.getInt("id_producto")+"] a "+rs.getDouble("precio")+" cada uno/a");
		while(rs.next()) {
			 System.out.println("- compró "+rs.getInt("compras_cantidad")+" "+rs.getString("descripcion")+"[id: "+rs.getInt("id_producto")+"] a "+rs.getDouble("precio")+" cada uno/a");
		}
		rs.close();
	}
}

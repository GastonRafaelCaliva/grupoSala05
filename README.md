# Sala5 Supermark 
_plataforma donde publicar sus productos, gestionar y premiar a los usuarios que se registren en la aplicación, con la intención de disminuir la saturación de las colas sin perder clientes._
_*(Comisión: Antes CN8 con el profesor René Mogro -> Ahora CN4 con el profesor Ramiro)*_

## Pre-requisitos 📋

_* [Eclipse](https://www.eclipse.org/) - IDE utilizado para el desarollo de la aplicación_
 ```
https://www.eclipse.org/downloads/packages/release/2022-06/r/eclipse-ide-java-developers
 ```
_* [MySql](https://www.mysql.com/) - Sistema de gestión de bases de datos utilizada para almacenar y acceder a los datos_
 ```
https://www.mysql.com/products/workbench/
 ```

## Antes de probarlo ⚙️
_* Debes ingresar a tu mysql, preferentemente a tu conexión, abres el script de la base de datos que esta en el repositorio y lo ejecutas, con esto ya tendrías la base de datos cargada junto con datos como los clientes y administradores, y algunas compras ya realizadas._
_* Luego deberías abrir el eclipse, cargar el proyecto que esta en el repositorio y hacer la conexión de eclipse con mysql. Luego muy importante debes entrar al paquete baseDeDatos -> a la clase usuarioDBPassword y en las siguientes lineas de código._
 ```
  static final String USER = "Tu usuario"; // <- Aquí poner tu usuario de tu conexión al mysql 
  static final String PASS = "Tu contraseña"; // <- Aquí poner tu contraseña de dicha conexion
 ```

## Como probarlo 🚀
_* En el eclipse te situas en la clase inicio y corres el programa_
_* Veras un menu para iniciar sesion o registrarte_
_* En caso de que te registres solo serás un cliente_

### Inicio de sesion 📌
#### En caso de que inicies sesion como cliente📄 podrás acceder a las siguientes opciones:
* 1 -> Seleccionar productos
* 2 -> Ver listado de productos seleccionados
* 3 -> Autorizar la compra de los productos seleccionados
* Otro caracter -> Salir
##### Consideraciones
-	Controlar el stock de productos.
-	No se puede seleccionar un producto con stock 0
-	Los usuarios pueden seleccionar hasta 30 artículos

#### En caso de que inicies sesion como administrador🛠️📦 podrás acceder a las siguientes opciones:
* 1 - Cargar Nuevos Productos
* 2 - Modificar Producto
* 3 - Eliminar Producto
* 4 - Ver clientes que realizaron una compra
* 5 - Ver lista de compras de un cliente
* 0 - Salir

## Construido con 🛠️
_Eclipse Java 2022-03, MySQL Workbrench 8.0_

* [Eclipse](https://www.eclipse.org/)
* [MySql](https://www.mysql.com/) - Manejador de dependencias

## Diagramas 📖
_El DER fue extraido del MySQL y el diagrama de clases UML fue creado con Umlet_
* [Umlet](https://www.umlet.com/)

## Autores ✒️

* **Caliva, Gaston Rafael** - [GastonRafaelCaliva](https://github.com/GastonRafaelCaliva)
* **Vargas, Carlos Ariel** - 
* **Guzmán Walter Omar** - 
* **Cardozo Macarena Soledad** - 
* **Sumbaine Fátima Isabel** - 
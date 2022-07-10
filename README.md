# Sala5 Supermark 
_Plataforma donde puede publicar sus productos, gestionar a los usuarios que se registren en la aplicación, con la intención de disminuir la saturación de las colas sin perder clientes._
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
* Debes ingresar a tu mysql, preferentemente a tu conexión, abres el script de la base de datos que esta en el repositorio y lo ejecutas, con esto ya tendrías la base de datos cargada junto con datos como los clientes y administradores, y algunas compras ya realizadas.
* Luego deberías abrir el eclipse, cargar el proyecto que esta en el repositorio y hacer la conexión de eclipse con mysql. Luego muy importante debes entrar al paquete baseDeDatos -> a la clase usuarioDBPassword y en las siguientes lineas de código.
 ```
  static final String USER = "Tu usuario"; // <- Aquí poner tu usuario de tu conexión al mysql 
  static final String PASS = "Tu contraseña"; // <- Aquí poner tu contraseña de dicha conexion
 ```

## Como probarlo 🚀
* En el eclipse te situas en la clase inicio y corres el programa.
* Veras un menu para iniciar sesion o registrarte.
* En caso de que te registres solo serás un cliente.

### Inicio de sesion 📌
#### Puede iniciar sesión como cliente o como administrador, teniendo en cuenta los usuarios creados previamente:
| **Usuario** | **Contraseña** | **Rol** |
| :---: | :---: | :---: |
| gaston | grc | admin |
| ariel | acv | admin |
| walter | wog | admin |
| macarena | msc | admin |
| fatima | fis | admin |
| rene | cn8 | admin |
| pepito| ppp | cliente |
| ramiro | rrr | cliente |

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
* **Vargas, Carlos Ariel** - [Arie-Vargas](https://github.com/Arie-Vargas)
* **Guzmán Walter Omar** - [......](https://github.com/)
* **Cardozo Macarena Soledad** - [SOLE-VME](https://github.com/SOLE-VME)
* **Sumbaine Fátima Isabel** - [fatisumbaine](https://github.com/fatisumbaine)

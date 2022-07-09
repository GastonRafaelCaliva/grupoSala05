drop database if exists supermercado;
CREATE DATABASE if not exists supermercado CHARACTER SET utf8mb4;
USE supermercado;

CREATE TABLE if not exists t_usuario(
id_usuario int not null auto_increment,
nombre varchar(25) not null,
contra varchar(25) not null,
rol enum('cliente', 'admin') not null,
primary key (id_usuario)
);

CREATE TABLE if not exists t_cliente(
id_cliente int not null auto_increment,
nombre varchar(25) not null,
apellido varchar(25) not null,
dni varchar(25) not null,
edad int not null,
telefono varchar(15) not null,
domicilio varchar(50) not null,
fk_id_usuario int not null,
foreign key (fk_id_usuario)
references t_usuario(id_usuario),
primary key (id_cliente)
);

CREATE TABLE if not exists t_administrador(
id_administrador int not null primary key,
fk_id_usuario int not null,
foreign key (fk_id_usuario)
references t_usuario(id_usuario)
);
CREATE TABLE if not exists t_producto(
id_producto int not null auto_increment,
descripcion varchar(50) not null,
precio double not null,
stock int,
primary key (id_producto)
);

CREATE TABLE if not exists t_compras(
fk_id_cliente INT NOT NULL,
fk_id_producto INT NOT NULL,
compras_cantidad int not null,
FOREIGN KEY (fk_id_cliente) REFERENCES t_cliente(id_cliente),
FOREIGN KEY (fk_id_producto) REFERENCES t_producto(id_producto)
);

INSERT INTO t_producto VALUES(1,'Leche Nido Deslactosada',600,20);
INSERT INTO t_producto VALUES(2,'Cerveza Salta',60,20);
INSERT INTO t_producto VALUES(3,'Arroz Gallo',10,20);
INSERT INTO t_producto VALUES(4,'Costeleta x kg',991.90,20);
INSERT INTO t_producto VALUES(5,'Sobaco x kg',999.99,20);
INSERT INTO t_producto VALUES(6,'Salchichas Sofia',84.90,20);
INSERT INTO t_producto VALUES(7,'Galleta Traviata',149.90,20);
INSERT INTO t_producto VALUES(8,'Poleta Molinor',77.90,20);
INSERT INTO t_producto VALUES(9,'Fideos La Americana',82.90,20);
INSERT INTO t_producto VALUES(10,'Vinagre Molto',74.90,20);
INSERT INTO t_producto VALUES(11,'Lavandina',96.90,20);
INSERT INTO t_producto VALUES(12,'Galleta Diversion',155.90,20);
INSERT INTO t_producto VALUES(13,'Dulce de Leche lA Serenisima',199.90,20);
INSERT INTO t_producto VALUES(14,'Picadillo La Negra',110,20);
INSERT INTO t_producto VALUES(15,'Pure de Tomate Molto',90,20);
INSERT INTO t_producto VALUES(16,'Cafe La Virginia x25 saq',136.90,20);
INSERT INTO t_producto VALUES(17,'Vino Viña del Baldo',284.90,20);
INSERT INTO t_producto VALUES(18,'Papel Higenico Cristal x70mt',40,20);
INSERT INTO t_producto VALUES(19,'Desodorante Jardin x930ml',82.90,20);
INSERT INTO t_producto VALUES(20,'Jabon Ala',95,20);

INSERT INTO  t_usuario VALUES (1,'gaston', 'grc', 'admin');
INSERT INTO  t_usuario VALUES(2, 'ariel','acv','admin');
INSERT INTO  t_usuario VALUES(3,'walter','wog','admin');
INSERT INTO  t_usuario VALUES(4,'macarena','msc','admin');
INSERT INTO  t_usuario VALUES(5,'fatima','fis','admin');
INSERT INTO  t_usuario VALUES(6,'pepito','ppp','cliente');
INSERT INTO  t_usuario VALUES(7,'rene','rrr','admin'); #The best teacher
INSERT INTO  t_usuario VALUES(8,'ramiro','rrr','cliente');

INSERT INTO t_cliente VALUES(1,'Pepito','Pepe','1100000',65,'387000000','Caseros100',6);
INSERT INTO t_cliente VALUES(2,'Ramiro','Medina','31450220',34,'3875505050','DomicilioDesconocido',8);

INSERT INTO t_administrador VALUES(1,1);
INSERT INTO t_administrador VALUES(2,2);
INSERT INTO t_administrador VALUES(3,3);
INSERT INTO t_administrador VALUES(4,4);
INSERT INTO t_administrador VALUES(5,5);
INSERT INTO t_administrador VALUES(6,7);

insert into t_compras values(2,1,5);
insert into t_compras values(2,3,2);
insert into t_compras values(2,7,9);

insert into t_compras values(1,2,6);
insert into t_compras values(1,5,4);
-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema proyectoava2
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ proyectoava2;
USE proyectoava2;

--
-- Table structure for table `proyectoava2`.`tbl_acceso`
--

DROP TABLE IF EXISTS `tbl_acceso`;
CREATE TABLE `tbl_acceso` (
  `usuario_codigo` int(11) NOT NULL,
  `modulo_codigo` varchar(45) NOT NULL,
  `acceso_estado` varchar(45) NOT NULL,
  KEY `fk_tbl_acceso_tbl_usuario1_idx` (`usuario_codigo`),
  KEY `fk_tbl_acceso_tbl_modulo1_idx` (`modulo_codigo`),
  CONSTRAINT `fk_tbl_acceso_tbl_modulo1` FOREIGN KEY (`modulo_codigo`) REFERENCES `tbl_modulo` (`modulo_codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_acceso_tbl_usuario1` FOREIGN KEY (`usuario_codigo`) REFERENCES `tbl_usuario` (`usuario_codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_acceso`
--

/*!40000 ALTER TABLE `tbl_acceso` DISABLE KEYS */;
INSERT INTO `tbl_acceso` (`usuario_codigo`,`modulo_codigo`,`acceso_estado`) VALUES 
 (1,'001','activo'),
 (1,'00101','activo'),
 (1,'00102','activo'),
 (1,'00103','activo'),
 (1,'00104','activo'),
 (1,'00105','activo'),
 (1,'002','activo'),
 (1,'003','activo'),
 (3,'001','activo'),
 (2,'001','activo'),
 (2,'00101','activo'),
 (1,'00201','activo'),
 (1,'00301','activo'),
 (2,'00102','inactivo'),
 (2,'00103','inactivo'),
 (2,'00104','inactivo'),
 (2,'00105','inactivo'),
 (2,'002','inactivo'),
 (2,'00201','inactivo'),
 (2,'003','inactivo'),
 (2,'00301','inactivo'),
 (3,'00101','activo'),
 (3,'00102','activo'),
 (3,'00103','inactivo'),
 (3,'00104','inactivo'),
 (3,'00105','inactivo'),
 (3,'002','inactivo'),
 (3,'00201','inactivo'),
 (3,'003','inactivo'),
 (3,'00301','inactivo'),
 (1,'01','activo'),
 (2,'01','inactivo'),
 (3,'01','inactivo'),
 (1,'004','activo'),
 (1,'00202','activo'),
 (1,'00203','activo'),
 (1,'00204','activo'),
 (1,'00302','inactivo'),
 (1,'00303','inactivo'),
 (1,'00304','inactivo'),
 (1,'00401','inactivo');
INSERT INTO `tbl_acceso` (`usuario_codigo`,`modulo_codigo`,`acceso_estado`) VALUES 
 (1,'00402','inactivo'),
 (1,'00403','inactivo'),
 (1,'00404','inactivo'),
 (1,'005','inactivo'),
 (1,'00501','inactivo'),
 (1,'00502','inactivo'),
 (1,'00503','inactivo'),
 (1,'00504','inactivo'),
 (1,'006','inactivo'),
 (1,'00601','inactivo'),
 (1,'00602','inactivo');
/*!40000 ALTER TABLE `tbl_acceso` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_bitacora`
--

DROP TABLE IF EXISTS `tbl_bitacora`;
CREATE TABLE `tbl_bitacora` (
  `bitacora_codigo` int(11) NOT NULL auto_increment,
  `bitacora_descripcion` longtext NOT NULL,
  `bitacora_fecha` datetime NOT NULL,
  PRIMARY KEY  (`bitacora_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_bitacora`
--

/*!40000 ALTER TABLE `tbl_bitacora` DISABLE KEYS */;
INSERT INTO `tbl_bitacora` (`bitacora_codigo`,`bitacora_descripcion`,`bitacora_fecha`) VALUES 
 (1,'Se Realizo una Compra de Producto kjhg con Codigo as500 Con un Peso de 2.00 cada uno, Cantidad Comprada de 1 con el Precio de 2.00 Se vendera a 2.00 teniendo una Ganancia de 0.00 el Codigo Categoria asignado es 1 con Codigo de Impuesto 1 el Codigo del Proveedor es el 1','2020-04-17 03:35:26'),
 (2,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as500 a as500  Se Camibo el nombre de kjhg a Sera Se Modifico la Cantidad 1 a 1 Se Modifico el Peso 2.00 a 2.00 Se Modifico el Precio de Costo 2.00 a 2.00 Se Modifico el Precio Venta 2.00 a 2.00 Se Modifico el Codigo de Categoria1 a 1 Se Modifico el Codigo de Impuesto 1 a 1 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 0.00 a 0.00','2020-04-17 03:35:50'),
 (3,'Se Realizo una Compra de Producto WIIU con Codigo as400 Con un Peso de 35.00 cada uno, Cantidad Comprada de 5 con el Precio de 35.00 Se vendera a 187.00 teniendo una Ganancia de 10.00 el Codigo Categoria asignado es 2 con Codigo de Impuesto 3 el Codigo del Proveedor es el 1','2020-04-17 04:10:14'),
 (4,'Se Realizo una Compra de Producto WIIU con Codigo as600 Con un Peso de 35.00 cada uno, Cantidad Comprada de 5 con el Precio de 35.00 Se vendera a 187.00 teniendo una Ganancia de 10.00 el Codigo Categoria asignado es 2 con Codigo de Impuesto 3 el Codigo del Proveedor es el 1','2020-04-17 04:14:59');
INSERT INTO `tbl_bitacora` (`bitacora_codigo`,`bitacora_descripcion`,`bitacora_fecha`) VALUES 
 (5,'Se Realizo una Compra de Producto Gatonera con Codigo as700 Con un Peso de 35.00 cada uno, Cantidad Comprada de 5 con el Precio de 35.00 Se vendera a 187.00 teniendo una Ganancia de 10.00 el Codigo Categoria asignado es 1 con Codigo de Impuesto 3 el Codigo del Proveedor es el 1','2020-04-17 04:14:59'),
 (6,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as700 a as700  Se Camibo el nombre de Gatonera a Gatonera Se Modifico la Cantidad 5 a 2 Se Modifico el Peso 35.00 a 35.00 Se Modifico el Precio de Costo 170.00 a 170.00 Se Modifico el Precio Venta 187.00 a 187.00 Se Modifico el Codigo de Categoria1 a 1 Se Modifico el Codigo de Impuesto 3 a 3 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 10.00 a 10.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 05:39:00'),
 (7,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as100 a as100  Se Camibo el nombre de PetFood 100 a PetFood 100 Se Modifico la Cantidad 3 a 0 Se Modifico el Peso 100.00 a 100.00 Se Modifico el Precio de Costo 500.00 a 500.00 Se Modifico el Precio Venta 510.00 a 510.00 Se Modifico el Codigo de Categoria2 a 2 Se Modifico el Codigo de Impuesto 2 a 2 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 2.00 a 2.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 05:39:24'),
 (8,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as600 a as600  Se Camibo el nombre de WIIU a WIIU Se Modifico la Cantidad 5 a 2 Se Modifico el Peso 35.00 a 35.00 Se Modifico el Precio de Costo 170.00 a 170.00 Se Modifico el Precio Venta 187.00 a 187.00 Se Modifico el Codigo de Categoria2 a 2 Se Modifico el Codigo de Impuesto 3 a 3 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 10.00 a 10.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 05:39:24');
INSERT INTO `tbl_bitacora` (`bitacora_codigo`,`bitacora_descripcion`,`bitacora_fecha`) VALUES 
 (9,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as700 a as700  Se Camibo el nombre de Gatonera a Gatonera Se Modifico la Cantidad 2 a 1 Se Modifico el Peso 35.00 a 35.00 Se Modifico el Precio de Costo 170.00 a 170.00 Se Modifico el Precio Venta 187.00 a 187.00 Se Modifico el Codigo de Categoria1 a 1 Se Modifico el Codigo de Impuesto 3 a 3 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 10.00 a 10.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 07:14:32'),
 (10,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as300 a as300  Se Camibo el nombre de Perrera a Perrera Se Modifico la Cantidad 3 a 2 Se Modifico el Peso 0.00 a 0.00 Se Modifico el Precio de Costo 0.00 a 0.00 Se Modifico el Precio Venta 0.00 a 0.00 Se Modifico el Codigo de Categoria1 a 1 Se Modifico el Codigo de Impuesto 1 a 1 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 0.00 a 0.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 07:26:13'),
 (11,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as200 a as200  Se Camibo el nombre de WIII a WIII Se Modifico la Cantidad 3 a 2 Se Modifico el Peso 30.00 a 30.00 Se Modifico el Precio de Costo 200.00 a 200.00 Se Modifico el Precio Venta 210.00 a 210.00 Se Modifico el Codigo de Categoria1 a 1 Se Modifico el Codigo de Impuesto 4 a 4 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 5.00 a 5.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 07:32:19');
INSERT INTO `tbl_bitacora` (`bitacora_codigo`,`bitacora_descripcion`,`bitacora_fecha`) VALUES 
 (12,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as200 a as200  Se Camibo el nombre de WIII a WIII Se Modifico la Cantidad 2 a 1 Se Modifico el Peso 30.00 a 30.00 Se Modifico el Precio de Costo 200.00 a 200.00 Se Modifico el Precio Venta 210.00 a 210.00 Se Modifico el Codigo de Categoria1 a 1 Se Modifico el Codigo de Impuesto 4 a 4 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 5.00 a 5.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 07:32:34'),
 (13,'Se Actualizo El Producto de la tabla tbl_producto con el codigo as600 a as600  Se Camibo el nombre de WIIU a WIIU Se Modifico la Cantidad 2 a 1 Se Modifico el Peso 35.00 a 35.00 Se Modifico el Precio de Costo 170.00 a 170.00 Se Modifico el Precio Venta 187.00 a 187.00 Se Modifico el Codigo de Categoria2 a 2 Se Modifico el Codigo de Impuesto 3 a 3 Se Modifico el Codigo de Proveedor 1 a 1 Se Modifico el Porcentaje de Ganancia 10.00 a 10.00 Se Modifico el Porcentaje de Descunto 0.00 a 0.00','2020-04-18 07:33:10'),
 (14,'Se Realizo una Compra de Producto nom con Codigo cod002 Con un Peso de 200.00 cada uno, Cantidad Comprada de 23 con el Precio de 200.00 Se vendera a 310.00 teniendo una Ganancia de 0.00 el Codigo Categoria asignado es 1 con Codigo de Impuesto 1 el Codigo del Proveedor es el 1 Con un Descuento de 0.00','2020-04-18 18:52:05');
INSERT INTO `tbl_bitacora` (`bitacora_codigo`,`bitacora_descripcion`,`bitacora_fecha`) VALUES 
 (15,'Se Realizo una Compra de Producto Huevos con Codigo hv100 Con un Peso de 5.00 cada uno, Cantidad Comprada de 30 con el Precio de 5.00 Se vendera a 90.90 teniendo una Ganancia de 1.00 el Codigo Categoria asignado es 1 con Codigo de Impuesto 2 el Codigo del Proveedor es el 1 Con un Descuento de 0.00','2020-04-18 19:09:17');
/*!40000 ALTER TABLE `tbl_bitacora` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_categoria`
--

DROP TABLE IF EXISTS `tbl_categoria`;
CREATE TABLE `tbl_categoria` (
  `categoria_codigo` int(11) NOT NULL auto_increment,
  `categoria_nombre` text NOT NULL,
  `categoria_descripcion` text,
  PRIMARY KEY  (`categoria_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_categoria`
--

/*!40000 ALTER TABLE `tbl_categoria` DISABLE KEYS */;
INSERT INTO `tbl_categoria` (`categoria_codigo`,`categoria_nombre`,`categoria_descripcion`) VALUES 
 (1,'Gallina','Huevos'),
 (2,'Perros','Caninos');
/*!40000 ALTER TABLE `tbl_categoria` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_cliente`
--

DROP TABLE IF EXISTS `tbl_cliente`;
CREATE TABLE `tbl_cliente` (
  `cliente_codigo` int(11) NOT NULL auto_increment,
  `cliente_nombre` text NOT NULL,
  `cliente_rtn` bigint(14) NOT NULL,
  `cliente_direccion` longtext NOT NULL,
  PRIMARY KEY  (`cliente_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_cliente`
--

/*!40000 ALTER TABLE `tbl_cliente` DISABLE KEYS */;
INSERT INTO `tbl_cliente` (`cliente_codigo`,`cliente_nombre`,`cliente_rtn`,`cliente_direccion`) VALUES 
 (1,'Mario Castañeda',101197000526,'La Masica');
/*!40000 ALTER TABLE `tbl_cliente` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_cliente_contacto`
--

DROP TABLE IF EXISTS `tbl_cliente_contacto`;
CREATE TABLE `tbl_cliente_contacto` (
  `cliente_codigo` int(11) NOT NULL,
  `cliente_contacto_telefono` int(15) NOT NULL,
  `cliente_contacto_email` varchar(45) NOT NULL,
  PRIMARY KEY  (`cliente_codigo`,`cliente_contacto_telefono`,`cliente_contacto_email`),
  KEY `fk_tbl_cliente_contacto_tbl_cliente1_idx` (`cliente_codigo`),
  CONSTRAINT `fk_tbl_cliente_contacto_tbl_cliente1` FOREIGN KEY (`cliente_codigo`) REFERENCES `tbl_cliente` (`cliente_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_cliente_contacto`
--

/*!40000 ALTER TABLE `tbl_cliente_contacto` DISABLE KEYS */;
INSERT INTO `tbl_cliente_contacto` (`cliente_codigo`,`cliente_contacto_telefono`,`cliente_contacto_email`) VALUES 
 (1,654,'f');
/*!40000 ALTER TABLE `tbl_cliente_contacto` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_config`
--

DROP TABLE IF EXISTS `tbl_config`;
CREATE TABLE `tbl_config` (
  `config_codigo` int(11) NOT NULL,
  `config_descripcion` varchar(45) default NULL,
  `config_factor` int(11) default NULL,
  PRIMARY KEY  (`config_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_config`
--

/*!40000 ALTER TABLE `tbl_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_config` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_deduccion`
--

DROP TABLE IF EXISTS `tbl_deduccion`;
CREATE TABLE `tbl_deduccion` (
  `deduccion_codigo` int(11) NOT NULL auto_increment,
  `deduccion_nombre` varchar(45) NOT NULL,
  `deduccion_descripcion` varchar(200) default NULL,
  `deduccion_forma` varchar(45) NOT NULL,
  `deduccion_estado` varchar(45) NOT NULL,
  PRIMARY KEY  (`deduccion_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_deduccion`
--

/*!40000 ALTER TABLE `tbl_deduccion` DISABLE KEYS */;
INSERT INTO `tbl_deduccion` (`deduccion_codigo`,`deduccion_nombre`,`deduccion_descripcion`,`deduccion_forma`,`deduccion_estado`) VALUES 
 (1,'IHSS','Deduccion de Seguro Publico','Porcentaje','habilitado'),
 (2,'RAP','Deduccion Publico Obligatorio','Monto','habilitado');
/*!40000 ALTER TABLE `tbl_deduccion` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_detalle_recibo_proveedor`
--

DROP TABLE IF EXISTS `tbl_detalle_recibo_proveedor`;
CREATE TABLE `tbl_detalle_recibo_proveedor` (
  `detalle_recibo_proveedor_saldo` decimal(20,2) NOT NULL,
  `detalle_recibo_proveedor_total` decimal(20,2) NOT NULL,
  `detalle_recibo_proveedor_saldoA` decimal(20,2) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_detalle_recibo_proveedor`
--

/*!40000 ALTER TABLE `tbl_detalle_recibo_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_detalle_recibo_proveedor` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_empleado`
--

DROP TABLE IF EXISTS `tbl_empleado`;
CREATE TABLE `tbl_empleado` (
  `empleado_codigo` int(11) NOT NULL auto_increment,
  `empleado_nombre` varchar(45) NOT NULL,
  `empleado_snombre` varchar(45) default NULL,
  `empleado_apellido` varchar(45) NOT NULL,
  `empleado_sapellido` varchar(45) default NULL,
  `empleado_id` bigint(13) NOT NULL,
  `empleado_fecha_nacimiento` datetime NOT NULL,
  `empleado_genero` varchar(45) NOT NULL,
  `empleado_estado_civil` varchar(45) NOT NULL,
  `empleado_cargo` varchar(45) NOT NULL,
  `empleado_tipo_contratacion` varchar(45) NOT NULL,
  `empleado_estado` varchar(45) NOT NULL,
  PRIMARY KEY  (`empleado_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_empleado`
--

/*!40000 ALTER TABLE `tbl_empleado` DISABLE KEYS */;
INSERT INTO `tbl_empleado` (`empleado_codigo`,`empleado_nombre`,`empleado_snombre`,`empleado_apellido`,`empleado_sapellido`,`empleado_id`,`empleado_fecha_nacimiento`,`empleado_genero`,`empleado_estado_civil`,`empleado_cargo`,`empleado_tipo_contratacion`,`empleado_estado`) VALUES 
 (1,'Salvador','Alejandro','Gonzalez','Acosta',107199600654,'1996-03-11 00:00:00','Masculino','Casado','Venta','Temporal','activo');
/*!40000 ALTER TABLE `tbl_empleado` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_empleado_deduccion`
--

DROP TABLE IF EXISTS `tbl_empleado_deduccion`;
CREATE TABLE `tbl_empleado_deduccion` (
  `empleado_codigo` int(11) NOT NULL,
  `deduccion_codigo` int(11) NOT NULL,
  `emp_ded_tipo` varchar(45) NOT NULL,
  `emp_ded_valor` varchar(45) NOT NULL,
  PRIMARY KEY  (`empleado_codigo`,`deduccion_codigo`),
  KEY `fk_tbl_empleado_has_tbl_deduccion_tbl_deduccion1_idx` (`deduccion_codigo`),
  KEY `fk_tbl_empleado_has_tbl_deduccion_tbl_empleado1_idx` (`empleado_codigo`),
  CONSTRAINT `fk_tbl_empleado_has_tbl_deduccion_tbl_empleado1` FOREIGN KEY (`empleado_codigo`) REFERENCES `tbl_empleado` (`empleado_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_empleado_has_tbl_deduccion_tbl_deduccion1` FOREIGN KEY (`deduccion_codigo`) REFERENCES `tbl_deduccion` (`deduccion_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_empleado_deduccion`
--

/*!40000 ALTER TABLE `tbl_empleado_deduccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_empleado_deduccion` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_empleado_salario`
--

DROP TABLE IF EXISTS `tbl_empleado_salario`;
CREATE TABLE `tbl_empleado_salario` (
  `empleado_codigo` int(11) NOT NULL,
  `empleado_salario` int(11) NOT NULL,
  PRIMARY KEY  (`empleado_codigo`,`empleado_salario`),
  KEY `fk_tbl_empleado_salario_tbl_empleado1_idx` (`empleado_codigo`),
  CONSTRAINT `fk_tbl_empleado_salario_tbl_empleado1` FOREIGN KEY (`empleado_codigo`) REFERENCES `tbl_empleado` (`empleado_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_empleado_salario`
--

/*!40000 ALTER TABLE `tbl_empleado_salario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_empleado_salario` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_impuesto`
--

DROP TABLE IF EXISTS `tbl_impuesto`;
CREATE TABLE `tbl_impuesto` (
  `impueto_codigo` int(11) NOT NULL auto_increment,
  `impuesto_descripcion` varchar(100) default NULL,
  `impuesto_valor` decimal(9,2) default NULL,
  PRIMARY KEY  (`impueto_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_impuesto`
--

/*!40000 ALTER TABLE `tbl_impuesto` DISABLE KEYS */;
INSERT INTO `tbl_impuesto` (`impueto_codigo`,`impuesto_descripcion`,`impuesto_valor`) VALUES 
 (1,'ISV 0','0.00'),
 (2,'ISV 15','0.15'),
 (3,'ISV 16','0.16'),
 (4,'ISV 18','0.18');
/*!40000 ALTER TABLE `tbl_impuesto` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_modulo`
--

DROP TABLE IF EXISTS `tbl_modulo`;
CREATE TABLE `tbl_modulo` (
  `modulo_codigo` varchar(45) NOT NULL,
  `modulo_nombre` varchar(45) default NULL,
  `modulo_descrip` varchar(100) default NULL,
  PRIMARY KEY  (`modulo_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_modulo`
--

/*!40000 ALTER TABLE `tbl_modulo` DISABLE KEYS */;
INSERT INTO `tbl_modulo` (`modulo_codigo`,`modulo_nombre`,`modulo_descrip`) VALUES 
 ('001','Usuario','Menu de los Usuarios'),
 ('00101','Crear','Crear un nuevo Usuario'),
 ('00102','Modificar','Modificar un Usuario'),
 ('00103','Eliminar','Eliminar el acceso a un Usuario'),
 ('00104','Restaurar','Restaurar el acceso a un Usuario'),
 ('00105','Accesos','Asignar o Remover Accesos a un Usuario'),
 ('002','Cliente','Menu de los Clientes'),
 ('00201','Crear Cliente','Crear un Cliente Nuevo'),
 ('00202','Modificar Cliente','Modificar Cliente Existente'),
 ('00203','Eliminar Cliente','Eliminar Cliente Existente'),
 ('00204','Restaurar Cliente','Restaurar Cliente Existente No Disponible'),
 ('003','Proveedor','Menu de los Proveedores'),
 ('00301','Crear Proveedor','Crear un Proveedor Nuevo'),
 ('00302','Modificar Proveedor','Modificar Datos de un Proveedor Agregado'),
 ('00303','Eliminar Proveedor','Eliminar Algun Proveedor Agregado'),
 ('00304','Restaurar Proveedor','Restaurar Proveedor anteriormente Agregado'),
 ('004','Empleados','Configurar Empleados');
INSERT INTO `tbl_modulo` (`modulo_codigo`,`modulo_nombre`,`modulo_descrip`) VALUES 
 ('00401','Crear Empleado','Ingresar Empleado Nuevo'),
 ('00402','Modificar Empleado','Modificar Empleado Existente'),
 ('00403','Eliminar Empleado','Eliminar Empleado Existente'),
 ('00404','Restaurar Empleado','Restaurar Empleado Existente'),
 ('005','Deduccion','Configurar Deduccion'),
 ('00501','Crear Deduccion','Agregar un Deduccion a la Lista Disponible'),
 ('00502','Modificar Deduccion','Modificar alguna Deduccion que este en la Lista Disponible'),
 ('00503','Eliminar Deduccion','Eliminar alguna Deduccion de la Lista Disponible'),
 ('00504','Restaurar Deduccion','Restaura alguna Deduccion que este Deshabilitada'),
 ('006','Planilla','Menu de Planilla'),
 ('00601','Ver Planilla','Ver Planilla'),
 ('00602','Configurar Planilla','Configurar Planilla'),
 ('01','Ajustes','Permitir Cambiar Ajustes Generales');
/*!40000 ALTER TABLE `tbl_modulo` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_producto`
--

DROP TABLE IF EXISTS `tbl_producto`;
CREATE TABLE `tbl_producto` (
  `producto_codigo` int(11) NOT NULL auto_increment,
  `producto_codigobarra` varchar(45) NOT NULL,
  `producto_descripcion` varchar(200) NOT NULL,
  `producto_stock` int(11) NOT NULL,
  `producto_peso` decimal(9,2) NOT NULL,
  `producto_precio_costo` decimal(9,2) NOT NULL,
  `producto_precio_venta` decimal(9,2) NOT NULL,
  `categoria_codigo` int(11) NOT NULL,
  `impuesto_codigo` int(11) NOT NULL,
  `proveedor_codigo` int(11) NOT NULL,
  `producto_ganancia` decimal(9,2) NOT NULL,
  `producto_descuento` decimal(9,2) NOT NULL default '0.00',
  PRIMARY KEY  (`producto_codigo`,`impuesto_codigo`,`proveedor_codigo`),
  KEY `fk_tbl_producto_tbl_categoria1_idx` (`categoria_codigo`),
  KEY `fk_tbl_producto_tbl_impuesto1_idx` (`impuesto_codigo`),
  KEY `fk_tbl_producto_tbl_proveedor1_idx` (`proveedor_codigo`),
  CONSTRAINT `fk_tbl_producto_tbl_categoria1` FOREIGN KEY (`categoria_codigo`) REFERENCES `tbl_categoria` (`categoria_codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_producto_tbl_impuesto1` FOREIGN KEY (`impuesto_codigo`) REFERENCES `tbl_impuesto` (`impueto_codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_producto_tbl_proveedor1` FOREIGN KEY (`proveedor_codigo`) REFERENCES `tbl_proveedor` (`proveedor_codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_producto`
--

/*!40000 ALTER TABLE `tbl_producto` DISABLE KEYS */;
INSERT INTO `tbl_producto` (`producto_codigo`,`producto_codigobarra`,`producto_descripcion`,`producto_stock`,`producto_peso`,`producto_precio_costo`,`producto_precio_venta`,`categoria_codigo`,`impuesto_codigo`,`proveedor_codigo`,`producto_ganancia`,`producto_descuento`) VALUES 
 (1,'as100','PetFood 100',0,'100.00','500.00','510.00',2,2,1,'2.00','0.00'),
 (2,'as200','WIII',1,'30.00','200.00','210.00',1,4,1,'5.00','0.00'),
 (3,'as300','Perrera',2,'0.00','0.00','0.00',1,1,1,'0.00','0.00'),
 (4,'as400','Robin',1,'190.00','5.00','5.00',2,4,1,'0.00','0.00'),
 (5,'as500','Sera',1,'2.00','2.00','2.00',1,1,1,'0.00','0.00'),
 (7,'as600','WIIU',1,'35.00','170.00','187.00',2,3,1,'10.00','0.00'),
 (8,'as700','Gatonera',1,'35.00','170.00','187.00',1,3,1,'10.00','0.00'),
 (11,'cod01','nom',5,'200.00','20.00','25.00',1,1,1,'2.00','0.00'),
 (19,'cod','nom',23,'200.00','300.00','310.00',1,1,1,'0.00','0.00'),
 (20,'cod01','nom',5,'200.00','20.00','20.40',1,1,1,'2.00','0.00'),
 (22,'cod002','nom',23,'200.00','300.00','310.00',1,1,1,'0.00','0.00'),
 (23,'hv100','Huevos',30,'5.00','90.00','90.90',1,2,1,'1.00','0.00');
/*!40000 ALTER TABLE `tbl_producto` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_proveedor`
--

DROP TABLE IF EXISTS `tbl_proveedor`;
CREATE TABLE `tbl_proveedor` (
  `proveedor_codigo` int(11) NOT NULL auto_increment,
  `proveedor_nombre` varchar(200) NOT NULL,
  `proveedor_rtn` bigint(14) NOT NULL,
  `proveedor_direccion` longtext,
  PRIMARY KEY  (`proveedor_codigo`),
  UNIQUE KEY `proveedor_nombre_UNIQUE` (`proveedor_nombre`),
  UNIQUE KEY `proveedor_rtn_UNIQUE` (`proveedor_rtn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_proveedor`
--

/*!40000 ALTER TABLE `tbl_proveedor` DISABLE KEYS */;
INSERT INTO `tbl_proveedor` (`proveedor_codigo`,`proveedor_nombre`,`proveedor_rtn`,`proveedor_direccion`) VALUES 
 (1,'CARGILL',1011990002413,'Col. Confite');
/*!40000 ALTER TABLE `tbl_proveedor` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_proveedor_contacto`
--

DROP TABLE IF EXISTS `tbl_proveedor_contacto`;
CREATE TABLE `tbl_proveedor_contacto` (
  `proveedor_codigo` int(11) NOT NULL,
  `proveedor_contacto_telefono` int(15) NOT NULL,
  `proveedor_contacto_email` varchar(100) default NULL,
  PRIMARY KEY  (`proveedor_codigo`,`proveedor_contacto_telefono`),
  KEY `fk_tbl_proveedor_contacto_tbl_proveedor1_idx` (`proveedor_codigo`),
  CONSTRAINT `fk_tbl_proveedor_contacto_tbl_proveedor1` FOREIGN KEY (`proveedor_codigo`) REFERENCES `tbl_proveedor` (`proveedor_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_proveedor_contacto`
--

/*!40000 ALTER TABLE `tbl_proveedor_contacto` DISABLE KEYS */;
INSERT INTO `tbl_proveedor_contacto` (`proveedor_codigo`,`proveedor_contacto_telefono`,`proveedor_contacto_email`) VALUES 
 (1,95633607,'cargil@cargil.com');
/*!40000 ALTER TABLE `tbl_proveedor_contacto` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_recibo`
--

DROP TABLE IF EXISTS `tbl_recibo`;
CREATE TABLE `tbl_recibo` (
  `recibo_codigo` int(11) NOT NULL auto_increment,
  `transacciones_codigo` varchar(200) NOT NULL,
  PRIMARY KEY  (`recibo_codigo`,`transacciones_codigo`),
  KEY `fk_tbl_recibo_tbl_transacciones1_idx` (`transacciones_codigo`),
  CONSTRAINT `fk_tbl_recibo_tbl_transacciones1` FOREIGN KEY (`transacciones_codigo`) REFERENCES `tbl_transacciones` (`transacciones_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_recibo`
--

/*!40000 ALTER TABLE `tbl_recibo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_recibo` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_recibo_proveedor`
--

DROP TABLE IF EXISTS `tbl_recibo_proveedor`;
CREATE TABLE `tbl_recibo_proveedor` (
  `codigo_recibo` int(11) NOT NULL,
  `recibo_proveedor_total` decimal(20,2) default NULL,
  PRIMARY KEY  (`codigo_recibo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_recibo_proveedor`
--

/*!40000 ALTER TABLE `tbl_recibo_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_recibo_proveedor` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_transaccion_cliente`
--

DROP TABLE IF EXISTS `tbl_transaccion_cliente`;
CREATE TABLE `tbl_transaccion_cliente` (
  `transacciones_codigo` varchar(200) NOT NULL,
  `cliente_codigo` int(11) NOT NULL,
  `vendedor_codigo` int(11) NOT NULL,
  PRIMARY KEY  (`transacciones_codigo`),
  KEY `fk_tbl_transaccion_cliente_tbl_cliente1_idx` (`cliente_codigo`),
  CONSTRAINT `fk_tbl_transaccion_cliente_tbl_cliente1` FOREIGN KEY (`cliente_codigo`) REFERENCES `tbl_cliente` (`cliente_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_transaccion_cliente_tbl_transacciones1` FOREIGN KEY (`transacciones_codigo`) REFERENCES `tbl_transacciones` (`transacciones_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_transaccion_cliente`
--

/*!40000 ALTER TABLE `tbl_transaccion_cliente` DISABLE KEYS */;
INSERT INTO `tbl_transaccion_cliente` (`transacciones_codigo`,`cliente_codigo`,`vendedor_codigo`) VALUES 
 ('FAC-002',1,1);
/*!40000 ALTER TABLE `tbl_transaccion_cliente` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_transaccion_detalle`
--

DROP TABLE IF EXISTS `tbl_transaccion_detalle`;
CREATE TABLE `tbl_transaccion_detalle` (
  `transacciones_codigo` varchar(200) NOT NULL,
  `transaccion_detalle_posicion` varchar(45) NOT NULL,
  `producto_codigo` int(11) NOT NULL,
  `transaccion_detalle_resta_unidad` int(11) NOT NULL,
  `transaccion_detalle_cantidad` int(11) NOT NULL,
  `transaccion_detalle_total_unidad` decimal(20,2) NOT NULL,
  `transaccion_detalle_total` decimal(20,2) NOT NULL,
  `transaccion_detalle_isv` decimal(20,2) NOT NULL,
  `transaccion_detalle_desc` decimal(20,2) NOT NULL,
  KEY `fk_tbl_transaccion_detalle_tbl_transacciones1_idx` (`transacciones_codigo`),
  KEY `fk_tbl_transaccion_detalle_tbl_producto1_idx` (`producto_codigo`),
  CONSTRAINT `fk_tbl_transaccion_detalle_tbl_producto1` FOREIGN KEY (`producto_codigo`) REFERENCES `tbl_producto` (`producto_codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_transaccion_detalle_tbl_transacciones1` FOREIGN KEY (`transacciones_codigo`) REFERENCES `tbl_transacciones` (`transacciones_codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_transaccion_detalle`
--

/*!40000 ALTER TABLE `tbl_transaccion_detalle` DISABLE KEYS */;
INSERT INTO `tbl_transaccion_detalle` (`transacciones_codigo`,`transaccion_detalle_posicion`,`producto_codigo`,`transaccion_detalle_resta_unidad`,`transaccion_detalle_cantidad`,`transaccion_detalle_total_unidad`,`transaccion_detalle_total`,`transaccion_detalle_isv`,`transaccion_detalle_desc`) VALUES 
 ('FAC-002','1',2,-1,1,'210.00','247.80','37.80','0.00'),
 ('FAC-002','1',2,-1,1,'210.00','247.80','37.80','0.00'),
 ('FAC-002','2',7,-1,1,'187.00','216.92','29.92','0.00');
/*!40000 ALTER TABLE `tbl_transaccion_detalle` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_transaccion_proveedor`
--

DROP TABLE IF EXISTS `tbl_transaccion_proveedor`;
CREATE TABLE `tbl_transaccion_proveedor` (
  `transacciones_codigo` varchar(200) NOT NULL,
  `proveedor_codigo` int(11) NOT NULL,
  PRIMARY KEY  (`transacciones_codigo`),
  KEY `fk_tbl_transaccion_proveedor_tbl_proveedor1_idx` (`proveedor_codigo`),
  CONSTRAINT `fk_tbl_transaccion_proveedor_tbl_proveedor1` FOREIGN KEY (`proveedor_codigo`) REFERENCES `tbl_proveedor` (`proveedor_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_transaccion_proveedor_tbl_transacciones` FOREIGN KEY (`transacciones_codigo`) REFERENCES `tbl_transacciones` (`transacciones_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_transaccion_proveedor`
--

/*!40000 ALTER TABLE `tbl_transaccion_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_transaccion_proveedor` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_transacciones`
--

DROP TABLE IF EXISTS `tbl_transacciones`;
CREATE TABLE `tbl_transacciones` (
  `transacciones_codigo` varchar(200) NOT NULL,
  `transacciones_fecha` datetime NOT NULL,
  `transacciones_cliente` varchar(45) NOT NULL,
  `transacciones_total` decimal(20,2) NOT NULL,
  `transacciones_referencia` varchar(45) NOT NULL,
  `transacciones_tipo_pago` varchar(45) NOT NULL,
  `transacciones_estado` varchar(45) NOT NULL,
  PRIMARY KEY  (`transacciones_codigo`,`transacciones_referencia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_transacciones`
--

/*!40000 ALTER TABLE `tbl_transacciones` DISABLE KEYS */;
INSERT INTO `tbl_transacciones` (`transacciones_codigo`,`transacciones_fecha`,`transacciones_cliente`,`transacciones_total`,`transacciones_referencia`,`transacciones_tipo_pago`,`transacciones_estado`) VALUES 
 ('COM-001','2020-04-18 00:00:00','1011990002413','2384.10','Contado','Tarjeta','Cancelado'),
 ('COM-0010','2020-04-18 00:00:00','1011990002413','31251184.13','Contado','Efectivo','Cancelado'),
 ('COM-0011','2020-04-18 00:00:00','1011990002413','102.00','Contado','Efectivo','Cancelado'),
 ('COM-0012','2020-04-18 00:00:00','1011990002413','102.00','Contado','Efectivo','Cancelado'),
 ('COM-0013','2020-04-18 00:00:00','1011990002413','3136.05','Contado','Efectivo','Cancelado'),
 ('COM-002','2020-04-18 00:00:00','1011990002413','2363.70','Contado','Efectivo','Cancelado'),
 ('COM-003','2020-04-18 00:00:00','1011990002413','32117.09','Contado','Efectivo','Cancelado'),
 ('COM-004','2020-04-18 00:00:00','1011990002413','2384.10','Contado','Efectivo','Cancelado'),
 ('COM-005','2020-04-18 00:00:00','1011990002413','103762.89','Contado','Efectivo','Cancelado'),
 ('COM-006','2020-04-18 00:00:00','1011990002413','0.00','Contado','Efectivo','Cancelado');
INSERT INTO `tbl_transacciones` (`transacciones_codigo`,`transacciones_fecha`,`transacciones_cliente`,`transacciones_total`,`transacciones_referencia`,`transacciones_tipo_pago`,`transacciones_estado`) VALUES 
 ('COM-007','2020-04-18 00:00:00','1011990002413','216.92','Contado','Tarjeta','Cancelado'),
 ('COM-008','2020-04-18 00:00:00','1011990002413','0.00','Contado','Efectivo','Cancelado'),
 ('COM-009','2020-04-18 00:00:00','1011990002413','165.36','Contado','Efectivo','Cancelado'),
 ('FAC-001','2020-04-18 00:00:00','101197000526','0.00','Contado','Efectivo','Cancelado'),
 ('FAC-002','2020-04-18 00:00:00','101197000526','247.80','Contado','Efectivo','Cancelado');
/*!40000 ALTER TABLE `tbl_transacciones` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
CREATE TABLE `tbl_usuario` (
  `usuario_codigo` int(11) NOT NULL auto_increment,
  `usuario_nick` varchar(45) NOT NULL,
  `usuario_clave` varchar(45) NOT NULL,
  `usuario_estado` varchar(45) NOT NULL,
  PRIMARY KEY  (`usuario_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_usuario`
--

/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` (`usuario_codigo`,`usuario_nick`,`usuario_clave`,`usuario_estado`) VALUES 
 (1,'admin','admin','habilitado'),
 (2,'cusadmin','password','deshabilitado'),
 (3,'us001','admin','habilitado');
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;


--
-- Table structure for table `proyectoava2`.`tbl_vendedor`
--

DROP TABLE IF EXISTS `tbl_vendedor`;
CREATE TABLE `tbl_vendedor` (
  `vendedor_codigo` int(11) NOT NULL auto_increment,
  `vendedor_nombre` varchar(45) NOT NULL,
  `usuario_codigo` int(11) NOT NULL,
  `empleado_codigo` int(11) NOT NULL,
  `vendedor_estado` varchar(45) NOT NULL,
  PRIMARY KEY  (`vendedor_codigo`,`usuario_codigo`),
  KEY `fk_tbl_vendedor_tbl_usuario_idx` (`usuario_codigo`),
  KEY `fk_tbl_vendedor_tbl_empleado1_idx` (`empleado_codigo`),
  CONSTRAINT `fk_tbl_vendedor_tbl_empleado1` FOREIGN KEY (`empleado_codigo`) REFERENCES `tbl_empleado` (`empleado_codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tbl_vendedor_tbl_usuario` FOREIGN KEY (`usuario_codigo`) REFERENCES `tbl_usuario` (`usuario_codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proyectoava2`.`tbl_vendedor`
--

/*!40000 ALTER TABLE `tbl_vendedor` DISABLE KEYS */;
INSERT INTO `tbl_vendedor` (`vendedor_codigo`,`vendedor_nombre`,`usuario_codigo`,`empleado_codigo`,`vendedor_estado`) VALUES 
 (1,'Salvador',1,1,'activo');
/*!40000 ALTER TABLE `tbl_vendedor` ENABLE KEYS */;


--
-- View structure for view `proyectoava2`.`vista_cliente`
--

DROP VIEW IF EXISTS `vista_cliente`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `proyectoava2`.`vista_cliente` AS select `c`.`cliente_codigo` AS `cliente_codigo`,`c`.`cliente_nombre` AS `cliente_nombre`,`c`.`cliente_rtn` AS `cliente_rtn`,`c`.`cliente_direccion` AS `cliente_direccion`,`cc`.`cliente_contacto_telefono` AS `cliente_contacto_telefono`,`cc`.`cliente_contacto_email` AS `cliente_contacto_email` from (`proyectoava2`.`tbl_cliente` `C` join `proyectoava2`.`tbl_cliente_contacto` `CC` on((`cc`.`cliente_codigo` = `c`.`cliente_codigo`)));


--
-- View structure for view `proyectoava2`.`vista_det_productos`
--

DROP VIEW IF EXISTS `vista_det_productos`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `proyectoava2`.`vista_det_productos` AS select `p`.`producto_codigo` AS `producto_codigo`,`p`.`producto_codigobarra` AS `producto_codigobarra`,`p`.`producto_descripcion` AS `producto_descripcion`,`p`.`producto_stock` AS `producto_stock`,`p`.`producto_peso` AS `producto_peso`,`p`.`producto_precio_costo` AS `producto_precio_costo`,`p`.`producto_precio_venta` AS `producto_precio_venta`,`c`.`categoria_nombre` AS `categoria_nombre`,`i`.`impuesto_valor` AS `impuesto_valor`,`pr`.`proveedor_nombre` AS `proveedor_nombre`,`p`.`producto_ganancia` AS `producto_ganancia`,`p`.`producto_descuento` AS `producto_descuento` from (((`proyectoava2`.`tbl_producto` `P` join `proyectoava2`.`tbl_impuesto` `I` on((`i`.`impueto_codigo` = `p`.`impuesto_codigo`))) join `proyectoava2`.`tbl_categoria` `C` on((`c`.`categoria_codigo` = `p`.`categoria_codigo`))) join `proyectoava2`.`tbl_proveedor` `PR` on((`pr`.`proveedor_codigo` = `p`.`proveedor_codigo`)));


--
-- View structure for view `proyectoava2`.`vista_factura`
--

DROP VIEW IF EXISTS `vista_factura`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `proyectoava2`.`vista_factura` AS select `t`.`transacciones_codigo` AS `Factura`,`td`.`transaccion_detalle_posicion` AS `Posicion`,`t`.`transacciones_fecha` AS `Fecha`,`p`.`producto_descripcion` AS `Producto`,`td`.`transaccion_detalle_cantidad` AS `Cantidad`,`td`.`transaccion_detalle_total_unidad` AS `Precio`,`td`.`transaccion_detalle_total` AS `Subtotal`,`td`.`transaccion_detalle_desc` AS `Descuento`,`td`.`transaccion_detalle_isv` AS `Isv`,`t`.`transacciones_total` AS `Total`,`c`.`cliente_nombre` AS `Cliente`,`v`.`vendedor_nombre` AS `Vendedor` from (((((`proyectoava2`.`tbl_transacciones` `T` join `proyectoava2`.`tbl_transaccion_detalle` `TD` on((`td`.`transacciones_codigo` = `t`.`transacciones_codigo`))) join `proyectoava2`.`tbl_transaccion_cliente` `TC` on((`tc`.`transacciones_codigo` = `td`.`transacciones_codigo`))) join `proyectoava2`.`tbl_producto` `P` on((`p`.`producto_codigo` = `td`.`producto_codigo`))) join `proyectoava2`.`tbl_vendedor` `V` on((`v`.`vendedor_codigo` = `tc`.`vendedor_codigo`))) join `proyectoava2`.`tbl_cliente` `C` on((`c`.`cliente_codigo` = `tc`.`cliente_codigo`)));

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

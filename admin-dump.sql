/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: restaurante
-- ------------------------------------------------------
-- Server version	11.7.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nomCategoria` varchar(100) DEFAULT NULL,
  `descripcionCategoria` text DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`idCategoria`, `nomCategoria`, `descripcionCategoria`) VALUES (1,'Comida','Platos fuertes y snacks.'),
(2,'Bebidas','Refrescos, jugos y bebidas alcohólicas.');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCte` int(11) NOT NULL AUTO_INCREMENT,
  `nomCte` varchar(100) DEFAULT NULL,
  `telCte` char(10) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `emailCte` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCte`),
  UNIQUE KEY `emailCte` (`emailCte`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` (`idCte`, `nomCte`, `telCte`, `direccion`, `emailCte`) VALUES (1,'Juan Pérez','5512345678','Av. Reforma 123','juan.perez@example.com'),
(2,'María López','5523456789','Calle Insurgentes 456','maria.lopez@example.com');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprainsumos`
--

DROP TABLE IF EXISTS `comprainsumos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `comprainsumos` (
  `idCompra` int(11) NOT NULL,
  `cantidadInsumo` int(11) DEFAULT NULL,
  `idInsumo` int(11) DEFAULT NULL,
  `fechCompra` date DEFAULT NULL,
  `costoCompra` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `CompraInsumosFK1` (`idInsumo`),
  CONSTRAINT `CompraInsumosFK1` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`),
  CONSTRAINT `CompraInsumosCH1` CHECK (`cantidadInsumo` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprainsumos`
--

LOCK TABLES `comprainsumos` WRITE;
/*!40000 ALTER TABLE `comprainsumos` DISABLE KEYS */;
INSERT INTO `comprainsumos` (`idCompra`, `cantidadInsumo`, `idInsumo`, `fechCompra`, `costoCompra`) VALUES (1,20,1,'2025-04-25',400.00),
(2,10,2,'2025-04-25',350.00),
(3,50,3,'2025-04-26',750.00);
/*!40000 ALTER TABLE `comprainsumos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleorden`
--

DROP TABLE IF EXISTS `detalleorden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleorden` (
  `idOrden` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrden`,`idProducto`),
  KEY `DetalleOrdenFK2` (`idProducto`),
  CONSTRAINT `DetalleOrdenFK1` FOREIGN KEY (`idOrden`) REFERENCES `orden` (`idOrden`),
  CONSTRAINT `DetalleOrdenFK2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`),
  CONSTRAINT `DetalleOrdenCH` CHECK (`cantidad` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleorden`
--

LOCK TABLES `detalleorden` WRITE;
/*!40000 ALTER TABLE `detalleorden` DISABLE KEYS */;
INSERT INTO `detalleorden` (`idOrden`, `idProducto`, `cantidad`) VALUES (59,4,1),
(61,12,1),
(62,19,1),
(63,22,1),
(64,4,1),
(64,5,1),
(64,6,1),
(66,4,1),
(66,5,1),
(67,4,1),
(67,5,1),
(67,6,1),
(67,7,1),
(68,8,1),
(68,19,3),
(68,20,1),
(69,4,1),
(69,8,1),
(69,20,1),
(69,21,1),
(69,22,2),
(69,23,1),
(70,4,1),
(70,5,1),
(70,6,1),
(71,7,3),
(71,20,1),
(71,21,1),
(71,22,1),
(72,7,1),
(72,8,1),
(72,9,1),
(72,10,1),
(72,20,1),
(73,6,1),
(73,7,1),
(73,8,1),
(73,20,1),
(73,21,1),
(73,22,1),
(74,8,1),
(74,10,1),
(74,12,1),
(74,19,9),
(75,13,1),
(75,15,1),
(75,16,1),
(75,17,1),
(75,18,1),
(75,21,1),
(75,22,1),
(75,23,1),
(76,8,3),
(76,21,1),
(77,7,1),
(77,8,1),
(77,9,1),
(77,21,1),
(78,5,1),
(78,6,1),
(78,7,2),
(78,8,1),
(78,9,4),
(78,12,1),
(79,7,1),
(79,20,1),
(79,21,2),
(80,20,1),
(80,21,1),
(80,22,1),
(81,6,1),
(81,8,4),
(81,19,1),
(82,19,5),
(82,20,1),
(82,21,3),
(83,19,1),
(83,20,2),
(83,21,3),
(83,22,1),
(85,19,1),
(86,19,1),
(86,20,1),
(87,19,1),
(87,20,1),
(87,22,1),
(87,23,1),
(88,19,2),
(89,20,2),
(90,8,1),
(90,21,2),
(90,22,1),
(91,7,1),
(91,8,1),
(91,22,1),
(92,4,8),
(92,5,8),
(92,6,1),
(92,7,1),
(92,8,1),
(92,20,1),
(92,21,1),
(95,4,1),
(96,4,1),
(96,5,1),
(96,6,1),
(97,4,1),
(97,5,1),
(98,4,1),
(98,6,1),
(98,7,1),
(99,4,1),
(99,5,1),
(99,6,1),
(100,4,1),
(100,5,1),
(101,4,1),
(101,5,1),
(102,4,1),
(102,22,2),
(103,4,1),
(103,21,2),
(103,22,1);
/*!40000 ALTER TABLE `detalleorden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleproducto`
--

DROP TABLE IF EXISTS `detalleproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleproducto` (
  `idInsumo` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL,
  PRIMARY KEY (`idInsumo`,`idProducto`),
  KEY `DetalleProductoFK2` (`idProducto`),
  CONSTRAINT `DetalleProductoFK1` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`),
  CONSTRAINT `DetalleProductoFK2` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleproducto`
--

LOCK TABLES `detalleproducto` WRITE;
/*!40000 ALTER TABLE `detalleproducto` DISABLE KEYS */;
INSERT INTO `detalleproducto` (`idInsumo`, `idProducto`) VALUES (2,4),
(1,5),
(1,8),
(2,8),
(2,10),
(2,11),
(1,13),
(1,14),
(2,15),
(1,16),
(1,17),
(3,19),
(3,20),
(3,21),
(3,22),
(3,23);
/*!40000 ALTER TABLE `detalleproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpl` int(11) NOT NULL AUTO_INCREMENT,
  `nomEmpl` varchar(100) DEFAULT NULL,
  `RFCEmpl` char(13) NOT NULL,
  `CurpEmpl` char(18) NOT NULL,
  `nssEmpl` char(11) NOT NULL,
  `horarioEntradaEmpl` time DEFAULT NULL,
  `horarioSalidaEmpl` time DEFAULT NULL,
  `fechIngresoEmpl` date NOT NULL,
  `telEmpl` char(10) DEFAULT NULL,
  `idPuesto` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEmpl`),
  UNIQUE KEY `RFCEmpl` (`RFCEmpl`),
  UNIQUE KEY `CurpEmpl` (`CurpEmpl`),
  UNIQUE KEY `nssEmpl` (`nssEmpl`),
  KEY `EmpleadoFK1` (`idPuesto`),
  CONSTRAINT `EmpleadoFK1` FOREIGN KEY (`idPuesto`) REFERENCES `puesto` (`idPuesto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`idEmpl`, `nomEmpl`, `RFCEmpl`, `CurpEmpl`, `nssEmpl`, `horarioEntradaEmpl`, `horarioSalidaEmpl`, `fechIngresoEmpl`, `telEmpl`, `idPuesto`, `password`) VALUES (1,'Carlos Sánchez','SANC840101ABC','SANC840101HDFRRL09','12345678901','09:00:00','17:00:00','2023-01-15','5511122233',1,'$argon2i$v=19$m=65536,t=2,p=1$PL/IlpKZ2qq9xnhXMr0RpA$76eCXvxlIVHDQvgFGqPExOMrFTMJLOl9/c14wl7C13k'),
(2,'Ana Martínez','MARA850202XYZ','MARA850202MDFABC01','10987654321','12:00:00','20:00:00','2023-03-10','5511223344',2,'$argon2i$v=19$m=65536,t=2,p=1$vLULZd2gSIpBCVoz1mMV5A$Hzq8mvuUaHRTwys9pR0s7CD21U4YnjGqBmBkrUrIeoU');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumo`
--

DROP TABLE IF EXISTS `insumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `insumo` (
  `idInsumo` int(11) NOT NULL AUTO_INCREMENT,
  `nomIns` varchar(100) DEFAULT NULL,
  `precioIns` decimal(10,2) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `idProveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idInsumo`),
  KEY `InsumoFK1` (`idProveedor`),
  CONSTRAINT `InsumoFK1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`),
  CONSTRAINT `InsumoCH1` CHECK (`precioIns` > 0),
  CONSTRAINT `InsumoCH2` CHECK (`cantidad` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumo`
--

LOCK TABLES `insumo` WRITE;
/*!40000 ALTER TABLE `insumo` DISABLE KEYS */;
INSERT INTO `insumo` (`idInsumo`, `nomIns`, `precioIns`, `cantidad`, `idProveedor`) VALUES (1,'Harina',20.00,50,1),
(2,'Aceite',35.00,30,1),
(3,'Refresco',15.00,100,2);
/*!40000 ALTER TABLE `insumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesa` (
  `idMesa` int(11) NOT NULL AUTO_INCREMENT,
  `capacidadMesa` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMesa`),
  CONSTRAINT `MesaCH1` CHECK (`capacidadMesa` >= 3)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
INSERT INTO `mesa` (`idMesa`, `capacidadMesa`) VALUES (1,4),
(2,6);
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodopago`
--

DROP TABLE IF EXISTS `metodopago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodopago` (
  `idMetodoPago` int(11) NOT NULL AUTO_INCREMENT,
  `tipoMetodo` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`idMetodoPago`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodopago`
--

LOCK TABLES `metodopago` WRITE;
/*!40000 ALTER TABLE `metodopago` DISABLE KEYS */;
INSERT INTO `metodopago` (`idMetodoPago`, `tipoMetodo`, `descripcion`) VALUES (1,'Efectivo','Pago en efectivo'),
(2,'Tarjeta','Pago con tarjeta');
/*!40000 ALTER TABLE `metodopago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden`
--

DROP TABLE IF EXISTS `orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden` (
  `idOrden` int(11) NOT NULL AUTO_INCREMENT,
  `idCte` int(11) DEFAULT NULL,
  `idEmpl` int(11) DEFAULT NULL,
  `idMesa` int(11) DEFAULT NULL,
  `precioOrden` decimal(10,2) DEFAULT NULL,
  `fechHora` datetime NOT NULL DEFAULT current_timestamp(),
  `idMetodoPago` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrden`),
  KEY `OrdenFK1` (`idCte`),
  KEY `OrdenFK2` (`idEmpl`),
  KEY `OrdenFK3` (`idMesa`),
  KEY `OrdenFK4` (`idMetodoPago`),
  CONSTRAINT `OrdenFK1` FOREIGN KEY (`idCte`) REFERENCES `clientes` (`idCte`),
  CONSTRAINT `OrdenFK2` FOREIGN KEY (`idEmpl`) REFERENCES `empleado` (`idEmpl`),
  CONSTRAINT `OrdenFK3` FOREIGN KEY (`idMesa`) REFERENCES `mesa` (`idMesa`),
  CONSTRAINT `OrdenFK4` FOREIGN KEY (`idMetodoPago`) REFERENCES `metodopago` (`idMetodoPago`),
  CONSTRAINT `OrdenCH1` CHECK (`precioOrden` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT INTO `orden` (`idOrden`, `idCte`, `idEmpl`, `idMesa`, `precioOrden`, `fechHora`, `idMetodoPago`) VALUES (1,1,1,1,450.00,'2025-04-27 18:40:45',NULL),
(2,1,2,1,300.00,'2025-05-25 21:44:40',NULL),
(51,1,2,1,270.00,'2025-05-25 22:08:23',NULL),
(53,1,2,1,270.00,'2025-05-25 22:09:35',NULL),
(54,1,2,1,270.00,'2025-05-25 22:11:18',NULL),
(55,1,2,1,270.00,'2025-05-25 22:15:24',NULL),
(56,1,2,1,270.00,'2025-05-25 22:15:47',NULL),
(57,1,2,1,300.00,'2025-05-25 22:15:58',NULL),
(58,1,2,1,300.00,'2025-05-25 22:16:03',NULL),
(59,1,2,1,75.00,'2025-05-25 22:35:47',NULL),
(61,1,2,1,82.00,'2025-05-25 22:42:39',NULL),
(62,1,2,1,25.00,'2025-05-25 22:49:28',NULL),
(63,1,2,1,50.00,'2025-05-25 22:50:55',NULL),
(64,1,2,1,220.00,'2025-05-25 23:13:45',NULL),
(66,1,2,1,155.00,'2025-05-25 23:22:49',NULL),
(67,1,2,1,305.00,'2025-05-25 23:25:09',NULL),
(68,1,2,1,133.00,'2025-05-25 23:30:41',NULL),
(69,1,2,1,283.00,'2025-05-25 23:30:41',NULL),
(70,1,2,1,220.00,'2025-05-25 23:33:44',NULL),
(71,1,2,1,193.00,'2025-05-25 23:33:44',NULL),
(72,1,2,1,343.00,'2025-05-25 23:36:15',NULL),
(73,1,2,1,336.00,'2025-05-25 23:37:13',NULL),
(74,1,2,1,275.00,'2025-05-25 23:37:56',NULL),
(75,1,2,1,400.00,'2025-05-25 23:44:57',NULL),
(76,1,2,1,106.00,'2025-05-26 00:06:16',NULL),
(77,1,2,1,251.00,'2025-05-26 00:07:22',NULL),
(78,1,2,1,450.00,'2025-05-26 09:51:10',NULL),
(79,1,2,2,143.00,'2025-05-26 09:51:38',NULL),
(80,1,2,1,108.00,'2025-05-26 09:56:43',NULL),
(81,1,2,1,168.00,'2025-05-26 12:12:54',NULL),
(82,2,2,1,83.00,'2025-05-26 12:19:02',NULL),
(83,1,2,1,133.00,'2025-05-26 12:31:30',NULL),
(85,2,2,2,25.00,'2025-05-26 12:38:06',NULL),
(86,2,2,2,55.00,'2025-05-26 12:40:36',NULL),
(87,1,2,1,127.00,'2025-05-26 12:40:55',NULL),
(88,2,2,2,25.00,'2025-05-26 12:50:52',NULL),
(89,2,2,2,30.00,'2025-05-26 12:51:02',NULL),
(90,2,2,2,156.00,'2025-05-26 12:51:28',NULL),
(91,1,2,1,213.00,'2025-05-26 12:55:45',NULL),
(92,1,2,1,441.00,'2025-06-02 04:57:28',1),
(95,1,2,1,75.00,'2025-06-02 04:58:13',1),
(96,1,2,1,220.00,'2025-06-02 04:58:23',1),
(97,1,2,1,155.00,'2025-06-02 05:10:47',1),
(98,1,2,1,225.00,'2025-06-02 05:10:57',1),
(99,1,2,1,220.00,'2025-06-02 05:11:58',1),
(100,1,2,1,155.00,'2025-06-02 05:12:48',1),
(101,1,2,1,155.00,'2025-06-02 05:16:03',1),
(102,1,2,1,125.00,'2025-06-02 05:16:21',1),
(103,1,2,1,153.00,'2025-06-02 12:53:28',1);
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL AUTO_INCREMENT,
  `nomProd` varchar(100) NOT NULL,
  `precioProd` decimal(10,2) DEFAULT NULL,
  `costoProd` decimal(10,2) DEFAULT NULL,
  `UrlImagenProd` varchar(200) NOT NULL,
  `idCategoria` int(11) NOT NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE KEY `UrlImagenProd` (`UrlImagenProd`),
  KEY `ProductoFK1` (`idCategoria`),
  CONSTRAINT `ProductoFK1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`),
  CONSTRAINT `ProductoCH1` CHECK (`precioProd` > `costoProd`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`idProducto`, `nomProd`, `precioProd`, `costoProd`, `UrlImagenProd`, `idCategoria`) VALUES (4,'Alitas',75.00,40.00,'alitas.jpg',1),
(5,'Burrito',80.00,45.00,'burrito.jpg',1),
(6,'Caldo',65.00,35.00,'caldo.jpg',1),
(7,'Ceviche',85.00,50.00,'ceviche.jpg',1),
(8,'Enchiladas',78.00,42.00,'enchiladas.jpg',1),
(9,'Ensalada',60.00,30.00,'ensalada.jpg',1),
(10,'Hamburguesa',90.00,50.00,'hamburguesa.jpg',1),
(11,'Nachos',55.00,25.00,'nachos.jpg',1),
(12,'Pasta',82.00,37.00,'pasta.jpg',1),
(13,'Pizza',95.00,55.00,'pizza.jpg',1),
(14,'Quesadillas',60.00,30.00,'quesadillas.jpg',1),
(15,'Sopa Tarasca',45.00,20.00,'sopaT.jpg',1),
(16,'Tacos',70.00,35.00,'tacos.jpg',1),
(17,'Tamales',40.00,18.00,'tamales.jpg',1),
(18,'Tostadas',50.00,25.00,'tostadas.jpg',1),
(19,'Agua de Jamaica',25.00,10.00,'aguaJamaica.jpg',2),
(20,'Café',30.00,12.00,'cafe.jpg',2),
(21,'Limonada',28.00,12.00,'limonada.jpg',2),
(22,'Michelada',50.00,25.00,'michelada.jpg',2),
(23,'Refresco',22.00,10.00,'refresco.jpg',2);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `nomProv` varchar(100) NOT NULL,
  `descripcionProv` text DEFAULT NULL,
  `emailProv` varchar(50) DEFAULT NULL,
  `direccionProv` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idProveedor`),
  UNIQUE KEY `emailProv` (`emailProv`),
  UNIQUE KEY `direccionProv` (`direccionProv`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`idProveedor`, `nomProv`, `descripcionProv`, `emailProv`, `direccionProv`) VALUES (1,'Distribuidora de Alimentos','Proveedor de insumos alimenticios.','proveedor1@example.com','Calle 100, CDMX'),
(2,'Bebidas y Más','Proveedor de bebidas.','proveedor2@example.com','Avenida Central 200, CDMX');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puesto`
--

DROP TABLE IF EXISTS `puesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `puesto` (
  `idPuesto` int(11) NOT NULL AUTO_INCREMENT,
  `nomPuesto` varchar(100) DEFAULT NULL,
  `sueldoPuesto` decimal(10,2) NOT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`idPuesto`),
  CONSTRAINT `PuestoCH1` CHECK (`sueldoPuesto` > 278.80)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puesto`
--

LOCK TABLES `puesto` WRITE;
/*!40000 ALTER TABLE `puesto` DISABLE KEYS */;
INSERT INTO `puesto` (`idPuesto`, `nomPuesto`, `sueldoPuesto`, `descripcion`) VALUES (1,'Mesero',300.00,'Atiende a los clientes en las mesas.'),
(2,'Chef',500.00,'Prepara los alimentos en la cocina.');
/*!40000 ALTER TABLE `puesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservacion`
--

DROP TABLE IF EXISTS `reservacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservacion` (
  `idReservacion` int(11) NOT NULL AUTO_INCREMENT,
  `duracionRese` int(11) NOT NULL,
  `horarioFechRese` datetime NOT NULL,
  `idCte` int(11) DEFAULT NULL,
  PRIMARY KEY (`idReservacion`),
  KEY `ReservacionFK1` (`idCte`),
  CONSTRAINT `ReservacionFK1` FOREIGN KEY (`idCte`) REFERENCES `clientes` (`idCte`),
  CONSTRAINT `ReservacionCH1` CHECK (`duracionRese` >= 1)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservacion`
--

LOCK TABLES `reservacion` WRITE;
/*!40000 ALTER TABLE `reservacion` DISABLE KEYS */;
INSERT INTO `reservacion` (`idReservacion`, `duracionRese`, `horarioFechRese`, `idCte`) VALUES (1,2,'2025-05-01 19:00:00',1),
(2,3,'2025-05-02 20:00:00',2),
(3,3,'2025-05-28 00:00:00',1),
(4,3,'2025-05-28 00:00:00',1),
(5,3,'2025-05-28 00:00:00',1),
(6,3,'2025-05-28 00:00:00',1),
(7,3,'2025-05-28 00:00:00',1),
(8,3,'2025-05-28 00:00:00',1),
(9,3,'2025-05-28 00:00:00',1),
(10,3,'2025-05-28 00:00:00',1),
(11,3,'2025-05-29 00:00:00',1),
(12,5,'2025-05-30 00:00:00',1),
(13,7,'2025-05-29 00:00:00',2),
(14,3,'2025-05-30 14:26:00',1),
(15,2,'2025-05-05 14:25:00',1);
/*!40000 ALTER TABLE `reservacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservacionmesa`
--

DROP TABLE IF EXISTS `reservacionmesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservacionmesa` (
  `idMesa` int(11) NOT NULL,
  `idReservacion` int(11) NOT NULL,
  PRIMARY KEY (`idMesa`,`idReservacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservacionmesa`
--

LOCK TABLES `reservacionmesa` WRITE;
/*!40000 ALTER TABLE `reservacionmesa` DISABLE KEYS */;
INSERT INTO `reservacionmesa` (`idMesa`, `idReservacion`) VALUES (1,1),
(2,2);
/*!40000 ALTER TABLE `reservacionmesa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-06-03  2:49:23

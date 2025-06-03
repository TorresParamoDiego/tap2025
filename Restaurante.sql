create database restaurante;
use restaurante;

CREATE TABLE Clientes(
                         idCte int auto_increment,
                         nomCte varchar(100),
                         telCte char(10),
                         direccion varchar(100),
                         emailCte varchar(50) unique,
                         CONSTRAINT ClientePK PRIMARY KEY(idCte)
);
create table Puesto(
                       idPuesto int auto_increment,
                       nomPuesto varchar(100),
                       sueldoPuesto Decimal(10,2) not null,
                       descripcion text,
                       constraint PuestoPK primary key(idPuesto),
                       constraint PuestoCH1 check (sueldoPuesto>278.80)
);
create table Empleado(
                         idEmpl int auto_increment,
                         nomEmpl varchar(100),
                         RFCEmpl char(13) not null unique,
                         CurpEmpl char(18) not null unique,
                         nssEmpl char(11) not null unique,
                         horarioEntradaEmpl Time,
                         horarioSalidaEmpl Time,
                         fechIngresoEmpl Date not null,
                         telEmpl char(10),
                         idPuesto int,
                         password varchar(96),
                         constraint EmpleadoPK primary key(idEmpl),
                         constraint EmpleadoFK1 foreign key (idPuesto) references Puesto(idPuesto)
);
create table Reservacion (
                             idReservacion int auto_increment,
                             duracionRese int not null,
                             horarioFechRese Datetime not null,
                             idCte int,
                             constraint ReservacionPK primary key (idReservacion),
                             constraint ReservacionFK1 foreign key (idCte) references Clientes(idCte),
                             constraint ReservacionCH1 check (duracionRese >= 1)
);
create table Mesa(
                     idMesa int auto_increment,
                     capacidadMesa int,
                     constraint MesaPK primary key (idMesa),
                     constraint MesaCH1 check(capacidadMesa >= 3)
);
create table ReservacionMesa(
                                idMesa int,
                                idReservacion int,
                                constraint ReservacionMesaPK primary key (idMesa,idReservacion),
                                constraint ReservacionMesaFK1 foreign key (idMesa) references Mesa(idMesa),
                                constraint ReservacionMesaFK2 foreign key (idReservacion) references Reservacion(idReservacion)
);
create table Orden(
                      idOrden int auto_increment,
                      idCte int,
                      idEmpl int,
                      idMesa int,
                      precioOrden Decimal(10,2),
                      fechHora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      idMetodoPago int,
                      constraint OrdenPK primary key (idOrden),
                      constraint OrdenFK1 foreign key (idCte) references Clientes(idCte),
                      constraint OrdenFK2 foreign key (idEmpl) references Empleado(idEmpl),
                      constraint OrdenFK3 foreign key (idMesa) references Mesa(idMesa),
                      CONSTRAINT OrdenFK4 FOREIGN KEY (idMetodoPago) REFERENCES MetodoPago(idMetodoPago),
                      constraint OrdenCH1 check (precioOrden>0)
);
create table Proveedor(
                          idProveedor int auto_increment,
                          nomProv varchar(100) not null,
                          descripcionProv Text,
                          emailProv varchar(50) unique,
                          direccionProv varchar(100) unique,
                          constraint ProveedorPK primary key (idProveedor)
);
create table Insumo(
                       idInsumo int auto_increment,
                       nomIns varchar(100),
                       precioIns Decimal(10,2) not null,
                       cantidad int,
                       idProveedor int,
                       constraint InsumoPK primary key (idInsumo),
                       constraint InsumoFK1 foreign key (idProveedor) references Proveedor(idProveedor),
                       constraint InsumoCH1 check (precioIns>0),
                       constraint InsumoCH2 check (cantidad>0)
);
create table Categoria(
                          idCategoria int auto_increment,
                          nomCategoria varchar(100),
                          descripcionCategoria Text,
                          constraint CategoriaPK primary key (idCategoria)
);
create table Producto(
                         idProducto int auto_increment,
                         nomProd varchar(100) not null,
                         precioProd Decimal(10,2),
                         costoProd Decimal(10,2),
                         UrlImagenProd varchar(200) not null unique,
                         idCategoria int not null,
                         constraint ProductoPK primary key (idProducto),
                         constraint ProductoFK1 foreign key (idCategoria) references Categoria(idCategoria),
                         constraint ProductoCH1 check (precioProd>costoProd)
);
create table DetalleOrden(
                             idOrden int,
                             idProducto int,
                             cantidad int,
                             constraint DetalleOrdenPK primary key (idOrden,idProducto),
                             constraint DetalleOrdenFK1 foreign key (idOrden) references Orden(idOrden),
                             constraint DetalleOrdenFK2 foreign key (idProducto) references Producto(idProducto),
                             constraint DetalleOrdenCH check (cantidad>0)
);
create table DetalleProducto(
                                idInsumo int,
                                idProducto int,
                                constraint DetalleProductoPK primary key (idInsumo,idProducto),
                                constraint DetalleProductoFK1 foreign key (idInsumo) references Insumo(idInsumo),
                                constraint DetalleProductoFK2 foreign key (idProducto) references Producto(idProducto)
);
create table CompraInsumos(
                              idCompra int,
                              cantidadInsumo int,
                              idInsumo int,
                              fechCompra Date,
                              costoCompra Decimal(10,2),
                              constraint CompraInsumosPK primary key (idCompra),
                              constraint CompraInsumosFK1 foreign key (idInsumo) references Insumo(idInsumo),
                              constraint CompraInsumosCH1 check (cantidadInsumo>0)
);
create table MetodoPago(
    idMetodoPago int auto_increment,
    tipoMetodo varchar(50),
    descripcion text,
    constraint MetodoPagoPK primary key(idMetodoPago)
);

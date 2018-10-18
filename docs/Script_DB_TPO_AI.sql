CREATE DATABASE TPO_AI
GO

CREATE TABLE usuarios
(
	idUsuario INT IDENTITY,
	username VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	fechaBaja DATETIME NULL
	CONSTRAINT username_pk PRIMARY KEY (username)
)
GO

CREATE TABLE clientes
(
	idCliente INT IDENTITY,
	nombre VARCHAR(25) NOT NULL,
	domicilio VARCHAR(25) NOT NULL,
	telefono VARCHAR(25) NOT NULL,
	mail VARCHAR(25) NOT NULL
	CONSTRAINT mail_pk PRIMARY KEY (mail)
)
GO

	private int nroFactura;
	private Date fechaFactura;
	private float montoTotal;
	private static float IVA;
	private List <ItemFactura> itemFactura;
	
CREATE TABLE facturas
(
	nroFactura INT NOT NULL,
	fechaFactura DATETIME NOT NULL,
	montoTotal FLOAT NOT NULL,
	IVA FLOAT NOT NULL,
	CONSTRAINT nroFactura_pk PRIMARY KEY (nroFactura)
)
GO

CREATE TABLE itemFactura
(
	idItemFactura INT IDENTITY,
	montoItem FLOAT NOT NULL,
	cantidad INT NOT NULL,
	nroFactura INT NOT NULL,
	CONSTRAINT nroFactura_fk FOREIGN KEY (nroFactura) REFERENCES facturas
)
GO

CREATE TABLE productos
(
	idProducto INT IDENTITY,
	codigo INT NOT NULL,
	titulo VARCHAR(50) NOT NULL,
	Descripcion VARCHAR(100) NOT NULL,
	precio FLOAT NOT NULL,
	CONSTRAINT codigo_pk PRIMARY KEY (codigo)
)
GO

CREATE TABLE tipoReclamo
(
	idTipoReclamo INT IDENTITY,
	TipoReclamo CHAR(20) NOT NULL
	CONSTRAINT tiporeclamo_pk PRIMARY KEY (TipoReclamo)
)
GO

CREATE TABLE reclamo_comp
(
	nroReclamo INT IDENTITY,
	fecha DATETIME NOT NULL,
	fechaCierre DATETIME NOT NULL,
	idCliente INT NOT NULL,
	Descripcion VARCHAR(100) NOT NULL, 
	estado CHAR(20) NOT NULL,
	idProducto INT NOT NULL,
	tipoDeReclamo INT NOT NULL,
	CONSTRAINT nroReclamo_pk PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE reclamo_simp
(
	nroReclamo INT IDENTITY,
	fecha DATETIME NOT NULL,
	fechaCierre DATETIME NOT NULL,
	idCliente INT NOT NULL,
	Descripcion VARCHAR(100) NOT NULL, 
	estado CHAR(20) NOT NULL,
	idProducto INT NOT NULL,
	tipoDeReclamo INT NOT NULL,
	reclamoComp INT NULL,
	CONSTRAINT nroReclamosimp_pk PRIMARY KEY (nroReclamo),
	CONSTRAINT reclamoComp_fk FOREIGN KEY (nroReclamo) REFERENCES reclamo_comp
)
GO


-- DROP DATABASE TPO_AI;
CREATE DATABASE TPO_AI;

USE TPO_AI;

CREATE TABLE roles
(
	idrol INT IDENTITY,
	descripcion VARCHAR(25) NOT NULL, 
	CONSTRAINT rol_pk PRIMARY KEY (idrol),
	CONSTRAINT uc_desc UNIQUE (descripcion)
)


CREATE TABLE usuarios
(
	idusuario INT IDENTITY,
	username VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	fechabaja DATETIME NULL,
	idrol INT,
	CONSTRAINT username_pk PRIMARY KEY (idusuario),
	CONSTRAINT rol_fk FOREIGN KEY (idrol) REFERENCES roles,
	CONSTRAINT uc_username UNIQUE (username)
)


CREATE TABLE tiposdereclamoporroles
(
	idrol INT NOT NULL,
	idtipodereclamo INT NOT NULL,	
	CONSTRAINT rol_fk_tr FOREIGN KEY (idrol) REFERENCES roles,
	CONSTRAINT rr_unique  UNIQUE (idrol, idtipodereclamo)
)

CREATE TABLE clientes
(
	idcliente INT IDENTITY,
	nombre VARCHAR(25) NOT NULL,
	cuit VARCHAR(25) NOT NULL,
	domicilio VARCHAR(25) NOT NULL,
	telefono VARCHAR(25) NOT NULL,
	mail VARCHAR(25) NOT NULL,
	fechabaja DATETIME NULL,
	CONSTRAINT idcliente_pk PRIMARY KEY (idcliente),
	CONSTRAINT cuit_unique UNIQUE (cuit)
)

CREATE TABLE productos
(
	idproducto INT IDENTITY,
	codigo VARCHAR(25) NOT NULL,
	titulo VARCHAR(50) NOT NULL,
	descripcion VARCHAR(100) NOT NULL,
	precio FLOAT NOT NULL,
	CONSTRAINT codigo_pk PRIMARY KEY (idproducto),
	CONSTRAINT uc_codigo UNIQUE (codigo)
)


CREATE TABLE facturas
(
	nrofactura INT IDENTITY,
	fechafactura DATETIME NOT NULL,
	idcliente INT NOT NULL,
	CONSTRAINT nroFactura_pk PRIMARY KEY (nrofactura),
	CONSTRAINT idCliente_fk FOREIGN KEY (idcliente) REFERENCES clientes
)
GO

CREATE TABLE itemsfacturas
(
	iditemfactura INT IDENTITY,
	idproducto INT NOT NULL,
	montoitem FLOAT NOT NULL,
	cantidad INT NOT NULL,
	nrofactura INT NOT NULL,
	CONSTRAINT nroFactura_fk FOREIGN KEY (nrofactura) REFERENCES facturas,
	CONSTRAINT codigo_fk FOREIGN KEY (idproducto) REFERENCES productos
)

CREATE TABLE reclamoscompuestos
(
	nroreclamo INT IDENTITY,
	descripcion VARCHAR(100) NOT NULL, 
	idestadoreclamo INT NOT NULL,
	idtiporeclamo INT NOT NULL,
	fecha DATETIME NOT NULL,
	fechacierre DATETIME,
	idcliente INT NOT NULL,	
	CONSTRAINT nroreclamocompuesto_pk PRIMARY KEY (nroreclamo)
)

CREATE TABLE reclamos
(
	nroreclamo INT IDENTITY,
	descripcion VARCHAR(100) NOT NULL, 
	idestadoreclamo INT NOT NULL,
	idtiporeclamo INT NOT NULL,
	fecha DATETIME NOT NULL,
	fechacierre DATETIME NULL,
	idcliente INT NOT NULL,
	idproducto INT NULL,
	cantidad INT NULL,
	zona VARCHAR(50) NULL,
	nroreclamocompuesto INT NULL,	
	CONSTRAINT nroreclamo_pk PRIMARY KEY (nroreclamo),
	CONSTRAINT reclamocomp_fk FOREIGN KEY (nroreclamocompuesto) REFERENCES reclamoscompuestos (nroreclamo)
)

CREATE TABLE facturasreclamos
(
	nrofactura INT NOT NULL,
	nroreclamo INT NOT NULL,
	CONSTRAINT nroFactura_fr_fk FOREIGN KEY (nrofactura) REFERENCES facturas (nrofactura),
	CONSTRAINT nroReclamo_fr_fk FOREIGN KEY (nroreclamo) REFERENCES reclamos (nroreclamo)
)

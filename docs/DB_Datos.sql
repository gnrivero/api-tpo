USE TPO_AI;

-- SELECT SCOPE_IDENTITY() AS [SCOPE_IDENTITY];

-- Roles
INSERT INTO roles (descripcion) VALUES ('Admin');
INSERT INTO roles (descripcion) VALUES ('Responsable Facturacion');
INSERT INTO roles (descripcion) VALUES ('Responsable Distribucion');
INSERT INTO roles (descripcion) VALUES ('Responsable Zona Entrega');
INSERT INTO roles (descripcion) VALUES ('Call Center');
INSERT INTO roles (descripcion) VALUES ('Consulta');

select * from roles order by idrol;

-- Usuarios
INSERT INTO usuarios (username, password, idrol) VALUES ('grivero', '123456',1);
INSERT INTO usuarios (username, password, idrol) VALUES ('edelgado', '123456',1);
INSERT INTO usuarios (username, password, idrol) VALUES ('mquevedo', '116785',1);
INSERT INTO usuarios (username, password, idrol) VALUES ('curioso', '000', 6);
select * from usuarios;

-- Permisos por rol 
-- INSERT INTO permisos (idrol, modulo, valor) VALUES (

select * from permisos;

-- Permisos Admin
INSERT INTO permisos (idrol, modulo, valor) VALUES (1,'RECLAMO', 6);

-- Permisos Call Center
INSERT INTO permisos (idrol, modulo, valor) 
VALUES (5,'RECLAMO', 6),
(5,'CREAR_RECLAMO',2);

-- Permisos Distribucion
INSERT INTO permisos (idrol, modulo, valor)  
VALUES (3,'RECLAMO', 2),
(3,'RECLAMO', 3),
(3,'RECLAMO', 4);

-- Permisos Facturacion
INSERT INTO permisos (idrol, modulo, valor)  VALUES (3,'RECLAMO',5);

-- Permisos Zona
INSERT INTO permisos (idrol, modulo, valor) VALUES (4,'RECLAMO',1);

-- Permisos Consulta
INSERT INTO permisos (idrol, modulo, valor) VALUES (6,'REPORTES', 4);

select * from permisos;

-- Productos
INSERT INTO productos (codigo, titulo, descripcion, precio)
VALUES ('1', 'Plumero', 'Pluma de pavo', 78),
('2', 'Escoba', 'De paja', 34),
('3', 'Tacho', 'De aluminio 50L', 500);

select * from productos;

-- Clientes
select * from clientes;

INSERT INTO clientes (nombre, cuit, domicilio, telefono, mail)
VALUES('Fravega S.A.', '20-31912399-2',	'Av. Scalabrini Ortiz 7500', '555-6666', 'fravega@mail.com'),
('Garbarino Hermanos', '20-33104999-2',	'Av. Del Libertador',	'47779999',	'garbarino@gmail.com');


SELECT SCOPE_IDENTITY() AS [SCOPE_IDENTITY];

-- Reclamos Hoja/Simples
select * from reclamos;

-- Reclamos Compuestos
select * from reclamoscompuestos

-- Factura
select * from facturas;
select * from itemsfacturas;

INSERT INTO facturas (fechafactura, idcliente) VALUES ('2018-11-01 14:04:33', 1);
INSERT INTO facturas (fechafactura, idcliente) VALUES ('2018-11-05 12:01:33', 1);
INSERT INTO facturas (fechafactura, idcliente) VALUES ('2018-11-07 13:01:33', 1);


INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (2, 34, 2, 2),
(3, 500, 1, 2);

INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (3, 500, 1, 3);

select * from itemsfacturas; 

-- Productos

select * from productos;


-- Reclamos
SELECT * 
FROM reclamos rs
	
WHERE idtiporeclamo  IN (1,2,3,4,5,6)
select * from reclamos;

SELECT * FROM reclamos;

-- Facturas por Reclamo
select * from facturasreclamos;

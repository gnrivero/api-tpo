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
INSERT INTO usuarios (username, password, idrol) VALUES ('curioso', '000', 6);
select * from usuarios;

-- Tipos de reclamos por roles


-- Permisos Call Center
INSERT INTO tiposdereclamoporroles VALUES (5, 6);

-- Permisos Distribucion
INSERT INTO tiposdereclamoporroles 
VALUES (3, 2),
(3, 3),
(3, 4);

TRUNCATE table tiposdereclamoporroles;

INSERT INTO tiposdereclamoporroles VALUES 
(2, 5),
(3, 1),
(3, 2),
(3, 3),
(4, 4),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(6, 5),
(6, 6);


select * from tiposdereclamoporroles;

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

select * from itemsfacturas; 

INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (3, 500, 1, 3);

-- Productos

select * from productos;


-- Reclamos
SELECT * 
FROM reclamos rs
	
WHERE idtiporeclamo  IN (1,2,3,4,5,6)
select * from reclamos;


SELECT * FROM reclamos;


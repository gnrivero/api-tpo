use tempdb;

USE TPO_AI



-- Roles
INSERT INTO roles (descripcion) VALUES ('Admin');
INSERT INTO roles (descripcion) VALUES ('Responsable Facturacion');
INSERT INTO roles (descripcion) VALUES ('Responsable Distribucion');
INSERT INTO roles (descripcion) VALUES ('Responsable Zona Entrega');
INSERT INTO roles (descripcion) VALUES ('Call Center');
INSERT INTO roles (descripcion) VALUES ('Consulta');

select * from roles;

DELETE FROM roles where idrol > 6;

-- Usuarios
INSERT INTO usuarios (username, password, idrol) VALUES ('grivero', '123456',1);

select * from usuarios;

-- Tipos de reclamos por roles

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
(6, 6)
;

select * from tiposdereclamoporroles;



-- SELECT SCOPE_IDENTITY() AS [SCOPE_IDENTITY];

SELECT * FROM roles;


-- Usuarios
INSERT INTO usuarios (username, password, idrol) VALUES ('gerentedistribucion', '123456', 3); 

SELECT * FROM usuarios;

-- Productos
select * from productos;

-- Clientes
select * from clientes;

INSERT INTO clientes (nombre, cuit, domicilio, telefono, mail)
VALUES('Fravega S.A.', '20-31912399-2',	'Av. Scalabrini Ortiz 7500', '555-6666', 'fravega@mail.com'),
('Garbarino Hermanos', '20-33104999-2',	'Av. Del Libertador',	'47779999',	'garbarino@gmail.com');


-- Reclamos Hoja/Simples
select * from reclamos;

-- Reclamos Compuestos
select * from reclamoscompuestos

-- Factura
select * from facturas;
select * from itemsfacturas;

truncate table facturas;

INSERT INTO facturas (fechafactura, idcliente) VALUES ('2018-11-01 14:04:33', 1);
INSERT INTO facturas (fechafactura, idcliente) VALUES ('2018-11-05 12:01:33', 1);

truncate table itemsfacturas;
INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (2, 78, 2, 2),
(3, 500, 1, 2);

INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (3, 500, 1, 3);


select * from roles;




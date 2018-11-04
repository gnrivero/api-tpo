USE TPO_AI

-- Roles
INSERT INTO roles (descripcion) VALUES ('Admin');

INSERT INTO roles (descripcion) VALUES ('Responsable Facturacion');
SELECT SCOPE_IDENTITY() AS [SCOPE_IDENTITY];

INSERT INTO roles (descripcion) VALUES ('Responsable Distribucion');
INSERT INTO roles (descripcion) VALUES ('Responsable Zona Entrega');
INSERT INTO roles (descripcion) VALUES ('Consulta');

SELECT * FROM roles;


-- Usuarios
INSERT INTO usuarios (username, password, idrol) VALUES ('gerentedistribucion', '123456', 3); 

SELECT * FROM usuarios;

-- Productos
select * from productos;

-- Clientes
INSERT INTO clientes (nombre, domicilio, telefono, mail) 
VALUES ('ClienteDePrueba',  'Cabrera 5400',  '4700-0000',  'prueba@mock.com')

select * from clientes;

INSERT INTO reclamos (descripcion, idtiporeclamo, idestadoreclamo, fecha, idcliente, idproducto, cantidad) 
VALUES ('Me piden detergente', 3, 1, '2018-10-30 21:28:58', 1, 1, 2)



-- Reclamos Hoja/Simples
select * from reclamos;

-- Reclamos Compuestos
select * from reclamoscompuestos


-- Factura
select * from facturas;
select * from itemsfacturas;

INSERT INTO facturas (fechafactura) VALUES (CURRENT_TIMESTAMP);

INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (2, 78, 2, 2),
(3, 500, 1, 2)

INSERT INTO itemsfacturas (idproducto, montoitem, cantidad, nrofactura) 
VALUES (3, 500, 1, 3)
;




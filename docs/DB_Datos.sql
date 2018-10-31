USE TPO_AI

-- Roles
INSERT INTO roles (descripcion) VALUES ('Admin');
INSERT INTO roles (descripcion) VALUES ('Responsable Facturacion');
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

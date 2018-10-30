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


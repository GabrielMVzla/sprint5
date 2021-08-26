INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'gabriel', 'Montes', 'Rios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Jose', 'Valenzuela', 'Lirios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Rey', 'Perez', 'Cucas');
--INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luis', 'Lopez', 'Loro' );
--INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luz', 'Pena', 'Centro');



INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('gabriel', '$2a$10$lmLRIrGU8gRAEf/TJAPnnug62VPk.KSiWj4dbXL.UyRfRsKTDMi7K', true, 'Gabriel', 'Montes', 'correo@correo.com');
INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('admin', '$2a$10$Nvj13630BZLJHBtPgNpAEOhGSI8pRMgxxL1ZAYWeilUowXuFQaniW', true, 'José', 'Valenzuela', 'email@email.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (1, 1);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (2, 2);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (2, 1);
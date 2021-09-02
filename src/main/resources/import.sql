INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'gabriel', 'Montes', 'Rios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Jose', 'Valenzuela', 'Lirios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Rey', 'Perez', 'Cucas');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luis', 'Lopez', 'Loro' );
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luz', 'Pena', 'Centro');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Angel', 'Lopez', 'Loro' );
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Chocotorro', 'Pena', 'Centro');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'gabriel', 'Montes', 'Rios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Jose', 'Valenzuela', 'Lirios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Rey', 'Perez', 'Cucas');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luis', 'Lopez', 'Loro' );
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luz', 'Pena', 'Centro');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Angel', 'Lopez', 'Loro' );
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Chocotorro', 'Pena', 'Centro');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'gabriel', 'Montes', 'Rios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Jose', 'Valenzuela', 'Lirios');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Rey', 'Perez', 'Cucas');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luis', 'Lopez', 'Loro' );
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Luz', 'Pena', 'Centro');
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Angel', 'Lopez', 'Loro' );
INSERT INTO clientes ( nombres, apellidos, direccion) VALUES ( 'Chocotorro', 'Pena', 'Centro');

INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('gabriel', '$2a$10$lmLRIrGU8gRAEf/TJAPnnug62VPk.KSiWj4dbXL.UyRfRsKTDMi7K', true, 'Gabriel', 'Montes', 'gabriel@correo.com');
INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('luis', '$2a$10$o0Am05dUujlUt2K3qiFW0O8SRmOCGQwf5NcyL6bGRlUfDucIZiJSO', true, 'Luis', 'Jara', 'luis@email.com');
INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('adiel', '$2a$10$R/TaIoQSuk4QKzrq7c9GguvgOt0JDAeU5XjIZJMEvbeFLT2.LFXk2', true, 'Adiel', 'Lara', 'adiel@email.com');
INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('hugo', '$2a$10$3SEw3.MlSdwAe6p3fclMPukcyxSdEaGfXjRR/6gQ5IE2maInBIo3G', true, 'Hugo', 'Garza', 'hugo@email.com');
INSERT INTO usuarios (username, password, enabled, nombres, apellidos, email) VALUES ('admin', '$2a$10$Nvj13630BZLJHBtPgNpAEOhGSI8pRMgxxL1ZAYWeilUowXuFQaniW', true, 'José', 'Valenzuela', 'email@email.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (1, 1);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (2, 1);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (3, 1);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (4, 1);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (5, 2);
INSERT INTO usuarios_roles (id_usuario_fk, id_role_fk) VALUES (5, 1);
-- insertamos roles
insert into roles( nombre, descripcion, estado) values ( 'ROLE_ADMIN', 'Tiene acceso a todos los modulos del sistema', true);
insert into roles( nombre, descripcion, estado) values ( 'ROLE_VENTA', 'Tene acceso solo al módulo e ventas', true);
insert into roles( nombre, descripcion, estado) values ( 'ROLE_SERVICIO', 'Tiene acceso solo al módulo de servicios', true);

INSERT INTO usuarios (nombre, password, estado, email) VALUES ('admin', '$2a$10$Zdw/FqSgfRV7oJ34gDONPO0ACv9lO87eMsfWN.mLSKgWeNNX7xady',true, 'guty@gmail.com'); 

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1,1);

insert into sexos(tipo, estado) values ('hombre', true);
insert into sexos(tipo, estado) values ('mujer', true);
insert into sexos(tipo, estado) values ('otro', true);

-- insertamos un cliente
-- insert into clientes( apellido, cedula, estado, nombre, telefono, email, direccion_id, sexo_id) values ('gutierrez', '0105566046', true, 'javier', '0987535645', 'javig@gmail.com', )

-- insertamos en la matriz datos
insert into matriz( nombre, telefono, ruc, direccion, email, logo) values ( 'LabHidro', '0987535645', '0105566046', 'checa', 'labhidro@gmail.com', 'logo.png');

-- insertar en los tipos de servicios
insert into tipo_servicios(estado, descripcion, nombre) values (true, 'Realiza pruebas  al agua', 'Pruebas químicas');
insert into tipo_servicios(estado, descripcion, nombre) values (true, 'Realiza mediciones del agua', 'Mediciones');
insert into tipo_servicios(estado, descripcion, nombre) values (true, 'Realiza servicios básicos', 'Servicios generales');

-- inserat servicos
insert into servicios( disponible, descripcion, nombre, img, tipo_servicio_id) values ( true, 'Revisa el agua de rigeo','Revisión de sistemas de riego', 'img.png', 2);
insert into servicios( disponible, descripcion, nombre, img, tipo_servicio_id) values ( true, 'Revisa el riesgo de desbordamientos','Predicción de caudal', 'img.png', 3);
insert into servicios( disponible, descripcion, nombre, img, tipo_servicio_id) values ( true, 'estudios de calidad de agua','Estudios de calidad de agua', 'img.png', 2);

-- insertamos u paquete de servicios
insert into servicio_item(servicio_id) values (1);
insert into servicio_item(servicio_id) values (3);
insert into paquetes_servicios (descripcion, disponible , nombre, precio, img) values('Paquete básico', true, 'Calidad y riego', 200, 'img.png');
insert into paquetes_servicios_items_paquete (paquete_servicio_id, items_paquete_id) values (1,1);
insert into paquetes_servicios_items_paquete (paquete_servicio_id, items_paquete_id) values (1,2);


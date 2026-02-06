Drop database if exists DBRepuestosAutomotriz_in5cm;
create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;

create table Proveedores(
	id_proveedor int auto_increment not null,
	nombre_proveedor varchar(60) not null,
	telefono_proveedor int not null,
	direccion varchar(100) not null,
	email_proveedor varchar(100) not null,
	primary key PK_id_proveedor(id_proveedor)
);

create table Empleados(
	id_empleado int auto_increment not null,
	nombre_empleado varchar(60) not null,
	apellido_empleado varchar(60) not null,
	puesto_empleado varchar(20) not null,
	email_empleado varchar(100) not null,
	primary key PK_id_empleado(id_empleado)
);

create table Repuestos(
	id_repuesto int auto_increment not null,
	nombre_repuesto varchar(60) not null,
	categoria_repuesto varchar(60) not null,
	precio_compra double not null,
	precio_venta double not null,
	id_proveedor int not null,
	primary key PK_id_repuesto(id_repuesto),
	constraint FK_repuesto_proveedor foreign key (id_proveedor)
	references proveedores(id_proveedor) on delete cascade
);

create table Ventas(
	id_venta int auto_increment not null,
	fecha_venta date not null,
	cantidad int not null,
	total double not null,
	id_empleado int not null,
	id_repuesto int not null,
	primary key PK_id_venta(id_venta),
	constraint FK_ventas_empleado foreign key (id_empleado)
	references Empleados(id_empleado) on delete cascade,
	constraint FK_ventas_repuestos foreign key (id_repuesto)
	references Repuestos(id_repuesto) on delete cascade
);

-- procedimientos almacenados
delimiter $$
create procedure sp_insertar_proveedores(
	in p_nombre_proveedor varchar(60),
	in p_telefono_proveedor int,
	in p_direccion varchar(100),
	in p_email_proveedor varchar(100)
)
begin
	insert into Proveedores (nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
	values (p_nombre_proveedor, p_telefono_proveedor, p_direccion, p_email_proveedor);
end$$
delimiter ;

delimiter $$
create procedure sp_leer_proveedores(
in p_id_proveedor int
)
begin
select * from Proveedores where id_proveedor = p_id_proveedor;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_proveedores(
	in p_id_proveedor int,
	in p_nombre_proveedor varchar(60),
	in p_telefono_proveedor int,
	in p_direccion varchar(100),
	in p_email_proveedor varchar(100)
)
begin
	update Proveedores
	set 
		nombre_proveedor = p_nombre_proveedor,
        telefono_proveedor = p_telefono_proveedor,
        direccion = p_direccion,
        email_proveedor = p_email_proveedor
	where id_proveedor = p_id_proveedor;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_proveedor(
in p_id_proveedor int
)
begin 
	delete from Proveedores
    where id_proveedor = p_id_proveedor;
end$$
delimiter ;

-- registros
call sp_insertar_proveedores('AutoPartes López', 5551234, 'Av. Central 123', 'contacto@lopez.com');
call sp_insertar_proveedores('Repuestos Martínez', 5552345, 'Calle Norte 45', 'ventas@martinez.com');
call sp_insertar_proveedores('Motores y Más', 5553456, 'Boulevard Sur 789', 'info@motoresymas.com');
call sp_insertar_proveedores('Distribuidora El Motor', 5554567, 'Zona Industrial 12', 'elmotor@gmail.com');
call sp_insertar_proveedores('AutoRepuestos GT', 5555678, 'Av. Reforma 321', 'ventas@autorepuestosgt.com');
call sp_insertar_proveedores('Repuestos Express', 5556789, 'Col. Centro 67', 'express@repuestos.com');
call sp_insertar_proveedores('Frenos y Clutch', 5557890, 'Av. Las Américas 90', 'frenos@clutch.com');
call sp_insertar_proveedores('Importadora AutoMax', 5558901, 'Km 15 Carretera Sur', 'automax@import.com');
call sp_insertar_proveedores('Partes del Norte', 5559012, 'Calle 10 Zona 5', 'norte@partes.com');
call sp_insertar_proveedores('Repuestos Premium', 5550123, 'Zona 10 Plaza Auto', 'premium@repuestos.com');

delimiter $$
create procedure sp_insertar_empleados(
	in p_nombre_empleado varchar(60),
	in p_apellido_empleado varchar(60),
	in p_puesto_empleado varchar(20),
	in p_email_empleado varchar(100)
)
begin
	insert into Empleados (nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
	values (p_nombre_empleado, p_apellido_empleado, p_puesto_empleado, p_email_empleado);
end$$
delimiter ;

delimiter $$
create procedure sp_leer_empleados(
in p_id_empleado int
)
begin
select * from Empleados where id_empleado = p_id_empleado;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_empleados(
	in p_id_empleado int,
	in p_nombre_empleado varchar(60),
	in p_apellido_empleado varchar(60),
	in p_puesto_empleado varchar(20),
	in p_email_empleado varchar(100)
)
begin
	update Empleados
	set 
		nombre_empleado = p_nombre_empleado,
        apellido_empleado = p_apellido_empleado,
        puesto_empleado = p_puesto_empleado,
        email_empleado = p_email_empleado 
	where id_empleado = p_id_empleado;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_empleados(
in p_id_empleado int
)
begin 
	delete from Empleados
    where id_empleado = p_id_empleado;
end$$
delimiter ;

-- registros
call sp_insertar_empleados('Carlos', 'Pérez', 'Vendedor', 'carlos@empresa.com');
call sp_insertar_empleados('Ana', 'López', 'Cajera', 'ana@empresa.com');
call sp_insertar_empleados('Luis', 'Martínez', 'Administrador', 'luis@empresa.com');
call sp_insertar_empleados('María', 'Gómez', 'Vendedor', 'maria@empresa.com');
call sp_insertar_empleados('José', 'Ramírez', 'Bodega', 'jose@empresa.com');
call sp_insertar_empleados('Laura', 'Hernández', 'Cajera', 'laura@empresa.com');
call sp_insertar_empleados('Pedro', 'Castillo', 'Vendedor', 'pedro@empresa.com');
call sp_insertar_empleados('Sofía', 'Morales', 'Marketing', 'sofia@empresa.com');
call sp_insertar_empleados('Jorge', 'Méndez', 'Supervisor', 'jorge@empresa.com');
call sp_insertar_empleados('Daniela', 'Ruiz', 'Vendedor', 'daniela@empresa.com');

delimiter $$
create procedure sp_insertar_repuestos(
	in p_nombre_repuesto varchar(60),
	in p_categoria_repuesto varchar(60),
	in p_precio_compra double,
	in p_precio_venta double,
    in p_id_proveedor int
)
begin
	insert into Repuestos (nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor)
	values (p_nombre_repuesto, p_categoria_repuesto, p_precio_compra, p_precio_venta, p_id_proveedor);
end$$
delimiter ;

delimiter $$
create procedure sp_leer_repuestos(
in p_id_repuesto int
)
begin
select * from Repuestos where id_repuesto = p_id_repuesto;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_repuestos(
	in p_nombre_repuesto varchar(60),
	in p_categoria_repuesto varchar(60),
	in p_precio_compra double,
	in p_precio_venta double,
    in p_id_repuesto int
)
begin
	update Repuestos
	set 
		nombre_repuesto = p_nombre_repuesto,
        categoria_repuesto = p_categoria_repuesto,
        precio_compra = p_precio_compra,
        precio_venta  = p_precio_venta 
	where id_repuesto = p_id_repuesto;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_repuestos(
in p_id_repuesto int
)
begin 
	delete from Repuestos
    where id_repuesto = p_id_repuesto;
end$$
delimiter ;

-- registros
call sp_insertar_repuestos('Filtro de aceite', 'Motor', 25.50, 40.00, 1);
call sp_insertar_repuestos('Bujía', 'Encendido', 10.00, 18.00, 2);
call sp_insertar_repuestos('Pastillas de freno', 'Frenos', 80.00, 120.00, 3);
call sp_insertar_repuestos('Batería 12V', 'Eléctrico', 450.00, 600.00, 4);
call sp_insertar_repuestos('Aceite 20W50', 'Lubricantes', 90.00, 130.00, 5);
call sp_insertar_repuestos('Radiador', 'Enfriamiento', 600.00, 850.00, 6);
call sp_insertar_repuestos('Amortiguador', 'Suspensión', 300.00, 450.00, 7);
call sp_insertar_repuestos('Correa de tiempo', 'Motor', 150.00, 230.00, 8);
call sp_insertar_repuestos('Alternador', 'Eléctrico', 900.00, 1200.00, 9);
call sp_insertar_repuestos('Filtro de aire', 'Motor', 35.00, 60.00, 10);

delimiter $$
create procedure sp_insertar_ventas(
	in p_fecha_venta date,
	in p_cantidad int,
	in p_total double,
    in p_id_empleado int,
    in p_id_repuesto int
)
begin
	insert into Ventas (fecha_venta, cantidad, total, id_empleado, id_repuesto)
	values (p_fecha_venta, p_cantidad, p_total, p_id_empleado, p_id_repuesto);
end$$
delimiter ;

delimiter $$
create procedure sp_leer_ventas(
in p_id_venta int
)
begin
select * from Ventas where id_venta = p_id_venta;
end$$
delimiter ;

delimiter $$
create procedure sp_actualizar_ventas(
	in p_fecha_venta date,
	in p_cantidad int,
	in p_total double,
    in p_id_venta int
)
begin
	update Ventas
	set 
		fecha_venta = p_fecha_venta,
        cantidad = p_cantidad,
        total = p_total
	where id_venta= p_id_venta;
end$$
delimiter ;

delimiter $$
create procedure sp_eliminar_venta(
in p_id_venta int
)
begin 
	delete from Ventas
    where id_venta = p_id_venta;
end$$
delimiter ;

-- registros
call sp_insertar_ventas('2025-01-05', 2, 80.00, 1, 1);
call sp_insertar_ventas('2025-01-06', 4, 72.00, 2, 2);
call sp_insertar_ventas('2025-01-07', 1, 120.00, 3, 3);
call sp_insertar_ventas('2025-01-08', 1, 600.00, 4, 4);
call sp_insertar_ventas('2025-01-09', 3, 390.00, 5, 5);
call sp_insertar_ventas('2025-01-10', 1, 850.00, 6, 6);
call sp_insertar_ventas('2025-01-11', 2, 900.00, 7, 7);
call sp_insertar_ventas('2025-01-12', 1, 230.00, 8, 8);
call sp_insertar_ventas('2025-01-13', 1, 1200.00, 9, 9);
call sp_insertar_ventas('2025-01-14', 5, 300.00, 10, 10);

select * from Empleados;
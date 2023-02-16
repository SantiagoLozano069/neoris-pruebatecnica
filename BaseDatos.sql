
create database basedatosneoris;

create table basedatosneoris.persona (
	id int primary key not null auto_increment,
    nombre varchar(50),
    genero varchar(9),
	edad int(3),
    identificacion varchar(10),
    direccion varchar(60),
    telefono varchar(10)
);

create table basedatosneoris.cliente (
	id int primary key not null auto_increment,
    contrasena varchar(4),
    estado boolean,
    id_persona int not null unique,
    foreign key (id_persona) references basedatosneoris.persona(id)
);


create table basedatosneoris.cuenta (
	id int primary key not null auto_increment,
    numero_cuenta varchar(6),
    tipo_cuenta varchar(9),
	saldo_inicial numeric,
    estado boolean,
    id_cliente int not null,
    foreign key (id_cliente) references basedatosneoris.cliente(id)
);


create table basedatosneoris.movimientos (
	id int primary key not null auto_increment,
    fecha datetime,
    tipo_movimiento varchar(20),
	valor numeric,
    saldo numeric,
    id_cuenta int not null,
	foreign key (id_cuenta) references basedatosneoris.cuenta(id)
);

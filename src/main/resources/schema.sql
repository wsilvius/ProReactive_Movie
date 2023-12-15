create table if not exists
pelicula(
    id_pelicula int not null auto_increment,
    titulo varchar2(50) not null,
    original varchar2(50) not null,
    descripcion varchar2(650),
    reparto varchar2(250),
    director varchar2(50),
    duracion varchar2(50),
    estreno varchar2(50),
    clasificacion varchar2(50),
    genero varchar2(50),
    primary key (id_pelicula)
);

create table if not exists
cartelera(
    id int not null auto_increment,
    horario varchar2(50) not null,
    sala varchar2(50) not null,
    pelicula varchar2(50) not null,
    primary key (id)
);

create table if not exists
sala(
    id int not null auto_increment,
    nombre varchar2(50) not null,
    ciudad varchar2(100) not null,
    formato varchar2(100) not null,
    nivel varchar2(100) not null,
    confiteria varchar2(10) not null,
    primary key (id)
);

create table if not exists
ciudad(
    id int not null auto_increment,
    nombre varchar2(50) not null,
    primary key (id)
);
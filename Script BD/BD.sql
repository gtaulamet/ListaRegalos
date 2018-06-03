--drop table ListaDeRegalos;
--drop table Usuario;
create table ListaDeRegalos (
	codigo int not null identity(1,1),
	nombreAgasajado varchar(100),
	fechaAgasajo datetime,
	mailAgasajado varchar(50),
	montoRecaudado decimal,
	fechaInicio datetime,
	fechaFin datetime,
	estado varchar(10),
	montoARecaudarXInteg decimal,
	administradorId int
)

create table Usuario (
	codigo int not null identity(1,1),
	username varchar(30),
	pass varchar(100),
	nombre varchar(50),
	apellido varchar(50),
	DNI int,
	mail varchar(50)
)

alter table [dbo].[ListaDeRegalos] 
add constraint PK_ListaDeRegalos PRIMARY KEY (codigo)

alter table Usuario
add constraint PK_Usuario PRIMARY KEY (codigo)

alter table ListaDeRegalos
add constraint FK_ListaDeRegalos_Usuario FOREIGN KEY (AdministradorId) 
REFERENCES Usuario(codigo)

create table ParticipanteLista (
	usuarioId int not null,
	listaDeRegalosId int not null,
	pago bit not null,
	estado varchar (8) check (estado in ('Activo','Inactivo'))

)

alter table ParticipanteLista
add constraint FK_Usuario foreign key (usuarioId)
references Usuario(codigo)

alter table ParticipanteLista
add constraint FK_ListaDeRegalos foreign key (listaDeRegalosId)
references ListaDeRegalos(codigo)

alter table ParticipanteLista
add constraint PK_ParticipanteLista primary key (usuarioId,listaDeRegalosId)

create table Pago (
	codigo int not null identity(1,1),
	usuarioId int not null,
	listaDeRegalosId int not null,
	monto decimal,
	fecha datetime
)

alter table Pago
add constraint FK_ParticipanteLista foreign key (usuarioId,listaDeRegalosId)
references ParticipanteLista (usuarioId,listaDeRegalosId)

alter table Pago
add constraint PK_Pago primary key (codigo,usuarioId,listaDeRegalosId)

create table Administrador (
	username varchar(10) not null,
	pass varchar(100) not null
)
alter table Administrador
add constraint PK_Administrador primary key (username)

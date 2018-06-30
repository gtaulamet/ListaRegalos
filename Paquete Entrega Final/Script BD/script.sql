USE master ;  
GO  
CREATE DATABASE BD_ListaRegalos
GO

ALTER DATABASE BD_ListaRegalos MODIFY FILE 
( NAME = N'BD_ListaRegalos' , SIZE = 50MB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
GO
ALTER DATABASE BD_ListaRegalos MODIFY FILE 
( NAME = N'BD_ListaRegalos_log' , SIZE = 50MB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
  
USE [master]
GO

/* For security reasons the login is created disabled and with a random password. */
/****** Object:  Login [api]    Script Date: 07/06/2018 13:21:47 ******/
CREATE LOGIN [api] WITH PASSWORD='api18', DEFAULT_DATABASE=[BD_ListaRegalos], DEFAULT_LANGUAGE=[Español], CHECK_EXPIRATION=off, check_policy = off;
GO

ALTER LOGIN [api] DISABLE
GO


USE [BD_ListaRegalos]
GO

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
	mail varchar(50),
	estado varchar(10)
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


USE [BD_ListaRegalos]
GO

/****** Object:  User [api]    Script Date: 07/06/2018 13:22:26 ******/
CREATE USER [api] FOR LOGIN [api] WITH DEFAULT_SCHEMA=[dbo]
GO

exec sp_addrole 'AI';
exec sp_addrolemember 'AI', 'api';

USE [BD_ListaRegalos]
GO
grant select on ListaDeRegalos to AI
grant select on Administrador to AI
grant select on Pago to AI
grant select on Usuario to AI
grant select on ParticipanteLista to AI
grant insert on ListaDeRegalos to AI
grant insert on Administrador to AI
grant insert on Pago to AI
grant insert on Usuario to AI
grant insert on ParticipanteLista to AI
grant delete on ListaDeRegalos to AI
grant delete on Administrador to AI
grant delete on Pago to AI
grant delete on Usuario to AI
grant delete on ParticipanteLista to AI
grant update on ListaDeRegalos to AI
grant update on Administrador to AI
grant update on Pago to AI
grant update on Usuario to AI
grant update on ParticipanteLista to AI
grant execute to AI

ALTER LOGIN [api] ENABLE
GO

use [BD_ListaRegalos]
go

INSERT [dbo].[Administrador] ([username], [pass]) VALUES (N'admin', N'8a5da52ed126447d359e70c05721a8aa')

SET IDENTITY_INSERT [dbo].[Usuario] OFF
INSERT [dbo].[Usuario] ( [username], [pass], [nombre], [apellido], [DNI], [mail],[estado]) VALUES ( N'gtaulamet', N'8a5da52ed126447d359e70c05721a8aa', N'Gustavo', N'Taulamet', 324234324, N'gustavo.taulamet@gmail.com', 'Activo')
INSERT [dbo].[Usuario] ( [username], [pass], [nombre], [apellido], [DNI], [mail],[estado]) VALUES ( N'ssuarez', N'8a5da52ed126447d359e70c05721a8aa', N'Sergio', N'Suarez', 324322344, N'gtaulamet@siema.com.ar', 'Activo')
INSERT [dbo].[Usuario] ( [username], [pass], [nombre], [apellido], [DNI], [mail],[estado]) VALUES ( N'pintilisano', N'8a5da52ed126447d359e70c05721a8aa', N'Pablo', N'Intilisano', 432432434, N'pablointiar@gmail.com', 'Activo')
INSERT [dbo].[Usuario] ( [username], [pass], [nombre], [apellido], [DNI], [mail],[estado]) VALUES ( N'jsalzano', N'8a5da52ed126447d359e70c05721a8aa', N'Jorge', N'Salzano', 324323234, N'pgplaystation@gmail.com', 'Activo')

SET IDENTITY_INSERT [dbo].[ListaDeRegalos] OFF
/*
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (N'Juan Gomez', CAST(N'2018-06-20T00:00:00.000' AS DateTime), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-06-15T00:00:00.000' AS DateTime), N'Activo', CAST(100 AS Decimal(18, 0)), 1)
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (N'Gustavo Taulamet', CAST(N'2018-06-04T00:00:00.000' AS DateTime), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-06-01T00:00:00.000' AS DateTime), N'Activo', CAST(1000 AS Decimal(18, 0)), 4)
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (N'Pedro Espinoza', CAST(N'2018-07-15T00:00:00.000' AS DateTime), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-07-10T00:00:00.000' AS DateTime), N'Activo', CAST(130 AS Decimal(18, 0)), 1)
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (N'Juan Sosa', CAST(N'2018-08-25T00:00:00.000' AS DateTime), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-08-20T00:00:00.000' AS DateTime), N'Activo', CAST(110 AS Decimal(18, 0)), 1)
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (N'Claudia Galapa', CAST(N'2018-10-10T00:00:00.000' AS DateTime), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-10-10T00:00:00.000' AS DateTime), N'Activo', CAST(100 AS Decimal(18, 0)), 1)
*/

--AGASAJO HOY
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES 
(N'Juan Gomez', CONVERT(VARCHAR(20), GETDATE(), 105), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CONVERT(VARCHAR(20), DATEADD(DAY,-5,GETDATE()), 105), CONVERT(VARCHAR(20), DATEADD(DAY,-2,GETDATE()), 105), N'Finalizada', CAST(100 AS Decimal(18, 0)), 1)
--INICIA HOY
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES 
(N'Gustavo Taulamet', CONVERT(VARCHAR(20), DATEADD(DAY,4,GETDATE()), 105), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CONVERT(VARCHAR(20), GETDATE(), 105), CONVERT(VARCHAR(20), DATEADD(DAY,2,GETDATE()), 105), N'Activo', CAST(1000 AS Decimal(18, 0)), 4)
--PROXIMO A VENCER
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES 
(N'Pedro Espinoza', CONVERT(VARCHAR(20), DATEADD(DAY,6,GETDATE()), 105), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CONVERT(VARCHAR(20), DATEADD(DAY,-1,GETDATE()), 105), CONVERT(VARCHAR(20), DATEADD(DAY,3,GETDATE()), 105), N'Activo', CAST(130 AS Decimal(18, 0)), 1)
--FINALIZA HOY
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES 
(N'Juan Sosa', CONVERT(VARCHAR(20), DATEADD(DAY,4,GETDATE()), 105), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CONVERT(VARCHAR(20), DATEADD(DAY,-2,GETDATE()), 105), CONVERT(VARCHAR(20), GETDATE(), 105), N'Activo', CAST(110 AS Decimal(18, 0)), 1)
--
INSERT [dbo].[ListaDeRegalos] ([nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES 
(N'Claudia Galapa', CONVERT(VARCHAR(20), DATEADD(DAY,5,GETDATE()), 105), N'gustavo.taulamet@gmail.com', CAST(0 AS Decimal(18, 0)), CONVERT(VARCHAR(20), DATEADD(DAY,2,GETDATE()), 105), CONVERT(VARCHAR(20), DATEADD(DAY,4,GETDATE()), 105), N'Activo', CAST(100 AS Decimal(18, 0)), 1)

INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (1, 2, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (2, 1, 0, N'Inactivo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (2, 2, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (2, 3, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (3, 1, 0, N'Inactivo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (3, 2, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (4, 1, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (4, 3, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (2, 4, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (3, 5, 0, N'Activo')
--SET IDENTITY_INSERT [dbo].[Usuario] ON 

USE [BD_ListaRegalos]
GO

/****** Object:  StoredProcedure [dbo].[CrearPago]    Script Date: 20/06/2018 13:37:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[CrearPago] @lr int, @u int, @f Date, @m decimal 

AS
BEGIN
	SET NOCOUNT ON;
	begin tran
		--Inserto el pago
		declare @cant as integer;
		
		select @cant = count(*) from Pago where usuarioId = @u and listaDeRegalosId = @lr;
		if @cant != 0
		begin
			return
		end
		insert into Pago (usuarioId,listaDeRegalosId,monto,fecha) VALUES (@u,@lr,@m,@f);
		
		if @@error != 0
		begin
			rollback tran;
		end

		--Actualizo el check de pago en participanteLista
		update ParticipanteLista set pago = 1 where usuarioId = @u and listaDeRegalosId = @lr;
		if @@error != 0
		begin
			rollback tran;
		end

		--Actualizo el monto recaudado en la lista de regalos
		declare @monto as decimal;
		select @monto = montoRecaudado  from ListaDeRegalos where codigo = @lr;
		update ListaDeRegalos set montoRecaudado = @monto+@m where codigo = @lr;
		if @@error != 0
		begin
			rollback tran;
		end
			
	commit tran

END
GO


USE [BD_ListaRegalos]
GO

/****** Object:  StoredProcedure [dbo].[CrearPago]    Script Date: 20/06/2018 13:37:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[ActualizarEstado] @f Date 

AS
BEGIN
	SET NOCOUNT ON;
	update ListaDeRegalos set estado = 'Finalizada' where fechaFin = @f;
END
GO

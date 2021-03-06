USE [master]
GO
create login [api] with password = 'api18', DEFAULT_DATABASE=[master], check_policy = off;
GO

create database [BD_ListaRegalos]
GO
USE [BD_ListaRegalos]
GO
/****** Object:  User [api]    Script Date: 06/06/2018 14:29:46 ******/
CREATE USER [api] FOR LOGIN [api] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [api]
GO
/****** Object:  Table [dbo].[Administrador]    Script Date: 04/06/2018 23:08:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Administrador](
	[username] [varchar](10) NOT NULL,
	[pass] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Administrador] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ListaDeRegalos]    Script Date: 04/06/2018 23:08:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ListaDeRegalos](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombreAgasajado] [varchar](100) NULL,
	[fechaAgasajo] [datetime] NULL,
	[mailAgasajado] [varchar](50) NULL,
	[montoRecaudado] [decimal](18, 0) NULL,
	[fechaInicio] [datetime] NULL,
	[fechaFin] [datetime] NULL,
	[estado] [varchar](10) NULL,
	[montoARecaudarXInteg] [decimal](18, 0) NULL,
	[administradorId] [int] NULL,
 CONSTRAINT [PK_ListaDeRegalos] PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pago]    Script Date: 04/06/2018 23:08:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pago](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[usuarioId] [int] NOT NULL,
	[listaDeRegalosId] [int] NOT NULL,
	[monto] [decimal](18, 0) NULL,
	[fecha] [datetime] NULL,
 CONSTRAINT [PK_Pago] PRIMARY KEY CLUSTERED 
(
	[codigo] ASC,
	[usuarioId] ASC,
	[listaDeRegalosId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ParticipanteLista]    Script Date: 04/06/2018 23:08:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ParticipanteLista](
	[usuarioId] [int] NOT NULL,
	[listaDeRegalosId] [int] NOT NULL,
	[pago] [bit] NOT NULL,
	[estado] [varchar](8) NULL,
 CONSTRAINT [PK_ParticipanteLista] PRIMARY KEY CLUSTERED 
(
	[usuarioId] ASC,
	[listaDeRegalosId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 04/06/2018 23:08:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](30) NULL,
	[pass] [varchar](100) NULL,
	[nombre] [varchar](50) NULL,
	[apellido] [varchar](50) NULL,
	[DNI] [int] NULL,
	[mail] [varchar](50) NULL,
 CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Administrador] ([username], [pass]) VALUES (N'admin', N'576031f7b5341875417575767997d17f')
SET IDENTITY_INSERT [dbo].[ListaDeRegalos] ON 

INSERT [dbo].[ListaDeRegalos] ([codigo], [nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (1, N'Juan Gomez', CAST(N'2018-06-20T00:00:00.000' AS DateTime), N'jg@hotmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-06-15T00:00:00.000' AS DateTime), N'Activo', CAST(100 AS Decimal(18, 0)), 1)
INSERT [dbo].[ListaDeRegalos] ([codigo], [nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (2, N'Gustavo Taulamet', CAST(N'2018-06-04T00:00:00.000' AS DateTime), N'gt@hotmail.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-06-01T00:00:00.000' AS DateTime), N'Activo', CAST(1000 AS Decimal(18, 0)), 4)
INSERT [dbo].[ListaDeRegalos] ([codigo], [nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (3, N'Pedro Espinoza', CAST(N'2018-07-15T00:00:00.000' AS DateTime), N'dd@dd.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-07-10T00:00:00.000' AS DateTime), N'Activo', CAST(130 AS Decimal(18, 0)), 1)
INSERT [dbo].[ListaDeRegalos] ([codigo], [nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (4, N'Juan Sosa', CAST(N'2018-08-25T00:00:00.000' AS DateTime), N'dd@dd.com', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-08-20T00:00:00.000' AS DateTime), N'Activo', CAST(110 AS Decimal(18, 0)), 1)
INSERT [dbo].[ListaDeRegalos] ([codigo], [nombreAgasajado], [fechaAgasajo], [mailAgasajado], [montoRecaudado], [fechaInicio], [fechaFin], [estado], [montoARecaudarXInteg], [administradorId]) VALUES (6, N'dddd', CAST(N'2018-10-10T00:00:00.000' AS DateTime), N'ddd', CAST(0 AS Decimal(18, 0)), CAST(N'2018-06-04T00:00:00.000' AS DateTime), CAST(N'2018-10-10T00:00:00.000' AS DateTime), N'Activo', CAST(0 AS Decimal(18, 0)), 1)
SET IDENTITY_INSERT [dbo].[ListaDeRegalos] OFF
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (1, 1, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (1, 2, 0, N'Inactivo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (2, 1, 0, N'Inactivo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (2, 2, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (3, 1, 0, N'Inactivo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (3, 2, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (4, 1, 0, N'Activo')
INSERT [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId], [pago], [estado]) VALUES (4, 2, 0, N'Activo')
SET IDENTITY_INSERT [dbo].[Usuario] ON 

INSERT [dbo].[Usuario] ([codigo], [username], [pass], [nombre], [apellido], [DNI], [mail]) VALUES (1, N'gtaulamet', N'576031f7b5341875417575767997d17f', N'Gustavo', N'Taulamet', 324234324, N'gt@dd.com')
INSERT [dbo].[Usuario] ([codigo], [username], [pass], [nombre], [apellido], [DNI], [mail]) VALUES (2, N'ssuarez', N'9c8cafccffe06d16454788b80aee4ea3', N'Sergio', N'Suarez', 324322344, N'fd@dsd.com')
INSERT [dbo].[Usuario] ([codigo], [username], [pass], [nombre], [apellido], [DNI], [mail]) VALUES (3, N'pinti', N'576031f7b5341875417575767997d17f', N'Pablo', N'Intilisano', 432432434, N'pi@dad.con')
INSERT [dbo].[Usuario] ([codigo], [username], [pass], [nombre], [apellido], [DNI], [mail]) VALUES (4, N'jsalzano', N'576031f7b5341875417575767997d17f', N'Jorge', N'Salzano', 324323234, N'jsalza@ddd.com')
SET IDENTITY_INSERT [dbo].[Usuario] OFF
ALTER TABLE [dbo].[ListaDeRegalos]  WITH CHECK ADD  CONSTRAINT [FK_ListaDeRegalos_Usuario] FOREIGN KEY([administradorId])
REFERENCES [dbo].[Usuario] ([codigo])
GO
ALTER TABLE [dbo].[ListaDeRegalos] CHECK CONSTRAINT [FK_ListaDeRegalos_Usuario]
GO
ALTER TABLE [dbo].[Pago]  WITH CHECK ADD  CONSTRAINT [FK_ParticipanteLista] FOREIGN KEY([usuarioId], [listaDeRegalosId])
REFERENCES [dbo].[ParticipanteLista] ([usuarioId], [listaDeRegalosId])
GO
ALTER TABLE [dbo].[Pago] CHECK CONSTRAINT [FK_ParticipanteLista]
GO
ALTER TABLE [dbo].[ParticipanteLista]  WITH CHECK ADD  CONSTRAINT [FK_ListaDeRegalos] FOREIGN KEY([listaDeRegalosId])
REFERENCES [dbo].[ListaDeRegalos] ([codigo])
GO
ALTER TABLE [dbo].[ParticipanteLista] CHECK CONSTRAINT [FK_ListaDeRegalos]
GO
ALTER TABLE [dbo].[ParticipanteLista]  WITH CHECK ADD  CONSTRAINT [FK_Usuario] FOREIGN KEY([usuarioId])
REFERENCES [dbo].[Usuario] ([codigo])
GO
ALTER TABLE [dbo].[ParticipanteLista] CHECK CONSTRAINT [FK_Usuario]
GO
ALTER TABLE [dbo].[ParticipanteLista]  WITH CHECK ADD CHECK  (([estado]='Inactivo' OR [estado]='Activo'))
GO

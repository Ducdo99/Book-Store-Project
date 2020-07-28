USE [SE1413]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 25-Jul-20 10:57:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[lastname] [nvarchar](50) NOT NULL,
	[isAdmin] [bit] NULL,
	[status] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Book]    Script Date: 25-Jul-20 10:57:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Book](
	[bookID] [varchar](30) NOT NULL,
	[bookName] [nvarchar](250) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Cart]    Script Date: 25-Jul-20 10:57:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cart](
	[cartID] [varchar](30) NOT NULL,
	[username] [varchar](20) NOT NULL,
	[receiverName] [nvarchar](50) NOT NULL,
	[receiverAddress] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_Cart] PRIMARY KEY CLUSTERED 
(
	[cartID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CartDetail]    Script Date: 25-Jul-20 10:57:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CartDetail](
	[cartID] [varchar](30) NOT NULL,
	[bookID] [varchar](30) NOT NULL,
	[bookName] [nvarchar](250) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[total] [float] NOT NULL,
 CONSTRAINT [PK_CartDetail] PRIMARY KEY CLUSTERED 
(
	[cartID] ASC,
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'demo', N'123', N'Duc Minh', 0, 0)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'duc', N'123', N'Duc Minh', 1, 1)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'ducdm', N'123', N'Minh Duc', 1, 1)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'ducdm1', N'123456', N'Minh Duc', 0, 0)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'ducdm12', N'123456', N'Minh Duc', 0, 1)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'ducdm123', N'123456', N'Minh Duc', 0, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000001', N'Java for beginer 1', 6, 15000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000002', N'Java for beginer 2', 5, 15000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000003', N'Java for beginer 3', 3, 30000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000004', N'Java for beginer 4', 7, 45000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000005', N'SQL Server 2008', 10, 30000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000006', N'SQL for beginer ', 6, 20000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000007', N'Python for beigner', 15, 100000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000008', N'Mina no NIhongo 1', 20, 50000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000009', N'Mina no Nihongo 2', 21, 150000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000010', N'Mina no Nihongo 3', 22, 200000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000011', N'Summit 1', 8, 45000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000012', N'Summit 2', 10, 45500, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000013', N'Topnotch 1', 12, 45500.5, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000014', N'Topnotch 2', 20, 100000.5, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000015', N'Topnotch 3', 22, 150000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000016', N'Clean code', 30, 50000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000017', N'Dekiru 1', 30, 55000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000018', N'Dekiru 2', 30, 60000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000019', N'Dekiru 3', 21, 65000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000020', N'Data Structures and Algorithms in Java', 15, 15000, 1)
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_CartDetail_Book] FOREIGN KEY([bookID])
REFERENCES [dbo].[Book] ([bookID])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_CartDetail_Book]
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_CartDetail_Cart] FOREIGN KEY([cartID])
REFERENCES [dbo].[Cart] ([cartID])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_CartDetail_Cart]
GO

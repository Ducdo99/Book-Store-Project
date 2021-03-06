USE [SE1413]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 28-Jul-20 3:43:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](100) NOT NULL,
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
/****** Object:  Table [dbo].[Book]    Script Date: 28-Jul-20 3:43:47 PM ******/
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
/****** Object:  Table [dbo].[Cart]    Script Date: 28-Jul-20 3:43:47 PM ******/
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
/****** Object:  Table [dbo].[CartDetail]    Script Date: 28-Jul-20 3:43:47 PM ******/
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
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'admin123', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', N'Admin', 1, 1)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'demo12', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', N'Demo 1', 0, 1)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'minhduc', N'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F', N'Minh Duc', 0, 1)
INSERT [dbo].[Account] ([username], [password], [lastname], [isAdmin], [status]) VALUES (N'minhduc1', N'F14F286CA435D1FA3B9D8041E8F06AA0AF7AB28EA8EDCD7E11FD485A100B632B', N'Minh Duc 1', 0, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000001', N'Java for beginer 1', 3, 15000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000002', N'Java for beginer 2', 8, 15000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000003', N'Java for beginer 3', 6, 30000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000004', N'Java for beginer 4', 9, 45000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000005', N'SQL Server 2008', 7, 30000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000006', N'SQL for beginer ', 4, 20000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000007', N'Python for beigner', 15, 100000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000008', N'Mina no NIhongo 1', 19, 50000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000009', N'Mina no Nihongo 2', 21, 150000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000010', N'Mina no Nihongo 3', 21, 200000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000011', N'Summit 1', 8, 45000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000012', N'Summit 2', 10, 45500, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000013', N'Topnotch 1', 11, 45500.5, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000014', N'Topnotch 2', 19, 100000.5, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000015', N'Topnotch 3', 21, 150000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000016', N'Clean code', 30, 50000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000017', N'Dekiru 1', 27, 55000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000018', N'Dekiru 2', 28, 60000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000019', N'Dekiru 3', 20, 65000, 1)
INSERT [dbo].[Book] ([bookID], [bookName], [quantity], [price], [status]) VALUES (N'B000020', N'Data Structures and Algorithms in Java', 14, 15000, 1)
INSERT [dbo].[Cart] ([cartID], [username], [receiverName], [receiverAddress]) VALUES (N'1', N'minhduc', N'Minh Hieu', N'123 CMT8, P.3, Q.10, TP.HCM')
INSERT [dbo].[Cart] ([cartID], [username], [receiverName], [receiverAddress]) VALUES (N'2', N'demo12', N'Minh Duc', N'15/11 D1, P.20, Q.BT, TP.HCM')
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'1', N'B000006', N'SQL for beginer ', 1, 20000, 20000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'1', N'B000008', N'Mina no NIhongo 1', 1, 50000, 50000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'1', N'B000010', N'Mina no Nihongo 3', 1, 200000, 200000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'2', N'B000001', N'Java for beginer 1', 1, 15000, 15000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'2', N'B000005', N'SQL Server 2008', 1, 30000, 30000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'2', N'B000015', N'Topnotch 3', 1, 150000, 150000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'2', N'B000017', N'Dekiru 1', 2, 55000, 110000)
INSERT [dbo].[CartDetail] ([cartID], [bookID], [bookName], [quantity], [price], [total]) VALUES (N'2', N'B000018', N'Dekiru 2', 1, 60000, 60000)
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

create database MagicShop
go
use MagicShop
create table Permission(
PermissionID INT Primary Key,
PermissionType Varchar(20)
);
go
create table MagicType(
MagicTypeID INT Primary Key,
MagicType Varchar(20)
);
go
create table OrderStatus(
OrderStatusID INT Primary Key,
OrderStatus Varchar(20)
);
go
create table Employee(
EmployeeID INT Primary Key IDENTITY(1,1),
Name Varchar(20),
Salary Float,
PermissionID INT,
OnVacation Bit,
Fired Bit,
Username Varchar(20),
Password Varchar(20),
Foreign Key(PermissionID) references Permission(PermissionID));
go
create table Good(
GoodID INT Primary Key IDENTITY(1,1),
MagicTypeID INT,
Name Varchar(20),
Description Varchar(20),
Price Float,
Amount INT,
Deleted Bit,
Foreign key (MagicTypeID) references MagicType(MagicTypeID));
go
create table CreationJob(
CreationJobID INT Primary Key IDENTITY(1,1),
GoodID INT,
EmployeeID INT,
AmountRemaining INT,
Foreign key (GoodID) references Good(GoodID),
Foreign key (EmployeeID) references Employee(EmployeeID));
go
create table Item(
ItemID INT Primary Key IDENTITY(1,1),
Description Varchar(20));
go
create table EnchantmentJob(
EnchantmentJobID INT Primary Key IDENTITY(1,1),
ItemID INT,
MagicTypeID INT,
Description Varchar(20),
Completed BIT,
Foreign key (MagicTypeID) references MagicType(MagicTypeID),
Foreign key (ItemID) references Item(ItemID));
go
create table [Order](
OrderID INT Primary Key IDENTITY(1,1),
ClientName Varchar(20),
OrderStatusID INT,
AssignedEmployeeID INT,
Country Varchar(20),
City Varchar(20),
Address Varchar(20)
Foreign key (OrderStatusID) references OrderStatus(OrderStatusID), 
Foreign key (AssignedEmployeeID) references Employee(EmployeeID));
go
create table OrderEnchantmentJob(
OrderEnchantmentJobID INT Primary Key IDENTITY(1,1),
OrderID INT,
EnchantmentJobID INT,
Foreign key (OrderID) references [Order](OrderID), 
Foreign key (EnchantmentJobID) references EnchantmentJob(EnchantmentJobID));
go
create table OrderGood(
OrderGoodID INT Primary Key IDENTITY(1,1),
GoodID INT,
OrderID INT,
Foreign key (OrderID) references [Order](OrderID), 
Foreign key (GoodID) references Good(GoodID));

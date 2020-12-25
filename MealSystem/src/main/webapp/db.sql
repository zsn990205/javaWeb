drop database if exists MealSystem;
create database MealSystem;
use MealSystem;

drop table if exists dishes;
create table dishes (
dishId int primary key auto_increment,
name varchar(50),
price int   -- 为了减少误差尽量使用int用分来表示
);

drop table if exists user;
create table user(
userId int primary key auto_increment,
name varchar(50) unique,
password varchar(50),
isAdmin int  -- 1表示是管理员用户
);

drop table if exists order_User;
create table order_User(
orderId int primary key auto_increment,
userId int,  -- 此userId和user表中的userId有关联关系
time datetime,  -- 下单时间
isDone int,     -- 1表示完结 0表示未完结
foreign key(userId) references user(userId)
);

drop table if exists order_dish;
create table order_dish(
orderId int,  -- orderId和order_User中的orderId有外键关系
dishId int,    -- dishId和dish中的dishId有外键关系
foreign key(orderId) references order_User(orderId),
foreign key(dishId) references dishes(dishId)
);
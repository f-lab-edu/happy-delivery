drop table if exists users;

drop table if exists user_addresses;

drop table if exists menus;

create table users
(
    id       bigint primary key auto_increment,
    email    varchar(20) not null,
    name     varchar(20) not null,
    password varchar(100) not null,
    phoneNumber varchar(20) not null
);

create table user_addresses
(
    id            bigint primary key auto_increment,
    userId        bigint      not null,
    addressCode   varchar(50) not null,
    addressDetail varchar(100) not null
);

create table menus
(
    menuId     int auto_increment primary key,
    menuName   varchar(100) null,
    menuDetail varchar(100) null,
    menuPrice  int          null,
    storeSid   int          null
);

INSERT INTO menus (menuId, menuName, menuDetail, menuPrice, storeSid)
VALUES (1, '짜장면', '맛있는 짜장면', 5000, 1);

INSERT INTO menus (menuId, menuName, menuDetail, menuPrice, storeSid)
VALUES (2, '짬뽕', '맛있는 짬뽕', 6000, 1);

INSERT INTO menus (menuId, menuName, menuDetail, menuPrice, storeSid)
VALUES (3, '돈까스', '맛있는 돈까스', 2000, 2);


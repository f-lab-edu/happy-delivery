drop table if exists users;

drop table if exists user_addresses;

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
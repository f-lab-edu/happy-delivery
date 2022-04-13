drop table if exists users;

create table users
(
    user_id int unsigned not null primary key auto_increment,
    email    varchar(20) not null,
    name     varchar(20) not null,
    password varchar(100) not null,
    phone_number varchar(11) not null
);
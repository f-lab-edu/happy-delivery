drop table if exists ceo;

create table ceo
(
    id       int unsigned not null auto_increment,
    email    varchar(20) null,
    password varchar(100) null,
    primary key (id)
);
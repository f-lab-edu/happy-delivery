drop table if exists restaurant_categories;

create table restaurant_categories
(
    id   int primary key auto_increment,
    name varchar(20) not null unique key
) ENGINE=InnoDB;

drop table if exists restaurants;

create table restaurants
(
    id          bigint primary key auto_increment,
    name        varchar(20)  not null,
    category    varchar(20)  not null,
    addressCode varchar(100) not null,
    index       idx1(category, addressCode)
) ENGINE=InnoDB;
drop table if exists restaurants;

create table restaurants
(
    id          bigint primary key auto_increment,
    name        varchar(20)  not null,
    category    int          not null,
    addressCode varchar(100) not null,
    index       idx1(category, addressCode)
) ENGINE=InnoDB;
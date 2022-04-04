drop table if exists restaurants;

create table restaurants
(
    id            bigint primary key auto_increment,
    name          varchar(20)  not null,
    category      varchar(20)  not null,
    address       point        not null,
    addressDetail varchar(100) not null,
    spatial       address_idx(address)
) ENGINE=InnoDB;

drop table if exists user_addresses;

create table user_addresses
(
    id            bigint primary key auto_increment,
    userId        bigint not null,
    address       point  not null,
    addressDetail varchar(100),
    index         user_id_idx(userId)
) ENGINE=InnoDB;
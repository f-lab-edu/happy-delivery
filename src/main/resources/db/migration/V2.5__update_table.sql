drop table if exists restaurants;

create table restaurants
(
    id       bigint primary key auto_increment,
    name     varchar(20) not null,
    category varchar(20) not null,
    location point       not null,
    spatial  location_idx(location)
) ENGINE=InnoDB;

drop table if exists user_addresses;

create table user_addresses
(
    id            bigint primary key auto_increment,
    userId        bigint       not null,
    location      point        not null,
    addressDetail varchar(100),
    index         user_id_idx(userId)
) ENGINE=InnoDB;
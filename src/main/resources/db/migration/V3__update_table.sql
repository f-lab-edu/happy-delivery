drop table if exists users;

create table users
(
    user_id      int unsigned not null primary key auto_increment,
    email        varchar(20)  not null,
    name         varchar(20)  not null,
    password     varchar(100) not null,
    phone_number varchar(11)  not null
);

drop table if exists user_addresses;

create table user_addresses
(
    user_address_id int unsigned not null primary key auto_increment,
    user_id         int unsigned not null,
    address         point        not null,
    address_detail  varchar(100) not null,
    main_address    boolean      not null,
    foreign key (user_id)
        references users (user_id)
        on update cascade
        on delete cascade
);
drop table if exists user_addresses;

create table user_addresses
(
    user_address_id int unsigned not null auto_increment,
    user_id         int unsigned not null,
    longitude       decimal(9, 6) not null,
    latitude        decimal(8, 6) not null,
    address_detail  varchar(100)  not null,
    main_address    boolean       not null,
    primary key (user_address_id),
    constraint users__user_addresses__user_id_fk foreign key (user_id) references users (user_id) on update cascade on delete cascade
);
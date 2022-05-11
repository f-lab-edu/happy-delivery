drop table if exists restaurants;

create table restaurants
(
    restaurant_id  int unsigned not null auto_increment,
    name           varchar(20)   not null,
    category       varchar(20)   not null,
    longitude      decimal(9, 6) not null,
    latitude       decimal(8, 6) not null,
    address_detail varchar(100)  not null,
    primary key (restaurant_id),
    constraint restaurant_categories__restaurants__category_fk foreign key (category) references restaurant_categories (category) on update cascade
);
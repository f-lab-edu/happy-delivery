alter table restaurant_categories modify id int unsigned auto_increment;

alter table restaurants modify id int unsigned auto_increment;

alter table restaurants
    add constraint category
        foreign key (category) references restaurant_categories (name)
            on update cascade;

drop table if exists menu_groups;

create table menu_groups
(
    id            int unsigned not null auto_increment,
    restaurant_id int unsigned not null,
    name          varchar(20) not null,
    primary key (id, restaurant_id),
    foreign key (restaurant_id) references restaurants (id)
        on update cascade on delete cascade
);
drop table if exists menu_groups;

create table menu_groups
(
    menu_group_id int unsigned not null auto_increment,
    restaurant_id int unsigned not null,
    name          varchar(20) not null,
    primary key (menu_group_id, restaurant_id),
    constraint restaurants__menu_groups__restaurant_id foreign key (restaurant_id) references restaurants (restaurant_id) on update cascade on delete cascade
);
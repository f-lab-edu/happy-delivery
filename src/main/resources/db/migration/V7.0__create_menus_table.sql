drop table if exists menus;

create table menus
(
    menu_id       int unsigned not null auto_increment,
    restaurant_id int unsigned not null,
    menu_group_id int unsigned not null,
    name          varchar(50) not null,
    detail        varchar(100),
    price         int         not null,
    primary key (menu_id, restaurant_id, menu_group_id),
    constraint menu_groups__menus__menu_group_id_fk foreign key (menu_group_id) references menu_groups (menu_group_id) on update cascade on delete cascade,
    constraint menu_groups__menus__restaurant_id_fk foreign key (restaurant_id) references menu_groups (restaurant_id) on update cascade on delete cascade
);
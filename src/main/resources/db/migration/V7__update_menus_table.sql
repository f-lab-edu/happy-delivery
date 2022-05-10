drop table if exists menus;

create table menus
(
    id            int unsigned not null auto_increment,
    restaurant_id int unsigned not null,
    menu_group_id int unsigned not null,
    name          varchar(50) not null,
    detail        varchar(100),
    price         int         not null,
    primary key (id, restaurant_id, menu_group_id),
    constraint menu_groups_menus_menu_group_id_fk foreign key (menu_group_id) references menu_groups (id) on update cascade on delete cascade,
    constraint menu_groups_menus_restaurant_id_fk foreign key (restaurant_id) references menu_groups (restaurant_id) on update cascade on delete cascade
);

drop table if exists option_groups;

create table option_groups
(
    option_group_id  int unsigned not null auto_increment,
    restaurant_id    int unsigned not null,
    menu_group_id    int unsigned not null,
    menu_id          int unsigned not null,
    name             varchar(50) not null,
    limit_of_options int unsigned not null,
    mandatory        boolean     not null,
    primary key (option_group_id, restaurant_id, menu_group_id, menu_id),
    constraint menus__option_groups__menu_id_fk foreign key (menu_id) references menus (menu_id) on update cascade on delete cascade,
    constraint menus__option_groups__restaurant_id_fk foreign key (restaurant_id) references menus (restaurant_id) on update cascade on delete cascade,
    constraint menus__option_groups__menu_group_id_fk foreign key (menu_group_id) references menus (menu_group_id) on update cascade on delete cascade
);
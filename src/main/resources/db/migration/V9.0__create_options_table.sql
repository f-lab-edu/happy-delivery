drop table if exists options;

create table options
(
    option_id       int unsigned not null auto_increment,
    restaurant_id   int unsigned not null,
    menu_group_id   int unsigned not null,
    menu_id         int unsigned not null,
    option_group_id int unsigned not null,
    name            varchar(50) not null,
    price           int         not null,
    primary key (option_id, restaurant_id, menu_group_id, menu_id, option_group_id),
    constraint option_groups__options__option_group_id_fk foreign key (option_group_id) references option_groups (option_group_id) on update cascade on delete cascade,
    constraint option_groups__options__restaurant_id_fk foreign key (restaurant_id) references option_groups (restaurant_id) on update cascade on delete cascade,
    constraint option_groups__options__menu_group_id_fk foreign key (menu_group_id) references option_groups (menu_group_id) on update cascade on delete cascade,
    constraint option_groups__options__menu_id_fk foreign key (menu_id) references option_groups (menu_id) on update cascade on delete cascade
);
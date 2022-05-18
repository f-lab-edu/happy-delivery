drop table if exists restaurant_categories;

create table restaurant_categories
(
    category varchar(20) not null,
    primary key (category)
);
drop table if exists restaurant_categories;

create table restaurant_categories
(
    id   int primary key auto_increment,
    name varchar(30) not null
);

ALTER TABLE restaurants MODIFY name VARCHAR(50) NOT NULL;

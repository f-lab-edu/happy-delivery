drop table if exists restaurant_categories;

create table restaurant_categories
(
    id   bigint primary key auto_increment,
    name varchar(20) not null
);

INSERT INTO restaurant_categories (name) VALUES ('일식');
INSERT INTO restaurant_categories (name) VALUES ('중식');
INSERT INTO restaurant_categories (name) VALUES ('치킨');
INSERT INTO restaurant_categories (name) VALUES ('한식');
INSERT INTO restaurant_categories (name) VALUES ('디저트');
INSERT INTO restaurant_categories (name) VALUES ('분식');
INSERT INTO restaurant_categories (name) VALUES ('양식');
INSERT INTO restaurant_categories (name) VALUES ('아시안');
INSERT INTO restaurant_categories (name) VALUES ('패스트푸드');
create table devices (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into devices(name, count, price) VALUES ('Tub', 3, 20000);
insert into devices(name, count, price) VALUES ('Laptop', 10, 50000);
insert into devices(name, count, price) VALUES ('Television', 3, 35000);

set session transaction isolation level read uncommitted;

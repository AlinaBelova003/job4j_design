create table product(
     id serial primary key,
     name varchar(255)
 );
 
 create table person(
     id serial primary key,
     name varchar(255)
 );
 
 create table product_person(
     id serial primary key,
     product_id int references product(id),
     person_id int references person(id)
 );
 
insert into person(name) values ('Ivan');
insert into person(name) values ('Alina');
insert into person(name) values ('Petr');

insert into product(name) values ('Milk');
insert into product(name) values ('Poteto');
insert into product(name) values ('Beef');

insert into product_person(person_id, product_id) values (1, 1);
insert into product_person(person_id, product_id) values (1, 2);
insert into product_person(person_id, product_id) values (1, 3);
insert into product_person(person_id, product_id) values (2, 1);
insert into product_person(person_id, product_id) values (2, 2);
insert into product_person(person_id, product_id) values (3, 1);
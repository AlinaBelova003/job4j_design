create table fauna(
	id serial primary key,
	name varchar(255) not null,
	avg_age int not null,
	discovery_data date
);

insert into fauna (name, avg_age, discovery_data) VALUES ('Гиганская черепаха', 29200, '1885-10-09');
insert into fauna (name, avg_age, discovery_data) VALUES ('Курносая обезьяна', 10300, '2010-10-05');
insert into fauna (name, avg_age, discovery_data) VALUES ('Утконос', 3652, '1802-05-08');
insert into fauna (name, avg_age, discovery_data) VALUES ('Морские свинки', 1825, '1580-20-11');
insert into fauna (name, avg_age, discovery_data) VALUES ('Pike fish', 6570, NULL);

select name FROM fauna where name LIKE '%FISH%';
select name FROM fauna where avg_age >= 10000 AND avg_age <= 21000;
select name FROM fauna where discovery_data IS NULL;
select name FROM fauna where discovery_data < '1950-10-09';
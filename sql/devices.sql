create table devices(
	id serial primary key,
	name text,
	price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Телефон', 100000), ('Планшет', 30000), ('Наушники', 9000);
insert into people(name) values ('Алина'), ('Максим'), ('Иван');
insert into devices_people(device_id, people_id) values (1, 3), (1, 2), (1, 1);
insert into devices_people(device_id, people_id) values (2, 3), (2, 2);
insert into devices_people(device_id, people_id) values (3, 3), (3, 1);

select d.name, avg(d.price)
from devices_people as dp
join devices d 
on dp.device_id = d.id
group by d.name;

select p.name, avg(d.price) as devices 
from devices_people as dp
join devices d 
on dp.device_id = d.id
join people p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 50000;


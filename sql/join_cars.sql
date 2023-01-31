create table car_bodies(
id serial primary key,
name_b varchar(50)
);

create table car_engines(
id serial primary key,
name_e varchar(50)
);

create table car_transmission(
id serial primary key,
name_t varchar(255)
);

create table cars(
id serial primary key,
name_cars varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmission(id)
);

insert into car_bodies(name_b) values ('Пикап'), ('Седан'), ('Лимузин'), ('Хетчбэк'), ('Кросовер'), ('Миневен');

insert into car_engines(name_e) values ('Бензиновые'), ('Дизельные'), ('Гибридные'), ('Китайские'), ('Тайваньские');

insert into car_transmission(name_t) values ('механика'), ('роботизированная коробка'), ('классический автомат'), ('что осталось из того и делали');

insert into cars(name_cars, body_id, engine_id, transmission_id) values
('Honda Accord', 2, 3, 2),
('Suzuki Ciaz', 2, 3, null),
('Toyota Hilux', null, 1, 3),
('Ford F150', 1, 2, 1),
('Rolls-Royce Phantom', 3, 3, 2),
('Skoda: Scala', 4, 1, 1),
('Audi RS3', 4, null, 3);

select distinct c.name_cars as "Машины", cb.name_b as "Тип кузова", 
ce.name_e as "Тип двигателя", ct.name_t as "Коробка передач" 
from cars as c
left join car_bodies as cb on c.body_id = cb.id
left join car_engines as ce on c.engine_id = ce.id
left join car_transmission as ct on c.transmission_id = ct.id;

select cb.name_b from car_bodies as cb
left join cars as c
on cb.id = c.body_id
where c.body_id is null;

select ce.name_e from car_engines as ce
left join cars as c
on ce.id = c.engine_id
where c.engine_id is null;

select ct.name_t
from car_transmission as ct
left join cars as c
on ct.id = c.transmission_id
where c.transmission_id is null;
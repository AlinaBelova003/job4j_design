create table type(
	id serial primary key,
	name varchar(255)
);
create table product(
	id serial primary key,
	name varchar(250),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into type(name) VALUES ('сыр'),('молоко'),('мороженое');

insert into product(name, type_id, expired_date, price) Values 
('Сыр моцарела', 1, '2023-01-12', 500.79),
('Сыр моцарела кружочки', 1, '2023-01-22', 580.08),
('Сыр брынза', 1, '2023-02-01', 610.9),
('Сыр брынза в воде', 1, '2023-11-01', 650.56),
('Сыр косичка', 1, '2022-10-12', 210.65),
('Сыр косичка с перцем', 1, '2022-11-12', 250.76),
('Сыр местный', 1, '2023-02-17', 380.54),
('Сыр с плесенью', 1, '2024-01-12', 1100.34),
('Сыр из козьего молока', 1, '2023-04-30', 900.42),
('Сыр грельеж', 1, '2023-11-06', 2050.7),
('Сыр биг', 1, '2022-12-03', 370.21),
('Фермерское подворье', 2, '2023-01-23', 70.63),
('Простаквашено', 2, '2023-02-23', 110.21),
('oreo мороженое', 3, '2023-10-21', 230.9),
('мороженое пломбир', 3, '2023-04-15', 73.23),
('магнат', 3, '2022-12-21', 90.98),
('cornetto', 3, '2023-10-21', 110.53);

select p.name 
from product as p
join type as t
on p.type_id = t.id
WHERE t.name LIKE '%сыр%';

select p.name from product as p
where p.name LIKE '%мороженое%';

select p.name from product as p
where expired_date < NOW();

select p.name from product as p
where p.price = (select max(p.price) from product as p);

select t.name, count(p.id) from type as t
join product as p 
on p.type_id = t.id
group by t.name;

select p.name from product as p
join type as t 
on p.type_id = t.id
where t.name LIKE '%сыр%' or t.name LIKE '%молоко%';

select t.name from type as t
join product as p 
on p.type_id = t.id
group by t.name
having count(p.id) < 10;

select * from product, type;
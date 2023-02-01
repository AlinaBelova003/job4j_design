create table departments(
id serial primary key,
name_department varchar(255)
);

create table employees(
id serial primary key,
name_emploees varchar(255),
departments_id int references departments(id)
);

create table teens(
id serial primary key,
name_people varchar(255),
gender char
);

insert into teens(name_people, gender) values 
('Alina', 'F'),
('Ivan', 'M'),
('Vera', 'F'),
('Oleg', 'M');


insert into departments(name_department) values 
('Юридический отдел'),
('Рекламный отдел'),
('Финансовый отдел'),
('Уборочный отдел'),
('Транспортный отдел');

insert into employees(name_emploees, departments_id) values 
('Юристы по притензиям', 1),
('Юристы заключение договоров', 1),
('Пиарщик в Инстаграмм', 2),
('Арт', 2),
('Бугалтерия', 3),
('Экономика и прогнозы', 3),
('Водители', 5),
('Логистика', 5);

select * from employees as e left join departments as d 
on e.departments_id = d.id;

select * from employees as e right join departments as d 
on e.departments_id = d.id;

select * from employees as e full join departments as d
on e.departments_id = d.id;

select * from employees as e cross join departments as d; 

select distinct d.name_department from departments as d left join employees as e
on d.id = e.departments_id where e.departments_id is null;

select distinct e.name_emploees, d.name_department from employees as e left join departments as d 
on e.departments_id = d.id;

select distinct e.name_emploees, d.name_department from departments as d right join employees as e
on d.id = e.departments_id;

select distinct * from teens as teens1 cross join teens as teens2
where teens1.gender <> teens2.gender;
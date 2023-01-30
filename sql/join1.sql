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
('Водители', 4),
('Логистика', 4),

select * from employees as e left join departments as d 
on e.departments_id = d.id;

select * from employees as e right join departments as d 
on e.departments_id = d.id;

select * from employees as e full join departments as d
on e.departments_id = d.id;

select * from employees as e cross join departments as d; 

select * from departments as d, count(e.id) join employees as e
on d.id = e.departments_id
where d.name_department is null;

select * from employees as e left join departments as d 
on e.departments_id = d.id;

select * from departments as d right join employees as e
on d.id = e.departments_id;

select * from teens as teens1 cross join teens as teens2
create table student(
	id serial primary key, 
	name text
);

create table subjects(
	id serial primary key, 
	name text
);

create table student_subjects(
	id serial primary key,
	mark float,
	student_id int references student(id),
	subject_id int references subjects(id)
);

insert into student(name) values ('Alina'), ('Petr'), ('Stas');
insert into subjects(name) values ('Математика'), ('Русский'), ('Информатика');
insert into student_subjects(student_id, subject_id, mark) values (1, 1, 3), (2, 1, 5), (3, 1, 5);
insert into student_subjects(student_id, subject_id, mark) values (1, 2, 4), (2, 2, 5), (3, 2, 3);
insert into student_subjects(student_id, subject_id, mark) values (1, 3, 3), (2, 3, 5), (3, 1, 4);

select avg(mark) from student_subjects;
select min(mark) from student_subjects;
select max(mark) from student_subjects;

select s.name, avg(mark) from student_subjects as ss
join subjects s on ss.subject_id = s.id 
group by s.name;

select s.name, avg(mark) from student_subjects as ss
join student s on ss.student_id = s.id
group by s.name;

select s.name, avg(ss.mark) 
from student_subjects as ss 
join student s 
on ss.subject_id = s.id 
group by s.name 
having avg(ss.mark) > 4.2;



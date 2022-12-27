create table student(
	id serial primary key,
	name varchar(50),
	age int,
	start_data date
);
insert into student(name, age, start_data) values('Alina', 18, '2022-10-11');
select * from student;												 
update student set age = 17;
select * from student;
delete from student;
select * from student; 
create table phone(
	id serial primary key,
	phon_namber int
);

create table person(
    id serial primary key,
    name varchar(255),
	phone_id int references phone(id) unique
);

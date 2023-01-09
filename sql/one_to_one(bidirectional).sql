create table phone2(
	id serial primary key,
	phon_namber int
);

create table person(
    id serial primary key,
    name varchar(255),
	phone_id int references phone(id) unique
);

create table phone_phone(
    id serial primary key,
    phone_id int references phone(id) unique,
    person_id int references person(id) unique
);
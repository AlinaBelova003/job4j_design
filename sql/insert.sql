create table role(
	id serial primary key,
	name_role varchar(255)
);

create table rules (
	id serial primary key,
	name_rules varchar(255)
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

insert into role(name_role) VALUES ('Castomer1');
insert into role(name_role) VALUES ('Castomer2');

insert into rules(name_rules) VALUES ('freeze');
insert into rules(name_rules) VALUES ('do not throw');

insert into role_rules(role_id, rules_id) VALUES (1, 2);
insert into role_rules(role_id, rules_id) VALUES (1, 1);
insert into role_rules(role_id, rules_id) VALUES (1, 2);


create table category(
	id serial primary key,
	name_category varchar(255)
);

insert into category(name_category) Values ('assembled');
insert into category(name_category) Values ('send');

create table state(
	id serial primary key,
	name_state varchar(255)
);

insert into state(name_state) Values ('medicines');
insert into state(name_state) Values ('design');

create table users(
id serial primary key,
    name_user varchar,
    role_id integer references role(id)
);
insert into users(name_user, role_id) VALUES ('Alina', 1);
insert into users(name_user, role_id) VALUES ('Ivan', 2);

create table item(
	id serial primary key,
	name_item varchar(255),
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

insert into item(name_item, users_id, state_id, category_id) Values ('paint', 1, 1, 1);
insert into item(name_item, users_id, state_id, category_id) Values ('crystals', 2, 2, 2);

create table comments(
	id serial primary key,
	name_comment varchar(255),
	item_id int references item(id)
);

create table attachs(
	id serial primary key,
	name_attachs varchar(255),
	item_id int references item(id)
);

insert into comments(name_comment, item_id) VALUES ('no', 1);
insert into comments(name_comment, item_id) VALUES ('no', 2);

insert into attachs(name_attachs, item_id) VALUES ('no', 1);
insert into attachs(name_attachs, item_id) VALUES ('yes', 2);
create table customer (
 id serial primary key,
	name_customer varchar(50)
);

create table brand (
id serial primary key,
    name_brand varchar(50)
); 

create table devices (
	id serial primary key,
    name_devices varchar(50),
	brand_id int references brand(id)
);

create table orders(
id serial primary key,
name_order varchar(50),
customer_id int references customer(id),
devices_id int references devices(id)
);
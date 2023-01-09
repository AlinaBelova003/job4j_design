create table price_product(
	id serial primary key,
	price int
);

create table order_shop(
	id serial primary key,
	name varchar(255),
	price_product_id int references price_product(id)
);
	
	insert into price_product(price) VALUES (3000);
	insert into order_shop(name, price_product_id) values ('Камера', 1);
	
	select * from order_shop;
	
	select * from price_product where id in (select price_product_id from order_shop);
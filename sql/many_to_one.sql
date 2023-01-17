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
	insert into price_product(price) values (30000);
	insert into price_product(price) values (20000);

	insert into order_shop(name, price_product_id) values ('Камера', 1);
	insert into order_shop(name, price_product_id) values ('Планшет', 2);
	insert into order_shop(name, price_product_id) values ('Телефон', 3);
	insert into order_shop(name) values ('Камера');

	
	select os.name, pp.price
	from order_shop as os join price_product as pp on os.price_product_id = pp.id;

	select os.name as Имя, pp.price Цена
	from order_shop as os join price_product as pp on os.price_product_id = pp.id;

	select os.name "Имя товара", pp.price Цена
	from order_shop os join price_product pp on os.price_product_id = pp.id;


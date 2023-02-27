CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into customers values (1, 'Anna', 'Bilt', 21, 'F'),
(2, 'Bob', 'Marle', 16, 'U'),
(3, 'Greme', 'Addams', 12, 'S'),
(4, 'Oleg', 'Pavlov', 19, 'R'),
(5, 'Maksim', 'Nikitin', 27, 'U'),
(6, 'Lizy', 'Roberts', 22, 'F');

select min(age) from customers;

SELECT last_name AS Фамилия, age from customers where age = (SELECT MIN(age) FROM customers);

insert into orders values (1, 3, 1), (2, 5, 2), (3, 8, 6), (4, 16, 5);

select * from customers where customers.id not in (select customer_id from orders);


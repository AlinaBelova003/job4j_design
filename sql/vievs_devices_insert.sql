insert into customer(name_customer) values ('Alina'), ('Vera'), ('Igor');

insert into brand(name_brand) values ('DEXP'), ('Sumsund'), ('Honor');

insert into devices(name_devices, brand_id) values
 ('Планшет', 2), ('Фен', 1), ('Телефон', 3), ('весы', 1), ('Посудомойка', 2), ('Плойка', 1);

insert into orders(name_order, customer_id, devices_id) values 
('2327482', 1, 1),
('8788964', 1, 3),
('4356767', 1, 5),
('7577356', 2, 6),
('9785631', 2, 3),
('9892427', 3, 2);
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('Milk', 'Prostacvasha', 5, 150);
insert into products (name, producer, count, price) VALUES ('Chees', 'Prostacvasha', 8, 150);
insert into products (name, producer, count, price) VALUES ('Cheek', 'Prostacvasha', 3, 150);
insert into products (name, producer, count, price) VALUES ('Мороженое', 'Prostacvasha', 5, 130);
insert into products (name, producer, count, price) VALUES ('Сырники', 'Prostacvasha', 8, 220);

create or replace function discount()
returns trigger as
$$
    BEGIN
	  update products
	  set price = price - (price * 0.2)
	  where count <= 5 AND id = new.id;
	  return NEW;
	END;
$$
LANGUAGE 'plpgsql';


create trigger discount_trigger
after insert
on products
for each row
execute procedure discount();

alter table products disable trigger discount_trigger;

create or replace function tax_13()
return trigger as
$$
  Begin
  update products
  set price = price + (price * 0.13)
  where id = new.id;
  return new;
  END;
$$
LANGUAGE 'plpgsql';

create trigger tax_13_trigger
BEFORE INSERT on products
for each row
execute procedure tax_13();

select * from products;


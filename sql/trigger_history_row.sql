create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_new_volume_table()
 returns trigger as
 $$
 Begin
     insert into history_of_price(name, price, timestamp) Values
     (new.name, new.price, new.timestamp);
    return new;
 End;
 $$
 LANGUAGE 'plpgsql';
 
 create trigger insert_new_volume_table_trigger
 AFTER INSERT on products
 for each row
 execute procedure insert_new_volume_table();
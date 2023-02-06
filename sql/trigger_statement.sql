create or replace function tax() 
   returns trigger as 
   $$
     BEGIN
	 update products
	 set price = price - price * 0.2
	 where id = (select id from inserted) and count <= 5;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
  after insert on products
  referencing new table as inserted
  for each statement
  execute procedure tax();

 create or replace function tax_13_percent()
returns trigger as
$$
    Begin
update products
set price = price - price + 0.13
where id = (select id from inserted);
return new;
    End;
$$
LANGUAGE 'plpgsql';

create trigger tax_13_percent_trigger
  after insert on products
  referencing new table as inserted
  for each statement
  execute procedure tax_13_percent();

 select * from products;




create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prob varchar, i_count integer, i_price integer)
LANGUAGE 'plpgsql'
as $$  
  BEGIN 
    insert into products(name, producer, count, price)
      values (i_name, prob, i_count, i_price);
  END
$$;

call insert_data('Procedure1', 'Producer_1', 15, 32);
call insert_data('Procedure2', 'Producer_2', 3, 50);
call insert_data('Procedure3', 'Producer_3', 8, 115);
call insert_data('Procedure4', 'Procedure_4', 0, 20);
call insert_data('Procedure4', null, 5, 198);

 select * from products;

create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
   BEGIN 
   if u_count > 0 THEN
       update products set count = count - u_count where id = u_id;
   end if;
   if tax > 0 THEN 
     update products set price = price + price * tax;
	 end if;
   END;
 $$;
 
 call update_data(10, 0, 1);
 call update_data(0, 0.2, 0);
 
 
 select * from products;
 
  create or replace procedure delete_data(d_id integer)
  language 'plpgsql'
as $$
   BEGIN 
   delete from products as p where p.count = 0;
   delete from products as p where p.producer is null;
   END;
 $$;
 
  call delete_data(2);
  
  select * from products;
 
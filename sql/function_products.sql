create or replace function f_inseert_date(i_name varchar, prod varchar, i_coount integer, i_price integer)
returns void
language 'plpgsql'
as 
 $$
   BEGIN 
     insert into products(name, producer, count, price) VALUES (i_name, prod, i_coount, i_price);
   End;
 $$;
 
 select f_inseert_date('Product1', 'Producer1', 25, 30);
 select f_inseert_date('product_2', 'producer_2', 15, 32);
 select f_inseert_date('product_3', 'producer_3', 8, 115);
 
 select * from products;
 

create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

select * from products;

select f_update_date(0, 0.2, 0);

select * from products;


create or replace function f_delete(d_count integer, producer varchar, d_id integer) 
returns varchar
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
	  delete from products as p where d_count < 10 and id = d_id;
	  delete from products as p where p.producer LIKE '%3';
	  return result;
	  end;
$$

select f_delete(0, 'Product2', 0);

select * from products;
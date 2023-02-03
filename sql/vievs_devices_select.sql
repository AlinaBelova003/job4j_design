create view show_customer_with_2_or_more_devisec
as select c.name_customer as customer, count(b.name_brand), b.name_brand as brand
from customer as c
join orders as o on c.id = o.customer_id
join devices as d on o.devices_id = d.id
join brand as b on d.brand_id = d.id
group by (c.name_customer, b.name_brand)
having count(b.name_brand) > 2; 

select * from show_customer_with_2_or_more_devisec
CREATE TABLE IF NOT EXISTS public.product
(
    id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    name text COLLATE pg_catalog."default" NOT NULL,
    type_id integer,
    expired_date date,
    price double precision,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT product_type_id_fkey FOREIGN KEY (type_id)
        REFERENCES public.type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

CREATE TABLE IF NOT EXISTS public.type
(
    id integer NOT NULL DEFAULT nextval('type_id_seq'::regclass),
    name text COLLATE pg_catalog."default",
    CONSTRAINT type_pkey PRIMARY KEY (id)
)

insert into type (name) values ('СЫР'), ('МОЛОКО');
insert into product (name, type_id, expired_date, price)
values ('Сыр плавленный', 1, date '2020-01-01', 350),
('Молоко', 2, date '2021-01-01', 450),
('Сыр моцарелла', 1, date '2020-02-01', 550),
('Мороженное', 2, date '2021-07-01', 80);



1)
select * from product
join type
on product.type_id = type.id
where type.name = 'СЫР';

2)
select * from product
join type
on product.type_id = type.id
where type.name like '%мороженое%';

3)
select * from product
join type
on product.type_id = type.id
where product.expired_date > '2019-12-01';

4)
select max(price) from product;

5)
select t.name as nameOftype, count(p.name)
from product as p
inner join type as t
on t.id = p.type_id
group by t.name;

6)
select * from product
join type
on product.type_id = type.id
where type.name = 'СЫР' and type.name = 'МОЛОКО';

7)
select t.name as nameOftype, count(p.name)
from product as p
inner join type as t
on t.id = p.type_id
group by t.name
having count(p.name) < 10;

8)
select * from product
inner join type
on type.id = product.type_id;







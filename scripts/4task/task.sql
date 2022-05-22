create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name,avg_age,discovery_date)
values ('bacteria', 8888, date '2020-01-01');
insert into fauna(name,avg_age,discovery_date)
values ('bird', 3333, date '2019-05-25');
insert into fauna(name,avg_age,discovery_date)
values ('cat', 20000, date '2022-07-19');
insert into fauna(name,avg_age,discovery_date)
values ('dog', 15000, date '2021-09-06');
insert into fauna(name,avg_age,discovery_date)
values ('fish', 10000, date '2021-12-01');
insert into fauna(name,avg_age,discovery_date)
values ('bear', 5000, date '2021-08-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';

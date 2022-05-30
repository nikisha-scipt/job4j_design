create table if not exists company (
    id serial primary key,
    name character varying
);

create table if not exists person (
    id serial primary key,
    name character varying,
    company_id int references company(id)
);

insert into company(name) values ('sber'), ('alfa'), ('tinkoff');
insert into person(name,company_id) values ('Danil', 1), ('Anton', 2), ('Boris', 3);

1)
a)
select p.name from person as p
left outer join company as c
on c.id = p.company_id
where c.id != 5;

b)
select c.name as nameofcompany, p.name as nameofperson from company as c
left outer join person as p
on p.company_id = c.id
group by c.name, p.name;

2)
a)
select c.name, count(p.company_id) from person as p
left outer join company as c
on c.id = p.company_id
group by c.name, p.company_id
order by p.company_id ASC
limit 1;

b)
select c.name, count(p.company_id) from person as p
left outer join company as c
on c.id = p.company_id
group by c.name, p.company_id
having max(p.company_id) < count(p.company_id);




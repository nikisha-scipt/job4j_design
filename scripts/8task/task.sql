create table departments (
	id serial primary key,
	name text
);

create table employees (
	id serial primary key,
	name text,
	id_dep int references departments(id)
);

insert into departments (name) values ('dep1'), ('dep2'), ('dep3');
insert into employees (name,id_dep) values ('Danil', 1), ('Anastasiya', 2),
('Andry', 3), ('Sergey', null), ('Maria', null);

2)
select * from employees as e
left outer join departments as d
on e.id_dep = d.id;

select * from employees as e
right outer join departments as d
on e.id_dep = d.id;

select * from employees as e
full outer join departments as d
on e.id_dep = d.id;

select * from employees as e
cross join departments as d;

3)
select d.name
from departments as d
left outer join employees as e
on d.id = e.id_dep
where e.id_dep is null
group by d.name;

4)
select * from departments as d
left outer join employees as e
on e.id_dep = d.id
where e.id_dep is not null;

select * from departments as d
right outer join employees as e
on e.id_dep = d.id
where e.id_dep is not null;

5)
create table teens (
	id serial primary key,
	name text,
	gender char(1)
);
insert into teens(name, gender) values ('junior', 'm'), ('middle', 'w'), ('senior', 'w');
select * from teens t1 cross join teens t2 where t1.gender != t2.gender;


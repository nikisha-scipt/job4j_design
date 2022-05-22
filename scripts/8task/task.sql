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

select * from employees as e
left outer join departments d
on e.id_dep = d.id where e.id_dep is null;

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
select m1.gender as a, m2.gender as b, (m1.gender != m2.gender) as "a&b=" from teens m1 cross join teens m2;


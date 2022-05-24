create table bodies (
	id serial primary key,
	color varchar(30)
);

create table transmissions (
	id serial primary key,
	type varchar(30) not null
);

create table engines (
	id serial primary key,
	name varchar(30) not null,
	power int not null
);

create table cars (
	id serial primary key,
	name varchar(30),
	id_body int references bodies(id),
	id_transmission int references transmissions(id),
	id_engine int references engines(id)
);

insert into bodies(color) values ('black'), ('green'), ('orange');
insert into transmissions(type) values ('mechanical'), ('auto'), ('semi-automatic');
insert into engines(name, power) values ('gas turbine', 6000), ('piston', 4000), ('rotary piston', 5000);
insert into cars(name,id_body,id_transmission, id_engine)
values ('BMW', 1, 1, 1), ('mercedes', 2, 2, 2),('LADA', 3, 3, 3);

1)
select c.name as nameofcar, b.color, t.type, e.name, e.power from cars as c
left outer join bodies b
on c.id_body = b.id
left outer join transmissions t
on c.id_transmission = t.id
left outer join engines e
on c.id_engine = e.id
group by c.name,b.color,t.type,e.name,e.power;

2)
select c.name as car, b.color as colorofbody, t.type as type, e.name as nameofengine
from cars as c
left join bodies as b on c.id_body != b.id
left join transmissions as t on c.id_transmission != t.id
left join engines as e on c.id_engine != e.id
group by c.name, b.color, t.type, e.name;



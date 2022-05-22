create table trains (
	id serial primary key,
	name text not null
);

create table carriagies (
	id serial primary key,
	name text not null,
	id_train int references trains(id)
);

insert into trains (name) values ('U1');
insert into trains (name) values ('U2');
insert into trains (name) values ('U3');
insert into trains (name) values ('U4');

insert into carriagies (name, id_train) values ('passenger', 1);
insert into carriagies (name, id_train) values ('reserved', 1);
insert into carriagies (name, id_train) values ('compartment', 1);
insert into carriagies (name, id_train) values ('restaurant', 1);

insert into carriagies (name, id_train) values ('passenger', 2);
insert into carriagies (name, id_train) values ('passenger', 2);
insert into carriagies (name, id_train) values ('passenger', 2);

insert into carriagies (name, id_train) values ('compartment', 3);
insert into carriagies (name, id_train) values ('compartment', 3);
insert into carriagies (name, id_train) values ('compartment', 3);

select t.name as NameOfTrain, c.name as TypeOfCarriege
from trains as t
inner join carriagies as c
on t.id = c.id_train;

select t.id as idoftrain, c.id_train as idofcarriages
from trains as t
inner join carriagies as c
on t.id = c.id_train;

select * from trains
inner join carriagies as c
on trains.id = c.id_train;
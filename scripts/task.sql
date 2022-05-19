create table employee (
	id serial primary key,
	name varchar(55) not null,
	surname varchar(55) not null,
	city text not null,
	age integer not null,
	salary real not null,
	dateOfBirth date not null
);
insert into employee (name, surname, city, age, salary, dateOfBirth) values ('Danil', 'Nikishin', 
																			 'Moscow', '26', 
																			 '1111.2', '1996-01-11');

update employee set name = 'Andrey', surname = 'Fedorov', age = '30', salary = '11111.2', dateOfBirth = '2000-01-01';
delete from employee;K
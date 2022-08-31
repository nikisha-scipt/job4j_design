create table books (
    id serial primary key,
    title text not null,
    author varchar(30) not null,
    price real not null,
    amount int not null
);

insert into books(title, author, price, amount) values
    ('Мастер и Маргарита', 'Булгаков М.А.', 670.99, 3),
    ('Белая гвардия', 'Булгаков М.А.', 540.50, 3),
    ('Идиот', 'Достоевский Ф.М.', 460.00, 15),
    ('Стихотворения и поэмы', 'Есенин С.А.', 550.00, 25);

set session characteristics as transaction isolation level serializable;

start transaction;
insert into books(title, author, price, amount) values
    ('Война и мир', 'Толстой Л.Н.', 970.99, 5);
commit transaction;

start transaction;
delete from books;
drop table books;
rollback transaction;

start transaction;
savepoint mysavepoint;
insert into books(title, author, price, amount) values
    ('Test','Test', 55.5, 15);
delete from books where title = 'Test';
update books set title = 'Test' where title = 'Идиот';
rollback to mysavepoint;
select * from books;
commit transaction;

start transaction;
insert into books(title, author, price, amount) values
    ('Test','Test', 55.5, 15);
delete from books where title = 'Test';
update books set title = 'Test' where title = 'Идиот';
savepoint mysavepoint;
drop table books;
savepoint  my_second_savepoint;
rollback to mysavepoint;
select * from books;
commit transaction;




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

create view show_books_with_max_price
    as select title as "Название",
    author as "Автор",
    price as "Цена книги",
    amount as "В кол-ве",
    max(price) as "Самая дорогая книга"
        from books
        where
            round((price * amount)::numeric, 2) < 5000
            and
            title like '%_ %_'
            and price = (select max(price) from books)
    group by title, author, price, amount
    order by author desc;
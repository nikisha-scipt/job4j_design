create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- 1
create trigger first_trigger
    after insert
    on products
    referencing new table as temp_table
    for each statement
    execute procedure after_tax();

create or replace function after_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + (price * 0.13)
        where id = (select id from temp_table);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

-- 2
create trigger second_trigger
    before insert
    on products
    for each row
    execute procedure before_tax();

create or replace function before_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - (price * 0.13)
        where id = new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';


-- 3
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger three_trigger
    after insert
    on product
    for each row
    execute procedure insert_history();

create or replace function insert_history()
     returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values (NEW.name, NEW.price, now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

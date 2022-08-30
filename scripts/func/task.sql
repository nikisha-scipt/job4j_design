create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure drop_row_procedure(u_id)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = u_id;
    END;
$$;

create or replace function drop_row_func(u_id)
returns integer
language 'plpgsql'
as $$
    declare
        result integer;
    BEGIN
        delete from products where id = u_id;
        select into result u_id;
        return result;
    END;
$$;


create or replace procedure drop_row_where_count_is_null()
language 'plpgsql'
as
$$
    begin
        delete from products where count = 0;
    end;
$$;
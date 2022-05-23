users - role = many-to-one (many - со стороны внешнего ключа)
role - rules = many-to-many
item - users = many-to-one
item - comments = one-to-many
item - attachs = one-to-many
item - category = many-to-one
item - state = many-to-one

create table role (
    id_role serial primary key,
    name text not null
);

create table rules (
    id_roles serial primary key,
    name text not null
);

create table category (
    id_category serial primary key,
    name text not null
);

create table state (
    id_state serial primary key,
    name text not null
);

create table users (
    id_user serial primary key,
    name varchar(50) not null
    surname varchar(50) not null
    id_role int references role(id_role)
);


create table role_rules (
    id_role_rules serial primary key,
    id_role int references role(id_role),
    id_rules int references rules(id_rules)
)

create table item (
    id_item serial primary key,
    name text not null,
    id_user int references users(id_user),
    id_category int references category(id_category),
    id_state int references state(id_state)
);

create table comments (
    id_comments serial primary key,
    name text not null,
    id_item int references item(id_item)
);

create table attachs (
    id_attachs serial primary key,
    name text not null,
    id_item int references item(id_item)
);




create table users (
    id_user serial primary key,
    name varchar(50) not null
    surname varchar(50) not null
);

create table role (
    id_role serial primary key,
    name text not null
    id_user int references users(id_user)
);

create table roles (
    roles serial primary key,
    name text not null,
    id_role int references role(id_role),
    id_user int references users(id_user)
);

create table item (
    id_item serial primary key,
    name text not null,
    id_user int references users(id_user)
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

create table category (
    id_category serial primary key,
    name text not null,
    id_item int references item(id_item)
);

create table state (
    id_state serial primary key,
    name text not null,
    id_item int references item(id_item)
);



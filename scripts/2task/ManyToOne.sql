create table car_showroom (
    id_room serial primary key,
    name character(50) not null
);

create table cars (
    id_car serial primary key,
    name character(50) not null,
    id_room int references car_showroom(id_room)
);

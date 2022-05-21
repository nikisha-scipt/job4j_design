insert into users (name, surname)
values ('Danil', 'Nikishin');

insert into role (name, id_user)
values ('programmer', 1);

insert into roles (name, id_role, id_user)
values ('high', 1, 1);

insert into item (name, id_user)
values ('one item', 1);

insert into comments (name, id_item)
values ('first comment', 1);

insert into attachs (name, id_item)
values ('first attachs', 1);

insert into category (name, id_item)
values ('first attachs', 1);

insert into state (name, id_item)
values ('first attachs', 1);
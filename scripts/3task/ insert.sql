insert into users (name, surname, id_role)
values ('Danil', 'Nikishin', 1);

insert into role (name)
values ('programmer');

insert into roles (name, id_role, id_user)
values ('high', 1, 1);

insert into item (name, id_user, id_category, id_state)
values ('one item', 1, 1, 1);

insert into comments (name, id_item)
values ('first comment', 1);

insert into attachs (name, id_item)
values ('first attachs', 1);

insert into category (name)
values ('first attachs');

insert into state (name)
values ('first attachs');
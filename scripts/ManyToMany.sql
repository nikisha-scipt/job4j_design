create table job (
    id_job serial primary key,
    name character(50) not null
);

create table person (
    id_person serial primary key,
    name character(50) not null
);

create table person_job (
    id_person_job serial primary key,
    person_id int references person(id_person),
    job_id int references job(id_job)
);

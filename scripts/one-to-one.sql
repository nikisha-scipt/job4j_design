create table job (
    id_job serial primary key,
    name character(50) not null
);

create table person (
    id_person serial primary key,
    name character(50) not null,
    job_id int referencesK job(id_job) unique
);
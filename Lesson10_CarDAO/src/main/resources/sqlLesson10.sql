create table car
(
    id    int     not null,
    name  varchar(100) null,
    color varchar(50)  null,
    price double    null,
    constraint Car_pk
        primary key (id)
);

create unique index Car_id_uindex
    on car (id);
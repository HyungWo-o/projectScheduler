create table schedule
(
    id       bigint auto_increment
        primary key,
    todo     varchar(200)             null,
    author   varchar(10)              null,
    password varchar(20)              null,
    isrt_dt  datetime default (now()) null,
    updt_dt  datetime default (now()) null
);

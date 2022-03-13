create table user_cextb00 (
    user_id int not null,
    login   varchar(25) not null,
    email   varchar(50) not null,
    constraint USER_CEXTB00_PK
        primary key (user_id)
);

create sequence hibernate_sequence start with 1 increment by 1;
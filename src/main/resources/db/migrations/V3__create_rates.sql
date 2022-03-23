create table rate_cextb01 (
                              rate_id bigint not null,
                              base varchar(255),
                              base_value double,
                              conversion_rate double,
                              dateTime timestamp,
                              user_id bigint,
                              primary key (rate_id)
);

create table rate_detail_cextb02 (
                             rate_detail_id bigint not null,
                             currency_value double not null,
                             conversion_tax double not null,
                             currency_name varchar(255) not null,
                             primary key (rate_detail_id, currency_name)
);

alter table rate_detail_cextb02
    add constraint FKg4q9a131wpa78cg8wsw1kougo
        foreign key (rate_detail_id)
            references rate_cextb01;
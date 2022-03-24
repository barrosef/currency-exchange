create table rate_cextb01 (
                              rate_id bigint not null,
                              base varchar(3),
                              base_value double,
                              conversion_tax double not null,
                              date_time timestamp,
                              symbol_to_convert varchar(3),
                              user_id bigint,
                              primary key (rate_id)
);
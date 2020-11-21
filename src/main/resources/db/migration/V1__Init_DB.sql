create table user_account
(
    id         bigint not null auto_increment,
    username   varchar(16) not null unique,
    password   varchar(255) not null ,
    first_name varchar(16) not null ,
    last_name  varchar(16) not null ,
    created_at date not null ,
    role       varchar(255) not null ,
    status     varchar(255) not null ,
    primary key (id)
) engine = InnoDB
create table comments (
    id bigint not null auto_increment,
    title varchar(100) not null,
    body varchar(100) not null,
    creates_at datetime not null,
    user_id bigint,
    topic varchar(100) not null,
    status tinyint not null,
    

    primary key (id),
    FOREIGN KEY (user) REFERENCES usuarios(id)
);
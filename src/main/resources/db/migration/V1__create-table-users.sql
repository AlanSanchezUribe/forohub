create table usuarios (
    id bigint not null auto_increment,
    username varchar(100) not null,
    email varchar(100) not null,
    password varchar(300) not null,
    

    primary key (id)
);

insert into `forohub`.usuarios (username, email, password) 
values ('Alan', 'alan@gmail.com', '$2a$10$eUrl9p8hvlnzwXLA3xBEkO1cHjGHcI8Puqi.xVzYkru/FCdfGhi6e');

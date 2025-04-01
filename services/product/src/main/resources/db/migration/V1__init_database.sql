create table if not exists category
(
    id serial not null primary key ,
    description varchar(255) ,
    name varchar(255)
);

create table if not exists product
(
    id serial not null primary key ,
    description varchar(255) ,
    name varchar(255),
    available_quantity double precision not null,
    price numeric(38,2)  not null,
    category_id integer,
                constraint category_foreign_key
                 foreign key(category_id) references category(id)
);

--create sequence if not exists category_seq increment by 50;
--create sequence if not exists product_seq increment by 50;
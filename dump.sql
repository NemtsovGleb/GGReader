create table Person(
                       id int generated always as identity primary key,
                       username varchar(30) check(length(username)>2) not null,
                       role varchar(30) not null,
                       password varchar(30)

);

alter table Person alter column password type varchar(255);
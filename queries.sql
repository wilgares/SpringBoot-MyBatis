CREATE DATABASE nazca

create table USERSBOOT(
id int not null primary key auto_increment,
firstname varchar(25) not null,
lastname varchar(25) not null,
email varchar(25) not null,
password varchar(25) not null,
age varchar(25) not null,
phone varchar(25) not null
);

insert into USERSBOOT(firstname,lastname, email, password, age, phone) values('Juan', 'Aguirre', 'Juan@gmail.com', 'clavecita', '25', '22939456');
insert into USERSBOOT(firstname,lastname, email, password, age, phone) values('Ana', 'Aguirre', 'Ana@gmail.com', 'password', '29', '23457866');
insert into USERSBOOT(firstname,lastname, email, password, age, phone) values('Julio', 'Aguirre', 'Julio@gmail.com', 'llave', '35', '97556566');
insert into USERSBOOT(firstname,lastname, email, password, age, phone) values('Rosa', 'Aguirre', 'Rosa@gmail.com', 'encrypt', '18', '94545454');

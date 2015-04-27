create database kartotek;

use kartotek;

drop table if exists person ;

create table person
(
   id int not null primary key auto_increment,
   navn varchar(50) NOT NULL,
   alder int NOT NULL
);       

# start data
insert into person (navn, alder) values 
	('Hans Jensen', 23),
	('Ulla Jacobsen', 25),
	('Peter Hansen', 25);
	
	
select * from person;


# queries to be used in program

select * from person limit 1,1;


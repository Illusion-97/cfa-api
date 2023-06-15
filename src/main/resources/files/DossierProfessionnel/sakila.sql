use sakila;

select * from film;
select * from film_text;

create table film_test as select * from film;
create table film_text_test as select * from film_text;

insert into film_text_test
select * from film_text_test;

create fulltext index fx_desc on film_test(description);

SELECT * FROM film_test
WHERE MATCH(description) AGAINST ('china');

SELECT * FROM film_text_test
WHERE description LIKE '%china%'
OR description like '%china%';

SELECT * FROM film_text_test
WHERE description LIKE '%china%'
and description like '%mad%';

SELECT * FROM film_test
WHERE MATCH(description) AGAINST ('mad' IN BOOLEAN MODE);


select description, MATCH(description) AGAINST ('ancient') as pertinence;

SELECT * FROM film _test
WHERE MATCH(description) AGAINST 
('a' IN BOOLEAN MODE);


create table test (
id int auto_increment,
nom varchar(30) not null check(length(nom)>=2),
prenom varchar(30) not null check(length(prenom)>=2),
sexe char(1),
date_naissance date,
rue varchar(200),
code_postal char(5),
ville varchar(50),
deseription TEXT,
CONSTRAINT pk_test PRIMARY KEY(id),
CONSTRAINT ck_code CHECK(LENGTH(code_postal) >= 3 OR code_postal = 99 ),
CONSTRAINT ck_nom CHECK(LENGTH(nom) >= 2 AND LENGTH(prenom) >= 2),
UNIQUE INDEX ux_nom_prenem(nom, prenom),
index ix_ville(ville),
fulltext index fx_desc(description));


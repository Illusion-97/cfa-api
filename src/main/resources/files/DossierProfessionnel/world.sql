
show databases;

use world;
select * from country;

select count(*) from country;

select * from information_schema.tables
where TABLE_SCHEMA = 'world';

select VERSION();

select length(cast('toto' as char(10))),
 char_length(cast('toto' as char(10)));
 
 set @@lc_time_names = 'zh_cn';
 select current_timestamp();
 select monthname(current_timestamp()), dayname(current_timestamp());
 
 select date_format(current_timestamp(), 'le %W %d du mois de %M');
 
 Alter table employees
 add constraint fk_countryEmp
 foreign key (CountryCode)
 references world.country(`code`);
 
 Alter table countryLanguage
 add constraint fk_countryLang
 foreign key (CountryCode)
 references country(`code`);
 
 alter table city 
 add constraint fk_country 
 foreign key (CountryCode) 
 references country(`Code`);
 
 describe city;
 
 show engines;
 
 start transaction;
 set foreign_key_checks = 0;
 delete from country;
 select * from country;
 set foreign_key_checks = 1;
 rollback;
 
 select * from country;
 
UPDATE Toto
SET solde = solde - 100
WHERE id = 1;

UPDATE Tonton
SET solde = solde + 100
WHERE id = 2;

create table test (
id int auto_increment,
nom varchar(30),
prenom varchar(30),
sexe char(1),
date_naissance date,
rue varchar(200),
code_postal char(5),
ville varchar(50),
constraint pk_test primary key(id),
index ix_nom_prenom(nom,prenom),
index ix_ville(ville),
fulltext index fx_desc(description));







 
 
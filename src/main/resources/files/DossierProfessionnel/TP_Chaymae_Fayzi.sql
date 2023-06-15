use world;

-- Afficher le nom de chaque ville avec son nom de pays
select country.name, city.name
FROM city
JOIN country ON city.countrycode = country.code;

-- Combien y a-t-il de pays ?
select count(*) from country;

-- Combien y a-t-il de pays en ‘Europe’ ?
select count(*) from country
where continent like 'Europe';

-- Combient y a-t-il de pays par continent ?
select count(*), continent from country
group by continent;

-- Population par continent
select count(population), continent from country
group by continent;


-- Population urbaine de chaque pays
select sum(city.population), country.name  from city
join country
ON city.countrycode = country.code
group by country.name;

-- Quelle est la capitale de chaque pays ?
select country.capital, country.name from country;

-- Y a-t-il des pays sans capitale, si oui quels sont-ils ?
select country.capital, country.name from country
where country.capital is null;

-- Quelle est l'année d'indépendance la plus récente ?
select max(IndepYear) from country;

-- Quels sont les pays à avoir été le plus récemment indépendants ?


-- Nombre de langues officielles par pays
select count(countrylanguage.language), country.name from countrylanguage
join country on country.code = countrylanguage.countryCode
where IsOfficial = 'T' 
group by country.name;


-- Quels sont les pays ayant plus d'une langue officielle ?
select count(countrylanguage.language), country.name from countrylanguage
join country on country.code = countrylanguage.countryCode
where IsOfficial = 'T' 
group by country.name
having count(countrylanguage.language) > 1;

-- Y a-t-il des pays sans langue officielle ?
select count(countrylanguage.language), country.name from countrylanguage
join country on country.code = countrylanguage.countryCode
where IsOfficial = 'F' 
group by country.name;

-- Quels sont les pays ayant le plus grand nombre de langues officielles ?
select count(countrylanguage.language), country.name from countrylanguage
join country on country.code = countrylanguage.countryCode
where IsOfficial = 'T' 
group by country.name
having count(countrylanguage.language) = ( select max(countrylanguage.language)  from countrylanguage
join country on country.code = countrylanguage.countryCode
where IsOfficial = 'T' 
group by country.name);

-- Nombre de locuteurs de chaque langue
select sum(countrylanguage.percentage * (select population from country where country.code = countrycode) ) , countrylanguage.language from countrylanguage
group by countrycode, countrylanguage.language;

select * from ( select name, continent, lifeExpectancy,
row_number() over(partition by continent order by lifeExpectancy desc)
as rang from country) esperanza
where rang <=3;
with cte_life as (select name, continent, lifeExpectancy, row_number() over(partition by continent order by lifeExpectancy desc)
as rang from country)







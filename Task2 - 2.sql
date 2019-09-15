drop database if exists xf;
create database xf;
use xf;

drop table if exists country;
create table Country (
	countryId int primary key,
    name varchar(50)
);
drop table if exists city;
create table city (
	cityid int primary key,
	countryid int,
    name varchar(50),
    population int,
    constraint foreign key (countryid) references country(countryid)
);
drop table if exists building;
create table building (
	buildingId int primary key,
    cityId int,
    name varchar(50),
    floors int,
    constraint foreign key (cityid) references city(cityid)
);

insert into country values(1, "Poland"), (2, "Germany"), (3, "France");
insert into city values(1, 1, "Krakow", 0), (2, 1, "Warsaw", 0), 
						(3, 1, "Lodz", 399), (4, 2, "Berlin", 21), 
                        (5, 3, "Stuttgart", 69);
insert into building values(1, 4, "X-Formation", 0), (2, 4, "Casino-Warsaw", 1),
							(3, 4, "Google", 3), (4, 4, "Amazon", 4), (5, 4, "Alibaba", 5);
                        
/* main code */
-- 2.1
select * 
from country c1
where c1.countryid in (select c2.countryId
					   from city c2
					   group by c2.countryid
					   having sum(c2.population) > 400);

-- 2.2
select name 
from country c1
where not exists (select *
				  from city c2
				  where ((c2.countryid = c1.countryid) and exists (select * 
																   from building c3 
																   where c3.cityid = c2.cityid))); 
                        
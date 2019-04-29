
/**
date: 30/03/2017
author: thinh
purpose:  - Kooragang 2304 NSW
          - Bandiana 3691 MBW
          - William Raaf 3027 MEL
**/

insert into xms_tbl_postal_code (countryname, countrycode, city_name, from_postcode, to_postcode, station_code) values ('AUSTRALIA',	'AU',	'KOORAGANG',	2304,	2304,	'NSW');
update xms_tbl_postal_code set from_postcode = 3691, to_postcode=3691 where id=6069;
insert into xms_tbl_postal_code (countryname, countrycode, city_name, from_postcode, to_postcode, station_code) values ('AUSTRALIA',	'AU',	'WILLIAM RAAF',	3027,	3027,	'MEL');
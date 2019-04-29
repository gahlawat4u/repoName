
/**
date: 30/03/2017
author: thinh
purpose:  - Kooragang 2304 NSW
          - Bandiana 3691 MBW
          - William Raaf 3027 MEL
**/

delete from xms_tbl_postal_code where city_name='KOORAGANG' and station_code='NSW' and countrycode='AU';
update xms_tbl_postal_code set from_postcode = 3694, to_postcode=3694 where id=6069;
delete from xms_tbl_postal_code where city_name='WILLIAM RAAF' and station_code='MEL' and countrycode='AU';

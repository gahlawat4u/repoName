/**
date: 24/03/2017
author: thinh
purpose: Update postcode for Longerenong as 3401
**/

select * from xms_tbl_postal_code where city_name="Longerenong";
update xms_tbl_postal_code set from_postcode = 3401, to_postcode=3401 where city_name="Longerenong";
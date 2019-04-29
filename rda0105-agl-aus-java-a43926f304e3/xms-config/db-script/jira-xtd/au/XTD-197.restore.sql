/**
date: 24/03/2017
author: thinh
purpose: Restore postcode for Longerenong as 3399
**/

update xms_tbl_postal_code set from_postcode = 3399, to_postcode=3399 where city_name="Longerenong";
/**
date: 29/03/2017
author: thanh
purpose: remove '=' character to get true data
**/

UPDATE xms_tbl_shipment SET airbill_number = REPLACE (airbill_number, '=' , '') WHERE airbill_number LIKE '=%';
/**
date: 11/04/2017
author: tulh
purpose: Fix incorrect character in shipment type name
target: xms_au
**/

UPDATE xms_tbl_shipment_type
SET shipment_type_name = replace(shipment_type_name, '–', '-');
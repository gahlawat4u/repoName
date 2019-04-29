/**
date: 22/05/2017
author: thanhbc
purpose: Update some shipment type about piece and customer value
target: xms_au
**/

-- For not allow add piece
UPDATE `xms_tbl_shipment_type_package` SET `allow_wpx_addpiece`='0' WHERE sp_id IN ('167','168','169','208','209','210','211','212');


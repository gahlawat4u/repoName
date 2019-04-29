/**
date: 19/06/2017
author: thanhbc
purpose: remove useless columns for XI 65 and add accessorial for manual handling surcharge XTD 203 to import in admin
target: xms_au
**/

ALTER TABLE xms_tbl_webship_quote_log DROP COLUMN domestic_security_surcharge;

ALTER TABLE xms_tbl_shipment DROP COLUMN domestic_security_surcharge;

INSERT INTO `xms_tbl_accessorial` (`code`, `description`, `modified_date`, `typeid`, `isquotable`, `carrier`) VALUES ('MHP', 'MANUAL HANDLING SURCHARGE', '2017-04-25 19:32:02', '1', '0', '3');

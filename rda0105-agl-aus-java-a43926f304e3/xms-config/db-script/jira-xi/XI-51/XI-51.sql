DELETE FROM xms_tbl_accessorial_detail WHERE accessorialid=662;

DELETE FROM `xms_tbl_customer_accessorial` WHERE `accessorialid` = '662';

DELETE FROM xms_tbl_accessorial WHERE accessorialid=662;

-- NOTICE :
-- Bellow are some tables that have foreign key to xms_tbl_accessorial table, but at the time of doing this ticket they don't have any records that links to the accessorial with ID = 662 :
-- + xms_tbl_customer_profile_accessorial : select * from xms_tbl_customer_profile_accessorial where accessorialid=662;
-- + xms_tbl_shipment_detail : select * from xms_tbl_shipment_detail where accessorialid=662;
-- + xms_tbl_webship_quote_log_detail : select * from xms_tbl_webship_quote_log_detail where accessorialid=662;


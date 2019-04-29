ALTER TABLE xms_tbl_sales_rep_service
CHANGE COLUMN `goal` `goal` FLOAT NULL,
CHANGE COLUMN `payout` `payout` FLOAT NULL;

ALTER TABLE `xms_tbl_sales_rep` 
CHANGE COLUMN `terminate_date` `terminate_date` DATETIME NULL;
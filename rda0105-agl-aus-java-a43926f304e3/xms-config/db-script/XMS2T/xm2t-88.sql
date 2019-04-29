-- ========== V1 fix error when adding new sales rep - 05/07/2016 =================
ALTER TABLE `xms_tbl_sales_rep` 
CHANGE COLUMN `terminate_date` `terminate_date` DATETIME NULL ;
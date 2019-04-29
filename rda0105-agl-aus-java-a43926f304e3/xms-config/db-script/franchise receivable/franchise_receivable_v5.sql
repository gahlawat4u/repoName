ALTER TABLE `xms_tbl_fran_rab_overview` 
ADD COLUMN `management_fee_on_credit_revenue` DECIMAL(24,6) NULL AFTER `total_receivable`,
ADD COLUMN `management_fee_on_credit_profit_shared` DECIMAL(24,6) NULL AFTER `management_fee_on_credit_revenue`;

ALTER TABLE `tmp_xms_tbl_fran_rab_overview` 
ADD COLUMN `franchise_code` VARCHAR(45) NULL AFTER `management_fee_on_credit_profit_shared`;

ALTER TABLE `xms_tbl_fran_rab_overview` 
ADD COLUMN `franchise_code` VARCHAR(45) NULL AFTER `management_fee_on_credit_profit_shared`;

ALTER TABLE `tmp_xms_tbl_fran_rab_overview` 
DROP PRIMARY KEY;

ALTER TABLE `xms_tbl_fran_rab_overview` 
DROP PRIMARY KEY;
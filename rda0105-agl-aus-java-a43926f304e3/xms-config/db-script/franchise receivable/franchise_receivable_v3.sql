ALTER TABLE `tmp_xms_tbl_sc_rpt_fran_pab_credit` 
ADD COLUMN `carrier_credit_amount` DECIMAL(24,6) NULL AFTER `credits_customer_cost_gst`,
ADD COLUMN `carrier_credit_gst` DECIMAL(24,6) NULL AFTER `carrier_credit_amount`,
ADD COLUMN `carrier_credit_total` DECIMAL(24,6) NULL AFTER `carrier_credit_gst`;

ALTER TABLE `xms_tbl_sc_rpt_fran_pab_credit` 
ADD COLUMN `carrier_credit_amount` DECIMAL(24,6) NULL AFTER `credits_customer_cost_gst`,
ADD COLUMN `carrier_credit_gst` DECIMAL(24,6) NULL AFTER `carrier_credit_amount`,
ADD COLUMN `carrier_credit_total` DECIMAL(24,6) NULL AFTER `carrier_credit_gst`;

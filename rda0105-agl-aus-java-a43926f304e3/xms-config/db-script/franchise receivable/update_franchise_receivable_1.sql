-- Drop un-used tables.
DROP TABLE 
	tmp_xms_tbl_sc_rpt_fran_pab_61days, 
	tmp_xms_tbl_sc_rpt_fran_pab_deduct, 
    tmp_xms_tbl_sc_rpt_fran_pab_margin, 
    tmp_xms_tbl_sc_rpt_fran_pab_non_central,
	xms_tbl_sc_rpt_fran_pab_61days, 
	xms_tbl_sc_rpt_fran_pab_deduct, 
    xms_tbl_sc_rpt_fran_pab_margin, 
    xms_tbl_sc_rpt_fran_pab_non_central;

-- Alter temp tables to keep more information.
ALTER TABLE `tmp_xms_tbl_fran_rab_shipment_raw` 
ADD COLUMN `is_taxable_shipment` TINYINT(1) NULL DEFAULT NULL AFTER `is_domestic`,
ADD COLUMN `cust_marginable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_tax`,
ADD COLUMN `cust_marginable_tax` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_marginable`;

ALTER TABLE `tmp_xms_tbl_fran_rab_shipment_detail` 
ADD COLUMN `is_taxable_shipment` TINYINT(1) NULL DEFAULT NULL AFTER `is_domestic`,
ADD COLUMN `cust_marginable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_tax`,
ADD COLUMN `cust_marginable_tax` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_marginable`;

ALTER TABLE `xms_tbl_fran_rab_shipment_detail` 
ADD COLUMN `is_taxable_shipment` TINYINT(1) NULL DEFAULT NULL AFTER `is_domestic`,
ADD COLUMN `cust_marginable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_tax`,
ADD COLUMN `cust_marginable_tax` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_marginable`;

ALTER TABLE `tmp_xms_tbl_sc_rpt_fran_pab_credit`
ADD COLUMN `is_taxable_shipment` TINYINT(1) NULL DEFAULT NULL AFTER `international_domestic`;

ALTER TABLE `xms_tbl_sc_rpt_fran_pab_credit`
ADD COLUMN `is_taxable_shipment` TINYINT(1) NULL DEFAULT NULL AFTER `international_domestic`;

ALTER TABLE `tmp_xms_tbl_fran_rab_overview`
ADD COLUMN `cust_cost` DECIMAL(20,2) NULL DEFAULT NULL AFTER `email_invoices`,
ADD COLUMN `cust_marginable_cost` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_cost`,
ADD COLUMN `fran_cost_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_cost`,
ADD COLUMN `fran_cost_non_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_cost_taxable`,
ADD COLUMN `fran_gst` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_cost_non_taxable`,
ADD COLUMN `fran_total` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_gst`,
ADD COLUMN `carrier_credits_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits`,
ADD COLUMN `carrier_credits_non_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits_taxable`,
ADD COLUMN `carrier_credits_gst` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits_non_taxable`,
ADD COLUMN `carrier_credits_total` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits_gst`,
ADD COLUMN `intl_shipment_count` BIGINT(20) NULL DEFAULT NULL AFTER `tech_fee_on_dom_shipment`,
ADD COLUMN `dom_shipment_count` BIGINT(20) NULL DEFAULT NULL AFTER `intl_shipment_count`,
DROP COLUMN `gross_payable`;

ALTER TABLE `xms_tbl_fran_rab_overview`
ADD COLUMN `cust_cost` DECIMAL(20,2) NULL DEFAULT NULL AFTER `email_invoices`,
ADD COLUMN `cust_marginable_cost` DECIMAL(20,2) NULL DEFAULT NULL AFTER `cust_cost`,
ADD COLUMN `fran_cost_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_cost`,
ADD COLUMN `fran_cost_non_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_cost_taxable`,
ADD COLUMN `fran_gst` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_cost_non_taxable`,
ADD COLUMN `fran_total` DECIMAL(20,2) NULL DEFAULT NULL AFTER `fran_gst`,
ADD COLUMN `carrier_credits_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits`,
ADD COLUMN `carrier_credits_non_taxable` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits_taxable`,
ADD COLUMN `carrier_credits_gst` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits_non_taxable`,
ADD COLUMN `carrier_credits_total` DECIMAL(20,2) NULL DEFAULT NULL AFTER `carrier_credits_gst`,
ADD COLUMN `intl_shipment_count` BIGINT(20) NULL DEFAULT NULL AFTER `tech_fee_on_dom_shipment`,
ADD COLUMN `dom_shipment_count` BIGINT(20) NULL DEFAULT NULL AFTER `intl_shipment_count`,
DROP COLUMN `gross_payable`;
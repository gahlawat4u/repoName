ALTER TABLE `tmp_xms_tbl_fran_rab_overview` 
CHANGE COLUMN `cust_cost` `cust_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable_cost` `cust_marginable_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost` `fran_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost_taxable` `fran_cost_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost_non_taxable` `fran_cost_non_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_gst` `fran_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_total` `fran_total` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `margin_shared` `margin_shared` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `management_fee` `management_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `marketing_fee` `marketing_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits` `carrier_credits` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_taxable` `carrier_credits_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_non_taxable` `carrier_credits_non_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_gst` `carrier_credits_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_total` `carrier_credits_total` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `tech_fee_on_intl_shipment` `tech_fee_on_intl_shipment` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `tech_fee_on_dom_shipment` `tech_fee_on_dom_shipment` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `net_receivable` `net_receivable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gst` `gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `total_receivable` `total_receivable` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `tmp_xms_tbl_fran_rab_shipment_detail` 
CHANGE COLUMN `cust_cost` `cust_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_tax` `cust_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable` `cust_marginable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable_tax` `cust_marginable_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost` `fran_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_tax` `fran_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin` `gross_margin` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_tax` `gross_margin_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_credit` `fran_credit` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_credit` `cust_credit` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `management_fee` `management_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `marketing_fee` `marketing_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share` `profit_share` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `tmp_xms_tbl_fran_rab_shipment_raw` 
CHANGE COLUMN `cust_cost` `cust_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_tax` `cust_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable` `cust_marginable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable_tax` `cust_marginable_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost` `fran_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_tax` `fran_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin` `gross_margin` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_tax` `gross_margin_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_credit` `fran_credit` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_credit` `cust_credit` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `management_fee_pct` `management_fee_pct` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `marketing_fee_pct` `marketing_fee_pct` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `intl_shipment_fee` `intl_shipment_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `dom_shipment_fee` `dom_shipment_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share_pct` `profit_share_pct` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `tmp_xms_tbl_fran_rab_tech_fee_detail` 
CHANGE COLUMN `intl_shipment_fee` `intl_shipment_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `dom_shipment_fee` `dom_shipment_fee` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `tmp_xms_tbl_sc_rpt_fran_pab_credit` 
CHANGE COLUMN `customer_total_exc_gst` `customer_total_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `customer_total_gst` `customer_total_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `franchise_cost_exc_gst` `franchise_cost_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `franchise_cost_gst` `franchise_cost_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `previously_paid` `previously_paid` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `payments_received` `payments_received` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `amount_outstanding` `amount_outstanding` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_exc_gst` `gross_margin_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_gst` `gross_margin_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share_exc_gst` `profit_share_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share_gst` `profit_share_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `total_profit_share` `total_profit_share` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `new_margin_exc_gst` `new_margin_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `new_margin_gst` `new_margin_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_franchise_cost_exc_gst` `credits_franchise_cost_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_franchise_cost_gst` `credits_franchise_cost_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_customer_cost_exc_gst` `credits_customer_cost_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_customer_cost_gst` `credits_customer_cost_gst` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `tmp_xms_tbl_sc_rpt_fran_pab_overpayment` 
CHANGE COLUMN `amount` `amount` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `xms_tbl_fran_rab_overview` 
CHANGE COLUMN `cust_cost` `cust_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable_cost` `cust_marginable_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost` `fran_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost_taxable` `fran_cost_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost_non_taxable` `fran_cost_non_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_gst` `fran_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_total` `fran_total` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `margin_shared` `margin_shared` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `management_fee` `management_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `marketing_fee` `marketing_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits` `carrier_credits` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_taxable` `carrier_credits_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_non_taxable` `carrier_credits_non_taxable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_gst` `carrier_credits_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `carrier_credits_total` `carrier_credits_total` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `tech_fee_on_intl_shipment` `tech_fee_on_intl_shipment` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `tech_fee_on_dom_shipment` `tech_fee_on_dom_shipment` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `net_receivable` `net_receivable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gst` `gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `total_receivable` `total_receivable` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `xms_tbl_fran_rab_shipment_detail` 
CHANGE COLUMN `cust_cost` `cust_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_tax` `cust_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable` `cust_marginable` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_marginable_tax` `cust_marginable_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_cost` `fran_cost` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_tax` `fran_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin` `gross_margin` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_tax` `gross_margin_tax` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `fran_credit` `fran_credit` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `cust_credit` `cust_credit` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `management_fee` `management_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `marketing_fee` `marketing_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share` `profit_share` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `xms_tbl_fran_rab_tech_fee_detail` 
CHANGE COLUMN `intl_shipment_fee` `intl_shipment_fee` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `dom_shipment_fee` `dom_shipment_fee` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `xms_tbl_sc_rpt_fran_pab_credit` 
CHANGE COLUMN `customer_total_exc_gst` `customer_total_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `customer_total_gst` `customer_total_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `franchise_cost_exc_gst` `franchise_cost_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `franchise_cost_gst` `franchise_cost_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `previously_paid` `previously_paid` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `payments_received` `payments_received` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `amount_outstanding` `amount_outstanding` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_exc_gst` `gross_margin_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `gross_margin_gst` `gross_margin_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share_exc_gst` `profit_share_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `profit_share_gst` `profit_share_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `total_profit_share` `total_profit_share` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `new_margin_exc_gst` `new_margin_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `new_margin_gst` `new_margin_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_franchise_cost_exc_gst` `credits_franchise_cost_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_franchise_cost_gst` `credits_franchise_cost_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_customer_cost_exc_gst` `credits_customer_cost_exc_gst` DECIMAL(24,6) NULL DEFAULT NULL ,
CHANGE COLUMN `credits_customer_cost_gst` `credits_customer_cost_gst` DECIMAL(24,6) NULL DEFAULT NULL ;

ALTER TABLE `xms_tbl_sc_rpt_fran_pab_overpayment` 
CHANGE COLUMN `amount` `amount` DECIMAL(24,6) NULL DEFAULT NULL ;
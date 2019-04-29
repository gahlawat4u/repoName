
-- Add setting for Franchise Receivable Model
-- share_model: by default is 0 (it means that the system will be use SC 1 model (the report will show management fee and marketing fee
--								and profit share column will be 0
--				1 (it mean that the system will be use SC 2 model (the report will show profit share column and management fee and marketing fee
--								will be 0
alter table xms_tbl_franchise
add column share_model tinyint(1) default 0;

-- Add setting for Management Fee and Marketing Fee
insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level)
values ('Management Service Fee Percentage',9,'',1);

insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level)
values ('Marketing Service Fee Percentage',1,'',1);

-- Add setting for International and Domestic Shipment Fee
insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level)
values ('International Shipment Fee',0.17,'',1);

insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level)
values ('Domestic Shipment Fee',0.11,'',1);

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_61days;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_61days` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` varchar(200) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(50) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  `previously_deducted_cost` decimal(20,2) DEFAULT NULL,
  `profit_share_on_late_fees` decimal(20,2) DEFAULT NULL,
  `repaid_carrier_deductions` decimal(20,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_credit;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` varchar(200) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(50) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  `new_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `new_margin_gst` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost_gst` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_deduct;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_deduct` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_payment` decimal(20,2) DEFAULT NULL,
  `customer_cost` decimal(20,2) DEFAULT NULL,
  `customer_tax` decimal(20,2) DEFAULT NULL,
  `franchise_cost` decimal(20,2) DEFAULT NULL,
  `franchise_tax` decimal(20,2) DEFAULT NULL,
  `franchise_charge` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_margin;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_margin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` varchar(200) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(50) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_non_central;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_non_central` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(20) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_overpayment;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_overpayment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `origin_payment_date` datetime DEFAULT NULL,
  `customer_number` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `overpayment_type` varchar(50) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_sc_rpt_fran_pab_61days;
CREATE TABLE `xms_tbl_sc_rpt_fran_pab_61days` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` varchar(200) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(50) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  `previously_deducted_cost` decimal(20,2) DEFAULT NULL,
  `profit_share_on_late_fees` decimal(20,2) DEFAULT NULL,
  `repaid_carrier_deductions` decimal(20,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_sc_rpt_fran_pab_credit;
CREATE TABLE `xms_tbl_sc_rpt_fran_pab_credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` varchar(200) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(50) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  `new_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `new_margin_gst` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost_gst` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_sc_rpt_fran_pab_deduct;
CREATE TABLE `xms_tbl_sc_rpt_fran_pab_deduct` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_payment` decimal(20,2) DEFAULT NULL,
  `customer_cost` decimal(20,2) DEFAULT NULL,
  `customer_tax` decimal(20,2) DEFAULT NULL,
  `franchise_cost` decimal(20,2) DEFAULT NULL,
  `franchise_tax` decimal(20,2) DEFAULT NULL,
  `franchise_charge` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_sc_rpt_fran_pab_margin;
CREATE TABLE `xms_tbl_sc_rpt_fran_pab_margin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` varchar(200) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(50) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_sc_rpt_fran_pab_non_central;
CREATE TABLE `xms_tbl_sc_rpt_fran_pab_non_central` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `invoice_number` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `customer_number` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `international_domestic` varchar(20) DEFAULT NULL,
  `customer_total_exc_gst` decimal(20,2) DEFAULT NULL,
  `customer_total_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(20,2) DEFAULT NULL,
  `franchise_cost_gst` decimal(20,2) DEFAULT NULL,
  `previously_paid` decimal(20,2) DEFAULT NULL,
  `payments_received` decimal(20,2) DEFAULT NULL,
  `amount_outstanding` decimal(20,2) DEFAULT NULL,
  `credits_franchise_cost` decimal(20,2) DEFAULT NULL,
  `credits_customer_cost` decimal(20,2) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(20,2) DEFAULT NULL,
  `gross_margin_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_exc_gst` decimal(20,2) DEFAULT NULL,
  `profit_share_gst` decimal(20,2) DEFAULT NULL,
  `total_profit_share` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_sc_rpt_fran_pab_overpayment;
CREATE TABLE `xms_tbl_sc_rpt_fran_pab_overpayment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `origin_payment_date` datetime DEFAULT NULL,
  `customer_number` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `overpayment_type` varchar(50) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_fran_rab_shipment_raw;
CREATE TABLE `tmp_xms_tbl_fran_rab_shipment_raw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `import_date` date DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `is_domestic` tinyint(1) DEFAULT NULL,
  `cust_cost` decimal(20,2) DEFAULT NULL,
  `cust_tax` decimal(20,2) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `fran_tax` decimal(20,2) DEFAULT NULL,
  `gross_margin` decimal(20,2) DEFAULT NULL,
  `gross_margin_tax` decimal(20,2) DEFAULT NULL,
  `fran_credit` decimal(20,2) DEFAULT NULL,
  `cust_credit` decimal(20,2) DEFAULT NULL,
  `share_model` tinyint(1) DEFAULT NULL,
  `management_fee_pct` decimal(20,2) DEFAULT NULL,
  `marketing_fee_pct` decimal(20,2) DEFAULT NULL,
  `intl_shipment_fee` decimal(20,2) DEFAULT NULL,
  `dom_shipment_fee` decimal(20,2) DEFAULT NULL,
  `profit_share_pct` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_fran_rab_shipment_detail;
CREATE TABLE `tmp_xms_tbl_fran_rab_shipment_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `import_date` date DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `is_domestic` tinyint(1) DEFAULT NULL,
  `cust_cost` decimal(20,2) DEFAULT NULL,
  `cust_tax` decimal(20,2) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `fran_tax` decimal(20,2) DEFAULT NULL,
  `gross_margin` decimal(20,2) DEFAULT NULL,
  `gross_margin_tax` decimal(20,2) DEFAULT NULL,
  `fran_credit` decimal(20,2) DEFAULT NULL,
  `cust_credit` decimal(20,2) DEFAULT NULL,
  `management_fee` decimal(20,2) DEFAULT NULL,
  `marketing_fee` decimal(20,2) DEFAULT NULL,
  `profit_share` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_fran_rab_tech_fee_detail;
CREATE TABLE `tmp_xms_tbl_fran_rab_tech_fee_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `import_date` date DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `is_domestic` tinyint(1) DEFAULT NULL,
  `intl_shipment_fee` decimal(20,2) DEFAULT NULL,
  `dom_shipment_fee` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_fran_rab_shipment_detail;
CREATE TABLE `xms_tbl_fran_rab_shipment_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `import_date` date DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `is_domestic` tinyint(1) DEFAULT NULL,
  `cust_cost` decimal(20,2) DEFAULT NULL,
  `cust_tax` decimal(20,2) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `fran_tax` decimal(20,2) DEFAULT NULL,
  `gross_margin` decimal(20,2) DEFAULT NULL,
  `gross_margin_tax` decimal(20,2) DEFAULT NULL,
  `fran_credit` decimal(20,2) DEFAULT NULL,
  `cust_credit` decimal(20,2) DEFAULT NULL,
  `management_fee` decimal(20,2) DEFAULT NULL,
  `marketing_fee` decimal(20,2) DEFAULT NULL,
  `profit_share` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_fran_rab_tech_fee_detail;
CREATE TABLE `xms_tbl_fran_rab_tech_fee_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `import_date` date DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `airbill_number` varchar(200) DEFAULT NULL,
  `is_domestic` tinyint(1) DEFAULT NULL,
  `intl_shipment_fee` decimal(20,2) DEFAULT NULL,
  `dom_shipment_fee` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_fran_rab_overview;
CREATE TABLE `tmp_xms_tbl_fran_rab_overview` (
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `setups` bigint(20) DEFAULT NULL,
  `activations` bigint(20) DEFAULT NULL,
  `printed_invoices` bigint(20) DEFAULT NULL,
  `email_invoices` bigint(20) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `margin_shared` decimal(20,2) DEFAULT NULL,
  `management_fee` decimal(20,2) DEFAULT NULL,
  `marketing_fee` decimal(20,2) DEFAULT NULL,
  `carrier_credits` decimal(20,2) DEFAULT NULL,
  `tech_fee_on_intl_shipment` decimal(20,2) DEFAULT NULL,
  `tech_fee_on_dom_shipment` decimal(20,2) DEFAULT NULL,
  `gross_payable` decimal(20,2) DEFAULT NULL,
  `net_receivable` decimal(20,2) DEFAULT NULL,
  `gst` decimal(20,2) DEFAULT NULL,
  `total_receivable` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`rpt_txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4096 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_fran_rab_overview;
CREATE TABLE `xms_tbl_fran_rab_overview` (
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `setups` bigint(20) DEFAULT NULL,
  `activations` bigint(20) DEFAULT NULL,
  `printed_invoices` bigint(20) DEFAULT NULL,
  `email_invoices` bigint(20) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `margin_shared` decimal(20,2) DEFAULT NULL,
  `management_fee` decimal(20,2) DEFAULT NULL,
  `marketing_fee` decimal(20,2) DEFAULT NULL,
  `carrier_credits` decimal(20,2) DEFAULT NULL,
  `tech_fee_on_intl_shipment` decimal(20,2) DEFAULT NULL,
  `tech_fee_on_dom_shipment` decimal(20,2) DEFAULT NULL,
  `gross_payable` decimal(20,2) DEFAULT NULL,
  `net_receivable` decimal(20,2) DEFAULT NULL,
  `gst` decimal(20,2) DEFAULT NULL,
  `total_receivable` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`rpt_txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4096 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
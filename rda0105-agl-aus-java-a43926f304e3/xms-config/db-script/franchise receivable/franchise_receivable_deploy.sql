alter table xms_tbl_franchise
add column share_model tinyint(1) default 0;

-- add index into xms_tbl_invoice_payment_detail
-- for optimizing when query to get franchise payables report
ALTER TABLE xms_tbl_invoice_payment_detail
ADD INDEX airbill_number(airbill_number);

-- add index into xms_tbl_shipment_billing
-- for optimizing when reconsile franchise tax amount
ALTER TABLE xms_tbl_shipment_billing
ADD INDEX franchise_tax_amount(franchise_tax_amount);

-- add index into xms_tbl_shipment_billing
-- for optimizing when do query to get count of airbills by import date range
ALTER TABLE xms_tbl_shipment_billing
ADD INDEX import_date(import_date);

-- alter table xms_tbl_period
-- to do frozen function in franchise payable report
ALTER TABLE xms_tbl_period
ADD rpt_txn_id varchar(128) default ''; 

-- Setting for Advertising Assessment percent.
INSERT INTO xms_tbl_system_setting (setting_name, setting_value, description, user_level, allow_null)
VALUE ('Advertising Assessment Percent','1','Setting for Advertising Assessment Percent',1,0);

-- Setting for  percent.
INSERT INTO xms_tbl_system_setting (setting_name, setting_value, description, user_level, allow_null)
VALUE ('Invoicing Charge Percent','1','Setting for Invoicing Charge Percent',1,0);

Drop table if exists tmp_xms_tbl_fran_rab_overview;
CREATE TABLE `tmp_xms_tbl_fran_rab_overview` (
  `rpt_txn_id` varchar(128) NOT NULL DEFAULT '',
  `setups` bigint(20) DEFAULT NULL,
  `activations` bigint(20) DEFAULT NULL,
  `printed_invoices` bigint(20) DEFAULT NULL,
  `email_invoices` bigint(20) DEFAULT NULL,
  `cust_cost` decimal(24,6) DEFAULT NULL,
  `cust_marginable_cost` decimal(24,6) DEFAULT NULL,
  `fran_cost` decimal(24,6) DEFAULT NULL,
  `fran_cost_taxable` decimal(24,6) DEFAULT NULL,
  `fran_cost_non_taxable` decimal(24,6) DEFAULT NULL,
  `fran_gst` decimal(24,6) DEFAULT NULL,
  `fran_total` decimal(24,6) DEFAULT NULL,
  `margin_shared` decimal(24,6) DEFAULT NULL,
  `management_fee` decimal(24,6) DEFAULT NULL,
  `marketing_fee` decimal(24,6) DEFAULT NULL,
  `carrier_credits` decimal(24,6) DEFAULT NULL,
  `carrier_credits_taxable` decimal(24,6) DEFAULT NULL,
  `carrier_credits_non_taxable` decimal(24,6) DEFAULT NULL,
  `carrier_credits_gst` decimal(24,6) DEFAULT NULL,
  `carrier_credits_total` decimal(24,6) DEFAULT NULL,
  `tech_fee_on_intl_shipment` decimal(24,6) DEFAULT NULL,
  `tech_fee_on_dom_shipment` decimal(24,6) DEFAULT NULL,
  `intl_shipment_count` bigint(20) DEFAULT NULL,
  `dom_shipment_count` bigint(20) DEFAULT NULL,
  `net_receivable` decimal(24,6) DEFAULT NULL,
  `gst` decimal(24,6) DEFAULT NULL,
  `total_receivable` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`rpt_txn_id`)
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
  `is_taxable_shipment` tinyint(1) DEFAULT NULL,
  `cust_cost` decimal(24,6) DEFAULT NULL,
  `cust_tax` decimal(24,6) DEFAULT NULL,
  `cust_marginable` decimal(24,6) DEFAULT NULL,
  `cust_marginable_tax` decimal(24,6) DEFAULT NULL,
  `fran_cost` decimal(24,6) DEFAULT NULL,
  `fran_tax` decimal(24,6) DEFAULT NULL,
  `gross_margin` decimal(24,6) DEFAULT NULL,
  `gross_margin_tax` decimal(24,6) DEFAULT NULL,
  `fran_credit` decimal(24,6) DEFAULT NULL,
  `cust_credit` decimal(24,6) DEFAULT NULL,
  `management_fee` decimal(24,6) DEFAULT NULL,
  `marketing_fee` decimal(24,6) DEFAULT NULL,
  `profit_share` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `is_taxable_shipment` tinyint(1) DEFAULT NULL,
  `cust_cost` decimal(24,6) DEFAULT NULL,
  `cust_tax` decimal(24,6) DEFAULT NULL,
  `cust_marginable` decimal(24,6) DEFAULT NULL,
  `cust_marginable_tax` decimal(24,6) DEFAULT NULL,
  `fran_cost` decimal(24,6) DEFAULT NULL,
  `fran_tax` decimal(24,6) DEFAULT NULL,
  `gross_margin` decimal(24,6) DEFAULT NULL,
  `gross_margin_tax` decimal(24,6) DEFAULT NULL,
  `fran_credit` decimal(24,6) DEFAULT NULL,
  `cust_credit` decimal(24,6) DEFAULT NULL,
  `share_model` tinyint(1) DEFAULT NULL,
  `management_fee_pct` decimal(24,6) DEFAULT NULL,
  `marketing_fee_pct` decimal(24,6) DEFAULT NULL,
  `intl_shipment_fee` decimal(24,6) DEFAULT NULL,
  `dom_shipment_fee` decimal(24,6) DEFAULT NULL,
  `profit_share_pct` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `intl_shipment_fee` decimal(24,6) DEFAULT NULL,
  `dom_shipment_fee` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_fran_pab_rpt_txn_id;
CREATE TABLE `tmp_xms_tbl_fran_pab_rpt_txn_id` (
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` text,
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `created_date` (`created_date`)
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
  `is_taxable_shipment` tinyint(1) DEFAULT NULL,
  `customer_total_exc_gst` decimal(24,6) DEFAULT NULL,
  `customer_total_gst` decimal(24,6) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(24,6) DEFAULT NULL,
  `franchise_cost_gst` decimal(24,6) DEFAULT NULL,
  `previously_paid` decimal(24,6) DEFAULT NULL,
  `payments_received` decimal(24,6) DEFAULT NULL,
  `amount_outstanding` decimal(24,6) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(24,6) DEFAULT NULL,
  `gross_margin_gst` decimal(24,6) DEFAULT NULL,
  `profit_share_exc_gst` decimal(24,6) DEFAULT NULL,
  `profit_share_gst` decimal(24,6) DEFAULT NULL,
  `total_profit_share` decimal(24,6) DEFAULT NULL,
  `new_margin_exc_gst` decimal(24,6) DEFAULT NULL,
  `new_margin_gst` decimal(24,6) DEFAULT NULL,
  `credits_franchise_cost_exc_gst` decimal(24,6) DEFAULT NULL,
  `credits_franchise_cost_gst` decimal(24,6) DEFAULT NULL,
  `credits_customer_cost_exc_gst` decimal(24,6) DEFAULT NULL,
  `credits_customer_cost_gst` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists tmp_xms_tbl_sc_rpt_fran_pab_overpayment;
CREATE TABLE `tmp_xms_tbl_sc_rpt_fran_pab_overpayment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `origin_payment_date` datetime DEFAULT NULL,
  `customer_number` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `overpayment_type` varchar(50) DEFAULT NULL,
  `amount` decimal(24,6) DEFAULT NULL,
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
  `amount` decimal(24,6) DEFAULT NULL,
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
  `is_taxable_shipment` tinyint(1) DEFAULT NULL,
  `customer_total_exc_gst` decimal(24,6) DEFAULT NULL,
  `customer_total_gst` decimal(24,6) DEFAULT NULL,
  `franchise_cost_exc_gst` decimal(24,6) DEFAULT NULL,
  `franchise_cost_gst` decimal(24,6) DEFAULT NULL,
  `previously_paid` decimal(24,6) DEFAULT NULL,
  `payments_received` decimal(24,6) DEFAULT NULL,
  `amount_outstanding` decimal(24,6) DEFAULT NULL,
  `gross_margin_exc_gst` decimal(24,6) DEFAULT NULL,
  `gross_margin_gst` decimal(24,6) DEFAULT NULL,
  `profit_share_exc_gst` decimal(24,6) DEFAULT NULL,
  `profit_share_gst` decimal(24,6) DEFAULT NULL,
  `total_profit_share` decimal(24,6) DEFAULT NULL,
  `new_margin_exc_gst` decimal(24,6) DEFAULT NULL,
  `new_margin_gst` decimal(24,6) DEFAULT NULL,
  `credits_franchise_cost_exc_gst` decimal(24,6) DEFAULT NULL,
  `credits_franchise_cost_gst` decimal(24,6) DEFAULT NULL,
  `credits_customer_cost_exc_gst` decimal(24,6) DEFAULT NULL,
  `credits_customer_cost_gst` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `franchise_code` (`franchise_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

Drop table if exists xms_tbl_fran_rab_overview;
CREATE TABLE `xms_tbl_fran_rab_overview` (
  `rpt_txn_id` varchar(128) NOT NULL DEFAULT '',
  `setups` bigint(20) DEFAULT NULL,
  `activations` bigint(20) DEFAULT NULL,
  `printed_invoices` bigint(20) DEFAULT NULL,
  `email_invoices` bigint(20) DEFAULT NULL,
  `cust_cost` decimal(24,6) DEFAULT NULL,
  `cust_marginable_cost` decimal(24,6) DEFAULT NULL,
  `fran_cost` decimal(24,6) DEFAULT NULL,
  `fran_cost_taxable` decimal(24,6) DEFAULT NULL,
  `fran_cost_non_taxable` decimal(24,6) DEFAULT NULL,
  `fran_gst` decimal(24,6) DEFAULT NULL,
  `fran_total` decimal(24,6) DEFAULT NULL,
  `margin_shared` decimal(24,6) DEFAULT NULL,
  `management_fee` decimal(24,6) DEFAULT NULL,
  `marketing_fee` decimal(24,6) DEFAULT NULL,
  `carrier_credits` decimal(24,6) DEFAULT NULL,
  `carrier_credits_taxable` decimal(24,6) DEFAULT NULL,
  `carrier_credits_non_taxable` decimal(24,6) DEFAULT NULL,
  `carrier_credits_gst` decimal(24,6) DEFAULT NULL,
  `carrier_credits_total` decimal(24,6) DEFAULT NULL,
  `tech_fee_on_intl_shipment` decimal(24,6) DEFAULT NULL,
  `tech_fee_on_dom_shipment` decimal(24,6) DEFAULT NULL,
  `intl_shipment_count` bigint(20) DEFAULT NULL,
  `dom_shipment_count` bigint(20) DEFAULT NULL,
  `net_receivable` decimal(24,6) DEFAULT NULL,
  `gst` decimal(24,6) DEFAULT NULL,
  `total_receivable` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`rpt_txn_id`)
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
  `is_taxable_shipment` tinyint(1) DEFAULT NULL,
  `cust_cost` decimal(24,6) DEFAULT NULL,
  `cust_tax` decimal(24,6) DEFAULT NULL,
  `cust_marginable` decimal(24,6) DEFAULT NULL,
  `cust_marginable_tax` decimal(24,6) DEFAULT NULL,
  `fran_cost` decimal(24,6) DEFAULT NULL,
  `fran_tax` decimal(24,6) DEFAULT NULL,
  `gross_margin` decimal(24,6) DEFAULT NULL,
  `gross_margin_tax` decimal(24,6) DEFAULT NULL,
  `fran_credit` decimal(24,6) DEFAULT NULL,
  `cust_credit` decimal(24,6) DEFAULT NULL,
  `management_fee` decimal(24,6) DEFAULT NULL,
  `marketing_fee` decimal(24,6) DEFAULT NULL,
  `profit_share` decimal(24,6) DEFAULT NULL,
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
  `intl_shipment_fee` decimal(24,6) DEFAULT NULL,
  `dom_shipment_fee` decimal(24,6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tmp_xms_tbl_fran_pab_margin_and_61days`;
CREATE TABLE `tmp_xms_tbl_fran_pab_margin_and_61days` (
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `airbill_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `rev_paid` decimal(20,2) DEFAULT NULL,
  `prev_paid` decimal(20,2) DEFAULT NULL,
  `cust_cost` decimal(20,2) DEFAULT NULL,
  `cust_tax` decimal(20,2) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `fran_tax` decimal(20,2) DEFAULT NULL,
  `total_cust_credit` decimal(20,2) DEFAULT NULL,
  `cur_cust_credit` decimal(20,2) DEFAULT NULL,
  `total_cust_carrier_credit` decimal(20,2) DEFAULT NULL,
  `cur_cust_carrier_credit` decimal(20,2) DEFAULT NULL,
  `total_fran_carrier_credit` decimal(20,2) DEFAULT NULL,
  `cur_fran_carrier_credit` decimal(20,2) DEFAULT NULL,
  `profit_share` decimal(5,2) DEFAULT NULL,
  `late_fee_share` decimal(5,2) DEFAULT NULL,
  `management_service_fee` decimal(5,2) DEFAULT NULL,
  `inter_domes` varchar(20) DEFAULT NULL,
  `pausing_day` bigint(20) DEFAULT '0',
  KEY `rpt_txn_id` (`rpt_txn_id`),
  KEY `apply_date` (`apply_date`),
  KEY `invoice_date` (`invoice_date`),
  KEY `rpt_txn_id_apply_date` (`rpt_txn_id`,`apply_date`),
  KEY `rpt_txn_id_apply_date_invoice_date` (`rpt_txn_id`,`apply_date`,`invoice_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tmp_xms_tbl_fran_pab_credit`;
CREATE TABLE `tmp_xms_tbl_fran_pab_credit` (
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `invoice_code` varchar(200) DEFAULT NULL,
  `invoice_date` date DEFAULT NULL,
  `airbill_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `rev_paid` decimal(20,2) DEFAULT NULL,
  `prev_paid` decimal(20,2) DEFAULT NULL,
  `cust_cost` decimal(20,2) DEFAULT NULL,
  `cust_tax` decimal(20,2) DEFAULT NULL,
  `fran_cost` decimal(20,2) DEFAULT NULL,
  `fran_tax` decimal(20,2) DEFAULT NULL,
  `total_cust_credit` decimal(20,2) DEFAULT NULL,
  `cur_cust_credit` decimal(20,2) DEFAULT NULL,
  `total_cust_carrier_credit` decimal(20,2) DEFAULT NULL,
  `cur_cust_carrier_credit` decimal(20,2) DEFAULT NULL,
  `total_fran_carrier_credit` decimal(20,2) DEFAULT NULL,
  `cur_fran_carrier_credit` decimal(20,2) DEFAULT NULL,
  `profit_share` decimal(5,2) DEFAULT NULL,
  `late_fee_share` decimal(5,2) DEFAULT NULL,
  `management_service_fee` decimal(5,2) DEFAULT NULL,
  `inter_domes` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `tmp_xms_tbl_fran_pab_overpayment`;
CREATE TABLE `tmp_xms_tbl_fran_pab_overpayment` (
  `rpt_txn_id` varchar(128) DEFAULT NULL,
  `franchise_code` varchar(3) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `customer_code` bigint(20) DEFAULT NULL,
  `customer_name` varchar(500) DEFAULT NULL,
  `over_amount` decimal(20,2) DEFAULT NULL,
  `overpayment_type` varchar(20) DEFAULT NULL,
  KEY `rpt_txn_id` (`rpt_txn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
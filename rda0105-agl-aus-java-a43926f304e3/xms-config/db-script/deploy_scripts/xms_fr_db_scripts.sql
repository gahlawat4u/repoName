-- ------------------------INSERT XMS 2.0 MENUS LOCALIZATION----------------------------------------------------
INSERT INTO `xms_tbl_localization` (`localizationid`, `localizationname`, `parentid`, `setup`, `default_text`) VALUES ('', 'franchise 2', '54,335,281,288,289,333,314,285,383,292,227,301,306,304,294,342,297,299,321,318,361,363,362,1865,359,356,344,341,867,366,870,927,985,1066,2366,368', '0', 'Franchise 2.0');
-- ------------------------INSERT XMS 2.0 MENUS----------------------------------------------------
INSERT INTO `xms_tbl_menu` (`menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES ('Franchise 2.0', '4', '5', '1.0', '2.0,3.0', '0', '0', '#', (select localizationid from xms_tbl_localization where localizationname = 'franchise 2'));
-- NOTE: GET ID FROM LOCALIZATION AND PUT IN <ID> IN THE FIRST INSERT QUERY
INSERT INTO `xms_tbl_menu` (`menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES ('Franchise Payables', <ID>, '101', '3.0', '', '0', '0', 'XMS2:/fpb_sc.ix', '344');


UPDATE `xms_tbl_menu` SET `url`='XMS2:/receive_payment.ix' WHERE `menuid`='52';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/invoice_overpayment.ix' WHERE `menuid`='56';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/credit_notes.ix' WHERE `menuid`='42';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/freeze_credit_notes.ix' WHERE `menuid`='49';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/send_credit_notes.ix' WHERE `menuid`='141';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/unfreeze_credit_notes.ix' WHERE `menuid`='144';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/manage_adjustment.ix' WHERE `menuid`='50';
INSERT INTO `xms_tbl_localization` (`localizationname`, `default_text`) VALUES ('manage_adjust_request_2.0', 'Manage Adjustment Request 2.0');
INSERT INTO `xms_tbl_menu` (`menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES ('Manage Adjustment Request', '2', '6', '1.0', '5.0', '0', '0', 'XMS2:/manage_adjustment_request.ix',(select localizationid from xms_tbl_localization where localizationname = 'manage_adjust_request_2.0'));
-- ----------------------------------------------------------------------------
CREATE TABLE `xms_tbl_session_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(200) DEFAULT NULL,
  `content` text,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
-- ----------------------------------------------------------------------------
-- when insert please check lang_code and orginal as composite key
DROP TABLE IF EXISTS `xms_tbl_language_value`;
CREATE TABLE `xms_tbl_language_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang_code` varchar(10) NOT NULL,
  `original` text NOT NULL,
  `destination` text,
  `mode` varchar(20) DEFAULT 'PRO',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- -----------------------------INVOICE TERMS-----------------------------------------------

DROP TABLE IF EXISTS `xms_tbl_invoice_terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xms_tbl_invoice_terms` (
  `invoice_terms_id` int(11) NOT NULL,
  `days` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoice_terms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xms_tbl_invoice_terms`
--

LOCK TABLES `xms_tbl_invoice_terms` WRITE;
/*!40000 ALTER TABLE `xms_tbl_invoice_terms` DISABLE KEYS */;
INSERT INTO `xms_tbl_invoice_terms` VALUES (0,7),(1,10),(2,12),(3,14),(4,15),(5,17),(6,20),(7,21);
/*!40000 ALTER TABLE `xms_tbl_invoice_terms` ENABLE KEYS */;
UNLOCK TABLES;

--  =========================== NEW 20150622

DROP TABLE IF EXISTS xms_tbl_airbill_pausing_deduct;
CREATE TABLE `xms_tbl_airbill_pausing_deduct` (
  `airbill_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pausing_day` int(11) NOT NULL DEFAULT '0',
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`airbill_number`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE `xms_tbl_airbill_pausing_deduct` 
CHANGE COLUMN `airbill_number` `airbill_number` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ;

-- Alter column actual_delivery_date of xms_tbl_airbill_adjustment
-- to save the adjustment with actual_delivery_date is null
alter table xms_tbl_airbill_adjustment
change column actual_delivery_date actual_delivery_date datetime null;

-- Adds an extra column to xms_tbl_customer_payment
-- to keep payment type
alter table xms_tbl_customer_payment
add payment_type tinyint(4) default 0;

-- Adds extra columns to xms_tbl_airbill_adjustment
-- to keep Reason for deleting, Franchise comments to FSC, FSC Credit notes
alter table xms_tbl_airbill_adjustment
add column reason_for_deleting text null,
add column franchise_comments_to_fsc text null,
add column fsc_credit_note text null;

-- Adds extra column to xms_tbl_airbill_adjustment
alter table xms_tbl_airbill_adjustment
add column sub_status tinyint(4) null;

-- set default substatus = 0
ALTER TABLE `xms_tbl_airbill_adjustment` 
CHANGE COLUMN `sub_status` `sub_status` TINYINT(4) NULL DEFAULT 0 ;
update xms_tbl_airbill_adjustment set sub_status = 0 where sub_status is null;

ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD COLUMN `start_pausing_date` DATE NULL AFTER `sub_status`;

-- Add index for xms_tbl_airbill_adjustment
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD INDEX `status` (`status` ASC);

-- Alter xms_tbl_note table to save follow_up_date with null
alter table xms_tbl_note
change column `follow_up_date` `follow_up_date` datetime null;

-- ---------------------ADD MISSING COLUMNS---------------------------------
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD COLUMN `franchise_amount` DECIMAL(20,2) NULL DEFAULT 0.00 AFTER `actual_delivery_date`,
ADD COLUMN `gst_franchise_amount` DECIMAL(20,2) NULL DEFAULT 0.00 AFTER `franchise_amount`;

-- ========= INSERT SYSTEM SETTINGS KEY 24-08-2015 ==========
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('Default Franchise Payable Tax Percent', '10', 'Default Franchise Payable Tax Percent', '1', '0');

-- add index into xms_tbl_shipment
-- for optimizing when execute 'get customer list' query
alter table xms_tbl_shipment
add index customer_code(customer_code);

-- update xms_tbl_invoice_terms
update xms_tbl_invoice_terms
set days=30 
where invoice_terms_id=7;

-- =================== NEW SYSTEM SETTINGS - 27/10/2015 ====================================
ALTER TABLE xms_tbl_system_setting ADD value_type VARCHAR(200);
ALTER TABLE xms_tbl_system_setting ADD list_value_default  TEXT;

update xms_tbl_system_setting set value_type="string",list_value_default = '[{"key":"0","value":"DHL","textDisplay":"Value", "inputDisplay":"select"},{"key":"1","value":"% Markup","textDisplay":"Value", "inputDisplay":"select"},{"key":"2","value":"% Margin","textDisplay":"Value", "inputDisplay":"select"},{"key":"3","value":"% Topup","textDisplay":"Value", "inputDisplay":"select"}]'
where setting_id=77;
update xms_tbl_system_setting set value_type="string",list_value_default = '[{"key":"dhl_countryid","value":"dhl_countryname","textDisplay":"Country Name", "inputDisplay":"select", "dataSource":"xms_tbl_dhl_country"},{"key":"sheet_id","value":"sheet_name","textDisplay":"Inbound Carrier Document", "inputDisplay":"select","dataSource":"xms_tbl_rate_sheet", "filter":"carrier_cost=1 and is_per_weight=0 and content=0 and bound=1"},{"key":"sheet_id","value":"sheet_name","textDisplay":"Inbound Carrier Non-Document", "inputDisplay":"select","dataSource":"xms_tbl_rate_sheet", "filter":"carrier_cost=1 and is_per_weight=0 and content=1 and bound=1"},{"key":"sheet_id","value":"sheet_name","textDisplay":"Inbound Carrier Per Weight", "inputDisplay":"select","dataSource":"xms_tbl_rate_sheet", "filter":"carrier_cost=1 and is_per_weight=1 and content=1 and bound=1"}]'
where setting_name='Default Carrier Inbound Rate Sheet by Zone';
update xms_tbl_system_setting set value_type="string", list_value_default = '[{"key":"0","value":"NO","inputDisplay":"radio"},{"key":"1","value":"YES", "inputDisplay":"radio"}]' 
WHERE setting_name='Compulsory for Webship Phone No';

-- ================== CUSTOMER AGING =======================================================
DROP TABLE IF EXISTS tmp_xms_tbl_customer_aging_invoice;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_customer_aging_invoice
(
	rpt_txn_id varchar(128),
	invoiceid bigint(20),
	invoice_code varchar(200),
	customer_code bigint(20),
	invoice_date date,
	total_cost decimal(20,2),
	total_paid decimal(20,2),
	last_payment_date date,
	invoice_age int,
	terms int,
	due_date date,
	days_overdue int,
	customer_name varchar(500),
	sales_rep_id bigint(20),
	collectorid bigint(20),
	sales_rep_name varchar(500),
	total_due decimal(20,2),
	index rpt_txn_id(rpt_txn_id),
	index customer_code(customer_code)
);

DROP TABLE IF EXISTS tmp_xms_tbl_customer_aging;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_customer_aging
(
	rpt_txn_id varchar(128),
	customer_name varchar(500),
	customer_code bigint(20),
	collectorid bigint(20),
	sales_rep_id bigint(20),
	sales_rep_name varchar(500),
	total_due decimal(20,2),
	total_overdue decimal(20,2),
	range_0 decimal(20,2),
	range_1_15 decimal(20,2),
	range_16_30 decimal(20,2),
	range_31_45 decimal(20,2),
	range_46_60 decimal(20,2),
	range_61_90 decimal(20,2),
	range_91_120 decimal(20,2),
	range_120 decimal(20,2),
	max_age int,
	max_days_over_due int,
	total_overpaid decimal(20,2),
	terms int,
	avg_days_to_pay int,
	index rpt_txn_id(rpt_txn_id)
);

ALTER TABLE tmp_xms_tbl_customer_aging
DROP COLUMN max_age,
DROP COLUMN max_days_over_due,
DROP COLUMN avg_days_to_pay,
ADD COLUMN avg_invoice_age decimal(10,2),
ADD COLUMN max_invoice_age int,
ADD COLUMN avg_days_overdue decimal(10,2),
ADD COLUMN max_days_overdue int,
ADD COLUMN avg_days_to_pay decimal(10,2);

ALTER TABLE tmp_xms_tbl_customer_aging_invoice
ADD COLUMN days_to_pay int;
-- ================== MANGAGE ADJUSTMENT CLAIM REQUEST ======================================
CREATE TABLE `xms_tbl_airbill_adjustment_request` (
  `adjustment_request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adjustment_type` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `shipmentid` bigint(20) NOT NULL,
  `airbill_number` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `carrier_amount` decimal(20,2) NOT NULL,
  `gst_carrier_amount` decimal(20,2) NOT NULL,
  `customer_amount` decimal(20,2) NOT NULL,
  `gst_customer_amount` decimal(20,2) NOT NULL,
  `carrier_credit` decimal(20,2) NOT NULL,
  `gst_carrier_credit` decimal(20,2) NOT NULL,
  `request_carrier` tinyint(1) NOT NULL,
  `note` text COLLATE utf8_unicode_ci NOT NULL,
  `credit_type` tinyint(1) NOT NULL COMMENT '0 - Upon carrier approval, 1 - Credit Now',
  `apply_gst_type` tinyint(1) NOT NULL COMMENT '0 - Non - GST Airbill (Not Applicable)',
  `status` tinyint(1) NOT NULL COMMENT '1 - Submitted, 2 - Pending, 3 - Disputed, 4 - Approved, 5 - Disputed Denied, 6 - Deleted',
  `actual_delivery_date` datetime DEFAULT NULL,
  `franchise_amount` decimal(20,2) DEFAULT '0.00',
  `gst_franchise_amount` decimal(20,2) DEFAULT '0.00',
  `reason_for_deleting` text COLLATE utf8_unicode_ci,
  `franchise_comments_to_fsc` text COLLATE utf8_unicode_ci,
  `fsc_credit_note` text COLLATE utf8_unicode_ci,
  `sub_status` tinyint(4) DEFAULT '0',
  `start_pausing_date` date DEFAULT NULL,
  PRIMARY KEY (`adjustment_request_id`),
  KEY `shipmentid` (`shipmentid`),
  KEY `airbill_number` (`airbill_number`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `xms_tbl_adjustment_request_history` (
  `adjustment_request_id` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `actiondate` datetime NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '1 - Submitted, 2 - Pending, 3 - Disputed, 4 - Approved, 5 - Disputed Denied',
  KEY `adjustment_request_id` (`adjustment_request_id`),
  KEY `userid` (`userid`),
  CONSTRAINT `xms_tbl_adjustment_request_history_ibfk_1` FOREIGN KEY (`adjustment_request_id`) REFERENCES `xms_tbl_airbill_adjustment_request` (`adjustment_request_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- =========== FRANCHISE RECEIVABLE SCRIPTS ==================================================
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
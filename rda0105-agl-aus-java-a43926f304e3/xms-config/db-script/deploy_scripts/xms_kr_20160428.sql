-- NOTE: THIS PART MUST BE DONE MANUALLY
-- ------------------------INSERT XMS 2.0 MENUS LOCALIZATION----------------------------------------------------
INSERT INTO `xms_tbl_localization` (`localizationid`, `localizationname`, `parentid`, `setup`, `default_text`) VALUES ('', 'franchise 2', '54,335,281,288,289,333,314,285,383,292,227,301,306,304,294,342,297,299,321,318,361,363,362,1865,359,356,344,341,867,366,870,927,985,1066,2366,368', '0', 'Franchise 2.0');

-- ------------------------INSERT XMS 2.0 MENUS----------------------------------------------------
-- NOTE: GET ID FROM LOCALIZATION AND PUT IN <ID> IN THE FIRST INSERT QUERY
INSERT INTO `xms_tbl_menu` (`menuid`, `menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES (NULL, 'Franchise 2.0', '4', '5', '1.0', '2.0,3.0', '0', '0', '#', <ID>);

-- NOTE: GET ID FROM FIRST QUERY AND PUT IN THE <ID> IN FOLLOWING QUERIES
INSERT INTO `xms_tbl_menu` (`menuid`, `menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES (NULL, 'Franchise Payables', <ID>, '101', '3.0', '', '0', '0', 'XMS2:/fpb_ms.ix', '344');
INSERT INTO `xms_tbl_menu` (`menuid`, `menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES (NULL, 'Freeze Franchise Payables', <ID>, '103', '1.0', '2.0', '0', '0', 'XMS2:/fpb_freeze_inv_ms.ix', '345');
INSERT INTO `xms_tbl_menu` (`menuid`, `menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES (NULL, 'Send Franchise Payables E-mails', <ID>, '104', '1.0', '2.0', '0', '0', 'XMS2:/fpb_send_inv_ms.ix', '347');
INSERT INTO `xms_tbl_menu` (`menuid`, `menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES (NULL, 'Franchise Payables Invoice', <ID>, '102', '3.0', '', '0', '0', 'XMS2:/fpb_inv_ms.ix', '2612');


UPDATE `xms_tbl_menu` SET `url`='XMS2:/receive_payment.ix' WHERE `menuid`='52';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/invoice_overpayment.ix' WHERE `menuid`='56';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/credit_notes.ix' WHERE `menuid`='42';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/freeze_credit_notes.ix' WHERE `menuid`='49';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/send_credit_notes.ix' WHERE `menuid`='141';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/unfreeze_credit_notes.ix' WHERE `menuid`='144';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/manage_adjustment.ix' WHERE `menuid`='50';
-- END MANUALLY

CREATE TABLE `xms_tbl_session_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(200) DEFAULT NULL,
  `content` text,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

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

-- ==========================BEGIN CREATE DATA FOR FRANCHISE PAYABLE REPORT 

DROP TABLE IF EXISTS xms_tbl_rpt_fran_pab_credit;

DROP TABLE IF EXISTS xms_tbl_rpt_fran_pab_deduct;

DROP TABLE IF EXISTS xms_tbl_rpt_fran_pab_margin_and_61days;

DROP TABLE IF EXISTS xms_tbl_rpt_fran_pab_non_central;

DROP TABLE IF EXISTS xms_tbl_rpt_fran_pab_overpayment;


DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_credit;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_credit
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	customer_code bigint(20),
	customer_name varchar(500),
	invoice_code varchar(200),
	airbill_number varchar(200),
	carrier_amount decimal(20,2),
	carrier_gst decimal(20,2),
	credit_amount decimal(20,2),
	credit_gst decimal(20,2),
	cur_fran_credit_amount decimal(20,2),
	prev_fran_credit_amount decimal(20,2),
	customer_amount decimal(20,2),
	customer_gst decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);

DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_deduct;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_deduct
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	customer_code bigint(20),
	customer_name varchar(500),
    invoice_code varchar(200),
    invoice_date date,
    invoice_date_61 date,
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,	 
    customer_payment decimal(20,2),
    cust_cost decimal(20,2),
    cust_tax decimal(20,2),
    fran_cost decimal(20,2),
    fran_tax decimal(20,2),
    franchise_charge decimal(20,2),
    index rpt_txn_id(rpt_txn_id)
);

DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_margin_and_61days;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_margin_and_61days
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	apply_date date,
    invoice_code varchar(200),
    invoice_date date,
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    customer_code bigint(20),
    customer_name varchar(500),
    rev_paid decimal(20,2),
    prev_paid decimal(20,2),
    cust_cost decimal(20,2),
    cust_tax decimal(20,2),
    fran_cost decimal(20,2),
    fran_tax decimal(20,2),
    total_cust_credit decimal(20,2),
    cur_cust_credit decimal(20,2),
    total_cust_carrier_credit decimal(20,2),
    cur_cust_carrier_credit decimal(20,2),
    total_fran_carrier_credit decimal(20,2),
    cur_fran_carrier_credit decimal(20,2),
    profit_share decimal(5,2),
    late_fee_share decimal(5,2),
    management_service_fee decimal(5,2),
    inter_domes varchar(20),
    index rpt_txn_id(rpt_txn_id),
    index apply_date(apply_date),
    index invoice_date(invoice_date),
    index rpt_txn_id_apply_date(rpt_txn_id,apply_date),
    index rpt_txn_id_apply_date_invoice_date(rpt_txn_id,apply_date,invoice_date)
);



DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_margin;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_margin
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	apply_date date,
    invoice_code varchar(200),
    invoice_date date,
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    customer_code bigint(20),
    customer_name varchar(500),
    rev_paid decimal(20,2),
    prev_paid decimal(20,2),
    cust_cost decimal(20,2),
    cust_tax decimal(20,2),
    fran_cost decimal(20,2),
    fran_tax decimal(20,2),
    total_cust_credit decimal(20,2),
    cur_cust_credit decimal(20,2),
    total_cust_carrier_credit decimal(20,2),
    cur_cust_carrier_credit decimal(20,2),
    total_fran_carrier_credit decimal(20,2),
    cur_fran_carrier_credit decimal(20,2),
    profit_share decimal(5,2),
    late_fee_share decimal(5,2),
    management_service_fee decimal(5,2),
    inter_domes varchar(20),
    index rpt_txn_id(rpt_txn_id),
    index apply_date(apply_date),
    index invoice_date(invoice_date),
    index rpt_txn_id_apply_date(rpt_txn_id,apply_date),
    index rpt_txn_id_apply_date_invoice_date(rpt_txn_id,apply_date,invoice_date)
);

ALTER TABLE `tmp_xms_tbl_fran_pab_margin` 
ADD COLUMN `pausing_day` BIGINT NULL DEFAULT 0 AFTER `inter_domes`;

DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_margin61;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_margin61
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	apply_date date,
    invoice_code varchar(200),
    invoice_date date,
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    customer_code bigint(20),
    customer_name varchar(500),
    rev_paid decimal(20,2),
    prev_paid decimal(20,2),
    cust_cost decimal(20,2),
    cust_tax decimal(20,2),
    fran_cost decimal(20,2),
    fran_tax decimal(20,2),
    total_cust_credit decimal(20,2),
    cur_cust_credit decimal(20,2),
    total_cust_carrier_credit decimal(20,2),
    cur_cust_carrier_credit decimal(20,2),
    total_fran_carrier_credit decimal(20,2),
    cur_fran_carrier_credit decimal(20,2),
    profit_share decimal(5,2),
    late_fee_share decimal(5,2),
    management_service_fee decimal(5,2),
    inter_domes varchar(20),
    index rpt_txn_id(rpt_txn_id),
    index apply_date(apply_date),
    index invoice_date(invoice_date),
    index rpt_txn_id_apply_date(rpt_txn_id,apply_date),
    index rpt_txn_id_apply_date_invoice_date(rpt_txn_id,apply_date,invoice_date)
);

ALTER TABLE `tmp_xms_tbl_fran_pab_margin61` 
ADD COLUMN `pausing_day` BIGINT NULL DEFAULT 0 AFTER `inter_domes`;

ALTER TABLE `tmp_xms_tbl_fran_pab_margin61` 
ADD COLUMN `invoice_date_6x` DATE NULL AFTER `pausing_day`,
ADD COLUMN `real_payment_in_60d` DECIMAL(20,2) NULL AFTER `invoice_date_6x`,
ADD COLUMN `total_fran_carrier_credit_in_60d` DECIMAL(20,2) NULL AFTER `real_payment_in_60d`,
ADD COLUMN `real_payment_60d_startdate` DECIMAL(20,2) NULL AFTER `total_fran_carrier_credit_in_60d`,
ADD COLUMN `total_cus_carrier_credit_60d_startdate` DECIMAL(20,2) NULL AFTER `real_payment_60d_startdate`;


DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_non_central;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_non_central
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	apply_date date,
    invoice_code varchar(200),
    invoice_date date,
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    customer_code bigint(20),
    customer_name varchar(500),
    rev_paid decimal(20,2),
    prev_paid decimal(20,2),
    cust_cost decimal(20,2),
    cust_tax decimal(20,2),
    fran_cost decimal(20,2),
    fran_tax decimal(20,2),
    total_fran_carrier_credit decimal(20,2),
    cur_fran_carrier_credit decimal(20,2),
    total_cust_carrier_credit decimal(20,2),
    cur_cust_carrier_credit decimal(20,2),
    total_cust_credit decimal(20,2),
    cur_cust_credit decimal(20,2),
    profit_share decimal(5,2),
    late_fee_share decimal(5,2),
    management_service_fee decimal(5,2),
    inter_domes varchar(20),
    index rpt_txn_id(rpt_txn_id),
    index apply_date(apply_date),
    index invoice_date(invoice_date),
    index rpt_txn_id_apply_date(rpt_txn_id,apply_date),
    index rpt_txn_id_apply_date_invoice_date(rpt_txn_id,apply_date,invoice_date)
);

DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_overpayment;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_overpayment
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date datetime,
    customer_code bigint(20),
    customer_name varchar(500),
    over_amount decimal(20,2),
    overpayment_type varchar(20),
    index rpt_txn_id(rpt_txn_id)
);

DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_rpt_txn_id;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_rpt_txn_id
(
	rpt_txn_id varchar(128),
	created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	description text,
	index rpt_txn_id(rpt_txn_id),
	index created_date(created_date)
);

-- ===========================================================================
-- NEW TABLES
-- ===========================================================================
DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_margin;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_margin
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number varchar(200),
	customer_name varchar(500),
	international_domestic varchar(50),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	credits_franchise_cost decimal(20,2),
	credits_customer_cost decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2)
);
DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_61days;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_61days
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number varchar(200),
	customer_name varchar(500),
	international_domestic varchar(50),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	credits_franchise_cost decimal(20,2),
	credits_customer_cost decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2),
    previously_deducted_cost decimal(20,2),
    profit_share_on_late_fees decimal(20,2)
);
DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_credit;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_credit
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	customer_name varchar(500),
	invoice_number varchar(200),
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
	carrier_amount decimal(20,2),
    carrier_amount_gst decimal(20,2),
    carrier_credit decimal(20,2),
	carrier_credit_gst decimal(20,2),
    customer_amount decimal(20,2),
    customer_amount_gst decimal(20,2)
);
DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_deduct;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_deduct
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	customer_name varchar(500),
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_payment decimal(20,2),
	customer_cost decimal(20,2),
	customer_tax decimal(20,2),
	franchise_cost decimal(20,2),
	franchise_tax decimal(20,2),
	franchise_charge decimal(20,2)
);
DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_non_central;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_non_central
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number bigint(20),
	customer_name varchar(500),
	international_domestic varchar(20),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	credits_franchise_cost decimal(20,2),
	credits_customer_cost decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2)
);
DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_overpayment;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_overpayment
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	origin_payment_date datetime,
	customer_number bigint(20),
	customer_name varchar(500),
	overpayment_type varchar(50),
	amount decimal(20,2)
);

-- ==================================================================
-- New tables for frozen
-- ==================================================================
DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_margin;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_margin
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number varchar(200),
	customer_name varchar(500),
	international_domestic varchar(50),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	credits_franchise_cost decimal(20,2),
	credits_customer_cost decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2)
);
DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_61days;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_61days
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number varchar(200),
	customer_name varchar(500),
	international_domestic varchar(50),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	credits_franchise_cost decimal(20,2),
	credits_customer_cost decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2),
    previously_deducted_cost decimal(20,2),
    profit_share_on_late_fees decimal(20,2)
);
DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_credit;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_credit
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	customer_name varchar(500),
	invoice_number varchar(200),
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
	carrier_amount decimal(20,2),
    carrier_amount_gst decimal(20,2),
    carrier_credit decimal(20,2),
	carrier_credit_gst decimal(20,2),
    customer_amount decimal(20,2),
    customer_amount_gst decimal(20,2)
);
DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_deduct;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_deduct
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	customer_name varchar(500),
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_payment decimal(20,2),
	customer_cost decimal(20,2),
	customer_tax decimal(20,2),
	franchise_cost decimal(20,2),
	franchise_tax decimal(20,2),
	franchise_charge decimal(20,2)
);
DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_non_central;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_non_central
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number bigint(20),
	customer_name varchar(500),
	international_domestic varchar(20),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	credits_franchise_cost decimal(20,2),
	credits_customer_cost decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2)
);
DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_overpayment;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_overpayment
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	origin_payment_date datetime,
	customer_number bigint(20),
	customer_name varchar(500),
	overpayment_type varchar(50),
	amount decimal(20,2)
);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_margin` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_61days` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_credit` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_deduct` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_non_central` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_overpayment` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);
-- ===============================================
ALTER TABLE `xms_tbl_ms_rpt_fran_pab_margin` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `xms_tbl_ms_rpt_fran_pab_61days` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `xms_tbl_ms_rpt_fran_pab_credit` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `xms_tbl_ms_rpt_fran_pab_deduct` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `xms_tbl_ms_rpt_fran_pab_non_central` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `xms_tbl_ms_rpt_fran_pab_overpayment` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

-- ===========================================================
-- ALTER TABLE to add auto increment column
-- ===========================================================
ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_margin
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_61days
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_credit
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_deduct
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_non_central
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_overpayment
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE xms_tbl_ms_rpt_fran_pab_margin
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE xms_tbl_ms_rpt_fran_pab_61days
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE xms_tbl_ms_rpt_fran_pab_credit
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE xms_tbl_ms_rpt_fran_pab_deduct
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE xms_tbl_ms_rpt_fran_pab_non_central
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE xms_tbl_ms_rpt_fran_pab_overpayment
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);
-- new add 20150609
ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_61days
ADD COLUMN `repaid_carrier_deductions` DECIMAL(20,2) DEFAULT 0.00;

ALTER TABLE xms_tbl_ms_rpt_fran_pab_61days
ADD COLUMN `repaid_carrier_deductions` DECIMAL(20,2) DEFAULT 0.00;

-- ===========================================================================
-- Prepare new tables for New Carrier Credit Details
-- ===========================================================================
DROP TABLE IF EXISTS tmp_xms_tbl_fran_pab_credit;

CREATE TABLE IF NOT EXISTS tmp_xms_tbl_fran_pab_credit
(
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	apply_date date,
    invoice_code varchar(200),
    invoice_date date,
    airbill_number varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
    customer_code bigint(20),
    customer_name varchar(500),
    rev_paid decimal(20,2),
    prev_paid decimal(20,2),
    cust_cost decimal(20,2),
    cust_tax decimal(20,2),
    fran_cost decimal(20,2),
    fran_tax decimal(20,2),
    total_cust_credit decimal(20,2),
    cur_cust_credit decimal(20,2),
    total_cust_carrier_credit decimal(20,2),
    cur_cust_carrier_credit decimal(20,2),
    total_fran_carrier_credit decimal(20,2),
    cur_fran_carrier_credit decimal(20,2),
    profit_share decimal(5,2),
    late_fee_share decimal(5,2),
    management_service_fee decimal(5,2),
    inter_domes varchar(20)
);

DROP TABLE IF EXISTS tmp_xms_tbl_ms_rpt_fran_pab_credit;
CREATE TABLE IF NOT EXISTS tmp_xms_tbl_ms_rpt_fran_pab_credit
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number varchar(200),
	customer_name varchar(500),
	international_domestic varchar(50),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2),
	new_margin_exc_gst decimal(20,2),
	new_margin_gst decimal(20,2),
	credits_franchise_cost_exc_gst decimal(20,2),
	credits_franchise_cost_gst decimal(20,2),
	credits_customer_cost_exc_gst decimal(20,2),
	credits_customer_cost_gst decimal(20,2)
);
ALTER TABLE tmp_xms_tbl_ms_rpt_fran_pab_credit
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

DROP TABLE IF EXISTS xms_tbl_ms_rpt_fran_pab_credit;
CREATE TABLE IF NOT EXISTS xms_tbl_ms_rpt_fran_pab_credit
(			
	rpt_txn_id varchar(128),
	franchise_code varchar(3),
	payment_date date,
	invoice_number varchar(200),
	airbill_number varchar(200),
	customer_number varchar(200),
	customer_name varchar(500),
	international_domestic varchar(50),
	customer_total_exc_gst decimal(20,2),
	customer_total_gst decimal(20,2),
	franchise_cost_exc_gst decimal(20,2),
	franchise_cost_gst decimal(20,2),
	previously_paid decimal(20,2),
	payments_received decimal(20,2),
	amount_outstanding decimal(20,2),
	gross_margin_exc_gst decimal(20,2),
	gross_margin_gst decimal(20,2),
	profit_share_exc_gst decimal(20,2),
	profit_share_gst decimal(20,2),
	total_profit_share decimal(20,2),
	new_margin_exc_gst decimal(20,2),
	new_margin_gst decimal(20,2),
	credits_franchise_cost_exc_gst decimal(20,2),
	credits_franchise_cost_gst decimal(20,2),
	credits_customer_cost_exc_gst decimal(20,2),
	credits_customer_cost_gst decimal(20,2)
);
ALTER TABLE xms_tbl_ms_rpt_fran_pab_credit
ADD COLUMN `id` BIGINT(20) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE `tmp_xms_tbl_ms_rpt_fran_pab_credit` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `xms_tbl_ms_rpt_fran_pab_credit` 
ADD INDEX `rpt_txn_id` (`rpt_txn_id` ASC),
ADD INDEX `franchise_code` (`franchise_code` ASC);

ALTER TABLE `tmp_xms_tbl_fran_pab_margin_and_61days` 
ADD COLUMN `pausing_day` BIGINT NULL DEFAULT 0 AFTER `inter_domes`;

--  =========================== NEW 20150622

DROP TABLE IF EXISTS xms_tbl_airbill_pausing_deduct;
CREATE TABLE `xms_tbl_airbill_pausing_deduct` (
  `airbill_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pausing_day` int(11) NOT NULL DEFAULT '0',
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`airbill_number`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

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

-- Add index for xms_tbl_airbill_adjustment
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD INDEX `status` (`status` ASC);

-- Alter xms_tbl_note table to save follow_up_date with null
alter table xms_tbl_note
change column `follow_up_date` `follow_up_date` datetime null;


-- ====== 03-03-2016 ===============
ALTER TABLE `xms_tbl_franchise` 
ADD INDEX `franchise_code` (`franchise_code` ASC);
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD COLUMN `start_pausing_date` DATE NULL AFTER `sub_status`;

ALTER TABLE `xms_tbl_airbill_pausing_deduct` 
CHANGE COLUMN `airbill_number` `airbill_number` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ;


-- ========= INSERT SYSTEM SETTINGS KEY 24-08-2015 ==========
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('Default Franchise Payable Tax Percent', '10', 'Default Franchise Payable Tax Percent', '1', '0');

-- add index into xms_tbl_shipment
-- for optimizing when execute 'get customer list' query
alter table xms_tbl_shipment
add index customer_code(customer_code);

-- create table to keep data for customer summary report
drop table if exists tmp_xms_tbl_customer_summary;
create table tmp_xms_tbl_customer_summary
(
	rpt_txn_id varchar(128),
	customer_code bigint(20), 
	customer_name varchar(500), 
	sales_rep_name varchar(200), 
	customer_cost decimal(20,2), 
	customer_tax decimal(20,2), 
	franchise_cost decimal(20,2), 
	franchise_tax decimal(20,2), 
	carrier_cost decimal(20,2), 
	carrier_tax decimal(20,2), 
	shipment_count bigint(20),
	customer_cost_base_charge decimal(20,2),
	customer_tax_base_charge decimal(20,2),
	franchise_cost_base_charge decimal(20,2),
	franchise_tax_base_charge decimal(20,2),
	carrier_cost_base_charge decimal(20,2),
	carrier_tax_base_charge decimal(20,2),
	dhl_customer_cost decimal(20,2), 
	dhl_customer_tax decimal(20,2), 
	dhl_franchise_cost decimal(20,2), 
	dhl_franchise_tax decimal(20,2), 
	dhl_carrier_cost decimal(20,2), 
	dhl_carrier_tax decimal(20,2), 
	dhl_shipment_count bigint(20),
	dhl_dom_customer_cost decimal(20,2), 
	dhl_dom_customer_tax decimal(20,2), 
	dhl_dom_franchise_cost decimal(20,2), 
	dhl_dom_franchise_tax decimal(20,2), 
	dhl_dom_carrier_cost decimal(20,2), 
	dhl_dom_carrier_tax decimal(20,2), 
	dhl_dom_shipment_count bigint(20),
	tnt_dom_customer_cost decimal(20,2), 
	tnt_dom_customer_tax decimal(20,2), 
	tnt_dom_franchise_cost decimal(20,2), 
	tnt_dom_franchise_tax decimal(20,2), 
	tnt_dom_carrier_cost decimal(20,2), 
	tnt_dom_carrier_tax decimal(20,2), 
	tnt_dom_shipment_count bigint(20),
	toll_priority_customer_cost decimal(20,2), 
	toll_priority_customer_tax decimal(20,2), 
	toll_priority_franchise_cost decimal(20,2), 
	toll_priority_franchise_tax decimal(20,2), 
	toll_priority_carrier_cost decimal(20,2), 
	toll_priority_carrier_tax decimal(20,2), 
	toll_priority_shipment_count bigint(20),
	other_customer_cost decimal(20,2), 
	other_customer_tax decimal(20,2), 
	other_franchise_cost decimal(20,2), 
	other_franchise_tax decimal(20,2), 
	other_carrier_cost decimal(20,2), 
	other_carrier_tax decimal(20,2), 
	other_shipment_count bigint(20),
    index rpt_txn_id(rpt_txn_id)
);
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
INSERT INTO `xms_tbl_invoice_terms` VALUES (0,7),(1,10),(2,12),(3,14),(4,15),(5,17),(6,20),(7,30);
/*!40000 ALTER TABLE `xms_tbl_invoice_terms` ENABLE KEYS */;
UNLOCK TABLES;

-- BEGIN REPORTS
-- customer_activation_report.sql
DROP TABLE IF EXISTS tmp_customer_activation_report;

CREATE TABLE IF NOT EXISTS tmp_customer_activation_report
(
	rpt_txn_id varchar(128),
	customer_code varchar(200),
	customer_name varchar(500),
	sale_rep_name varchar(500),
	submission_date date,
	activation_date date,
	days_to_activate bigint(20), 
	first_invoice varchar(200),
	margin_on_invoice decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);

-- customer_aging_report.sql
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
-- customer_aging_report_fix_bug_882_20160301.sql
drop table if exists tmp_xms_tbl_customer_aging_invoice;
drop table if exists tmp_xms_tbl_customer_aging;

create table tmp_customer_aging_invoice
(
	rpt_txn_id varchar(128),
    customer_code bigint(20),
    sales_rep_id bigint(20),
	collectorid bigint(20),
    term int,
    invoiceid bigint(20),
	invoice_code varchar(200),
	invoice_date date,
	due_date date,
    invoice_age int,
    days_overdue int,
    last_payment_date date,
    total_cost decimal(24,6),
	total_paid decimal(24,6),
	total_due decimal(24,6),
	days_to_pay int,
	index rpt_txn_id(rpt_txn_id),
	index customer_code(customer_code),
    index summary_index(customer_code,invoice_age,days_overdue,days_to_pay)
);

create table tmp_customer_aging (
	rpt_txn_id varchar(128),
	customer_name varchar(500),
	customer_code bigint(20),
	sales_rep_name varchar(500),
	total_due decimal(24,6),
	total_overdue decimal(24,6),
	range_0 decimal(24,6),
	range_1_15 decimal(24,6),
	range_16_30 decimal(24,6),
	range_31_45 decimal(24,6),
	range_46_60 decimal(24,6),
	range_61_90 decimal(24,6),
	range_91_120 decimal(24,6),
	range_120 decimal(24,6),
	total_overpaid decimal(24,6),
	terms int,
	avg_invoice_age decimal(24,6),
	max_invoice_age int,
	avg_days_overdue decimal(24,6),
	max_days_overdue int,
	avg_days_to_pay decimal(24,6),
	key rpt_txn_id (rpt_txn_id)
);
-- customer_invoice_detail_report.sql
DROP TABLE IF EXISTS tmp_customer_invoice_detail_report;

CREATE TABLE IF NOT EXISTS tmp_customer_invoice_detail_report
(
	rpt_txn_id varchar(128),
	invoiceid bigint(20),
	invoice_code varchar(200),
	customer_name varchar(500),
	invoice_date date,
	invoice_amount decimal(20,2),
	invoice_credit decimal(20,2),
	net_amount decimal(20,2),
	gst decimal(20,2),
	credit_gst decimal(20,2),
	net_gst decimal(20,2),
	total decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);

DROP TABLE IF EXISTS tmp_customer_credit_note_detail_report;

CREATE TABLE IF NOT EXISTS tmp_customer_credit_note_detail_report
(
	rpt_txn_id varchar(128),
	credit_note_id bigint(20),
	credit_code varchar(200),
	customer_name varchar(500),
	create_date date,
	amount decimal(20,2),
	gst decimal(20,2),
	total decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);
-- customer_status_report.sql
DROP TABLE IF EXISTS tmp_weekly_customer_status_report;

CREATE TABLE IF NOT EXISTS tmp_weekly_customer_status_report
(
	rpt_txn_id varchar(128),
	period date,
	setups bigint(20),
	activations bigint(20),
	shipment_count bigint(20),
	rev_exc_gst decimal(20,2),
	rev_inc_gst decimal(20,2),
	margin_exc_gst decimal(20,2),
	margin_inc_gst decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);

DROP TABLE IF EXISTS tmp_monthly_customer_status_report;

CREATE TABLE IF NOT EXISTS tmp_monthly_customer_status_report
(
	rpt_txn_id varchar(128),
	period date,
	setups bigint(20),
	activations bigint(20),
	shipment_count bigint(20),
	rev_exc_gst decimal(20,2),
	rev_inc_gst decimal(20,2),
	margin_exc_gst decimal(20,2),
	margin_inc_gst decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);
-- customer_status_report_fix_bug_799_20151215.sql
TRUNCATE TABLE tmp_weekly_customer_status_report;

ALTER TABLE tmp_weekly_customer_status_report
ADD COLUMN `start_date` date DEFAULT NULL AFTER `rpt_txn_id`,
ADD COLUMN `end_date` date DEFAULT NULL AFTER `start_date`,
ADD COLUMN `carrier` INT(11) DEFAULT NULL AFTER `end_date`,
DROP COLUMN `period`;

TRUNCATE TABLE tmp_monthly_customer_status_report;

ALTER TABLE tmp_monthly_customer_status_report
ADD COLUMN `start_date` date DEFAULT NULL AFTER `rpt_txn_id`,
ADD COLUMN `end_date` date DEFAULT NULL AFTER `start_date`,
ADD COLUMN `carrier` INT(11) DEFAULT NULL AFTER `end_date`,
DROP COLUMN `period`;
-- update_customer_summary_report.sql
-- update customer summary temp table using for the customer summary report
DROP TABLE IF EXISTS tmp_xms_tbl_customer_summary;
create table tmp_xms_tbl_customer_summary
(
	rpt_txn_id varchar(128),
	customer_code bigint(20), 
	customer_name varchar(500), 
	sale_rep_name varchar(200), 
	-- Total
	cust_cost decimal(20,2), 
	cust_tax decimal(20,2), 
	fran_cost decimal(20,2), 
	fran_tax decimal(20,2), 
	carrier_cost decimal(20,2), 
	carrier_tax decimal(20,2), 
	shipment_count bigint(20),
	-- Base charge
	cust_base_charge_cost decimal(20,2),
	cust_base_charge_tax decimal(20,2),
	fran_base_charge_cost decimal(20,2),
	fran_base_charge_tax decimal(20,2),
	carrier_base_charge_cost decimal(20,2),
	carrier_base_charge_tax decimal(20,2),
	-- Surcharge tax
	cust_surcharge_cost decimal(20,2), 
	cust_surcharge_tax decimal(20,2), 
	fran_surcharge_cost decimal(20,2), 
	fran_surcharge_tax decimal(20,2), 
	carrier_surcharge_cost decimal(20,2), 
	carrier_surcharge_tax decimal(20,2), 
	-- DHL
	dhl_cust_cost decimal(20,2), 
	dhl_cust_tax decimal(20,2), 
	dhl_fran_cost decimal(20,2), 
	dhl_fran_tax decimal(20,2), 
	dhl_carrier_cost decimal(20,2), 
	dhl_carrier_tax decimal(20,2), 
	dhl_shipment_count bigint(20),
	-- DHL Surcharge tax
	dhl_cust_surcharge_cost decimal(20,2), 
	dhl_cust_surcharge_tax decimal(20,2), 
	dhl_fran_surcharge_cost decimal(20,2), 
	dhl_fran_surcharge_tax decimal(20,2), 
	dhl_carrier_surcharge_cost decimal(20,2), 
	dhl_carrier_surcharge_tax decimal(20,2), 
	-- DHL Domestic
	dhl_dom_cust_cost decimal(20,2), 
	dhl_dom_cust_tax decimal(20,2), 
	dhl_dom_fran_cost decimal(20,2), 
	dhl_dom_fran_tax decimal(20,2), 
	dhl_dom_carrier_cost decimal(20,2), 
	dhl_dom_carrier_tax decimal(20,2), 
	dhl_dom_shipment_count bigint(20),
	-- DHL Domestic Surcharge tax
	dhl_dom_cust_surcharge_cost decimal(20,2), 
	dhl_dom_cust_surcharge_tax decimal(20,2), 
	dhl_dom_fran_surcharge_cost decimal(20,2), 
	dhl_dom_fran_surcharge_tax decimal(20,2), 
	dhl_dom_carrier_surcharge_cost decimal(20,2), 
	dhl_dom_carrier_surcharge_tax decimal(20,2), 
	-- TNT Domestic
	tnt_dom_cust_cost decimal(20,2), 
	tnt_dom_cust_tax decimal(20,2), 
	tnt_dom_fran_cost decimal(20,2), 
	tnt_dom_fran_tax decimal(20,2), 
	tnt_dom_carrier_cost decimal(20,2), 
	tnt_dom_carrier_tax decimal(20,2), 
	tnt_dom_shipment_count bigint(20),
	-- TNT Domestic Surcharge tax
	tnt_dom_cust_surcharge_cost decimal(20,2), 
	tnt_dom_cust_surcharge_tax decimal(20,2), 
	tnt_dom_fran_surcharge_cost decimal(20,2), 
	tnt_dom_fran_surcharge_tax decimal(20,2), 
	tnt_dom_carrier_surcharge_cost decimal(20,2), 
	tnt_dom_carrier_surcharge_tax decimal(20,2), 
	-- Toll Priority
	toll_priority_cust_cost decimal(20,2), 
	toll_priority_cust_tax decimal(20,2), 
	toll_priority_fran_cost decimal(20,2), 
	toll_priority_fran_tax decimal(20,2), 
	toll_priority_carrier_cost decimal(20,2), 
	toll_priority_carrier_tax decimal(20,2), 
	toll_priority_shipment_count bigint(20),
	-- Toll Priority Surcharge tax
	toll_priority_cust_surcharge_cost decimal(20,2), 
	toll_priority_cust_surcharge_tax decimal(20,2), 
	toll_priority_fran_surcharge_cost decimal(20,2), 
	toll_priority_fran_surcharge_tax decimal(20,2), 
	toll_priority_carrier_surcharge_cost decimal(20,2), 
	toll_priority_carrier_surcharge_tax decimal(20,2), 
	-- Other
	other_cust_cost decimal(20,2), 
	other_cust_tax decimal(20,2), 
	other_fran_cost decimal(20,2), 
	other_fran_tax decimal(20,2), 
	other_carrier_cost decimal(20,2), 
	other_carrier_tax decimal(20,2), 
	other_shipment_count bigint(20),
	-- Other Surcharge tax
	other_cust_surcharge_cost decimal(20,2), 
	other_cust_surcharge_tax decimal(20,2), 
	other_fran_surcharge_cost decimal(20,2), 
	other_fran_surcharge_tax decimal(20,2), 
	other_carrier_surcharge_cost decimal(20,2), 
	other_carrier_surcharge_tax decimal(20,2), 
    index rpt_txn_id(rpt_txn_id)
);
-- customer_summary_report_fix_bug_884_20160219.sql
-- Fix bug 884
DROP TABLE IF EXISTS tmp_xms_tbl_customer_summary;
create table tmp_xms_tbl_customer_summary
(
	rpt_txn_id varchar(128),
	customer_code bigint(20),
	sales_rep_id bigint(20),
	carrier_id bigint(20), 
	shipment_type_id int(11),
	-- Total
	cust_cost decimal(24,6), 
	cust_tax decimal(24,6),
	fran_cost decimal(24,6), 
	fran_tax decimal(24,6),
	carrier_cost decimal(24,6), 
	carrier_tax decimal(24,6), 
	shipment_count bigint(20),
	-- Base charge
	cust_bc_cost decimal(24,6),
	cust_bc_tax decimal(24,6),
	fran_bc_cost decimal(24,6),
	fran_bc_tax decimal(24,6),
	carrier_bc_cost decimal(24,6),
	carrier_bc_tax decimal(24,6),
	-- Surcharge tax
	cust_sc_cost decimal(24,6), 
	cust_sc_tax decimal(24,6), 
	fran_sc_cost decimal(24,6), 
	fran_sc_tax decimal(24,6), 
	carrier_sc_cost decimal(24,6), 
	carrier_sc_tax decimal(24,6), 
    index rpt_txn_id(rpt_txn_id)
);
-- invoice_pending_airbills_report.sql
-- Add index into shipment_date of xms_tbl_shipment table to improve query of this report
alter table xms_tbl_shipment
add index shipment_date(shipment_date);

analyze table xms_tbl_shipment;
-- overall_franchise_ranking_report.sql
DROP TABLE IF EXISTS tmp_overall_franchise_ranking_report;

CREATE TABLE IF NOT EXISTS tmp_overall_franchise_ranking_report
(
	rpt_txn_id varchar(128),
	franchise_code varchar(20),
	territory varchar(500),
	activate_customers bigint(20),
	activations bigint(20),
	shipments bigint(20),
	weights decimal(20,2),
	rev_inc_gst decimal(20,2),
	rev_exc_gst decimal(20,2),
	rev_per_ship_inc_gst decimal(20,2),
	rev_per_ship_exc_gst decimal(20,2),
	fran_cost_inc_tax decimal(20,2),
	fran_cost_exc_tax decimal(20,2),
	gross_margin_inc_tax decimal(20,2),
	gross_margin_exc_tax decimal(20,2),
	margin_per_ship_inc_tax decimal(20,2),
	margin_per_ship_exc_tax decimal(20,2),
	margin_per_weight_inc_tax decimal(20,2),
	margin_per_weight_exc_tax decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);
-- sales_rep_ranking_report.sql
DROP TABLE IF EXISTS tmp_sales_rep_ranking_report;

CREATE TABLE IF NOT EXISTS tmp_sales_rep_ranking_report
(
	rpt_txn_id varchar(128),
	sale_rep_name varchar(500),
	franchise_code varchar(20),
	territory varchar(500),
	activate_customers bigint(20),
	activations bigint(20),
	shipments bigint(20),
	weights decimal(20,2),
	rev_inc_gst decimal(20,2),
	rev_exc_gst decimal(20,2),
	rev_per_ship_inc_gst decimal(20,2),
	rev_per_ship_exc_gst decimal(20,2),
	fran_cost_inc_tax decimal(20,2),
	fran_cost_exc_tax decimal(20,2),
	gross_margin_inc_tax decimal(20,2),
	gross_margin_exc_tax decimal(20,2),
	margin_per_ship_inc_tax decimal(20,2),
	margin_per_ship_exc_tax decimal(20,2),
	margin_per_weight_inc_tax decimal(20,2),
	margin_per_weight_exc_tax decimal(20,2),
	index rpt_txn_id(rpt_txn_id)
);
-- webship_customer_history_report.sql
DROP TABLE IF EXISTS tmp_webship_customer_history_period;

CREATE TABLE IF NOT EXISTS tmp_webship_customer_history_period
(
	rpt_txn_id varchar(128),
	period date,
	index rpt_txn_id(rpt_txn_id)
);

DROP TABLE IF EXISTS tmp_webship_customer_history_report;

CREATE TABLE IF NOT EXISTS tmp_webship_customer_history_report
(
	rpt_txn_id varchar(128),
	customer_code varchar(200), 
    customer_name varchar(500),
    sale_rep_name varchar(500),
    period date,
    shipment_count bigint(20),
	index rpt_txn_id(rpt_txn_id)
);

-- Add index into create_date of xms_tbl_shipment table to improve query of this report
alter table xms_tbl_shipment
add index create_date(create_date);
-- search_payments_optimize_20160317.sql
alter table xms_tbl_note
add index paymentid(paymentid);


update xms_tbl_menu set url = 'XMS2:/customer_aging.ix' where url like '%customer_aging.php';
update xms_tbl_menu set url = 'XMS2:/search_payments.ix' where url like '%search_payments.php';
update xms_tbl_menu set url = 'XMS2:/customer_status.ix' where url like '%customer_status_report.php';
update xms_tbl_menu set url = 'XMS2:/customer_summary.ix' where url like '%customer_summary_report.php';
update xms_tbl_menu set url = 'XMS2:/customer_activation.ix' where url like '%activations_report.php';
update xms_tbl_menu set url = 'XMS2:/customer_inactivity.ix' where url like '%inactivity_report.php';
update xms_tbl_menu set url = 'XMS2:/customer_13weeks_activity.ix' where url like '%13weeks_activity_report.php';
update xms_tbl_menu set url = 'XMS2:/customer_invoice_detail.ix' where url like '%customer_invoice_detail.php';
update xms_tbl_menu set url = 'XMS2:/overall_fran_ranking.ix' where url like '%overall_franchise_rankings.php';
update xms_tbl_menu set url = 'XMS2:/sales_rep_ranking.ix' where url like '%sales_rep_rankings.php';
update xms_tbl_menu set url = 'XMS2:/webship_cust_history.ix' where url like '%webship_customer_history.php';
update xms_tbl_menu set url = 'XMS2:/webship_quote_history.ix' where url like '%webship_quote_history.php';
update xms_tbl_menu set url = 'XMS2:/invoice_pending_airbill.ix' where url like '%invoice_pending_airbill.php';
update xms_tbl_menu set url = 'XMS2:/webship_customer_detail.ix' where url like '%webship_customer_details.php';
update xms_tbl_menu set url = 'XMS2:/search_customers.ix' where url like '%search_customers.php';
update xms_tbl_menu set url = 'XMS2:/customer_list.ix' where url like '%customer_list.php';
update xms_tbl_menu set url = 'XMS2:/franchise_list.ix' where url like '%franchise_list.php';
update xms_tbl_menu set url = 'XMS2:/manage_users.ix' where url like '%manage_users.php';
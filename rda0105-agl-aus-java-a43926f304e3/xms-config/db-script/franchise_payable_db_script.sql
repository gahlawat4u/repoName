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

-- update xms_tbl_invoice_terms
update xms_tbl_invoice_terms
set days=30 
where invoice_terms_id=7;

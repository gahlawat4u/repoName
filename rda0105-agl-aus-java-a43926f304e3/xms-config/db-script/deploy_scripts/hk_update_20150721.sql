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

-------------------------------------------------------------------------------------------------------------------------
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD COLUMN `franchise_amount` DECIMAL(20,2) NULL DEFAULT 0.00 AFTER `actual_delivery_date`,
ADD COLUMN `gst_franchise_amount` DECIMAL(20,2) NULL DEFAULT 0.00 AFTER `franchise_amount`;
--------------------------------------------------------------------------------------------------------------------------
UPDATE `xms_tbl_menu` SET `url`='XMS2:/receive_payment.ix' WHERE `menuid`='52';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/invoice_overpayment.ix' WHERE `menuid`='56';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/credit_notes.ix' WHERE `menuid`='42';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/freeze_credit_notes.ix' WHERE `menuid`='49';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/send_credit_notes.ix' WHERE `menuid`='141';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/unfreeze_credit_notes.ix' WHERE `menuid`='144';
UPDATE `xms_tbl_menu` SET `url`='XMS2:/manage_adjustment.ix' WHERE `menuid`='50';

========= INSERT SYSTEM SETTINGS KEY 24-08-2015 ==========
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('Default Franchise Payable Tax Percent', '0', 'Default Franchise Payable Tax Percent', '1', '0');


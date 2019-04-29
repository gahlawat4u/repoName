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
-- Add index into shipment_date of xms_tbl_shipment table to improve query of this report
alter table xms_tbl_shipment
add index shipment_date(shipment_date);

analyze table xms_tbl_shipment;


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
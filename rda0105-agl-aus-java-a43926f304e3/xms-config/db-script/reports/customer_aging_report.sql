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
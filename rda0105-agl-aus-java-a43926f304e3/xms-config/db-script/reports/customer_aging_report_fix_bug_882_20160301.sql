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


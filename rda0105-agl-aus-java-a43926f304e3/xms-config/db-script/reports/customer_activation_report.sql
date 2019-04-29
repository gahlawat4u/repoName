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
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
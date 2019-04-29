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
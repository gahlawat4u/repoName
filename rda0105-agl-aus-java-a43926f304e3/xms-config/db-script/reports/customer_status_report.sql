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
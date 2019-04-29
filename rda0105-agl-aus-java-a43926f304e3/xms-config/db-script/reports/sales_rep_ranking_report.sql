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
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
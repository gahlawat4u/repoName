-- Add index into shipment_date of xms_tbl_shipment table to improve query of this report
alter table xms_tbl_shipment
add index shipment_date(shipment_date);

analyze table xms_tbl_shipment;
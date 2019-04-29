ALTER TABLE xms_tbl_shipment_billing 
ADD INDEX pal USING BTREE (pal ASC);
ALTER TABLE xms_tbl_shipment_billing 
ADD INDEX billing_shipment_type_id USING BTREE (billing_shipment_type_id ASC);

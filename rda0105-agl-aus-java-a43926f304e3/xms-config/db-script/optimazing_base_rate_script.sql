CREATE INDEX shipmentId ON xms_tbl_shipment_type (shipment_type_id) USING BTREE;
CREATE INDEX sheet_id ON xms_tbl_rate_sheet (sheet_id) USING BTREE;
CREATE INDEX shipment_type ON xms_tbl_rate_sheet (shipment_type_id) USING BTREE;
CREATE INDEX column_id ON xms_tbl_rate_sheet_column (column_id) USING BTREE;
CREATE INDEX row_id ON xms_tbl_rate_sheet_row (row_id) USING BTREE;
CREATE INDEX carrier ON xms_tbl_carrier_zone (carrier) USING BTREE;


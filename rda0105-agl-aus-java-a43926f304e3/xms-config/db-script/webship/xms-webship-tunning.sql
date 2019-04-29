-- tunning xms_tbl_webship_history
CREATE INDEX customer_code ON xms_tbl_shipment (customer_code);
CREATE INDEX shipmentIdCreateDate ON xms_tbl_shipment (create_date, shipmentid);
CREATE INDEX piece_id ON xms_tbl_piece (piece_id);
CREATE INDEX piece_shipment ON xms_tbl_piece (piece_id, shipmentid);


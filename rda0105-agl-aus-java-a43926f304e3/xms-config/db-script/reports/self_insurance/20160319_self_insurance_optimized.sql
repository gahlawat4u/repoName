ALTER TABLE xms_tbl_piece
ADD INDEX summary_by_shipmentid USING BTREE (shipmentid,weight,dead_weight,dimension_l,dimension_w,dimension_h);

ALTER TABLE xms_tbl_shipment_detail
ADD INDEX summary_amount_by_shipmentid USING BTREE (shipmentid,amount);
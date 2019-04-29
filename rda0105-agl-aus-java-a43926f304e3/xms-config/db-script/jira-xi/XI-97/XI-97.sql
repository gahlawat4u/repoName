
/**
date: 18/07/2017
author: huynh
purpose: Fix missing FS
**/


SET @awb := 7365310014815;


SELECT @shid := shipmentid
FROM xms_tbl_shipment
WHERE airbill_number = @awb;

SELECT @fuel_sc := round(round(base_charge * 11.3, 0) / 100, 2)
FROM xms_tbl_shipment
WHERE airbill_number = @awb;

INSERT INTO xms_tbl_shipment_detail (shipmentid, accessorialid, amount) VALUES (@shid, 406, @fuel_sc);



SET @awb := 7365310014158;

SELECT @shid := shipmentid
FROM xms_tbl_shipment
WHERE airbill_number = @awb;

SELECT @fuel_sc := round(round(base_charge * 11.3, 0) / 100, 2)
FROM xms_tbl_shipment
WHERE airbill_number = @awb;

INSERT INTO xms_tbl_shipment_detail (shipmentid, accessorialid, amount) VALUES (@shid, 406, @fuel_sc);



SET @awb := 7365310014156;

SELECT @shid := shipmentid
FROM xms_tbl_shipment
WHERE airbill_number = @awb;

SELECT @fuel_sc := round(round(base_charge * 11.3, 0) / 100, 2)
FROM xms_tbl_shipment
WHERE airbill_number = @awb;

INSERT INTO xms_tbl_shipment_detail (shipmentid, accessorialid, amount) VALUES (@shid, 406, @fuel_sc);

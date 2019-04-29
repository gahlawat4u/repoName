/**
AU - TNT Domestic to only offer Satchel if Overnight is selected
 */
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 21 AND packageid = 6;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 21 AND packageid = 7;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 21 AND packageid = 8;
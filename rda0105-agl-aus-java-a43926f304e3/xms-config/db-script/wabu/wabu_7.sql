-- SELECT shiptype_pack.sp_id
-- FROM xms_tbl_shipment_type_package AS shiptype_pack
-- INNER JOIN xms_tbl_package AS pack ON shiptype_pack.packageid = pack.packageid
-- INNER JOIN xms_tbl_shipment_type AS shiptype ON shiptype_pack.shipment_type_id = shiptype.shipment_type_id
-- INNER JOIN xms_tbl_service AS service ON shiptype.serviceid = service.service_id
-- WHERE shiptype.serviceid = '72'
-- AND shiptype.shipment_type_name LIKE '%Fixed Price Premium%'
-- AND shiptype_pack.allow_wpx = 1
-- AND shiptype_pack.allow_wpx_addpiece = 1;

UPDATE xms_tbl_shipment_type_package
SET allow_wpx_addpiece = 0
WHERE sp_id IN (208, 209, 210, 211, 212);

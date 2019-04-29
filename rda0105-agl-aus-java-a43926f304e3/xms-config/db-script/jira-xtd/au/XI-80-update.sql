/**
AU - TNT Domestic to only offer Satchel if Overnight is selected
 */

INSERT INTO `xms_tbl_shipment_type_package` (`shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('21', '6', 'WPX', '0', '1', '0', '1', '1');
INSERT INTO `xms_tbl_shipment_type_package` (`shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('21', '7', 'WPX', '0', '1', '0', '1', '1');
INSERT INTO `xms_tbl_shipment_type_package` (`shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('21', '8', 'WPX', '0', '1', '0', '1', '1');

DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 18 AND packageid = 5;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 23 AND packageid = 5;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 52 AND packageid = 5;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 22 AND packageid = 5;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 50 AND packageid = 5;
DELETE FROM xms_tbl_shipment_type_package WHERE shipment_type_id = 51 AND packageid = 5;


------------------------rollback package Satchel for TNT Domestic-------------
------------------------ DO NOT RUN ------------------------------------
-- INSERT INTO `xms_tbl_shipment_type_package` (`sp_id`, `shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('105', '18', '5', 'DOX', '1', '1', '1', '1', '1');
-- INSERT INTO `xms_tbl_shipment_type_package` (`sp_id`, `shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('113', '23', '5', 'DOX', '1', '1', '1', '1', '1');
-- INSERT INTO `xms_tbl_shipment_type_package` (`sp_id`, `shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('117', '52', '5', 'DOX', '1', '1', '1', '1', '1');
-- INSERT INTO `xms_tbl_shipment_type_package` (`sp_id`, `shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('121', '22', '5', 'DOX', '1', '1', '1', '1', '1');
-- INSERT INTO `xms_tbl_shipment_type_package` (`sp_id`, `shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('125', '50', '5', 'DOX', '1', '1', '1', '1', '1');
-- INSERT INTO `xms_tbl_shipment_type_package` (`sp_id`, `shipment_type_id`, `packageid`, `default_content_type`, `allow_dox_addpiece`, `allow_wpx_addpiece`, `allow_dox`, `allow_wpx`, `allow_custom_value`) VALUES ('129', '51', '5', 'DOX', '1', '1', '1', '1', '1');






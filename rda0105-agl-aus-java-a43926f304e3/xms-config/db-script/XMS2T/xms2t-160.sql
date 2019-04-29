INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '228');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '228');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 228));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 228));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 228));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 228));


INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '229');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '229');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 229));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 229));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 229));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 229));

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '230');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '230');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 230));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 230));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 230));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 230));

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '231');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '231');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 231));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 231));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 231));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 231));

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '232');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '232');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 232));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 232));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 232));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 232));

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '233');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '233');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 233));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 233));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 233));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 233));

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '234');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '234');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 234));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 234));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 234));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 234));

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Dangerous Goods', 'checkbox', 'dangerousgoods', '235');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Authorized to Leave (ATL)', 'checkbox', 'atl', '235');

INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('Where to leave', 'text', 'atltoleave', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'atl' and shipment_type_id = 235));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('UN Number (4Digits)', 'text', 'unnumber', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 235));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_list_value`, `add_con_detail_code`, `add_con_id`) VALUES ('Packing Group', 'list', '1,2,3,4', 'packinggroup', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 235));
INSERT INTO `xms_tbl_service_add_con_details` (`add_con_detail_name`, `add_con_detail_type`, `add_con_detail_code`, `add_con_id`) VALUES ('I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.', 'checkbox', 'msda', (select add_con_id FROM xms_tbl_service_add_con where add_con_code = 'dangerousgoods' and shipment_type_id = 235));

UPDATE `xms_tbl_service_add_con_details` 
SET 
    `add_con_detail_name` = 'Packing Group'
WHERE
    `add_con_detail_name` = 'Packaging Group';


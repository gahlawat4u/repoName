insert into xms_tbl_service (service_id, service_name, inactive, allow_change_name, carrier_type, integrated, non_centralized )
values (400, 'UPS International',0,	1,	0,	0,	0);

select @serviceId := service_id from xms_tbl_service where service_name= 'UPS International';

INSERT INTO xms_tbl_shipment_type ( `service_priority`, `shipment_type_name`, `edi_description`, `service_code`, `basic_charge`, `document`, `document_inbound`, `package`, `package_inbound`, `pak`, `pak_inbound`, `allow_non_carrier`, `allow_carrier`, `carrier_document_rate`, `carrier_document_inbound_rate`, `carrier_package_rate`, `carrier_package_perweight_rate`, `carrier_package_inbound_rate`, `carrier_package_inbound_perweight_rate`, `carrier_pak_rate`, `carrier_pak_inbound_rate`, `carrier_pak_perweight_rate`, `carrier_pak_inbound_perweight_rate`, `modified_date`, `non_carrier_document_rate`, `non_carrier_document_inbound_rate`, `non_carrier_package_rate`, `non_carrier_package_perweight_rate`, `non_carrier_package_inbound_rate`, `non_carrier_package_inbound_perweight_rate`, `non_carrier_pak_rate`, `non_carrier_pak_inbound_rate`, `non_carrier_pak_perweight_rate`, `non_carrier_pak_inbound_perweight_rate`, `serviceid`, `no_of_rate`, `localizationid`, `perweight_status`, `show_status`, `show_domestic`, `global_product_code_doc`, `global_product_code_non_doc`, `local_product_code_doc`, `local_product_code_non_doc`, `doc_outbound_tax`, `nondoc_outbound_tax`, `doc_inbound_tax`, `nondoc_inbound_tax`, `allow_change_name`, `start_with_carrier_name`, `per_kg`, `service_group`) VALUES
(1, 'Express Saver', 'Saver', '65', 0.00, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 379, 0, 0, 0, 0, 0, 0, 0, NOW(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, @serviceId, 0, 0, 0, 1, 0, ' ', ' ', ' ', 'per additional 1 kg', NULL, NULL, NULL, NULL, 1, 0, NULL, 'ups_express_saver'),
(1, 'Expedited', 'Expedited', '08', 0.00, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 379, 0, 0, 0, 0, 0, 0, 0, NOW(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, @serviceId, 0, 0, 0, 1, 2, ' ', ' ', ' ', 'per additional 1 kg', NULL, NULL, NULL, NULL, 1, 0, NULL, 'ups_expedited');

select @shipmentTypeSaver:=shipment_type_id from  xms_tbl_shipment_type where shipment_type_name = 'Express Saver' and serviceid=@serviceId;
select @shipmentTypeExpedited:=shipment_type_id from  xms_tbl_shipment_type where shipment_type_name = 'Expedited' and serviceid=@serviceId;

insert into xms_tbl_account_service (customer_code, usertype, serviceid, shipment_type_id)
values
(10000001, 1, @serviceId, @shipmentTypeSaver),
(10000001, 1, @serviceId, @shipmentTypeExpedited);

INSERT INTO xms_tbl_package (
	packagename,
	packagetypecode,
	addpiece,
	carrier,
	localizationid,
	content_type,
	config_id
)
VALUES

('UPS Envelope', '01', 0, @serviceId, 0, 2, 0),
('Pak', '04', 0, @serviceId, 0, 2, 0),
('UPS Express Box', '02', 1, @serviceId, 0, 2, 0),
('UPS Express Tube', '02', 1, @serviceId, 0, 2, 0),
('Customer Packaging', '02', 0, @serviceId, 0, 2, 0);

select @packageUpsEnvelop := packageid from xms_tbl_package where carrier=@serviceId and packagename='UPS Envelope';
select @packagePak := packageid from xms_tbl_package where carrier=@serviceId and packagename='Pak';
select @packageUPSExpressBox := packageid from xms_tbl_package where carrier=@serviceId and packagename='UPS Express Box';
select @packageUPSExpressTube := packageid from xms_tbl_package where carrier=@serviceId and packagename='UPS Express Tube';
select @packageCustomerPackaging := packageid from xms_tbl_package where carrier=@serviceId and packagename='Customer Packaging';


insert into xms_tbl_shipment_type_package (shipment_type_id, packageid, default_content_type, allow_dox_addpiece, allow_wpx_addpiece, allow_dox, allow_wpx, allow_custom_value)
values
(@shipmentTypeSaver, @packageUpsEnvelop, 'DOX', 0, 0, 1, 0,1),
(@shipmentTypeSaver, @packagePak, 'WPX', 1, 1, 1 ,1 ,1),
(@shipmentTypeSaver, @packageUPSExpressBox, 'WPX',1 ,1 ,1 ,1 ,1),
(@shipmentTypeSaver, @packageUPSExpressTube, 'WPX',1 ,1 ,1 ,1 ,1),
(@shipmentTypeSaver, @packageCustomerPackaging, 'WPX',1 ,1 ,1 ,1 ,1),
(@shipmentTypeExpedited, @packageUpsEnvelop, 'DOX', 0, 0, 1, 0,1),
(@shipmentTypeExpedited, @packagePak, 'WPX', 1, 1, 1 ,1 ,1),
(@shipmentTypeExpedited, @packageUPSExpressBox, 'WPX',1 ,1 ,1 ,1 ,1),
(@shipmentTypeExpedited, @packageUPSExpressTube, 'WPX',1 ,1 ,1 ,1 ,1),
(@shipmentTypeExpedited, @packageCustomerPackaging, 'WPX',1 ,1 ,1 ,1 ,1);

-- insert into xms_tbl_service_add_con (add_con_name,add_con_type,add_con_code, shipment_type_id)
-- VALUES
-- 	('Insurance',	'checkbox',	'insurance',	@shipmentTypeSaver),
-- 	('Insurance',	'checkbox',	'insurance',	@shipmentTypeExpedited);


insert into xms_tbl_accessorial (code, description, modified_date, typeid, isquotable, carrier) values ('Fuel Surcharge',	'Fuel Surcharge',	NOW(),2,1, @serviceId);
insert into xms_tbl_accessorial (code, description, modified_date, typeid, isquotable, carrier) values ('UPS SECURITY INT',	'Security Surcharge',	NOW(),2,1, 400);

select @accessorialidFuel := accessorialid from xms_tbl_accessorial where carrier = @serviceId and code='Fuel Surcharge';
select @accessorialidSecur := accessorialid from xms_tbl_accessorial where carrier = 400 and code='UPS SECURITY INT';

insert into xms_tbl_accessorial_detail (accessorialid, default_charge, apply_start_date, default_charge_carrier)
values
(@accessorialidFuel, 12.75, '2017-03-30', 12.75),
(@accessorialidSecur, 2, '2017-03-30', 2);

insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level, allow_null)
values
('UPS Intl Conn Number Prefix', 'XMS', 'UPS Intl Conn Number Prefix', 1, 1),
('Volume Weight Divided By (UPS)', '5000', 'Volume Weight Divided By (UPS)',	1,	0);

alter table xms_tbl_franchise add column ups_markup_rate  float NOT NULL DEFAULT '0';

CREATE TABLE `xms_tbl_ups_connote` (
  `connote_id` int(11) NOT NULL AUTO_INCREMENT,
  `shipmentid` bigint(20) NOT NULL,
  `conn_number` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `status` tinyint(4) NOT NULL COMMENT '0=pending, 1=deliver , -1=cancel',
  PRIMARY KEY (`connote_id`),
  KEY `shipmentid` (`shipmentid`),
  CONSTRAINT `xms_tbl_ups_connote_ibfk_1` FOREIGN KEY (`shipmentid`) REFERENCES `xms_tbl_shipment` (`shipmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=13099 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

alter table xms_tbl_ups_zone add COLUMN ups_express_ib tinyint(4) NOT NULL DEFAULT '1' COMMENT '1=outbound, 2=inbound';
update xms_tbl_ups_zone set ups_express_ib = 2 where ups_countryid < 251;

alter table xms_tbl_franchise add column ups_int_markup_rate float NOT NULL DEFAULT '0';

insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level, allow_null) values
('UPS Int Tax Percentage', 10, 'UPS Int Tax Percentage', 1, 0);


insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level, allow_null) values
('UPS Security Charge Inbound', '9.95', 'UPS Security Charge Inbound', 1, 0),
('UPS Security Charge Outbound', '0.50', 'UPS Security Charge Outbound', 1, 0);

-- ============ last change================================================================================================================================================
CREATE TABLE `xms_tbl_ups_area_surcharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `country_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country_code` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `from_postal` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `to_postal` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `city_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `origin_surcharge` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `destination_surcharge` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61546 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level, allow_null) values
('Remote Area Surcharge UPS Charge Per Kg', 1.2, 'Remote Area Surcharge UPS Charge Per Kg', 1, 0),
('Extend Area Surcharge UPS Charge Per Kg', 1, 'Extend Area Surcharge UPS Charge Per Kg', 1, 0),
('Additional Handling Surcharge Minimum Weight Apply', 32, 'Additional Handing Surcharge Minimum Weight Apply', 1, 0),
('Additional Handling Surcharge 1nd Value Dimension Apply', 122, 'Additional Handing Surcharge 1nd Value Dimension Apply', 1, 0),
('Additional Handling Surcharge 2nd Value Dimension Apply', 76, 'Additional Handing Surcharge 2nd Value Dimension Apply', 1, 0),
('Additional Handling Charger Apply', 9.55, 'Additional Handling Charger Apply', 1, 0),
('Large Package Surcharge Minimum Dimensions Data', 330, 'Large Package Surcharge Minimum Dimensions Data', 1, 0),
('Large Package Surcharge Maximum Dimensions Data', 419, 'Large Package Surcharge Maximum Dimensions Data', 1, 0),
('Large Package Surcharge Cubic Weight Apply', 40, 'Large Package Surcharge Cubic Weight Apply', 1, 0),
('Large Package Surcharge Dead Weight Apply', 70, 'Large Package Surcharge Dead Weight Apply', 1, 0),
('Large Package Surcharge Dimensions L Apply', 270, 'Large Package Surcharge Dimensions L Apply', 1, 0),
('Large Package Surcharge Charge', 56.7, 'Large Package Surcharge Charge', 1, 0),
('UPS Fall Large Package Charge', 'Quote not available at present. If you would like a quote please proceed with booking this shipment and your Freight Consultant will be in touch', 'UPS Fall Large Package Charge', 1, 0);

insert into xms_tbl_accessorial (code, description, modified_date, typeid, isquotable, carrier) values
('RAS', 'Remote Area Surcharge', NOW(), 1, 1, 400),
('EAS', 'Extend Area Surcharge', NOW(), 1, 1, 400),
('AHS', 'Additional Handling Surcharge', NOW(), 1, 1, 400),
('LPS', 'Large Package Surcharge', NOW(), 1, 1, 400);

select @accessorialRAS := accessorialid from xms_tbl_accessorial where code = 'RAS' and carrier=400;
select @accessorialEAS := accessorialid from xms_tbl_accessorial where code = 'EAS' and carrier=400;
select @accessorialAHS := accessorialid from xms_tbl_accessorial where code = 'AHS' and carrier=400;
select @accessorialLPS := accessorialid from xms_tbl_accessorial where code = 'LPS' and carrier=400;

insert into xms_tbl_accessorial_detail (accessorialid, default_charge, apply_start_date, default_charge_carrier) values
(@accessorialRAS, 43.00, NOW(), 0.00),
(@accessorialEAS, 33.00, NOW(), 0.00),
(@accessorialAHS, 9.55, NOW(), 0.00),
(@accessorialLPS, 56.70, NOW(), 0.00);

ALTER TABLE `xms_tbl_ups_area_surcharge`
ADD INDEX `index_cityname` (`city_name`) USING BTREE ,
ADD INDEX `index_frompostal` (`from_postal`) USING BTREE ,
ADD INDEX `index_topostal` (`to_postal`) USING BTREE ,
ADD INDEX `index_countryid` (`country_id`) USING BTREE;

create table abc (`customer_code` bigint(20) NOT NULL);
insert into abc select DISTINCT(customer_code) from xms_tbl_customer_base_rate;
insert into xms_tbl_customer_base_rate select null as customer_base_rate_id, cc.customer_code , @shipmentTypeExpedited as shipment_type_id, 0 as rate_type, 0 as weight, 0 as rate,0 as zone_check, 1 as content, 0 as bound,'Expedited - Package' as baserate_description, 400 as carrierid  from abc as cc where cc.customer_code<>0 ;
insert into xms_tbl_customer_base_rate select null as customer_base_rate_id, cc.customer_code , @shipmentTypeSaver as shipment_type_id, 0 as rate_type, 0 as weight, 0 as rate,0 as zone_check, 1 as content, 0 as bound,'Express Saver - Package' as baserate_description, 400 as carrierid  from abc as cc where cc.customer_code<>0;
insert into xms_tbl_customer_base_rate select null as customer_base_rate_id, cc.customer_code , @shipmentTypeSaver as shipment_type_id, 0 as rate_type, 0 as weight, 0 as rate,0 as zone_check, 1 as content, 1 as bound,'Express Saver - Package (Inbound)' as baserate_description, 400 as carrierid  from abc as cc where cc.customer_code<>0;
insert into xms_tbl_customer_base_rate select null as customer_base_rate_id, cc.customer_code , @shipmentTypeExpedited as shipment_type_id, 0 as rate_type, 0 as weight, 0 as rate,0 as zone_check, 1 as content, 1 as bound,'Expedited - Package (Inbound)' as baserate_description, 400 as carrierid  from abc as cc where cc.customer_code<>0 ;
drop table abc;


alter table xms_tbl_shipment_detail add column type INT(1) ;

insert into xms_tbl_accessorial (code, description, modified_date, typeid, isquotable, carrier) values ('UPS GST',	'GST',	NOW(),1,1, 400);

alter table xms_tbl_webship_quote_log_detail add column type TINYINT(1) ;

insert into xms_tbl_accessorial (code, description, modified_date, typeid, isquotable, carrier) VALUES('IWI',	'Agl Warranty International',	NOW(),	1,	0,	400);
select @warrantyAc := accessorialid from xms_tbl_accessorial where code='IWI' and carrier=400;
insert into xms_tbl_accessorial_detail (accessorialid, default_charge, apply_start_date, default_charge_carrier) values
(@warrantyAc, 6.00, NOW(), 4.29);
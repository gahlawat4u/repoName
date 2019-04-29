ALTER TABLE xms_tbl_package
ADD content_type INT DEFAULT 2,
ADD config_id INT DEFAULT 0;

-- tunning xms_tbl_webship_quote_log
ALTER TABLE `xms_tbl_webship_quote_log` 
ADD INDEX `customer_code` (`customer_code` ASC);

--
-- Table structure for table `xms_tbl_shipment_type_package`
--

DROP TABLE IF EXISTS `xms_tbl_shipment_type_package`;
CREATE TABLE `xms_tbl_shipment_type_package` (
  `sp_id` int(11) NOT NULL AUTO_INCREMENT,
  `shipment_type_id` int(11) NOT NULL,
  `packageid` int(11) NOT NULL,
  `default_content_type` varchar(3) CHARACTER SET latin1 NOT NULL,
  `allow_dox_addpiece` int(1) NOT NULL,
  `allow_wpx_addpiece` int(1) NOT NULL,
  `allow_dox` int(1) NOT NULL,
  `allow_wpx` int(1) NOT NULL,
  `allow_custom_value` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`sp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;
INSERT INTO `xms_tbl_shipment_type_package` VALUES (105,18,5,'DOX',1,1,1,1,1),(106,18,6,'WPX',0,1,0,1,1),(107,18,7,'WPX',0,1,0,1,1),(108,18,8,'WPX',0,1,0,1,1),(109,21,5,'DOX',1,1,1,1,1),(110,21,6,'WPX',0,1,0,1,1),(111,21,7,'WPX',0,1,0,1,1),(112,21,8,'WPX',0,1,0,1,1),(113,23,5,'DOX',1,1,1,1,1),(114,23,6,'WPX',0,1,0,1,1),(115,23,7,'WPX',0,1,0,1,1),(116,23,8,'WPX',0,1,0,1,1),(117,52,5,'DOX',1,1,1,1,1),(118,52,6,'WPX',0,1,0,1,1),(119,52,7,'WPX',0,1,0,1,1),(120,52,8,'WPX',0,1,0,1,1),(121,22,5,'DOX',1,1,1,1,1),(122,22,6,'WPX',0,1,0,1,1),(123,22,7,'WPX',0,1,0,1,1),(124,22,8,'WPX',0,1,0,1,1),(125,50,5,'DOX',1,1,1,1,1),(126,50,6,'WPX',0,1,0,1,1),(127,50,7,'WPX',0,1,0,1,1),(128,50,8,'WPX',0,1,0,1,1),(129,51,5,'DOX',1,1,1,1,1),(130,51,6,'WPX',0,1,0,1,1),(131,51,7,'WPX',0,1,0,1,1),(132,51,8,'WPX',0,1,0,1,1),(133,53,1,'DOX',0,0,1,0,1),(134,53,2,'WPX',0,1,1,1,1),(135,53,3,'WPX',0,1,1,1,1),(136,53,4,'DOX',0,1,1,1,1),(137,95,3,'WPX',0,1,1,1,1),(138,96,3,'WPX',0,1,1,1,1),(139,99,3,'WPX',0,1,1,1,1),(140,205,3,'WPX',0,1,1,1,1),(141,203,3,'WPX',0,1,1,1,1),(142,204,3,'WPX',0,1,1,1,1),(143,101,3,'WPX',0,1,1,1,1),(144,102,3,'WPX',0,1,1,1,1),(145,103,3,'WPX',0,1,1,1,1),(146,209,3,'WPX',0,1,1,1,1),(147,210,3,'WPX',0,1,1,1,1),(148,207,3,'WPX',0,1,1,1,1),(149,208,3,'WPX',0,1,1,1,1),(150,104,3,'WPX',0,1,1,1,1),(151,100,3,'WPX',0,1,1,1,1),(152,132,3,'WPX',0,1,1,1,1),(153,135,3,'WPX',0,1,1,1,1),(154,150,3,'WPX',0,1,1,1,1),(155,172,5,'WPX',0,1,1,1,0),(156,172,6,'WPX',0,1,0,1,0),(157,172,7,'WPX',0,1,0,1,0),(158,172,8,'WPX',0,1,0,1,0),(159,173,5,'WPX',0,1,1,1,0),(160,173,6,'WPX',0,1,0,1,0),(161,173,7,'WPX',0,1,0,1,0),(162,173,8,'WPX',0,1,0,1,0),(163,174,5,'WPX',0,1,1,1,0),(164,174,6,'WPX',0,1,0,1,0),(165,174,7,'WPX',0,1,0,1,0),(166,174,8,'WPX',0,1,0,1,0),(167,175,5,'DOX',0,0,1,0,0),(168,176,5,'DOX',0,0,1,0,0),(169,177,5,'DOX',0,0,1,0,0),(170,178,5,'WPX',0,1,1,1,0),(171,178,6,'WPX',0,1,0,1,0),(172,178,7,'WPX',0,1,0,1,0),(173,178,8,'WPX',0,1,0,1,0),(174,179,5,'WPX',0,1,1,1,0),(175,179,6,'WPX',0,1,0,1,0),(176,179,7,'WPX',0,1,0,1,0),(177,179,8,'WPX',0,1,0,1,0),(178,215,5,'WPX',0,1,1,1,1),(179,215,6,'WPX',0,1,1,1,1),(180,215,7,'WPX',0,1,1,1,1),(181,215,8,'WPX',0,1,1,1,1),(182,216,5,'WPX',0,1,1,1,1),(183,216,6,'WPX',0,1,1,1,1),(184,216,7,'WPX',0,1,1,1,1),(185,216,8,'WPX',0,1,1,1,1),(186,217,5,'WPX',0,1,1,1,1),(187,217,6,'WPX',0,1,1,1,1),(188,217,7,'WPX',0,1,1,1,1),(189,217,8,'WPX',0,1,1,1,1),(190,1,1,'DOX',0,0,1,0,1),(191,1,2,'WPX',0,1,1,1,1),(192,1,3,'WPX',0,1,1,1,1),(193,1,4,'DOX',0,1,1,1,1),(194,122,1,'DOX',0,0,1,0,1),(195,122,2,'WPX',0,1,1,1,1),(196,122,3,'WPX',0,1,1,1,1),(197,122,4,'DOX',0,1,1,1,1),(198,128,3,'WPX',0,1,1,1,1),(199,218,1,'DOX',0,0,1,0,1),(200,218,2,'WPX',0,1,1,1,1),(201,218,3,'WPX',0,1,1,1,1),(202,218,4,'DOX',0,1,1,1,1),(203,213,3,'WPX',0,1,1,1,1),(204,214,3,'WPX',0,1,1,1,1);

UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='189');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='188');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='187');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='186');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='185');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='184');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='183');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='182');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='181');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='180');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='179');
UPDATE `xms_tbl_shipment_type_package` SET `allow_custom_value`='0' WHERE (`sp_id`='178');

-- ADD NEW SYSTEM SETTINGS VALUE - 11-09-2015 --
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('TNT Domestic Maximum Shipment Value', '10000', 'TNT Maximum Shipment Value', '1', '0');
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('TNT International Maximum Insurance Coverage', '10000', 'TNT International Maximum Insurance Coverage', '1', '0');

-- Reset password
ALTER TABLE xms_tbl_webship
ADD `reset_password_status` tinyint DEFAULT 0;
UPDATE xms_tbl_webship SET reset_password_status = 0 WHERE create_date <> '';

-- tunning xms_tbl_webship_history
CREATE INDEX customer_code ON xms_tbl_shipment (customer_code);
CREATE INDEX shipmentIdCreateDate ON xms_tbl_shipment (create_date, shipmentid);
CREATE INDEX piece_id ON xms_tbl_piece (piece_id);
CREATE INDEX piece_shipment ON xms_tbl_piece (piece_id, shipmentid);

--
-- Table structure for table `xms_tbl_service_add_con`
--
DROP TABLE IF EXISTS `xms_tbl_service_add_con`;
CREATE TABLE `xms_tbl_service_add_con` (
  `add_con_id` int(11) NOT NULL AUTO_INCREMENT,
  `add_con_name` varchar(500) NOT NULL,
  `add_con_type` varchar(45) DEFAULT NULL,
  `add_con_code` varchar(45) DEFAULT NULL,
  `shipment_type_id` int(11) NOT NULL,
  PRIMARY KEY (`add_con_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

LOCK TABLES `xms_tbl_service_add_con` WRITE;
INSERT INTO `xms_tbl_service_add_con` VALUES (1,'Insurance','checkbox','insurance',172),(2,'Authorized to Leave (ATL)','checkbox','atl',172),(3,'Time Critical Service Request','checkbox','timecriticial',172),(4,'Insurance','checkbox','insurance',173),(5,'Authorized to Leave (ATL)','checkbox','atl',173),(6,'Insurance','checkbox','insurance',174),(7,'Authorized to Leave (ATL)','checkbox','atl',174),(8,'Insurance','checkbox','insurance',175),(9,'Authorized to Leave (ATL)','checkbox','atl',175),(10,'Insurance','checkbox','insurance',176),(11,'Authorized to Leave (ATL)','checkbox','atl',176),(12,'Insurance','checkbox','insurance',177),(13,'Authorized to Leave (ATL)','checkbox','atl',177),(14,'Insurance','checkbox','insurance',178),(15,'Authorized to Leave (ATL)','checkbox','atl',178),(16,'Insurance','checkbox','insurance',179),(17,'Authorized to Leave (ATL)','checkbox','atl',179),(18,'Insurance','checkbox','insurance',18),(19,'Authorized to Leave (ATL)','checkbox','atl',18),(20,'Dangerous Goods','checkbox','dangerousgoods',18),(21,'Insurance','checkbox','insurance',21),(22,'Authorized to Leave (ATL)','checkbox','atl',21),(23,'Dangerous Goods','checkbox','dangerousgoods',21),(24,'Insurance','checkbox','insurance',22),(25,'Authorized to Leave (ATL)','checkbox','atl',22),(26,'Dangerous Goods','checkbox','dangerousgoods',22),(27,'Insurance','checkbox','insurance',23),(28,'Authorized to Leave (ATL)','checkbox','atl',23),(29,'Dangerous Goods','checkbox','dangerousgoods',23),(30,'Insurance','checkbox','insurance',50),(31,'Authorized to Leave (ATL)','checkbox','atl',50),(32,'Dangerous Goods','checkbox','dangerousgoods',50),(33,'Insurance','checkbox','insurance',51),(34,'Authorized to Leave (ATL)','checkbox','atl',51),(35,'Dangerous Goods','checkbox','dangerousgoods',51),(36,'Insurance','checkbox','insurance',52),(37,'Authorized to Leave (ATL)','checkbox','atl',52),(38,'Dangerous Goods','checkbox','dangerousgoods',52),(39,'Insurance','checkbox','insurance',53),(40,'Pre-Clearance','checkbox','preclearance',1),(41,'Insurance','checkbox','insurance',1),(42,'Duties/Taxes Paid by Sender','checkbox','dtpfee',1),(43,'Pre-Clearance','checkbox','preclearance',122),(44,'Insurance','checkbox','insurance',122),(45,'Duties/Taxes Paid by Sender','checkbox','dtpfee',122),(46,'Pre-Clearance','checkbox','preclearance',128),(47,'Insurance','checkbox','insurance',128),(48,'Duties/Taxes Paid by Sender','checkbox','dtpfee',128),(49,'Pre-Clearance','checkbox','preclearance',218),(50,'Insurance','checkbox','insurance',218),(51,'Duties/Taxes Paid by Sender','checkbox','dtpfee',218),(52,'Insurance','checkbox','insurance',213),(53,'Dangerous Goods','checkbox','dangerousgoods',213),(54,'Duties/Taxes Paid by Sender','checkbox','dtpfee',213),(55,'Insurance','checkbox','insurance',214),(56,'Dangerous Goods','checkbox','dangerousgoods',214),(57,'Duties/Taxes Paid by Sender','checkbox','dtpfee',214),(58,'Insurance','checkbox','insurance',95),(59,'Insurance','checkbox','insurance',96),(60,'Insurance','checkbox','insurance',99),(61,'Insurance','checkbox','insurance',203),(62,'Insurance','checkbox','insurance',204),(63,'Insurance','checkbox','insurance',205),(64,'Insurance','checkbox','insurance',101),(65,'Insurance','checkbox','insurance',102),(66,'Insurance','checkbox','insurance',103),(67,'Insurance','checkbox','insurance',104),(68,'Insurance','checkbox','insurance',207),(69,'Insurance','checkbox','insurance',208),(70,'Insurance','checkbox','insurance',209),(71,'Insurance','checkbox','insurance',210),(72,'Insurance','checkbox','insurance',100),(73,'Insurance','checkbox','insurance',132),(74,'Insurance','checkbox','insurance',135),(75,'Insurance','checkbox','insurance',150);
UNLOCK TABLES;

--
-- Table structure for table `xms_tbl_service_add_con_details`
--

DROP TABLE IF EXISTS `xms_tbl_service_add_con_details`;
CREATE TABLE `xms_tbl_service_add_con_details` (
  `add_con_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `add_con_detail_name` varchar(500) DEFAULT NULL,
  `add_con_detail_type` varchar(45) NOT NULL,
  `add_con_detail_list_value` text,
  `add_con_detail_code` varchar(45) NOT NULL,
  `add_con_id` int(11) NOT NULL,
  PRIMARY KEY (`add_con_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

LOCK TABLES `xms_tbl_service_add_con_details` WRITE;
INSERT INTO `xms_tbl_service_add_con_details` VALUES (1,'Where to leave','text',NULL,'atltoleave',2),(2,'Where to leave','text',NULL,'atltoleave',5),(3,'Where to leave','text',NULL,'atltoleave',7),(4,'Where to leave','text',NULL,'atltoleave',9),(5,'Where to leave','text',NULL,'atltoleave',11),(6,'Where to leave','text',NULL,'atltoleave',13),(7,'Where to leave','text',NULL,'atltoleave',15),(8,'Where to leave','text',NULL,'atltoleave',17),(9,'Where to leave','text',NULL,'atltoleave',22),(10,'Where to leave','text',NULL,'atltoleave',25),(11,'Where to leave','text',NULL,'atltoleave',28),(12,'Where to leave','text',NULL,'atltoleave',19),(13,'Where to leave','text',NULL,'atltoleave',31),(14,'Where to leave','text',NULL,'atltoleave',34),(15,'Where to leave','text',NULL,'atltoleave',37),(16,'UN Number (4Digits)','text',NULL,'unnumber',20),(17,'Packaging Group','list','1,2,3,4','packinggroup',20),(18,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',20),(19,'UN Number (4Digits)','text',NULL,'unnumber',23),(20,'Packaging Group','list','1,2,3,4','packinggroup',23),(21,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',23),(22,'UN Number (4Digits)','text',NULL,'unnumber',26),(23,'Packaging Group','list','1,2,3,4','packinggroup',26),(24,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',26),(25,'UN Number (4Digits)','text',NULL,'unnumber',29),(26,'Packaging Group','list','1,2,3,4','packinggroup',29),(27,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',29),(28,'UN Number (4Digits)','text',NULL,'unnumber',32),(29,'Packaging Group','list','1,2,3,4','packinggroup',32),(30,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',32),(31,'UN Number (4Digits)','text',NULL,'unnumber',35),(32,'Packaging Group','list','1,2,3,4','packinggroup',35),(33,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',35),(34,'UN Number (4Digits)','text',NULL,'unnumber',38),(35,'Packaging Group','list','1,2,3,4','packinggroup',38),(36,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',38),(37,'UN Number (4Digits)','text',NULL,'unnumber',53),(38,'Packaging Group','list','1,2,3,4','packinggroup',53),(39,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',53),(40,'UN Number (4Digits)','text',NULL,'unnumber',56),(41,'Packaging Group','list','1,2,3,4','packinggroup',56),(42,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',56),(43,'','list','6:00,06:30,07:00,07:30,08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30,12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30,19:00,19:30,20:00,20:30,21:00,21:30,22:00,22:30,23:00,23:30','seltimecriticial',3);
UNLOCK TABLES;

INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Insurance', 'checkbox', 'insurance', '215');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Insurance', 'checkbox', 'insurance', '216');
INSERT INTO `xms_tbl_service_add_con` (`add_con_name`, `add_con_type`, `add_con_code`, `shipment_type_id`) VALUES ('Insurance', 'checkbox', 'insurance', '217');

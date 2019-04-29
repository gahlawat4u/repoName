ALTER TABLE xms_tbl_package
ADD content_type INT DEFAULT 2,
ADD config_id INT DEFAULT 0;

-- tunning xms_tbl_webship_quote_log
ALTER TABLE `xms_tbl_webship_quote_log` 
ADD INDEX `customer_code` (`customer_code` ASC);

-- shipment_type_package table new ---
-- MySQL dump 10.13  Distrib 5.6.16, for Linux (x86_64)
--
-- Host: localhost    Database: xms2_au
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

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

-- ADD NEW SYSTEM SETTINGS VALUE - 11-09-2015 --
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('TNT Domestic Maximum Shipment Value', '10000', 'TNT Maximum Shipment Value', '1', '0');
INSERT INTO `xms_tbl_system_setting` (`setting_name`, `setting_value`, `description`, `user_level`, `allow_null`) VALUES ('TNT International Maximum Insurance Coverage', '10000', 'TNT International Maximum Insurance Coverage', '1', '0');

-- Update Table customer_default_setting add column
ALTER TABLE xms_tbl_customer_default_setting
ADD `default_shipper_reference` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '';

-- Reset password
ALTER TABLE xms_tbl_webship
ADD `reset_password_status` tinyint DEFAULT 0;
UPDATE xms_tbl_webship SET reset_password_status = 0 WHERE create_date <> '';


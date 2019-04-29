-- MySQL dump 10.13  Distrib 5.6.21, for Win32 (x86)
--
-- Host: localhost    Database: xms_au_live_2
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `xms_tbl_service_add_con_details`
--

DROP TABLE IF EXISTS `xms_tbl_service_add_con_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xms_tbl_service_add_con_details` (
  `add_con_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `add_con_detail_name` varchar(500) DEFAULT NULL,
  `add_con_detail_type` varchar(45) NOT NULL,
  `add_con_detail_list_value` text,
  `add_con_detail_code` varchar(45) NOT NULL,
  `add_con_id` int(11) NOT NULL,
  PRIMARY KEY (`add_con_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xms_tbl_service_add_con_details`
--

LOCK TABLES `xms_tbl_service_add_con_details` WRITE;
/*!40000 ALTER TABLE `xms_tbl_service_add_con_details` DISABLE KEYS */;
INSERT INTO `xms_tbl_service_add_con_details` VALUES (1,'Where to leave','text',NULL,'atltoleave',2),(2,'Where to leave','text',NULL,'atltoleave',5),(3,'Where to leave','text',NULL,'atltoleave',7),(4,'Where to leave','text',NULL,'atltoleave',9),(5,'Where to leave','text',NULL,'atltoleave',11),(6,'Where to leave','text',NULL,'atltoleave',13),(7,'Where to leave','text',NULL,'atltoleave',15),(8,'Where to leave','text',NULL,'atltoleave',17),(9,'Where to leave','text',NULL,'atltoleave',22),(10,'Where to leave','text',NULL,'atltoleave',25),(11,'Where to leave','text',NULL,'atltoleave',28),(12,'Where to leave','text',NULL,'atltoleave',19),(13,'Where to leave','text',NULL,'atltoleave',31),(14,'Where to leave','text',NULL,'atltoleave',34),(15,'Where to leave','text',NULL,'atltoleave',37),(16,'UN Number (4Digits)','text',NULL,'unnumber',20),(17,'Packaging Group','list','1,2,3,4','packinggroup',20),(18,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',20),(19,'UN Number (4Digits)','text',NULL,'unnumber',23),(20,'Packaging Group','list','1,2,3,4','packinggroup',23),(21,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',23),(22,'UN Number (4Digits)','text',NULL,'unnumber',26),(23,'Packaging Group','list','1,2,3,4','packinggroup',26),(24,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',26),(25,'UN Number (4Digits)','text',NULL,'unnumber',29),(26,'Packaging Group','list','1,2,3,4','packinggroup',29),(27,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',29),(28,'UN Number (4Digits)','text',NULL,'unnumber',32),(29,'Packaging Group','list','1,2,3,4','packinggroup',32),(30,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',32),(31,'UN Number (4Digits)','text',NULL,'unnumber',35),(32,'Packaging Group','list','1,2,3,4','packinggroup',35),(33,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',35),(34,'UN Number (4Digits)','text',NULL,'unnumber',38),(35,'Packaging Group','list','1,2,3,4','packinggroup',38),(36,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',38),(37,'UN Number (4Digits)','text',NULL,'unnumber',53),(38,'Packaging Group','list','1,2,3,4','packinggroup',53),(39,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',53),(40,'UN Number (4Digits)','text',NULL,'unnumber',56),(41,'Packaging Group','list','1,2,3,4','packinggroup',56),(42,'I have a MSDS(Material Safety Data Sheet). Dangerous Goods attracts an additional surcharge.','checkbox',NULL,'msda',56),(43,'','list','6:00,06:30,07:00,07:30,08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30,12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30,19:00,19:30,20:00,20:30,21:00,21:30,22:00,22:30,23:00,23:30','seltimecriticial',3);
/*!40000 ALTER TABLE `xms_tbl_service_add_con_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-01 16:50:20

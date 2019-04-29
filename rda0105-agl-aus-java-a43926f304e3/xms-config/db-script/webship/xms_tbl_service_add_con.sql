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
-- Table structure for table `xms_tbl_service_add_con`
--

DROP TABLE IF EXISTS `xms_tbl_service_add_con`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xms_tbl_service_add_con` (
  `add_con_id` int(11) NOT NULL AUTO_INCREMENT,
  `add_con_name` varchar(500) NOT NULL,
  `add_con_type` varchar(45) DEFAULT NULL,
  `add_con_code` varchar(45) DEFAULT NULL,
  `shipment_type_id` int(11) NOT NULL,
  PRIMARY KEY (`add_con_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xms_tbl_service_add_con`
--

LOCK TABLES `xms_tbl_service_add_con` WRITE;
/*!40000 ALTER TABLE `xms_tbl_service_add_con` DISABLE KEYS */;
INSERT INTO `xms_tbl_service_add_con` VALUES (1,'Insurance','checkbox','insurance',172),(2,'Authorized to Leave (ATL)','checkbox','atl',172),(3,'Time Critical Service Request','checkbox','timecriticial',172),(4,'Insurance','checkbox','insurance',173),(5,'Authorized to Leave (ATL)','checkbox','atl',173),(6,'Insurance','checkbox','insurance',174),(7,'Authorized to Leave (ATL)','checkbox','atl',174),(8,'Insurance','checkbox','insurance',175),(9,'Authorized to Leave (ATL)','checkbox','atl',175),(10,'Insurance','checkbox','insurance',176),(11,'Authorized to Leave (ATL)','checkbox','atl',176),(12,'Insurance','checkbox','insurance',177),(13,'Authorized to Leave (ATL)','checkbox','atl',177),(14,'Insurance','checkbox','insurance',178),(15,'Authorized to Leave (ATL)','checkbox','atl',178),(16,'Insurance','checkbox','insurance',179),(17,'Authorized to Leave (ATL)','checkbox','atl',179),(18,'Insurance','checkbox','insurance',18),(19,'Authorized to Leave (ATL)','checkbox','atl',18),(20,'Dangerous Goods','checkbox','dangerousgoods',18),(21,'Insurance','checkbox','insurance',21),(22,'Authorized to Leave (ATL)','checkbox','atl',21),(23,'Dangerous Goods','checkbox','dangerousgoods',21),(24,'Insurance','checkbox','insurance',22),(25,'Authorized to Leave (ATL)','checkbox','atl',22),(26,'Dangerous Goods','checkbox','dangerousgoods',22),(27,'Insurance','checkbox','insurance',23),(28,'Authorized to Leave (ATL)','checkbox','atl',23),(29,'Dangerous Goods','checkbox','dangerousgoods',23),(30,'Insurance','checkbox','insurance',50),(31,'Authorized to Leave (ATL)','checkbox','atl',50),(32,'Dangerous Goods','checkbox','dangerousgoods',50),(33,'Insurance','checkbox','insurance',51),(34,'Authorized to Leave (ATL)','checkbox','atl',51),(35,'Dangerous Goods','checkbox','dangerousgoods',51),(36,'Insurance','checkbox','insurance',52),(37,'Authorized to Leave (ATL)','checkbox','atl',52),(38,'Dangerous Goods','checkbox','dangerousgoods',52),(39,'Insurance','checkbox','insurance',53),(40,'Pre-Clearance','checkbox','preclearance',1),(41,'Insurance','checkbox','insurance',1),(42,'Duties/Taxes Paid by Sender','checkbox','dtpfee',1),(43,'Pre-Clearance','checkbox','preclearance',122),(44,'Insurance','checkbox','insurance',122),(45,'Duties/Taxes Paid by Sender','checkbox','dtpfee',122),(46,'Pre-Clearance','checkbox','preclearance',128),(47,'Insurance','checkbox','insurance',128),(48,'Duties/Taxes Paid by Sender','checkbox','dtpfee',128),(49,'Pre-Clearance','checkbox','preclearance',218),(50,'Insurance','checkbox','insurance',218),(51,'Duties/Taxes Paid by Sender','checkbox','dtpfee',218),(52,'Insurance','checkbox','insurance',213),(53,'Dangerous Goods','checkbox','dangerousgoods',213),(54,'Duties/Taxes Paid by Sender','checkbox','dtpfee',213),(55,'Insurance','checkbox','insurance',214),(56,'Dangerous Goods','checkbox','dangerousgoods',214),(57,'Duties/Taxes Paid by Sender','checkbox','dtpfee',214),(58,'Insurance','checkbox','insurance',95),(59,'Insurance','checkbox','insurance',96),(60,'Insurance','checkbox','insurance',99),(61,'Insurance','checkbox','insurance',203),(62,'Insurance','checkbox','insurance',204),(63,'Insurance','checkbox','insurance',205),(64,'Insurance','checkbox','insurance',101),(65,'Insurance','checkbox','insurance',102),(66,'Insurance','checkbox','insurance',103),(67,'Insurance','checkbox','insurance',104),(68,'Insurance','checkbox','insurance',207),(69,'Insurance','checkbox','insurance',208),(70,'Insurance','checkbox','insurance',209),(71,'Insurance','checkbox','insurance',210),(72,'Insurance','checkbox','insurance',100),(73,'Insurance','checkbox','insurance',132),(74,'Insurance','checkbox','insurance',135),(75,'Insurance','checkbox','insurance',150);
/*!40000 ALTER TABLE `xms_tbl_service_add_con` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-01 16:50:35

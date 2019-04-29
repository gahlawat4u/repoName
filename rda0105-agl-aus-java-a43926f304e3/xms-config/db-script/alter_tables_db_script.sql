-- -----------------------------INVOICE TERMS-----------------------------------------------

DROP TABLE IF EXISTS `xms_tbl_invoice_terms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xms_tbl_invoice_terms` (
  `invoice_terms_id` int(11) NOT NULL,
  `days` int(11) DEFAULT NULL,
  PRIMARY KEY (`invoice_terms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xms_tbl_invoice_terms`
--

LOCK TABLES `xms_tbl_invoice_terms` WRITE;
/*!40000 ALTER TABLE `xms_tbl_invoice_terms` DISABLE KEYS */;
INSERT INTO `xms_tbl_invoice_terms` VALUES (0,7),(1,10),(2,12),(3,14),(4,15),(5,17),(6,20),(7,30);
/*!40000 ALTER TABLE `xms_tbl_invoice_terms` ENABLE KEYS */;
UNLOCK TABLES;


-- Alter column actual_delivery_date of xms_tbl_airbill_adjustment
-- to save the adjustment with actual_delivery_date is null
alter table xms_tbl_airbill_adjustment
change column actual_delivery_date actual_delivery_date datetime null;

-- Adds an extra column to xms_tbl_customer_payment
-- to keep payment type
alter table xms_tbl_customer_payment
add payment_type tinyint(4) default 0;

-- Adds extra columns to xms_tbl_airbill_adjustment
-- to keep Reason for deleting, Franchise comments to FSC, FSC Credit notes
alter table xms_tbl_airbill_adjustment
add column reason_for_deleting text null,
add column franchise_comments_to_fsc text null,
add column fsc_credit_note text null;

-- Adds extra column to xms_tbl_airbill_adjustment
alter table xms_tbl_airbill_adjustment
add column sub_status tinyint(4) null;

-- set default substatus = 0
ALTER TABLE `xms_tbl_airbill_adjustment` 
CHANGE COLUMN `sub_status` `sub_status` TINYINT(4) NULL DEFAULT 0 ;
update xms_tbl_airbill_adjustment set sub_status = 0 where sub_status is null;

-- Add index for xms_tbl_airbill_adjustment
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD INDEX `status` (`status` ASC);

-- Alter xms_tbl_note table to save follow_up_date with null
alter table xms_tbl_note
change column `follow_up_date` `follow_up_date` datetime null;


-- ====== 03-03-2016 ===============
ALTER TABLE `xms_tbl_franchise` 
ADD INDEX `franchise_code` (`franchise_code` ASC);

-- ---------------------ADD MISSING COLUMNS---------------------------------
ALTER TABLE `xms_tbl_airbill_adjustment` 
ADD COLUMN `franchise_amount` DECIMAL(20,2) NULL DEFAULT 0.00 AFTER `actual_delivery_date`,
ADD COLUMN `gst_franchise_amount` DECIMAL(20,2) NULL DEFAULT 0.00 AFTER `franchise_amount`;

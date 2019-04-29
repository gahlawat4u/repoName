CREATE TABLE `xms_tbl_airbill_adjustment_request` (
  `adjustment_request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adjustment_type` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `shipmentid` bigint(20) NOT NULL,
  `airbill_number` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `create_date` datetime NOT NULL,
  `carrier_amount` decimal(20,2) NOT NULL,
  `gst_carrier_amount` decimal(20,2) NOT NULL,
  `customer_amount` decimal(20,2) NOT NULL,
  `gst_customer_amount` decimal(20,2) NOT NULL,
  `carrier_credit` decimal(20,2) NOT NULL,
  `gst_carrier_credit` decimal(20,2) NOT NULL,
  `request_carrier` tinyint(1) NOT NULL,
  `note` text COLLATE utf8_unicode_ci NOT NULL,
  `credit_type` tinyint(1) NOT NULL COMMENT '0 - Upon carrier approval, 1 - Credit Now',
  `apply_gst_type` tinyint(1) NOT NULL COMMENT '0 - Non - GST Airbill (Not Applicable)',
  `status` tinyint(1) NOT NULL COMMENT '1 - Submitted, 2 - Pending, 3 - Disputed, 4 - Approved, 5 - Disputed Denied, 6 - Deleted',
  `actual_delivery_date` datetime DEFAULT NULL,
  `franchise_amount` decimal(20,2) DEFAULT '0.00',
  `gst_franchise_amount` decimal(20,2) DEFAULT '0.00',
  `reason_for_deleting` text COLLATE utf8_unicode_ci,
  `franchise_comments_to_fsc` text COLLATE utf8_unicode_ci,
  `fsc_credit_note` text COLLATE utf8_unicode_ci,
  `sub_status` tinyint(4) DEFAULT '0',
  `start_pausing_date` date DEFAULT NULL,
  PRIMARY KEY (`adjustment_request_id`),
  KEY `shipmentid` (`shipmentid`),
  KEY `airbill_number` (`airbill_number`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `xms_tbl_adjustment_request_history` (
  `adjustment_request_id` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `actiondate` datetime NOT NULL,
  `status` tinyint(1) NOT NULL COMMENT '1 - Submitted, 2 - Pending, 3 - Disputed, 4 - Approved, 5 - Disputed Denied',
  KEY `adjustment_request_id` (`adjustment_request_id`),
  KEY `userid` (`userid`),
  CONSTRAINT `xms_tbl_adjustment_request_history_ibfk_1` FOREIGN KEY (`adjustment_request_id`) REFERENCES `xms_tbl_airbill_adjustment_request` (`adjustment_request_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `xms_tbl_localization` (`localizationname`, `default_text`) VALUES ('manage_adjust_request_2.0', 'Manage Adjustment Request 2.0');
INSERT INTO `xms_tbl_menu` (`menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES ('Manage Adjustment Request', '2', '6', '1.0', '5.0', '0', '0', 'XMS2:/manage_adjustment_request.ix',(select localizationid from xms_tbl_localization where localizationname = 'manage_adjust_request_2.0'));

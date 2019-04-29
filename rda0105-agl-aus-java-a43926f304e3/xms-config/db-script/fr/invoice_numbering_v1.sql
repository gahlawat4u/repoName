CREATE TABLE `xms_tbl_invoice_numbering` (
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `inv_type` varchar(10) NOT NULL,
  `current_unique_number` int(11) NOT NULL,
  PRIMARY KEY(`month`,`year`,`inv_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
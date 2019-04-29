CREATE TABLE `xms_tbl_invoice_numbering` (
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `franchise_code` bigint(20) DEFAULT NULL,
  `invoice_code` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `current_unique_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `xms_tbl_fran_invoice_numbering` (
  `year` INT NULL,
  `month` INT NULL,
  `franchise_code` VARCHAR(20) NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `counter` INT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
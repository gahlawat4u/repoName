-- when insert please check lang_code and orginal as composite key
DROP TABLE IF EXISTS `xms_tbl_language_value`;
CREATE TABLE `xms_tbl_language_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lang_code` varchar(10) NOT NULL,
  `original` text NOT NULL,
  `destination` text,
  `mode` varchar(20) DEFAULT 'PRO',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


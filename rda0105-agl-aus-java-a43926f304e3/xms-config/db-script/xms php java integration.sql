CREATE TABLE `xms_tbl_session_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(200) DEFAULT NULL,
  `content` text,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;


-- please check out http://54.169.18.35:81/svn/xms_au/tag/xms_au_1.
-- login admin xms 1 --> login to 2.0
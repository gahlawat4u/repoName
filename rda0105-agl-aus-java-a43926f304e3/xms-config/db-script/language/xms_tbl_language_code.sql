-- MySQL dump 10.13  Distrib 5.6.20, for Win32 (x86)
--
-- Host: localhost    Database: xms_fr
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `xms_tbl_language_code`
--

DROP TABLE IF EXISTS `xms_tbl_language_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xms_tbl_language_code` (
  `language_name` varchar(200) NOT NULL,
  `language_code` varchar(45) NOT NULL,
  PRIMARY KEY (`language_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xms_tbl_language_code`
--

LOCK TABLES `xms_tbl_language_code` WRITE;
/*!40000 ALTER TABLE `xms_tbl_language_code` DISABLE KEYS */;
INSERT INTO `xms_tbl_language_code` VALUES ('Abkhazian','ab'),('Afar','aa'),('Afrikaans','af'),('Akan','ak'),('Albanian','sq'),('Amharic','am'),('Arabic','ar'),('Aragonese','an'),('Armenian','hy'),('Assamese','as'),('Avaric','av'),('Avestan','ae'),('Aymara','ay'),('Azerbaijani','az'),('Bambara','bm'),('Bashkir','ba'),('Basque','eu'),('Belarusian','be'),('Bengali','bn'),('Bihari languages','bh'),('Bislama','bi'),('BokmÃ¥l, Norwegian','nb'),('Bosnian','bs'),('Breton','br'),('Bulgarian','bg'),('Burmese','my'),('Castilian','es'),('Catalan','ca'),('Central Khmer','km'),('Chamorro','ch'),('Chechen','ce'),('Chewa','ny'),('Chichewa','ny'),('Chinese','zh'),('Chuang','za'),('Church Slavic','cu'),('Church Slavonic','cu'),('Chuvash','cv'),('Cornish','kw'),('Corsican','co'),('Cree','cr'),('Croatian','hr'),('Czech','cs'),('Danish','da'),('Dhivehi','dv'),('Divehi','dv'),('Dutch','nl'),('Dzongkha','dz'),('English','en'),('Esperanto','eo'),('Estonian','et'),('Ewe','ee'),('Faroese','fo'),('Fijian','fj'),('Finnish','fi'),('Flemish','nl'),('French','fr'),('Fulah','ff'),('Gaelic','gd'),('Galician','gl'),('Ganda','lg'),('Georgian','ka'),('German','de'),('Gikuyu','ki'),('Greek','el'),('Greenlandic','kl'),('Guarani','gn'),('Gujarati','gu'),('Haitian','ht'),('Haitian Creole','ht'),('Hausa','ha'),('Hebrew','he'),('Herero','hz'),('Hindi','hi'),('Hiri Motu','ho'),('Hungarian','hu'),('Icelandic','is'),('Ido','io'),('Igbo','ig'),('Indonesian','id'),('Interlingua','ia'),('Interlingue','ie'),('Inuktitut','iu'),('Inupiaq','ik'),('Irish','ga'),('Italian','it'),('Japanese','ja'),('Javanese','jv'),('Kalaallisut','kl'),('Kannada','kn'),('Kanuri','kr'),('Kashmiri','ks'),('Kazakh','kk'),('Kikuyu','ki'),('Kinyarwanda','rw'),('Kirghiz','ky'),('Komi','kv'),('Kongo','kg'),('Korean','ko'),('Kuanyama','kj'),('Kurdish','ku'),('Kwanyama','kj'),('Kyrgyz','ky'),('Lao','lo'),('Latin','la'),('Latvian','lv'),('Letzeburgesch','lb'),('Limburgan','li'),('Limburger','li'),('Limburgish','li'),('Lingala','ln'),('Lithuanian','lt'),('Luba-Katanga','lu'),('Luxembourgish','lb'),('Macedonian','mk'),('Malagasy','mg'),('Malay','ms'),('Malayalam','ml'),('Maldivian','dv'),('Maltese','mt'),('Manx','gv'),('Maori','mi'),('Marathi','mr'),('Marshallese','mh'),('Moldavian','ro'),('Moldovan','ro'),('Mongolian','mn'),('Nauru','na'),('Navaho','nv'),('Navajo','nv'),('Ndebele, North','nd'),('Ndebele, South','nr'),('Ndonga','ng'),('Nepali','ne'),('North Ndebele','nd'),('Northern Sami','se'),('Norwegian','no'),('Norwegian BokmÃ¥l','nb'),('Norwegian Nynorsk','nn'),('Nuosu','ii'),('Nyanja','ny'),('Nynorsk, Norwegian','nn'),('Occidental','ie'),('Occitan','oc'),('Ojibwa','oj'),('Old Bulgarian','cu'),('Old Church Slavonic','cu'),('Old Slavonic','cu'),('Oriya','or'),('Oromo','om'),('Ossetian','os'),('Ossetic','os'),('Pali','pi'),('Panjabi','pa'),('Pashto','ps'),('Persian','fa'),('Polish','pl'),('Portuguese','pt'),('ProvenÃ§al','oc'),('Punjabi','pa'),('Pushto','ps'),('Quechua','qu'),('Romanian','ro'),('Romansh','rm'),('Rundi','rn'),('Russian','ru'),('Samoan','sm'),('Sango','sg'),('Sanskrit','sa'),('Sardinian','sc'),('Scottish Gaelic','gd'),('Serbian','sr'),('Shona','sn'),('Sichuan Yi','ii'),('Sindhi','sd'),('Sinhala','si'),('Sinhalese','si'),('Slovak','sk'),('Slovenian','sl'),('Somali','so'),('Sotho, Southern','st'),('South Ndebele','nr'),('Spanish','es'),('Sundanese','su'),('Swahili','sw'),('Swati','ss'),('Swedish','sv'),('Tagalog','tl'),('Tahitian','ty'),('Tajik','tg'),('Tamil','ta'),('Tatar','tt'),('Telugu','te'),('Thai','th'),('Tibetan','bo'),('Tigrinya','ti'),('Tonga','to'),('Tsonga','ts'),('Tswana','tn'),('Turkish','tr'),('Turkmen','tk'),('Twi','tw'),('Uighur','ug'),('Ukrainian','uk'),('Urdu','ur'),('Uyghur','ug'),('Uzbek','uz'),('Valencian','ca'),('Venda','ve'),('Vietnamese','vi'),('VolapÃ¼k','vo'),('Walloon','wa'),('Welsh','cy'),('Western Frisian','fy'),('Wolof','wo'),('Xhosa','xh'),('Yiddish','yi'),('Yoruba','yo'),('Zhuang','za'),('Zulu','zu');
/*!40000 ALTER TABLE `xms_tbl_language_code` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-02 16:35:09

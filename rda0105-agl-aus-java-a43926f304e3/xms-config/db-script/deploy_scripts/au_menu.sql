select * from xms_tbl_menu where parentid = 1;
select * from xms_tbl_menu where parentid = 23;

select * from xms_tbl_menu where url like 'XMS2%';
 -- XMS2:/credit_notes.ix
 -- accounts/search_customers.php
 -- accounts/customer_list.php
 
-- UPDATE `xms_au`.`xms_tbl_menu` SET `url`='XMS2:/search_customers.ix' WHERE `menuid`='25';
-- UPDATE `xms_au`.`xms_tbl_menu` SET `url`='XMS2:/customer_list.ix' WHERE `menuid`='96';
select * from xms_tbl_menu where parentid = 26;
-- accounts/franchise_list.php
-- UPDATE `xms_au`.`xms_tbl_menu` SET `url`='XMS2:/franchise_list.ix' WHERE `menuid`='28';
select * from xms_tbl_menu where parentid = 29;
-- accounts/manage_users.php
-- UPDATE `xms_au`.`xms_tbl_menu` SET `url`='XMS2:/manage_users.ix' WHERE `menuid`='30';
-- 20160222


-- menu for warranty

INSERT INTO `xms_tbl_localization` (`localizationid`, `localizationname`, `parentid`, `setup`, `default_text`) VALUES ('', 'Warranty', '54,335,281,288,289,333,314,285,383,292,227,301,306,304,294,342,297,299,321,318,361,363,362,1865,359,356,344,341,867,366,870,927,985,1066,2366,368', '0', 'Warranty');
INSERT INTO `xms_tbl_localization` (`localizationid`, `localizationname`, `parentid`, `setup`, `default_text`) VALUES ('', 'Webship History Report', '54,335,281,288,289,333,314,285,383,292,227,301,306,304,294,342,297,299,321,318,361,363,362,1865,359,356,344,341,867,366,870,927,985,1066,2366,368', '0', 'Webship History Report');
INSERT INTO `xms_tbl_localization` (`localizationid`, `localizationname`, `parentid`, `setup`, `default_text`) VALUES ('', 'Invoice History Report', '54,335,281,288,289,333,314,285,383,292,227,301,306,304,294,342,297,299,321,318,361,363,362,1865,359,356,344,341,867,366,870,927,985,1066,2366,368', '0', 'Invoice History Report');
INSERT INTO `xms_tbl_menu` (`menuid`, `menuname`, `parentid`, `srno`, `user_level_id`, `other_level`, `show_collector`, `hidden`, `url`, `localizationid`) VALUES (NULL, 'Warranty', '5', '5', '1.0', '2.0,3.0', '0', '0', '#', (select localizationid from xms_tbl_localization where localizationname = 'Warranty'));
INSERT INTO `xms_tbl_menu` (`menuid`,`menuname`,`parentid`,`srno`,`user_level_id`,`other_level`,`show_collector`,`hidden`,`url`,`localizationid`) VALUES (null,'Webship History Report',152,1,3.0,'',0,0,'XMS2:/webship_label.ix',(select localizationid from xms_tbl_localization where localizationname = 'Webship History Report'));
INSERT INTO `xms_tbl_menu` (`menuid`,`menuname`,`parentid`,`srno`,`user_level_id`,`other_level`,`show_collector`,`hidden`,`url`,`localizationid`) VALUES (null,'Invoice History Report',152,2,3.0,'',0,0,'XMS2:/invoiced_airbill.ix',(select localizationid from xms_tbl_localization where localizationname = 'Invoice History Report'));



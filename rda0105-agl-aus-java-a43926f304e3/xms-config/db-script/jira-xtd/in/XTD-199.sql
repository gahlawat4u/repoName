/**
date: 24/03/2017
author: thanh
purpose: delete duplicate credit note and set unique for it can't be duplicate again
**/

DELETE FROM xms_tbl_credit_note_detail WHERE credit_note_id = '102' limit 1;
DELETE FROM xms_tbl_credit_note_detail WHERE credit_note_id = '103' limit 1;
DELETE FROM xms_tbl_credit_note_detail WHERE credit_note_id = '104' limit 1;

ALTER IGNORE TABLE xms_tbl_credit_note_detail ADD UNIQUE (adjustmentid);

--For MySQL 5.7.4 or later:
--ALTER TABLE mytbl ADD UNIQUE (columnName);
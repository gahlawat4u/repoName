TRUNCATE TABLE tmp_weekly_customer_status_report;

ALTER TABLE tmp_weekly_customer_status_report
ADD COLUMN `start_date` date DEFAULT NULL AFTER `rpt_txn_id`,
ADD COLUMN `end_date` date DEFAULT NULL AFTER `start_date`,
ADD COLUMN `carrier` INT(11) DEFAULT NULL AFTER `end_date`,
DROP COLUMN `period`;

TRUNCATE TABLE tmp_monthly_customer_status_report;

ALTER TABLE tmp_monthly_customer_status_report
ADD COLUMN `start_date` date DEFAULT NULL AFTER `rpt_txn_id`,
ADD COLUMN `end_date` date DEFAULT NULL AFTER `start_date`,
ADD COLUMN `carrier` INT(11) DEFAULT NULL AFTER `end_date`,
DROP COLUMN `period`;
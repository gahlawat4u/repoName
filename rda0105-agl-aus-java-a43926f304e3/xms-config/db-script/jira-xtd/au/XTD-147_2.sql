/**
date: 28/03/2017
author: huynh
purpose: Fix some old data wrong
**/

-- those airbill Request Date at 22-11-2016 and Response Date at 31-01-2017 but pausing day still = 0.
-- It should be 73 days
update xms_tbl_airbill_pausing_deduct set pausing_day=73 where airbill_number = '980182886253';

-- clear frozen report cache to re-calculate
-- update xms_tbl_period set franchise_payable_status =0, rpt_txn_id = '' where start_date in ('2016-12-05','2017-01-16','2017-01-23');
-- restore
update xms_tbl_period set franchise_payable_status =2, rpt_txn_id = '0b91e9ba-d123-40e7-b89d-80446c4e466b' where start_date = '2016-12-05';
update xms_tbl_period set franchise_payable_status =2, rpt_txn_id = '2efe31d4-a36e-47fa-ae36-cfd892cec8a9' where start_date = '2017-01-16';
update xms_tbl_period set franchise_payable_status =2, rpt_txn_id = '46e1ce4b-31d5-48ef-82c0-0d13586fce50' where start_date = '2017-01-23';

-- Fix 05dec - 11-dec 2016
update tmp_xms_tbl_ms_rpt_fran_pab_margin set customer_total_exc_gst = 1980.00, customer_total_gst = 198.00, franchise_cost_exc_gst = 1980.00, franchise_cost_gst = 198.00
WHERE	rpt_txn_id = '0b91e9ba-d123-40e7-b89d-80446c4e466b' AND franchise_code IN ('136') and airbill_number = '12063942';

-- remove deduct for 16-22 JAN deduct
DELETE FROM tmp_xms_tbl_ms_rpt_fran_pab_deduct WHERE rpt_txn_id = '2efe31d4-a36e-47fa-ae36-cfd892cec8a9' and airbill_number = '12063942';

-- Fix 23-29 Jan 61+day
update tmp_xms_tbl_ms_rpt_fran_pab_61days set previously_paid = 0.00, profit_share_exc_gst =0.00,profit_share_on_late_fees =0.00,profit_share_gst=0.00
WHERE	rpt_txn_id = '46e1ce4b-31d5-48ef-82c0-0d13586fce50' AND franchise_code IN ('136') and airbill_number = '12063942';

-- 2017-03-20
-- update xms_tbl_period set franchise_payable_status =0, rpt_txn_id = '' where start_date in ('2017-03-20');
-- revert
-- update xms_tbl_period set franchise_payable_status =2, rpt_txn_id = 'c7cd8ead-28e0-4c25-ac7e-2655b7d0fd3d' where start_date in ('2017-03-20');
update xms_tbl_period set franchise_payable_status =2, rpt_txn_id = '74ad1ada-a49e-499a-a26c-8cdada9079a1' where start_date in ('2017-03-27');


-- move deduct row from 27-mar -> 20-mar
-- DELETE from tmp_xms_tbl_ms_rpt_fran_pab_deduct WHERE airbill_number = '28AZ50002105' and rpt_txn_id = '74ad1ada-a49e-499a-a26c-8cdada9079a1';
INSERT INTO `tmp_xms_tbl_ms_rpt_fran_pab_deduct` ( `rpt_txn_id`, `franchise_code`, `customer_name`, `invoice_number`, `airbill_number`, `customer_payment`, `customer_cost`, `customer_tax`, `franchise_cost`, `franchise_tax`, `franchise_charge`)
VALUES ( 'c7cd8ead-28e0-4c25-ac7e-2655b7d0fd3d', '133', 'MIRACLE IMAGE MARKETING', '13300126QG18', '28AZ50002105', '128.70', '167.48', '16.74', '143.90', '14.39', '29.59');


-- update xms_tbl_airbill_pausing_deduct set pausing_day=188 where airbill_number = '28AZ50002105';
-- revert to old value
-- update xms_tbl_airbill_pausing_deduct set pausing_day=104 where airbill_number = '28AZ50002105';


-- revert back deletion above to fix comment on 28-Apr-2018 XTD-147
INSERT INTO `tmp_xms_tbl_ms_rpt_fran_pab_deduct` (`rpt_txn_id`, `franchise_code`, `customer_name`, `invoice_number`, `airbill_number`, `customer_payment`, `customer_cost`, `customer_tax`, `franchise_cost`, `franchise_tax`, `franchise_charge`)
VALUES ('74ad1ada-a49e-499a-a26c-8cdada9079a1', '133', 'MIRACLE IMAGE MARKETING', '13300126QG18', '28AZ50002105', '184.22', '167.48', '16.74', '143.90', '14.39', '55.52');

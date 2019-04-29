/**
date: 28/03/2017
author: huynh
purpose: Fix some old data wrong
**/

-- those airbill Request Date at 22-11-2016 and Response Date at 31-01-2017 but pausing day still = 0.
-- It should be 70 days
update xms_tbl_airbill_pausing_deduct set pausing_day = 70 where airbill_number in ('IXX500301261','IXX500301786')
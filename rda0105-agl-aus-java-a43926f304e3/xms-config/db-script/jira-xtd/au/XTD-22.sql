/**
date: 17/07/2017
author: thanh
purpose: delete duplicate adjustment
**/

DELETE FROM xms_tbl_airbill_adjustment WHERE adjustmentid = '19050' AND airbill_number = '4INX0332' AND reason_for_deleting = 'duplication';
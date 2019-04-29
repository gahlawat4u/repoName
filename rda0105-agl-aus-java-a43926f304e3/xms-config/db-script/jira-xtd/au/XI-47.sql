/**
date: 06/07/2017
author: thanhbc
purpose: increase length for column 'town'
target: xms_au
**/

-- For not allow add piece
ALTER TABLE xms_tbl_toll_remote_area MODIFY  COLUMN town VARCHAR(100);

-- if not run,use this
-- ALTER TABLE xms_tbl_toll_remote_area CHANGE  COLUMN town VARCHAR(100);


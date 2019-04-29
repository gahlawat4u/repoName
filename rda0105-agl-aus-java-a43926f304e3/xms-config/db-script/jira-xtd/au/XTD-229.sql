/**
date: 12/04/2017
author: anhtu
purpose: Fix bug can't add new Sales Rep under Payables > Sales Reps > Manage Sales Reps & Commissions
target: xms_au
**/

-- modify column terminate_date on table xms_tbl_sales_rep from not null to nullable

alter table xms_tbl_sales_rep modify column terminate_date datetime null;
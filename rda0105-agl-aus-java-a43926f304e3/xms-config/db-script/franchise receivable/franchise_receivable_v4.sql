-- alter table for keep more information frozen data.
ALTER TABLE `tmp_xms_tbl_fran_rab_overview` 
ADD COLUMN `management_fee_on_credit_revenue` DECIMAL(24,6) NULL AFTER `total_receivable`,
ADD COLUMN `management_fee_on_credit_profit_shared` DECIMAL(24,6) NULL AFTER `management_fee_on_credit_revenue`;

-- Add setting for Management Fee on Credit Revenue Percent.
insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level)
values ('Management Fee On Credit Revenue Percent',9,'',1);

-- Add setting for Management Fee on Credit Profit Shared Percent.
insert into xms_tbl_system_setting (setting_name, setting_value, description, user_level)
values ('Management Fee On Credit Profit Shared Percent',30,'',1);
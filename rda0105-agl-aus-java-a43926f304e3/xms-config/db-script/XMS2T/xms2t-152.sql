ALTER TABLE `xms_tbl_webship` 
ADD COLUMN `reset_password_code` VARCHAR(10) NULL AFTER `reset_password_status`,
ADD UNIQUE INDEX `reset_password_code_UNIQUE` (`reset_password_code` ASC);
